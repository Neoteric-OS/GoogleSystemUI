package com.android.systemui.statusbar.policy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryStateNotifier implements BatteryController.BatteryStateChangeCallback {
    public final Context context;
    public final BatteryController controller;
    public final DelayableExecutor delayableExecutor;
    public final NotificationManager noMan;
    public boolean stateUnknown;

    public BatteryStateNotifier(BatteryController batteryController, NotificationManager notificationManager, DelayableExecutor delayableExecutor, Context context) {
        this.controller = batteryController;
        this.noMan = notificationManager;
        this.delayableExecutor = delayableExecutor;
        this.context = context;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onBatteryUnknownStateChanged(boolean z) {
        this.stateUnknown = z;
        if (!z) {
            final Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.policy.BatteryStateNotifier$scheduleNotificationCancel$r$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BatteryStateNotifier batteryStateNotifier = BatteryStateNotifier.this;
                    if (!batteryStateNotifier.stateUnknown) {
                        batteryStateNotifier.noMan.cancel(666);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.delayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.policy.BatteryStateNotifierKt$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Function0.this.invoke();
                }
            }, 14400000L);
        } else {
            NotificationChannel notificationChannel = new NotificationChannel("battery_status", "Battery status", 3);
            this.noMan.createNotificationChannel(notificationChannel);
            this.noMan.notify("BatteryStateNotifier", 666, new Notification.Builder(this.context, notificationChannel.getId()).setAutoCancel(false).setContentTitle(this.context.getString(R.string.battery_state_unknown_notification_title)).setContentText(this.context.getString(R.string.battery_state_unknown_notification_text)).setSmallIcon(android.R.drawable.stat_sys_download_anim5).setContentIntent(PendingIntent.getActivity(this.context, 0, new Intent("android.intent.action.VIEW", Uri.parse(this.context.getString(R.string.config_batteryStateUnknownUrl))), 67108864)).setAutoCancel(true).setOngoing(true).build());
        }
    }
}
