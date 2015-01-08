package com.m2dl.helloandroid;

import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class HelloAndroid extends ActionBarActivity implements View.OnTouchListener {

    private TextView textView;
    private Integer counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        counter = 0;

        textView = new TextView(this);
        textView.setText("Build model : " + Build.MODEL);
        textView.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float posx=event.getX();
        float posy=event.getY();

        ++counter;
        if (counter > 10) {
            System.exit(RESULT_OK);
        }

        textView.setText("Entry #" + counter + "\nPosition X : " + posx + "\nPosition Y : " + posy);
        return true;
    }
}
