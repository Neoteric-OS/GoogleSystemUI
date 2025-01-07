package com.android.systemui.power;

import android.content.DialogInterface;
import com.android.systemui.volume.Events;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PowerNotificationWarnings$$ExternalSyntheticLambda3 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PowerNotificationWarnings f$0;

    public /* synthetic */ PowerNotificationWarnings$$ExternalSyntheticLambda3(PowerNotificationWarnings powerNotificationWarnings, int i) {
        this.$r8$classId = i;
        this.f$0 = powerNotificationWarnings;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i = this.$r8$classId;
        PowerNotificationWarnings powerNotificationWarnings = this.f$0;
        switch (i) {
            case 0:
                powerNotificationWarnings.mUsbHighTempDialog = null;
                Events.writeEvent(20, 9, Boolean.valueOf(powerNotificationWarnings.mKeyguard.isKeyguardLocked()));
                break;
            case 1:
                powerNotificationWarnings.mSaverConfirmation = null;
                powerNotificationWarnings.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.SAVER_CONFIRM_DISMISS);
                break;
            case 2:
                powerNotificationWarnings.mThermalShutdownDialog = null;
                break;
            default:
                powerNotificationWarnings.mHighTempDialog = null;
                break;
        }
    }
}
