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

    private ObtainSizeTask obtainSizeTask;
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
        obtainSizeTask = new ObtainSizeTask(url);

    }
}
