package com.anand.android.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ClockView clockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockView = findViewById(R.id.clock_view);
        final Button start = findViewById(R.id.btn_start);
        final Button pause = findViewById(R.id.btn_pause);
        Button reset = findViewById(R.id.btn_reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clockView.isRunning) {
                    clockView.isRunning = true;
                    start.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                    clockView.postInvalidate();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clockView.isRunning) {
                    clockView.isRunning = false;
                    pause.setText("Resume");
                    clockView.i--;
                    clockView.s--;
                }else{
                    pause.setText("Pause");
                    clockView.isRunning=true;
                }
                clockView.postInvalidate();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.isRunning = false;
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
                clockView.i = -900;
                pause.setText("Pause");
                clockView.s = -15;
                clockView.invalidate();
            }
        });
    }
}