package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustManagedModel extends TrustMessage {
    public final boolean isTrustManaged;
    public final int userId;

    public TrustManagedModel(int i, boolean z) {
        this.userId = i;
        this.isTrustManaged = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TrustManagedModel)) {
            return false;
        }
        TrustManagedModel trustManagedModel = (TrustManagedModel) obj;
        return this.userId == trustManagedModel.userId && this.isTrustManaged == trustManagedModel.isTrustManaged;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isTrustManaged) + (Integer.hashCode(this.userId) * 31);
    }

    public final String toString() {
        return "TrustManagedModel(userId=" + this.userId + ", isTrustManaged=" + this.isTrustManaged + ")";
    }
}
