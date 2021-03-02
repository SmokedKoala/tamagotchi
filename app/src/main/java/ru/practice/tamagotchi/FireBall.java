package ru.practice.tamagotchi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FireBall {
    int x,y;
    Bitmap fireball;

    FireBall(Resources resources){
        fireball = BitmapFactory.decodeResource(resources,R.drawable.fireball);
        int wight = fireball.getWidth()/2;
        int height = fireball.getHeight()/2;
        fireball = Bitmap.createScaledBitmap(fireball, wight,height,false);
    }
}
