package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

public class ContainsFilenameFilter implements FilenameFilter {
    private String fileName;

    public ContainsFilenameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.contains(fileName);
    }
}
