package ru.practice.tamagotchi.minigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.practice.tamagotchi.R;

public class GameBackground {

    int x = 0, y = 0;
    Bitmap background ;

    GameBackground (int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);



    }

}