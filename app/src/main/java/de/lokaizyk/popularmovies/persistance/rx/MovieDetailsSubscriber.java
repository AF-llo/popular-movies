package de.lokaizyk.popularmovies.persistance.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.rx.RequestSubscriber;

/**
 * Created by lars on 05.10.16.
 */

public class MovieDetailsSubscriber extends RequestSubscriber<MovieDetails, MovieDetails> {
    public MovieDetailsSubscriber(MoviesProvider.RequestListener<MovieDetails> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieDetails movieDetails) {
        onSuccess(movieDetails);
    }
}
