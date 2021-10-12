package com.company.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class DiskManager {

    private DBPageId dBpageId;

    public DiskManager() {
    dBpageId = new DBPageId();

    }

    public PageId AllocPage() throws IOException {

        PageId id = new PageId(0, 0);
        File fich = new File(DBParams.DBPath);

        File[] fichList = fich.listFiles();

        int trouve = -1;
        for (int i = 0; i < fichList.length; i++) {
            if (fichList[i].length() < DBParams.pageSize * DBParams.maxPagesPerFile)
                trouve = i;
            break;

        }

        if (trouve == -1) {

            File fichier = new File(DBParams.DBPath + "\\F" + fichList.length + ".df");

            if (fichier.createNewFile()) {
                System.out.println("new file created");
            } else {
                System.out.println("file already exists");
            }
            try {

                RandomAccessFile f = new RandomAccessFile(fichier, "rw");
                byte[] buffer = new byte[DBParams.pageSize];
                f.write(buffer);
                id = new PageId(fichList.length, 0);
                dBpageId.add(id);
                f.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {

            File file = fichList[trouve];

            int pageIdx = (int) (file.length() / DBParams.pageSize);

            PageId pageId = dBpageId.getPageIdList().get(pageIdx-1);

            this.WritePage(pageId, new byte[DBParams.pageSize*pageIdx]);
        }

        return id;
    }

    public void readPage(PageId pageId, byte[] content) {
        content = new byte[DBParams.pageSize];
        RandomAccessFile disk = null;
        try {
            File diskFile = new File(DBParams.DBPath + "\\F" + pageId.getFileIdx() + ".df");
            disk = new RandomAccessFile(diskFile, "rw");
            disk.seek((long) (pageId.getPageIdX()) * DBParams.maxPagesPerFile);
            System.out.println(disk.readByte());;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (disk != null) {
                try {
                    disk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void WritePage(PageId pageId, byte[] content) {
        RandomAccessFile disk = null;
        try {
            File diskFile = new File(DBParams.DBPath + "\\F" + pageId.getFileIdx() + ".df");
            if(diskFile.createNewFile()){
                System.out.println("New file created: "+diskFile.getName());
            }
            disk = new RandomAccessFile(diskFile, "rw");
            disk.seek((long) (pageId.getPageIdX()) * DBParams.pageSize);
            disk.write(content);
            dBpageId.add(pageId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (disk != null) {
                try {
                    disk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
