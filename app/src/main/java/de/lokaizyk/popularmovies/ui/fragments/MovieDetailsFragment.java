package de.lokaizyk.popularmovies.ui.fragments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.databinding.FragmentMovieDetailsBinding;
import de.lokaizyk.popularmovies.logic.MoviesProvider;
import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.ui.activities.MovieDetailsActivity;
import de.lokaizyk.popularmovies.ui.adapter.BaseBindingRecyclerAdapter;
import de.lokaizyk.popularmovies.ui.adapter.ReviewsRecyclerAdapter;
import de.lokaizyk.popularmovies.ui.adapter.TrailersRecyclerAdapter;

/**
 * Created by lars on 15.09.16.
 */
public class MovieDetailsFragment extends BaseBindingFragment<FragmentMovieDetailsBinding> implements MoviesProvider.RequestListener<MovieDetails>,
        BaseBindingRecyclerAdapter.OnItemClickListener<MovieVideo> {

    public static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private static final String EXTRA_ISLOADING = "extraKeyIsLoading";

    private static final String EXTRA_MOVIE_DETAILS = "extraKeyMovieDetails";

    public ObservableBoolean isLoading = new ObservableBoolean(false);
    
    public ObservableField<MovieDetails> movieDetails = new ObservableField<>();

    public static Fragment get(String movieId) {
        Fragment fragment = new MovieDetailsFragment();
        if (!TextUtils.isEmpty(movieId)) {
            Bundle arguments = new Bundle();
            arguments.putString(MovieDetailsActivity.EXTRAS_MOVIE_ID, movieId);
            fragment.setArguments(arguments);
        }
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
            movieDetails = new ObservableField<>(savedInstanceState.getParcelable(EXTRA_MOVIE_DETAILS));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_details;
    }

    @Override
    protected void onBindingInitialized() {
        getBinding().setMovieDetailsFragment(this);
        getBinding().trailerList.setNestedScrollingEnabled(false);
        getBinding().reviewList.setNestedScrollingEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadMovieDetails();
    }

    private void loadMovieDetails() {
        if (!isLoading.get() && movieDetails.get() == null) {
            isLoading.set(true);
            MoviesProvider.loadFullMovieDetails(getArguments().getString(MovieDetailsActivity.EXTRAS_MOVIE_ID), this);
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

    public void toggleFavorite(View view) {
        if (movieDetails.get().isFavorite()) {
            Toast.makeText(getContext(), "Remove from DB", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Write to DB", Toast.LENGTH_SHORT).show();
        }
        movieDetails.get().toggleFavorite();
        movieDetails.notifyChange();
    }

    @Override
    public void onItemClicked(MovieVideo item, int position) {
        Toast.makeText(getContext(), "Show video with key " + item.getKey(), Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(MovieDetails data) {
        movieDetails.set(data);
        isLoading.set(false);
        RecyclerView videoList = getBinding().trailerList;
        BaseBindingRecyclerAdapter recyclerAdapter = new TrailersRecyclerAdapter();
        ObservableArrayList<MovieVideo> videos = new ObservableArrayList<>();
        videos.addAll(data.getTrailers());
        recyclerAdapter.setItems(videos);
        videoList.setLayoutManager(new LinearLayoutManager(getContext()));
        videoList.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(this);

        RecyclerView reviewList = getBinding().reviewList;
        BaseBindingRecyclerAdapter reviewAdapter = new ReviewsRecyclerAdapter();
        ObservableArrayList<MovieReview> reviews = new ObservableArrayList<>();
        reviews.addAll(data.getReviews());
        reviewAdapter.setItems(reviews);
        reviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewList.setAdapter(reviewAdapter);
    }

    @Override
    public void onError(String cause) {
        if (isAdded()) {
            isLoading.set(false);
            Toast.makeText(getContext(), getContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }
}
