package de.lokaizyk.popularmovies.network;

import org.json.JSONException;

/**
 * Created by lars on 14.09.16.
 */
public interface JsonParser<T> {

    // movies list

    String KEY_RESULTS = "results";

    String KEY_ID = "id";

    String KEY_POSTER_PATH = "poster_path";

    String KEY_TITLE = "title";

    String KEY_OVERVIEW = "overview";

    String KEY_VOTING = "vote_average";

    String KEY_RELEASE_DATE = "release_date";

    String KEY_RUNTIME = "runtime";

    T parseJson(String json) throws JSONException;
}
