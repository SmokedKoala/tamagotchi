package ru.practice.tamagotchi.minigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import ru.practice.tamagotchi.R;
import ru.practice.tamagotchi.minigame.GameView;

import static ru.practice.tamagotchi.minigame.GameView.screenRatioX;


public class FlyingTamagotchi {
    public boolean isGoingUp = false ;
    int toShoot = 0;
    int  width, height,x, y, wingCounter = 0;
    Bitmap flight1, flight2, attack1, dead;
    private GameView gameView;

    FlyingTamagotchi(GameView gameView, int screenY, int screenX, Resources res){

        this.gameView = gameView;
        flight1 = BitmapFactory.decodeResource(res, R.drawable.dragon_1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.dragon_2);
        attack1 = BitmapFactory.decodeResource(res, R.drawable.dragon_3);
        dead = BitmapFactory.decodeResource(res, R.drawable.dragon_death);

        width = (int) screenRatioX;
        height = (int) screenRatioX;


        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);
        attack1 = Bitmap.createScaledBitmap(attack1, width, height, false);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY/2;
        x = screenX/10;
    }

    //функция для смены кадров взмаха крыла и атаки
    Bitmap getFlight(){
        if (toShoot != 0){
            toShoot--;
            gameView.newFireBall();
            return attack1;
        }

        if (wingCounter == 0){
            wingCounter++;
            return flight1;
        }
        wingCounter --;
        return flight2;
    }

    Rect getUnitShape(){
        return new Rect(x,y,x+width,y+height);
    }

    Bitmap getDeath(){
        return dead;
    }
}
