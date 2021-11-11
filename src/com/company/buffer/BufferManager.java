package com.company.buffer;


import com.company.core.DBParams;
import com.company.core.PageId;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class BufferManager {

    private Frame frames[];

    /*
     * compteur pour faire MRU, on increment a chaque fois qd
     * on incremente ou decremente le pincount
     */
    private int compteur;


    public BufferManager() {
        frames = new Frame[DBParams.frameCount];
        compteur = 0;
    }

    /*
     * cherche si la page se trouve dans le buffer pool
     */
    public Frame existePage(PageId pageId) {
        for (Frame frame : frames) {
            if (frame.getPageId().equals(pageId)) {
                return frame;
            }
        }
        return null;
    }

    /*
     * Get empty frame
     */
    public Frame getEmptyFrame(PageId pageId) {
        for (Frame frame : frames) {
            if (frame.getPageId() == null && frame.getPin_count() == 0 && frame.getDirty() == 0) {
                frame.setPageId(pageId);
                frame.setPin_count(frame.getPin_count() + 1);
                compteur++;

                return frame;
            } else {
                int n = 0;
                int value = 0;

                for (int i = 0; i < frames.length; i++) {
                    if (value < frames[i].getLast_pin_count()) {
                        value = frames[i].getLast_pin_count();
                        n = i;
                    }
                }

                frames[n].setPageId(pageId);
                frames[n].setPin_count(frames[n].getPin_count() + 1);
                compteur++;
                return frames[n];
            }
        }
        return null;
    }

    public byte[] getPage(PageId pageId) {

        Frame frame = existePage(pageId);

        // condition 1 frame exist
        if (frame != null) {
            //changer le pin count
            compteur++;
            return frame.getBuffer();
        }

        /*
         * condition 2 si on ne trouve pas la page dans le case
         * on cherche s'il y a une case vide
         *
         */
        else {
            return getEmptyFrame(pageId).getBuffer();

        }
    }


    public void FreePage(PageId pageId, boolean valdirty) {

        Frame frame = new Frame();
        /*
         */
        frame = existePage(pageId);
        if (frame != null) {

            frame.setPin_count(frame.getPin_count() - 1);
            compteur++;
            if (frame.getPin_count() == 0) {
                frame.setLast_pin_count(compteur);
            }
            if (valdirty) {
                frame.setDirty(frame.getDirty() + 1);
            }
        }
    }

    public void FlushAll() {
        for (Frame frame : frames) {
            if (frame.getDirty() == 1) {
                try {
                    RandomAccessFile wr = new RandomAccessFile(DBParams.DBPath, "rw");
                } catch (FileNotFoundException e) {
                    System.out.println("fichier n'existe pas ");
                    e.printStackTrace();
                }
                frame.setPin_count(0);
                frame.setDirty(0);
                frame.setPageId(null);
                frame.setLast_pin_count(0);
                frame.setBuffer(null);
                compteur = 0;

            }
        }
    }

}



