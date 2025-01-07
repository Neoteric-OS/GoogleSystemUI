package com.android.systemui.power;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.PowerNotificationWarnings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PowerNotificationWarnings$$ExternalSyntheticLambda4 implements ActivityStarter.Callback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PowerNotificationWarnings$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // com.android.systemui.plugins.ActivityStarter.Callback
    public final void onActivityStarted(int i) {
        switch (this.$r8$classId) {
            case 0:
                ((PowerNotificationWarnings) this.f$0).mUsbHighTempDialog = null;
                break;
            case 1:
                ((PowerNotificationWarnings.AnonymousClass1) this.f$0).this$0.mHighTempDialog = null;
                break;
            default:
                ((PowerNotificationWarnings.AnonymousClass1) this.f$0).this$0.mThermalShutdownDialog = null;
                break;
        }
    }
}
