package com.google.android.systemui.elmyra.sensors.config;

import android.content.Context;
import android.os.PowerManager;
import android.util.TypedValue;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenStateAdjustment {
    public GestureConfiguration$$ExternalSyntheticLambda0 mCallback;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final PowerManager mPowerManager;
    public final float mScreenOffAdjustment;

    public ScreenStateAdjustment(Context context, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.elmyra.sensors.config.ScreenStateAdjustment.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onFinishedGoingToSleep(int i) {
                ScreenStateAdjustment screenStateAdjustment = ScreenStateAdjustment.this;
                GestureConfiguration$$ExternalSyntheticLambda0 gestureConfiguration$$ExternalSyntheticLambda0 = screenStateAdjustment.mCallback;
                if (gestureConfiguration$$ExternalSyntheticLambda0 != null) {
                    gestureConfiguration$$ExternalSyntheticLambda0.accept(screenStateAdjustment);
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onStartedWakingUp() {
                ScreenStateAdjustment screenStateAdjustment = ScreenStateAdjustment.this;
                GestureConfiguration$$ExternalSyntheticLambda0 gestureConfiguration$$ExternalSyntheticLambda0 = screenStateAdjustment.mCallback;
                if (gestureConfiguration$$ExternalSyntheticLambda0 != null) {
                    gestureConfiguration$$ExternalSyntheticLambda0.accept(screenStateAdjustment);
                }
            }
        };
        this.mKeyguardUpdateMonitorCallback = keyguardUpdateMonitorCallback;
        this.mPowerManager = powerManager;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(R.dimen.elmyra_screen_off_adjustment, typedValue, true);
        this.mScreenOffAdjustment = typedValue.getFloat();
        keyguardUpdateMonitor.registerCallback(keyguardUpdateMonitorCallback);
    }
}
