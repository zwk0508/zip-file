package com.zwk.zip;

import com.zwk.filter.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    private String source;
    private String dest;
    private String[] ignore;
    private File destFile;

    private FilenameFilter filter;
    private static final int BUFFER_SIZE = 2048;

    public Zipper(String source, String dest, String... ignore) {
        this.source = source;
        this.dest = dest;
        this.ignore = ignore;
    }

    public void zip() throws IOException {
        File s = new File(source);
        if (!s.exists()) {
            return;
        }
        File d = getDestFile(s);
        destFile = d;
        parse(ignore);
        zip(s, d);
    }

    private void zip(File source, File dest) throws IOException {
        FilenameFilter filter = this.filter;
        if (filter != null && !filter.accept(null, source.getName())) {
            return;
        }
        try (ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(dest))) {
            zip(source, outputStream, null);
        }
    }

    private void zip(File source, ZipOutputStream outputStream, String base) throws IOException {
        FilenameFilter filter = this.filter;
        String nextBase = base == null ? source.getName() : base + "/" + source.getName();
        if (source.isFile()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            outputStream.putNextEntry(new ZipEntry(nextBase));
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(source))) {
                int read;
                while ((read = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, read);
                }
            }
        } else {
            outputStream.putNextEntry(new ZipEntry(nextBase + "/"));
            File[] files;
            if (filter == null) {
                files = source.listFiles();
            } else {
                files = source.listFiles(filter);
            }
            if (files == null || files.length == 0) {
                return;
            }
            for (File file : files) {
                if (base == null && destFile.equals(file)) {
                    continue;
                }
                zip(file, outputStream, nextBase);
            }
        }
    }

    private void parse(String[] ignore) {
        if (ignore == null || ignore.length == 0) {
            return;
        }
        List<FilenameFilter> list = new ArrayList<>();
        filter = new CompositeFilenameFilter(list);
        for (String s : ignore) {
            boolean startsWith = s.startsWith("*");
            boolean endsWith = s.endsWith("*");
            if (startsWith && endsWith) {
                list.add(new ContainsFilenameFilter(s.substring(1, s.length() - 1)));
            } else if (startsWith) {
                list.add(new EndsWithFilenameFilter(s.substring(1)));
            } else if (endsWith) {
                list.add(new StartsWithFilenameFilter(s.substring(0, s.length() - 1)));
            } else {
                list.add(new EqualsFilenameFilter(s));
            }
        }
    }

    private File getDestFile(File source) {
        File d = new File(dest);
        if (d.exists()) {
            if (d.isDirectory()) {
                d = getDestFile(source, d);
            }
        } else {
            if (dest.endsWith(".zip")) {
                d.getParentFile().mkdirs();
            } else {
                d.mkdirs();
                d = this.getDestFile(source, d);
            }
        }
        return d;
    }

    private File getDestFile(File source, File dest) {
        String sourceName = source.getName();
        if (source.isDirectory()) {
            dest = new File(dest, sourceName + ".zip");
        } else {
            int index = sourceName.lastIndexOf(".");
            String fileName;
            if (index > -1) {
                fileName = sourceName.substring(0, index) + ".zip";
            } else {
                fileName = sourceName + ".zip";
            }
            dest = new File(dest, fileName);
        }
        return dest;
    }


}
