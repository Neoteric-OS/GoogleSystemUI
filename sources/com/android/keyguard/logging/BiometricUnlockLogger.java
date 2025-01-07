package com.android.keyguard.logging;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.hardware.biometrics.BiometricSourceType;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricUnlockLogger {
    public final LogBuffer logBuffer;

    public BiometricUnlockLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void deferringAuthenticationDueToSleep(int i, BiometricSourceType biometricSourceType, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$deferringAuthenticationDueToSleep$2 biometricUnlockLogger$deferringAuthenticationDueToSleep$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$deferringAuthenticationDueToSleep$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onBiometricAuthenticated, deferring auth: userId: " + logMessage.getInt1() + ", biometricSourceType: " + logMessage.getStr1() + ", goingToSleep: true, mPendingAuthentication != null: " + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$deferringAuthenticationDueToSleep$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        String name = biometricSourceType.name();
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = name;
        logMessageImpl.bool2 = z;
        logBuffer.commit(obtain);
    }

    public final void logCalculateModeForFingerprintUnlockingAllowed(boolean z, boolean z2, boolean z3) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2 biometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("calculateModeForFingerprint unlockingAllowed=true deviceInteractive=", " isKeyguardShowing=", " deviceDreaming=", bool1, bool2);
                m.append(bool3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logBuffer.commit(obtain);
    }

    public final void logCalculateModeForFingerprintUnlockingNotAllowed(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingNotAllowed$2 biometricUnlockLogger$logCalculateModeForFingerprintUnlockingNotAllowed$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingNotAllowed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                int int1 = logMessage.getInt1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                StringBuilder sb = new StringBuilder("calculateModeForFingerprint unlockingAllowed=false strongBiometric=");
                sb.append(bool1);
                sb.append(" strongAuthFlags=");
                sb.append(int1);
                sb.append(" nonStrongBiometricAllowed=");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, bool2, " deviceInteractive=", bool3, " isKeyguardShowing=");
                sb.append(bool4);
                return sb.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logCalculateModeForFingerprintUnlockingNotAllowed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logMessageImpl.bool4 = z4;
        logBuffer.commit(obtain);
    }

    public final void logCalculateModeForPassiveAuthUnlockingAllowed(boolean z, boolean z2, boolean z3, boolean z4) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingAllowed$2 biometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingAllowed$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingAllowed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("calculateModeForPassiveAuth unlockingAllowed=true deviceInteractive=", " isKeyguardShowing=", " deviceDreaming=", bool1, bool2);
                m.append(bool3);
                m.append(" bypass=");
                m.append(bool4);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingAllowed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logMessageImpl.bool4 = z4;
        logBuffer.commit(obtain);
    }

    public final void logCalculateModeForPassiveAuthUnlockingNotAllowed(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingNotAllowed$2 biometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingNotAllowed$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingNotAllowed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean z6 = logMessage.getInt1() == 1;
                int int2 = logMessage.getInt2();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                StringBuilder sb = new StringBuilder("calculateModeForPassiveAuth unlockingAllowed=false strongBiometric=");
                sb.append(z6);
                sb.append(" strongAuthFlags=");
                sb.append(int2);
                sb.append(" nonStrongBiometricAllowed=");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, bool1, " deviceInteractive=", bool2, " isKeyguardShowing=");
                sb.append(bool3);
                sb.append(" bypass=");
                sb.append(bool4);
                return sb.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logCalculateModeForPassiveAuthUnlockingNotAllowed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = z ? 1 : 0;
        logMessageImpl.int2 = i;
        logMessageImpl.bool1 = z2;
        logMessageImpl.bool2 = z3;
        logMessageImpl.bool3 = z4;
        logMessageImpl.bool4 = z5;
        logBuffer.commit(obtain);
    }

    public final void logStartWakeAndUnlock(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logStartWakeAndUnlock$2 biometricUnlockLogger$logStartWakeAndUnlock$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logStartWakeAndUnlock$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str;
                int int1 = ((LogMessage) obj).getInt1();
                switch (int1) {
                    case 0:
                        str = "MODE_NONE";
                        break;
                    case 1:
                        str = "MODE_WAKE_AND_UNLOCK";
                        break;
                    case 2:
                        str = "MODE_WAKE_AND_UNLOCK_PULSING";
                        break;
                    case 3:
                        str = "MODE_SHOW_BOUNCER";
                        break;
                    case 4:
                        str = "MODE_ONLY_WAKE";
                        break;
                    case 5:
                        str = "MODE_UNLOCK_COLLAPSING";
                        break;
                    case 6:
                        str = "MODE_WAKE_AND_UNLOCK_FROM_DREAM";
                        break;
                    case 7:
                        str = "MODE_DISMISS_BOUNCER";
                        break;
                    default:
                        str = GenericDocument$$ExternalSyntheticOutline0.m("UNKNOWN{", "}", int1);
                        break;
                }
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("startWakeAndUnlock(", str, ")");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logStartWakeAndUnlock$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUdfpsAttemptThresholdMet(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        BiometricUnlockLogger$logUdfpsAttemptThresholdMet$2 biometricUnlockLogger$logUdfpsAttemptThresholdMet$2 = new Function1() { // from class: com.android.keyguard.logging.BiometricUnlockLogger$logUdfpsAttemptThresholdMet$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "udfpsAttemptThresholdMet consecutiveFailedAttempts=");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BiometricUnlockLogger", logLevel, biometricUnlockLogger$logUdfpsAttemptThresholdMet$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }
}
