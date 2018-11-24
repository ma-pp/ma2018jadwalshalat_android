package com.achmad.achmad.jadwalshalatkita.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.achmad.achmad.jadwalshalatkita.R;
import com.achmad.achmad.jadwalshalatkita.activities.preferences.PreferencesActivity;
import com.achmad.achmad.jadwalshalatkita.fragments.NoPlacesFoundFragment;
import com.achmad.achmad.jadwalshalatkita.fragments.PrayerTimesFragment;
import com.achmad.achmad.jadwalshalatkita.models.Place;
import com.github.mehmetakiftutuncu.toolbelt.Optional;
import com.achmad.achmad.jadwalshalatkita.utilities.Pref;
import com.stephentuso.welcome.WelcomeHelper;

public class PrayerTimesActivity extends MuezzinActivity {
    private WelcomeHelper welcomeScreenHelper;
    private boolean shownWelcomeScreen;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayertimes);

        welcomeScreenHelper = new WelcomeHelper(this, WelcomeActivity.class);
        welcomeScreenHelper.show(savedInstanceState);

//        shownWelcomeScreen = savedInstanceState != null && savedInstanceState.getBoolean("shownWelcomeScreen", false);
//
//        if (!shownWelcomeScreen) {
//            shownWelcomeScreen = true;
//
//            //njajal
//            if (Pref.Application.getVersion(this) < 4) {
//                // Installed version 2.0 for the first time
//                Toast.makeText(this, R.string.welcome_updateNotice, Toast.LENGTH_LONG).show();
//                Pref.getSharedPreferences(this).edit().clear().apply();
//                welcomeScreenHelper.forceShow();
//                Pref.Application.setVersion(this);
//            } else {
//                welcomeScreenHelper.show(savedInstanceState);
//            }
//        }
    }

    @Override protected void onResume() {
        super.onResume();

        Optional<Place> maybeCurrentPlace = Pref.Places.getCurrentPlace(this);

        if (maybeCurrentPlace.isEmpty()) {
            showNoPlacesFound();
        } else {
            Place currentPlace = maybeCurrentPlace.get();

            showPrayerTimes(currentPlace.toBundle());
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_preferences:
                startActivity(new Intent(this, PreferencesActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        welcomeScreenHelper.onSaveInstanceState(outState);
//        outState.putBoolean("shownWelcomeScreen", shownWelcomeScreen);

        super.onSaveInstanceState(outState);
    }

    private void showNoPlacesFound() {
        NoPlacesFoundFragment noPlacesFoundFragment = new NoPlacesFoundFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_prayerTimesContainer, noPlacesFoundFragment, "NoPlacesFoundFragment")
                .commit();
    }

    private void showPrayerTimes(Bundle bundle) {
        PrayerTimesFragment prayerTimesFragment = PrayerTimesFragment.with(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_prayerTimesContainer, prayerTimesFragment, "PrayerTimesFragment")
                .commit();
    }
}
