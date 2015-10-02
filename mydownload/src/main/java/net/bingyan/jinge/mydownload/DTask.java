package net.bingyan.jinge.mydownload;

import android.support.annotation.NonNull;

import java.net.HttpURLConnection;

/**
 * Created on 2015/9/29.
 */
public abstract class DTask implements Runnable, Comparable {

    protected static int PRI_GET_SIZE = 200;
    protected static int PRI_DOWNLOAD = 100;

    protected String url;
    protected int priority;
    protected HttpURLConnection connection;
    protected DCallback callback;

    protected boolean taskOn;
    protected boolean taskLive;

    public DTask(String url) {
        this.url = url;
        setPriority(0);
    }

    @Override
    public int compareTo(@NonNull Object another) {
        int anPriority = ((DTask) another).getPriority();
        if (priority > anPriority) {
            return -1;
        } else {
            return 1;
        }
    }

    protected void setCallback(DCallback callback) {
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

    public void setTaskOn(boolean taskOn) {
        this.taskOn = taskOn;
    }

    public void setTaskLive(boolean taskLive) {
        this.taskLive = taskLive;
    }
}
