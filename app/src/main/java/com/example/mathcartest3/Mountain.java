package com.example.mathcartest3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Mountain extends GameObject {
    private Bitmap mountainBitmap, seaBitmap, spaceBitmap;
    private int mountain1X, mountain2X, mountainY;
    private int sea1X, sea2X, seaY;
    private int space1X, space2X, spaceY;
    private long startTimestamp;
    private MainActivity mainActivity;

    public Mountain(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;

        mountainBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.mountain);
        mountain1X = 0;
        mountain2X = mountainBitmap.getWidth();
        mountainY = mainActivity.surfaceHeight - mountainBitmap.getHeight();

        seaBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.seabackground);
        sea1X = 0;
        sea2X = seaBitmap.getWidth();
        seaY = mainActivity.surfaceHeight - seaBitmap.getHeight();

        spaceBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.spacebackground);
        space1X = 0;
        space2X = spaceBitmap.getWidth();
        spaceY = mainActivity.surfaceHeight - spaceBitmap.getHeight();

        startTimestamp = System.currentTimeMillis();
    }



    public void draw(Canvas canvas) {

        // super.draw(canvas);
        if (((System.currentTimeMillis() - startTimestamp) / 10000) % 3 == 0) {
            canvas.drawBitmap(mountainBitmap, mountain1X, mountainY, null);
            canvas.drawBitmap(mountainBitmap, mountain2X, mountainY, null);
            //setBitmap(mountainBitmap);
            mountain1X -= 10;
            mountain2X -= 10;
            if (mountain1X + mountainBitmap.getWidth() < 0) {
                mountain1X = mountain2X + mountainBitmap.getWidth();
            }
            if (mountain2X + mountainBitmap.getWidth() < 0) {
                mountain2X = mountain1X + mountainBitmap.getWidth();
            }
        } else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 3 == 1){

            canvas.drawBitmap(seaBitmap, sea1X, seaY, null);
            canvas.drawBitmap(seaBitmap, sea2X, seaY, null);
           // setBitmap(seaBitmap);
            sea1X -= 10;
            sea2X -= 10;
            if (sea1X + seaBitmap.getWidth() < 0) {
                sea1X = sea2X + seaBitmap.getWidth();
            }
            if (sea2X + seaBitmap.getWidth() < 0) {
                sea2X = sea1X + seaBitmap.getWidth();
            }
        }
       /* else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 3 == 2){

            canvas.drawBitmap(spaceBitmap, space1X, spaceY, null);
            canvas.drawBitmap(spaceBitmap, space2X, spaceY, null);
            // setBitmap(seaBitmap);
            space1X -= 10;
            space2X -= 10;
            if (sea1X + spaceBitmap.getWidth() < 0) {
                sea1X = sea2X + spaceBitmap.getWidth();
            }
            if (sea2X + spaceBitmap.getWidth() < 0) {
                space2X = space1X + spaceBitmap.getWidth();
            }
        }*/

    }
}
