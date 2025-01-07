package com.android.systemui.qs.pipeline.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RestoreData {
    public final Set restoredAutoAddedTiles;
    public final List restoredTiles;
    public final int userId;

    public RestoreData(int i, List list, Set set) {
        this.restoredTiles = list;
        this.restoredAutoAddedTiles = set;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RestoreData)) {
            return false;
        }
        RestoreData restoreData = (RestoreData) obj;
        return Intrinsics.areEqual(this.restoredTiles, restoreData.restoredTiles) && Intrinsics.areEqual(this.restoredAutoAddedTiles, restoreData.restoredAutoAddedTiles) && this.userId == restoreData.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + ((this.restoredAutoAddedTiles.hashCode() + (this.restoredTiles.hashCode() * 31)) * 31);
    }

    public final String toString() {
        List list = this.restoredTiles;
        Set set = this.restoredAutoAddedTiles;
        StringBuilder sb = new StringBuilder("RestoreData(restoredTiles=");
        sb.append(list);
        sb.append(", restoredAutoAddedTiles=");
        sb.append(set);
        sb.append(", userId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userId, ")");
    }
}
