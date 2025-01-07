package com.android.systemui.haptics.slider;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeekableSliderTrackerConfig {
    public final float lowerBookendThreshold;
    public final float upperBookendThreshold;

    public SeekableSliderTrackerConfig(float f, float f2) {
        this.lowerBookendThreshold = f;
        this.upperBookendThreshold = f2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeekableSliderTrackerConfig)) {
            return false;
        }
        SeekableSliderTrackerConfig seekableSliderTrackerConfig = (SeekableSliderTrackerConfig) obj;
        seekableSliderTrackerConfig.getClass();
        return Float.compare(0.02f, 0.02f) == 0 && Float.compare(this.lowerBookendThreshold, seekableSliderTrackerConfig.lowerBookendThreshold) == 0 && Float.compare(this.upperBookendThreshold, seekableSliderTrackerConfig.upperBookendThreshold) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.upperBookendThreshold) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Long.hashCode(100L) * 31, 0.02f, 31), this.lowerBookendThreshold, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SeekableSliderTrackerConfig(waitTimeMillis=100, jumpThreshold=0.02, lowerBookendThreshold=");
        sb.append(this.lowerBookendThreshold);
        sb.append(", upperBookendThreshold=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.upperBookendThreshold, ")");
    }
}
