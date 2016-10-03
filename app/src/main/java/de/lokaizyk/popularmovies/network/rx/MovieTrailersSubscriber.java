package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;

/**
 * Created by lars on 03.10.16.
 */

public class MovieTrailersSubscriber extends RequestSubscriber<MovieVideo, MovieVideosResponse> {

    public MovieTrailersSubscriber(MoviesProvider.RequestListener<MovieVideo> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieVideosResponse movieVideosResponse) {
        if (movieVideosResponse == null) {
            onError("MovieVideosResonse was null");
            return;
        }
        // TODO: 03.10.16 parse response to model
        onSuccess(new MovieVideo());
    }
}
