package com.android.systemui.biometrics.shared.model;

import android.hardware.biometrics.BiometricSourceType;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.shared.model.AuthenticationReason;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface AuthenticationState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Acquired implements AuthenticationState {
        public final int acquiredInfo;
        public final BiometricSourceType biometricSourceType;
        public final AuthenticationReason requestReason;

        public Acquired(BiometricSourceType biometricSourceType, AuthenticationReason authenticationReason, int i) {
            this.biometricSourceType = biometricSourceType;
            this.requestReason = authenticationReason;
            this.acquiredInfo = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Acquired)) {
                return false;
            }
            Acquired acquired = (Acquired) obj;
            return this.biometricSourceType == acquired.biometricSourceType && Intrinsics.areEqual(this.requestReason, acquired.requestReason) && this.acquiredInfo == acquired.acquiredInfo;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            return Integer.hashCode(this.acquiredInfo) + ((this.requestReason.hashCode() + (this.biometricSourceType.hashCode() * 31)) * 31);
        }

        public final String toString() {
            BiometricSourceType biometricSourceType = this.biometricSourceType;
            StringBuilder sb = new StringBuilder("Acquired(biometricSourceType=");
            sb.append(biometricSourceType);
            sb.append(", requestReason=");
            sb.append(this.requestReason);
            sb.append(", acquiredInfo=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.acquiredInfo, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Error implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;
        public final int errCode;
        public final String errString;
        public final AuthenticationReason requestReason;

        public Error(BiometricSourceType biometricSourceType, String str, int i, AuthenticationReason authenticationReason) {
            this.biometricSourceType = biometricSourceType;
            this.errString = str;
            this.errCode = i;
            this.requestReason = authenticationReason;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return this.biometricSourceType == error.biometricSourceType && Intrinsics.areEqual(this.errString, error.errString) && this.errCode == error.errCode && Intrinsics.areEqual(this.requestReason, error.requestReason);
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            int hashCode = this.biometricSourceType.hashCode() * 31;
            String str = this.errString;
            return this.requestReason.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.errCode, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        }

        public final String toString() {
            return "Error(biometricSourceType=" + this.biometricSourceType + ", errString=" + this.errString + ", errCode=" + this.errCode + ", requestReason=" + this.requestReason + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Failed implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;
        public final AuthenticationReason requestReason;
        public final int userId;

        public Failed(BiometricSourceType biometricSourceType, AuthenticationReason authenticationReason, int i) {
            this.biometricSourceType = biometricSourceType;
            this.requestReason = authenticationReason;
            this.userId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Failed)) {
                return false;
            }
            Failed failed = (Failed) obj;
            return this.biometricSourceType == failed.biometricSourceType && Intrinsics.areEqual(this.requestReason, failed.requestReason) && this.userId == failed.userId;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            return Integer.hashCode(this.userId) + ((this.requestReason.hashCode() + (this.biometricSourceType.hashCode() * 31)) * 31);
        }

        public final String toString() {
            BiometricSourceType biometricSourceType = this.biometricSourceType;
            StringBuilder sb = new StringBuilder("Failed(biometricSourceType=");
            sb.append(biometricSourceType);
            sb.append(", requestReason=");
            sb.append(this.requestReason);
            sb.append(", userId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Help implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;
        public final int helpCode;
        public final String helpString;
        public final AuthenticationReason requestReason;

        public Help(BiometricSourceType biometricSourceType, String str, int i, AuthenticationReason authenticationReason) {
            this.biometricSourceType = biometricSourceType;
            this.helpString = str;
            this.helpCode = i;
            this.requestReason = authenticationReason;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Help)) {
                return false;
            }
            Help help = (Help) obj;
            return this.biometricSourceType == help.biometricSourceType && Intrinsics.areEqual(this.helpString, help.helpString) && this.helpCode == help.helpCode && Intrinsics.areEqual(this.requestReason, help.requestReason);
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            int hashCode = this.biometricSourceType.hashCode() * 31;
            String str = this.helpString;
            return this.requestReason.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.helpCode, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        }

        public final String toString() {
            return "Help(biometricSourceType=" + this.biometricSourceType + ", helpString=" + this.helpString + ", helpCode=" + this.helpCode + ", requestReason=" + this.requestReason + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Idle implements AuthenticationState {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Idle)) {
                return false;
            }
            ((Idle) obj).getClass();
            AuthenticationReason.NotRunning notRunning = AuthenticationReason.NotRunning.INSTANCE;
            return notRunning.equals(notRunning);
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return null;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return AuthenticationReason.NotRunning.INSTANCE;
        }

        public final int hashCode() {
            return 1553231364;
        }

        public final String toString() {
            return "Idle(biometricSourceType=null, requestReason=" + AuthenticationReason.NotRunning.INSTANCE + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Started implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;
        public final AuthenticationReason requestReason;

        public Started(BiometricSourceType biometricSourceType, AuthenticationReason authenticationReason) {
            this.biometricSourceType = biometricSourceType;
            this.requestReason = authenticationReason;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Started)) {
                return false;
            }
            Started started = (Started) obj;
            return this.biometricSourceType == started.biometricSourceType && Intrinsics.areEqual(this.requestReason, started.requestReason);
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            return this.requestReason.hashCode() + (this.biometricSourceType.hashCode() * 31);
        }

        public final String toString() {
            return "Started(biometricSourceType=" + this.biometricSourceType + ", requestReason=" + this.requestReason + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Stopped implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;

        public Stopped(BiometricSourceType biometricSourceType) {
            this.biometricSourceType = biometricSourceType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Stopped) || this.biometricSourceType != ((Stopped) obj).biometricSourceType) {
                return false;
            }
            AuthenticationReason.NotRunning notRunning = AuthenticationReason.NotRunning.INSTANCE;
            return notRunning.equals(notRunning);
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return AuthenticationReason.NotRunning.INSTANCE;
        }

        public final int hashCode() {
            return (this.biometricSourceType.hashCode() * 31) + 1553231364;
        }

        public final String toString() {
            return "Stopped(biometricSourceType=" + this.biometricSourceType + ", requestReason=" + AuthenticationReason.NotRunning.INSTANCE + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Succeeded implements AuthenticationState {
        public final BiometricSourceType biometricSourceType;
        public final boolean isStrongBiometric;
        public final AuthenticationReason requestReason;
        public final int userId;

        public Succeeded(BiometricSourceType biometricSourceType, boolean z, AuthenticationReason authenticationReason, int i) {
            this.biometricSourceType = biometricSourceType;
            this.isStrongBiometric = z;
            this.requestReason = authenticationReason;
            this.userId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Succeeded)) {
                return false;
            }
            Succeeded succeeded = (Succeeded) obj;
            return this.biometricSourceType == succeeded.biometricSourceType && this.isStrongBiometric == succeeded.isStrongBiometric && Intrinsics.areEqual(this.requestReason, succeeded.requestReason) && this.userId == succeeded.userId;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final BiometricSourceType getBiometricSourceType() {
            return this.biometricSourceType;
        }

        @Override // com.android.systemui.biometrics.shared.model.AuthenticationState
        public final AuthenticationReason getRequestReason() {
            return this.requestReason;
        }

        public final int hashCode() {
            return Integer.hashCode(this.userId) + ((this.requestReason.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.biometricSourceType.hashCode() * 31, 31, this.isStrongBiometric)) * 31);
        }

        public final String toString() {
            return "Succeeded(biometricSourceType=" + this.biometricSourceType + ", isStrongBiometric=" + this.isStrongBiometric + ", requestReason=" + this.requestReason + ", userId=" + this.userId + ")";
        }
    }

    BiometricSourceType getBiometricSourceType();

    AuthenticationReason getRequestReason();
}
