package com.android.systemui.doze;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.IndentingPrintWriter;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.util.sensors.AsyncSensorManager;
import com.android.systemui.util.settings.SystemSettingsImpl;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeScreenBrightness extends BroadcastReceiver implements DozeMachine.Part, SensorEventListener {
    public final int mDefaultDozeBrightness;
    public final float mDefaultDozeBrightnessFloat;
    public int mDevicePosture;
    public final AnonymousClass1 mDevicePostureCallback;
    public final DevicePostureController mDevicePostureController;
    public final DisplayManager mDisplayManager;
    public final DozeServiceHost mDozeHost;
    public final DozeLog mDozeLog;
    public final DozeParameters mDozeParameters;
    public final DozeMachine.Service mDozeService;
    public final Handler mHandler;
    public final Optional[] mLightSensorOptional;
    public boolean mRegistered;
    public final int mScreenBrightnessDim;
    public final float mScreenBrightnessDimFloat;
    public final float mScreenBrightnessMinimumDimAmountFloat;
    public final AsyncSensorManager mSensorManager;
    public final int[] mSensorToBrightness;
    public final float[] mSensorToBrightnessFloat;
    public final int[] mSensorToScrimOpacity;
    public final SystemSettingsImpl mSystemSettings;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public boolean mPaused = false;
    public boolean mScreenOff = false;
    public int mLastSensorValue = -1;
    public DozeMachine.State mState = DozeMachine.State.UNINITIALIZED;
    public int mDebugBrightnessBucket = -1;

    static {
        SystemProperties.getBoolean("debug.aod_brightness", false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.doze.DozeScreenBrightness$1, java.lang.Object] */
    public DozeScreenBrightness(Context context, DozeMachine.Service service, AsyncSensorManager asyncSensorManager, Optional[] optionalArr, DozeServiceHost dozeServiceHost, Handler handler, AlwaysOnDisplayPolicy alwaysOnDisplayPolicy, WakefulnessLifecycle wakefulnessLifecycle, DozeParameters dozeParameters, DevicePostureController devicePostureController, DozeLog dozeLog, SystemSettingsImpl systemSettingsImpl, DisplayManager displayManager) {
        ?? r0 = new DevicePostureController.Callback() { // from class: com.android.systemui.doze.DozeScreenBrightness.1
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i) {
                DozeScreenBrightness dozeScreenBrightness = DozeScreenBrightness.this;
                int i2 = dozeScreenBrightness.mDevicePosture;
                if (i2 != i) {
                    Optional[] optionalArr2 = dozeScreenBrightness.mLightSensorOptional;
                    if (optionalArr2.length < 2 || i >= optionalArr2.length) {
                        return;
                    }
                    Sensor sensor = (Sensor) optionalArr2[i2].get();
                    Sensor sensor2 = (Sensor) dozeScreenBrightness.mLightSensorOptional[i].get();
                    if (Objects.equals(sensor, sensor2)) {
                        dozeScreenBrightness.mDevicePosture = i;
                        return;
                    }
                    if (dozeScreenBrightness.mRegistered) {
                        dozeScreenBrightness.setLightSensorEnabled(false);
                        dozeScreenBrightness.mDevicePosture = i;
                        dozeScreenBrightness.setLightSensorEnabled(true);
                    } else {
                        dozeScreenBrightness.mDevicePosture = i;
                    }
                    DozeLog dozeLog2 = dozeScreenBrightness.mDozeLog;
                    int i3 = dozeScreenBrightness.mDevicePosture;
                    String str = "DozeScreenBrightness swap {" + sensor + "} => {" + sensor2 + "}, mRegistered=" + dozeScreenBrightness.mRegistered;
                    DozeLogger dozeLogger = dozeLog2.mLogger;
                    dozeLogger.getClass();
                    LogLevel logLevel = LogLevel.INFO;
                    DozeLogger$logPostureChanged$2 dozeLogger$logPostureChanged$2 = DozeLogger$logPostureChanged$2.INSTANCE;
                    LogBuffer logBuffer = dozeLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logPostureChanged$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.int1 = i3;
                    logMessageImpl.str1 = str;
                    logBuffer.commit(obtain);
                }
            }
        };
        this.mDevicePostureCallback = r0;
        this.mDozeService = service;
        this.mSensorManager = asyncSensorManager;
        this.mDisplayManager = displayManager;
        this.mLightSensorOptional = optionalArr;
        this.mDevicePostureController = devicePostureController;
        DevicePostureControllerImpl devicePostureControllerImpl = (DevicePostureControllerImpl) devicePostureController;
        this.mDevicePosture = devicePostureControllerImpl.getDevicePosture();
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mDozeParameters = dozeParameters;
        this.mDozeHost = dozeServiceHost;
        this.mHandler = handler;
        this.mDozeLog = dozeLog;
        this.mSystemSettings = systemSettingsImpl;
        this.mScreenBrightnessMinimumDimAmountFloat = context.getResources().getFloat(R.dimen.config_scrollFactor);
        this.mDefaultDozeBrightness = alwaysOnDisplayPolicy.defaultDozeBrightness;
        this.mDefaultDozeBrightnessFloat = displayManager.getDefaultDozeBrightness(context.getDisplayId());
        this.mScreenBrightnessDim = alwaysOnDisplayPolicy.dimBrightness;
        this.mScreenBrightnessDimFloat = alwaysOnDisplayPolicy.dimBrightnessFloat;
        this.mSensorToBrightness = alwaysOnDisplayPolicy.screenBrightnessArray;
        this.mSensorToBrightnessFloat = displayManager.getDozeBrightnessSensorValueToBrightness(context.getDisplayId());
        this.mSensorToScrimOpacity = alwaysOnDisplayPolicy.dimmingScrimArray;
        devicePostureControllerImpl.addCallback(r0);
    }

    public final int clampToDimBrightnessForScreenOff(int i) {
        return ((this.mDozeParameters.shouldClampToDimBrightness() || this.mWakefulnessLifecycle.mWakefulness == 3) && this.mState == DozeMachine.State.INITIALIZED && this.mWakefulnessLifecycle.mLastSleepReason == 2) ? Math.max(0, Math.min(i - ((int) Math.floor(this.mScreenBrightnessMinimumDimAmountFloat * 255.0f)), this.mScreenBrightnessDim)) : i;
    }

    public final float clampToDimBrightnessForScreenOffFloat(float f) {
        return ((this.mDozeParameters.shouldClampToDimBrightness() || this.mWakefulnessLifecycle.mWakefulness == 3) && this.mState == DozeMachine.State.INITIALIZED && this.mWakefulnessLifecycle.mLastSleepReason == 2) ? Math.max(0.0f, Math.min(f - this.mScreenBrightnessMinimumDimAmountFloat, this.mScreenBrightnessDimFloat)) : f;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void dump(PrintWriter printWriter) {
        printWriter.println("DozeScreenBrightness:");
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("registered=" + this.mRegistered);
        indentingPrintWriter.println("posture=" + DevicePostureController.devicePostureToString(this.mDevicePosture));
        indentingPrintWriter.println("sensorToBrightness=" + Arrays.toString(this.mSensorToBrightness));
        indentingPrintWriter.println("sensorToBrightnessFloat=" + Arrays.toString(this.mSensorToBrightnessFloat));
        indentingPrintWriter.println("sensorToScrimOpacity=" + Arrays.toString(this.mSensorToScrimOpacity));
        indentingPrintWriter.println("screenBrightnessDim=" + this.mScreenBrightnessDim);
        indentingPrintWriter.println("screenBrightnessDimFloat=" + this.mScreenBrightnessDimFloat);
        indentingPrintWriter.println("mDefaultDozeBrightness=" + this.mDefaultDozeBrightness);
        indentingPrintWriter.println("mDefaultDozeBrightnessFloat=" + this.mDefaultDozeBrightnessFloat);
        indentingPrintWriter.println("mLastSensorValue=" + this.mLastSensorValue);
        StringBuilder sb = new StringBuilder("shouldUseFloatBrightness()=");
        sb.append(this.mSensorToBrightnessFloat != null);
        indentingPrintWriter.println(sb.toString());
    }

    public final boolean isLightSensorPresent() {
        int i;
        Optional[] optionalArr = this.mLightSensorOptional;
        return (optionalArr == null || (i = this.mDevicePosture) >= optionalArr.length) ? optionalArr != null && optionalArr[0].isPresent() : optionalArr[i].isPresent();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.mDebugBrightnessBucket = intent.getIntExtra("brightness_bucket", -1);
        updateBrightnessAndReady(false);
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096L, "DozeScreenBrightness.onSensorChanged" + sensorEvent.values[0]);
        }
        try {
            if (this.mRegistered) {
                this.mLastSensorValue = (int) sensorEvent.values[0];
                updateBrightnessAndReady(false);
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void resetBrightnessToDefault() {
        if (this.mSensorToBrightnessFloat != null) {
            this.mDozeService.setDozeScreenBrightnessFloat(clampToDimBrightnessForScreenOffFloat(Math.min(this.mDefaultDozeBrightnessFloat, this.mDisplayManager.getBrightness(0))));
        } else {
            this.mDozeService.setDozeScreenBrightness(clampToDimBrightnessForScreenOff(Math.min(this.mDefaultDozeBrightness, this.mSystemSettings.getIntForUser("screen_brightness", Integer.MAX_VALUE, -2))));
        }
        this.mDozeHost.setAodDimmingScrim(0.0f);
    }

    public final void setLightSensorEnabled(boolean z) {
        int i;
        if (z && !this.mRegistered && isLightSensorPresent()) {
            AsyncSensorManager asyncSensorManager = this.mSensorManager;
            Optional[] optionalArr = this.mLightSensorOptional;
            this.mRegistered = asyncSensorManager.registerListener(this, (optionalArr == null || (i = this.mDevicePosture) >= optionalArr.length) ? null : (Sensor) optionalArr[i].get(), 3, this.mHandler);
            this.mLastSensorValue = -1;
            return;
        }
        if (z || !this.mRegistered) {
            return;
        }
        this.mSensorManager.unregisterListener(this);
        this.mRegistered = false;
        this.mLastSensorValue = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0020, code lost:
    
        if (r4 != 12) goto L24;
     */
    @Override // com.android.systemui.doze.DozeMachine.Part
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void transitionTo(com.android.systemui.doze.DozeMachine.State r4, com.android.systemui.doze.DozeMachine.State r5) {
        /*
            r3 = this;
            r3.mState = r5
            int r4 = r5.ordinal()
            r0 = 1
            r1 = 0
            if (r4 == r0) goto L3f
            r2 = 2
            if (r4 == r2) goto L38
            r2 = 3
            if (r4 == r2) goto L38
            r2 = 4
            if (r4 == r2) goto L34
            r2 = 5
            if (r4 == r2) goto L34
            r2 = 9
            if (r4 == r2) goto L27
            r2 = 10
            if (r4 == r2) goto L23
            r2 = 12
            if (r4 == r2) goto L34
            goto L42
        L23:
            r3.setLightSensorEnabled(r1)
            goto L42
        L27:
            r3.setLightSensorEnabled(r1)
            com.android.systemui.statusbar.policy.DevicePostureController r4 = r3.mDevicePostureController
            com.android.systemui.doze.DozeScreenBrightness$1 r2 = r3.mDevicePostureCallback
            com.android.systemui.statusbar.policy.DevicePostureControllerImpl r4 = (com.android.systemui.statusbar.policy.DevicePostureControllerImpl) r4
            r4.removeCallback(r2)
            goto L42
        L34:
            r3.setLightSensorEnabled(r0)
            goto L42
        L38:
            r3.setLightSensorEnabled(r1)
            r3.resetBrightnessToDefault()
            goto L42
        L3f:
            r3.resetBrightnessToDefault()
        L42:
            com.android.systemui.doze.DozeMachine$State r4 = com.android.systemui.doze.DozeMachine.State.FINISH
            if (r5 == r4) goto L65
            com.android.systemui.doze.DozeMachine$State r4 = com.android.systemui.doze.DozeMachine.State.DOZE
            if (r5 != r4) goto L4c
            r4 = r0
            goto L4d
        L4c:
            r4 = r1
        L4d:
            boolean r2 = r3.mScreenOff
            if (r2 == r4) goto L56
            r3.mScreenOff = r4
            r3.updateBrightnessAndReady(r0)
        L56:
            com.android.systemui.doze.DozeMachine$State r4 = com.android.systemui.doze.DozeMachine.State.DOZE_AOD_PAUSED
            if (r5 != r4) goto L5b
            goto L5c
        L5b:
            r0 = r1
        L5c:
            boolean r4 = r3.mPaused
            if (r4 == r0) goto L65
            r3.mPaused = r0
            r3.updateBrightnessAndReady(r1)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeScreenBrightness.transitionTo(com.android.systemui.doze.DozeMachine$State, com.android.systemui.doze.DozeMachine$State):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateBrightnessAndReady(boolean r10) {
        /*
            r9 = this;
            r0 = -1
            if (r10 != 0) goto Lb
            boolean r10 = r9.mRegistered
            if (r10 != 0) goto Lb
            int r10 = r9.mDebugBrightnessBucket
            if (r10 == r0) goto La4
        Lb:
            int r10 = r9.mDebugBrightnessBucket
            if (r10 != r0) goto L11
            int r10 = r9.mLastSensorValue
        L11:
            float[] r1 = r9.mSensorToBrightnessFloat
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L19
            r4 = r2
            goto L1a
        L19:
            r4 = r3
        L1a:
            r5 = -2
            java.lang.String r6 = "screen_brightness_mode"
            if (r4 == 0) goto L51
            if (r10 < 0) goto L28
            int r4 = r1.length
            if (r10 < r4) goto L25
            goto L28
        L25:
            r1 = r1[r10]
            goto L2a
        L28:
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
        L2a:
            r4 = 0
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r4 < 0) goto L31
            r4 = r2
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 == 0) goto L85
            com.android.systemui.doze.DozeMachine$Service r7 = r9.mDozeService
            com.android.systemui.util.settings.SystemSettingsImpl r8 = r9.mSystemSettings
            int r5 = r8.getIntForUser(r6, r3, r5)
            if (r5 != r2) goto L3f
            goto L49
        L3f:
            android.hardware.display.DisplayManager r2 = r9.mDisplayManager
            float r2 = r2.getBrightness(r3)
            float r1 = java.lang.Math.min(r1, r2)
        L49:
            float r1 = r9.clampToDimBrightnessForScreenOffFloat(r1)
            r7.setDozeScreenBrightnessFloat(r1)
            goto L85
        L51:
            if (r10 < 0) goto L5c
            int[] r1 = r9.mSensorToBrightness
            int r4 = r1.length
            if (r10 < r4) goto L59
            goto L5c
        L59:
            r1 = r1[r10]
            goto L5d
        L5c:
            r1 = r0
        L5d:
            if (r1 <= 0) goto L61
            r4 = r2
            goto L62
        L61:
            r4 = r3
        L62:
            if (r4 == 0) goto L85
            com.android.systemui.doze.DozeMachine$Service r7 = r9.mDozeService
            com.android.systemui.util.settings.SystemSettingsImpl r8 = r9.mSystemSettings
            int r6 = r8.getIntForUser(r6, r3, r5)
            if (r6 != r2) goto L6f
            goto L7e
        L6f:
            com.android.systemui.util.settings.SystemSettingsImpl r2 = r9.mSystemSettings
            java.lang.String r6 = "screen_brightness"
            r8 = 2147483647(0x7fffffff, float:NaN)
            int r2 = r2.getIntForUser(r6, r8, r5)
            int r1 = java.lang.Math.min(r1, r2)
        L7e:
            int r1 = r9.clampToDimBrightnessForScreenOff(r1)
            r7.setDozeScreenBrightness(r1)
        L85:
            boolean r1 = r9.isLightSensorPresent()
            if (r1 != 0) goto L8d
            r0 = r3
            goto L99
        L8d:
            if (r4 == 0) goto L99
            if (r10 < 0) goto L99
            int[] r1 = r9.mSensorToScrimOpacity
            int r2 = r1.length
            if (r10 < r2) goto L97
            goto L99
        L97:
            r0 = r1[r10]
        L99:
            if (r0 < 0) goto La4
            com.android.systemui.statusbar.phone.DozeServiceHost r9 = r9.mDozeHost
            float r10 = (float) r0
            r0 = 1132396544(0x437f0000, float:255.0)
            float r10 = r10 / r0
            r9.setAodDimmingScrim(r10)
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeScreenBrightness.updateBrightnessAndReady(boolean):void");
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }
}
