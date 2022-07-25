package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

public class EndsWithFilenameFilter implements FilenameFilter {
    private String fileName;

    public EndsWithFilenameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.endsWith(fileName);
    }
}
