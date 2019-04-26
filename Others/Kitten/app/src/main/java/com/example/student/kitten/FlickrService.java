package com.example.student.kitten;


/*
https://api.flickr.com
/services/rest/?
method=flickr.photos.search&
api_key=0bfc196010b2e1c650a8bda8f2191ac5&
text=cats&
format=json&
nojsoncallback=1&
page=14
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {
    @GET("/services/rest/")
    Call<Result> search (
            @Query("method") String method ,
            @Query("api_key") String key,
            @Query("text") String text,
            @Query("format") String format,
            @Query("nojsoncallback") int callback,
            @Query("page") int page
    );
}
