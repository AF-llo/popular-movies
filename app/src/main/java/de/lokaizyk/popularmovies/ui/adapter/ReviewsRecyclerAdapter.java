package de.lokaizyk.popularmovies.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.lokaizyk.popularmovies.BR;
import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.ui.viewholder.MovieReviewViewHolder;
import de.lokaizyk.popularmovies.ui.viewholder.ViewHolder;

/**
 * Created by lars on 04.10.16.
 */

public class ReviewsRecyclerAdapter extends BaseBindingRecyclerAdapter<MovieReview> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieReviewViewHolder(LayoutInflater.from(parent.getContext()), parent, R.layout.movie_review_item, BR.review);
    }
}
