package net.bingyan.jinge.mydownload;

import net.bingyan.jinge.mydownload.old.TaskBinTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2015/9/29.
 */
public class DownloadWork {
    private boolean workLive = true;
    private boolean workOn = true;

    private String url;
    private String path;
    private int size;
    private int parts;
    private int partSize;

    private GetSizeTask getSizeTask;
    private List<DownloadTask> downloadTasks = new ArrayList<>();

    public DownloadWork(String url) {
        this.url = url;
        initObtainSizeTask();
    }

    public DownloadWork(String url, String path) {
        this.url = url;
        this.path = path;
        initObtainSizeTask();
    }

    private void initObtainSizeTask() {
        getSizeTask = new GetSizeTask(url);
        DownloadManager.addTask(getSizeTask);
    }

    public void onSizeGet(int size) {
        this.size = size;
        if (size >> PowerOf2.M2 <= 1) {
            DownloadTask downloadTask = new DownloadTask(url, path, 0, 0, size);
            downloadTasks.add(downloadTask);
            DownloadManager.addTask(downloadTask);
            parts = 1;
            partSize = PowerOf2.M2;
        } else if (size <= PowerOf2.M8) {
            int i;
            for (i = 0; i < size >> PowerOf2.M2; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path, i,
                        i << PowerOf2.M2, (i + 1) << PowerOf2.M2 - 1);
                downloadTasks.add(downloadTask);
                DownloadManager.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path, i,
                    i << PowerOf2.M2, size - 1);
            downloadTasks.add(downloadTask);
            DownloadManager.addTask(downloadTask);
            parts = ++i;
            partSize = PowerOf2.M2;
        } else if (size <= PowerOf2.M32) {
            int i;
            for (i = 0; i < size >> PowerOf2.M8; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path, i,
                        i << PowerOf2.M8, (i + 1) << PowerOf2.M8 - 1);
                downloadTasks.add(downloadTask);
                DownloadManager.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path, i,
                    i << PowerOf2.M8, size - 1);
            downloadTasks.add(downloadTask);
            DownloadManager.addTask(downloadTask);
            parts = ++i;
            partSize = PowerOf2.M8;
        } else {
            int i;
            for (i = 0; i < size >> PowerOf2.M32; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path, i,
                        i << PowerOf2.M32, (i + 1) << PowerOf2.M32 - 1);
                downloadTasks.add(downloadTask);
                DownloadManager.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path, i,
                    i << PowerOf2.M32, size - 1);
            downloadTasks.add(downloadTask);
            DownloadManager.addTask(downloadTask);
            parts = ++i;
            partSize = PowerOf2.M32;
        }
    }

    public void onPercentGet(int partId, int percent) {
        downloadTasks.get(partId);
    }

    public boolean isWorkLive() {
        return workLive;
    }

    public void setWorkLive(boolean workLive) {
        this.workLive = workLive;
    }

    public boolean isWorkOn() {
        return workOn;
    }

    public void setWorkOn(boolean workOn) {
        this.workOn = workOn;
    }
}
