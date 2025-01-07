package com.android.systemui.media.controls.ui.controller;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CacheKey {
    public float expansion;
    public boolean gutsVisible;
    public int heightMeasureSpec;
    public int widthMeasureSpec;

    public CacheKey(int i, int i2, float f, boolean z) {
        this.widthMeasureSpec = i;
        this.heightMeasureSpec = i2;
        this.expansion = f;
        this.gutsVisible = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheKey)) {
            return false;
        }
        CacheKey cacheKey = (CacheKey) obj;
        return this.widthMeasureSpec == cacheKey.widthMeasureSpec && this.heightMeasureSpec == cacheKey.heightMeasureSpec && Float.compare(this.expansion, cacheKey.expansion) == 0 && this.gutsVisible == cacheKey.gutsVisible;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.gutsVisible) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.heightMeasureSpec, Integer.hashCode(this.widthMeasureSpec) * 31, 31), this.expansion, 31);
    }

    public final String toString() {
        int i = this.widthMeasureSpec;
        int i2 = this.heightMeasureSpec;
        float f = this.expansion;
        boolean z = this.gutsVisible;
        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "CacheKey(widthMeasureSpec=", ", heightMeasureSpec=", ", expansion=");
        m.append(f);
        m.append(", gutsVisible=");
        m.append(z);
        m.append(")");
        return m.toString();
    }
}
