package de.lokaizyk.popularmovies.ui.adapter;

import de.lokaizyk.popularmovies.BR;
import de.lokaizyk.popularmovies.R;
import de.lokaizyk.popularmovies.logic.model.MovieModel;

/**
 * Created by lars on 12.09.16.
 */
public class MovieAdapter extends BaseBindingAdapter<MovieModel> {
    public MovieAdapter() {
        super(R.layout.movie_item, BR.movie);
    }
}
