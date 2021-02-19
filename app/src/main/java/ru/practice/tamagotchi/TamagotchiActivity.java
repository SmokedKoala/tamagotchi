package ru.practice.tamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TamagotchiActivity extends AppCompatActivity {

    Button playButton;
    ImageView tamagotchiView;
    TamagotchiUnit tamagotchi;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamagotchi_activity);
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(TamagotchiClickListener);

    }

    View.OnClickListener TamagotchiClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.playButton:
                    intent = new Intent(TamagotchiActivity.this,GameActivity.class);
                    startActivity(intent);
            }
        }
    };
}
