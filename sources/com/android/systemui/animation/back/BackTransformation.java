package com.android.systemui.animation.back;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackTransformation {
    public float scale;
    public ScalePivotPosition scalePivotPosition;
    public float translateX;
    public float translateY;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BackTransformation)) {
            return false;
        }
        BackTransformation backTransformation = (BackTransformation) obj;
        return Float.compare(this.translateX, backTransformation.translateX) == 0 && Float.compare(this.translateY, backTransformation.translateY) == 0 && Float.compare(this.scale, backTransformation.scale) == 0 && this.scalePivotPosition == backTransformation.scalePivotPosition;
    }

    public final int hashCode() {
        int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.translateX) * 31, this.translateY, 31), this.scale, 31);
        ScalePivotPosition scalePivotPosition = this.scalePivotPosition;
        return m + (scalePivotPosition == null ? 0 : scalePivotPosition.hashCode());
    }

    public final String toString() {
        return "BackTransformation(translateX=" + this.translateX + ", translateY=" + this.translateY + ", scale=" + this.scale + ", scalePivotPosition=" + this.scalePivotPosition + ")";
    }
}
