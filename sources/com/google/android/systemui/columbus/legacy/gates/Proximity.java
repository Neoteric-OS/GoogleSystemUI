package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorEvent;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Proximity extends Gate {
    public final Proximity$proximityListener$1 proximityListener = new ThresholdSensor.Listener() { // from class: com.google.android.systemui.columbus.legacy.gates.Proximity$proximityListener$1
        @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
        public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
            Proximity proximity = Proximity.this;
            proximity.setBlocking(Intrinsics.areEqual(((ProximitySensorImpl) ((ProximitySensor) proximity.proximitySensor.get())).isNear(), Boolean.TRUE));
        }
    };
    public final Lazy proximitySensor;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.Proximity$proximityListener$1] */
    public Proximity(Lazy lazy) {
        this.proximitySensor = lazy;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        Lazy lazy = this.proximitySensor;
        ((ProximitySensor) lazy.get()).register(this.proximityListener);
        setBlocking(Intrinsics.areEqual(((ProximitySensorImpl) ((ProximitySensor) lazy.get())).isNear(), Boolean.TRUE));
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        ((ProximitySensor) this.proximitySensor.get()).unregister(this.proximityListener);
    }
}
