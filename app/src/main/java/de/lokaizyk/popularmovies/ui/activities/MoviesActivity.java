package de.lokaizyk.popularmovies.ui.activities;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMoviesBinding;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.fragments.MovieDetailsFragment;
import de.lokaizyk.popularmovies.ui.fragments.MoviesFragment;
import de.lokaizyk.popularmovies.util.DeviceSettings;
import de.lokaizyk.popularmovies.util.PrefHelper;

public class MoviesActivity extends BaseBindingActivity<ActivityMoviesBinding> implements MoviesFragment.Callback {

    private static final String TAG = MoviesActivity.class.getSimpleName();

    private String sorting = "";

    @Override
    protected int getLayoutRessourceId() {
        return R.layout.activity_movies;
    }

    @Override
    protected void onBindingInitialized() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                MoviesFragment fragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.movies_fragment);
                fragment.refreshMovies();
                return true;
            case R.id.action_settings:
                SettingsActivity.start(this);
                return true;
            case R.id.action_favourite:
                showFavouriteMovies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currentSorting = PrefHelper.getSortingSettings(this);
        if (!this.sorting.equals(currentSorting)) {
            MoviesFragment fragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.movies_fragment);
            fragment.onSortingChanged();
            sorting = currentSorting;
        }
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

    @Override
    public void showFavouriteMovies() {
        Toast.makeText(this, "show favourite movies", Toast.LENGTH_SHORT).show();
    }
}
