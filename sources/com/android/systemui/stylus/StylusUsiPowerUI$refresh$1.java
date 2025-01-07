package com.android.systemui.stylus;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.InputDevice;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationManagerCompat;
import com.android.systemui.shared.hardware.InputManagerKt;
import com.android.wm.shell.R;
import java.text.NumberFormat;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StylusUsiPowerUI$refresh$1 implements Runnable {
    public final /* synthetic */ StylusUsiPowerUI this$0;

    public StylusUsiPowerUI$refresh$1(StylusUsiPowerUI stylusUsiPowerUI) {
        this.this$0 = stylusUsiPowerUI;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StylusUsiPowerUI stylusUsiPowerUI = this.this$0;
        boolean z = !Float.isNaN(stylusUsiPowerUI.batteryCapacity) && stylusUsiPowerUI.batteryCapacity <= 0.16f;
        StylusUsiPowerUI stylusUsiPowerUI2 = this.this$0;
        if (stylusUsiPowerUI2.suppressed || InputManagerKt.hasInputDevice(stylusUsiPowerUI2.inputManager, new Function1() { // from class: com.android.systemui.stylus.StylusUsiPowerUI$hasConnectedBluetoothStylus$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                InputDevice inputDevice = (InputDevice) obj;
                return Boolean.valueOf(inputDevice.supportsSource(16386) && inputDevice.getBluetoothAddress() != null);
            }
        }) || !z) {
            StylusUsiPowerUI stylusUsiPowerUI3 = this.this$0;
            if (stylusUsiPowerUI3.suppressed || !z) {
                boolean z2 = Build.IS_DEBUGGABLE;
                Reflection.getOrCreateKotlinClass(StylusUsiPowerUI.class).getSimpleName();
                stylusUsiPowerUI3.instanceId = null;
                stylusUsiPowerUI3.notificationManager.mNotificationManager.cancel(null, StylusUsiPowerUI.USI_NOTIFICATION_ID);
            }
            if (z) {
                return;
            }
            this.this$0.suppressed = false;
            return;
        }
        StylusUsiPowerUI stylusUsiPowerUI4 = this.this$0;
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(stylusUsiPowerUI4.context, "BAT");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_power_low;
        notificationCompat$Builder.mNotification.deleteIntent = PendingIntent.getBroadcast(stylusUsiPowerUI4.context, 0, new Intent("StylusUsiPowerUI.dismiss").setPackage(stylusUsiPowerUI4.context.getPackageName()), 67108864);
        notificationCompat$Builder.mContentIntent = PendingIntent.getBroadcast(stylusUsiPowerUI4.context, 0, new Intent("StylusUsiPowerUI.click").setPackage(stylusUsiPowerUI4.context.getPackageName()), 67108864);
        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(stylusUsiPowerUI4.context.getString(R.string.stylus_battery_low_percentage, NumberFormat.getPercentInstance().format(Float.valueOf(stylusUsiPowerUI4.batteryCapacity))));
        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(stylusUsiPowerUI4.context.getString(R.string.stylus_battery_low_subtitle));
        notificationCompat$Builder.mLocalOnly = true;
        notificationCompat$Builder.setFlag(8, true);
        notificationCompat$Builder.setFlag(16, true);
        Notification build = notificationCompat$Builder.build();
        boolean z3 = Build.IS_DEBUGGABLE;
        Reflection.getOrCreateKotlinClass(StylusUsiPowerUI.class).getSimpleName();
        stylusUsiPowerUI4.logUiEvent(StylusUiEvent.STYLUS_LOW_BATTERY_NOTIFICATION_SHOWN);
        NotificationManagerCompat notificationManagerCompat = stylusUsiPowerUI4.notificationManager;
        notificationManagerCompat.getClass();
        Bundle bundle = build.extras;
        int i = StylusUsiPowerUI.USI_NOTIFICATION_ID;
        if (bundle == null || !bundle.getBoolean("android.support.useSideChannel")) {
            notificationManagerCompat.mNotificationManager.notify(null, i, build);
            return;
        }
        NotificationManagerCompat.NotifyTask notifyTask = new NotificationManagerCompat.NotifyTask(notificationManagerCompat.mContext.getPackageName(), i, build);
        synchronized (NotificationManagerCompat.sLock) {
            try {
                if (NotificationManagerCompat.sSideChannelManager == null) {
                    NotificationManagerCompat.sSideChannelManager = new NotificationManagerCompat.SideChannelManager(notificationManagerCompat.mContext.getApplicationContext());
                }
                NotificationManagerCompat.sSideChannelManager.mHandler.obtainMessage(0, notifyTask).sendToTarget();
            } catch (Throwable th) {
                throw th;
            }
        }
        notificationManagerCompat.mNotificationManager.cancel(null, i);
    }
}
