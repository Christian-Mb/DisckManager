package com.company.core;

import java.util.ArrayList;
import java.util.List;

public class PageId {

    private int fileIdx;
    private int pageIdX;

    private static final List<PageId> pageIdList = new ArrayList<>();

    public PageId(int fileIdx, int pageIdX) {
        this.fileIdx = fileIdx;
        this.pageIdX = pageIdX;

    }

    public PageId() {
    }

    public void add(PageId pageId){
        pageIdList.add(pageId);
    }

    public static List<PageId> getPageIdList() {
        return pageIdList;
    }

    public int getFileIdx() {
        return fileIdx;
    }

    public void setFileIdx(int fileIdx) {
        this.fileIdx = fileIdx;
    }

    public int getPageIdX() {
        return pageIdX;
    }

    public void setPageIdX(int pageIdX) {
        this.pageIdX = pageIdX;
    }

    @Override
    public String toString() {
        return "PageID{" +
                "fileIdx=" + fileIdx +
                ", pageIdX=" + pageIdX +
                '}';
    }
}
