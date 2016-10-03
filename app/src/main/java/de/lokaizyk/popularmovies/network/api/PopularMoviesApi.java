package de.lokaizyk.popularmovies.network.api;

import de.lokaizyk.popularmovies.network.model.MovieDetailsResponse;
import de.lokaizyk.popularmovies.network.model.MovieReviewsResponse;
import de.lokaizyk.popularmovies.network.model.MovieVideosResponse;
import de.lokaizyk.popularmovies.network.model.MoviesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lars on 03.10.16.
 */

public interface PopularMoviesApi {

    String PARAM_API_KEY = "api_key";

    String MOVIES_URL = "3/movie/";

    String PARAM_SORTING = "sorting";

    String PARAM_MOVIE_ID = "movieId";

    String SORTED_MOVIES_URL = MOVIES_URL + "{" + PARAM_SORTING + "}";

    String MOVIE_DETAILS_URL = MOVIES_URL + "{" + PARAM_MOVIE_ID + "}";

    String MOVIE_VIDEOS_URL = MOVIE_DETAILS_URL + "/" + "videos";

    String MOVIE_REVIEWS_URL = MOVIE_DETAILS_URL + "/" + "reviews";

    @GET(SORTED_MOVIES_URL)
    Observable<MoviesResponse> getMoviesObservable(@Path(PARAM_SORTING) String sorting, @Query(PARAM_API_KEY) String apiKey);

    @GET(MOVIE_DETAILS_URL)
    Observable<MovieDetailsResponse> getMovieDetailsObservable(@Path(PARAM_MOVIE_ID) String movieId, @Query(PARAM_API_KEY) String apiKey);

    @GET(MOVIE_VIDEOS_URL)
    Observable<MovieVideosResponse> getMovieVideosObservable(@Path(PARAM_MOVIE_ID) String movieId, @Query(PARAM_API_KEY) String apiKey);

    @GET(MOVIE_REVIEWS_URL)
    Observable<MovieReviewsResponse> getMovieReviewsObservable(@Path(PARAM_MOVIE_ID) String movieId, @Query(PARAM_API_KEY) String apiKey);

}
