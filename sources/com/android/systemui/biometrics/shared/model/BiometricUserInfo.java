package com.android.systemui.biometrics.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricUserInfo {
    public final int deviceCredentialOwnerId;
    public final int userId;
    public final int userIdForPasswordEntry;

    public BiometricUserInfo(int i, int i2, int i3) {
        this.userId = i;
        this.deviceCredentialOwnerId = i2;
        this.userIdForPasswordEntry = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BiometricUserInfo)) {
            return false;
        }
        BiometricUserInfo biometricUserInfo = (BiometricUserInfo) obj;
        return this.userId == biometricUserInfo.userId && this.deviceCredentialOwnerId == biometricUserInfo.deviceCredentialOwnerId && this.userIdForPasswordEntry == biometricUserInfo.userIdForPasswordEntry;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userIdForPasswordEntry) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.deviceCredentialOwnerId, Integer.hashCode(this.userId) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BiometricUserInfo(userId=");
        sb.append(this.userId);
        sb.append(", deviceCredentialOwnerId=");
        sb.append(this.deviceCredentialOwnerId);
        sb.append(", userIdForPasswordEntry=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userIdForPasswordEntry, ")");
    }
}
