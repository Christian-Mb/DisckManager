package com.company.buffer;

import com.company.core.DBParams;
import com.company.core.PageId;

public class Frame {

    private byte[] buffer;
    private PageId pageId;
    private int pin_count;
    private int last_pin_count;
    private int dirty;


    public Frame() {
        buffer = new byte[DBParams.pageSize];
        pin_count = 0;
        last_pin_count = 0;
        pageId = null;
        dirty = 0;
    }


    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public PageId getPageId() {
        return pageId;
    }

    public void setPageId(PageId pageId) {
        this.pageId = pageId;
    }

    public int getPin_count() {
        return pin_count;
    }

    public void setPin_count(int pin_count) {
        this.pin_count = pin_count;
    }

    public int getDirty() {
        return dirty;
    }

    public void setDirty(int dirty) {
        this.dirty = dirty;
    }

    public int getLast_pin_count() {
        return last_pin_count;
    }

    public void setLast_pin_count(int last_pin_count) {
        this.last_pin_count = last_pin_count;
    }
}
