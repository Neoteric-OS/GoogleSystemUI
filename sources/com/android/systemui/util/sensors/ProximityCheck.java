package com.android.systemui.util.sensors;

import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.sensors.ThresholdSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProximityCheck implements Runnable {
    public final DelayableExecutor mDelayableExecutor;
    public final ProximityCheck$$ExternalSyntheticLambda1 mListener;
    public final ProximitySensor mSensor;
    public List mCallbacks = new ArrayList();
    public final AtomicBoolean mRegistered = new AtomicBoolean();

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.util.sensors.ProximityCheck$$ExternalSyntheticLambda1] */
    public ProximityCheck(ProximitySensor proximitySensor, DelayableExecutor delayableExecutor) {
        this.mSensor = proximitySensor;
        ((ProximitySensorImpl) proximitySensor).setTag("prox_check");
        this.mDelayableExecutor = delayableExecutor;
        this.mListener = new ThresholdSensor.Listener() { // from class: com.android.systemui.util.sensors.ProximityCheck$$ExternalSyntheticLambda1
            @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
            public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
                ProximityCheck proximityCheck = ProximityCheck.this;
                List list = proximityCheck.mCallbacks;
                proximityCheck.mCallbacks = new ArrayList();
                proximityCheck.mSensor.unregister(proximityCheck.mListener);
                proximityCheck.mRegistered.set(false);
                ((ArrayList) list).forEach(new ProximityCheck$$ExternalSyntheticLambda0(thresholdSensorEvent));
            }
        };
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.mSensor.unregister(this.mListener);
        this.mRegistered.set(false);
        List list = this.mCallbacks;
        this.mCallbacks = new ArrayList();
        this.mSensor.unregister(this.mListener);
        this.mRegistered.set(false);
        ((ArrayList) list).forEach(new ProximityCheck$$ExternalSyntheticLambda0(null));
    }
}
