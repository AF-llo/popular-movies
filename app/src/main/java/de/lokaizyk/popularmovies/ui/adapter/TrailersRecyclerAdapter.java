package de.lokaizyk.popularmovies.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.lokaizyk.popularmovies.BR;
import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.ui.viewholder.MovieVideoViewHolder;
import de.lokaizyk.popularmovies.ui.viewholder.ViewHolder;

/**
 * Created by lars on 04.10.16.
 */

public class TrailersRecyclerAdapter extends BaseBindingRecyclerAdapter<MovieVideo> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieVideoViewHolder(LayoutInflater.from(parent.getContext()), parent, R.layout.movie_video_item, BR.trailer);
    }
}
