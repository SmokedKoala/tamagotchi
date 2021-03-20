package ru.practice.tamagotchi.tamagotchiactivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class TamagotchiActivity extends AppCompatActivity {


    private TamagotchiView tamagotchiView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //создание переменной Point для получения размеров экрана
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        tamagotchiView = new TamagotchiView(this,point.x,point.y);

        setContentView(tamagotchiView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        tamagotchiView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tamagotchiView.resume();
    }
}
