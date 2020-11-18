package com.example.mathcartest3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Car extends GameObject {
    private MainActivity mainActivity;
    private boolean isJumping;
    private int jumpIndex = 0;
    private int[] jumpSteps = {-6, -6, -6, -6, -4, -4, -4, -4, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 6, 6, 6, 6};
    private int x, y;
    private  Bitmap carbimap0,carbimap1,carbimap2,carbimap3,carbimap4,carbimap5,carbimap6,carbimap7,carbimap8,carbimap9,carbimap10;
    private Bitmap carsNum[] = new Bitmap[7];
    private int carsIndex = 0;
    private long startTimestamp;

    private List<Bitmap> carBitmapsList = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();

    public Car(MainActivity mainActivity ) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;

        carbimap0 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car0);
        carbimap1 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car1);
        carbimap2 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car2);
        carbimap3 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car3);
        carbimap4 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car4);
        carbimap5 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car5);
        carbimap6 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car6);
        carbimap7 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car5);
        carbimap8 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car4);
        carbimap9 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car3);
        carbimap9 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car2);
        carbimap10 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car1);
        setBitmap(R.drawable.ic_car0);
        setXY(280, mainActivity.surfaceHeight - getHeight());
        isJumping = false;
        startTimestamp = System.currentTimeMillis();
      /*  for (int i = 0 ; i < 7 ; i++){
            carsNum[i] = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car0 + i);
        }*/
       /* carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car0));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car1));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car2));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car3));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car4));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car5));
        carBitmapsList.add(BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.car6));

        for (int i = 0 ; i < carBitmapsList.size(); i ++ ){
            cars.add((Car) carBitmapsList);
        }*/


    }

    public void setXY() {
        //setXY(280, mainActivity.surfaceHeight - getHeight());
    }
    public void draw(Canvas canvas) {
        //carsIndex = 0;
        super.draw(canvas);
        if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 0){
            setBitmap(carbimap0);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 1){
            setBitmap(carbimap1);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 2){
            setBitmap(carbimap2);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 3){
            setBitmap(carbimap3);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 4){
            setBitmap(carbimap4);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 5){
            setBitmap(carbimap5);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 6) {
            setBitmap(carbimap6);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 7) {
            setBitmap(carbimap7);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 8){
            setBitmap(carbimap8);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 9){
            setBitmap(carbimap9);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 10){
            setBitmap(carbimap10);
        }


       // canvas.drawBitmap(bitmap,x,y,null);
        /*while (true) {
            carsIndex ++;
            carsImage = carsNum[carsIndex];
*/
            if (isJumping) {
                moveYdp(jumpSteps[jumpIndex]);
                jumpIndex++;
                if (jumpIndex >= jumpSteps.length) {
                    isJumping = false;
                }
            }
            if (mainActivity.accellerator.isPressed() == true) {
                moveXdp(5);
            }
            if (mainActivity.brake.isPressed() == true) {
                moveXdp(-5);
            }
        }

  //  }

    public void jump() {
        if (isJumping == false) {
            jumpIndex = 0;
            isJumping = true;
        }
    }

}