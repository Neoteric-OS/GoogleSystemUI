package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DwellTempDefenderNotification {
    public final Context context;
    public boolean lastPlugged;
    public final NotificationManager notificationManager;
    public boolean notificationVisible;
    public boolean postNotificationVisible;
    public final UiEventLogger uiEventLogger;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BatteryDefendType {
        public static final /* synthetic */ BatteryDefendType[] $VALUES;
        public static final BatteryDefendType DWELL_DEFEND;
        public static final BatteryDefendType TEMP_DEFEND;

        static {
            BatteryDefendType batteryDefendType = new BatteryDefendType("TEMP_DEFEND", 0);
            TEMP_DEFEND = batteryDefendType;
            BatteryDefendType batteryDefendType2 = new BatteryDefendType("DWELL_DEFEND", 1);
            DWELL_DEFEND = batteryDefendType2;
            BatteryDefendType[] batteryDefendTypeArr = {batteryDefendType, batteryDefendType2};
            $VALUES = batteryDefendTypeArr;
            EnumEntriesKt.enumEntries(batteryDefendTypeArr);
        }

        public static BatteryDefendType valueOf(String str) {
            return (BatteryDefendType) Enum.valueOf(BatteryDefendType.class, str);
        }

        public static BatteryDefendType[] values() {
            return (BatteryDefendType[]) $VALUES.clone();
        }
    }

    public DwellTempDefenderNotification(Context context, NotificationManager notificationManager, UiEventLogger uiEventLogger) {
        this.context = context;
        this.notificationManager = notificationManager;
        this.uiEventLogger = uiEventLogger;
    }

    public final void cancelNotification() {
        Log.d("DwellTempDefenderNotification", "cancelNotification, notificationVisible:" + this.notificationVisible + "->false");
        this.notificationVisible = false;
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager != null) {
            notificationManager.cancelAsUser("battery_defender", R.string.defender_notify_title, UserHandle.ALL);
        }
    }

    public final void cancelPostNotification() {
        Log.d("DwellTempDefenderNotification", "swipeNotificationByUser, postNotificationVisible:" + this.postNotificationVisible + "->false");
        this.postNotificationVisible = false;
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager != null) {
            notificationManager.cancelAsUser("battery_defender", R.string.defender_post_notify_title, UserHandle.ALL);
        }
    }

    public final void sendDefenderNotification(boolean z, BatteryDefendType batteryDefendType) {
        int i;
        int i2;
        Log.d("DwellTempDefenderNotification", "sendDefenderNotification, plugged:" + z + ", type: " + batteryDefendType);
        int ordinal = batteryDefendType.ordinal();
        if (ordinal == 0) {
            i = R.string.temp_defender_notify_title;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.dwell_defender_notify_title;
        }
        String string = this.context.getString(i);
        int ordinal2 = batteryDefendType.ordinal();
        if (ordinal2 == 0) {
            i2 = R.string.temp_defender_notify_des;
        } else {
            if (ordinal2 != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i2 = R.string.dwell_defender_notify_des;
        }
        String string2 = this.context.getString(i2);
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this.context, "BAT");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_with_shield;
        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string);
        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string2);
        notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntentAsUser(this.context);
        notificationCompat$Builder.mSilent = true;
        notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(this.context, "systemui.power.action.dismissBatteryDefenderWarning", null);
        notificationCompat$Builder.addAction(this.context.getString(R.string.battery_health_notify_learn_more), PowerUtils.createHelpArticlePendingIntentAsUser(R.string.defender_notify_help_url, this.context));
        notificationCompat$Builder.mLocalOnly = true;
        if (z) {
            notificationCompat$Builder.addAction(this.context.getString(R.string.defender_notify_resume_charge), PowerUtils.createPendingIntent(this.context, "PNW.defenderResumeCharging", null));
        }
        PowerUtils.overrideNotificationAppName(this.context, notificationCompat$Builder);
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager != null) {
            notificationManager.notifyAsUser("battery_defender", R.string.defender_notify_title, notificationCompat$Builder.build(), UserHandle.ALL);
        }
    }
}
