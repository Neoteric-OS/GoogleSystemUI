package com.android.compose.animation.scene;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Scale {
    public final long pivot;
    public final float scaleX;
    public final float scaleY;
    public static final Scale Default = new Scale(1.0f, 1.0f, 9205357640488583168L);
    public static final Scale Zero = new Scale(0.0f, 0.0f, 0);
    public static final Scale Unspecified = new Scale(Float.MAX_VALUE, Float.MAX_VALUE, 9205357640488583168L);

    public Scale(float f, float f2, long j) {
        this.scaleX = f;
        this.scaleY = f2;
        this.pivot = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scale)) {
            return false;
        }
        Scale scale = (Scale) obj;
        return Float.compare(this.scaleX, scale.scaleX) == 0 && Float.compare(this.scaleY, scale.scaleY) == 0 && Offset.m310equalsimpl0(this.pivot, scale.pivot);
    }

    public final int hashCode() {
        return Long.hashCode(this.pivot) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.scaleX) * 31, this.scaleY, 31);
    }

    public final String toString() {
        return "Scale(scaleX=" + this.scaleX + ", scaleY=" + this.scaleY + ", pivot=" + Offset.m317toStringimpl(this.pivot) + ")";
    }
}
