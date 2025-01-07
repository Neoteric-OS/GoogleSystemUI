package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.State;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FloatPropertyValues extends PropertyValues {
    @Override // androidx.compose.animation.graphics.vector.PropertyValues
    public final State createState(Transition transition, String str, int i, Composer composer, int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(2006928772);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        PropertyValues$createAnimationSpec$1 propertyValues$createAnimationSpec$1 = new PropertyValues$createAnimationSpec$1(this, i);
        int i3 = (i2 & 14) | ((i2 << 3) & 896);
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        int i4 = ((i3 << 3) & 7168) | (i3 & 14);
        boolean booleanValue = ((Boolean) transition.getCurrentState()).booleanValue();
        composerImpl.startReplaceGroup(-1743438372);
        float floatValue = booleanValue ? ((Number) ((Keyframe) CollectionsKt.last(((PropertyValuesHolderFloat) ((Timestamp) CollectionsKt.last(this.timestamps)).holder).animatorKeyframes)).value).floatValue() : ((Number) ((Keyframe) CollectionsKt.first(((PropertyValuesHolderFloat) ((Timestamp) CollectionsKt.first(this.timestamps)).holder).animatorKeyframes)).value).floatValue();
        composerImpl.end(false);
        Float valueOf = Float.valueOf(floatValue);
        boolean booleanValue2 = ((Boolean) ((SnapshotMutableStateImpl) transition.targetState$delegate).getValue()).booleanValue();
        composerImpl.startReplaceGroup(-1743438372);
        float floatValue2 = booleanValue2 ? ((Number) ((Keyframe) CollectionsKt.last(((PropertyValuesHolderFloat) ((Timestamp) CollectionsKt.last(this.timestamps)).holder).animatorKeyframes)).value).floatValue() : ((Number) ((Keyframe) CollectionsKt.first(((PropertyValuesHolderFloat) ((Timestamp) CollectionsKt.first(this.timestamps)).holder).animatorKeyframes)).value).floatValue();
        composerImpl.end(false);
        Transition.TransitionAnimationState createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, valueOf, Float.valueOf(floatValue2), (FiniteAnimationSpec) propertyValues$createAnimationSpec$1.invoke(transition.getSegment(), composerImpl, 0), twoWayConverter, composerImpl, (i4 & 14) | ((i4 << 6) & 458752));
        composerImpl.end(false);
        return createTransitionAnimation;
    }
}
