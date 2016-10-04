package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;

/**
 * Created by lars on 04.10.16.
 */

public class CombinedMovieDetailsSubscriber extends RequestSubscriber<MovieDetails, MovieDetails> {

    public CombinedMovieDetailsSubscriber(MoviesProvider.RequestListener<MovieDetails> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieDetails movieDetails) {
        // TODO: 04.10.16 maybe check some error
        onSuccess(movieDetails);
    }
}
