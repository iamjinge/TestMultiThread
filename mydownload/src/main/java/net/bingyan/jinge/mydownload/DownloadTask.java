package net.bingyan.jinge.mydownload;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2015/9/28.
 */
public class DownloadTask extends DTask {
    private int start;
    private int end;
    private String fileName;

    public DownloadTask(String url, String fileName) {
        super(url);
        this.fileName = fileName;
    }

    @Override
    protected void buildConnection() {

    }

    @Override
    protected void setCallback(DCallback callback) {

    }

    @Override
    public void run() {

    }
}
