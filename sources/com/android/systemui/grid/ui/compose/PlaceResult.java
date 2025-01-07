package com.android.systemui.grid.ui.compose;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.layout.Placeable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PlaceResult {
    public final int mainAxisGroup;
    public final Placeable placeable;
    public final int slotIndex;

    public PlaceResult(int i, int i2, Placeable placeable) {
        this.placeable = placeable;
        this.slotIndex = i;
        this.mainAxisGroup = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceResult)) {
            return false;
        }
        PlaceResult placeResult = (PlaceResult) obj;
        return Intrinsics.areEqual(this.placeable, placeResult.placeable) && this.slotIndex == placeResult.slotIndex && this.mainAxisGroup == placeResult.mainAxisGroup;
    }

    public final int hashCode() {
        return Integer.hashCode(this.mainAxisGroup) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.slotIndex, this.placeable.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlaceResult(placeable=");
        sb.append(this.placeable);
        sb.append(", slotIndex=");
        sb.append(this.slotIndex);
        sb.append(", mainAxisGroup=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.mainAxisGroup, ")");
    }
}
