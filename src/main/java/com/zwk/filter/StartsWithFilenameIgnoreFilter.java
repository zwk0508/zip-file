package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件或文件夹名称不能以对应的文件名开始
 */
public class StartsWithFilenameIgnoreFilter implements FilenameFilter {
    private String fileName;

    public StartsWithFilenameIgnoreFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.startsWith(fileName);
    }
}
