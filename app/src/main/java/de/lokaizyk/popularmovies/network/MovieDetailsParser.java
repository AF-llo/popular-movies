package de.lokaizyk.popularmovies.network;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsParser implements JsonParser<MovieDetails> {

    private static final String TAG = MovieDetailsParser.class.getSimpleName();

    @Override
    public MovieDetails parseJson(String json) throws JSONException {
        if (TextUtils.isEmpty(json)) {
            throw new JSONException("json response was empty or null");
        }
        Log.d(TAG, "parseJson() called with: " + "json = [" + json + "]");
        MovieDetails details = new MovieDetails();
        JSONObject detailsObject = new JSONObject(json);

        if (detailsObject.has(JsonParser.KEY_TITLE)) {
            String title = detailsObject.getString(JsonParser.KEY_TITLE);
            if (!TextUtils.isEmpty(title)) {
                details.setTitle(title);
            }
        }

        if (detailsObject.has(JsonParser.KEY_POSTER_PATH)) {
            String imageUrl = detailsObject.getString(JsonParser.KEY_POSTER_PATH);
            if (!TextUtils.isEmpty(imageUrl)) {
                details.setImageUrl(imageUrl);
            }
        }

        if (detailsObject.has(JsonParser.KEY_OVERVIEW)) {
            String overview = detailsObject.getString(JsonParser.KEY_OVERVIEW);
            if (!TextUtils.isEmpty(overview)) {
                details.setOverview(overview);
            }
        }

        if (detailsObject.has(JsonParser.KEY_RELEASE_DATE)) {
            String releaseDate = detailsObject.getString(JsonParser.KEY_RELEASE_DATE);
            if (!TextUtils.isEmpty(releaseDate)) {
                details.setReleaseDate(releaseDate);
            }
        }

        if (detailsObject.has(JsonParser.KEY_VOTING)) {
            double voting = detailsObject.getDouble(JsonParser.KEY_VOTING);
            details.setVotingRate(String.valueOf(voting));
        }

        return details;
    }
}
