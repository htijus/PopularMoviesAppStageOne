package example.android.app.com.popularmovies;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import example.android.app.com.popularmovies.domain.MovieResponseList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        MovieResponseList.Movie movie;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = extras.getParcelable(DetailsFragment.ARG_MOVIE);
        } else {
            throw new NullPointerException("No movie found");
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.title);
        }

        DetailsFragment fragment = DetailsFragment.getInstance(movie);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
