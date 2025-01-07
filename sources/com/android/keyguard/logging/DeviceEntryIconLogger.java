package com.android.keyguard.logging;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryIconLogger {
    public final LogBuffer logBuffer;

    public DeviceEntryIconLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void logDeviceEntryUdfpsTouchOverlayShouldHandleTouches(boolean z, boolean z2, boolean z3, boolean z4) {
        LogLevel logLevel = LogLevel.DEBUG;
        DeviceEntryIconLogger$logDeviceEntryUdfpsTouchOverlayShouldHandleTouches$2 deviceEntryIconLogger$logDeviceEntryUdfpsTouchOverlayShouldHandleTouches$2 = new Function1() { // from class: com.android.keyguard.logging.DeviceEntryIconLogger$logDeviceEntryUdfpsTouchOverlayShouldHandleTouches$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool4 = logMessage.getBool4();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("shouldHandleTouches=", " canTouchDeviceEntryViewAlpha=", " alternateBouncerVisible=", bool4, bool1);
                m.append(bool2);
                m.append(" hideAffordancesRequest=");
                m.append(bool3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("DeviceEntryUdfpsTouchOverlay", logLevel, deviceEntryIconLogger$logDeviceEntryUdfpsTouchOverlayShouldHandleTouches$2, null);
        ((LogMessageImpl) obtain).bool1 = z2;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool2 = z3;
        logMessageImpl.bool3 = z4;
        logMessageImpl.bool4 = z;
        logBuffer.commit(obtain);
    }
}
