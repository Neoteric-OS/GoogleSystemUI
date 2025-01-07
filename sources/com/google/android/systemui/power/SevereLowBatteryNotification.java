package com.google.android.systemui.power;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SevereLowBatteryNotification {
    public final Context context;
    public final KeyguardManager keyguardManager;
    public final Lazy notificationManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.google.android.systemui.power.SevereLowBatteryNotification$notificationManager$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (NotificationManager) SevereLowBatteryNotification.this.context.getSystemService(NotificationManager.class);
        }
    });
    public final UiEventLogger uiEventLogger;

    public SevereLowBatteryNotification(Context context, UiEventLogger uiEventLogger, KeyguardManager keyguardManager) {
        this.context = context;
        this.uiEventLogger = uiEventLogger;
        this.keyguardManager = keyguardManager;
    }

    public final void logEvent(BatteryMetricEvent batteryMetricEvent) {
        this.uiEventLogger.log(batteryMetricEvent);
        Log.d("SevereLowBatteryNotification", "logEvent " + batteryMetricEvent);
    }
}
