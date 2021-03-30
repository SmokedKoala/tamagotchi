package ru.practice.tamagotchi.tamagotchiactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.practice.tamagotchi.R;

public class Play {
    int  width, height;
    float x, y;
    Bitmap play;

    Play(int screenY, int screenX, Resources res){
        width = screenX/10;
        height = screenX/10;
        x = screenX*0.08f;
        y = screenY*0.65f;
        play = BitmapFactory.decodeResource(res, R.drawable.play);
        play = Bitmap.createScaledBitmap(play,width,height,false);
    }
    public Bitmap getButton(){
        return play;
    }
}
