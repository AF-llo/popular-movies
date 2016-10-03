package de.lokaizyk.popularmovies.network.api;

import de.lokaizyk.popularmovies.network.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lars on 03.10.16.
 */

public interface PopularMoviesApi {

    String PARAM_API_KEY = "api_key";

    String MOVIES_URL = "3/movie/";

    String PARAM_SORTING = "sorting";

    String URL_MOVIES_SORTING = MOVIES_URL + "{" + PARAM_SORTING + "}";

    @GET(URL_MOVIES_SORTING)
    public Call<MoviesResponse> loadMovies(@Path(PARAM_SORTING) String sorting, @Query(PARAM_API_KEY) String apiKey);

}
