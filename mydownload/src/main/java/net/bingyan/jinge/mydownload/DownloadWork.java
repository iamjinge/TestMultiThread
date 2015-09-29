package net.bingyan.jinge.mydownload;

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
        if (size >> PowerOf2.M2 <= 1) {
            DownloadTask downloadTask = new DownloadTask(url, path, 0, size);
            taskBinTree.addTask(downloadTask);
        } else if (size <= PowerOf2.M8) {
            int i;
            for (i = 0; i < size >> PowerOf2.M2; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path,
                        i >> PowerOf2.M2, (i+1) >> PowerOf2.M2 -1);
                taskBinTree.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path,
                    i >> PowerOf2.M2, size - 1);
            taskBinTree.addTask(downloadTask);
        }else if (size <= PowerOf2.M32) {
            int i;
            for (i = 0; i < size >> PowerOf2.M8; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path,
                        i >> PowerOf2.M8, (i+1) >> PowerOf2.M8 -1);
                taskBinTree.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path,
                    i >> PowerOf2.M8, size - 1);
            taskBinTree.addTask(downloadTask);
        } else {
            int i;
            for (i = 0; i < size >> PowerOf2.M32; i++) {
                DownloadTask downloadTask = new DownloadTask(url, path,
                        i >> PowerOf2.M32, (i+1) >> PowerOf2.M32 -1);
                taskBinTree.addTask(downloadTask);
            }
            DownloadTask downloadTask = new DownloadTask(url, path,
                    i >> PowerOf2.M32, size - 1);
            taskBinTree.addTask(downloadTask);
        }
    }
}
