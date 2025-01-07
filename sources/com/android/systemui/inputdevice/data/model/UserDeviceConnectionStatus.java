package com.android.systemui.inputdevice.data.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserDeviceConnectionStatus {
    public final boolean isConnected;
    public final int userId;

    public UserDeviceConnectionStatus(int i, boolean z) {
        this.isConnected = z;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDeviceConnectionStatus)) {
            return false;
        }
        UserDeviceConnectionStatus userDeviceConnectionStatus = (UserDeviceConnectionStatus) obj;
        return this.isConnected == userDeviceConnectionStatus.isConnected && this.userId == userDeviceConnectionStatus.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + (Boolean.hashCode(this.isConnected) * 31);
    }

    public final String toString() {
        return "UserDeviceConnectionStatus(isConnected=" + this.isConnected + ", userId=" + this.userId + ")";
    }
}
