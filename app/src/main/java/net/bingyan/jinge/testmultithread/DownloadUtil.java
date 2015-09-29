package net.bingyan.jinge.testmultithread;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * Created on 2015/9/27.
 */
public class DownloadUtil {
    private String mUrl = "http://7xn38b.com1.z0.glb.clouddn.com/picture/FILE0194.jpg";
    private Context context;

    public DownloadUtil(Context context) {
        this.context = context;
        new Thread(timeRunnable).start();
    }

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File("/storage/emulated/0/TestFile/FILE0194.jpg");

            try {
                if (file.createNewFile()) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            RandomAccessFile savedFile = null;
            try {
                savedFile = new RandomAccessFile(file, "rwd");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                int k = 1024;

                HttpURLConnection connection = (HttpURLConnection) new URL(mUrl).openConnection();
//                connection.setRequestProperty("Range", "bytes=" + 0 + "-" + (8 * k * k - 1));
                Log.d("time", "length conn " + connection.getContentLength());

                InputStream in = connection.getInputStream();
                Log.d("time", "get input");
                byte[] buffer = new byte[1024 * 2];
                int len = 0;
                int i = 0;
                int total = 0;
                savedFile.seek(0);

//                BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
//                BufferedReader bufferedReader = new

                while ((len = in.read(buffer)) != -1) {
//                    Log.d("test", len + " last");
                    savedFile.write(buffer, 0, len);
                    i++;
                    total += len;
                    if (len != 2 * k) {
                        Log.d("time", len + "");
                    }
                }
                Log.d("time", "read write");
                savedFile.close();
                connection.disconnect();
                Log.d("time", i + "  " + total);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

    private Runnable downloadRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File("/storage/emulated/0/食刻--地标_酒店.png");

            try {
                if (file.createNewFile()) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            RandomAccessFile savedFile = null;
            try {
                savedFile = new RandomAccessFile(file, "rwd");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(mUrl).openConnection();
                Log.d("length", conn.getContentLength() + "");

                int k = 4096;
                int length = conn.getContentLength();
                int time = length / k;
                for (int i = 0; i < time; i++) {
                    int start = i * k;
                    int end = start + k - 1;

                    HttpURLConnection connection = (HttpURLConnection) new URL(mUrl).openConnection();
                    connection.setRequestProperty("Range", "bytes=" + start + "-" + end);

                    InputStream in = connection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    savedFile.seek(start);
                    while ((len = in.read(buffer)) != -1) {
                        Log.d("test", len + " " + i);
                        savedFile.write(buffer);
                    }
                }

                HttpURLConnection connection = (HttpURLConnection) new URL(mUrl).openConnection();
                connection.setRequestProperty("Range", "bytes=" + (time * k) + "-" + length);

                InputStream in = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                savedFile.seek(time * k);
                while ((len = in.read(buffer)) != -1) {
                    Log.d("test", len + " last");
                    savedFile.write(buffer);
                }
                savedFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
