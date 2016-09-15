package de.lokaizyk.popularmovies.util;

import android.content.Context;
import android.preference.PreferenceManager;

import de.lokaizyk.popularmovies.R;

/**
 * Created by lars on 14.09.16.
 */
public class PrefHelper {

    public static String getSortingSettings(Context context) {
        if (context != null) {
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(context.getString(R.string.pref_key_sorting), context.getString(R.string.pref_value_sorting_default));
        }
        return "";
    }

}
