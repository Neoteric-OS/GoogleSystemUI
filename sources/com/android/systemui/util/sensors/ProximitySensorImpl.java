package com.android.systemui.util.sensors;

import android.os.Build;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ProximitySensorImpl implements ProximitySensor {
    public static final boolean DEBUG;
    public ExecutorImpl.ExecutionToken mCancelSecondaryRunnable;
    public final DelayableExecutor mDelayableExecutor;
    public int mDevicePosture;
    public final ExecutionImpl mExecution;
    ThresholdSensorEvent mLastEvent;
    public ThresholdSensorEvent mLastPrimaryEvent;
    protected boolean mPaused;
    public ThresholdSensor mPrimaryThresholdSensor;
    public boolean mRegistered;
    public ThresholdSensor mSecondaryThresholdSensor;
    public final Set mListeners = new CopyOnWriteArraySet();
    public String mTag = null;
    public final AtomicBoolean mAlerting = new AtomicBoolean();
    public boolean mInitializedListeners = false;
    public boolean mSecondarySafe = false;
    public final ProximitySensorImpl$$ExternalSyntheticLambda0 mPrimaryEventListener = new ThresholdSensor.Listener() { // from class: com.android.systemui.util.sensors.ProximitySensorImpl$$ExternalSyntheticLambda0
        @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
        public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
            ProximitySensorImpl proximitySensorImpl = ProximitySensorImpl.this;
            proximitySensorImpl.mExecution.assertIsMainThread();
            ThresholdSensorEvent thresholdSensorEvent2 = proximitySensorImpl.mLastPrimaryEvent;
            if (thresholdSensorEvent2 == null || thresholdSensorEvent.mBelow != thresholdSensorEvent2.mBelow) {
                proximitySensorImpl.mLastPrimaryEvent = thresholdSensorEvent;
                if (proximitySensorImpl.mSecondarySafe && proximitySensorImpl.mSecondaryThresholdSensor.isLoaded()) {
                    StringBuilder sb = new StringBuilder("Primary sensor reported ");
                    sb.append(thresholdSensorEvent.mBelow ? "near" : "far");
                    sb.append(". Checking secondary.");
                    proximitySensorImpl.logDebug(sb.toString());
                    if (proximitySensorImpl.mCancelSecondaryRunnable == null) {
                        proximitySensorImpl.mSecondaryThresholdSensor.resume();
                        return;
                    }
                    return;
                }
                if (!proximitySensorImpl.mSecondaryThresholdSensor.isLoaded()) {
                    proximitySensorImpl.logDebug("Primary sensor event: " + thresholdSensorEvent.mBelow + ". No secondary.");
                    proximitySensorImpl.onSensorEvent(thresholdSensorEvent);
                    return;
                }
                if (!thresholdSensorEvent.mBelow) {
                    proximitySensorImpl.onSensorEvent(thresholdSensorEvent);
                    return;
                }
                proximitySensorImpl.logDebug("Primary sensor event: " + thresholdSensorEvent.mBelow + ". Checking secondary.");
                ExecutorImpl.ExecutionToken executionToken = proximitySensorImpl.mCancelSecondaryRunnable;
                if (executionToken != null) {
                    executionToken.run();
                }
                proximitySensorImpl.mSecondaryThresholdSensor.resume();
            }
        }
    };
    public final AnonymousClass1 mSecondaryEventListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.util.sensors.ProximitySensorImpl$1, reason: invalid class name */
    public final class AnonymousClass1 implements ThresholdSensor.Listener {
        public AnonymousClass1() {
        }

        @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
        public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
            ThresholdSensorEvent thresholdSensorEvent2;
            ProximitySensorImpl proximitySensorImpl = ProximitySensorImpl.this;
            if (!proximitySensorImpl.mSecondarySafe && ((thresholdSensorEvent2 = proximitySensorImpl.mLastPrimaryEvent) == null || !thresholdSensorEvent2.mBelow || !thresholdSensorEvent.mBelow)) {
                proximitySensorImpl.chooseSensor();
                ThresholdSensorEvent thresholdSensorEvent3 = proximitySensorImpl.mLastPrimaryEvent;
                if (thresholdSensorEvent3 == null || !thresholdSensorEvent3.mBelow) {
                    ExecutorImpl.ExecutionToken executionToken = proximitySensorImpl.mCancelSecondaryRunnable;
                    if (executionToken != null) {
                        executionToken.run();
                        proximitySensorImpl.mCancelSecondaryRunnable = null;
                        return;
                    }
                    return;
                }
                proximitySensorImpl.mCancelSecondaryRunnable = proximitySensorImpl.mDelayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.util.sensors.ProximitySensorImpl$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProximitySensorImpl proximitySensorImpl2 = ProximitySensorImpl.this;
                        proximitySensorImpl2.mPrimaryThresholdSensor.pause();
                        proximitySensorImpl2.mSecondaryThresholdSensor.resume();
                    }
                }, 5000L);
            }
            proximitySensorImpl.logDebug("Secondary sensor event: " + thresholdSensorEvent.mBelow + ".");
            if (proximitySensorImpl.mPaused) {
                return;
            }
            proximitySensorImpl.onSensorEvent(thresholdSensorEvent);
        }
    }

    static {
        DEBUG = Log.isLoggable("ProxSensor", 3) || Build.IS_DEBUGGABLE;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.util.sensors.ProximitySensorImpl$$ExternalSyntheticLambda0] */
    public ProximitySensorImpl(ThresholdSensor thresholdSensor, ThresholdSensor thresholdSensor2, DelayableExecutor delayableExecutor, ExecutionImpl executionImpl) {
        this.mPrimaryThresholdSensor = thresholdSensor;
        this.mSecondaryThresholdSensor = thresholdSensor2;
        this.mDelayableExecutor = delayableExecutor;
        this.mExecution = executionImpl;
    }

    public final void alertListeners() {
        this.mExecution.assertIsMainThread();
        if (this.mAlerting.getAndSet(true)) {
            return;
        }
        final ThresholdSensorEvent thresholdSensorEvent = this.mLastEvent;
        if (thresholdSensorEvent != null) {
            ((CopyOnWriteArraySet) this.mListeners).forEach(new Consumer() { // from class: com.android.systemui.util.sensors.ProximitySensorImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ThresholdSensor.Listener) obj).onThresholdCrossed(ThresholdSensorEvent.this);
                }
            });
        }
        this.mAlerting.set(false);
    }

    public final void chooseSensor() {
        this.mExecution.assertIsMainThread();
        if (!this.mRegistered || this.mPaused || this.mListeners.isEmpty()) {
            return;
        }
        if (this.mSecondarySafe) {
            this.mSecondaryThresholdSensor.resume();
            this.mPrimaryThresholdSensor.pause();
        } else {
            this.mPrimaryThresholdSensor.resume();
            this.mSecondaryThresholdSensor.pause();
        }
    }

    @Override // com.android.systemui.util.sensors.ProximitySensor
    public void destroy() {
        pause();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final boolean isLoaded() {
        return this.mPrimaryThresholdSensor.isLoaded();
    }

    public final Boolean isNear() {
        ThresholdSensorEvent thresholdSensorEvent;
        if (!this.mPrimaryThresholdSensor.isLoaded() || (thresholdSensorEvent = this.mLastEvent) == null) {
            return null;
        }
        return Boolean.valueOf(thresholdSensorEvent.mBelow);
    }

    public final void logDebug(String str) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mTag != null ? ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("["), this.mTag, "] ") : "");
            sb.append(str);
            Log.d("ProxSensor", sb.toString());
        }
    }

    public final void onSensorEvent(ThresholdSensorEvent thresholdSensorEvent) {
        this.mExecution.assertIsMainThread();
        ThresholdSensorEvent thresholdSensorEvent2 = this.mLastEvent;
        if (thresholdSensorEvent2 == null || thresholdSensorEvent.mBelow != thresholdSensorEvent2.mBelow) {
            if (!this.mSecondarySafe && !thresholdSensorEvent.mBelow) {
                chooseSensor();
            }
            this.mLastEvent = thresholdSensorEvent;
            alertListeners();
        }
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void pause() {
        this.mExecution.assertIsMainThread();
        this.mPaused = true;
        unregisterInternal();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public void register(ThresholdSensor.Listener listener) {
        this.mExecution.assertIsMainThread();
        if (this.mPrimaryThresholdSensor.isLoaded()) {
            if (this.mListeners.contains(listener)) {
                logDebug("ProxListener registered multiple times: " + listener);
            } else {
                this.mListeners.add(listener);
            }
            registerInternal();
        }
    }

    public final void registerInternal() {
        this.mExecution.assertIsMainThread();
        if (this.mRegistered || this.mPaused || this.mListeners.isEmpty()) {
            return;
        }
        if (!this.mInitializedListeners) {
            this.mPrimaryThresholdSensor.pause();
            this.mSecondaryThresholdSensor.pause();
            this.mPrimaryThresholdSensor.register(this.mPrimaryEventListener);
            this.mSecondaryThresholdSensor.register(this.mSecondaryEventListener);
            this.mInitializedListeners = true;
        }
        this.mRegistered = true;
        chooseSensor();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void resume() {
        this.mExecution.assertIsMainThread();
        this.mPaused = false;
        registerInternal();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void setDelay() {
        this.mExecution.assertIsMainThread();
        this.mPrimaryThresholdSensor.setDelay();
        this.mSecondaryThresholdSensor.setDelay();
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public final void setTag(String str) {
        this.mTag = str;
        this.mPrimaryThresholdSensor.setTag(str + ":primary");
        this.mSecondaryThresholdSensor.setTag(str + ":secondary");
    }

    public String toString() {
        return String.format("{registered=%s, paused=%s, near=%s, posture=%s, primarySensor=%s, secondarySensor=%s secondarySafe=%s}", Boolean.valueOf(this.mRegistered), Boolean.valueOf(this.mPaused), isNear(), Integer.valueOf(this.mDevicePosture), this.mPrimaryThresholdSensor, this.mSecondaryThresholdSensor, Boolean.valueOf(this.mSecondarySafe));
    }

    @Override // com.android.systemui.util.sensors.ThresholdSensor
    public void unregister(ThresholdSensor.Listener listener) {
        this.mExecution.assertIsMainThread();
        this.mListeners.remove(listener);
        if (this.mListeners.isEmpty()) {
            unregisterInternal();
        }
    }

    public final void unregisterInternal() {
        this.mExecution.assertIsMainThread();
        if (this.mRegistered) {
            logDebug("unregistering sensor listener");
            this.mPrimaryThresholdSensor.pause();
            this.mSecondaryThresholdSensor.pause();
            ExecutorImpl.ExecutionToken executionToken = this.mCancelSecondaryRunnable;
            if (executionToken != null) {
                executionToken.run();
                this.mCancelSecondaryRunnable = null;
            }
            this.mLastPrimaryEvent = null;
            this.mLastEvent = null;
            this.mRegistered = false;
        }
    }
}
