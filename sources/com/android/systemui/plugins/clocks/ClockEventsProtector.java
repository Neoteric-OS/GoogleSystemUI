package com.android.systemui.plugins.clocks;

import android.content.res.Resources;
import android.util.Log;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockEventsProtector implements ClockEvents, PluginWrapper {
    private static final String CLASS = "ClockEvents";
    private boolean mHasError = false;
    private ClockEvents mInstance;
    private ProtectedPluginListener mListener;

    private ClockEventsProtector(ClockEvents clockEvents, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockEvents;
        this.mListener = protectedPluginListener;
    }

    public static ClockEventsProtector protect(ClockEvents clockEvents, ProtectedPluginListener protectedPluginListener) {
        return clockEvents instanceof ClockEventsProtector ? (ClockEventsProtector) clockEvents : new ClockEventsProtector(clockEvents, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public boolean isReactiveTouchInteractionEnabled() {
        if (this.mHasError) {
            return false;
        }
        try {
            return this.mInstance.isReactiveTouchInteractionEnabled();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: isReactiveTouchInteractionEnabled", e);
            this.mHasError = this.mListener.onFail(CLASS, "isReactiveTouchInteractionEnabled", e);
            return false;
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onAlarmDataChanged(AlarmData alarmData) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onAlarmDataChanged(alarmData);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onAlarmDataChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onAlarmDataChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onColorPaletteChanged(Resources resources) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onColorPaletteChanged(resources);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onColorPaletteChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onColorPaletteChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onLocaleChanged(Locale locale) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onLocaleChanged(locale);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onLocaleChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onLocaleChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onReactiveAxesChanged(List list) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onReactiveAxesChanged(list);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onReactiveAxesChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onReactiveAxesChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onSeedColorChanged(Integer num) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onSeedColorChanged(num);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onSeedColorChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onSeedColorChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onTimeFormatChanged(boolean z) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onTimeFormatChanged(z);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onTimeFormatChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onTimeFormatChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onTimeZoneChanged(TimeZone timeZone) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onTimeZoneChanged(timeZone);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onTimeZoneChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onTimeZoneChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onWeatherDataChanged(WeatherData weatherData) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onWeatherDataChanged(weatherData);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onWeatherDataChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onWeatherDataChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void onZenDataChanged(ZenData zenData) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onZenDataChanged(zenData);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onZenDataChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onZenDataChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockEvents
    public void setReactiveTouchInteractionEnabled(boolean z) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.setReactiveTouchInteractionEnabled(z);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: setReactiveTouchInteractionEnabled", e);
            this.mHasError = this.mListener.onFail(CLASS, "setReactiveTouchInteractionEnabled", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockEvents getPlugin() {
        return this.mInstance;
    }
}
