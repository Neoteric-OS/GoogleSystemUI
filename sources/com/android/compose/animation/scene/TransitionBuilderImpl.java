package com.android.compose.animation.scene;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.DurationBasedAnimationSpec;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.EasingKt$$ExternalSyntheticLambda0;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import com.android.compose.animation.scene.transformation.EdgeTranslate;
import com.android.compose.animation.scene.transformation.Fade;
import com.android.compose.animation.scene.transformation.PropertyTransformation;
import com.android.compose.animation.scene.transformation.RangedPropertyTransformation;
import com.android.compose.animation.scene.transformation.TransformationRange;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionBuilderImpl {
    public final DefaultSwipeDistance distance;
    public TransformationRange range;
    public final SpringSpec swipeSpec;
    public final List transformations = new ArrayList();
    public FiniteAnimationSpec spec = AnimationSpecKt.spring$default(0.0f, 200.0f, null, 5);
    public final Lazy durationMillis$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.compose.animation.scene.TransitionBuilderImpl$durationMillis$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            FiniteAnimationSpec finiteAnimationSpec = TransitionBuilderImpl.this.spec;
            if (finiteAnimationSpec instanceof DurationBasedAnimationSpec) {
                return Integer.valueOf(((DurationBasedAnimationSpec) finiteAnimationSpec).vectorize(VectorConvertersKt.FloatToVector).getDurationMillis());
            }
            throw new IllegalStateException("timestampRange {} can only be used with a DurationBasedAnimationSpec");
        }
    });

    public static void fractionRange$default(TransitionBuilderImpl transitionBuilderImpl, Float f, Float f2, CubicBezierEasing cubicBezierEasing, Function1 function1, int i) {
        if ((i & 1) != 0) {
            f = null;
        }
        if ((i & 2) != 0) {
            f2 = null;
        }
        Easing easing = cubicBezierEasing;
        if ((i & 4) != 0) {
            easing = EasingKt.LinearEasing;
        }
        transitionBuilderImpl.getClass();
        transitionBuilderImpl.range = new TransformationRange(f != null ? f.floatValue() : Float.MIN_VALUE, f2 != null ? f2.floatValue() : Float.MIN_VALUE, easing);
        function1.invoke(transitionBuilderImpl);
        transitionBuilderImpl.range = null;
    }

    public static void timestampRange$default(TransitionBuilderImpl transitionBuilderImpl, Integer num, Integer num2, Function1 function1, int i) {
        if ((i & 1) != 0) {
            num = null;
        }
        EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda0 = EasingKt.LinearEasing;
        transitionBuilderImpl.getClass();
        if (num != null && (num.intValue() < 0 || num.intValue() > transitionBuilderImpl.getDurationMillis())) {
            throw new IllegalStateException(("invalid start value: startMillis=" + num + " durationMillis=" + transitionBuilderImpl.getDurationMillis()).toString());
        }
        if (num2.intValue() < 0 || num2.intValue() > transitionBuilderImpl.getDurationMillis()) {
            throw new IllegalStateException(("invalid end value: endMillis=" + num + " durationMillis=" + transitionBuilderImpl.getDurationMillis()).toString());
        }
        Float valueOf = num != null ? Float.valueOf(num.intValue() / transitionBuilderImpl.getDurationMillis()) : null;
        transitionBuilderImpl.range = new TransformationRange(valueOf != null ? valueOf.floatValue() : Float.MIN_VALUE, Float.valueOf(num2.intValue() / transitionBuilderImpl.getDurationMillis()).floatValue(), easingKt$$ExternalSyntheticLambda0);
        function1.invoke(transitionBuilderImpl);
        transitionBuilderImpl.range = null;
    }

    public static void translate$default(TransitionBuilderImpl transitionBuilderImpl, ElementKey elementKey, Edge edge) {
        transitionBuilderImpl.getClass();
        transitionBuilderImpl.transformation(new EdgeTranslate(elementKey, edge, true));
    }

    public final void fade(ElementMatcher elementMatcher) {
        transformation(new Fade(elementMatcher));
    }

    public final int getDurationMillis() {
        return ((Number) this.durationMillis$delegate.getValue()).intValue();
    }

    public final void transformation(PropertyTransformation propertyTransformation) {
        TransformationRange transformationRange = this.range;
        if (transformationRange != null) {
            propertyTransformation = new RangedPropertyTransformation(propertyTransformation, transformationRange);
        }
        this.transformations.add(propertyTransformation);
    }
}
