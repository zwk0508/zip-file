package com.zwk;

import com.zwk.zip.Zipper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            return;
        }
        String source = null;
        String dest = null;
        String[] ignore = null;
        int i = 0;
        int paramLen = args.length;
        while (i < args.length) {
            String arg = args[i];
            switch (arg) {
                case "-source":
                    if (i + 1 < paramLen) {
                        source = args[i + 1];
                    }
                    break;
                case "-dest":
                    if (i + 1 < paramLen) {
                        dest = args[i + 1];
                    }
                    break;
                case "-ignore":
                    if (i + 1 < paramLen) {
                        ignore = args[i + 1].split(",");
                    }
                    break;
                default:
            }
            i += 2;
        }
        if (source == null) {
            System.err.println("source not found");
            return;
        }
        if (dest == null) {
            System.err.println("dest not found");
            return;
        }
        zip(source, dest, ignore);
    }

    public static void zip(String source, String dest, String... ignore) throws IOException {
        new Zipper(source, dest, ignore).zip();
    }
}
