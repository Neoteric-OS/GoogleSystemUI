package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.wm.shell.R;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.power.AdaptiveChargingNotification;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AdaptiveChargingNotification {
    public final AdaptiveChargingManager mAdaptiveChargingManager;
    public final Context mContext;
    public final NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    boolean mWasActive = false;
    boolean mAdaptiveChargingQueryInBackground = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.power.AdaptiveChargingNotification$1, reason: invalid class name */
    public final class AnonymousClass1 implements AdaptiveChargingManager.AdaptiveChargingStatusReceiver {
        public final /* synthetic */ boolean val$forceUpdate;

        public AnonymousClass1(boolean z) {
            this.val$forceUpdate = z;
        }

        @Override // com.google.android.systemui.googlebattery.AdaptiveChargingManager.AdaptiveChargingStatusReceiver
        public final void onReceiveStatus(final int i, final String str) {
            Handler handler = AdaptiveChargingNotification.this.mHandler;
            final boolean z = this.val$forceUpdate;
            handler.post(new Runnable() { // from class: com.google.android.systemui.power.AdaptiveChargingNotification$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AdaptiveChargingNotification.AnonymousClass1 anonymousClass1 = AdaptiveChargingNotification.AnonymousClass1.this;
                    String str2 = str;
                    int i2 = i;
                    boolean z2 = z;
                    AdaptiveChargingNotification adaptiveChargingNotification = AdaptiveChargingNotification.this;
                    adaptiveChargingNotification.getClass();
                    boolean z3 = AdaptiveChargingManager.DEBUG;
                    if ((!"Active".equals(str2) && !"Enabled".equals(str2)) || i2 <= 0) {
                        adaptiveChargingNotification.cancelNotification();
                        return;
                    }
                    if (!adaptiveChargingNotification.mWasActive || z2) {
                        String formatTimeToFull = adaptiveChargingNotification.mAdaptiveChargingManager.formatTimeToFull(TimeUnit.SECONDS.toMillis(i2 + 29) + System.currentTimeMillis());
                        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(adaptiveChargingNotification.mContext, "BAT");
                        notificationCompat$Builder.mShowWhen = false;
                        notificationCompat$Builder.mSilent = true;
                        notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_charging;
                        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(adaptiveChargingNotification.mContext.getString(R.string.adaptive_charging_notify_title));
                        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(adaptiveChargingNotification.mContext.getString(R.string.adaptive_charging_notify_des, formatTimeToFull));
                        notificationCompat$Builder.addAction(adaptiveChargingNotification.mContext.getString(R.string.adaptive_charging_notify_turn_off_once), PowerUtils.createPendingIntent(adaptiveChargingNotification.mContext, "PNW.acChargeNormally", null));
                        notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(adaptiveChargingNotification.mContext, "systemui.power.action.dismissAdaptiveChargingWarning", null);
                        PowerUtils.overrideNotificationAppName(adaptiveChargingNotification.mContext, notificationCompat$Builder);
                        adaptiveChargingNotification.mNotificationManager.notifyAsUser("adaptive_charging", R.string.adaptive_charging_notify_title, notificationCompat$Builder.build(), UserHandle.ALL);
                        adaptiveChargingNotification.mUiEventLogger.log(BatteryMetricEvent.ADAPTIVE_CHARGING_NOTIFICATION);
                        adaptiveChargingNotification.mWasActive = true;
                    }
                }
            });
        }
    }

    public AdaptiveChargingNotification(Context context, AdaptiveChargingManager adaptiveChargingManager, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mUiEventLogger = uiEventLogger;
        this.mAdaptiveChargingManager = adaptiveChargingManager;
    }

    public final void cancelNotification() {
        if (this.mWasActive) {
            this.mNotificationManager.cancelAsUser("adaptive_charging", R.string.adaptive_charging_notify_title, UserHandle.ALL);
            this.mWasActive = false;
        }
    }

    public final void checkAdaptiveChargingStatus(boolean z) {
        this.mAdaptiveChargingManager.getClass();
        if (DeviceConfig.getBoolean("adaptive_charging", "adaptive_charging_notification", false)) {
            final AnonymousClass1 anonymousClass1 = new AnonymousClass1(z);
            if (this.mAdaptiveChargingQueryInBackground) {
                AsyncTask.execute(new Runnable() { // from class: com.google.android.systemui.power.AdaptiveChargingNotification$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AdaptiveChargingNotification adaptiveChargingNotification = AdaptiveChargingNotification.this;
                        AdaptiveChargingNotification.AnonymousClass1 anonymousClass12 = anonymousClass1;
                        adaptiveChargingNotification.mAdaptiveChargingManager.getClass();
                        AdaptiveChargingManager.queryStatus(anonymousClass12);
                    }
                });
            } else {
                AdaptiveChargingManager.queryStatus(anonymousClass1);
            }
        }
    }

    public void resolveBatteryChangedIntent(Intent intent) {
        boolean z = intent.getIntExtra("plugged", 0) != 0;
        boolean isCharged = BatteryStatus.isCharged(intent.getIntExtra("status", 1), BatteryStatus.getBatteryLevel(intent));
        if (!z || isCharged) {
            cancelNotification();
        } else {
            checkAdaptiveChargingStatus(false);
        }
    }
}
