package com.android.systemui.stylus;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StylusUsiPowerUI {
    public static final int USI_NOTIFICATION_ID;
    public final Context context;
    public final Handler handler;
    public Integer inputDeviceId;
    public final InputManager inputManager;
    public InstanceId instanceId;
    public final NotificationManagerCompat notificationManager;
    public boolean suppressed;
    public final UiEventLogger uiEventLogger;
    public float batteryCapacity = 1.0f;
    public final InstanceIdSequence instanceIdSequence = new InstanceIdSequence(8192);
    public final StylusUsiPowerUI$receiver$1 receiver = new BroadcastReceiver() { // from class: com.android.systemui.stylus.StylusUsiPowerUI$receiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                int hashCode = action.hashCode();
                if (hashCode != -263791366) {
                    if (hashCode == 710718844 && action.equals("StylusUsiPowerUI.dismiss")) {
                        boolean z = Build.IS_DEBUGGABLE;
                        Reflection.getOrCreateKotlinClass(StylusUsiPowerUI$receiver$1.class).getSimpleName();
                        StylusUsiPowerUI.this.logUiEvent(StylusUiEvent.STYLUS_LOW_BATTERY_NOTIFICATION_DISMISSED);
                        StylusUsiPowerUI stylusUsiPowerUI = StylusUsiPowerUI.this;
                        stylusUsiPowerUI.getClass();
                        stylusUsiPowerUI.handler.post(new StylusUsiPowerUI$updateSuppression$1(stylusUsiPowerUI, true));
                        return;
                    }
                    return;
                }
                if (action.equals("StylusUsiPowerUI.click")) {
                    boolean z2 = Build.IS_DEBUGGABLE;
                    Reflection.getOrCreateKotlinClass(StylusUsiPowerUI$receiver$1.class).getSimpleName();
                    StylusUsiPowerUI.this.logUiEvent(StylusUiEvent.STYLUS_LOW_BATTERY_NOTIFICATION_CLICKED);
                    StylusUsiPowerUI stylusUsiPowerUI2 = StylusUsiPowerUI.this;
                    stylusUsiPowerUI2.getClass();
                    stylusUsiPowerUI2.handler.post(new StylusUsiPowerUI$updateSuppression$1(stylusUsiPowerUI2, true));
                    if (StylusUsiPowerUI.this.inputDeviceId == null) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    Integer num = StylusUsiPowerUI.this.inputDeviceId;
                    Intrinsics.checkNotNull(num);
                    bundle.putInt("device_input_id", num.intValue());
                    try {
                        context.startActivity(new Intent("com.android.settings.STYLUS_USI_DETAILS_SETTINGS").putExtra(":settings:show_fragment_args", bundle).addFlags(67108864).addFlags(268435456));
                    } catch (ActivityNotFoundException unused) {
                        Log.e("StylusUsiPowerUI", "Cannot open USI details page.");
                    }
                }
            }
        }
    };

    static {
        Reflection.getOrCreateKotlinClass(StylusUsiPowerUI.class).getSimpleName();
        USI_NOTIFICATION_ID = R.string.stylus_battery_low_percentage;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.stylus.StylusUsiPowerUI$receiver$1] */
    public StylusUsiPowerUI(Context context, NotificationManagerCompat notificationManagerCompat, InputManager inputManager, Handler handler, UiEventLogger uiEventLogger) {
        this.context = context;
        this.notificationManager = notificationManagerCompat;
        this.inputManager = inputManager;
        this.handler = handler;
        this.uiEventLogger = uiEventLogger;
    }

    public final InstanceId getInstanceId() {
        InstanceId instanceId = this.instanceId;
        if (instanceId == null) {
            if (instanceId == null) {
                instanceId = this.instanceIdSequence.newInstanceId();
            }
            this.instanceId = instanceId;
        }
        return this.instanceId;
    }

    public final void logUiEvent(StylusUiEvent stylusUiEvent) {
        this.uiEventLogger.logWithInstanceIdAndPosition(stylusUiEvent, ActivityManager.getCurrentUser(), this.context.getPackageName(), getInstanceId(), (int) (this.batteryCapacity * 100.0d));
    }

    public static /* synthetic */ void getInputDeviceId$annotations() {
    }

    public static /* synthetic */ void getInstanceIdSequence$annotations() {
    }

    public static /* synthetic */ void getReceiver$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
