package de.lokaizyk.popularmovies.network;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.lokaizyk.popularmovies.logic.model.MovieModel;

/**
 * Created by lars on 14.09.16.
 */
public class MoviesParser implements JsonParser<List<MovieModel>> {

    private static final String TAG = MoviesParser.class.getSimpleName();

    @Override
    public List<MovieModel> parseJson(String json) throws JSONException {
        if (TextUtils.isEmpty(json)) {
            throw new JSONException("json response was empty or null");
        }
        Log.d(TAG, "parseJson() called with: " + "json = [" + json + "]");
        JSONObject responseObject = new JSONObject(json);
        if (!responseObject.has(JsonParser.KEY_RESULTS)) {
            throw new JSONException(JsonParser.KEY_RESULTS + " not defined in " + json);
        }
        List<MovieModel> movies = new ArrayList<>();
        JSONArray movieArray = responseObject.getJSONArray(JsonParser.KEY_RESULTS);
        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            if (!movieObject.has(JsonParser.KEY_ID)) {
                throw new JSONException(JsonParser.KEY_ID + " not defined in " + json);
            }
            MovieModel movieModel = new MovieModel(
                    movieObject.getString(JsonParser.KEY_POSTER_PATH),
                    String.valueOf(movieObject.getLong(JsonParser.KEY_ID)));
            movies.add(movieModel);
        }
        return movies;
    }
}
