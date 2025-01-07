package com.android.systemui.haptics.slider;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderHapticFeedbackConfig {
    public final float additionalVelocityMaxBump;
    public final float deltaProgressForDragThreshold;
    public final float maxVelocityToScale;
    public final int numberOfLowTicks;
    public final int velocityAxis;

    public SliderHapticFeedbackConfig(float f, float f2, float f3, int i, int i2) {
        this.additionalVelocityMaxBump = f;
        this.deltaProgressForDragThreshold = f2;
        this.numberOfLowTicks = i;
        this.maxVelocityToScale = f3;
        this.velocityAxis = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SliderHapticFeedbackConfig)) {
            return false;
        }
        SliderHapticFeedbackConfig sliderHapticFeedbackConfig = (SliderHapticFeedbackConfig) obj;
        sliderHapticFeedbackConfig.getClass();
        return Float.compare(1.0f, 1.0f) == 0 && Float.compare(1.0f, 1.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.2f, 0.2f) == 0 && Float.compare(this.additionalVelocityMaxBump, sliderHapticFeedbackConfig.additionalVelocityMaxBump) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.deltaProgressForDragThreshold, sliderHapticFeedbackConfig.deltaProgressForDragThreshold) == 0 && this.numberOfLowTicks == sliderHapticFeedbackConfig.numberOfLowTicks && Float.compare(this.maxVelocityToScale, sliderHapticFeedbackConfig.maxVelocityToScale) == 0 && this.velocityAxis == sliderHapticFeedbackConfig.velocityAxis && Float.compare(1.0f, 1.0f) == 0 && Float.compare(0.05f, 0.05f) == 0 && Float.compare(1.1235955f, 1.1235955f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(1.1235955f) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.velocityAxis, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.numberOfLowTicks, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(1.0f) * 31, 1.0f, 31), 0.0f, 31), 0.2f, 31), this.additionalVelocityMaxBump, 31), 0.0f, 31), this.deltaProgressForDragThreshold, 31), 31), this.maxVelocityToScale, 31), 31), 1.0f, 31), 0.05f, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SliderHapticFeedbackConfig(velocityInterpolatorFactor=1.0, progressInterpolatorFactor=1.0, progressBasedDragMinScale=0.0, progressBasedDragMaxScale=0.2, additionalVelocityMaxBump=");
        sb.append(this.additionalVelocityMaxBump);
        sb.append(", deltaMillisForDragInterval=0.0, deltaProgressForDragThreshold=");
        sb.append(this.deltaProgressForDragThreshold);
        sb.append(", numberOfLowTicks=");
        sb.append(this.numberOfLowTicks);
        sb.append(", maxVelocityToScale=");
        sb.append(this.maxVelocityToScale);
        sb.append(", velocityAxis=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.velocityAxis, ", upperBookendScale=1.0, lowerBookendScale=0.05, exponent=1.1235955)");
    }
}
