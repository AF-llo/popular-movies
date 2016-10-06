package de.lokaizyk.popularmovies.persistance.rx;

import android.text.TextUtils;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApi;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApiFactory;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;
import de.lokaizyk.popularmovies.network.rx.MovieDetailsZipper;
import de.lokaizyk.popularmovies.persistance.DbManager;
import de.lokaizyk.popularmovies.persistance.model.DbMovieDetails;
import de.lokaizyk.popularmovies.util.ModelHelper;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by lars on 05.10.16.
 */

public class MovieDetailsLoader implements Func0<Observable<MovieDetails>> {

    private String movieId = "";

    private MovieDetailsLoader(String id) {
        this.movieId = id;
    }

    @Override
    public Observable<MovieDetails> call() {
        if (TextUtils.isEmpty(movieId)) {
            return Observable.error(new IllegalArgumentException("Cannot load movie from DB becaus movieId was null or empty!"));
        }
        DbMovieDetails movieDetails = DbManager.getInstance().loadFavoriteMovie(movieId);
        if (movieDetails != null) {
            // Already Favorite Movie
            return Observable.just(ModelHelper.dbMovieDetailsToMovieDetails(movieDetails));
        } else {
            // No favorite -> load from API
            PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
            Observable<MovieDetailsResponse> movieDetailsObservable = moviesApi.getMovieDetailsObservable(movieId, BuildConfig.MOVIESDB_API_KEY);
            Observable<MovieVideosResponse> movieVideosObservable = moviesApi.getMovieVideosObservable(movieId, BuildConfig.MOVIESDB_API_KEY);
            Observable<MovieReviewsResponse> movieReviewsObservable = moviesApi.getMovieReviewsObservable(movieId, BuildConfig.MOVIESDB_API_KEY);
            return Observable.zip(movieDetailsObservable, movieVideosObservable, movieReviewsObservable, new MovieDetailsZipper());
        }
    }

    public static Observable<MovieDetails> loadDetails(String id) {
        return Observable.defer(new MovieDetailsLoader(id));
    }

}
