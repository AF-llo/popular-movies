package de.lokaizyk.popularmovies.ui.activities;

import android.support.v4.app.Fragment;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMoviesBinding;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.fragments.MovieDetailsFragment;
import de.lokaizyk.popularmovies.ui.fragments.MoviesFragment;
import de.lokaizyk.popularmovies.util.DeviceSettings;

public class MoviesActivity extends BaseBindingActivity<ActivityMoviesBinding> implements MoviesFragment.Callback {

    private static final String TAG = MoviesActivity.class.getSimpleName();

    @Override
    protected int getLayoutRessourceId() {
        return R.layout.activity_movies;
    }

    @Override
    protected void onBindingInitialized() {

    }

    @Override
    public void onMovieSelected(MovieModel movie) {
        if (DeviceSettings.isTablet(this)) {
            Fragment fragment = MovieDetailsFragment.get(movie.getMovieId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, MovieDetailsFragment.TAG)
                    .commit();
        } else {
            MovieDetailsActivity.start(this, movie.getMovieId());
        }
    }
}
