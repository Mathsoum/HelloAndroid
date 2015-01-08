package com.m2dl.helloandroid;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HelloAndroid extends ActionBarActivity {

    private Handler mHandler;
    private Integer counter;
    private TextView textView;

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            --counter;
            textView.setText(counter.toString());
            if (counter < 0) {
                System.exit(RESULT_OK);
            }
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 5;

        textView = new TextView(this);
        textView.setText(counter.toString());

        mHandler = new Handler();
        mHandler.postDelayed(mUpdateTimeTask, 1000);

        setContentView(textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_android, menu);
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
