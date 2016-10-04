package de.lokaizyk.popularmovies.util;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.network.model.MovieReviewResult;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.network.model.MovieVideoResult;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;

/**
 * Created by lars on 04.10.16.
 */

public class ModelHelper {
    public static MovieDetails detailsResponseAsMovieDetails(MovieDetailsResponse movieDetailsResponse) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setImageUrl(movieDetailsResponse.getPosterPath());
        movieDetails.setLength(String.valueOf(movieDetailsResponse.getRuntime()));
        movieDetails.setOverview(movieDetailsResponse.getOverview());
        movieDetails.setReleaseDate(movieDetailsResponse.getReleaseDate());
        movieDetails.setTitle(movieDetailsResponse.getTitle());
        movieDetails.setVotingRate(String.valueOf(movieDetailsResponse.getVoteAverage()));
        return movieDetails;
    }

    public static List<MovieVideo> videosFromResponse(MovieVideosResponse movieVideosResponse) {
        List<MovieVideo> trailers = new ArrayList<>();
        for (MovieVideoResult movieVideoResult : movieVideosResponse.getResults()) {
            MovieVideo movieVideo = null;
            if (movieVideoResult.getId() != null && movieVideoResult.getKey() != null) {
                movieVideo = new MovieVideo(movieVideoResult.getId(), movieVideoResult.getKey());
            }
            if (movieVideo != null) {
                movieVideo.setTitle(movieVideoResult.getName());
                trailers.add(movieVideo);
            }
        }
        return trailers;
    }

    public static List<MovieReview> reviewsFromResponse(MovieReviewsResponse movieReviewsResponse) {
        List<MovieReview> reviews = new ArrayList<>();
        for (MovieReviewResult movieReviewResult : movieReviewsResponse.getResults()) {
            MovieReview review = new MovieReview(movieReviewResult.getAuthor(), movieReviewResult.getContent());
            reviews.add(review);
        }
        return reviews;
    }
}
