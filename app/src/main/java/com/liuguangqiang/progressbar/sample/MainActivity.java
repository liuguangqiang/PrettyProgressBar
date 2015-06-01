package com.liuguangqiang.progressbar.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuguangqiang.progressbar.SquareProgressBar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnCircleProgressBar;
    private Button btnSquareProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCircleProgressBar = (Button) this.findViewById(R.id.btn_circle_progressbar);
        btnCircleProgressBar.setOnClickListener(this);

        btnSquareProgressBar = (Button) this.findViewById(R.id.btn_square_progressbar);
        btnSquareProgressBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_circle_progressbar:
                startActivity(new Intent(getApplicationContext(), CircleProgressBarSample.class));
                break;
            case R.id.btn_square_progressbar:
                startActivity(new Intent(getApplicationContext(), SquareProgressBarSample.class));
                break;
        }
    }
}
