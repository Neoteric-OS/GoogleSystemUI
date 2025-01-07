package com.android.systemui.util.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AsyncSensorManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AsyncSensorManager f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ AsyncSensorManager$$ExternalSyntheticLambda4(AsyncSensorManager asyncSensorManager, Object obj, Object obj2, int i) {
        this.$r8$classId = i;
        this.f$0 = asyncSensorManager;
        this.f$1 = obj;
        this.f$2 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AsyncSensorManager asyncSensorManager = this.f$0;
                Sensor sensor = (Sensor) this.f$1;
                SensorEventListener sensorEventListener = (SensorEventListener) this.f$2;
                if (sensor != null) {
                    asyncSensorManager.mInner.unregisterListener(sensorEventListener, sensor);
                    break;
                } else {
                    asyncSensorManager.mInner.unregisterListener(sensorEventListener);
                    break;
                }
            default:
                AsyncSensorManager asyncSensorManager2 = this.f$0;
                asyncSensorManager2.mInner.registerDynamicSensorCallback((SensorManager.DynamicSensorCallback) this.f$1, (Handler) this.f$2);
                break;
        }
    }
}
