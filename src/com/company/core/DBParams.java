package com.company.core;

public class DBParams {

    public static final String DBPath = "src/com/company/db";
    public static final  int pageSize = 4096;
    public static final int maxPagesPerFile = 4;
    public static int frameCount;

    private DBParams() {
    }

    public static int getFrameCount() {
        return frameCount;
    }

    public static void setFrameCount(int frameCount) {
        DBParams.frameCount = frameCount;
    }

    @Override
    public String toString() {
        return "DBParams{" +
                "DBPath='" + DBPath + '\'' +
                ", pageSize=" + pageSize +
                ", maxPagesPerFile=" + maxPagesPerFile +
                '}';
    }
}
