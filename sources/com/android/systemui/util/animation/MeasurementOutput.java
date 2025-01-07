package com.android.systemui.util.animation;

import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MeasurementOutput {
    public int measuredWidth = 0;
    public int measuredHeight = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MeasurementOutput)) {
            return false;
        }
        MeasurementOutput measurementOutput = (MeasurementOutput) obj;
        return this.measuredWidth == measurementOutput.measuredWidth && this.measuredHeight == measurementOutput.measuredHeight;
    }

    public final int hashCode() {
        return Integer.hashCode(this.measuredHeight) + (Integer.hashCode(this.measuredWidth) * 31);
    }

    public final String toString() {
        return MutableVectorKt$$ExternalSyntheticOutline0.m(this.measuredWidth, this.measuredHeight, "MeasurementOutput(measuredWidth=", ", measuredHeight=", ")");
    }
}
