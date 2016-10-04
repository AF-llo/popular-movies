package de.lokaizyk.popularmovies.network.rx;

import java.util.List;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.util.ModelHelper;

/**
 * Created by lars on 03.10.16.
 */

public class MovieReviewsSubscriber extends RequestSubscriber<List<MovieReview>, MovieReviewsResponse> {

    public MovieReviewsSubscriber(MoviesProvider.RequestListener<List<MovieReview>> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieReviewsResponse movieReviewsResponse) {
        if (movieReviewsResponse == null) {
            onError("MovieReviewsResponse was null");
            return;
        }
        onSuccess(ModelHelper.reviewsFromResponse(movieReviewsResponse));
    }
}
