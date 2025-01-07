package com.google.android.systemui.googlebattery;

import android.content.Context;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import java.util.Locale;
import vendor.google.google_battery.ChargingStage;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AdaptiveChargingManager {
    public static final boolean DEBUG = Log.isLoggable("AdaptiveChargingManager", 3);
    Context mContext;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface AdaptiveChargingStatusReceiver {
        void onReceiveStatus(int i, String str);
    }

    public AdaptiveChargingManager(Context context) {
        this.mContext = context;
    }

    public static void queryStatus(final AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver) {
        IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.google.android.systemui.googlebattery.AdaptiveChargingManager.1
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                if (AdaptiveChargingManager.DEBUG) {
                    Log.d("AdaptiveChargingManager", "serviceDied");
                }
                AdaptiveChargingStatusReceiver.this.getClass();
            }
        };
        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(deathRecipient);
        if (initHalInterface == null) {
            adaptiveChargingStatusReceiver.getClass();
            return;
        }
        try {
            ChargingStage chargingStageAndDeadline = ((IGoogleBattery.Stub.Proxy) initHalInterface).getChargingStageAndDeadline();
            queryStatusReceived(adaptiveChargingStatusReceiver, chargingStageAndDeadline.stage, chargingStageAndDeadline.deadlineSecs);
        } catch (ServiceSpecificException | RemoteException | IllegalArgumentException e) {
            Log.e("AdaptiveChargingManager", "Failed to get Adaptive Chaging status: ", e);
        }
        GoogleBatteryManager.destroyHalInterface(initHalInterface, deathRecipient);
        adaptiveChargingStatusReceiver.getClass();
    }

    public static void queryStatusReceived(AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver, String str, int i) {
        if (DEBUG) {
            Log.d("AdaptiveChargingManager", "getChargingStageDeadlineCallback stage: \"" + str + "\", seconds: " + i);
        }
        adaptiveChargingStatusReceiver.onReceiveStatus(i, str);
    }

    public final String formatTimeToFull(long j) {
        String str = DateFormat.is24HourFormat(this.mContext) ? "Hm" : "hma";
        LocaleList locales = this.mContext.getResources().getConfiguration().getLocales();
        return DateFormat.format(DateFormat.getBestDateTimePattern((locales == null || locales.isEmpty()) ? Locale.getDefault() : locales.get(0), str), j).toString();
    }

    public boolean hasAdaptiveChargingFeature() {
        return this.mContext.getPackageManager().hasSystemFeature("com.google.android.feature.ADAPTIVE_CHARGING");
    }

    public final boolean isEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "adaptive_charging_enabled", 1) == 1;
    }
}
