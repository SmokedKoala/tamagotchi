package ru.practice.tamagotchi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class FlyingTamagotchi {
    public boolean isGoingUp = false ;
    int  width, height,x, y, wingCounter = 0;
    Bitmap flight1, flight2;

    FlyingTamagotchi(int screenY, int screenX, Resources res){
        flight1 = BitmapFactory.decodeResource(res, R.drawable.dragon_1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.dragon_2);

        width = screenX/10;
        height = screenX/10;


        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        y = screenY/2;
        x = screenX/10;
    }

    //функция для смены кадров взмаха крыла
    Bitmap getFlight(){
        if (wingCounter == 0){
            wingCounter++;
            return flight1;
        }
        wingCounter --;
        return flight2;
    }
}
