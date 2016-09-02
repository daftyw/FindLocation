package com.augmentis.ayp.findlocation;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.MenuItem;

/**
 * Created by Rawin on 02-Sep-16.
 */
public class LocationSettingFragment extends PreferenceFragment {

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener
            sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } if(preference instanceof SwitchPreference) {
                SwitchPreference switchPreference = (SwitchPreference) preference;

                if(preference.getKey().equals(LocationPreference.PREF_USE_FUSE)) {
                    preference.setSummary((boolean) value ?
                            R.string.pref_use_fuse_api_use : R.string.pref_use_fuse_api_not_use);
                }

            } else {
                preference.setSummary(stringValue);
            }

            return true;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_location);
        setHasOptionsMenu(true);

        bindPreferenceSummaryToBoolean(findPreference(LocationPreference.PREF_USE_FUSE));
    }

    private static void bindPreferenceSummaryToBoolean(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getBoolean(preference.getKey(), true));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
