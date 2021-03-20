package ru.practice.tamagotchi.minigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.practice.tamagotchi.R;

public class Pause {

    int  width, height;
    float x, y;
    Bitmap pause;

    Pause(int screenY, int screenX, Resources res){
        width = screenX/10;
        height = screenX/10;
        x = screenX*0.88f;
        y = screenY*0.05f;
        pause = BitmapFactory.decodeResource(res, R.drawable.pause);
        pause = Bitmap.createScaledBitmap(pause,width,height,false);
    }
    public Bitmap getButton(){
        return pause;
    }
}
