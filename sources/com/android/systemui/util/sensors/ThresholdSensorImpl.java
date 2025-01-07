package com.android.systemui.util.sensors;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThresholdSensorImpl implements ThresholdSensor {
    public static final boolean DEBUG = Log.isLoggable("ThresholdSensor", 3);
    public final ExecutionImpl mExecution;
    public Boolean mLastBelow;
    public boolean mPaused;
    public boolean mRegistered;
    public final Sensor mSensor;
    public final AsyncSensorManager mSensorManager;
    public String mTag;
    public final float mThreshold;
    public final float mThresholdLatch;
    public final List mListeners = new ArrayList();
    public final AnonymousClass1 mSensorEventListener = new SensorEventListener() { // from class: com.android.systemui.util.sensors.ThresholdSensorImpl.1
        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            ThresholdSensorImpl thresholdSensorImpl = ThresholdSensorImpl.this;
            final boolean z = f < thresholdSensorImpl.mThreshold;
            boolean z2 = f >= thresholdSensorImpl.mThresholdLatch;
            thresholdSensorImpl.logDebug$1("Sensor value: " + sensorEvent.values[0]);
            ThresholdSensorImpl thresholdSensorImpl2 = ThresholdSensorImpl.this;
            final long j = sensorEvent.timestamp;
            thresholdSensorImpl2.mExecution.assertIsMainThread();
            if (thresholdSensorImpl2.mRegistered) {
                Boolean bool = thresholdSensorImpl2.mLastBelow;
                if (bool != null) {
                    if (bool.booleanValue() && !z2) {
                        return;
                    }
                    if (!thresholdSensorImpl2.mLastBelow.booleanValue() && !z) {
                        return;
                    }
                }
                thresholdSensorImpl2.mLastBelow = Boolean.valueOf(z);
                thresholdSensorImpl2.logDebug$1("Alerting below: " + z);
                new ArrayList(thresholdSensorImpl2.mListeners).forEach(new Consumer() { // from class: com.android.systemui.util.sensors.ThresholdSensorImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((ThresholdSensor.Listener) obj).onThresholdCrossed(new ThresholdSensorEvent(j, z));
                    }
                });
            }
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    public int mSensorDelay = 3;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final ExecutionImpl mExecution;
        public final Resources mResources;
        public Sensor mSensor;
        public final AsyncSensorManager mSensorManager;
        public boolean mSensorSet;
        public float mThresholdLatchValue;
        public boolean mThresholdLatchValueSet;
        public boolean mThresholdSet;
        public float mThresholdValue;

        public Builder(Resources resources, AsyncSensorManager asyncSensorManager, ExecutionImpl executionImpl) {
            this.mResources = resources;
            this.mSensorManager = asyncSensorManager;
            this.mExecution = executionImpl;
        }

        public final ThresholdSensorImpl build() {
            if (!this.mSensorSet) {
                throw new IllegalStateException("A sensor was not successfully set.");
            }
            if (!this.mThresholdSet) {
                throw new IllegalStateException("A threshold was not successfully set.");
            }
            if (this.mThresholdValue > this.mThresholdLatchValue) {
                throw new IllegalStateException("Threshold must be less than or equal to Threshold Latch");
            }
            return new ThresholdSensorImpl(this.mSensorManager, this.mSensor, this.mExecution, this.mThresholdValue, this.mThresholdLatchValue);
        }

        public Sensor findSensorByType(String str, boolean z) {
            Sensor sensor = null;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (Sensor sensor2 : this.mSensorManager.getSensorList(-1)) {
                if (str.equals(sensor2.getStringType())) {
                    if (!z || sensor2.isWakeUpSensor()) {
                        return sensor2;
                    }
                    sensor = sensor2;
                }
            }
            return sensor;
        }

        public final void setThresholdLatchResourceId(int i) {
            try {
                this.mThresholdLatchValue = this.mResources.getFloat(i);
                this.mThresholdLatchValueSet = true;
            } catch (Resources.NotFoundException unused) {
            }
        }

        public final void setThresholdValue(float f) {
            this.mThresholdValue = f;
            this.mThresholdSet = true;
            if (this.mThresholdLatchValueSet) {
                return;
            }
            this.mThresholdLatchValue = f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BuilderFactory {
        public final ExecutionImpl mExecution;
        public final Resources mResources;
        public final AsyncSensorManager mSensorManager;

        public BuilderFactory(Resources resources, AsyncSensorManager asyncSensorManager, ExecutionImpl executionImpl) {
            this.mResources = resources;
            this.mSensorManager = asyncSensorManager;
            this.mExecution = executionImpl;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.util.sensors.ThresholdSensorImpl$1] */
    public ThresholdSensorImpl(AsyncSensorManager asyncSensorManager, Sensor sensor, ExecutionImpl executionImpl, float f, float f2) {
        this.mSensorManager = asyncSensorManager;
        this.mExecution = executionImpl;
        this.mSensor = sensor;
        this.mThreshold = f;
        this.mThresholdLatch = f2;
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final boolean isLoaded() {
        return this.mSensor != null;
    }

    public boolean isRegistered() {
        return this.mRegistered;
    }

    public final void logDebug$1(String str) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mTag != null ? ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("["), this.mTag, "] ") : "");
            sb.append(str);
            Log.d("ThresholdSensor", sb.toString());
        }
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void pause() {
        this.mExecution.assertIsMainThread();
        this.mPaused = true;
        unregisterInternal$1();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void register(ThresholdSensor.Listener listener) {
        this.mExecution.assertIsMainThread();
        if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
        registerInternal$1();
    }

    public final void registerInternal$1() {
        this.mExecution.assertIsMainThread();
        if (this.mRegistered || this.mPaused || this.mListeners.isEmpty()) {
            return;
        }
        logDebug$1("Registering sensor listener");
        this.mSensorManager.registerListener(this.mSensorEventListener, this.mSensor, this.mSensorDelay);
        this.mRegistered = true;
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void resume() {
        this.mExecution.assertIsMainThread();
        this.mPaused = false;
        registerInternal$1();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void setDelay() {
        if (1 == this.mSensorDelay) {
            return;
        }
        this.mSensorDelay = 1;
        if (isLoaded()) {
            unregisterInternal$1();
            registerInternal$1();
        }
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void setTag(String str) {
        this.mTag = str;
    }

    public final String toString() {
        boolean isLoaded = isLoaded();
        boolean z = this.mRegistered;
        boolean z2 = this.mPaused;
        Sensor sensor = this.mSensor;
        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("{isLoaded=", ", registered=", ", paused=", isLoaded, z);
        m.append(z2);
        m.append(", threshold=");
        m.append(this.mThreshold);
        m.append(", sensor=");
        m.append(sensor);
        m.append("}");
        return m.toString();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void unregister(ThresholdSensor.Listener listener) {
        this.mExecution.assertIsMainThread();
        this.mListeners.remove(listener);
        unregisterInternal$1();
    }

    public final void unregisterInternal$1() {
        this.mExecution.assertIsMainThread();
        if (this.mRegistered) {
            logDebug$1("Unregister sensor listener");
            this.mSensorManager.unregisterListener(this.mSensorEventListener);
            this.mRegistered = false;
            this.mLastBelow = null;
        }
    }
}
