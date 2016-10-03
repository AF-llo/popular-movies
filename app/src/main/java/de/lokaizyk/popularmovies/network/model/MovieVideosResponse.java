package de.lokaizyk.popularmovies.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 03.10.16.
 */

public class MovieVideosResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("results")
    private List<MovieVideoResult> results = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieVideoResult> getResults() {
        return results;
    }

    public void setResults(List<MovieVideoResult> results) {
        this.results = results;
    }

}
