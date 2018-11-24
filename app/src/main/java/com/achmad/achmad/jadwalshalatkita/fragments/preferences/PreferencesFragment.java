package com.achmad.achmad.jadwalshalatkita.fragments.preferences;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.achmad.achmad.jadwalshalatkita.R;
import com.achmad.achmad.jadwalshalatkita.activities.LicencesActivity;
import com.achmad.achmad.jadwalshalatkita.activities.PlaceSelectionActivity;
import com.achmad.achmad.jadwalshalatkita.activities.preferences.ReminderPreferencesActivity;
import com.achmad.achmad.jadwalshalatkita.models.Place;
import com.achmad.achmad.jadwalshalatkita.models.PrayerTimeReminder;
import com.achmad.achmad.jadwalshalatkita.utilities.Pref;
import com.achmad.achmad.jadwalshalatkita.widgetproviders.PrayerTimesWidgetBase;
import com.github.mehmetakiftutuncu.toolbelt.Optional;

/**
 * Created by akif on 08/05/16.
 */
public class PreferencesFragment extends PreferenceFragment {
    public static final String KEY_GENERAL_PLACE     = "general_place";
    public static final String KEY_GENERAL_REMINDERS = "general_reminders";
    public static final String KEY_MORE_RATE         = "more_rate";
    public static final String KEY_MORE_FEEDBACK     = "more_feedback";
    public static final String KEY_MORE_VERSION      = "more_version";
    public static final String KEY_MORE_LICENSES     = "more_licenses";

    public static final String RATE_URI = "market://details?id=com.mehmetakiftutuncu.muezzin";

    public static final String FEEDBACK_CONTACT = "budi@gmail.com";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    @Override public void onResume() {
        super.onResume();

        initializePreferences();

        PrayerTimeReminder.reschedulePrayerTimeReminders(getActivity());
        PrayerTimesWidgetBase.updateAllWidgets(getActivity());
    }

    private void initializePreferences() {
        Preference place = findPreference(KEY_GENERAL_PLACE);
        place.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity(), PlaceSelectionActivity.class);
            intent.putExtra(PlaceSelectionActivity.EXTRA_STARTED_FROM_PREFERENCES, true);
            startActivity(intent);

            return true;
        });

        updatePlaceSummary(place);

        Preference reminders = findPreference(KEY_GENERAL_REMINDERS);
        reminders.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity(), ReminderPreferencesActivity.class);
            startActivity(intent);

            return true;
        });

        Preference rate = findPreference(KEY_MORE_RATE);
        rate.setOnPreferenceClickListener(preference -> {

            Toast.makeText(getActivity(), "menu comming soon", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(RATE_URI));
//            startActivity(Intent.createChooser(intent, getString(R.string.preferences_more_rate)));

            return true;
        });

        Preference feedback = findPreference(KEY_MORE_FEEDBACK);
        feedback.setOnPreferenceClickListener(preference -> {

            Toast.makeText(getActivity(), "menu comming soon", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("message/rfc822");
//            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{FEEDBACK_CONTACT});
//            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.applicationName));
//            startActivity(Intent.createChooser(intent, getString(R.string.preferences_more_feedback)));

            return true;
        });

        Preference version = findPreference(KEY_MORE_VERSION);
        String versionName = Pref.getAppVersionName(getActivity());
        version.setSummary(versionName);

        Preference licenses = findPreference(KEY_MORE_LICENSES);
        licenses.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity(), LicencesActivity.class);
            startActivity(intent);

            return true;
        });
    }

    private void updatePlaceSummary(Preference preference) {
        Optional<Place> maybeCurrentPlace = Pref.Places.getCurrentPlace(getActivity());

        String summary = maybeCurrentPlace.isDefined() ? maybeCurrentPlace.get().getPlaceName(getActivity()).getOrElse("") : "";

        if (!summary.isEmpty()) {
            preference.setSummary(summary);
        }
    }
}
