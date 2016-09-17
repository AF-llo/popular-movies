package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMovieDetailsBinding;
import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.ui.activities.MovieDetailsActivity;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsFragment extends BaseBindingFragment<FragmentMovieDetailsBinding> implements MoviesProvider.RequestListener<MovieDetails> {

    public ObservableBoolean isLoading = new ObservableBoolean(true);
    
    public ObservableField<MovieDetails> movieDetails = new ObservableField<>();

    public static Fragment get(String movieId) {
        Fragment fragment = new MovieDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(MovieDetailsActivity.EXTRAS_MOVIE_ID, movieId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_details;
    }

    @Override
    protected void onBindingInitialized() {
        getBinding().setMovieDetailsFragment(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (movieDetails.get() == null) {
            MoviesProvider.loadMovieDetails(getArguments().getString(MovieDetailsActivity.EXTRAS_MOVIE_ID), this);
        }
    }

    @Override
    public void onSuccess(MovieDetails data) {
        movieDetails.set(data);
        isLoading.set(false);
    }

    @Override
    public void onError(String cause) {
        // TODO: 15.09.16
    }
}
