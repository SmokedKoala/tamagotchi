package ru.practice.tamagotchi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private FlyingTamagotchi flyingTamagotchi;
    //для прокрутки заднего фона создаём 2 объекта и будем смещать их одновременно в сторону
    private GameBackground gameBackground1, gameBackground2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX =screenX;
        this.screenY= screenY;
        screenRatioX = 1920f/screenX;
        screenRatioY = 1080f/screenY;
        gameBackground1 = new GameBackground(screenX, screenY, getResources());
        gameBackground2 = new GameBackground(screenX, screenY, getResources());

        flyingTamagotchi = new FlyingTamagotchi(screenY,screenX,getResources());
        //присваиваем положение вне видимости экрана для второго заднего фона
        gameBackground2.x=screenX;

        paint = new Paint();
    }

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }
    //присвоить новые координаты
    private void update(){
        //перемещение фонового изображения назад, когда оно выходит из видимости экрана
        gameBackground1.x -= (10 * screenRatioX);
        if (gameBackground1.x + this.screenX < 0){
            gameBackground1.x = screenX;
        }
        if (gameBackground1.x < 0) {
            gameBackground2.x = gameBackground1.x + this.screenX;
        }
        else {
            gameBackground2.x = gameBackground1.x - this.screenX;
        }
        //если переменная = true то y координата уменьшается
        if (flyingTamagotchi.isGoingUp){
            flyingTamagotchi.y -=30 * screenRatioY;
        }
        //если переменная = false то y координата увеличивается
        else {
            flyingTamagotchi.y +=30 * screenRatioY;
        }
        // если тамагочи выше верхнего края экрана, выровнить его
        if (flyingTamagotchi.y<0){
            flyingTamagotchi.y = 0;
        }
        // если тамагочи ниже нижнего края экрана, выровнить его
        if (flyingTamagotchi.y > screenY - flyingTamagotchi.height*2){
            flyingTamagotchi.y = screenY - flyingTamagotchi.height*2;
        }
    }

    //отрисовать новые позиции
    private void draw(){
        //проверка область для прорисовки создалась
        if (getHolder().getSurface().isValid()){
            //создание полотна для области
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(gameBackground1.background, gameBackground1.x,gameBackground1.y, paint);
            canvas.drawBitmap(gameBackground2.background, gameBackground2.x,gameBackground2.y, paint);
            //отрисовка тамагочи
            canvas.drawBitmap(flyingTamagotchi.getFlight(), flyingTamagotchi.x,flyingTamagotchi.y,paint);

            //отрисовка полотна на области
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    //приостановть метод
    private  void sleep(){
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
        switch (event.getAction()){
//          когда происходит нажатие на экран
            case MotionEvent.ACTION_DOWN:
                //если нажата левая часть экрана
                if (event.getX() < screenX/2){
                    flyingTamagotchi.isGoingUp = true;
                }
                break;
//          когда не происходит нажатие на экран
            case  MotionEvent.ACTION_UP:
                flyingTamagotchi.isGoingUp = false;
                break;
        }
        return true;
    }
}
