package com.android.systemui.util.animation;

import android.graphics.PointF;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisappearParameters {
    public final PointF gonePivot = new PointF(0.0f, 1.0f);
    public final PointF disappearSize = new PointF(1.0f, 0.0f);
    public final PointF contentTranslationFraction = new PointF(0.0f, 0.8f);
    public float disappearEnd = 1.0f;
    public float fadeStartPosition = 0.9f;

    public final boolean equals(Object obj) {
        if (!(obj instanceof DisappearParameters)) {
            return false;
        }
        DisappearParameters disappearParameters = (DisappearParameters) obj;
        return this.disappearSize.equals(disappearParameters.disappearSize) && this.gonePivot.equals(disappearParameters.gonePivot) && this.contentTranslationFraction.equals(disappearParameters.contentTranslationFraction) && this.disappearEnd == disappearParameters.disappearEnd && this.fadeStartPosition == disappearParameters.fadeStartPosition;
    }

    public final int hashCode() {
        return Float.hashCode(this.fadeStartPosition) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.contentTranslationFraction.hashCode() + ((this.gonePivot.hashCode() + (this.disappearSize.hashCode() * 31)) * 31)) * 31, 0.0f, 31), this.disappearEnd, 31);
    }
}
