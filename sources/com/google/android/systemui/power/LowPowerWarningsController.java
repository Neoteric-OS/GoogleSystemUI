package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LowPowerWarningsController {
    public final Context context;
    public final Executor executor;
    public boolean extremeLowBatterySectionEntered;
    public final ExtremeLowBatteryNotification extremeLowNotification;
    public final GlobalSettings globalSettings;
    public final LowBatteryNotification lowBatteryNotification;
    public boolean lowBatteryNotificationCancelled;
    public boolean lowBatterySectionEntered;
    public final PowerManager powerManager;
    public List prevBatteryEventTypes;
    public Integer prevBatteryLevel;
    public Boolean prevPowerSaveEnabledAsync;
    public final SevereLowBatteryNotification severeLowBatteryNotification;
    public boolean severeLowBatteryNotificationCancelled;
    public boolean severeLowBatterySectionEntered;
    public final UiEventLogger uiEventLogger;

    public LowPowerWarningsController(Context context, SevereLowBatteryNotification severeLowBatteryNotification, GlobalSettings globalSettings, UiEventLogger uiEventLogger, Executor executor) {
        LowBatteryNotification lowBatteryNotification = new LowBatteryNotification(context);
        ExtremeLowBatteryNotification extremeLowBatteryNotification = new ExtremeLowBatteryNotification(context, uiEventLogger);
        this.context = context;
        this.severeLowBatteryNotification = severeLowBatteryNotification;
        this.globalSettings = globalSettings;
        this.uiEventLogger = uiEventLogger;
        this.executor = executor;
        this.lowBatteryNotification = lowBatteryNotification;
        this.extremeLowNotification = extremeLowBatteryNotification;
        this.powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.prevBatteryEventTypes = EmptyList.INSTANCE;
        Settings.Secure.putInt(context.getContentResolver(), "suppress_auto_battery_saver_suggestion", 1);
        Settings.Secure.putInt(context.getContentResolver(), "low_power_warning_acknowledged", 1);
    }

    public final void cancelNotification() {
        if (this.lowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->lowBatterySection");
            this.lowBatteryNotification.mNotificationManager.cancelAsUser("low_battery", 3, UserHandle.ALL);
            this.lowBatteryNotificationCancelled = true;
        }
        if (this.severeLowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->severeLowBatterySection");
            SevereLowBatteryNotification severeLowBatteryNotification = this.severeLowBatteryNotification;
            severeLowBatteryNotification.getClass();
            Log.d("SevereLowBatteryNotification", "cancel()");
            ((NotificationManager) severeLowBatteryNotification.notificationManager$delegate.getValue()).cancelAsUser("low_battery", 3, UserHandle.ALL);
            this.severeLowBatteryNotificationCancelled = true;
        }
        if (this.extremeLowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->extremeLowBatterySection");
            this.extremeLowNotification.mNotificationManager.cancelAsUser("low_battery", R.string.extreme_low_battery_notification_title, UserHandle.ALL);
        }
    }

    public final boolean isScheduledByPercentage() {
        GlobalSettings globalSettings = this.globalSettings;
        return globalSettings.getInt(0, "automatic_power_save_mode") == 0 && globalSettings.getInt(0, "low_power_trigger_level") > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBatteryEventUpdate(int r19, java.util.List r20) {
        /*
            Method dump skipped, instructions count: 929
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.LowPowerWarningsController.onBatteryEventUpdate(int, java.util.List):void");
    }
}
