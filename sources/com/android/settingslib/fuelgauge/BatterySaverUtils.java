package com.android.settingslib.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Slog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BatterySaverUtils {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Parameters {
        public final int endNth;
        public final int startNth;

        public Parameters(Context context) {
            String string = Settings.Global.getString(context.getContentResolver(), "low_power_mode_suggestion_params");
            KeyValueListParser keyValueListParser = new KeyValueListParser(',');
            try {
                keyValueListParser.setString(string);
            } catch (IllegalArgumentException unused) {
                Slog.wtf("BatterySaverUtils", "Bad constants: " + string);
            }
            this.startNth = keyValueListParser.getInt("start_nth", 4);
            this.endNth = keyValueListParser.getInt("end_nth", 8);
        }
    }

    public static void sendSystemUiBroadcast(Context context, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        intent.setFlags(268435456);
        intent.setPackage("com.android.systemui");
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        if (r10 != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        android.provider.Settings.Secure.putInt(r8.getContentResolver(), "low_power_warning_acknowledged", 1);
        android.provider.Settings.Secure.putInt(r8.getContentResolver(), "extra_low_power_warning_acknowledged", 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void setPowerSaveMode(android.content.Context r8, boolean r9, boolean r10, int r11) {
        /*
            java.lang.String r0 = "Device is locked, setPowerSaveModeEnabled by default. "
            java.lang.Class<com.android.settingslib.fuelgauge.BatterySaverUtils> r1 = com.android.settingslib.fuelgauge.BatterySaverUtils.class
            monitor-enter(r1)
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3d
            java.lang.Class<android.os.PowerManager> r3 = android.os.PowerManager.class
            java.lang.Object r3 = r8.getSystemService(r3)     // Catch: java.lang.Throwable -> L3d
            android.os.PowerManager r3 = (android.os.PowerManager) r3     // Catch: java.lang.Throwable -> L3d
            java.lang.Class<android.app.KeyguardManager> r4 = android.app.KeyguardManager.class
            java.lang.Object r4 = r8.getSystemService(r4)     // Catch: java.lang.Throwable -> L3d
            android.app.KeyguardManager r4 = (android.app.KeyguardManager) r4     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            if (r9 == 0) goto L40
            if (r10 == 0) goto L40
            if (r4 == 0) goto L40
            boolean r4 = r4.isDeviceLocked()     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L40
            boolean r8 = r3.setPowerSaveModeEnabled(r5)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r9 = "BatterySaverUtils"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3d
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L3d
            r10.append(r8)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r8 = r10.toString()     // Catch: java.lang.Throwable -> L3d
            android.util.Log.d(r9, r8)     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r1)
            return
        L3d:
            r8 = move-exception
            goto Ld4
        L40:
            android.os.Bundle r0 = new android.os.Bundle     // Catch: java.lang.Throwable -> L3d
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = "extra_confirm_only"
            r6 = 0
            r0.putBoolean(r4, r6)     // Catch: java.lang.Throwable -> L3d
            if (r9 == 0) goto L6f
            if (r10 == 0) goto L6f
            android.content.ContentResolver r4 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r7 = "low_power_warning_acknowledged"
            int r4 = android.provider.Settings.Secure.getInt(r4, r7, r6)     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L68
            android.content.ContentResolver r4 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r7 = "extra_low_power_warning_acknowledged"
            int r4 = android.provider.Settings.Secure.getInt(r4, r7, r6)     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L68
            goto L6f
        L68:
            java.lang.String r9 = "PNW.startSaverConfirmation"
            sendSystemUiBroadcast(r8, r9, r0)     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r1)
            return
        L6f:
            if (r9 == 0) goto L85
            if (r10 != 0) goto L85
            android.content.ContentResolver r10 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = "low_power_warning_acknowledged"
            android.provider.Settings.Secure.putInt(r10, r4, r5)     // Catch: java.lang.Throwable -> L3d
            android.content.ContentResolver r10 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = "extra_low_power_warning_acknowledged"
            android.provider.Settings.Secure.putInt(r10, r4, r5)     // Catch: java.lang.Throwable -> L3d
        L85:
            boolean r10 = r3.setPowerSaveModeEnabled(r9)     // Catch: java.lang.Throwable -> L3d
            if (r10 == 0) goto Ld2
            if (r9 == 0) goto Lbb
            java.lang.String r10 = "low_power_manual_activation_count"
            int r10 = android.provider.Settings.Secure.getInt(r2, r10, r6)     // Catch: java.lang.Throwable -> L3d
            int r10 = r10 + r5
            java.lang.String r3 = "low_power_manual_activation_count"
            android.provider.Settings.Secure.putInt(r2, r3, r10)     // Catch: java.lang.Throwable -> L3d
            com.android.settingslib.fuelgauge.BatterySaverUtils$Parameters r3 = new com.android.settingslib.fuelgauge.BatterySaverUtils$Parameters     // Catch: java.lang.Throwable -> L3d
            r3.<init>(r8)     // Catch: java.lang.Throwable -> L3d
            int r4 = r3.startNth     // Catch: java.lang.Throwable -> L3d
            if (r10 < r4) goto Lbb
            int r3 = r3.endNth     // Catch: java.lang.Throwable -> L3d
            if (r10 > r3) goto Lbb
            java.lang.String r10 = "low_power_trigger_level"
            int r10 = android.provider.Settings.Global.getInt(r2, r10, r6)     // Catch: java.lang.Throwable -> L3d
            if (r10 != 0) goto Lbb
            java.lang.String r10 = "suppress_auto_battery_saver_suggestion"
            int r10 = android.provider.Settings.Secure.getInt(r2, r10, r6)     // Catch: java.lang.Throwable -> L3d
            if (r10 != 0) goto Lbb
            java.lang.String r10 = "PNW.autoSaverSuggestion"
            sendSystemUiBroadcast(r8, r10, r0)     // Catch: java.lang.Throwable -> L3d
        Lbb:
            android.os.Bundle r10 = new android.os.Bundle     // Catch: java.lang.Throwable -> L3d
            r0 = 2
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r0 = "extra_power_save_mode_manual_enabled_reason"
            r10.putInt(r0, r11)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r11 = "extra_power_save_mode_manual_enabled"
            r10.putBoolean(r11, r9)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r9 = "com.android.settingslib.fuelgauge.ACTION_SAVER_STATE_MANUAL_UPDATE"
            sendSystemUiBroadcast(r8, r9, r10)     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r1)
            return
        Ld2:
            monitor-exit(r1)
            return
        Ld4:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L3d
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.fuelgauge.BatterySaverUtils.setPowerSaveMode(android.content.Context, boolean, boolean, int):void");
    }
}
