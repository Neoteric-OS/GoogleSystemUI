package com.android.keyguard;

import android.hardware.biometrics.BiometricSourceType;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.KeyguardBiometricLockoutLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.io.PrintWriter;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBiometricLockoutLogger implements CoreStartable {
    public static final Companion Companion = null;
    public boolean encryptedOrLockdown;
    public boolean faceLockedOut;
    public boolean fingerprintLockedOut;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardBiometricLockoutLogger$mKeyguardUpdateMonitorCallback$1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onLockedOutStateChanged(BiometricSourceType biometricSourceType) {
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FINGERPRINT;
            KeyguardBiometricLockoutLogger keyguardBiometricLockoutLogger = KeyguardBiometricLockoutLogger.this;
            if (biometricSourceType == biometricSourceType2) {
                boolean isFingerprintLockedOut = keyguardBiometricLockoutLogger.keyguardUpdateMonitor.isFingerprintLockedOut();
                if (isFingerprintLockedOut && !keyguardBiometricLockoutLogger.fingerprintLockedOut) {
                    keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
                } else if (!isFingerprintLockedOut && keyguardBiometricLockoutLogger.fingerprintLockedOut) {
                    keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT_RESET, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
                }
                keyguardBiometricLockoutLogger.fingerprintLockedOut = isFingerprintLockedOut;
                return;
            }
            if (biometricSourceType == BiometricSourceType.FACE) {
                SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = keyguardBiometricLockoutLogger.keyguardUpdateMonitor.mFaceAuthInteractor;
                boolean z = systemUIDeviceEntryFaceAuthInteractor != null && ((Boolean) systemUIDeviceEntryFaceAuthInteractor.isLockedOut.getValue()).booleanValue();
                if (z && !keyguardBiometricLockoutLogger.faceLockedOut) {
                    keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
                } else if (!z && keyguardBiometricLockoutLogger.faceLockedOut) {
                    keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT_RESET, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
                }
                keyguardBiometricLockoutLogger.faceLockedOut = z;
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onStrongAuthStateChanged(int i) {
            KeyguardBiometricLockoutLogger keyguardBiometricLockoutLogger = KeyguardBiometricLockoutLogger.this;
            if (i != keyguardBiometricLockoutLogger.selectedUserInteractor.getSelectedUserId()) {
                return;
            }
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardBiometricLockoutLogger.keyguardUpdateMonitor;
            int strongAuthForUser = keyguardUpdateMonitor.mStrongAuthTracker.getStrongAuthForUser(i);
            boolean isEncryptedOrLockdown = keyguardUpdateMonitor.isEncryptedOrLockdown(i);
            if (isEncryptedOrLockdown && !keyguardBiometricLockoutLogger.encryptedOrLockdown) {
                keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_ENCRYPTED_OR_LOCKDOWN, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
            }
            keyguardBiometricLockoutLogger.encryptedOrLockdown = isEncryptedOrLockdown;
            boolean z = (strongAuthForUser & 64) != 0;
            if (z && !keyguardBiometricLockoutLogger.unattendedUpdate) {
                keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_UNATTENDED_UPDATE, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
            }
            keyguardBiometricLockoutLogger.unattendedUpdate = z;
            boolean z2 = ((strongAuthForUser & 16) == 0 && (strongAuthForUser & 128) == 0) ? false : true;
            if (z2 && !keyguardBiometricLockoutLogger.timeout) {
                keyguardBiometricLockoutLogger.uiEventLogger.log(KeyguardBiometricLockoutLogger.PrimaryAuthRequiredEvent.PRIMARY_AUTH_REQUIRED_TIMEOUT, keyguardBiometricLockoutLogger.sessionTracker.getSessionId(1));
            }
            keyguardBiometricLockoutLogger.timeout = z2;
        }
    };
    public final SelectedUserInteractor selectedUserInteractor;
    public final SessionTracker sessionTracker;
    public boolean timeout;
    public final UiEventLogger uiEventLogger;
    public boolean unattendedUpdate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PrimaryAuthRequiredEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ PrimaryAuthRequiredEvent[] $VALUES;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_ENCRYPTED_OR_LOCKDOWN;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT_RESET;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT_RESET;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_TIMEOUT;
        public static final PrimaryAuthRequiredEvent PRIMARY_AUTH_REQUIRED_UNATTENDED_UPDATE;
        private final int mId;

        static {
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT", 0, 924);
            PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT = primaryAuthRequiredEvent;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent2 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT_RESET", 1, 925);
            PRIMARY_AUTH_REQUIRED_FINGERPRINT_LOCKED_OUT_RESET = primaryAuthRequiredEvent2;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent3 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT", 2, 926);
            PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT = primaryAuthRequiredEvent3;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent4 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT_RESET", 3, 927);
            PRIMARY_AUTH_REQUIRED_FACE_LOCKED_OUT_RESET = primaryAuthRequiredEvent4;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent5 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_ENCRYPTED_OR_LOCKDOWN", 4, 928);
            PRIMARY_AUTH_REQUIRED_ENCRYPTED_OR_LOCKDOWN = primaryAuthRequiredEvent5;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent6 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_TIMEOUT", 5, 929);
            PRIMARY_AUTH_REQUIRED_TIMEOUT = primaryAuthRequiredEvent6;
            PrimaryAuthRequiredEvent primaryAuthRequiredEvent7 = new PrimaryAuthRequiredEvent("PRIMARY_AUTH_REQUIRED_UNATTENDED_UPDATE", 6, 931);
            PRIMARY_AUTH_REQUIRED_UNATTENDED_UPDATE = primaryAuthRequiredEvent7;
            PrimaryAuthRequiredEvent[] primaryAuthRequiredEventArr = {primaryAuthRequiredEvent, primaryAuthRequiredEvent2, primaryAuthRequiredEvent3, primaryAuthRequiredEvent4, primaryAuthRequiredEvent5, primaryAuthRequiredEvent6, primaryAuthRequiredEvent7};
            $VALUES = primaryAuthRequiredEventArr;
            EnumEntriesKt.enumEntries(primaryAuthRequiredEventArr);
        }

        public PrimaryAuthRequiredEvent(String str, int i, int i2) {
            this.mId = i2;
        }

        public static PrimaryAuthRequiredEvent valueOf(String str) {
            return (PrimaryAuthRequiredEvent) Enum.valueOf(PrimaryAuthRequiredEvent.class, str);
        }

        public static PrimaryAuthRequiredEvent[] values() {
            return (PrimaryAuthRequiredEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    public KeyguardBiometricLockoutLogger(UiEventLogger uiEventLogger, KeyguardUpdateMonitor keyguardUpdateMonitor, SessionTracker sessionTracker, SelectedUserInteractor selectedUserInteractor) {
        this.uiEventLogger = uiEventLogger;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.sessionTracker = sessionTracker;
        this.selectedUserInteractor = selectedUserInteractor;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  mFingerprintLockedOut=", this.fingerprintLockedOut, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  mFaceLockedOut=", this.faceLockedOut, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  mIsEncryptedOrLockdown=", this.encryptedOrLockdown, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  mIsUnattendedUpdate=", this.unattendedUpdate, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  mIsTimeout=", this.timeout, printWriter);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mKeyguardUpdateMonitorCallback.onStrongAuthStateChanged(this.selectedUserInteractor.getSelectedUserId());
        this.keyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
    }
}
