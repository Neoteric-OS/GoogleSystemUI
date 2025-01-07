package com.google.android.msdl.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HapticCompositionPrimitive {
    public final int delayMillis;
    public final int primitiveId;
    public final float scale;

    public HapticCompositionPrimitive(int i, float f, int i2) {
        this.primitiveId = i;
        this.scale = f;
        this.delayMillis = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HapticCompositionPrimitive)) {
            return false;
        }
        HapticCompositionPrimitive hapticCompositionPrimitive = (HapticCompositionPrimitive) obj;
        return this.primitiveId == hapticCompositionPrimitive.primitiveId && Float.compare(this.scale, hapticCompositionPrimitive.scale) == 0 && this.delayMillis == hapticCompositionPrimitive.delayMillis;
    }

    public final int hashCode() {
        return Integer.hashCode(this.delayMillis) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Integer.hashCode(this.primitiveId) * 31, this.scale, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HapticCompositionPrimitive(primitiveId=");
        sb.append(this.primitiveId);
        sb.append(", scale=");
        sb.append(this.scale);
        sb.append(", delayMillis=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.delayMillis, ")");
    }
}
