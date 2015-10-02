package net.bingyan.jinge.testmultithread;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.net.*;
import java.util.concurrent.PriorityBlockingQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DownloadUtil util = new DownloadUtil(MainActivity.this);
                test();
            }
        });

    }

    private void test() {
        Log.d("test", "start");
        PriorityBlockingQueue<DTask> pbqString = new PriorityBlockingQueue<>();
        DownloadTask dt = new DownloadTask("1", "1", 1);
        dt.setPriority(1);
        pbqString.add(dt);
        dt = new DownloadTask("2", "2", 2);
        dt.setPriority(2);
        pbqString.add(dt);
        dt = new DownloadTask("3", "3", 3);
        dt.setPriority(2);
        pbqString.add(dt);
        dt = new DownloadTask("4", "4", 4);
        dt.setPriority(0);
        pbqString.add(dt);
        Log.d("test", "add");
        while (true) {
            DTask d = pbqString.poll();
            if (d == null) {
                break;
            } else {
                Log.d("test", d.getUrl() + "  " + d.getPriority());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
