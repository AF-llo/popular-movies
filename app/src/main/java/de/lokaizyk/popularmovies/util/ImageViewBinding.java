package de.lokaizyk.popularmovies.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.lokaizyk.popularmovies.BuildConfig;
import de.lokaizyk.popularmovies.R;

/**
 * Created by lars on 15.09.16.
 */
public class ImageViewBinding {
    @BindingAdapter("bind:photoPath")
    public static void loadImage(ImageView imageView, String photoPath) {
        String url = BuildConfig.BASE_IMAGE_URL + photoPath;
        Picasso.with(imageView.getContext()).load(url).error(R.drawable.ic_image_failed).into(imageView);
    }
}
