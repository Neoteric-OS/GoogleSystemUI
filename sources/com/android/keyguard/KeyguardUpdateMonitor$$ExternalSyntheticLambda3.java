package com.android.keyguard;

import android.os.Trace;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger;
import com.android.systemui.log.core.LogLevel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardUpdateMonitor$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardUpdateMonitor f$0;

    public /* synthetic */ KeyguardUpdateMonitor$$ExternalSyntheticLambda3(KeyguardUpdateMonitor keyguardUpdateMonitor, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardUpdateMonitor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.f$0;
        switch (i) {
            case 0:
                KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger = keyguardUpdateMonitor.mLogger;
                keyguardUpdateMonitorLogger.getClass();
                keyguardUpdateMonitorLogger.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.ERROR, "Fp cancellation not received, transitioning to STOPPED", null);
                boolean z = keyguardUpdateMonitor.mFingerprintRunningState == 3;
                keyguardUpdateMonitor.mFingerprintRunningState = 0;
                keyguardUpdateMonitor.mFingerprintDetectRunning = false;
                if (!z) {
                    keyguardUpdateMonitor.updateFingerprintListeningState(1);
                    break;
                } else {
                    keyguardUpdateMonitor.updateFingerprintListeningState(2);
                    break;
                }
            case 1:
                int i2 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.updateFingerprintListeningState(2);
                break;
            case 2:
                int i3 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.getClass();
                Trace.beginSection("#startBiometricWatchdog");
                if (keyguardUpdateMonitor.mFpm != null) {
                    KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger2 = keyguardUpdateMonitor.mLogger;
                    keyguardUpdateMonitorLogger2.getClass();
                    keyguardUpdateMonitorLogger2.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.DEBUG, "Scheduling biometric watchdog for ".concat("fingerprint"), null);
                    keyguardUpdateMonitor.mFpm.scheduleWatchdog();
                }
                Trace.endSection();
                break;
            case 3:
                int i4 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.getClass();
                int defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
                ServiceState serviceStateForSubscriber = keyguardUpdateMonitor.mTelephonyManager.getServiceStateForSubscriber(defaultSubscriptionId);
                KeyguardUpdateMonitor.AnonymousClass13 anonymousClass13 = keyguardUpdateMonitor.mHandler;
                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(330, defaultSubscriptionId, 0, serviceStateForSubscriber));
                break;
            case 4:
                keyguardUpdateMonitor.mLogger.d("Retrying fingerprint listening after power pressed error.");
                keyguardUpdateMonitor.updateFingerprintListeningState(0);
                break;
            default:
                int i5 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.updateFingerprintListeningState(2);
                break;
        }
    }
}
