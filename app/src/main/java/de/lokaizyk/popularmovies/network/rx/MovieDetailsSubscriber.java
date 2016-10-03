package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;

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
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setImageUrl(movieDetailsResponse.getPosterPath());
        movieDetails.setLength(String.valueOf(movieDetailsResponse.getRuntime()));
        movieDetails.setOverview(movieDetailsResponse.getOverview());
        movieDetails.setReleaseDate(movieDetailsResponse.getReleaseDate());
        movieDetails.setTitle(movieDetailsResponse.getTitle());
        movieDetails.setVotingRate(String.valueOf(movieDetailsResponse.getVoteAverage()));
        onSuccess(movieDetails);
    }
}
