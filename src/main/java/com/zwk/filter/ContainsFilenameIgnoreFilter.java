package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件或文件夹名称中不能包含对应的文件名
 */
public class ContainsFilenameIgnoreFilter implements FilenameFilter {
    private String fileName;

    public ContainsFilenameIgnoreFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.contains(fileName);
    }
}
