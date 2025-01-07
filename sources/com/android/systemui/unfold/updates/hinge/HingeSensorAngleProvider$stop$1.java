package com.android.systemui.unfold.updates.hinge;

import android.hardware.Sensor;
import android.os.Trace;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HingeSensorAngleProvider$stop$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HingeSensorAngleProvider this$0;

    public /* synthetic */ HingeSensorAngleProvider$stop$1(HingeSensorAngleProvider hingeSensorAngleProvider, int i) {
        this.$r8$classId = i;
        this.this$0 = hingeSensorAngleProvider;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                HingeSensorAngleProvider hingeSensorAngleProvider = this.this$0;
                if (hingeSensorAngleProvider.started) {
                    hingeSensorAngleProvider.sensorManager.unregisterListener(hingeSensorAngleProvider.sensorListener);
                    this.this$0.started = false;
                    break;
                }
                break;
            default:
                if (!this.this$0.started) {
                    Trace.beginSection("HingeSensorAngleProvider#start");
                    Sensor defaultSensor = this.this$0.sensorManager.getDefaultSensor(36);
                    HingeSensorAngleProvider hingeSensorAngleProvider2 = this.this$0;
                    hingeSensorAngleProvider2.sensorManager.registerListener(hingeSensorAngleProvider2.sensorListener, defaultSensor, 0, hingeSensorAngleProvider2.listenerHandler);
                    Trace.endSection();
                    this.this$0.started = true;
                    break;
                }
                break;
        }
    }
}
