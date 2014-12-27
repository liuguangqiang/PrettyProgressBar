package com.liuguangqiang.progressbar.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuguangqiang.progressbar.CircleProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    private int progress = 0;
    private Timer mTimer;

    private CircleProgressBar progressBar1;
    private CircleProgressBar progressBar2;
    private CircleProgressBar progressBar3;
    private CircleProgressBar progressBar4;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = (CircleProgressBar) this.findViewById(R.id.progressbar1);
        progressBar2 = (CircleProgressBar) this.findViewById(R.id.progressbar2);
        progressBar3 = (CircleProgressBar) this.findViewById(R.id.progressbar3);
        progressBar4 = (CircleProgressBar) this.findViewById(R.id.progressbar4);

        btnTest = (Button) this.findViewById(R.id.btn_start);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        progress = 0;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress++;
                        if (progress <= progressBar1.getMax()) {
                            progressBar1.setProgress(progress);
                            progressBar2.setProgress(progress);
                            progressBar3.setProgress(progress);
                            progressBar4.setProgress(progress);
                        }
                    }
                });
            }
        }, 100, 100);
    }
}
