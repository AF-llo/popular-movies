package de.lokaizyk.popularmovies.logic.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 12.09.16.
 */
public class MovieDetails implements Parcelable {

    private static final String RATING_SEPERATOR = "/";

    private static final String MAX_RATING = "10";

    private static final String MIN_SUFFIX = "min";

    private String title = "";

    private String overview = "";

    private String imageUrl = "";

    private String votingRate = "";

    private String releaseDate = "";

    private String length = "";

    private boolean isFavorite = false;

    private List<MovieVideo> trailers = new ArrayList<>();

    private List<MovieReview> reviews = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVotingRate() {
        return votingRate + RATING_SEPERATOR + MAX_RATING;
    }

    public void setVotingRate(String votingRate) {
        this.votingRate = votingRate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLength() {
        return length + MIN_SUFFIX;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public List<MovieVideo> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<MovieVideo> trailers) {
        if (trailers != null) {
            this.trailers = trailers;
        }
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReview> reviews) {
        if (reviews != null) {
            this.reviews = reviews;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.imageUrl);
        dest.writeString(this.votingRate);
        dest.writeString(this.releaseDate);
        dest.writeString(this.length);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.trailers);
        dest.writeTypedList(this.reviews);
    }

    public MovieDetails() {
    }

    protected MovieDetails(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.imageUrl = in.readString();
        this.votingRate = in.readString();
        this.releaseDate = in.readString();
        this.length = in.readString();
        this.isFavorite = in.readByte() != 0;
        this.trailers = in.createTypedArrayList(MovieVideo.CREATOR);
        this.reviews = in.createTypedArrayList(MovieReview.CREATOR);
    }

    public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel source) {
            return new MovieDetails(source);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
}
