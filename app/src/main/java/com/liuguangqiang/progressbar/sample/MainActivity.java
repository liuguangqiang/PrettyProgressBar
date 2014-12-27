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

    private CircleProgressBar arcProgressBar1;
    private CircleProgressBar arcProgressBar2;
    private CircleProgressBar arcProgressBar3;
    private CircleProgressBar arcProgressBar4;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcProgressBar1 = (CircleProgressBar) this.findViewById(R.id.arc_progressbar1);
        arcProgressBar2 = (CircleProgressBar) this.findViewById(R.id.arc_progressbar2);
        arcProgressBar3 = (CircleProgressBar) this.findViewById(R.id.arc_progressbar3);
        arcProgressBar4 = (CircleProgressBar) this.findViewById(R.id.arc_progressbar4);

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
                        if (progress <= arcProgressBar1.getMax()) {
                            arcProgressBar1.setProgress(progress);
                            arcProgressBar2.setProgress(progress);
                            arcProgressBar3.setProgress(progress);
                            arcProgressBar4.setProgress(progress);
                        }
                    }
                });
            }
        }, 100, 100);
    }
}
