package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.util.ModelHelper;

/**
 * Created by lars on 03.10.16.
 */

public class MovieDetailsSubscriber extends RequestSubscriber<MovieDetails, MovieDetailsResponse> {

    public MovieDetailsSubscriber(MoviesProvider.RequestListener<MovieDetails> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieDetailsResponse movieDetailsResponse) {
        if (movieDetailsResponse == null) {
            onError("MovieDetailsResponse was null");
            return;
        }
        onSuccess(ModelHelper.detailsResponseAsMovieDetails(movieDetailsResponse));
    }
}
