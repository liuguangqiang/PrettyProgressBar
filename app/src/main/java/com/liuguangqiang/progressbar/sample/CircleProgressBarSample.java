package com.liuguangqiang.progressbar.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.liuguangqiang.progressbar.CircleProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Eric on 14/12/27.
 */
public class CircleProgressBarSample extends ActionBarActivity {

    private CircleProgressBar progressBar1;
    private CircleProgressBar progressBar2;
    private CircleProgressBar progressBar3;
    private CircleProgressBar progressBar4;

    private boolean hasInited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progressbar);
        progressBar1 = (CircleProgressBar) this.findViewById(R.id.progressbar1);
        progressBar2 = (CircleProgressBar) this.findViewById(R.id.progressbar2);
        progressBar3 = (CircleProgressBar) this.findViewById(R.id.progressbar3);
        progressBar4 = (CircleProgressBar) this.findViewById(R.id.progressbar4);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !hasInited) {
            hasInited = true;
            test();
        }
    }

    private void test() {
        progressBar1.setProgressWithAnim(100, 1000);
        progressBar2.setProgressWithAnim(100, 1500);
        progressBar3.setProgressWithAnim(100, 2000);
        progressBar4.setProgressWithAnim(100, 2500);
    }

}
