package com.android.compose.animation.scene.transformation;

import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementMatcher;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.content.state.TransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RangedPropertyTransformation implements PropertyTransformation {
    public final PropertyTransformation delegate;
    public final TransformationRange range;

    public RangedPropertyTransformation(PropertyTransformation propertyTransformation, TransformationRange transformationRange) {
        this.delegate = propertyTransformation;
        this.range = transformationRange;
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final ElementMatcher getMatcher() {
        return this.delegate.getMatcher();
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final TransformationRange getRange() {
        return this.range;
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final Transformation reversed() {
        PropertyTransformation propertyTransformation = (PropertyTransformation) this.delegate.reversed();
        TransformationRange transformationRange = this.range;
        float f = transformationRange.end;
        float f2 = TransformationRange.isSpecified(f) ? 1.0f - f : Float.MIN_VALUE;
        float f3 = transformationRange.start;
        return new RangedPropertyTransformation(propertyTransformation, new TransformationRange(f2, TransformationRange.isSpecified(f3) ? 1.0f - f3 : Float.MIN_VALUE, transformationRange.easing));
    }

    @Override // com.android.compose.animation.scene.transformation.PropertyTransformation
    public final Object transform(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, Element.State state, TransitionState.Transition transition, Object obj) {
        return this.delegate.transform(sceneTransitionLayoutImpl, contentKey, element, state, transition, obj);
    }
}
