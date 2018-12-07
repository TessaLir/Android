package ru.vetukov.cinema.mycinemaviewers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ru.vetukov.cinema.mycinemaviewers.objects.Film;

public class CinemaInfoActivity extends AppCompatActivity {

    private TextView mTVName;
    private TextView mTVLName;
    private TextView mTVRating;
    private TextView mTVCComments;
    private TextView mTVDescription;

    private ImageView mIVAfisha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);

        mTVLName        = findViewById(R.id.view_detail_localname);
        mTVName         = findViewById(R.id.view_detail_name);
        mTVRating       = findViewById(R.id.view_detail_rating);
        mTVCComments    = findViewById(R.id.view_detail_countcomments);


        Intent intent = getIntent();

        Film film = intent.getParcelableExtra("FILM");

        mTVLName.setText(film.getLocalName());
        mTVName.setText(film.getName());
        mTVRating.setText(film.getReting());
        mTVCComments.setText(film.getCommentCount());


    }
}
