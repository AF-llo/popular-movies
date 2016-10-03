package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;

/**
 * Created by lars on 03.10.16.
 */

public class MovieReviewsSubscriber extends RequestSubscriber<MovieReview, MovieReviewsResponse> {

    public MovieReviewsSubscriber(MoviesProvider.RequestListener<MovieReview> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieReviewsResponse movieReviewsResponse) {
        if (movieReviewsResponse == null) {
            onError("MovieReviewsResponse was null");
            return;
        }
        // TODO: 03.10.16 parse response to model
        onSuccess(new MovieReview());
    }
}
