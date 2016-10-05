package de.lokaizyk.popularmovies.logic.model;

import android.os.Parcel;
import android.os.Parcelable;

import de.lokaizyk.popularmovies.BuildConfig;

/**
 * Created by lars on 12.09.16.
 */
public class MovieModel implements Parcelable {

    protected String imageUrl = "";

    protected String movieId = "";

    protected boolean isFavorite = false;

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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.movieId);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected MovieModel(Parcel in) {
        this.imageUrl = in.readString();
        this.movieId = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
