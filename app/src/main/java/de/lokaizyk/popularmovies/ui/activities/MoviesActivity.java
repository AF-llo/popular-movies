package de.lokaizyk.popularmovies.ui.activities;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMoviesBinding;

public class MoviesActivity extends BaseBindingActivity<ActivityMoviesBinding> {

    private static final String TAG = MoviesActivity.class.getSimpleName();

    @Override
    protected int getLayoutRessourceId() {
        return R.layout.activity_movies;
    }

    @Override
    protected void onBindingInitialized() {
    }
}
