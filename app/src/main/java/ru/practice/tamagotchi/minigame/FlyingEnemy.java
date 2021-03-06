package ru.practice.tamagotchi.minigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import ru.practice.tamagotchi.R;

import static ru.practice.tamagotchi.minigame.GameView.screenRatioX;

public class FlyingEnemy {
    public int speed = 20;
    public boolean wasShot = true;
    int x=0, y, width, height;
    Bitmap enemy;

    FlyingEnemy(Resources resources){
        enemy = BitmapFactory.decodeResource(resources, R.drawable.ghost);

        width = (int) screenRatioX;
        height = (int) screenRatioX;

        enemy = Bitmap.createScaledBitmap(enemy,width,height,false);

        y = -height;

    }

    //функция для прорисовки противника, но у меня нет кадров для смены
    Bitmap getEnemy(){
        return enemy;
    }

    Rect getUnitShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
