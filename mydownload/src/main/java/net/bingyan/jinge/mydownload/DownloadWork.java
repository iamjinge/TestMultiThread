package net.bingyan.jinge.mydownload;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2015/9/29.
 */
public class DownloadWork {

    private String url;
    private String path;
    private int size;

    private GetSizeTask getSizeTask;
    private List<DownloadTask> downloadTasks = new ArrayList<>();

    private TaskBinTree taskBinTree = TaskBinTree.getInstance();

    public DownloadWork(String url) {
        this.url = url;
    }

    public DownloadWork(String url, String path) {
        this.url = url;
        this.path = path;
    }

    private void initObtainSizeTask() {
        getSizeTask = new GetSizeTask(url);
        taskBinTree.addTask(getSizeTask);
    }

    public void onSizeGet(int size) {
        this.size = size;
        if (size <= DataType.M2) {
            DownloadTask downloadTask = new DownloadTask(url, path);
            taskBinTree.addTask(downloadTask);
        } else if (size <= DataType.M8) {
            for (int i = 0; i < size / DataType.M2; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path);
                
            }
        }
    }
}
