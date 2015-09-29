package net.bingyan.jinge.testmultithread;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
                DownloadUtil util = new DownloadUtil(MainActivity.this);
            }
        });


        char[] sample = "()[]".toCharArray();

        int start = 0;
        int end = sample.length - 1;

        boolean result = false;

        while (start < end) {
            int m = 0, n = 0, p = 0, q = 0;
            for (int i = start; i <= end; i++) {
                switch (sample[i]) {
                    case '(':
                        m++;
                        break;
                    case ')':
                        n++;
                        break;
                    case '[':
                        p++;
                        break;
                    case ']':
                        q++;
                        break;
                }
            }
            if (m != n && p != q) {
                break;
            }
            if ((sample[start] == '(' && sample[end] == ')') ||
                    (sample[start] == '[' && sample[end] == ']')) {
                start ++;
                end --;
            } else {
                start ++;
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
