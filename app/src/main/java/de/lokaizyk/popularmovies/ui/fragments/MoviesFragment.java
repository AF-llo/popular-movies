package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableBoolean;
import android.os.Handler;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMoviesBinding;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesFragment extends BaseBindingFragment<FragmentMoviesBinding> {

    public ObservableBoolean isLoading = new ObservableBoolean(true);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void onBindingInitialized() {
        getBinding().setMoviesFragment(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoading.set(false);
            }
        }, 1000);
    }
}
