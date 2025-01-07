package com.google.android.systemui.biometrics;

import android.hardware.biometrics.BiometricSourceType;
import android.hardware.biometrics.events.AuthenticationAcquiredInfo;
import android.hardware.biometrics.events.AuthenticationErrorInfo;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.shared.model.FingerprintSensorType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeviceEntryUnlockEvent {
    public final DeviceEntryUnlockStage stage;
    public final BiometricSourceType type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Acquired extends DeviceEntryUnlockEvent {
        public final AuthenticationAcquiredInfo authInfo;

        public Acquired(BiometricSourceType biometricSourceType, AuthenticationAcquiredInfo authenticationAcquiredInfo) {
            super(DeviceEntryUnlockStage.HAL_ACQUISITION, biometricSourceType);
            this.authInfo = authenticationAcquiredInfo;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Error extends DeviceEntryUnlockEvent {
        public final AuthenticationErrorInfo authInfo;

        public Error(BiometricSourceType biometricSourceType, AuthenticationErrorInfo authenticationErrorInfo) {
            super(DeviceEntryUnlockStage.CANCELED, biometricSourceType);
            this.authInfo = authenticationErrorInfo;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExitKeyguard extends DeviceEntryUnlockEvent {
        public final ExitKeyguardInfo info;
        public boolean unlockToLauncher;

        public ExitKeyguard(ExitKeyguardInfo exitKeyguardInfo) {
            super(DeviceEntryUnlockStage.EXIT_KEYGUARD, null);
            this.info = exitKeyguardInfo;
            this.unlockToLauncher = false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExitKeyguardInfo {
        public final boolean isWakeAndUnlockNotFromDream;
        public final boolean playingCannedAnimation;

        public ExitKeyguardInfo(boolean z, boolean z2) {
            this.playingCannedAnimation = z;
            this.isWakeAndUnlockNotFromDream = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ExitKeyguardInfo)) {
                return false;
            }
            ExitKeyguardInfo exitKeyguardInfo = (ExitKeyguardInfo) obj;
            return this.playingCannedAnimation == exitKeyguardInfo.playingCannedAnimation && this.isWakeAndUnlockNotFromDream == exitKeyguardInfo.isWakeAndUnlockNotFromDream;
        }

        public final int hashCode() {
            return Long.hashCode(633L) + Scale$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.playingCannedAnimation) * 31, 31, this.isWakeAndUnlockNotFromDream), 31, 25L);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ExitKeyguardInfo(playingCannedAnimation=");
            sb.append(this.playingCannedAnimation);
            sb.append(", isWakeAndUnlockNotFromDream=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isWakeAndUnlockNotFromDream, ", unlockAnimationStartDelay=25, unlockAnimationDuration=633)");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Started extends DeviceEntryUnlockEvent {
        public FingerprintSensorType fpsSensorType;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Stopped extends DeviceEntryUnlockEvent {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Succeeded extends DeviceEntryUnlockEvent {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unlocked extends DeviceEntryUnlockEvent {
    }

    public DeviceEntryUnlockEvent(DeviceEntryUnlockStage deviceEntryUnlockStage, BiometricSourceType biometricSourceType) {
        this.stage = deviceEntryUnlockStage;
        this.type = biometricSourceType;
    }
}
