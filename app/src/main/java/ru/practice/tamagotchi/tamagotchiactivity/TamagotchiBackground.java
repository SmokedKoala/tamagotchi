package ru.practice.tamagotchi.tamagotchiactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.practice.tamagotchi.R;

public class TamagotchiBackground {

    int x = 0, y = 0;
    Bitmap background ;

    TamagotchiBackground (int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);



    }
}
