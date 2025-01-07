package com.android.systemui.shared.shadow;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DoubleShadowTextHelper$ShadowInfo {
    public final float alpha;
    public final float blur;
    public final float offsetX;
    public final float offsetY;

    public DoubleShadowTextHelper$ShadowInfo(float f, float f2, float f3, float f4) {
        this.blur = f;
        this.offsetX = f2;
        this.offsetY = f3;
        this.alpha = f4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleShadowTextHelper$ShadowInfo)) {
            return false;
        }
        DoubleShadowTextHelper$ShadowInfo doubleShadowTextHelper$ShadowInfo = (DoubleShadowTextHelper$ShadowInfo) obj;
        return Float.compare(this.blur, doubleShadowTextHelper$ShadowInfo.blur) == 0 && Float.compare(this.offsetX, doubleShadowTextHelper$ShadowInfo.offsetX) == 0 && Float.compare(this.offsetY, doubleShadowTextHelper$ShadowInfo.offsetY) == 0 && Float.compare(this.alpha, doubleShadowTextHelper$ShadowInfo.alpha) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.alpha) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.blur) * 31, this.offsetX, 31), this.offsetY, 31);
    }

    public final String toString() {
        return "ShadowInfo(blur=" + this.blur + ", offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", alpha=" + this.alpha + ")";
    }
}
