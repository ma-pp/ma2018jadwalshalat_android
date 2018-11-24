package com.achmad.achmad.jadwalshalatkita.activities.preferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.achmad.achmad.jadwalshalatkita.R;
import com.achmad.achmad.jadwalshalatkita.fragments.preferences.PreferencesFragment;

public class PreferencesActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setTitle("Setting");

        PreferencesFragment preferencesFragment = new PreferencesFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_preferencesContainer, preferencesFragment)
                .commit();
    }
}
