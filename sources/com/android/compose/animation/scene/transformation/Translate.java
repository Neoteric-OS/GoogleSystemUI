package com.android.compose.animation.scene.transformation;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementMatcher;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.content.state.TransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Translate implements PropertyTransformation {
    public final ElementMatcher matcher;
    public final float x;
    public final float y;

    public Translate(ElementMatcher elementMatcher, float f, float f2) {
        this.matcher = elementMatcher;
        this.x = f;
        this.y = f2;
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final ElementMatcher getMatcher() {
        return this.matcher;
    }

    @Override // com.android.compose.animation.scene.transformation.PropertyTransformation
    public final Object transform(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, Element.State state, TransitionState.Transition transition, Object obj) {
        Density density = sceneTransitionLayoutImpl.density;
        long j = ((Offset) obj).packedValue;
        float mo51toPx0680j_4 = density.mo51toPx0680j_4(this.x) + Float.intBitsToFloat((int) (j >> 32));
        float mo51toPx0680j_42 = density.mo51toPx0680j_4(this.y) + Float.intBitsToFloat((int) (j & 4294967295L));
        return new Offset((Float.floatToRawIntBits(mo51toPx0680j_4) << 32) | (Float.floatToRawIntBits(mo51toPx0680j_42) & 4294967295L));
    }
}
