package de.lokaizyk.popularmovies.persistance.rx;

import android.text.TextUtils;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.persistance.DbManager;
import de.lokaizyk.popularmovies.persistance.model.DbMovieDetails;
import de.lokaizyk.popularmovies.util.ModelHelper;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by lars on 05.10.16.
 */

public class FavoriteMovieDetailsLoader implements Func0<Observable<MovieDetails>> {

    private String id = "";

    private FavoriteMovieDetailsLoader(String id) {
        this.id = id;
    }

    @Override
    public Observable<MovieDetails> call() {
        if (TextUtils.isEmpty(id)) {
            return Observable.error(new IllegalArgumentException("Cannot load movie from DB becaus movieId was null or empty!"));
        }
        DbMovieDetails movieDetails = DbManager.getInstance().loadFavoriteMovie(id);
        return Observable.just(ModelHelper.dbMovieDetailsToMovieDetails(movieDetails));
    }

    public static Observable<MovieDetails> get(String id) {
        return Observable.defer(new FavoriteMovieDetailsLoader(id));
    }

}
