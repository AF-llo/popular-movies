package de.lokaizyk.popularmovies.network.rx;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.network.model.BackendMovie;
import de.lokaizyk.popularmovies.network.model.MoviesResponse;

/**
 * Created by lars on 03.10.16.
 */

public class MoviesSubscriber extends RequestSubscriber<List<MovieModel>, MoviesResponse> {

    public MoviesSubscriber(MoviesProvider.RequestListener<List<MovieModel>> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MoviesResponse moviesResponse) {
        if (moviesResponse == null) {
            onError("MoviesResponse was null");
            return;
        }
        List<MovieModel> movies = new ArrayList<>();
        for (BackendMovie backendMovie : moviesResponse.getResults()) {
            movies.add(new MovieModel(backendMovie.getPosterPath(), String.valueOf(backendMovie.getId())));
        }
        onSuccess(movies);
    }
}
