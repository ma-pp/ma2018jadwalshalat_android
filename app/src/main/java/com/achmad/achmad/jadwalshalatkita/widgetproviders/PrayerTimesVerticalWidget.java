package com.achmad.achmad.jadwalshalatkita.widgetproviders;

import com.achmad.achmad.jadwalshalatkita.models.WidgetType;

/**
 * Created by akif on 08/07/16.
 */
public class PrayerTimesVerticalWidget extends PrayerTimesWidgetBase {
    public static final int UPDATE_INTERVAL_IN_SECONDS = 10;

    @Override protected WidgetType widgetType() {
        return WidgetType.PRAYER_TIMES_VERTICAL;
    }

    @Override public int updateIntervalInSeconds() {
        return UPDATE_INTERVAL_IN_SECONDS;
    }
}
