package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMovieDetailsBinding;
import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.ui.activities.MovieDetailsActivity;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsFragment extends BaseBindingFragment<FragmentMovieDetailsBinding> implements MoviesProvider.RequestListener<MovieDetails> {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private static final String EXTRA_ISLOADING = "extraKeyIsLoading";

    private static final String EXTRA_MOVIE_DETAILS = "extraKeyMovieDetails";

    public ObservableBoolean isLoading = new ObservableBoolean(false);
    
    public ObservableField<MovieDetails> movieDetails = new ObservableField<>();

    public static Fragment get(String movieId) {
        Fragment fragment = new MovieDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(MovieDetailsActivity.EXTRAS_MOVIE_ID, movieId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            Log.d(TAG, "retain savedInstanceState");
            isLoading = savedInstanceState.getParcelable(EXTRA_ISLOADING);
            movieDetails = new ObservableField<>((MovieDetails)savedInstanceState.getParcelable(EXTRA_MOVIE_DETAILS));
        }
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
        loadMovieDetails();
    }

    private void loadMovieDetails() {
        if (!isLoading.get() && movieDetails.get() == null) {
            isLoading.set(true);
            MoviesProvider.loadMovieDetails(getArguments().getString(MovieDetailsActivity.EXTRAS_MOVIE_ID), this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_MOVIE_DETAILS, movieDetails.get());
        outState.putParcelable(EXTRA_ISLOADING, isLoading);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MoviesProvider.clearSubscriptions();
    }

    @Override
    public void onSuccess(MovieDetails data) {
        movieDetails.set(data);
        isLoading.set(false);
    }

    @Override
    public void onError(String cause) {
        if (isAdded()) {
            isLoading.set(false);
            Toast.makeText(getContext(), getContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }
}
