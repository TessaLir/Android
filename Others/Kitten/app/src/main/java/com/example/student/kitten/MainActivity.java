package com.example.student.kitten;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// INFO https://www.flickr.com/services/api/explore/flickr.photos.search

public class MainActivity
        extends AppCompatActivity
        implements Callback<Result>
                    , AdapterView.OnItemClickListener
                    , AbsListView.OnScrollListener {

    // Константа для передачи URL в детальную Activity
    // через Intent
    public static final String IMAGE_URL = "IMAGE_URL";

    public static final String sql = "   insert into    " +
            PhotosTable.TABLE_PHOTOS +
            "   (   " +
            PhotosTable.COLUMN_URL +
            "   )   values (   ?    ) ;  ";

    // "insert into photos (url) values (?);"


    private SQLiteStatement statement;



    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private FlickrService service = retrofit.create(FlickrService.class);


    private GridView grid;
    private CursorAdapter adapter;
    private PhotosDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_main);

        // Сделаем тулбар
        Toolbar bar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Kitten");

        grid = (GridView) findViewById(R.id.grid);

        // Чтобы скроллинг "вверх" грида вызывал
        // исчезновение тулбара.
//        grid.setNestedScrollingEnabled(true);

        adapter = new PhotoAdapter(this, null, 0);
        helper = new PhotosDBHelper(this);

        // скомпилированный параметризованный sql запрос
        statement = helper.getWritableDatabase().compileStatement(sql);


        grid.setAdapter(adapter);

        grid.setOnItemClickListener(this);
        grid.setOnScrollListener(this);

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

        helper.getWritableDatabase().delete(
                PhotosTable.TABLE_PHOTOS,
                null,
                null
        );

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
                "376a4dc5019705c18a701ffcc2261174",
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

        long startTime = System.currentTimeMillis();

        if(result.getStat().equals("ok"))
        {
            Photos photos = result.getPhotos();
            currentPage = photos.getPage();
            List<Photo> allPhotos = photos.getPhoto();

            SQLiteDatabase db = helper.getWritableDatabase();


            db.beginTransaction();
            for(Photo p : allPhotos)
            {
                // Log.d("happy", "onResponse " + p.getTitle());
                String url = createUrl(p);
//                ContentValues values = new ContentValues();
//                values.put(PhotosTable.COLUMN_URL, url);
//
//                db.insert(
//                        PhotosTable.TABLE_PHOTOS,
//                         null,
//                        values
//                );
                statement.bindString(1, url);
                statement.execute();
            }
            db.setTransactionSuccessful();
            db.endTransaction();

            Cursor cursor = getPhotoCursor();
            updateCursor(cursor);
        }

        loading = false;

        long endTime = System.currentTimeMillis();

        Log.d("time", "insert time (ms): " + (endTime - startTime));

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);

        String url = cursor.getString(
                cursor.getColumnIndex(PhotosTable.COLUMN_URL)
        );

        Intent detail = new Intent(this, Detail.class);

        detail.putExtra(IMAGE_URL, url);

        startActivity(detail);

        // ImageViewTouch
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // Только в состоянии покоя
        if(scrollState == SCROLL_STATE_IDLE)
        {
            int last  = grid.getLastVisiblePosition();
            int total = grid.getCount();
            // только если ничего не загружаем
            if(!loading)
            {
                if( (total - last) < threshold )
                {
                    loadMore(currentPage + 1, term);
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.main_search)
        {

            handleSearch(item);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void handleSearch(MenuItem item) {

        final SearchView searchView = (SearchView) item.getActionView();

        item.expandActionView();

        searchView.setQuery(term, false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 0. если введенное значение отличается от старого, то:
                if(!term.equals(query))
                {
                    //      1. term = ...
                    //      2. заново запускать поиск с новым термином
                    term = query;
                    searchView.clearFocus();
                    startOver();

                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}













