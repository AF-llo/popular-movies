package de.lokaizyk.popularmovies.ui.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.lokaizyk.popularmovies.logic.model.MovieModel;

/**
 * Created by lars on 03.10.16.
 */

public class MovieViewHolder extends ViewHolder<MovieModel> {

    public MovieViewHolder(LayoutInflater inflater, ViewGroup container, int layoutId, int itemId) {
        super(inflater, container, layoutId, itemId);
    }

    @Override
    protected void onItemBound(MovieModel item) {

    }
}
