package com.example.mathcartest3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Reset {
    private int resetX, resetY;
    private int bitmapResourceId0, bitmapResourceId1, bitmapResourceId2,bitmapResourceId3;
    private boolean reset0Pressed, reset1Pressed, reset2Pressed,reset3Pressed;
    private Bitmap reset0Bitmap, reset1Bitmap, reset2Bitmap, reset3Bitmap;

    public Reset(Context context, int resetX, int resetY){
        this.resetX = resetX;
        this.resetY = resetY;
        reset0Pressed = false;
        reset0Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset00);
        reset1Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset01);
        reset2Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset02);
        reset3Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset03);

        reset();
    }
    public void reset(){
        reset0Pressed = false;
    }
    public boolean isHit(int x, int y) {
        if (this.resetX < x && x < this.resetX + reset0Bitmap.getWidth() && this.resetY < y && y < this.resetY + reset0Bitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public void press(boolean press) {
        reset0Pressed = press;
    }

    public boolean isPressed() {
        return reset0Pressed;
    }

    public void draw(Canvas canvas){
        if (reset0Pressed == true) {
            canvas.drawBitmap(reset0Bitmap, resetX, resetY, null);
        }else {
            canvas.drawBitmap(reset1Bitmap, resetX, resetY, null);
        }

    }
}
