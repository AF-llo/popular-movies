package de.lokaizyk.popularmovies.network;

import android.net.Uri;

import de.lokaizyk.popularmovies.BuildConfig;

/**
 * Created by lars on 14.09.16.
 */
public class RequestHelper {

    public static final String HTTP_PARAM_GET = "GET";

    private static final String PARAM_API_KEY = "api_key";

    public static String getMoviesRequestUrl(String baseMovieUrl) {
        Uri buildUri = Uri.parse(baseMovieUrl).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, BuildConfig.MOVIESDB_API_KEY)
                .build();
        return buildUri.toString();
    }

    public static String getMovieDetailsUrl(String movieId) {
        return "";
    }

}
