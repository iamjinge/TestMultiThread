package net.bingyan.jinge.testfile;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d("getCacheDir dirs", this.getCacheDir().getAbsolutePath());
//        Log.d("getObbDir dirs", this.getObbDir().getAbsolutePath());
//        Log.d("getFilesDir dirs", this.getFilesDir().getAbsolutePath());
//        Log.d("getObbDir dirs", this.getObbDir().getAbsolutePath());
////        Log.d("getCodeCacheDir dirs", this.getCodeCacheDir().getAbsolutePath());
////        Log.d("getNoBackupFilesDir", this.getNoBackupFilesDir().getAbsolutePath());
//        Log.d("getExternalCacheDir", this.getExternalCacheDir().getAbsolutePath());
//        Log.d("getExternalFilesDir dir", this.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath());

        File test = new File("/storage/emulated/0/TestFile/test.txt");
        try {
            test.createNewFile();
            RandomAccessFile random = new RandomAccessFile(test, "rwd");


            random.seek(10);
            random.write("asdfghaksj\n".getBytes());
            random.seek(0);
            random.write("asdfghaksj\n".getBytes());
            random.setLength(20 * 1024 * 1024);
            random.close();
//            test.renameTo(
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

