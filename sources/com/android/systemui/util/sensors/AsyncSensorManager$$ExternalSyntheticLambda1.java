package com.android.systemui.util.sensors;

import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AsyncSensorManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AsyncSensorManager f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ AsyncSensorManager$$ExternalSyntheticLambda1(AsyncSensorManager asyncSensorManager, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = asyncSensorManager;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AsyncSensorManager asyncSensorManager = this.f$0;
                asyncSensorManager.mInner.setOperationParameter((SensorAdditionalInfo) this.f$1);
                break;
            default:
                AsyncSensorManager asyncSensorManager2 = this.f$0;
                asyncSensorManager2.mInner.unregisterDynamicSensorCallback((SensorManager.DynamicSensorCallback) this.f$1);
                break;
        }
    }
}
