package com.example.mathcartest3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.TypedValue;

public class Obstacle extends GameObject {
    private MainActivity mainActivity;
    private Bitmap stoneBitmap, waterBitmap;
    private boolean isValid;

  //  SoundPool soundPool = null;
   // int stonemusic, watermusic;
   // Context context;

    public Obstacle(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;
        stoneBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_stone);
        waterBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_water);
        //soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        //stonemusic = soundPool.load(context.getResources(), R.raw.stonemusic,1);

        reset();

    }

    private void reset() {
        if (Math.random() < 0.5) {
            setBitmap(stoneBitmap);
        } else {
            setBitmap(waterBitmap);
        }
        int x = mainActivity.surfaceWidth + (int)(mainActivity.surfaceWidth * Math.random());
        int y = mainActivity.surfaceHeight - getHeight();
        setXY(x, y);

        isValid = true;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (isValid == true && getCollisionRect(0.7).intersect(mainActivity.car.getCollisionRect(0.7))) {
            int movement = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mainActivity.getResources().getDisplayMetrics());
            if (getBitmap() == stoneBitmap) {
                mainActivity.car.moveX(-movement);
                //soundPool.play(stonemusic,1f,1f,0,0,1f);
                isValid = false;
                mainActivity.stoneMusic();
            } else if (getBitmap() == waterBitmap) {
                mainActivity.waterMusic();
                mainActivity.car.moveX(movement);
                isValid = false;
            }
           mainActivity.hitObstacle();
        }

        moveX(-5);
        if (getX() < -getWidth()) {
            reset();
        }
    }
}
