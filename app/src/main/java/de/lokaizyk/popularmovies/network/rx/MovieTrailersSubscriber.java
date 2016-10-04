package de.lokaizyk.popularmovies.network.rx;

import java.util.List;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;
import de.lokaizyk.popularmovies.util.ModelHelper;

/**
 * Created by lars on 03.10.16.
 */

public class MovieTrailersSubscriber extends RequestSubscriber<List<MovieVideo>, MovieVideosResponse> {

    public MovieTrailersSubscriber(MoviesProvider.RequestListener<List<MovieVideo>> requestListener) {
        super(requestListener);
    }

    @Override
    public void onNext(MovieVideosResponse movieVideosResponse) {
        if (movieVideosResponse == null) {
            onError("MovieVideosResonse was null");
            return;
        }
        onSuccess(ModelHelper.videosFromResponse(movieVideosResponse));
    }
}
