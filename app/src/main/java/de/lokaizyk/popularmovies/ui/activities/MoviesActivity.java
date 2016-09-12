package de.lokaizyk.popularmovies.ui.activities;

import android.view.Menu;
import android.view.MenuItem;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMoviesBinding;

public class MoviesActivity extends BaseBindingActivity<ActivityMoviesBinding> {

    @Override
    protected int getLayoutRessourceId() {
        return R.layout.activity_movies;
    }

    @Override
    protected void onBindingInitialized() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
