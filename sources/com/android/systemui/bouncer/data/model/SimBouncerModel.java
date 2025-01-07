package com.android.systemui.bouncer.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimBouncerModel {
    public final boolean isSimPukLocked;
    public final int subscriptionId;

    public SimBouncerModel(int i, boolean z) {
        this.isSimPukLocked = z;
        this.subscriptionId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimBouncerModel)) {
            return false;
        }
        SimBouncerModel simBouncerModel = (SimBouncerModel) obj;
        return this.isSimPukLocked == simBouncerModel.isSimPukLocked && this.subscriptionId == simBouncerModel.subscriptionId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.subscriptionId) + (Boolean.hashCode(this.isSimPukLocked) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimBouncerModel(isSimPukLocked=");
        sb.append(this.isSimPukLocked);
        sb.append(", subscriptionId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.subscriptionId, ")");
    }
}
