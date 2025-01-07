package com.android.compose.animation.scene.transformation;

import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementMatcher;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.content.state.TransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Fade implements PropertyTransformation {
    public final ElementMatcher matcher;

    public Fade(ElementMatcher elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final ElementMatcher getMatcher() {
        return this.matcher;
    }

    @Override // com.android.compose.animation.scene.transformation.PropertyTransformation
    public final /* bridge */ /* synthetic */ Object transform(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, Element.State state, TransitionState.Transition transition, Object obj) {
        ((Number) obj).floatValue();
        return Float.valueOf(0.0f);
    }
}
