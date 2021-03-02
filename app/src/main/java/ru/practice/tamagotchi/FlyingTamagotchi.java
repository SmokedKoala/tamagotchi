package ru.practice.tamagotchi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class FlyingTamagotchi {
    public boolean isGoingUp = false ;
    int toShoot = 0;
    int  width, height,x, y, wingCounter = 0;
    Bitmap flight1, flight2, attack1;
    private GameView gameView;

    FlyingTamagotchi(GameView gameView, int screenY, int screenX, Resources res){

        this.gameView = gameView;
        flight1 = BitmapFactory.decodeResource(res, R.drawable.dragon_1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.dragon_2);
        attack1 = BitmapFactory.decodeResource(res, R.drawable.dragon_3);

        width = screenX/10;
        height = screenX/10;


        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);
        attack1 = Bitmap.createScaledBitmap(attack1, width, height, false);

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
}
