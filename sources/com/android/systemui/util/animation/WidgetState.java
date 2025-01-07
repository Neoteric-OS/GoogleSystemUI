package com.android.systemui.util.animation;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WidgetState {
    public float alpha;
    public boolean gone;
    public int height;
    public int measureHeight;
    public int measureWidth;
    public float scale;
    public int width;
    public float x;
    public float y;

    public WidgetState(float f, float f2, int i, int i2, int i3, int i4, float f3, float f4, boolean z) {
        this.x = f;
        this.y = f2;
        this.width = i;
        this.height = i2;
        this.measureWidth = i3;
        this.measureHeight = i4;
        this.alpha = f3;
        this.scale = f4;
        this.gone = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WidgetState)) {
            return false;
        }
        WidgetState widgetState = (WidgetState) obj;
        return Float.compare(this.x, widgetState.x) == 0 && Float.compare(this.y, widgetState.y) == 0 && this.width == widgetState.width && this.height == widgetState.height && this.measureWidth == widgetState.measureWidth && this.measureHeight == widgetState.measureHeight && Float.compare(this.alpha, widgetState.alpha) == 0 && Float.compare(this.scale, widgetState.scale) == 0 && this.gone == widgetState.gone;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.gone) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.measureHeight, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.measureWidth, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.height, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.width, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.x) * 31, this.y, 31), 31), 31), 31), 31), this.alpha, 31), this.scale, 31);
    }

    public final String toString() {
        float f = this.x;
        float f2 = this.y;
        int i = this.width;
        int i2 = this.height;
        int i3 = this.measureWidth;
        int i4 = this.measureHeight;
        float f3 = this.alpha;
        float f4 = this.scale;
        boolean z = this.gone;
        StringBuilder sb = new StringBuilder("WidgetState(x=");
        sb.append(f);
        sb.append(", y=");
        sb.append(f2);
        sb.append(", width=");
        ViewPager$$ExternalSyntheticOutline0.m(sb, i, ", height=", i2, ", measureWidth=");
        ViewPager$$ExternalSyntheticOutline0.m(sb, i3, ", measureHeight=", i4, ", alpha=");
        sb.append(f3);
        sb.append(", scale=");
        sb.append(f4);
        sb.append(", gone=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, z, ")");
    }

    public /* synthetic */ WidgetState(int i) {
        this(0.0f, 0.0f, 0, 0, 0, 0, (i & 64) != 0 ? 1.0f : 0.0f, 1.0f, false);
    }
}
