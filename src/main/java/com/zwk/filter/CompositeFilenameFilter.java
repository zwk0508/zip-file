package com.zwk.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class CompositeFilenameFilter implements FilenameFilter {
    private List<FilenameFilter> list;

    public CompositeFilenameFilter(List<FilenameFilter> list) {
        this.list = list;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (FilenameFilter filter : list) {
            if (!filter.accept(dir, name)) {
                return false;
            }
        }
        return true;
    }
}