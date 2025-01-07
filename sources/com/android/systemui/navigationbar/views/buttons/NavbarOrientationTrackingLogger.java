package com.android.systemui.navigationbar.views.buttons;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.runtime.ComposerImpl$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavbarOrientationTrackingLogger {
    public final LogBuffer buffer;

    public NavbarOrientationTrackingLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logPrimaryAndSecondaryVisibility(String str, boolean z, boolean z2, boolean z3, int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        Function1 function1 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavbarOrientationTrackingLogger$logPrimaryAndSecondaryVisibility$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                NavbarOrientationTrackingLogger navbarOrientationTrackingLogger = NavbarOrientationTrackingLogger.this;
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                navbarOrientationTrackingLogger.getClass();
                int i3 = int2 - int1;
                if (i3 < 0) {
                    i3 += 4;
                }
                String str2 = i3 != 1 ? i3 != 2 ? i3 != 3 ? "0" : "270" : "180" : "90";
                int int12 = logMessage.getInt1();
                int int22 = logMessage.getInt2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Caller Method: ", str1, "\n\tNavbar Visible: ", bool1, "\n\tImmersive Mode: ");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool2, "\n\tSecondary Handle Visible: ", bool3, "\n\tDelta Rotation: ");
                ComposerImpl$$ExternalSyntheticOutline0.m(m, str2, "\n\tStarting QuickSwitch Rotation: ", int12, "\n\tCurrent Rotation: ");
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(m, int22, "\n");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("NavbarOrientationTracking", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logMessageImpl.int1 = i2;
        logMessageImpl.int2 = i;
        logBuffer.commit(obtain);
    }
}
