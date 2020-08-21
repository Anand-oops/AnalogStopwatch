package com.anand.android.stopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ClockView extends View {
    public boolean isRunning = false;

    private Paint hand = new Paint();
    private Paint dial = new Paint();

    private final float pi = (float) Math.PI;

    private float mainDialX;
    private float mainDialY;
    private float smallDialX;
    private float smallDialY;
    private float secondHandLength;
    private float minHandLength;

    public int i = -900;
    public int s = -15;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void drawSecLineAng(int i, Canvas canvas) {
        canvas.drawLine(mainDialX, mainDialY, (float) (mainDialX + secondHandLength *Math.cos(pi*i/30)),
                (float) (mainDialY + secondHandLength *Math.sin(pi*i/30)), hand);
    }

    private void drawMinLineAng(int i, Canvas canvas) {
        canvas.drawLine(smallDialX, smallDialY, (float) (smallDialX + minHandLength *Math.cos(pi*i/1800)),
                (float) (smallDialY + minHandLength *Math.sin(pi*i/1800)), hand);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float screenWidth = getResources().getDisplayMetrics().widthPixels;
        float screenHeight = getResources().getDisplayMetrics().heightPixels;

        mainDialX = screenWidth /2;
        mainDialY = screenHeight *5/12;
        float mainRadius = screenHeight / 4;
        smallDialX = screenWidth /2;
        smallDialY = screenHeight *15/48;
        float smallRadius = screenHeight / 15;
        secondHandLength = screenHeight /6;
        minHandLength = screenHeight /20;

        dial.setColor(Color.BLUE);
        dial.setStyle(Paint.Style.STROKE);
        dial.setStrokeWidth(10f);
        dial.setAntiAlias(true);
        dial.setStrokeCap(Paint.Cap.ROUND);

        hand.setColor(Color.RED);
        hand.setStyle(Paint.Style.STROKE);
        hand.setStrokeWidth(10f);
        hand.setAntiAlias(true);
        hand.setStrokeCap(Paint.Cap.ROUND);

        //draw outer dial
        canvas.drawCircle(screenWidth /2, screenHeight *5/12, screenHeight /4, dial);

        //draw inner dial
        canvas.drawCircle(screenWidth /2, screenHeight *15/48, screenHeight /15, dial);

        for(int j = 0 ; j < 12 ; j++) {
            //draw markings on outer dial   1 round is 1 minute
            canvas.drawLine((float) (mainDialX + mainRadius *9/10*Math.cos(pi*j/6)) ,
                    (float) (mainDialY + mainRadius *9/10*Math.sin(pi*j/6)),
                    (float) (mainDialX + mainRadius *Math.cos(pi*j/6)),
                    (float) (mainDialY + mainRadius *Math.sin(pi*j/6)), hand);
            //draw markings on inner dial//draw markings on inner dial  1 round is 1 hour
            canvas.drawLine((float) (smallDialX + smallRadius *9/10*Math.cos(pi*j/6)) ,
                    (float) (smallDialY + smallRadius *9/10*Math.sin(pi*j/6)),
                    (float) (smallDialX + smallRadius *Math.cos(pi*j/6)),
                    (float) (smallDialY + smallRadius *Math.sin(pi*j/6)), hand);
        }

        drawMinLineAng(i, canvas);
        drawSecLineAng(s, canvas);

        if(isRunning) {
            i++;
            s++;
            postInvalidateDelayed(1000);
        }
    }


}
