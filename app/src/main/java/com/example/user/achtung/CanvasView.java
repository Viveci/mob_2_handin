package com.example.user.achtung;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/*
import java.lang.Object;

import java.awt.*;
import java.util.*;
import java.awt.Rectangle; */


public class CanvasView extends View {

    private Player player;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 2;
    private static final int SPEED = 50;
    private Paint paint = new Paint();

    private int playerY, width, height, playerX, score;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        paint.setStyle(Paint.Style.FILL);
        playerY = mCanvas.getHeight() - 20;
        width = mCanvas.getWidth();
        height = 5;
        playerX = (mCanvas.getWidth()) / 2;
        score = 0;

//        obstacles = new int[3][2];
//        rand = new Random();
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mX = mCanvas.getWidth() / 2;
        mY = mCanvas.getHeight() - 300;
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateView.run();
    }

    Handler viewHandler = new Handler();
    Runnable updateView = new Runnable() {
        @Override
        public void run() {
            int left = (int) ((Math.random() * width));
            int right = left + 50;
            int height = 5;
            int bottom = 0;
            while (true) {
                mCanvas.drawRect(left, height, right, bottom, paint);
                if (playerY >= height && playerY <=bottom) {
                    if (!(playerX <= left && playerX>=right)) {
                        player.addScore(score);
                        return;
                    }
                }
                if(height > playerY){
                    mCanvas.drawColor(Color.BLACK);
                    return;
                }
                mCanvas.drawRect(left, height +1, right, bottom +1, paint);
                notifyAll();
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                invalidate();
                viewHandler.postDelayed(updateView, SPEED);
            }
           // int timer = Math.random(1, 3) * 1000;

           // Thread.sleep(timer);
        }
    };

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mX = x;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        mX = x;
    }

    public void clearCanvas() {
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        int y = playerY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        playerX = (int) x;
        return true;
    }
}