package com.example.mathcartest3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Oil extends GameObject {
    public MainActivity mainActivity;
    private Bitmap oilBitmap;
    private boolean isVisible;
    private int x, y;
    Paint paint;


    public Oil(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;
        oilBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.oil);

        reset();
    }

    public void setXY(int x, int y) {

        this.x = x;
        this.y = y;
    }

    private void reset() {
        if (Math.random() < 0.4) {
            setBitmap(oilBitmap);
        }
        x = (int) (Math.random() * (mainActivity.surfaceWidth - oilBitmap.getWidth()));
        y = 0 - oilBitmap.getHeight();
        setXY(x, y);

        isVisible = true;
    }


    public void draw(Canvas canvas) {
    super.draw(canvas);

        if (isVisible && getCollisionRect().intersect(mainActivity.car.getCollisionRect(0.7))) {
            isVisible = false;
            mainActivity.plusScoreBoard.addOil(10);
        }
        if(isVisible){
            canvas.drawBitmap(oilBitmap, x, y, paint);
        }
        if (mainActivity.plusScoreBoard.getOil() == 0){
            mainActivity.scoreTOCompleted();
        }
        y += 4;
        if (y > mainActivity.surfaceHeight) {
            //화면 밑을 벗어날 경우
            x = (int) (Math.random() * (mainActivity.surfaceWidth - oilBitmap.getWidth())); //X좌표는 랜덤
            y = 0 - oilBitmap.getHeight();//화면 위에서부터 시작
            isVisible = true;
        }
    }
    public Rect getRect() {
        return new Rect(x, y, x + oilBitmap.getWidth() - 1, y + oilBitmap.getHeight() - 1);
    }

    private static final double collisionRate = 0.6;
    public Rect getCollisionRect() {
        return new Rect((int)(x + (oilBitmap.getWidth() * (1 - collisionRate)) / 2), (int)(y + (oilBitmap.getHeight() * (1 - collisionRate)) / 2), (int)(x + (oilBitmap.getWidth() * collisionRate) - 1), (int)(y + (oilBitmap.getHeight() * collisionRate) - 1));
    }
}


