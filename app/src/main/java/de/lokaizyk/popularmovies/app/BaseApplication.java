package de.lokaizyk.popularmovies.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

import de.lokaizyk.popularmovies.persistance.DbManager;

/**
 * Created by lars on 15.09.16.
 */
public class BaseApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        DbManager.init(getApplicationContext());
        Stetho.initializeWithDefaults(this);
    }

}
