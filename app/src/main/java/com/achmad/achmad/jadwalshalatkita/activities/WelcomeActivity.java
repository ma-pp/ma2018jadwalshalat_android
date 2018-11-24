package com.achmad.achmad.jadwalshalatkita.activities;

import android.content.Context;
import android.content.Intent;

import com.achmad.achmad.jadwalshalatkita.R;
import com.achmad.achmad.jadwalshalatkita.activities.preferences.PrefManager;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.WelcomeConfiguration;


public class WelcomeActivity extends com.stephentuso.welcome.WelcomeActivity {
    PrefManager prefManager;

    @Override
    protected WelcomeConfiguration configuration() {
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            prefManager.setIsFirstTimeLaunch(false);
            sendTo(getApplicationContext(), PrayerTimesActivity.class);
            finish();
        }
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(android.R.color.holo_green_dark)
                .page(new BasicPage(R.drawable.ic_masjeedd,
                        getString(R.string.welcome_title1),
                        getString(R.string.welcome_content1))
                )
                .page(new BasicPage(R.drawable.ic_tempatt,
                        getString(R.string.welcome_title2),
                        getString(R.string.welcome_content2))
                )
                .page(new BasicPage(R.drawable.ic_settings,
                        getString(R.string.welcome_title3),
                        getString(R.string.welcome_content3))
                )
                .page(new BasicPage(R.drawable.ic_widgett,
                        getString(R.string.welcome_title4),
                        getString(R.string.welcome_content4))
                )
                .animateButtons(true)
                .swipeToDismiss(true)
                .build();
    }

    private void sendTo(Context context, Class kelas) {
        Intent i = new Intent(context, kelas);
        startActivity(i);
    }
}
