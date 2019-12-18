package ru.skillbranch.ex_047_063_using_video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView view = findViewById(R.id.main_vv_video);
        view.setVideoPath("android.resource://" + getPackageName() +
                "/" + R.raw.mertvyie_zvezdyi);

        MediaController controller = new MediaController(this);
        controller.setAnchorView(view);
        view.setMediaController(controller);

        view.start();
    }
}
