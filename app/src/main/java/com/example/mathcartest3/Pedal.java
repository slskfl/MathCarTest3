package com.example.mathcartest3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Pedal {

    private int pedalX, pedalY;
    private boolean pedalPressed;
    private Bitmap pedalBitmap, pedalPressedBitmap;
    public MainActivity mainActivity;

    public Pedal(MainActivity mainActivity, int pedalX, int pedalY, int bitmapResourceId, int pressedBitmapResourceId) {
        this.pedalX = pedalX;
        this.pedalY = pedalY;
        pedalPressed = false;
        pedalBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), bitmapResourceId);
        pedalPressedBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), pressedBitmapResourceId);
    }

    public boolean isHit(int x, int y) {
        if (pedalX < x && x < pedalX + pedalBitmap.getWidth() && pedalY < y && y < pedalY + pedalBitmap.getHeight()) {
            return true;
        }
        return false;
    }

    public void press(boolean press) {
        pedalPressed = press;
    }

    public boolean isPressed() {
        return pedalPressed;
    }



    public void draw(Canvas canvas) {
        if (pedalPressed == true) {
            canvas.drawBitmap(pedalPressedBitmap, pedalX, pedalY, null);
        } else {
            canvas.drawBitmap(pedalBitmap, pedalX, pedalY, null);
        }
    }
}
