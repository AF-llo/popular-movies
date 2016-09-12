package de.lokaizyk.popularmovies.logic;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieModel;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesProvider {

    private static final String TAG = MoviesProvider.class.getSimpleName();

    // TODO: 12.09.16 add sorting as parameter

    private static final String URL_POPULAR_MOVIES = "popular";

    private static final String URL_TOP_RATED_MOVIES = "top_rated";

    public static void loadMovies(RequestType type, final RequestListener<List<MovieModel>> listener) {
        Log.d(TAG, "load " + type.name() + " moviews");
        // TODO: 12.09.16 implement
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<MovieModel> movies = new ArrayList<>();
                movies.add(new MovieModel("/asdf.jpg", "550"));
                movies.add(new MovieModel("/jklö.jpg", "660"));
                if (listener != null) {
                    listener.onSuccess(movies);
                }
            }
        }, 1000);
    }

    public static void loadMovieDetails(String movieId, RequestListener<MovieDetails> listener) {
        Log.d(TAG, "load details for ID=" + movieId );
        // TODO: 12.09.16 implement
    }

    public enum RequestType {
        POPULAR(URL_POPULAR_MOVIES),
        TOP_RATED(URL_TOP_RATED_MOVIES);

        private String url = "";

        RequestType(String url) {
            if (!TextUtils.isEmpty(url)) {
                this.url = url;
            }
        }

        public String getUrl() {
            return BuildConfig.BASE_MOVIE_URL + url;
        }
    }

    public interface RequestListener<T> {
        void onSuccess(T data);
        void onError(String cause);
    }

}
