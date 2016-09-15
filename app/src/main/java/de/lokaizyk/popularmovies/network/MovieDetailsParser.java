package de.lokaizyk.popularmovies.network;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;

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
        details.setName("blablabla");
        return details;
    }
}
