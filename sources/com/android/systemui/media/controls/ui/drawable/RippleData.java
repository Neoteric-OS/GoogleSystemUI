package com.android.systemui.media.controls.ui.drawable;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RippleData {
    public float alpha;
    public float highlight;
    public float maxSize;
    public float minSize;
    public float progress;
    public float x;
    public float y;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RippleData)) {
            return false;
        }
        RippleData rippleData = (RippleData) obj;
        return Float.compare(this.x, rippleData.x) == 0 && Float.compare(this.y, rippleData.y) == 0 && Float.compare(this.alpha, rippleData.alpha) == 0 && Float.compare(this.progress, rippleData.progress) == 0 && Float.compare(this.minSize, rippleData.minSize) == 0 && Float.compare(this.maxSize, rippleData.maxSize) == 0 && Float.compare(this.highlight, rippleData.highlight) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.highlight) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.x) * 31, this.y, 31), this.alpha, 31), this.progress, 31), this.minSize, 31), this.maxSize, 31);
    }

    public final String toString() {
        float f = this.x;
        float f2 = this.y;
        float f3 = this.alpha;
        float f4 = this.progress;
        float f5 = this.minSize;
        float f6 = this.maxSize;
        float f7 = this.highlight;
        StringBuilder sb = new StringBuilder("RippleData(x=");
        sb.append(f);
        sb.append(", y=");
        sb.append(f2);
        sb.append(", alpha=");
        sb.append(f3);
        sb.append(", progress=");
        sb.append(f4);
        sb.append(", minSize=");
        sb.append(f5);
        sb.append(", maxSize=");
        sb.append(f6);
        sb.append(", highlight=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, f7, ")");
    }
}
