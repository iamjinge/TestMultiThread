package net.bingyan.jinge.mydownload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2015/9/28.
 */
public class DownloadTask extends DTask {
	private int partId;
    private int start;
    private int end;
    private String path;

    public DownloadTask(String url, String path, int partId) {
        super(url);
        this.path = path;
		this.partId = partId;
    }

    public DownloadTask(String url, String path, int partId, int start, int end) {
        this(url,path,partId);
        this.end = end;
        this.path = path;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = PRI_DOWNLOAD + priority;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            RandomAccessFile randomFile = new RandomAccessFile(file, "rwd");
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Range", "bytes=" + start + "-" + end);

            InputStream in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            randomFile.seek(start);
            while ((len = in.read(buffer)) != -1) {
//                Log.d("test", len + " " + i);
                randomFile.write(buffer, 0, len);
                callback.getPercent(url, partId, len);
            }
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

}
