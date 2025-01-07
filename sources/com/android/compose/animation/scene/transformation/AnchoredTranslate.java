package com.android.compose.animation.scene.transformation;

import androidx.compose.ui.geometry.Offset;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.ElementMatcher;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnchoredTranslate implements PropertyTransformation {
    public final ElementKey anchor;
    public final ElementKey matcher;

    public AnchoredTranslate(ElementKey elementKey, ElementKey elementKey2) {
        this.matcher = elementKey;
        this.anchor = elementKey2;
    }

    public static final Offset transform_wEWqZic$anchorOffsetIn(Element element, ContentKey contentKey) {
        Element.State state = (Element.State) element.stateByContent.get(contentKey);
        if (state == null) {
            return null;
        }
        long m729getTargetOffsetF1C5BW0 = state.m729getTargetOffsetF1C5BW0();
        Offset offset = new Offset(m729getTargetOffsetF1C5BW0);
        if ((m729getTargetOffsetF1C5BW0 & 9223372034707292159L) != 9205357640488583168L) {
            return offset;
        }
        return null;
    }

    public static final void transform_wEWqZic$throwException(AnchoredTranslate anchoredTranslate, ContentKey contentKey) {
        ElementKey elementKey = anchoredTranslate.anchor;
        throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n        Anchor " + elementKey.debugName + " does not have a target state in content " + (contentKey != null ? contentKey.debugName : null) + ".\n        This either means that it was not composed at all during the transition or that it was\n        composed too late, for instance during layout/subcomposition. To avoid flickers in\n        AnchoredTranslate, you should make sure that the composition and layout of anchor is *not*\n        deferred, for instance by moving it out of lazy layouts.\n    ").toString());
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final ElementMatcher getMatcher() {
        return this.matcher;
    }

    @Override // com.android.compose.animation.scene.transformation.PropertyTransformation
    public final Object transform(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, Element.State state, TransitionState.Transition transition, Object obj) {
        long j;
        long j2;
        Offset offset = (Offset) obj;
        Element element2 = (Element) sceneTransitionLayoutImpl.elements.get(this.anchor);
        if (element2 == null) {
            transform_wEWqZic$throwException(this, null);
            throw null;
        }
        ContentKey contentKey2 = transition.fromContent;
        Offset transform_wEWqZic$anchorOffsetIn = transform_wEWqZic$anchorOffsetIn(element2, contentKey2);
        if (transform_wEWqZic$anchorOffsetIn == null) {
            transform_wEWqZic$throwException(this, contentKey2);
            throw null;
        }
        ContentKey contentKey3 = transition.toContent;
        Offset transform_wEWqZic$anchorOffsetIn2 = transform_wEWqZic$anchorOffsetIn(element2, contentKey3);
        if (transform_wEWqZic$anchorOffsetIn2 == null) {
            transform_wEWqZic$throwException(this, contentKey3);
            throw null;
        }
        long m314minusMKHz9U = Offset.m314minusMKHz9U(transform_wEWqZic$anchorOffsetIn2.packedValue, transform_wEWqZic$anchorOffsetIn.packedValue);
        boolean equals = contentKey.equals(contentKey3);
        long j3 = offset.packedValue;
        if (equals) {
            float intBitsToFloat = Float.intBitsToFloat((int) (j3 >> 32)) - Float.intBitsToFloat((int) (m314minusMKHz9U >> 32));
            float intBitsToFloat2 = Float.intBitsToFloat((int) (j3 & 4294967295L)) - Float.intBitsToFloat((int) (m314minusMKHz9U & 4294967295L));
            j = Float.floatToRawIntBits(intBitsToFloat) << 32;
            j2 = Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L;
        } else {
            float intBitsToFloat3 = Float.intBitsToFloat((int) (m314minusMKHz9U >> 32)) + Float.intBitsToFloat((int) (j3 >> 32));
            float intBitsToFloat4 = Float.intBitsToFloat((int) (m314minusMKHz9U & 4294967295L)) + Float.intBitsToFloat((int) (j3 & 4294967295L));
            long floatToRawIntBits = Float.floatToRawIntBits(intBitsToFloat3);
            long floatToRawIntBits2 = Float.floatToRawIntBits(intBitsToFloat4);
            j = floatToRawIntBits << 32;
            j2 = floatToRawIntBits2 & 4294967295L;
        }
        return new Offset(j | j2);
    }
}
