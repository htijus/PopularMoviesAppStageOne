package example.android.app.com.popularmovies.domain;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RestMovieServiceAdapter {
    @GET("/discover/movie")
    void getMovieList(@Query("sort_by") String sortBy, Callback<MovieResponseList> callback);
}
