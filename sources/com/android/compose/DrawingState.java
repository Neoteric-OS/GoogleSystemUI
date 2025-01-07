package com.android.compose;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DrawingState {
    public final float iconWidth;
    public final float indicatorBottom;
    public final float indicatorLeft;
    public final float indicatorRight;
    public final boolean isRtl;
    public final float labelWidth;
    public final float totalHeight;
    public final float totalWidth;

    public DrawingState(boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.isRtl = z;
        this.totalWidth = f;
        this.totalHeight = f2;
        this.indicatorLeft = f3;
        this.indicatorRight = f4;
        this.indicatorBottom = f5;
        this.iconWidth = f6;
        this.labelWidth = f7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DrawingState)) {
            return false;
        }
        DrawingState drawingState = (DrawingState) obj;
        return this.isRtl == drawingState.isRtl && Float.compare(this.totalWidth, drawingState.totalWidth) == 0 && Float.compare(this.totalHeight, drawingState.totalHeight) == 0 && Float.compare(this.indicatorLeft, drawingState.indicatorLeft) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.indicatorRight, drawingState.indicatorRight) == 0 && Float.compare(this.indicatorBottom, drawingState.indicatorBottom) == 0 && Float.compare(this.iconWidth, drawingState.iconWidth) == 0 && Float.compare(this.labelWidth, drawingState.labelWidth) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.labelWidth) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isRtl) * 31, this.totalWidth, 31), this.totalHeight, 31), this.indicatorLeft, 31), 0.0f, 31), this.indicatorRight, 31), this.indicatorBottom, 31), this.iconWidth, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DrawingState(isRtl=");
        sb.append(this.isRtl);
        sb.append(", totalWidth=");
        sb.append(this.totalWidth);
        sb.append(", totalHeight=");
        sb.append(this.totalHeight);
        sb.append(", indicatorLeft=");
        sb.append(this.indicatorLeft);
        sb.append(", indicatorTop=0.0, indicatorRight=");
        sb.append(this.indicatorRight);
        sb.append(", indicatorBottom=");
        sb.append(this.indicatorBottom);
        sb.append(", iconWidth=");
        sb.append(this.iconWidth);
        sb.append(", labelWidth=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.labelWidth, ")");
    }
}
