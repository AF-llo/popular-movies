package de.lokaizyk.popularmovies.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import de.lokaizyk.popularmovies.R;

/**
 * Created by lars on 04.10.16.
 */

public class ButtonViewBinding {
    @BindingAdapter("bind:favoriteIcon")
    public static void setFavoriteIcon(ImageView imageView, boolean isFavourite) {
        if (isFavourite) {
            imageView.setImageResource(R.drawable.ic_favorite);
        } else {
            imageView.setImageResource(R.drawable.ic_no_favorite);
        }
    }
}
