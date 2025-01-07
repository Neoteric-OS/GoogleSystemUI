package com.android.systemui.power;

import com.android.systemui.power.PowerUI;
import com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PowerUI$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PowerUI$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                PowerUI powerUI = (PowerUI) obj;
                powerUI.doSkinThermalEventListenerRegistration();
                powerUI.doUsbThermalEventListenerRegistration();
                break;
            default:
                PowerUI.Receiver receiver = (PowerUI.Receiver) obj;
                if (PowerUI.this.mPowerManager.isPowerSaveMode()) {
                    ((PowerNotificationWarningsGoogleImpl) PowerUI.this.mWarnings).dismissLowBatteryWarning();
                    break;
                }
                break;
        }
    }
}
