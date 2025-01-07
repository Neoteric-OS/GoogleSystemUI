package com.android.keyguard.logging;

import android.content.Intent;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.hardware.biometrics.BiometricSourceType;
import android.os.PowerManager;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardListenModel;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.TrustGrantFlags;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardUpdateMonitorLogger {
    public final LogBuffer logBuffer;

    public KeyguardUpdateMonitorLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void allowFingerprintOnCurrentOccludingActivityChanged(boolean z) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2 keyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("allowFingerprintOnCurrentOccludingActivityChanged: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void d(String str) {
        this.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.DEBUG, str, null);
    }

    public final void logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig$2 keyguardUpdateMonitorLogger$logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Skip requesting active unlock from wake reason that doesn't trigger face auth reason=", PowerManager.wakeReasonToString(((LogMessage) obj).getInt1()));
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("ActiveUnlock", logLevel, keyguardUpdateMonitorLogger$logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logActiveUnlockTriggered(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logActiveUnlockTriggered$2 keyguardUpdateMonitorLogger$logActiveUnlockTriggered$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logActiveUnlockTriggered$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("initiate active unlock triggerReason=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("ActiveUnlock", logLevel, keyguardUpdateMonitorLogger$logActiveUnlockTriggered$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logAssistantVisible(boolean z) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logAssistantVisible$2 keyguardUpdateMonitorLogger$logAssistantVisible$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logAssistantVisible$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Updating mAssistantVisible to new value: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logAssistantVisible$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logAuthInterruptDetected(boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logAuthInterruptDetected$2 keyguardUpdateMonitorLogger$logAuthInterruptDetected$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logAuthInterruptDetected$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return "onAuthInterruptDetected(" + ((LogMessage) obj).getBool1() + ")";
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logAuthInterruptDetected$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logBroadcastReceived(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logBroadcastReceived$2 keyguardUpdateMonitorLogger$logBroadcastReceived$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logBroadcastReceived$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("received broadcast ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logBroadcastReceived$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logDeviceProvisionedState(boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logDeviceProvisionedState$2 keyguardUpdateMonitorLogger$logDeviceProvisionedState$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logDeviceProvisionedState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("DEVICE_PROVISIONED state = ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logDeviceProvisionedState$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logException(Exception exc, final String str) {
        LogLevel logLevel = LogLevel.ERROR;
        Function1 function1 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logException$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return str;
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        logBuffer.commit(logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, function1, exc));
    }

    public final void logFaceAuthError(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFaceAuthError$2 keyguardUpdateMonitorLogger$logFaceAuthError$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFaceAuthError$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Face error received: ", logMessage.getStr1(), " msgId= ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logFaceAuthError$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFaceAuthForWrongUser(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFaceAuthForWrongUser$2 keyguardUpdateMonitorLogger$logFaceAuthForWrongUser$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFaceAuthForWrongUser$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Face authenticated for wrong user: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logFaceAuthForWrongUser$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFaceAuthSuccess(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFaceAuthSuccess$2 keyguardUpdateMonitorLogger$logFaceAuthSuccess$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFaceAuthSuccess$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Face auth succeeded for user ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logFaceAuthSuccess$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFaceDetected(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFaceDetected$2 keyguardUpdateMonitorLogger$logFaceDetected$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFaceDetected$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Face detected: userId: " + logMessage.getInt1() + ", isStrongBiometric: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logFaceDetected$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintAcquired(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintAcquired$2 keyguardUpdateMonitorLogger$logFingerprintAcquired$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintAcquired$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "fingerprint acquire message: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintAcquired$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintAuthForWrongUser(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintAuthForWrongUser$2 keyguardUpdateMonitorLogger$logFingerprintAuthForWrongUser$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintAuthForWrongUser$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Fingerprint authenticated for wrong user: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintAuthForWrongUser$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintDetected(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintDetected$2 keyguardUpdateMonitorLogger$logFingerprintDetected$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintDetected$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Fingerprint detected: userId: " + logMessage.getInt1() + ", isStrongBiometric: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintDetected$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintDisabledForUser(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintDisabledForUser$2 keyguardUpdateMonitorLogger$logFingerprintDisabledForUser$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintDisabledForUser$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Fingerprint disabled by DPM for userId: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintDisabledForUser$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintError(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintError$2 keyguardUpdateMonitorLogger$logFingerprintError$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintError$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Fingerprint error received: ", logMessage.getStr1(), " msgId= ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintError$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintLockoutReset(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintLockoutReset$2 keyguardUpdateMonitorLogger$logFingerprintLockoutReset$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintLockoutReset$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "handleFingerprintLockoutReset: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintLockoutReset$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintRunningState(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintRunningState$2 keyguardUpdateMonitorLogger$logFingerprintRunningState$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintRunningState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "fingerprintRunningState: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintRunningState$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logFingerprintSuccess(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logFingerprintSuccess$2 keyguardUpdateMonitorLogger$logFingerprintSuccess$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logFingerprintSuccess$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Fingerprint auth successful: userId: " + logMessage.getInt1() + ", isStrongBiometric: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardFingerprintLog", logLevel, keyguardUpdateMonitorLogger$logFingerprintSuccess$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logForceIsDismissibleKeyguard(boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logForceIsDismissibleKeyguard$2 keyguardUpdateMonitorLogger$logForceIsDismissibleKeyguard$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logForceIsDismissibleKeyguard$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("keepUnlockedOnFold changed to: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logForceIsDismissibleKeyguard$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logHandleBatteryUpdate(BatteryStatus batteryStatus) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logHandleBatteryUpdate$2 keyguardUpdateMonitorLogger$logHandleBatteryUpdate$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logHandleBatteryUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                int int1 = logMessage.getInt1();
                long long1 = logMessage.getLong1();
                String str1 = logMessage.getStr1();
                int int2 = logMessage.getInt2();
                long long2 = logMessage.getLong2();
                StringBuilder sb = new StringBuilder("handleBatteryUpdate: isNotNull: ");
                sb.append(bool1);
                sb.append(" BatteryStatus{status= ");
                sb.append(int1);
                sb.append(", level=");
                sb.append(long1);
                sb.append(", plugged=");
                sb.append(str1);
                sb.append(", chargingStatus=");
                sb.append(int2);
                sb.append(", maxChargingWattage= ");
                return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(long2, "}", sb);
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logHandleBatteryUpdate$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = true;
        logMessageImpl.int1 = batteryStatus.status;
        logMessageImpl.int2 = batteryStatus.chargingStatus;
        logMessageImpl.long1 = batteryStatus.level;
        logMessageImpl.long2 = batteryStatus.maxChargingWattage;
        logMessageImpl.str1 = String.valueOf(batteryStatus.plugged);
        logBuffer.commit(obtain);
    }

    public final void logHandlerHasAuthContinueMsgs(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logHandlerHasAuthContinueMsgs$2 keyguardUpdateMonitorLogger$logHandlerHasAuthContinueMsgs$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logHandlerHasAuthContinueMsgs$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "MSG_BIOMETRIC_AUTHENTICATION_CONTINUE already queued up, ignoring updating FP listening state to ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logHandlerHasAuthContinueMsgs$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logInvalidSubId(int i) {
        LogLevel logLevel = LogLevel.INFO;
        KeyguardUpdateMonitorLogger$logInvalidSubId$2 keyguardUpdateMonitorLogger$logInvalidSubId$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logInvalidSubId$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GenericDocument$$ExternalSyntheticOutline0.m("Previously active sub id ", " is now invalid, will remove", ((LogMessage) obj).getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logInvalidSubId$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logKeyguardListenerModel(KeyguardListenModel keyguardListenModel) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logKeyguardListenerModel$2 keyguardUpdateMonitorLogger$logKeyguardListenerModel$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logKeyguardListenerModel$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str1 = ((LogMessage) obj).getStr1();
                Intrinsics.checkNotNull(str1);
                return str1;
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logKeyguardListenerModel$2, null);
        ((LogMessageImpl) obtain).str1 = String.valueOf(keyguardListenModel);
        logBuffer.commit(obtain);
    }

    public final void logKeyguardShowingChanged(boolean z, boolean z2, boolean z3) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logKeyguardShowingChanged$2 keyguardUpdateMonitorLogger$logKeyguardShowingChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logKeyguardShowingChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("keyguardShowingChanged(showing=", " occluded=", " visible=", bool1, bool2), logMessage.getBool3(), ")");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logKeyguardShowingChanged$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logBuffer.commit(obtain);
    }

    public final void logKeyguardStateUpdate(boolean z, boolean z2, boolean z3, boolean z4) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logKeyguardStateUpdate$2 keyguardUpdateMonitorLogger$logKeyguardStateUpdate$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logKeyguardStateUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("#update secure=", " canDismissKeyguard=", " trusted=", bool1, bool2);
                m.append(bool3);
                m.append(" trustManaged=");
                m.append(bool4);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardState", logLevel, keyguardUpdateMonitorLogger$logKeyguardStateUpdate$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logMessageImpl.bool4 = z4;
        logBuffer.commit(obtain);
    }

    public final void logMissingSupervisorAppError(int i) {
        LogLevel logLevel = LogLevel.ERROR;
        KeyguardUpdateMonitorLogger$logMissingSupervisorAppError$2 keyguardUpdateMonitorLogger$logMissingSupervisorAppError$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logMissingSupervisorAppError$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "No Profile Owner or Device Owner supervision app found for User ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logMissingSupervisorAppError$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logPhoneStateChanged(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logPhoneStateChanged$2 keyguardUpdateMonitorLogger$logPhoneStateChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logPhoneStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("handlePhoneStateChanged(", ((LogMessage) obj).getStr1(), ")");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logPhoneStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logPrimaryKeyguardBouncerChanged(boolean z, boolean z2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logPrimaryKeyguardBouncerChanged$2 keyguardUpdateMonitorLogger$logPrimaryKeyguardBouncerChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logPrimaryKeyguardBouncerChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "handlePrimaryBouncerChanged primaryBouncerIsOrWillBeShowing=" + logMessage.getBool1() + " primaryBouncerFullyShown=" + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logPrimaryKeyguardBouncerChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        ((LogMessageImpl) obtain).bool2 = z2;
        logBuffer.commit(obtain);
    }

    public final void logRegisterCallback(KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logRegisterCallback$2 keyguardUpdateMonitorLogger$logRegisterCallback$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logRegisterCallback$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("*** register callback for ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logRegisterCallback$2, null);
        ((LogMessageImpl) obtain).str1 = String.valueOf(keyguardUpdateMonitorCallback);
        logBuffer.commit(obtain);
    }

    public final void logReportSuccessfulBiometricUnlock(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logReportSuccessfulBiometricUnlock$2 keyguardUpdateMonitorLogger$logReportSuccessfulBiometricUnlock$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logReportSuccessfulBiometricUnlock$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "reporting successful biometric unlock: isStrongBiometric: " + logMessage.getBool1() + ", userId: " + logMessage.getInt1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logReportSuccessfulBiometricUnlock$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logRetryAfterFpErrorWithDelay(String str, int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logRetryAfterFpErrorWithDelay$2 keyguardUpdateMonitorLogger$logRetryAfterFpErrorWithDelay$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logRetryAfterFpErrorWithDelay$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int int2 = logMessage.getInt2();
                int int1 = logMessage.getInt1();
                String str1 = logMessage.getStr1();
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int2, int1, "Fingerprint scheduling retry auth after ", " ms due to(", ") -> ");
                m.append(str1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logRetryAfterFpErrorWithDelay$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = i2;
        logMessageImpl.str1 = String.valueOf(str);
        logBuffer.commit(obtain);
    }

    public final void logRetryAfterFpHwUnavailable(int i) {
        LogLevel logLevel = LogLevel.WARNING;
        KeyguardUpdateMonitorLogger$logRetryAfterFpHwUnavailable$2 keyguardUpdateMonitorLogger$logRetryAfterFpHwUnavailable$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logRetryAfterFpHwUnavailable$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Retrying fingerprint attempt: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logRetryAfterFpHwUnavailable$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logSendPrimaryBouncerChanged(boolean z, boolean z2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logSendPrimaryBouncerChanged$2 keyguardUpdateMonitorLogger$logSendPrimaryBouncerChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logSendPrimaryBouncerChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "sendPrimaryBouncerChanged primaryBouncerIsOrWillBeShowing=" + logMessage.getBool1() + " primaryBouncerFullyShown=" + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logSendPrimaryBouncerChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        ((LogMessageImpl) obtain).bool2 = z2;
        logBuffer.commit(obtain);
    }

    public final void logServiceProvidersUpdated(Intent intent) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logServiceProvidersUpdated$2 keyguardUpdateMonitorLogger$logServiceProvidersUpdated$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logServiceProvidersUpdated$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "action SERVICE_PROVIDERS_UPDATED subId=" + logMessage.getInt1() + " spn=" + logMessage.getStr1() + " plmn=" + logMessage.getStr2();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logServiceProvidersUpdated$2, null);
        ((LogMessageImpl) obtain).int1 = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = intent.getStringExtra("android.telephony.extra.SPN");
        logMessageImpl.str2 = intent.getStringExtra("android.telephony.extra.PLMN");
        logBuffer.commit(obtain);
    }

    public final void logServiceStateChange(int i, ServiceState serviceState) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logServiceStateChange$2 keyguardUpdateMonitorLogger$logServiceStateChange$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logServiceStateChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "handleServiceStateChange(subId=" + logMessage.getInt1() + ", serviceState=" + logMessage.getStr1() + ")";
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logServiceStateChange$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).str1 = String.valueOf(serviceState);
        logBuffer.commit(obtain);
    }

    public final void logServiceStateIntent(String str, ServiceState serviceState, int i) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logServiceStateIntent$2 keyguardUpdateMonitorLogger$logServiceStateIntent$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logServiceStateIntent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                int int1 = logMessage.getInt1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("action ", str1, " serviceState=", str2, " subId=");
                m.append(int1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logServiceStateIntent$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        String valueOf = String.valueOf(serviceState);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = valueOf;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logSimState(int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logSimState$2 keyguardUpdateMonitorLogger$logSimState$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logSimState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(logMessage.getLong1(), ")", ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(logMessage.getInt1(), logMessage.getInt2(), "handleSimStateChange(subId=", ", slotId=", ", state="));
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logSimState$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int2 = i2;
        logMessageImpl.long1 = i3;
        logBuffer.commit(obtain);
    }

    public final void logSimStateFromIntent(String str, int i, int i2, String str2) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logSimStateFromIntent$2 keyguardUpdateMonitorLogger$logSimStateFromIntent$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logSimStateFromIntent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str22 = logMessage.getStr2();
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("action ", str1, " state: ", str22, " slotId: ");
                m.append(int1);
                m.append(" subid: ");
                m.append(int2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logSimStateFromIntent$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = str2;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = i2;
        logBuffer.commit(obtain);
    }

    public final void logSimUnlocked(int i) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logSimUnlocked$2 keyguardUpdateMonitorLogger$logSimUnlocked$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logSimUnlocked$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GenericDocument$$ExternalSyntheticOutline0.m("reportSimUnlocked(subId=", ")", ((LogMessage) obj).getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logSimUnlocked$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logSubInfo(SubscriptionInfo subscriptionInfo) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logSubInfo$2 keyguardUpdateMonitorLogger$logSubInfo$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logSubInfo$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("SubInfo:", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logSubInfo$2, null);
        ((LogMessageImpl) obtain).str1 = String.valueOf(subscriptionInfo);
        logBuffer.commit(obtain);
    }

    public final void logTaskStackChangedForAssistant(boolean z) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logTaskStackChangedForAssistant$2 keyguardUpdateMonitorLogger$logTaskStackChangedForAssistant$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logTaskStackChangedForAssistant$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("TaskStackChanged for ACTIVITY_TYPE_ASSISTANT, assistant visible: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logTaskStackChangedForAssistant$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logTimeFormatChanged(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logTimeFormatChanged$2 keyguardUpdateMonitorLogger$logTimeFormatChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logTimeFormatChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("handleTimeFormatUpdate timeFormat=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logTimeFormatChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logTrustChanged(int i, boolean z, boolean z2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logTrustChanged$2 keyguardUpdateMonitorLogger$logTrustChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logTrustChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onTrustChanged[user=" + logMessage.getInt1() + "] wasTrusted=" + logMessage.getBool1() + " isNowTrusted=" + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logTrustChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool2 = z2;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logTrustGrantedWithFlags(int i, int i2, String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logTrustGrantedWithFlags$2 keyguardUpdateMonitorLogger$logTrustGrantedWithFlags$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logTrustGrantedWithFlags$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "trustGrantedWithFlags[user=" + logMessage.getInt2() + "] newlyUnlocked=" + logMessage.getBool1() + " flags=" + new TrustGrantFlags(logMessage.getInt1()) + " message=" + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logTrustGrantedWithFlags$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = z;
        logMessageImpl.int2 = i2;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logTrustUsuallyManagedUpdated(int i, String str, boolean z, boolean z2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logTrustUsuallyManagedUpdated$2 keyguardUpdateMonitorLogger$logTrustUsuallyManagedUpdated$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logTrustUsuallyManagedUpdated$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "trustUsuallyManaged changed for userId: " + logMessage.getInt1() + " old: " + logMessage.getBool1() + ", new: " + logMessage.getBool2() + " context: " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logTrustUsuallyManagedUpdated$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logUdfpsPointerDown(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUdfpsPointerDown$2 keyguardUpdateMonitorLogger$logUdfpsPointerDown$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUdfpsPointerDown$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "onUdfpsPointerDown, sensorId: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUdfpsPointerDown$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUdfpsPointerUp(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUdfpsPointerUp$2 keyguardUpdateMonitorLogger$logUdfpsPointerUp$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUdfpsPointerUp$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "onUdfpsPointerUp, sensorId: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUdfpsPointerUp$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUnexpectedFpCancellationSignalState(int i, boolean z) {
        LogLevel logLevel = LogLevel.ERROR;
        KeyguardUpdateMonitorLogger$logUnexpectedFpCancellationSignalState$2 keyguardUpdateMonitorLogger$logUnexpectedFpCancellationSignalState$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUnexpectedFpCancellationSignalState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Cancellation signal is not null, high chance of bug in fp auth lifecycle management. FP state: " + logMessage.getInt1() + ", unlockPossible: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUnexpectedFpCancellationSignalState$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logUnregisterCallback(KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback) {
        LogLevel logLevel = LogLevel.VERBOSE;
        KeyguardUpdateMonitorLogger$logUnregisterCallback$2 keyguardUpdateMonitorLogger$logUnregisterCallback$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUnregisterCallback$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("*** unregister callback for ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUnregisterCallback$2, null);
        ((LogMessageImpl) obtain).str1 = String.valueOf(keyguardUpdateMonitorCallback);
        logBuffer.commit(obtain);
    }

    public final void logUserRemoved(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserRemoved$2 keyguardUpdateMonitorLogger$logUserRemoved$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "userRemoved userId: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserRemoved$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUserRequestedUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin activeUnlockRequestOrigin, String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserRequestedUnlock$2 keyguardUpdateMonitorLogger$logUserRequestedUnlock$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserRequestedUnlock$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                boolean bool1 = logMessage.getBool1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("reportUserRequestedUnlock origin=", str1, " reason=", str2, " dismissKeyguard=");
                m.append(bool1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("ActiveUnlock", logLevel, keyguardUpdateMonitorLogger$logUserRequestedUnlock$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = activeUnlockRequestOrigin.name();
        logMessageImpl.str2 = str;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logUserStopped(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserStopped$2 keyguardUpdateMonitorLogger$logUserStopped$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserStopped$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "userStopped userId: " + logMessage.getInt1() + " isUnlocked: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserStopped$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logUserSwitchComplete(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserSwitchComplete$2 keyguardUpdateMonitorLogger$logUserSwitchComplete$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserSwitchComplete$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("userSwitchComplete: ", logMessage.getStr1(), ", userId: ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserSwitchComplete$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logUserSwitching(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserSwitching$2 keyguardUpdateMonitorLogger$logUserSwitching$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserSwitching$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("userCurrentlySwitching: ", logMessage.getStr1(), ", userId: ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserSwitching$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logUserUnlocked(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserUnlocked$2 keyguardUpdateMonitorLogger$logUserUnlocked$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserUnlocked$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "userUnlocked userId: ");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserUnlocked$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUserUnlockedInitialState(int i, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$logUserUnlockedInitialState$2 keyguardUpdateMonitorLogger$logUserUnlockedInitialState$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logUserUnlockedInitialState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "userUnlockedInitialState userId: " + logMessage.getInt1() + " isUnlocked: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$logUserUnlockedInitialState$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void notifyAboutEnrollmentsChanged(BiometricSourceType biometricSourceType) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardUpdateMonitorLogger$notifyAboutEnrollmentsChanged$2 keyguardUpdateMonitorLogger$notifyAboutEnrollmentsChanged$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardUpdateMonitorLogger$notifyAboutEnrollmentsChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("notifying about enrollments changed: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardUpdateMonitorLog", logLevel, keyguardUpdateMonitorLogger$notifyAboutEnrollmentsChanged$2, null);
        ((LogMessageImpl) obtain).str1 = String.valueOf(biometricSourceType);
        logBuffer.commit(obtain);
    }

    public final void v(String str) {
        this.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.VERBOSE, str, null);
    }
}
