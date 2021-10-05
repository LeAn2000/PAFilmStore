package com.pastore.pafilmstore;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.style.ChasingDots;
import com.pastore.pafilmstore.adapter.Check;
import com.pastore.pafilmstore.adapter.NetWordChangeListener;

public class WelcomeActivity extends AppCompatActivity {

    private TextView mTextView;
    NetWordChangeListener netWordChangeListener = new NetWordChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        ChasingDots wSprite = new ChasingDots();
        progressBar.setIndeterminateDrawable(wSprite);

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWordChangeListener, filter);
        super.onStart();
        if(Check.isConnectedToInternet(WelcomeActivity.this))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 5000);
        }

    }



    @Override
    protected void onStop() {
        unregisterReceiver(netWordChangeListener);
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(Check.isConnectedToInternet(WelcomeActivity.this))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }
}