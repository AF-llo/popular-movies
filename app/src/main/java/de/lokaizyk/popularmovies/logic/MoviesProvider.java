package de.lokaizyk.popularmovies.logic;

import java.io.IOException;
import java.net.URL;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.MovieDetailsParser;
import de.lokaizyk.popularmovies.network.RequestHelper;
import de.lokaizyk.popularmovies.network.RequestTask;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApi;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApiFactory;
import de.lokaizyk.popularmovies.network.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesProvider {

    private static final String TAG = MoviesProvider.class.getSimpleName();

    public static void loadMovies(String sorting, Callback<MoviesResponse> callback) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        Call<MoviesResponse> moviesResponseCall = moviesApi.loadMovies(sorting, BuildConfig.MOVIESDB_API_KEY);
        moviesResponseCall.enqueue(callback);
    }

    // TODO: 03.10.16 load movie details with retrofit 
    public static void loadMovieDetails(String movieId, RequestListener<MovieDetails> listener) {
        try {
            URL movieDetailsUrl = new URL(RequestHelper.getMovieDetailsUrl(BuildConfig.BASE_MOVIE_URL + movieId));
            new RequestTask<>(listener, new MovieDetailsParser()).execute(movieDetailsUrl);
        } catch (IOException e) {
            onError(e.getMessage(), listener);
        }
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
