package ru.practice.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.practice.tamagotchi.tamagotchiactivity.TamagotchiActivity;

public class MainActivity extends AppCompatActivity {

    Button start;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        start.setOnClickListener(startMenuClickListener);
    }

    View.OnClickListener startMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start:
                    intent = new Intent(MainActivity.this, TamagotchiActivity.class);
                    startActivity(intent);
            }
        }
    };
}