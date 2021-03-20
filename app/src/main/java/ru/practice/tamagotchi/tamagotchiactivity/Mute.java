package ru.practice.tamagotchi.tamagotchiactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.practice.tamagotchi.R;

public class Mute {

    int  width, height;
    float x, y;
    Bitmap mute;
    Resources res;
    boolean isMute;

    Mute(int screenY, int screenX, Resources res, boolean isMute){
        this.res = res;
        this.isMute = isMute;
        width = screenX/10;
        height = screenX/10;
        x = screenX*0.88f;
        y = screenY*0.05f;
        if (isMute)
            this.mute = BitmapFactory.decodeResource(res, R.drawable.mute);
        else
            this.mute = BitmapFactory.decodeResource(res, R.drawable.volume);
        this.mute = Bitmap.createScaledBitmap(mute,width,height,false);
    }

    public Bitmap getButton(){
        return mute;
    }

    public Bitmap changeButton(boolean isMute){
        if (isMute) {
            mute = BitmapFactory.decodeResource(res, R.drawable.mute);
        }
        else {
            mute = BitmapFactory.decodeResource(res, R.drawable.volume);
        }
        return Bitmap.createScaledBitmap(mute,width,height,false);
    }
}
