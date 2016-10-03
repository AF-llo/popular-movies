package de.lokaizyk.popularmovies.logic;

import java.util.List;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.network.rx.MovieDetailsSubscriber;
import de.lokaizyk.popularmovies.network.rx.MoviesSubscriber;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApi;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApiFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesProvider {

    private static final String TAG = MoviesProvider.class.getSimpleName();

    public static void loadMovies(String sorting, RequestListener<List<MovieModel>> listener) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        moviesApi.getMoviesObservable(sorting, BuildConfig.MOVIESDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MoviesSubscriber(listener));
    }

    public static void loadMovieDetails(String movieId, RequestListener<MovieDetails> listener) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        moviesApi.getMovieDetailsObservable(movieId, BuildConfig.MOVIESDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MovieDetailsSubscriber(listener));
    }

    /**
     * This interface should be implemented to display the result of a request
     * @param <T>
     */
    public interface RequestListener<T> {
        void onSuccess(T data);
        void onError(String cause);
    }


}
