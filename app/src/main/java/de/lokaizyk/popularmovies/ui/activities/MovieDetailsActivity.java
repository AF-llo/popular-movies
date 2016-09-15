package de.lokaizyk.popularmovies.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.ActivityMovieDetailsBinding;
import de.lokaizyk.popularmovies.ui.fragments.MovieDetailsFragment;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsActivity extends BaseBindingActivity<ActivityMovieDetailsBinding> {

    public static final String EXTRAS_MOVIE_ID = "extrasMovieId";

    public static void start(Context context, String movieId) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRAS_MOVIE_ID, movieId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MovieDetailsFragment.get(getIntent().getExtras().getString(EXTRAS_MOVIE_ID)))
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
