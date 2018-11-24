package com.achmad.achmad.jadwalshalatkita.broadcastreceivers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.achmad.achmad.jadwalshalatkita.services.PrayerTimeReminderService;
import com.github.mehmetakiftutuncu.toolbelt.Log;

public class PrayerTimeReminderBroadcastReceiver extends WakefulBroadcastReceiver {
    public PrayerTimeReminderBroadcastReceiver() {}

    @Override public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();

            switch (action) {
                case PrayerTimeReminderService.ACTION_REMIND:
                    Log.debug(getClass(), "Received prayer time reminder!");

                    PrayerTimeReminderService.setReminder(context, intent.getExtras());
                    break;

                case PrayerTimeReminderService.ACTION_SCHEDULE:
                    Log.debug(getClass(), "Received prayer time scheduler!");

                    PrayerTimeReminderService.setScheduler(context);
                    break;
            }
        }

    }
}
