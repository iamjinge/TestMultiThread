package net.bingyan.jinge.mydownload;

import java.util.concurrent.ExecutorService;
import java.util.*;

/**
 * Created on 2015/9/29.
 */
public class DownloadManager {
	private static int MIN_SIZE = 1;
	private static int MAX_SIZE = 16;
    private ExecutorService threadPool;
	private int threadSize;
	private TaskBinTree taskBinTree = TaskBinTree.getInstance();
	private List<DownloadWork> works = new ArrayList<DownloadWork>();
    public DownloadManager() {

    }
	
	public void addWork(String url){
		DownloadWork work = new DownloadWork(url,"");
		works.add(work);
	}
}
