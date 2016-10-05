package de.lokaizyk.popularmovies.persistance.rx;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.persistance.DbManager;
import de.lokaizyk.popularmovies.persistance.model.DbMovieDetails;
import de.lokaizyk.popularmovies.util.ModelHelper;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by lars on 05.10.16.
 */

public class AllFavoriteMovieLoader implements Func0<Observable<List<MovieModel>>> {

    private AllFavoriteMovieLoader() {}

    @Override
    public Observable<List<MovieModel>> call() {
        List<DbMovieDetails> favoriteMovies = DbManager.getInstance().loadAllFavorites();
        List<MovieModel> movieModels = new ArrayList<>();
        for (DbMovieDetails favoriteMovy : favoriteMovies) {
            movieModels.add(ModelHelper.dbMovieDetailsToMovieModel(favoriteMovy));
        }
        return Observable.just(movieModels);
    }

    public static Observable<List<MovieModel>> get() {
        return Observable.defer(new AllFavoriteMovieLoader());
    }
}
