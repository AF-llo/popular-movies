package de.lokaizyk.popularmovies.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.lokaizyk.popularmovies.BuildConfig;

/**
 * Created by lars on 15.09.16.
 */
public class ImageViewBinding {
    @BindingAdapter("bind:photoPath")
    public static void loadImage(ImageView imageView, String photoPath) {
        // TODO: 15.09.16 show placeholder and error
        String url = BuildConfig.BASE_IMAGE_URL + photoPath;
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
