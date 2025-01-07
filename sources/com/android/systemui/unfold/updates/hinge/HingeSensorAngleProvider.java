package com.android.systemui.unfold.updates.hinge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import androidx.core.util.Consumer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HingeSensorAngleProvider implements HingeAngleProvider {
    public final Handler listenerHandler;
    public final SensorManager sensorManager;
    public final Executor singleThreadBgExecutor;
    public boolean started;
    public final HingeAngleSensorListener sensorListener = new HingeAngleSensorListener();
    public final List listeners = new CopyOnWriteArrayList();

    public HingeSensorAngleProvider(SensorManager sensorManager, Executor executor, Handler handler) {
        this.sensorManager = sensorManager;
        this.singleThreadBgExecutor = executor;
        this.listenerHandler = handler;
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        ((CopyOnWriteArrayList) this.listeners).add((Consumer) obj);
    }

    @Override // com.android.systemui.unfold.updates.hinge.HingeAngleProvider
    public final void start() {
        this.singleThreadBgExecutor.execute(new HingeSensorAngleProvider$stop$1(this, 1));
    }

    @Override // com.android.systemui.unfold.updates.hinge.HingeAngleProvider
    public final void stop() {
        this.singleThreadBgExecutor.execute(new HingeSensorAngleProvider$stop$1(this, 0));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HingeAngleSensorListener implements SensorEventListener {
        public HingeAngleSensorListener() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            Iterator it = HingeSensorAngleProvider.this.listeners.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(Float.valueOf(sensorEvent.values[0]));
            }
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
