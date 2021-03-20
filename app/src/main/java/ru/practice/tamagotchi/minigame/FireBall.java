package ru.practice.tamagotchi.minigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import ru.practice.tamagotchi.R;

public class FireBall {
    int x,y,width,height;
    Bitmap fireball;

    FireBall(Resources resources){
        fireball = BitmapFactory.decodeResource(resources, R.drawable.fireball);
        width = fireball.getWidth()/2;
        height = fireball.getHeight()/2;
        fireball = Bitmap.createScaledBitmap(fireball, width,height,false);
    }

    Rect getUnitShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
