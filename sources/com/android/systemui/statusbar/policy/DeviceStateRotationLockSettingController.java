package com.android.systemui.statusbar.policy;

import android.hardware.devicestate.DeviceStateManager;
import android.util.IndentingPrintWriter;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import com.android.internal.view.RotationPolicy;
import com.android.settingslib.devicestate.DeviceStateRotationLockSettingsManager;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.util.wrapper.RotationPolicyWrapperImpl;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceStateRotationLockSettingController implements RotationLockController.RotationLockControllerCallback, Dumpable {
    public int mDeviceState = -1;
    public DeviceStateRotationLockSettingController$$ExternalSyntheticLambda0 mDeviceStateCallback;
    public final DeviceStateManager mDeviceStateManager;
    public final DeviceStateRotationLockSettingsManager mDeviceStateRotationLockSettingsManager;
    public final DeviceStateRotationLockSettingControllerLogger mLogger;
    public final Executor mMainExecutor;
    public final RotationPolicyWrapperImpl mRotationPolicyWrapper;

    public DeviceStateRotationLockSettingController(RotationPolicyWrapperImpl rotationPolicyWrapperImpl, DeviceStateManager deviceStateManager, Executor executor, DeviceStateRotationLockSettingsManager deviceStateRotationLockSettingsManager, DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger, DumpManager dumpManager) {
        this.mRotationPolicyWrapper = rotationPolicyWrapperImpl;
        this.mDeviceStateManager = deviceStateManager;
        this.mMainExecutor = executor;
        this.mDeviceStateRotationLockSettingsManager = deviceStateRotationLockSettingsManager;
        this.mLogger = deviceStateRotationLockSettingControllerLogger;
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        DeviceStateRotationLockSettingsManager deviceStateRotationLockSettingsManager = this.mDeviceStateRotationLockSettingsManager;
        deviceStateRotationLockSettingsManager.getClass();
        indentingPrintWriter.println("DeviceStateRotationLockSettingsManager");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("mPostureRotationLockDefaults: " + Arrays.toString(deviceStateRotationLockSettingsManager.mPostureRotationLockDefaults));
        indentingPrintWriter.println("mPostureDefaultRotationLockSettings: " + deviceStateRotationLockSettingsManager.mPostureDefaultRotationLockSettings);
        indentingPrintWriter.println("mDeviceStateRotationLockSettings: " + deviceStateRotationLockSettingsManager.mPostureRotationLockSettings);
        indentingPrintWriter.println("mPostureRotationLockFallbackSettings: " + deviceStateRotationLockSettingsManager.mPostureRotationLockFallbackSettings);
        indentingPrintWriter.println("mSettableDeviceStates: " + deviceStateRotationLockSettingsManager.mSettableDeviceStates);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("DeviceStateRotationLockSettingController");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("mDeviceState: " + this.mDeviceState);
        indentingPrintWriter.decreaseIndent();
    }

    @Override // com.android.systemui.statusbar.policy.RotationLockController.RotationLockControllerCallback
    public final void onRotationLockStateChanged(boolean z) {
        int i = this.mDeviceState;
        DeviceStateRotationLockSettingsManager deviceStateRotationLockSettingsManager = this.mDeviceStateRotationLockSettingsManager;
        boolean z2 = deviceStateRotationLockSettingsManager.getRotationLockSetting(i) == 1;
        final DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger = this.mLogger;
        LogLevel logLevel = LogLevel.VERBOSE;
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger$logRotationLockStateChanged$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onRotationLockStateChanged: state=" + logMessage.getInt1() + " [" + DeviceStateRotationLockSettingControllerLogger.access$toDevicePostureString(DeviceStateRotationLockSettingControllerLogger.this, logMessage.getInt1()) + "], newRotationLocked=" + logMessage.getBool1() + ", currentRotationLocked=" + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = deviceStateRotationLockSettingControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("DSRotateLockSettingCon", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
        if (i == -1 || z == z2) {
            return;
        }
        int i2 = this.mDeviceState;
        LogMessage obtain2 = logBuffer.obtain("DSRotateLockSettingCon", logLevel, new Function1() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger$logSaveNewRotationLockSetting$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "saveNewRotationLockSetting: isRotationLocked=" + logMessage.getBool1() + ", state=" + logMessage.getInt1();
            }
        }, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.bool1 = z;
        logMessageImpl2.int1 = i2;
        logBuffer.commit(obtain2);
        int deviceStateToPosture = deviceStateRotationLockSettingsManager.mPosturesHelper.deviceStateToPosture(i2);
        if (deviceStateRotationLockSettingsManager.mPostureRotationLockFallbackSettings.indexOfKey(deviceStateToPosture) >= 0) {
            deviceStateToPosture = deviceStateRotationLockSettingsManager.mPostureRotationLockFallbackSettings.get(deviceStateToPosture);
        }
        deviceStateRotationLockSettingsManager.mPostureRotationLockSettings.put(deviceStateToPosture, z ? 1 : 2);
        deviceStateRotationLockSettingsManager.persistSettings();
    }

    public final void readPersistedSetting(int i, String str) {
        int rotationLockSetting = this.mDeviceStateRotationLockSettingsManager.getRotationLockSetting(i);
        boolean z = rotationLockSetting == 1;
        RotationPolicyWrapperImpl rotationPolicyWrapperImpl = this.mRotationPolicyWrapper;
        boolean isRotationLocked = RotationPolicy.isRotationLocked(rotationPolicyWrapperImpl.context);
        final DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger = this.mLogger;
        LogLevel logLevel = LogLevel.VERBOSE;
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger$readPersistedSetting$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                String access$toDevicePostureString = DeviceStateRotationLockSettingControllerLogger.access$toDevicePostureString(DeviceStateRotationLockSettingControllerLogger.this, logMessage.getInt1());
                int int2 = logMessage.getInt2();
                String str2 = int2 != 0 ? int2 != 1 ? int2 != 2 ? "Unknown" : "UNLOCKED" : "LOCKED" : "IGNORED";
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("readPersistedSetting: caller=", str1, ", state=", int1, " [");
                PlatformSliderColors$$ExternalSyntheticOutline0.m(m, access$toDevicePostureString, "], rotationLockSettingForState: ", str2, ", shouldBeLocked=");
                m.append(bool1);
                m.append(", isLocked=");
                m.append(bool2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = deviceStateRotationLockSettingControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("DSRotateLockSettingCon", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = rotationLockSetting;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = isRotationLocked;
        logBuffer.commit(obtain);
        if (rotationLockSetting == 0) {
            return;
        }
        this.mDeviceState = i;
        if (z != isRotationLocked) {
            rotationPolicyWrapperImpl.setRotationLock("DeviceStateRotationLockSettingController#readPersistedSetting", z);
        }
    }
}
