package example.android.app.com.popularmovies;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.pref_general, false);
    }
}
