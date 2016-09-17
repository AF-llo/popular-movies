package de.lokaizyk.popularmovies.logic.model;

/**
 * Created by lars on 12.09.16.
 */
public class MovieDetails {

    private static final String RATING_SEPERATOR = "/";

    private static final String MAX_RATING = "10";

    private static final String MIN_SUFFIX = "min";

    private String title = "";

    private String overview = "";

    private String imageUrl = "";

    private String votingRate = "";

    private String releaseDate = "";

    private String length = "";

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
}
