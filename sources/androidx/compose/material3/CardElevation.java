package androidx.compose.material3;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.unit.Dp;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CardElevation {
    public final float defaultElevation;
    public final float disabledElevation;
    public final float focusedElevation;
    public final float hoveredElevation;
    public final float pressedElevation;

    public CardElevation(float f, float f2, float f3, float f4, float f5, float f6) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.focusedElevation = f3;
        this.hoveredElevation = f4;
        this.disabledElevation = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CardElevation)) {
            return false;
        }
        CardElevation cardElevation = (CardElevation) obj;
        return Dp.m668equalsimpl0(this.defaultElevation, cardElevation.defaultElevation) && Dp.m668equalsimpl0(this.pressedElevation, cardElevation.pressedElevation) && Dp.m668equalsimpl0(this.focusedElevation, cardElevation.focusedElevation) && Dp.m668equalsimpl0(this.hoveredElevation, cardElevation.hoveredElevation) && Dp.m668equalsimpl0(this.disabledElevation, cardElevation.disabledElevation);
    }

    public final int hashCode() {
        return Float.hashCode(this.disabledElevation) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.defaultElevation) * 31, this.pressedElevation, 31), this.focusedElevation, 31), this.hoveredElevation, 31);
    }
}
