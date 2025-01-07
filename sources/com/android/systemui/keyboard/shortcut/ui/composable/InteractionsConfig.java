package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InteractionsConfig {
    public final long focusOutlineColor;
    public final float focusOutlineCornerRadius;
    public final float focusOutlinePadding;
    public final float focusOutlineStrokeWidth;
    public final float hoverOverlayAlpha;
    public final long hoverOverlayColor;
    public final float hoverPadding;
    public final float pressedOverlayAlpha;
    public final long pressedOverlayColor;
    public final float pressedPadding;
    public final float surfaceCornerRadius;

    public InteractionsConfig(long j, long j2, long j3, float f, float f2, float f3, float f4, float f5, int i) {
        long j4 = (i & 1) != 0 ? Color.Transparent : j;
        float f6 = (i & 2) != 0 ? 0.0f : 0.11f;
        long j5 = (i & 4) != 0 ? Color.Transparent : j2;
        float f7 = (i & 8) == 0 ? 0.15f : 0.0f;
        long j6 = (i & 16) != 0 ? Color.Transparent : j3;
        float f8 = (i & 32) != 0 ? 0 : f;
        float f9 = (i & 64) != 0 ? 0 : f2;
        float f10 = (i & 128) != 0 ? 0 : f3;
        float f11 = (i & 256) != 0 ? 0 : f4;
        float f12 = (i & 512) != 0 ? 0 : f5;
        this.hoverOverlayColor = j4;
        this.hoverOverlayAlpha = f6;
        this.pressedOverlayColor = j5;
        this.pressedOverlayAlpha = f7;
        this.focusOutlineColor = j6;
        this.focusOutlineStrokeWidth = f8;
        this.focusOutlinePadding = f9;
        this.surfaceCornerRadius = f10;
        this.focusOutlineCornerRadius = f11;
        this.hoverPadding = f12;
        this.pressedPadding = f12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InteractionsConfig)) {
            return false;
        }
        InteractionsConfig interactionsConfig = (InteractionsConfig) obj;
        return Color.m363equalsimpl0(this.hoverOverlayColor, interactionsConfig.hoverOverlayColor) && Float.compare(this.hoverOverlayAlpha, interactionsConfig.hoverOverlayAlpha) == 0 && Color.m363equalsimpl0(this.pressedOverlayColor, interactionsConfig.pressedOverlayColor) && Float.compare(this.pressedOverlayAlpha, interactionsConfig.pressedOverlayAlpha) == 0 && Color.m363equalsimpl0(this.focusOutlineColor, interactionsConfig.focusOutlineColor) && Dp.m668equalsimpl0(this.focusOutlineStrokeWidth, interactionsConfig.focusOutlineStrokeWidth) && Dp.m668equalsimpl0(this.focusOutlinePadding, interactionsConfig.focusOutlinePadding) && Dp.m668equalsimpl0(this.surfaceCornerRadius, interactionsConfig.surfaceCornerRadius) && Dp.m668equalsimpl0(this.focusOutlineCornerRadius, interactionsConfig.focusOutlineCornerRadius) && Dp.m668equalsimpl0(this.hoverPadding, interactionsConfig.hoverPadding) && Dp.m668equalsimpl0(this.pressedPadding, interactionsConfig.pressedPadding);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Float.hashCode(this.pressedPadding) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Long.hashCode(this.hoverOverlayColor) * 31, this.hoverOverlayAlpha, 31), 31, this.pressedOverlayColor), this.pressedOverlayAlpha, 31), 31, this.focusOutlineColor), this.focusOutlineStrokeWidth, 31), this.focusOutlinePadding, 31), this.surfaceCornerRadius, 31), this.focusOutlineCornerRadius, 31), this.hoverPadding, 31);
    }

    public final String toString() {
        String m369toStringimpl = Color.m369toStringimpl(this.hoverOverlayColor);
        String m369toStringimpl2 = Color.m369toStringimpl(this.pressedOverlayColor);
        String m369toStringimpl3 = Color.m369toStringimpl(this.focusOutlineColor);
        String m669toStringimpl = Dp.m669toStringimpl(this.focusOutlineStrokeWidth);
        String m669toStringimpl2 = Dp.m669toStringimpl(this.focusOutlinePadding);
        String m669toStringimpl3 = Dp.m669toStringimpl(this.surfaceCornerRadius);
        String m669toStringimpl4 = Dp.m669toStringimpl(this.focusOutlineCornerRadius);
        String m669toStringimpl5 = Dp.m669toStringimpl(this.hoverPadding);
        String m669toStringimpl6 = Dp.m669toStringimpl(this.pressedPadding);
        StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("InteractionsConfig(hoverOverlayColor=", m369toStringimpl, ", hoverOverlayAlpha=");
        m.append(this.hoverOverlayAlpha);
        m.append(", pressedOverlayColor=");
        m.append(m369toStringimpl2);
        m.append(", pressedOverlayAlpha=");
        m.append(this.pressedOverlayAlpha);
        m.append(", focusOutlineColor=");
        m.append(m369toStringimpl3);
        m.append(", focusOutlineStrokeWidth=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m669toStringimpl, ", focusOutlinePadding=", m669toStringimpl2, ", surfaceCornerRadius=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m669toStringimpl3, ", focusOutlineCornerRadius=", m669toStringimpl4, ", hoverPadding=");
        m.append(m669toStringimpl5);
        m.append(", pressedPadding=");
        m.append(m669toStringimpl6);
        m.append(")");
        return m.toString();
    }
}
