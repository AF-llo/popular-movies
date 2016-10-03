package de.lokaizyk.popularmovies.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.lokaizyk.popularmovies.BR;
import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.logic.model.MovieModel;
import de.lokaizyk.popularmovies.ui.viewholder.MovieViewHolder;
import de.lokaizyk.popularmovies.ui.viewholder.ViewHolder;

/**
 * Created by lars on 03.10.16.
 */

public class MoviesRecyclerAdapter extends BaseBindingRecyclerAdapter<MovieModel> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()), parent, R.layout.movie_item, BR.movie);
    }
}
