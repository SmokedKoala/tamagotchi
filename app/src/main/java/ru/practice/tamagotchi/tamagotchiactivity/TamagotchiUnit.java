package ru.practice.tamagotchi.tamagotchiactivity;

import android.graphics.drawable.Drawable;

public class TamagotchiUnit {
    private int mood;
    private int hunger;
    private int thirst;
    private int purity;

    TamagotchiUnit(){
        this.hunger = 100;
        this.mood = 100;
        this.purity = 100;
        this.thirst = 100;
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
}
