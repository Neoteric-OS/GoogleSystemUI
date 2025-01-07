package com.google.android.systemui.tips.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HistoricalTipsModel {
    public final int count;
    public final long lastTimestamp;
    public final long penultimateTimestamp;

    public HistoricalTipsModel(int i, long j, long j2) {
        this.lastTimestamp = j;
        this.penultimateTimestamp = j2;
        this.count = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HistoricalTipsModel)) {
            return false;
        }
        HistoricalTipsModel historicalTipsModel = (HistoricalTipsModel) obj;
        return this.lastTimestamp == historicalTipsModel.lastTimestamp && this.penultimateTimestamp == historicalTipsModel.penultimateTimestamp && this.count == historicalTipsModel.count;
    }

    public final int hashCode() {
        return Integer.hashCode(this.count) + Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.lastTimestamp) * 31, 31, this.penultimateTimestamp);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HistoricalTipsModel(lastTimestamp=");
        sb.append(this.lastTimestamp);
        sb.append(", penultimateTimestamp=");
        sb.append(this.penultimateTimestamp);
        sb.append(", count=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.count, ")");
    }
}
