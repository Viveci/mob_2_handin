package com.example.user.achtung;

    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.Canvas;
    import android.graphics.Paint;
    import android.os.Handler;
    import android.util.AttributeSet;
    import android.view.MotionEvent;
    import android.view.View;

    import java.util.Random;

public class CanvasView extends View{

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 2;
    private static final int SPEED = 50;
    private Paint paint = new Paint();

    private int[][] obstacles;
    private Random rand;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        paint.setStyle(Paint.Style.FILL);
        obstacles = new int[3][2];
        rand = new Random();
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mX = mCanvas.getWidth()/2;
        mY = mCanvas.getHeight()-300;
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,30,paint);
        for (int i = 0; i < obstacles.length; i++) {
            canvas.drawCircle(obstacles[i][0], obstacles[i][1], 30, paint);
        }
        updateView.run();
    }

    Handler viewHandler = new Handler();
    Runnable updateView = new Runnable(){
        @Override
        public void run(){
            for (int i = 0; i < obstacles.length; i++){
                obstacles[i][0] = mCanvas.getWidth()/2;
                obstacles[i][1]++;
            }
            invalidate();
            viewHandler.postDelayed(updateView, SPEED);
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
        float y = event.getY();

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
        return true;
    }
}