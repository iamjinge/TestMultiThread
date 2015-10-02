package net.bingyan.jinge.mydownload;

import java.io.Serializable;

/**
 * Created on 2015/10/2.
 */
public class DownloadData implements Serializable {
    public static final String TABLE_NAME = "downloadTable";
    public static final String ID = "id";
    public static final String WORK_ID = "work_id";
    public static final String START = "start";
    public static final String END = "end";

    private int id;
    private String url;
    private int start;
    private int end;
}
