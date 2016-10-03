package de.lokaizyk.popularmovies.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 03.10.16.
 */

public class MoviesResponse {

    @SerializedName("results")
    private List<BackendMovie> results = new ArrayList<BackendMovie>();

    public List<BackendMovie> getResults() {
        return results;
    }

    public void setResults(List<BackendMovie> results) {
        this.results = results;
    }

}
