package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActiveUnlockModel {
    public final boolean isRunning;
    public final int userId;

    public ActiveUnlockModel(int i, boolean z) {
        this.isRunning = z;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveUnlockModel)) {
            return false;
        }
        ActiveUnlockModel activeUnlockModel = (ActiveUnlockModel) obj;
        return this.isRunning == activeUnlockModel.isRunning && this.userId == activeUnlockModel.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + (Boolean.hashCode(this.isRunning) * 31);
    }

    public final String toString() {
        return "ActiveUnlockModel(isRunning=" + this.isRunning + ", userId=" + this.userId + ")";
    }
}
