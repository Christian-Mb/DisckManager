package com.company.core;

import java.io.IOException;

public class DiskManagerTests {


    public static void main(String[] args) throws IOException {
        DiskManager diskManager = new DiskManager();
     /*   PageId pageId = new PageId(0, 0);

        System.out.println("je suiss");
        diskManager.WritePage(pageId, new byte[4096*4]);*/

        // diskManager.readPage(pageId,new byte[4096]);

        for (int i = 0; i < 2; i++) {
            System.out.println("test : " + i + " " + diskManager.AllocPage());
        }


    }
}
