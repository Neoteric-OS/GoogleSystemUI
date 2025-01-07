package com.google.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.BatteryController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryControllerImplGoogle$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatteryControllerImplGoogle f$0;

    public /* synthetic */ BatteryControllerImplGoogle$$ExternalSyntheticLambda0(BatteryControllerImplGoogle batteryControllerImplGoogle, int i) {
        this.$r8$classId = i;
        this.f$0 = batteryControllerImplGoogle;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        BatteryControllerImplGoogle batteryControllerImplGoogle = this.f$0;
        BatteryController.BatteryStateChangeCallback batteryStateChangeCallback = (BatteryController.BatteryStateChangeCallback) obj;
        switch (i) {
            case 0:
                batteryStateChangeCallback.onReverseChanged(batteryControllerImplGoogle.mRtxLevel, batteryControllerImplGoogle.mName, batteryControllerImplGoogle.mReverse);
                break;
            default:
                batteryStateChangeCallback.onExtremeBatterySaverChanged(batteryControllerImplGoogle.mExtremeSaver);
                break;
        }
    }
}
