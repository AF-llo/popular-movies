package de.lokaizyk.popularmovies.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 03.10.16.
 */

public class MoviesResponse {

    @SerializedName("results")
    private List<MovieResult> results = new ArrayList<MovieResult>();

    public List<MovieResult> getResults() {
        return results;
    }

    public void setResults(List<MovieResult> results) {
        this.results = results;
    }

}
