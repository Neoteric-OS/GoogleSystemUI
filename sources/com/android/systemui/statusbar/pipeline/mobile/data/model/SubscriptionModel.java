package com.android.systemui.statusbar.pipeline.mobile.data.model;

import android.os.ParcelUuid;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SubscriptionModel {
    public final String carrierName;
    public final ParcelUuid groupUuid;
    public final boolean isExclusivelyNonTerrestrial;
    public final boolean isOpportunistic;
    public final int profileClass;
    public final int subscriptionId;

    public SubscriptionModel(int i, boolean z, boolean z2, ParcelUuid parcelUuid, String str, int i2) {
        this.subscriptionId = i;
        this.isOpportunistic = z;
        this.isExclusivelyNonTerrestrial = z2;
        this.groupUuid = parcelUuid;
        this.carrierName = str;
        this.profileClass = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubscriptionModel)) {
            return false;
        }
        SubscriptionModel subscriptionModel = (SubscriptionModel) obj;
        return this.subscriptionId == subscriptionModel.subscriptionId && this.isOpportunistic == subscriptionModel.isOpportunistic && this.isExclusivelyNonTerrestrial == subscriptionModel.isExclusivelyNonTerrestrial && Intrinsics.areEqual(this.groupUuid, subscriptionModel.groupUuid) && Intrinsics.areEqual(this.carrierName, subscriptionModel.carrierName) && this.profileClass == subscriptionModel.profileClass;
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.subscriptionId) * 31, 31, this.isOpportunistic), 31, this.isExclusivelyNonTerrestrial);
        ParcelUuid parcelUuid = this.groupUuid;
        return Integer.hashCode(this.profileClass) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.carrierName, (m + (parcelUuid == null ? 0 : parcelUuid.hashCode())) * 31, 31);
    }

    public final String toString() {
        return "SubscriptionModel(subscriptionId=" + this.subscriptionId + ", isOpportunistic=" + this.isOpportunistic + ", isExclusivelyNonTerrestrial=" + this.isExclusivelyNonTerrestrial + ", groupUuid=" + this.groupUuid + ", carrierName=" + this.carrierName + ", profileClass=" + this.profileClass + ")";
    }
}
