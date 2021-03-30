package ru.practice.tamagotchi.tamagotchiactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import ru.practice.tamagotchi.R;
import ru.practice.tamagotchi.minigame.GameView;

import static ru.practice.tamagotchi.minigame.GameView.screenRatioX;

public class TamagotchiUnit {
    private int mood;
    private int hunger;
    private int thirst;
    private int purity;
    Bitmap flight1, flight2, flight3;
    private TamagotchiView tamagotchiView;
    int  width, height,x, y, wingCounter = 0;

    TamagotchiUnit(TamagotchiView tamagotchiView, int screenY, int screenX, Resources res){
        this.tamagotchiView = tamagotchiView;
        flight1 = BitmapFactory.decodeResource(res, R.drawable.dragon_front_1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.dragon_front_2);
        flight3 = BitmapFactory.decodeResource(res, R.drawable.dragon_front_3);
        this.hunger = 100;
        this.mood = 100;
        this.purity = 100;
        this.thirst = 100;
        width = (int) screenRatioX*5;
        height = (int) screenRatioX*5;
        y = screenY/4;
        x = screenX/3;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getPurity() {
        return purity;
    }

    public void setPurity(int purity) {
        this.purity = purity;
    }

    Bitmap getFlight(){

        if (wingCounter == 0){
            wingCounter++;
            return flight1;
        }
        if (wingCounter == 1){
            wingCounter++;
            return flight2;
        }
        wingCounter = 0;
        return flight3;
    }

    Rect getUnitShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
