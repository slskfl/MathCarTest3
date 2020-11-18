package com.example.mathcartest3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.List;

public class PlusScoreBoard extends GameObject{
    private int plusscore,minusscore;
    private MainActivity mainActivity;
    private int number1, number2;
    public int oilnumber;
    private boolean plusmode, minusmode;
    private int scoreboardX, scoreboardY;
    private Bitmap scoreBoardBitmap;

    public PlusScoreBoard(MainActivity mainActivity, int scoreboardX, int scoreboardY) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;
        /*setBitmap(R.drawable.score_board);
        setXYdp(0, 0);*/
       this.scoreboardX = scoreboardX;
       this.scoreboardY = scoreboardY;
        plusmode = false;
        minusmode = false;
        scoreBoardBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.score_board);

        plusreset();
        minusreset();
        oilreset();

    }

    public void plusreset() {
        plusscore = 0;
    }
    public void minusreset() {
        minusscore = 0;
    }
    public void oilreset() { oilnumber = 50; }

    public void Plusmode(boolean press){
        plusmode = press;
    }
    public void Minusmode(boolean press){
        minusmode = press;
    }


    @Override
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(scoreBoardBitmap,scoreboardX, scoreboardY, null);
        if( plusmode == true) {
            canvas.drawBitmap(scoreBoardBitmap,scoreboardX, scoreboardY, null);
            drawText(canvas, "점수: " + plusscore, 16, 30, 25, Color.BLACK);
            drawText(canvas, "연료: " + oilnumber, 16, 70, 25, Color.BLACK);
            drawText(canvas, "문제: " + number1 + " + " + number2, 16, 110, 25, Color.BLACK);
        }else if (minusmode == true){
            canvas.drawBitmap(scoreBoardBitmap,scoreboardX, scoreboardY, null);
            drawText(canvas, "점수: " + minusscore, 16, 30, 25, Color.BLACK);
            drawText(canvas, "연료: " + oilnumber, 16, 70, 25, Color.BLACK);
            drawText(canvas, "문제: " + number1 + " - " + number2, 16, 110, 25, Color.BLACK);
        }
    }

    public void resetAnswer() {
        List<Balloon> balloonList = mainActivity.balloons.getBalloons();
        Balloon balloon = balloonList.get((int) (Math.random() * balloonList.size()));
        int balloonNumber = balloon.getNumber();

        if (plusmode == true) {
            number1 = (int)(balloonNumber / 2 * Math.random());
            number2 = balloonNumber - number1;
        } else if(minusmode == true) {
            number1 = (int) ( Math.random() *( balloonNumber / 2)) + ( balloonNumber  + 1 );
            number2 =  number1 - balloonNumber ;

        }
    }
    public void addPlusScore(int score) {

        this.plusscore += score;
    }
    public void addMinusScore(int score) {

        this.minusscore += score;
    }
    public int getPlusScore() {

        return plusscore;
    }
    public int getMinusScore() {

        return minusscore;
    }

    public int getPlusAnswer() {

        return number1 + number2;
    }
    public int getMinusAnswer() {

        return number1 - number2;
    }

    public void addOil(int oilnum){

        this.oilnumber += oilnum;
    }
    public void loseOil(int oilnum){

        this.oilnumber -= oilnum;
    }
    public int getOil(){
        return oilnumber;
    }




  /*public void getScoreIsLow(){

        if (getScore() == -70){
            mainActivity.scoreTOCompleted();
        }
    }*/
}
