package de.lokaizyk.popularmovies.util;

import android.databinding.BindingAdapter;
import android.widget.Button;

import de.lokaizyk.popularmovies.R;

/**
 * Created by lars on 04.10.16.
 */

public class ButtonViewBinding {
    @BindingAdapter("bind:favoriteText")
    public static void setFavoriteText(Button button, boolean isFavourite) {
        if (isFavourite) {
            button.setText(button.getContext().getString(R.string.remove_from_favorites));
        } else {
            button.setText(button.getContext().getString(R.string.add_to_favorites));
        }
    }
}
