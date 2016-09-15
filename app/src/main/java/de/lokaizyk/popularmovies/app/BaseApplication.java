package de.lokaizyk.popularmovies.app;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

import de.lokaizyk.popularmovies.R;

/**
 * Created by lars on 15.09.16.
 */
public class BaseApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }

}
