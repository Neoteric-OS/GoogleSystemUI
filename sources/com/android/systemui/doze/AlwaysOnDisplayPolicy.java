package com.android.systemui.doze;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlwaysOnDisplayPolicy {
    public int defaultDozeBrightness;
    public int dimBrightness;
    public float dimBrightnessFloat;
    public int[] dimmingScrimArray;
    public final Context mContext;
    public final KeyValueListParser mParser;
    public long proxScreenOffDelayMs;
    public int[] screenBrightnessArray;
    public long wallpaperFadeOutDuration;
    public long wallpaperVisibilityDuration;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsObserver extends ContentObserver {
        public final Uri ALWAYS_ON_DISPLAY_CONSTANTS_URI;

        public SettingsObserver(Handler handler) {
            super(handler);
            this.ALWAYS_ON_DISPLAY_CONSTANTS_URI = Settings.Global.getUriFor("always_on_display_constants");
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            update(uri);
        }

        public final void update(Uri uri) {
            if (uri == null || this.ALWAYS_ON_DISPLAY_CONSTANTS_URI.equals(uri)) {
                Resources resources = AlwaysOnDisplayPolicy.this.mContext.getResources();
                try {
                    AlwaysOnDisplayPolicy.this.mParser.setString(Settings.Global.getString(AlwaysOnDisplayPolicy.this.mContext.getContentResolver(), "always_on_display_constants"));
                } catch (IllegalArgumentException unused) {
                    Log.e("AlwaysOnDisplayPolicy", "Bad AOD constants");
                }
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy.proxScreenOffDelayMs = alwaysOnDisplayPolicy.mParser.getLong("prox_screen_off_delay", 10000L);
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy2 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy2.mParser.getLong("prox_cooldown_trigger", 2000L);
                alwaysOnDisplayPolicy2.getClass();
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy3 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy3.mParser.getLong("prox_cooldown_period", 5000L);
                alwaysOnDisplayPolicy3.getClass();
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy4 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy4.wallpaperFadeOutDuration = alwaysOnDisplayPolicy4.mParser.getLong("wallpaper_fade_out_duration", 400L);
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy5 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy5.wallpaperVisibilityDuration = alwaysOnDisplayPolicy5.mParser.getLong("wallpaper_visibility_timeout", 60000L);
                AlwaysOnDisplayPolicy.this.defaultDozeBrightness = resources.getInteger(R.integer.config_screen_magnification_multi_tap_adjustment);
                AlwaysOnDisplayPolicy.this.dimBrightness = resources.getInteger(R.integer.config_screenTimeoutOverride);
                AlwaysOnDisplayPolicy.this.dimBrightnessFloat = resources.getFloat(R.dimen.config_screenBrightnessSettingMinimumFloat);
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy6 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy6.screenBrightnessArray = alwaysOnDisplayPolicy6.mParser.getIntArray("screen_brightness_array", resources.getIntArray(com.android.wm.shell.R.array.config_doze_brightness_sensor_to_brightness));
                AlwaysOnDisplayPolicy alwaysOnDisplayPolicy7 = AlwaysOnDisplayPolicy.this;
                alwaysOnDisplayPolicy7.dimmingScrimArray = alwaysOnDisplayPolicy7.mParser.getIntArray("dimming_scrim_array", resources.getIntArray(com.android.wm.shell.R.array.config_doze_brightness_sensor_to_scrim_opacity));
            }
        }
    }

    public AlwaysOnDisplayPolicy(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mParser = new KeyValueListParser(',');
        SettingsObserver settingsObserver = new SettingsObserver(applicationContext.getMainThreadHandler());
        applicationContext.getContentResolver().registerContentObserver(settingsObserver.ALWAYS_ON_DISPLAY_CONSTANTS_URI, false, settingsObserver, -1);
        settingsObserver.update(null);
    }
}
