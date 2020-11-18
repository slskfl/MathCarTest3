package com.example.mathcartest3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Music {
    public int musicX, musicY;
    public boolean musicOnpressed,musicOffpressed;
    private Bitmap musicOnBitmap, musicOffBitmap;

    public Music(Context context, int musicX, int musicY){
        this.musicX = musicX;
        this.musicY = musicY;
        musicOnpressed = false;
        musicOffpressed = true;
        musicOnBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.soundon);
        musicOffBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.soundoff);
    }
    public boolean isHit(int x, int y) {
        if (musicX < x && x < musicX + musicOnBitmap.getWidth() && musicY < y && y < musicY + musicOnBitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public void OffToOnpress(boolean press) {
        musicOffpressed = ! press;
        musicOnpressed = press;

    }
    public void OnToOffpress(boolean press) {
        musicOnpressed = !press;
        musicOffpressed = press;
    }


    public void draw(Canvas canvas){
        if (musicOnpressed == true) {
            canvas.drawBitmap(musicOffBitmap, musicX, musicY, null);
        }else if (musicOffpressed == true){
            canvas.drawBitmap(musicOnBitmap, musicX, musicY, null);
        }else {
            canvas.drawBitmap(musicOnBitmap, musicX, musicY, null);
        }
    }

}
