package de.lokaizyk.popularmovies.logic;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.network.MoviesParser;
import de.lokaizyk.popularmovies.network.RequestHelper;
import de.lokaizyk.popularmovies.network.RequestTask;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesProvider {

    private static final String TAG = MoviesProvider.class.getSimpleName();

    public static void loadMovies(String sorting, final RequestListener<List<MovieModel>> listener) {
        try {
            URL moviesUrl = new URL(RequestHelper.getMoviesRequestUrl(BuildConfig.BASE_MOVIE_URL + sorting));
            new RequestTask<>(listener, new MoviesParser()).execute(moviesUrl);
        } catch (IOException e) {
            onError(e.getMessage(), listener);
        }
    }

    public static void loadMovieDetails(String movieId, RequestListener<MovieDetails> listener) {
        Log.d(TAG, "load details for ID=" + movieId );
        // TODO: 12.09.16 implement
    }

    private static void onError(String cause, RequestListener listener) {
        if (listener != null && cause != null) {
            listener.onError(cause);
        }
    }

    public interface RequestListener<T> {
        void onSuccess(T data);
        void onError(String cause);
    }


}
