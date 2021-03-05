package ru.practice.tamagotchi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private FlyingEnemy[] flyingEnemies;
    private Random random;
    private FlyingTamagotchi flyingTamagotchi;
    List<FireBall> fireBalls;
    //для прокрутки заднего фона создаём 2 объекта и будем смещать их одновременно в сторону
    private GameBackground gameBackground1, gameBackground2;



    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX =screenX;
        this.screenY= screenY;

//        нужно настроить переменные для адекватного масштабирования
        screenRatioX = screenX/10;
        screenRatioY = screenY/10;

        gameBackground1 = new GameBackground(screenX, screenY, getResources());
        gameBackground2 = new GameBackground(screenX, screenY, getResources());

        flyingTamagotchi = new FlyingTamagotchi(this,screenY,screenX,getResources());
        fireBalls = new ArrayList<>();
        //присваиваем положение вне видимости экрана для второго заднего фона
        gameBackground2.x=screenX;

        paint = new Paint();

        flyingEnemies = new FlyingEnemy[4];
        for(int i = 0; i< flyingEnemies.length;i++){
            FlyingEnemy flyingEnemy = new FlyingEnemy(getResources());
            flyingEnemies[i] = flyingEnemy;
        }
        random = new Random();
    }




    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }



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
            flyingTamagotchi.y -=30 ;
        }
        //если переменная = false то y координата увеличивается
        else {
            flyingTamagotchi.y +=30 ;
        }
        // если тамагочи выше верхнего края экрана, выровнить его
        if (flyingTamagotchi.y<0){
            flyingTamagotchi.y = 0;
        }
        // если тамагочи ниже нижнего края экрана, выровнить его
        if (flyingTamagotchi.y > screenY - flyingTamagotchi.height*2){
            flyingTamagotchi.y = screenY - flyingTamagotchi.height*2;
        }

        List<FireBall> trash = new ArrayList<>();

        for (FireBall fireBall : fireBalls){
            //когда фаербол выходит за пределы экрана - добавить его в список на удаление
            if (fireBall.x > screenX){
                trash.add((fireBall));
            }
            //иначе изменить его координату
            fireBall.x += 50 ;

            //если сталкиваются фаербол и противник
            for (FlyingEnemy flyingEnemy : flyingEnemies){
                if (Rect.intersects(flyingEnemy.getUnitShape(),fireBall.getUnitShape())){
                    flyingEnemy.x = -screenX;
                    fireBall.x = screenX *2;
                    flyingEnemy.wasShot = true;
                }
            }
        }
        //удаление фаерболов из списка на удаление
        for (FireBall fireBall : trash){
            fireBalls.remove(fireBall);
        }

        for (FlyingEnemy flyingEnemy : flyingEnemies){
            //смещение координты врага
            flyingEnemy.x -= flyingEnemy.speed;
            //если враг добрался до конца экрана
            if (flyingEnemy.x + flyingEnemy.width < 0){

//          усложнение игры: проигрыш, если пропускаешь одного
//                if(!flyingEnemy.wasShot){
//                    isGameOver = true;
//                    return;
//                }

//                int bound = (int) (30 * screenRatioX);
                int bound = (int) (30);
                //установить ему новую случайную скорость
                flyingEnemy.speed = random.nextInt(bound);

//                if (flyingEnemy.speed < 10 * screenRatioX)
//                  flyingEnemy.speed = (int) (10 * screenRatioX);
                if (flyingEnemy.speed < 10 )
                    flyingEnemy.speed = (int) (10);

                //перенести врага направо и установить случайную y-координату
                flyingEnemy.x = screenX;
                flyingEnemy.y = random.nextInt(screenY - 2*flyingEnemy.height);

                flyingEnemy.wasShot = false;
            }

            //если сталкиваются враг и тамогочи
            if(Rect.intersects(flyingEnemy.getUnitShape(), flyingTamagotchi.getUnitShape())){
                isGameOver = true;
                return;
            }
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

            if(isGameOver){
                isPlaying = false;
                canvas.drawBitmap(flyingTamagotchi.getDeath(),flyingTamagotchi.x,flyingTamagotchi.y,paint);
                for (FlyingEnemy flyingEnemy: flyingEnemies){
                    canvas.drawBitmap(flyingEnemy.getEnemy(),flyingEnemy.x,flyingEnemy.y,paint);
                }
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            //отрисовка врагов
            for (FlyingEnemy flyingEnemy: flyingEnemies){
                canvas.drawBitmap(flyingEnemy.getEnemy(),flyingEnemy.x,flyingEnemy.y,paint);
            }

            //отрисовка тамагочи
            canvas.drawBitmap(flyingTamagotchi.getFlight(), flyingTamagotchi.x,flyingTamagotchi.y,paint);

            //отрисовка фаерболов
            for (FireBall fireBall : fireBalls){
                canvas.drawBitmap(fireBall.fireball, fireBall.x,fireBall.y,paint);
            }

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
//          ACTION_DOWN предназначен для первого пальца, который касается экрана
            case MotionEvent.ACTION_DOWN:
                //если нажата левая часть экрана
                if (event.getX() < screenX/2){
                    flyingTamagotchi.isGoingUp = true;
                }
                break;
//          ACTION_UP отправляется, когда последний палец покидает экран
            case  MotionEvent.ACTION_UP:
                flyingTamagotchi.isGoingUp = false;
                //если нажата правая часть экрана
                if (event.getX() > screenX/2){
                    flyingTamagotchi.toShoot++;
                }
                break;
        }
        return true;
    }



    //создание нового фаербола
    public void newFireBall() {
        FireBall fireBall = new FireBall(getResources());
        fireBall.x = flyingTamagotchi.x + flyingTamagotchi.width/4;
        fireBall.y = flyingTamagotchi.y + flyingTamagotchi.height/4;
        fireBalls.add(fireBall);
    }
}
