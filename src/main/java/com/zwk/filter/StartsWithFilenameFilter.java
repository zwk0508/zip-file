package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

public class StartsWithFilenameFilter implements FilenameFilter {
    private String fileName;

    public StartsWithFilenameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.startsWith(fileName);
    }
}
