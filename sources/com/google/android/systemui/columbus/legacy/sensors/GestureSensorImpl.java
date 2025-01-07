package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.columbus.ColumbusEvent;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GestureSensorImpl extends GestureSensor {
    public final Sensor accelerometer;
    public final Sensor gyroscope;
    public final Handler handler;
    public boolean isListening;
    public final long samplingIntervalNs;
    public final GestureSensorEventListener sensorEventListener;
    public final SensorManager sensorManager;
    public final TapRT tap;
    public final UiEventLogger uiEventLogger;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00f2, code lost:
    
        if (r13.equals("Pixel 4") != false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GestureSensorImpl(android.content.Context r12, android.os.Handler r13, com.android.internal.logging.UiEventLogger r14) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.<init>(android.content.Context, android.os.Handler, com.android.internal.logging.UiEventLogger):void");
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final boolean isListening() {
        return this.isListening;
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void startListening() {
        this.sensorEventListener.setListening(true);
        this.tap.mLowpassAcc.getClass();
        this.tap.mLowpassGyro.getClass();
        this.tap.mHighpassAcc.setPara();
        this.tap.mHighpassGyro.setPara();
        TapRT tapRT = this.tap;
        PeakDetector peakDetector = tapRT.mPeakDetector;
        peakDetector.mMinNoiseTolerate = 0.03f;
        peakDetector.mWindowSize = 64;
        PeakDetector peakDetector2 = tapRT.mValleyDetector;
        peakDetector2.mMinNoiseTolerate = 0.015f;
        peakDetector2.mWindowSize = 64;
        tapRT.mAccXs.clear();
        tapRT.mAccYs.clear();
        tapRT.mAccZs.clear();
        tapRT.mGyroXs.clear();
        tapRT.mGyroYs.clear();
        tapRT.mGyroZs.clear();
        tapRT.mGotAcc = false;
        tapRT.mGotGyro = false;
        tapRT.mSyncTime = 0L;
        int i = tapRT.mNumberFeature;
        tapRT.mFeatureVector = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            tapRT.mFeatureVector.add(Float.valueOf(0.0f));
        }
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_HIGH_POWER_ACTIVE);
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void stopListening() {
        this.sensorEventListener.setListening(false);
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_INACTIVE);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class GestureSensorEventListener implements SensorEventListener {
        public GestureSensorEventListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0449  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0468  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x04a5  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x04b5  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x046b  */
        /* JADX WARN: Type inference failed for: r5v12 */
        /* JADX WARN: Type inference failed for: r5v17 */
        /* JADX WARN: Type inference failed for: r5v18 */
        /* JADX WARN: Type inference failed for: r5v19 */
        @Override // android.hardware.SensorEventListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onSensorChanged(android.hardware.SensorEvent r27) {
            /*
                Method dump skipped, instructions count: 1217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.GestureSensorEventListener.onSensorChanged(android.hardware.SensorEvent):void");
        }

        public final void setListening(boolean z) {
            GestureSensorImpl gestureSensorImpl;
            Sensor sensor;
            if (!z || (sensor = (gestureSensorImpl = GestureSensorImpl.this).accelerometer) == null || gestureSensorImpl.gyroscope == null) {
                GestureSensorImpl gestureSensorImpl2 = GestureSensorImpl.this;
                gestureSensorImpl2.sensorManager.unregisterListener(gestureSensorImpl2.sensorEventListener);
                GestureSensorImpl.this.isListening = false;
            } else {
                gestureSensorImpl.sensorManager.registerListener(gestureSensorImpl.sensorEventListener, sensor, 0, gestureSensorImpl.handler);
                GestureSensorImpl gestureSensorImpl3 = GestureSensorImpl.this;
                gestureSensorImpl3.sensorManager.registerListener(gestureSensorImpl3.sensorEventListener, gestureSensorImpl3.gyroscope, 0, gestureSensorImpl3.handler);
                GestureSensorImpl.this.isListening = true;
            }
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
