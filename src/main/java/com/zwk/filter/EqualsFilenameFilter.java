package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

public class EqualsFilenameFilter implements FilenameFilter {
    private String fileName;

    public EqualsFilenameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !Objects.equals(name, fileName);
    }
}
