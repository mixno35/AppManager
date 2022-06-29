package com.mixno.appmanager.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import com.mixno.appmanager.R;
import com.mixno.appmanager.data.Data;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		Preference themeKey = findPreference("themeKey");

		themeKey.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					Data.changeSetting = true;
					getActivity().recreate();
					return false;
				}
			});
    }
}
