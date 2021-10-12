package com.company.core;

import java.util.ArrayList;
import java.util.List;

public class DBPageId {


    private List<PageId> pageIdList = new ArrayList<>();

    public DBPageId() {
        pageIdList = new ArrayList<>();
    }

    public List<PageId> getPageIdList() {
        return pageIdList;
    }

    public void add(PageId pageId) {
        pageIdList.add(pageId);
    }
}
