package com.android.systemui.statusbar.connectivity;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiState extends ConnectivityState {
    public boolean isCarrierMerged;
    public boolean isDefault;
    public boolean isDefaultConnectionValidated;
    public String ssid;
    public String statusLabel;
    public int subId;

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final void copyFrom(ConnectivityState connectivityState) {
        super.copyFrom(connectivityState);
        WifiState wifiState = (WifiState) connectivityState;
        this.ssid = wifiState.ssid;
        this.isDefault = wifiState.isDefault;
        this.statusLabel = wifiState.statusLabel;
        this.isCarrierMerged = wifiState.isCarrierMerged;
        this.isDefaultConnectionValidated = wifiState.isDefaultConnectionValidated;
        this.subId = wifiState.subId;
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!WifiState.class.equals(obj != null ? obj.getClass() : null) || !super.equals(obj)) {
            return false;
        }
        WifiState wifiState = (WifiState) obj;
        return Intrinsics.areEqual(this.ssid, wifiState.ssid) && this.isDefault == wifiState.isDefault && Intrinsics.areEqual(this.statusLabel, wifiState.statusLabel) && this.isCarrierMerged == wifiState.isCarrierMerged && this.isDefaultConnectionValidated == wifiState.isDefaultConnectionValidated && this.subId == wifiState.subId;
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.ssid;
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((hashCode + (str != null ? str.hashCode() : 0)) * 31, 31, false), 31, this.isDefault);
        String str2 = this.statusLabel;
        return TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((m + (str2 != null ? str2.hashCode() : 0)) * 31, 31, this.isCarrierMerged), 31, this.isDefaultConnectionValidated) + this.subId;
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final List tableColumns() {
        return CollectionsKt.plus((Iterable) CollectionsKt__CollectionsKt.listOf("ssid", "isTransient", "isDefault", "statusLabel", "isCarrierMerged", "isDefaultConnectionValidated", "subId"), (Collection) super.tableColumns());
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final List tableData() {
        List listOf = CollectionsKt__CollectionsKt.listOf(this.ssid, Boolean.FALSE, Boolean.valueOf(this.isDefault), this.statusLabel, Boolean.valueOf(this.isCarrierMerged), Boolean.valueOf(this.isDefaultConnectionValidated), Integer.valueOf(this.subId));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        return CollectionsKt.plus((Iterable) arrayList, (Collection) super.tableData());
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final void toString(StringBuilder sb) {
        super.toString(sb);
        sb.append(",ssid=");
        sb.append(this.ssid);
        sb.append(",isTransient=");
        sb.append(false);
        sb.append(",isDefault=");
        sb.append(this.isDefault);
        sb.append(",statusLabel=");
        sb.append(this.statusLabel);
        sb.append(",isCarrierMerged=");
        sb.append(this.isCarrierMerged);
        sb.append(",isDefaultConnectionValidated=");
        sb.append(this.isDefaultConnectionValidated);
        sb.append(",subId=");
        sb.append(this.subId);
    }
}
