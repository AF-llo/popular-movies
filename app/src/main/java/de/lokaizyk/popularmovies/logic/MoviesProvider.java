package de.lokaizyk.popularmovies.logic;

import android.util.Log;

import java.util.List;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApi;
import de.lokaizyk.popularmovies.network.api.PopularMoviesApiFactory;
import de.lokaizyk.popularmovies.network.rx.MovieReviewsSubscriber;
import de.lokaizyk.popularmovies.network.rx.MovieTrailersSubscriber;
import de.lokaizyk.popularmovies.network.rx.MoviesSubscriber;
import de.lokaizyk.popularmovies.persistance.rx.AllFavoriteMovieLoader;
import de.lokaizyk.popularmovies.persistance.rx.MovieDetailsSubscriber;
import de.lokaizyk.popularmovies.persistance.rx.FavoriteMoviesSubscriber;
import de.lokaizyk.popularmovies.persistance.rx.MovieDetailsLoader;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.util.SubscriptionList;
import rx.schedulers.Schedulers;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesProvider {

    private static final String TAG = MoviesProvider.class.getSimpleName();

    private static SubscriptionList mSubscriptionList = new SubscriptionList();

    public static void loadFavoriteMovies(RequestListener<List<MovieModel>> listener) {
        Subscription favoritMovieSubscription = AllFavoriteMovieLoader.get()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FavoriteMoviesSubscriber(listener));
        mSubscriptionList.add(favoritMovieSubscription);
    }

    public static void loadMovieDetails(String movieId, RequestListener<MovieDetails> listener) {
        Subscription favoritMovieSubscription = MovieDetailsLoader.loadDetails(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MovieDetailsSubscriber(listener));
        mSubscriptionList.add(favoritMovieSubscription);
    }

    public static void loadMovies(String sorting, RequestListener<List<MovieModel>> listener) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        Subscription moviesSubscription = moviesApi.getMoviesObservable(sorting, BuildConfig.MOVIESDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MoviesSubscriber(listener));
        mSubscriptionList.add(moviesSubscription);
    }

    public static void loadMovieTrailers(String movieId, RequestListener<List<MovieVideo>> listener) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        Subscription movieDetailsSubscription = moviesApi.getMovieVideosObservable(movieId, BuildConfig.MOVIESDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MovieTrailersSubscriber(listener));
        mSubscriptionList.add(movieDetailsSubscription);
    }

    public static void loadMovieReviews(String movieId, RequestListener<List<MovieReview>> listener) {
        PopularMoviesApi moviesApi = PopularMoviesApiFactory.getInstance().createService();
        Subscription movieDetailsSubscription = moviesApi.getMovieReviewsObservable(movieId, BuildConfig.MOVIESDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MovieReviewsSubscriber(listener));
        mSubscriptionList.add(movieDetailsSubscription);
    }

    /**
     * This interface should be implemented to display the result of a request
     * @param <T>
     */
    public interface RequestListener<T> {
        void onSuccess(T data);
        void onError(String cause);
    }

    public static void clearSubscriptions() {
        if (mSubscriptionList.hasSubscriptions()) {
            Log.d(TAG, "clearSubscriptions()");
            mSubscriptionList.clear();
        }
    }
}
