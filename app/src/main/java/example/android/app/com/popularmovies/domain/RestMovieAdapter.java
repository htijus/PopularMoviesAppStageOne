package example.android.app.com.popularmovies.domain;

import example.android.app.com.popularmovies.BuildConfig;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestMovieAdapter {
    private final static String BASE_URL = "http://api.themoviedb.org/3/";

    private final static String API_KEY = "API_KEY";

    private static RestAdapter movieRestAdapter;
    private static RequestInterceptor movieRequestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addQueryParam("api_key", API_KEY);
        }
    };

    public static RestAdapter getRestAdapter() {
        if (movieRestAdapter == null) {
            movieRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setRequestInterceptor(movieRequestInterceptor)
                    .build();
            if (BuildConfig.DEBUG) {
                movieRestAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
            }
        }
        return movieRestAdapter;
    }
}
