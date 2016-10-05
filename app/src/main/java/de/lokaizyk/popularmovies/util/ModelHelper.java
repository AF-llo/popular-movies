package de.lokaizyk.popularmovies.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.network.model.MovieReviewResult;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.network.model.MovieVideoResult;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;
import de.lokaizyk.popularmovies.persistance.model.DbMovieDetails;
import de.lokaizyk.popularmovies.persistance.model.DbMovieReview;
import de.lokaizyk.popularmovies.persistance.model.DbMovieVideo;

/**
 * Created by lars on 04.10.16.
 */

public class ModelHelper {

    // API to view models

    public static MovieDetails detailsResponseAsMovieDetails(MovieDetailsResponse movieDetailsResponse) {
        MovieDetails movieDetails = new MovieDetails(movieDetailsResponse.getPosterPath(), String.valueOf(movieDetailsResponse.getId()));
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
            if (!TextUtils.isEmpty(movieReviewResult.getId())) {
                MovieReview review = new MovieReview(movieReviewResult.getId());
                review.setAuthor(movieReviewResult.getAuthor());
                review.setContent(movieReviewResult.getContent());
                reviews.add(review);
            }
        }
        return reviews;
    }

    // view models to DB

    public static DbMovieDetails movieDetailsAsDbModel(MovieDetails movieDetails) {
        DbMovieDetails details = new DbMovieDetails();
        details.setId(movieDetails.getMovieId());
        details.setTitle(movieDetails.getTitle());
        details.setImageUrl(movieDetails.getImageUrl());
        details.setOverview(movieDetails.getOverview());
        details.setReleaseDate(movieDetails.getReleaseDate());
        details.setRuntime(Integer.valueOf(movieDetails.getLength()));
        details.setVoting(movieDetails.getVotingRate());
        return details;
    }

    public static DbMovieVideo movieVideoToDbModel(String movieId, MovieVideo movieVideo) {
        DbMovieVideo video = new DbMovieVideo();
        video.setId(movieVideo.getId());
        video.setMovieId(movieId);
        video.setKey(movieVideo.getKey());
        video.setTitle(movieVideo.getTitle());
        return video;
    }

    public static DbMovieReview movieReviewToDbModel(String movieId, MovieReview movieReview) {
        DbMovieReview review = new DbMovieReview();
        review.setId(movieReview.getId());
        review.setMovieId(movieId);
        review.setAuthor(movieReview.getAuthor());
        review.setContent(movieReview.getContent());
        return review;
    }

    // DB models to view

    public static MovieModel dbMovieDetailsToMovieModel(DbMovieDetails movieDetails) {
        MovieModel movieModel = new MovieModel(movieDetails.getImageUrl(), movieDetails.getId());
        movieModel.toggleFavorite();
        return movieModel;
    }

    public static MovieDetails dbMovieDetailsToMovieDetails(DbMovieDetails movieDetails) {
        MovieDetails details = new MovieDetails(movieDetails.getImageUrl(), movieDetails.getId());
        details.setTitle(movieDetails.getTitle());
        details.setVotingRate(movieDetails.getVoting());
        details.setReleaseDate(movieDetails.getReleaseDate());
        details.setLength(String.valueOf(movieDetails.getRuntime()));
        details.setOverview(movieDetails.getOverview());
        details.toggleFavorite();
        List<MovieReview> reviews = new ArrayList<>();
        for (DbMovieReview review : movieDetails.getReviews()) {
            reviews.add(dbMovieReviewToMovieReview(review));
        }
        details.setReviews(reviews);
        List<MovieVideo> videos = new ArrayList<>();
        for (DbMovieVideo dbMovieVideo : movieDetails.getMovies()) {
            videos.add(dbMovieVideoToMovieVideo(dbMovieVideo));
        }
        details.setTrailers(videos);
        return details;
    }

    public static MovieReview dbMovieReviewToMovieReview(DbMovieReview movieReview) {
        MovieReview review = new MovieReview(movieReview.getId());
        review.setAuthor(movieReview.getAuthor());
        review.setContent(movieReview.getContent());
        return review;
    }

    public static MovieVideo dbMovieVideoToMovieVideo(DbMovieVideo movieVideo) {
        MovieVideo video = new MovieVideo(movieVideo.getId(), movieVideo.getKey());
        video.setTitle(movieVideo.getTitle());
        return video;
    }
}
