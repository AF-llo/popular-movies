package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMoviesBinding;
import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.adapter.BaseBindingRecyclerAdapter;
import de.lokaizyk.popularmovies.ui.adapter.MoviesRecyclerAdapter;
import de.lokaizyk.popularmovies.util.DeviceSettings;
import de.lokaizyk.popularmovies.util.PrefHelper;

/**
 * Created by lars on 12.09.16.
 */
public class MoviesFragment extends BaseBindingFragment<FragmentMoviesBinding> implements BaseBindingRecyclerAdapter.OnItemClickListener<MovieModel> {

    private static final String TAG = MoviesFragment.class.getSimpleName();

    private static final String EXTRA_MOVIES = "extraKeyMovies";

    private static final String EXTRA_ISLOADING = "extraKeyLoading";

    private static final String EXTRA_SORTING_CHANGED = "extraSortingChanged";

    public ObservableBoolean isLoading = new ObservableBoolean(false);

    public ObservableArrayList<MovieModel> movies = new ObservableArrayList<>();

    private boolean sortingChanged = true;

    private MoviesProvider.RequestListener<List<MovieModel>> moviesListener = new MoviesProvider.RequestListener<List<MovieModel>>() {
        @Override
        public void onSuccess(List<MovieModel> data) {
            updateMovies(data);
        }

        @Override
        public void onError(String cause) {
            Log.e(TAG, cause);
            if (isAdded()) {
                isLoading.set(false);
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getBinding().movieList;
        BaseBindingRecyclerAdapter adapter = new MoviesRecyclerAdapter();
        int columnCount = DeviceSettings.getColumnCount(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        recyclerView.setAdapter(adapter);
        if (savedInstanceState != null) {
            Log.d(TAG, "retain savedInstanceState");
            movies = (ObservableArrayList) savedInstanceState.getParcelableArrayList(EXTRA_MOVIES);
            isLoading = savedInstanceState.getParcelable(EXTRA_ISLOADING);
            sortingChanged = savedInstanceState.getBoolean(EXTRA_SORTING_CHANGED);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sortingChanged | PrefHelper.getSortingSettings(getContext()).equals(getString(R.string.pref_value_sorting_favorite))) {
            refreshMovies();
            sortingChanged = false;
        }
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_MOVIES, movies);
        outState.putParcelable(EXTRA_ISLOADING, isLoading);
        outState.putBoolean(EXTRA_SORTING_CHANGED, sortingChanged);
    }

    public void refreshMovies() {
        if (!isLoading.get()) {
            isLoading.set(true);
            String sorting = PrefHelper.getSortingSettings(getContext());
            if (sorting.equals(getString(R.string.pref_value_sorting_favorite))) {
                MoviesProvider.loadFavoriteMovies(moviesListener);
            } else {
                MoviesProvider.loadMovies(sorting, moviesListener);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String sorting = PrefHelper.getSortingSettings(getContext());
        if (sorting.equals(getString(R.string.pref_value_sorting_favorite))) {
            refreshMovies();
        }
    }

    public void onSortingChanged() {
        sortingChanged = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MoviesProvider.clearSubscriptions();
    }

    @Override
    public void onItemClicked(MovieModel item, int position) {
        ((Callback)getActivity()).onMovieSelected(item);
    }

    private void updateMovies(List<MovieModel> newMovies) {
        if (isAdded()) {
            isLoading.set(false);
            if (newMovies != null) {
                movies.clear();
                movies.addAll(newMovies);
            }
        }
    }

    public interface Callback {
        void onMovieSelected(MovieModel movie);
    }
}
