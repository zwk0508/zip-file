package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

/**
 * 文件或文件夹名称不能是对应的文件名
 */
public class EqualsFilenameIgnoreFilter implements FilenameFilter {
    private String fileName;

    public EqualsFilenameIgnoreFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !Objects.equals(name, fileName);
    }
}
