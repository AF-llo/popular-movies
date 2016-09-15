package de.lokaizyk.popularmovies.network;

import org.json.JSONException;

/**
 * Created by lars on 14.09.16.
 */
public interface JsonParser<T> {

    String KEY_RESULTS = "results";

    String KEY_ID = "id";

    String KEY_POSTER_PATH = "poster_path";

    String KEY_TITLE = "title";

    T parseJson(String json) throws JSONException;
}
