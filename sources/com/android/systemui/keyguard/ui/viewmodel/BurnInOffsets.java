package com.android.systemui.keyguard.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BurnInOffsets {
    public final float progress;
    public final int x;
    public final int y;

    public BurnInOffsets(int i, float f, int i2) {
        this.x = i;
        this.y = i2;
        this.progress = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BurnInOffsets)) {
            return false;
        }
        BurnInOffsets burnInOffsets = (BurnInOffsets) obj;
        return this.x == burnInOffsets.x && this.y == burnInOffsets.y && Float.compare(this.progress, burnInOffsets.progress) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.progress) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.y, Integer.hashCode(this.x) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BurnInOffsets(x=");
        sb.append(this.x);
        sb.append(", y=");
        sb.append(this.y);
        sb.append(", progress=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.progress, ")");
    }
}
