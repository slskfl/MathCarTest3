package com.example.mathcartest3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnTouchListener {

    private GamePlayThread gamePlayThread;
    private SurfaceHolder surfaceHolder;
    private boolean isRunning;
    private int resetCount;
    private int scoreCount;

    public GameMode gameMode;
    public int surfaceWidth, surfaceHeight;
    public Car car;
    public PlusScoreBoard plusScoreBoard;
    public Balloons balloons;
    public Obstacle obstacle;
    public GameStart gameStart;
    public GameCompleted gameCompleted;
    public Pedal accellerator, brake;
    public Jump jump;
    public GameModeChoose gameModeChoose;
    public Oil oil;
    public Reset reset;
    public Music music;


    private static MediaPlayer backgroundmusic;
    SoundPool soundPool = null;
    static int accellratormusic, brakemusic, jumpmusic, stonemusic, watermusic, background_ms;
    int waitCouter = 1000;
    int waitLimit =0;
    int throttle = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView mainSurfaceViwe = findViewById(R.id.main_surface);
        mainSurfaceViwe.setOnTouchListener(this);
        surfaceHolder = mainSurfaceViwe.getHolder();
        surfaceHolder.addCallback(this);

        backgroundmusic = MediaPlayer.create(this, R.raw.backgound);
        backgroundmusic.setLooping(false);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        accellratormusic = soundPool.load(this, R.raw.accellatormusic,1);
        brakemusic = soundPool.load(this, R.raw.brakemusic,2);
        jumpmusic = soundPool.load(this, R.raw.jumpmusic,3);
       // background_ms = soundPool.load(this, R.raw.backgound,1);
        //stonemusic = soundPool.load(this, R.raw.stonemusic,1);
        // watermusic = soundPool.load(this, R.raw.watermusic,1);

    }


    public void stoneMusic(){
        soundPool.play(stonemusic,1f,1f,2,0,2f);
    }

    public void waterMusic(){
        soundPool.play(watermusic,1f,1f,3,0,2f);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        surfaceWidth = width;
        surfaceHeight = height;
        gameMode = GameMode.Ready;
        gamePlayThread = new GamePlayThread();
        isRunning = true;
        gamePlayThread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gamePlayThread.press(true, (int)motionEvent.getX(), (int)motionEvent.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                gamePlayThread.press(false, (int)motionEvent.getX(), (int)motionEvent.getY());
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
        backgroundmusic.release();
        soundPool.release();
    }

    public void hitObstacle() {
        plusScoreBoard.loseOil(10);
    }
   /* public void resetCount(){
        resetCount--;
        if (resetCount == 1){
            plusScoreBoard.resetAnswer();
            balloons.reset();
        }
    }*/

    public void scoreTOCompleted(){

        //if (scoreBoard.getScore() == -70){
        gameMode = GameMode.PlusCompleted;
        //}
    }

    /*public void smokeCar(){
        obstacle.setBitmap(R.drawable.smokecar);
    }*/

    public boolean plusModeisHit(){
        return true;
    }

    public boolean minusModeisHit(){
        return true;
    }

    private class GamePlayThread extends Thread {

        public void press(boolean press, int x, int y) {
            switch (gameMode) {
                case Ready:
                    if (press == true && gameStart.isHit(x, y) == true) {
                        gameMode = GameMode.Gamemode;
                        resetCount = 1;
                       /*plusScoreBoard.resetAnswer();*/
                    }  else if (music.isHit(x, y) == true){
                         if(music.musicOffpressed == true){
                             music.OffToOnpress(true);
                            backgroundmusic.pause();
                             }
                        else if(music.musicOnpressed == true)  {
                            music.OnToOffpress(true);
                            backgroundmusic.start();
                        }
                     }
                    break;
                case Gamemode:
                    if(press == true ) {
                        if (gameModeChoose.isHitPlus(x, y) == true) {
                            gameModeChoose.Pulspress(true);
                            plusScoreBoard.Plusmode(true);
                            plusScoreBoard.resetAnswer();
                            plusScoreBoard.plusreset();
                        }
                        else if(gameModeChoose.isHitMinus(x,y) == true){
                           gameModeChoose.Minuspress(true);
                            plusScoreBoard.Minusmode(true);
                            plusScoreBoard.resetAnswer();
                            plusScoreBoard.minusreset();
                        }
                        gameMode = GameMode.PlusPlay;
                        scoreCount = 0;
                        balloons.reset();
                    } else if (music.isHit(x, y) == true){
                        if(music.musicOffpressed == true){
                            music.OffToOnpress(true);
                            backgroundmusic.pause();
                        }
                        else if(music.musicOnpressed == true)  {
                            music.OnToOffpress(true);
                            backgroundmusic.start();
                        }
                    }
                    break;
                case PlusPlay:
                    if (press == true ) {
                        if (accellerator.isHit(x, y) == true) {
                            accellerator.press(true);
                            soundPool.play(accellratormusic,1f,1f,0,0,1f);/*== 0 && waitCouter < waitLimit);*/
                        } else if (brake.isHit(x, y) == true) {
                            brake.press(true);
                            soundPool.play(brakemusic,1f,1f,0,0,2f);/* == 0 && waitCouter < waitLimit);*/
                        } else if (jump.isHit(x, y) == true) {
                            car.jump();
                            soundPool.play(jumpmusic,1f,1f,0,0,0.5f);/*== 0 && waitCouter < waitLimit);*/
                        } else if (reset.isHit(x,y) == true){
                            reset.press(true);
                            if (resetCount == 1){
                                plusScoreBoard.resetAnswer();
                                balloons.reset();
                                resetCount--;
                            }
                        } else if (music.isHit(x, y) == true){
                            //Log.e("slskfl", "off// on"+ music.musicOffpressed +"//"+music.musicOnpressed);
                            if(music.musicOffpressed == true){
                                music.OffToOnpress(true);
                                backgroundmusic.pause();
                                soundPool.pause(accellratormusic);
                                soundPool.pause(brakemusic);
                                soundPool.pause(jumpmusic);
                            }
                            else if(music.musicOnpressed == true)  {
                                 music.OnToOffpress(true);
                                backgroundmusic.start();
                                soundPool.resume(accellratormusic);
                                soundPool.resume(brakemusic);
                                soundPool.resume(jumpmusic);
                            }
                        }
                    }

                    else {
                        accellerator.press(false);
                        brake.press(false);
                    }
                    break;

                case PlusCompleted:
                    if (press == true) {
                        if (gameCompleted.isHitBack(x, y) == true) {
                            gameMode = GameMode.PlusPlay;
                            // hitCount = 0;
                            scoreCount = 0;
                            resetCount = 1;
                            plusScoreBoard.plusreset();
                            plusScoreBoard.minusreset();
                            balloons.reset();
                        } else if (gameCompleted.isHitHome(x, y) == true) {
                            gameMode = GameMode.Ready;
                        } else if (music.isHit(x, y) == true){
                            Log.e("slskfl", "off// on"+ music.musicOffpressed +"//"+music.musicOnpressed);
                            if(music.musicOffpressed == true){
                                Log.e("slskfl", "off to on"); // on -> off
                                music.OffToOnpress(true);
                                backgroundmusic.pause();
                            }
                            else if(music.musicOnpressed == true)  {
                                Log.e("slskfl", "on to off"); // off -> on
                                music.OnToOffpress(true);
                                backgroundmusic.start();
                                  }
                        }
                    }
                    break;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void run() {
            super.run();
            Paint skyPaint = new Paint();
            skyPaint.setColor(getResources().getColor(R.color.colorSky, null));

            Mountain mountain = new Mountain(MainActivity.this);
            gameStart = new GameStart(MainActivity.this);
            gameCompleted = new GameCompleted(MainActivity.this);
            accellerator = new Pedal( MainActivity.this,20, 600, R.drawable.accellerator, R.drawable.accellerator_pressed);
            brake = new Pedal( MainActivity.this, 150, 700, R.drawable.brake, R.drawable.brake_pressed);
            jump = new Jump(MainActivity.this, 1600, 700);
            car = new Car(MainActivity.this);
            plusScoreBoard = new PlusScoreBoard(MainActivity.this, 0, 0);
            balloons = new Balloons(MainActivity.this, 6);
            obstacle = new Obstacle(MainActivity.this);
            gameModeChoose = new GameModeChoose(MainActivity.this);
            oil = new Oil(MainActivity.this);
            reset = new Reset(MainActivity.this, 1600, 500);
            music = new Music(MainActivity.this, 1600, 0);

            plusScoreBoard.resetAnswer();
            backgroundmusic.start();
            while (isRunning) {
                Canvas canvas = surfaceHolder.lockCanvas();
               // backgroundmusic.start();
                if (canvas != null) {

                    canvas.drawRect(0, 0, surfaceWidth - 1, surfaceHeight - 1, skyPaint);
                    mountain.draw(canvas);
                    music.draw(canvas);
                    switch (gameMode) {
                        case Ready:
                            gameStart.draw(canvas);
                            break;
                        case Gamemode:
                            gameModeChoose.draw(canvas);
                            break;
                        case PlusPlay:
                            car.draw(canvas);
                            balloons.draw(canvas);
                            accellerator.draw(canvas);
                            brake.draw(canvas);
                            jump.draw(canvas);
                            obstacle.draw(canvas);
                            //if(plusModeisHit() == true) {
                            plusScoreBoard.draw(canvas);
                            oil.draw(canvas);
                            reset.draw(canvas);
                                /* backgroundmusic.start();
                           } else if (minusModeisHit() == true){
                                minusScoreBoard.draw(canvas);
                            }*/
                            break;
                        case PlusCompleted:
                            //backgroundmusic.stop();
                            // soundPool.play(completedmusic,1f,1f,0,0,1f);
                            gameCompleted.draw(canvas);

                            break;
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private enum GameMode {
        Ready, PlusPlay, MinusPlay, PlusCompleted, MinusCompleted,Gamemode;
    }
}