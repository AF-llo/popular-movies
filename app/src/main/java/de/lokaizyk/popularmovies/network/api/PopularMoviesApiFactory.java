package de.lokaizyk.popularmovies.network.api;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.network.ServiceFactory;

/**
 * Created by lars on 03.10.16.
 */

public class PopularMoviesApiFactory extends ServiceFactory<PopularMoviesApi> {

    private static PopularMoviesApiFactory mInstance;

    private PopularMoviesApiFactory() {
        super(PopularMoviesApi.class, BuildConfig.BASE_MOVIE_URL);
    }

    public static synchronized PopularMoviesApiFactory getInstance() {
        if (mInstance == null) {
            mInstance = new PopularMoviesApiFactory();
        }
        return mInstance;
    }
}
