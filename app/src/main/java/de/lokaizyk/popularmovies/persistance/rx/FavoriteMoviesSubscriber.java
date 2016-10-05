package de.lokaizyk.popularmovies.persistance.rx;

import java.util.List;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.network.rx.RequestSubscriber;

/**
 * Created by lars on 05.10.16.
 */

public class FavoriteMoviesSubscriber extends RequestSubscriber<List<MovieModel>, List<MovieModel>> {
    public FavoriteMoviesSubscriber(MoviesProvider.RequestListener<List<MovieModel>> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(List<MovieModel> movieModels) {
        onSuccess(movieModels);
    }
}
