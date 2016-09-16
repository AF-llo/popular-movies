package de.lokaizyk.popularmovies.logic.model;

/**
 * Created by lars on 12.09.16.
 */
public class MovieDetails {

    private String title = "";

    private String overview = "";

    private String imageUrl = "";

    private String votingRate = "";

    private String releaseDate = "";

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
        return votingRate;
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
}
