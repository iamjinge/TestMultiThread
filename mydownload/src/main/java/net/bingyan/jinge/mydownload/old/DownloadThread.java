package net.bingyan.jinge.mydownload.old;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2015/9/29.
 */
public class DownloadThread implements Runnable {
    private String url;
    private String fileName;
    private HttpURLConnection connection;

    public DownloadThread (){

    }

    public DownloadThread(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface DCallback{
        void getSize(String url, int size);

        void getPecentage(String url, int size);
    }
}
