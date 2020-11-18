package com.example.mathcartest3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class GameModeChoose extends GameObject {
    private Bitmap gamemodeBitmap;
    private Rect plusmode, minusmode;
    public int plusmodeX, plusmodeY, minusmodeX, minusmodeY, width = 300, height = 300;
    public MainActivity mainActivity;
    public boolean isPressedPlus,isPressedMinus;

    public GameModeChoose(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);

        this.mainActivity = mainActivity;
        isPressedPlus = false;
        isPressedMinus = false;

        gamemodeBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.gamemode);
        setBitmap(gamemodeBitmap);
        alignCenter();

        //plusmode = getRect(400, 300 , 500, 200);
        // minusmode = getRect(900, 300,500, 200);

    }

    public boolean isHitPlus(int x, int y) {
        if (plusmodeX < x && x < plusmodeX + gamemodeBitmap.getWidth()/2 && plusmodeY < y && y < plusmodeY + gamemodeBitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public boolean isHitMinus(int x, int y) {
        if (plusmodeX + gamemodeBitmap.getWidth()/2< x && x < plusmodeX + gamemodeBitmap.getWidth() && plusmodeY < y && y < plusmodeY + gamemodeBitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public void Pulspress(boolean press) {
        isPressedPlus = press;
    }
    public void Minuspress(boolean press) {
        isPressedMinus = press;
    }
    public boolean isPressedPlus() {
        return isPressedPlus;
    }
    public boolean isPressedMinus() {
        return isPressedMinus;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        setBitmap(gamemodeBitmap);
    }
}

