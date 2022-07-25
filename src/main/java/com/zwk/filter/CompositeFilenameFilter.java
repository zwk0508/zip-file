package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * 组合的文件名过滤器
 */
public class CompositeFilenameFilter implements FilenameFilter {
    private List<FilenameFilter> list;

    public CompositeFilenameFilter(List<FilenameFilter> list) {
        this.list = list;
    }

    @Override
    public boolean accept(File dir, String name) {
        //只要有一个不符合，则忽略当前文件或文件夹
        for (FilenameFilter filter : list) {
            if (!filter.accept(dir, name)) {
                return false;
            }
        }
        return true;
    }
}