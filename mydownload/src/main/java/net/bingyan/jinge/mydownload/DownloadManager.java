package net.bingyan.jinge.mydownload;

import net.bingyan.jinge.mydownload.old.TaskBinTree;

import java.util.concurrent.ExecutorService;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created on 2015/9/29.
 */
public class DownloadManager implements DCallback {
    private static int MIN_SIZE = 1;
    private static int MAX_SIZE = 16;
    private static DownloadManager instance;
    private static ExecutorService threadPool;
    private BlockingQueue<Runnable> taskQueue = new PriorityBlockingQueue<>();
    private int threadSize;
    private boolean running;
    private boolean alive;
    private Map<String, DownloadWork> works = new HashMap<String, DownloadWork>();

    private DownloadManager() {
        threadPool = Executors.newFixedThreadPool(threadSize);
        taskQueue.add(new DownloadTask("", "", 1));
        threadPool = new ThreadPoolExecutor(4, MAX_SIZE, 60L, TimeUnit.MILLISECONDS, taskQueue);
    }

    public static DownloadManager getInstance() {
        if (instance == null) {
            synchronized (DownloadManager.class) {
                if (instance == null) {
                    instance = new DownloadManager();
                }
            }
        }
        return instance;
    }

    public void addWork(String url) {
        DownloadWork work = new DownloadWork(url, FileUtil.getPathFromUrl(url));
        works.put(url, work);
    }

    public static void addTask(DTask dTask) {
        threadPool.execute(dTask);
        try {
            threadPool.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getSize(String url, int size) {
        works.get(url).onSizeGet(size);
    }

    @Override
    public void getPercent(String url, int partId, int size) {
        works.get(url).onPercentGet(partId, size);
    }

    private Runnable poolRunnable = new Runnable() {
        @Override
        public void run() {
            while (alive) {
                while (running) {

                }
            }
        }
    };
}

