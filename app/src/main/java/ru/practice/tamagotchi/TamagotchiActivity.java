package ru.practice.tamagotchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TamagotchiActivity extends AppCompatActivity {

    Button playButton, volumeButton;
    ImageView tamagotchiView;
    TamagotchiUnit tamagotchi;
    Intent intent;
    private boolean isMute;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamagotchi_activity);
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(TamagotchiClickListener);
        volumeButton = findViewById(R.id.volumeButton);
        volumeButton.setOnClickListener(TamagotchiClickListener);
        preferences = getSharedPreferences("mini-game",MODE_PRIVATE);
        isMute = preferences.getBoolean("isMute",false);

    }

    View.OnClickListener TamagotchiClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.playButton:
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isMute",isMute);
                    editor.apply();
                    intent = new Intent(TamagotchiActivity.this,GameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.volumeButton:
                    if (!isMute){
                        volumeButton.setText("Unmute");
                        isMute = true;
                    } else {
                        volumeButton.setText("Mute");
                        isMute=false;
                    }
                    break;
            }
        }
    };
}
