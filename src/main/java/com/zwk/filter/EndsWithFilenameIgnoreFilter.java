package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件或文件夹名称不能以对应的文件名结尾
 */
public class EndsWithFilenameIgnoreFilter implements FilenameFilter {
    private String fileName;

    public EndsWithFilenameIgnoreFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return !name.endsWith(fileName);
    }
}
