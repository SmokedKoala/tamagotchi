package ru.practice.tamagotchi.tamagotchiactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import ru.practice.tamagotchi.minigame.GameActivity;

import static android.content.Context.MODE_PRIVATE;


public class TamagotchiView extends SurfaceView implements Runnable {

    private Thread thread;
    private int screenX, screenY;
    private Paint paint;
    private TamagotchiActivity activity;
    private TamagotchiBackground tamagotchiBackground;
    private TamagotchiUnit tamagotchiUnit;
    private boolean isPlaying;
    private boolean isMute;
    private SharedPreferences preferences;
    private Mute mute;
    private Play play;
    Intent intent;

    public TamagotchiView(TamagotchiActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;
        this.screenX =screenX;
        this.screenY= screenY;
        paint = new Paint();

        preferences = activity.getSharedPreferences("mini-game",MODE_PRIVATE);
        isMute = preferences.getBoolean("isMute",false);

        mute = new Mute(screenY,screenX,getResources(),isMute);
        play = new Play(screenY,screenX,getResources());
        tamagotchiUnit = new TamagotchiUnit(this,screenY,screenX,getResources());
        tamagotchiBackground = new TamagotchiBackground(screenX,screenY,getResources());
    }

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }


    private void update() {

    }


    private void draw() {
        //проверка область для прорисовки создалась
        if (getHolder().getSurface().isValid()) {
            //создание полотна для области
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(tamagotchiBackground.background, tamagotchiBackground.x, tamagotchiBackground.y, paint);
            canvas.drawBitmap(mute.getButton(),mute.x,mute.y,paint);
            canvas.drawBitmap(play.getButton(),play.x,play.y,paint);
            canvas.drawBitmap(tamagotchiUnit.getFlight(), tamagotchiUnit.x,tamagotchiUnit.y,paint);

            //отрисовка полотна на области
            getHolder().unlockCanvasAndPost(canvas);
        }
    }


    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //начать игру
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }


    //поставить на паузу
    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    функция обработки нажатия на экран
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case  MotionEvent.ACTION_UP:
                buttonIsPressed(event);
                break;
        }
        return true;
    }

    public void buttonIsPressed(MotionEvent event){
        if (mute.x<=event.getX() && event.getX()<=mute.width+mute.x && mute.y<=event.getY() && event.getY()<=mute.height+mute.y){
            if (isMute)
                isMute = false;
            else
                isMute=true;
            mute.mute=mute.changeButton(isMute);
        }
        if (play.x<=event.getX() && event.getX()<=play.width+play.x && play.y<=event.getY() && event.getY()<=play.height+play.y){
            intent = new Intent(activity, GameActivity.class);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isMute",isMute);
            editor.apply();
            activity.startActivity(intent);
        }
    }

}
