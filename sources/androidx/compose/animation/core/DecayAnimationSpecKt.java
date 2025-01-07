package androidx.compose.animation.core;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DecayAnimationSpecKt {
    public static final float calculateTargetValue(DecayAnimationSpec decayAnimationSpec, float f) {
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        return ((AnimationVector1D) new VectorizedFloatDecaySpec(((DecayAnimationSpecImpl) decayAnimationSpec).floatDecaySpec).getTargetValue(new AnimationVector1D(0.0f), new AnimationVector1D(f))).value;
    }

    public static final DecayAnimationSpec generateDecayAnimationSpec(SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec) {
        return new DecayAnimationSpecImpl(splineBasedFloatDecayAnimationSpec);
    }
}
