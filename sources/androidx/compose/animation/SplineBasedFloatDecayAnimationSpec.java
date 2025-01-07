package androidx.compose.animation;

import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplineBasedFloatDecayAnimationSpec {
    public final FlingCalculator flingCalculator;

    public SplineBasedFloatDecayAnimationSpec(Density density) {
        this.flingCalculator = new FlingCalculator(SplineBasedFloatDecayAnimationSpec_androidKt.platformFlingScrollFriction, density);
    }
}
