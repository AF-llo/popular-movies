package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMoviesBinding;
import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.activities.MovieDetailsActivity;
import de.lokaizyk.popularmovies.ui.adapter.BaseBindingAdapter;
import de.lokaizyk.popularmovies.ui.adapter.MovieAdapter;
import de.lokaizyk.popularmovies.util.PrefHelper;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesFragment extends BaseBindingFragment<FragmentMoviesBinding> implements BaseBindingAdapter.OnItemClickListener<MovieModel> {

    public ObservableBoolean isLoading = new ObservableBoolean(true);

    public ObservableArrayList<MovieModel> movies = new ObservableArrayList<>();

    private MoviesProvider.RequestListener<List<MovieModel>> moviesListener = new MoviesProvider.RequestListener<List<MovieModel>>() {
        @Override
        public void onSuccess(List<MovieModel> data) {
            updateMovies(data);
            isLoading.set(false);
        }

        @Override
        public void onError(String cause) {
            // TODO: 12.09.16 implement
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBinding().movieGrid.setAdapter(new MovieAdapter());
    }

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
        MoviesProvider.loadMovies(PrefHelper.getSortingSettings(getContext()), moviesListener);
    }

    @Override
    public void onItemSelected(int position, MovieModel item) {
        MovieDetailsActivity.start(getContext(), item.getMovieId());
    }

    private void updateMovies(List<MovieModel> newMovies) {
        if (newMovies != null) {
            movies.clear();
            movies.addAll(newMovies);
        }
    }
}
