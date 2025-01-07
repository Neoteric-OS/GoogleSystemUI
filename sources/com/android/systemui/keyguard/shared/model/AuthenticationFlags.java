package com.android.systemui.keyguard.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationFlags {
    public final int flag;
    public final boolean isInUserLockdown;
    public final boolean isPrimaryAuthRequiredAfterDpmLockdown;
    public final boolean isPrimaryAuthRequiredAfterLockout;
    public final boolean isPrimaryAuthRequiredAfterReboot;
    public final boolean isPrimaryAuthRequiredAfterTimeout;
    public final boolean isPrimaryAuthRequiredForUnattendedUpdate;
    public final boolean isSomeAuthRequiredAfterAdaptiveAuthRequest;
    public final boolean someAuthRequiredAfterTrustAgentExpired;
    public final boolean someAuthRequiredAfterUserRequest;
    public final boolean strongerAuthRequiredAfterNonStrongBiometricsTimeout;
    public final int userId;

    public AuthenticationFlags(int i, int i2) {
        this.userId = i;
        this.flag = i2;
        this.isInUserLockdown = AuthenticationFlagsKt.access$containsFlag(i2, 32);
        this.isPrimaryAuthRequiredAfterReboot = AuthenticationFlagsKt.access$containsFlag(i2, 1);
        this.isPrimaryAuthRequiredAfterTimeout = AuthenticationFlagsKt.access$containsFlag(i2, 16);
        this.isPrimaryAuthRequiredAfterLockout = AuthenticationFlagsKt.access$containsFlag(i2, 8);
        this.isPrimaryAuthRequiredAfterDpmLockdown = AuthenticationFlagsKt.access$containsFlag(i2, 2);
        this.someAuthRequiredAfterUserRequest = AuthenticationFlagsKt.access$containsFlag(i2, 4);
        this.someAuthRequiredAfterTrustAgentExpired = AuthenticationFlagsKt.access$containsFlag(i2, 256);
        this.isPrimaryAuthRequiredForUnattendedUpdate = AuthenticationFlagsKt.access$containsFlag(i2, 64);
        this.strongerAuthRequiredAfterNonStrongBiometricsTimeout = AuthenticationFlagsKt.access$containsFlag(i2, 128);
        this.isSomeAuthRequiredAfterAdaptiveAuthRequest = AuthenticationFlagsKt.access$containsFlag(i2, 512);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationFlags)) {
            return false;
        }
        AuthenticationFlags authenticationFlags = (AuthenticationFlags) obj;
        return this.userId == authenticationFlags.userId && this.flag == authenticationFlags.flag;
    }

    public final int hashCode() {
        return Integer.hashCode(this.flag) + (Integer.hashCode(this.userId) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AuthenticationFlags(userId=");
        sb.append(this.userId);
        sb.append(", flag=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.flag, ")");
    }
}
