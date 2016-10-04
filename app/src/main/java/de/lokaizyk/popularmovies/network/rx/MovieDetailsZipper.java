package de.lokaizyk.popularmovies.network.rx;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;
import de.lokaizyk.popularmovies.util.ModelHelper;
import rx.functions.Func3;

/**
 * Created by lars on 04.10.16.
 */

public class MovieDetailsZipper implements Func3<MovieDetailsResponse, MovieVideosResponse, MovieReviewsResponse, MovieDetails> {
    @Override
    public MovieDetails call(MovieDetailsResponse movieDetailsResponse, MovieVideosResponse movieVideosResponse, MovieReviewsResponse movieReviewsResponse) {
        if (movieDetailsResponse == null) {
            throw new IllegalArgumentException("MovieDetailsResponse was null");
        }
        MovieDetails movieDetails = ModelHelper.detailsResponseAsMovieDetails(movieDetailsResponse);
        if (movieVideosResponse != null) {
            movieDetails.setTrailers(ModelHelper.videosFromResponse(movieVideosResponse));
        }
        if (movieReviewsResponse != null) {
            movieDetails.setReviews(ModelHelper.reviewsFromResponse(movieReviewsResponse));
        }
        return movieDetails;
    }
}
