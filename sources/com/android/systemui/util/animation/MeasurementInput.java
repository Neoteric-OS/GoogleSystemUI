package com.android.systemui.util.animation;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MeasurementInput {
    public final int heightMeasureSpec;
    public int widthMeasureSpec;

    public MeasurementInput(int i, int i2) {
        this.widthMeasureSpec = i;
        this.heightMeasureSpec = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MeasurementInput)) {
            return false;
        }
        MeasurementInput measurementInput = (MeasurementInput) obj;
        return this.widthMeasureSpec == measurementInput.widthMeasureSpec && this.heightMeasureSpec == measurementInput.heightMeasureSpec;
    }

    public final int hashCode() {
        return Integer.hashCode(this.heightMeasureSpec) + (Integer.hashCode(this.widthMeasureSpec) * 31);
    }

    public final String toString() {
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(MutableObjectList$$ExternalSyntheticOutline0.m("MeasurementInput(widthMeasureSpec=", ", heightMeasureSpec=", this.widthMeasureSpec), this.heightMeasureSpec, ")");
    }
}
