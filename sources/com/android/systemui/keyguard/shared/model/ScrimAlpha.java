package com.android.systemui.keyguard.shared.model;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrimAlpha {
    public final float behindAlpha;
    public final float notificationsAlpha;

    public ScrimAlpha(int i, float f, float f2) {
        f = (i & 2) != 0 ? 0.0f : f;
        f2 = (i & 4) != 0 ? 0.0f : f2;
        this.behindAlpha = f;
        this.notificationsAlpha = f2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrimAlpha)) {
            return false;
        }
        ScrimAlpha scrimAlpha = (ScrimAlpha) obj;
        scrimAlpha.getClass();
        return Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.behindAlpha, scrimAlpha.behindAlpha) == 0 && Float.compare(this.notificationsAlpha, scrimAlpha.notificationsAlpha) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.notificationsAlpha) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(0.0f) * 31, this.behindAlpha, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ScrimAlpha(frontAlpha=0.0, behindAlpha=");
        sb.append(this.behindAlpha);
        sb.append(", notificationsAlpha=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.notificationsAlpha, ")");
    }
}
