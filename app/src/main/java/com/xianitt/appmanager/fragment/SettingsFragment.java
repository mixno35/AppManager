package com.xianitt.appmanager.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import com.xianitt.appmanager.R;
import com.xianitt.appmanager.data.Data;

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
