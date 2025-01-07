package com.android.systemui.statusbar.policy;

import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Trace;
import com.android.internal.view.RotationPolicy;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.util.wrapper.RotationPolicyWrapperImpl;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationLockControllerImpl implements RotationLockController {
    public final CopyOnWriteArrayList mCallbacks;
    public final RotationPolicyWrapperImpl mRotationPolicy;
    public final AnonymousClass1 mRotationPolicyListener;

    /* JADX WARN: Type inference failed for: r3v6, types: [android.hardware.devicestate.DeviceStateManager$DeviceStateCallback, com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingController$$ExternalSyntheticLambda0] */
    public RotationLockControllerImpl(RotationPolicyWrapperImpl rotationPolicyWrapperImpl, final DeviceStateRotationLockSettingController deviceStateRotationLockSettingController, String[] strArr) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.mCallbacks = copyOnWriteArrayList;
        RotationPolicy.RotationPolicyListener rotationPolicyListener = new RotationPolicy.RotationPolicyListener() { // from class: com.android.systemui.statusbar.policy.RotationLockControllerImpl.1
            public final void onChange() {
                RotationLockControllerImpl rotationLockControllerImpl = RotationLockControllerImpl.this;
                Iterator it = rotationLockControllerImpl.mCallbacks.iterator();
                while (it.hasNext()) {
                    RotationLockController.RotationLockControllerCallback rotationLockControllerCallback = (RotationLockController.RotationLockControllerCallback) it.next();
                    RotationPolicyWrapperImpl rotationPolicyWrapperImpl2 = rotationLockControllerImpl.mRotationPolicy;
                    boolean isRotationLocked = RotationPolicy.isRotationLocked(rotationPolicyWrapperImpl2.context);
                    RotationPolicy.isRotationLockToggleVisible(rotationPolicyWrapperImpl2.context);
                    rotationLockControllerCallback.onRotationLockStateChanged(isRotationLocked);
                }
            }
        };
        this.mRotationPolicy = rotationPolicyWrapperImpl;
        boolean z = strArr.length > 0;
        if (z) {
            copyOnWriteArrayList.add(deviceStateRotationLockSettingController);
        }
        RotationPolicy.registerRotationPolicyListener(rotationPolicyWrapperImpl.context, rotationPolicyListener, -1);
        if (z) {
            DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger = deviceStateRotationLockSettingController.mLogger;
            LogLevel logLevel = LogLevel.VERBOSE;
            DeviceStateRotationLockSettingControllerLogger$logListeningChange$2 deviceStateRotationLockSettingControllerLogger$logListeningChange$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger$logListeningChange$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("setListening: ", ((LogMessage) obj).getBool1());
                }
            };
            LogBuffer logBuffer = deviceStateRotationLockSettingControllerLogger.logBuffer;
            LogMessage obtain = logBuffer.obtain("DSRotateLockSettingCon", logLevel, deviceStateRotationLockSettingControllerLogger$logListeningChange$2, null);
            ((LogMessageImpl) obtain).bool1 = true;
            logBuffer.commit(obtain);
            ?? r3 = new DeviceStateManager.DeviceStateCallback() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingController$$ExternalSyntheticLambda0
                public final void onDeviceStateChanged(DeviceState deviceState) {
                    DeviceStateRotationLockSettingController deviceStateRotationLockSettingController2 = DeviceStateRotationLockSettingController.this;
                    int i = deviceStateRotationLockSettingController2.mDeviceState;
                    int identifier = deviceState.getIdentifier();
                    final DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger2 = deviceStateRotationLockSettingController2.mLogger;
                    LogLevel logLevel2 = LogLevel.VERBOSE;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger$logUpdateDeviceState$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return "updateDeviceState: current=" + logMessage.getInt1() + " [" + DeviceStateRotationLockSettingControllerLogger.access$toDevicePostureString(DeviceStateRotationLockSettingControllerLogger.this, logMessage.getInt1()) + "], new=" + logMessage.getInt2() + " [" + DeviceStateRotationLockSettingControllerLogger.access$toDevicePostureString(DeviceStateRotationLockSettingControllerLogger.this, logMessage.getInt2()) + "]";
                        }
                    };
                    LogBuffer logBuffer2 = deviceStateRotationLockSettingControllerLogger2.logBuffer;
                    LogMessage obtain2 = logBuffer2.obtain("DSRotateLockSettingCon", logLevel2, function1, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
                    logMessageImpl.int1 = i;
                    logMessageImpl.int2 = identifier;
                    logBuffer2.commit(obtain2);
                    try {
                        if (Trace.isEnabled()) {
                            Trace.traceBegin(4096L, "updateDeviceState [state=" + deviceState.getIdentifier() + "]");
                        }
                        if (deviceStateRotationLockSettingController2.mDeviceState != deviceState.getIdentifier()) {
                            deviceStateRotationLockSettingController2.readPersistedSetting(deviceState.getIdentifier(), "updateDeviceState");
                        }
                        Trace.endSection();
                    } catch (Throwable th) {
                        Trace.endSection();
                        throw th;
                    }
                }
            };
            deviceStateRotationLockSettingController.mDeviceStateCallback = r3;
            deviceStateRotationLockSettingController.mDeviceStateManager.registerCallback(deviceStateRotationLockSettingController.mMainExecutor, (DeviceStateManager.DeviceStateCallback) r3);
            deviceStateRotationLockSettingController.mDeviceStateRotationLockSettingsManager.mListeners.add(new DeviceStateRotationLockSettingController$$ExternalSyntheticLambda1(deviceStateRotationLockSettingController));
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        RotationLockController.RotationLockControllerCallback rotationLockControllerCallback = (RotationLockController.RotationLockControllerCallback) obj;
        this.mCallbacks.add(rotationLockControllerCallback);
        RotationPolicyWrapperImpl rotationPolicyWrapperImpl = this.mRotationPolicy;
        boolean isRotationLocked = RotationPolicy.isRotationLocked(rotationPolicyWrapperImpl.context);
        RotationPolicy.isRotationLockToggleVisible(rotationPolicyWrapperImpl.context);
        rotationLockControllerCallback.onRotationLockStateChanged(isRotationLocked);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mCallbacks.remove((RotationLockController.RotationLockControllerCallback) obj);
    }
}
