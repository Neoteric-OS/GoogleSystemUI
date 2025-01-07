package com.android.systemui.plugins.clocks;

import android.content.res.Resources;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockEvents {
    boolean isReactiveTouchInteractionEnabled();

    void onAlarmDataChanged(AlarmData alarmData);

    void onColorPaletteChanged(Resources resources);

    void onLocaleChanged(Locale locale);

    void onReactiveAxesChanged(List list);

    void onSeedColorChanged(Integer num);

    void onTimeFormatChanged(boolean z);

    void onTimeZoneChanged(TimeZone timeZone);

    void onWeatherDataChanged(WeatherData weatherData);

    void onZenDataChanged(ZenData zenData);

    void setReactiveTouchInteractionEnabled(boolean z);
}
