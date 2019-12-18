package ru.skillbranch.ex_047_063_playing_audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BTN_PLAY = "PLAY";
    public static final String BTN_PAUSE = "PAUSE";

    private Button btnPlay;
    private Button btnStop;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.mertvie_zvezdy);

        btnPlay = findViewById(R.id.main_btn_play);
        btnStop = findViewById(R.id.main_btn_stop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.main_btn_play :
                if (!mp.isPlaying()) {
                    mp.start();
                    btnPlay.setText(BTN_PAUSE);
                } else {
                    mp.pause();
                    btnPlay.setText(BTN_PLAY);
                }
                break;
            case R.id.main_btn_stop :
                mp.stop();
                break;
        }
    }
}
