package example.android.app.com.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import example.android.app.com.popularmovies.domain.MovieResponseList;

public class DetailsFragment extends Fragment {

    public final static String ARG_MOVIE = "movie";

    public final static String RATING_CONSTANT = "Rating: ";

    public final static String RELEASE_DATE_CONSTANT = "Release Date: ";

    public final static String DATE_FORMAT = "dd-MM-yyyy";

    private ImageView moviePosterImageView;

    private TextView movieRatingTextView;

    private TextView movieDateTextView;

    private TextView movieOverviewTextView;

    private MovieResponseList.Movie movie;

    public static DetailsFragment getInstance(MovieResponseList.Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getArguments().getParcelable(ARG_MOVIE);
        if (movie == null) {
            throw new NullPointerException("Movie object should be put into fragment arguments.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_fragment, container, false);
        moviePosterImageView = (ImageView) v.findViewById(R.id.poster_image_view);
        movieRatingTextView = (TextView) v.findViewById(R.id.rating_text_view);
        movieDateTextView = (TextView) v.findViewById(R.id.date_text_view);
        movieOverviewTextView = (TextView) v.findViewById(R.id.overview_text_view);

        Glide.with(getActivity())
                .load(movie.getPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.poster_placeholder)
                .crossFade()
                .into(moviePosterImageView);

        movieRatingTextView.setText(RATING_CONSTANT + Float.toString(movie.rating));
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        String desiredDateStr = movie.releaseDate;
        try{
            date = sourceDateFormat.parse(movie.releaseDate);
            desiredDateStr = desiredDateFormat.format(date);
        }catch (ParseException pe){
            //do nothing
        }

        movieDateTextView.setText(RELEASE_DATE_CONSTANT + desiredDateStr);
        movieOverviewTextView.setText(movie.overview);
        return v;
    }
}
