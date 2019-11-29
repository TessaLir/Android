package ru.vetukov.tmp.ex_4_kittenstarter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.GridView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Result> {

    // Константа для передачи URL в детальную Activity
    // через Intent
    public static final String IMAGE_URL = "IMAGE_URL";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private FlickrService service = retrofit.create(FlickrService.class);

    private GridView grid;
    private CursorAdapter adapter;
    private PhotosDBHelper helper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_main);

        // Сделаем тулбар
        Toolbar bar = findViewById(R.id.top_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Kitten");

        grid =  findViewById(R.id.grid);

        // Чтобы скроллинг "вверх" грида вызывал
        // исчезновение тулбара.
        grid.setNestedScrollingEnabled(true);

        adapter = new PhotoAdapter(this, null, 0);
        helper = new PhotosDBHelper(this);


        grid.setAdapter(adapter);


        // Загрузить картинки
        startOver();
    }

    // Порог
    // Если разница между крайним видимым элементом GridView и количеством
    // элементов в GridView меньше порога, запросим
    // еще картинки с сервера
    private static final  int threshold = 40;


    private static String createUrl(Photo p) {
        // Сервисная функция для получения URL картинки по объекту
        // Подробности https://www.flickr.com/services/api/misc.urls.html
        //Log.d("happy", url);
        return String.format(
                "https://farm%s.staticflickr.com/%s/%s_%s_q.jpg",
                p.getFarm(),
                p.getServer(),
                p.getId(),
                p.getSecret()
        );
    }

    // Начинаем с первой страницы
    private int currentPage = 1;

    // Запрос по-умолчанию
    private String term = "kitten";


    // Выполняется при старте приложения
    // или изменении поискового запроса
    private void startOver() {
        // Начнем с первой страницы
        currentPage = 1;
        // Загрузим
        loadMore(currentPage, term);
    }

    // Исользуется для загрузки новой порции изобразений из сервиса
    // Вызов Retrofit
    private void loadMore(int page, String search) {
        loading = true;

        Call<Result> call = service.search(
                "flickr.photos.search",
                "0bfc196010b2e1c650a8bda8f2191ac5",
                search,
                "json",
                1,
                page
        );
        // так делать не надо
        call.enqueue(this);

    }


    // Только один процесс загрузки данных с сервера
    private boolean loading = false;


    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        Result result = response.body();
        if(result.getStat().equals("ok"))
        {
            Photos photos = result.getPhotos();
            currentPage = photos.getPage();
            List<Photo> allPhotos = photos.getPhoto();

            SQLiteDatabase db = helper.getWritableDatabase();

            for(Photo p : allPhotos)
            {
                Log.d("happy", "onResponse " + p.getTitle());

                String url = createUrl(p);
                ContentValues values = new ContentValues();
                values.put(PhotosTable.COLUMN_URL, url);

                db.insert(
                        PhotosTable.TABLE_PHOTOS,
                        null,
                        values
                );
            }

            Cursor cursor = getPhotoCursor();
            updateCursor(cursor);
        }

        loading = false;
    }

    private void updateCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    private Cursor getPhotoCursor() {
        return  helper.getReadableDatabase().query(
                PhotosTable.TABLE_PHOTOS,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {
        Log.d("happy", "onFailure " + t);
        loading = false;
    }
}
