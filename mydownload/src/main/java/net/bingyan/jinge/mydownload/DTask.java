package net.bingyan.jinge.mydownload;

import java.net.HttpURLConnection;

/**
 * Created on 2015/9/29.
 */
public abstract class DTask implements Runnable {

    protected static int PRI_GET_SIZE = 200;
    protected static int PRI_DOWNLOAD = 100;

    protected String url;
    protected int priority;
    protected HttpURLConnection connection;
    protected DCallback callback;

    public DTask(String url) {
        this.url = url;
        setPriority(0);
    }

    protected void setCallback(DCallback callback){
        this.callback = callback;
    }

    public abstract void setPriority(int priority);

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPriority() {
        return priority;
    }
    
}
