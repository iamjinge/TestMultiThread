package net.bingyan.jinge.mydownload;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created on 2015/9/29.
 */
public class GetSizeTask extends DTask {
    private int size;

    public GetSizeTask(String url) {
        super(url);
    }

    @Override
    public void setPriority(int priority) {
        this.priority = PRI_GET_SIZE + priority;
    }

    @Override
    public void run() {
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            size = connection.getContentLength();
            callback.getSize(url, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
