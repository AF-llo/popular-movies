package de.lokaizyk.popularmovies.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import de.lokaizyk.popularmovies.ui.fragments.SettingsFragment;

/**
 * Created by lars on 17.09.16.
 */
public class SettingsActivity extends PreferenceActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, SettingsFragment.get())
                .commit();
    }
}
