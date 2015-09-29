package net.bingyan.jinge.mydownload;

import java.net.HttpURLConnection;

/**
 * Created on 2015/9/29.
 */
public abstract class DTask implements Runnable {
    protected String url;
    protected int priority;
    protected HttpURLConnection connection;
    protected DCallback callback;

    public DTask(String url) {
        this.url = url;
    }

    protected abstract void buildConnection();

    protected abstract void setCallback(DCallback callback);


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public interface DCallback {
        void getSize(String url, int size);

        void getPecentage(String url, int size);
    }
}
