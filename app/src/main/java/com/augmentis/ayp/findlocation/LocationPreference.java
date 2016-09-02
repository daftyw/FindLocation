package com.augmentis.ayp.findlocation;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Rawin on 02-Sep-16.
 */
public class LocationPreference {
    public static final String PREF_USE_FUSE = "using_fuse";

    public static Boolean getSharedPref(Context context, String prefKey) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(prefKey, false);
    }
}
