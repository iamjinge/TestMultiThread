package net.bingyan.jinge.mydownload;

import java.util.concurrent.ExecutorService;
import java.util.*;

/**
 * Created on 2015/9/29.
 */
public class DownloadManager implements DCallback{
	private static int MIN_SIZE = 1;
	private static int MAX_SIZE = 16;
    private ExecutorService threadPool;
	private int threadSize;
	private TaskBinTree taskBinTree = TaskBinTree.getInstance();
	private Map<String,DownloadWork> works = new HashMap<String,DownloadWork>();
	private DCallback callback;
    public DownloadManager() {

    }
	
	public void addWork(String url){
		DownloadWork work = new DownloadWork(url,FileUtil.getPathFromUrl(url));
		works.put(url,work);
	}

	@Override
	public void getSize(String url, int size)
	{
		works.get(url).onSizeGet(size);
	}

	@Override
	public void getPercent(String url, int size)
	{
	// TODO: Implement this method
	}

}
