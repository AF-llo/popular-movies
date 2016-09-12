package de.lokaizyk.popularmovies.logic.model;

import de.lokaizyk.popularmovies.BuildConfig;

/**
 * Created by lars on 12.09.16.
 */
public class MovieModel {

    private String imageUrl = "";

    private String movieId = "";

    public MovieModel(String imagePath, String movieId) {
        setImageUrl(imagePath);
        this.movieId = movieId;
    }

    public void setImageUrl(String imagePath) {
        this.imageUrl = BuildConfig.BASE_IMAGE_URL + imagePath;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMovieId() {
        return movieId;
    }
}
