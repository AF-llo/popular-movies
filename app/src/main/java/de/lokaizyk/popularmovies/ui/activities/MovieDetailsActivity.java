package de.lokaizyk.popularmovies.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMovieDetailsBinding;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.fragments.MovieDetailsFragment;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsActivity extends BaseBindingActivity<ActivityMovieDetailsBinding> {

    public static final String EXTRAS_MOVIE = "extrasMovie";

    public static void start(Context context, MovieModel movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRAS_MOVIE, movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(MovieDetailsFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = MovieDetailsFragment.get(getIntent().getParcelableExtra(EXTRAS_MOVIE));
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, MovieDetailsFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutRessourceId() {
        return R.layout.activity_movie_details;
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
