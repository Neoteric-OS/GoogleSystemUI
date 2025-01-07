package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ColorPropertyValues extends PropertyValues {
    @Override // androidx.compose.animation.graphics.vector.PropertyValues
    public final State createState(Transition transition, String str, int i, Composer composer, int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-2133734837);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        PropertyValues$createAnimationSpec$1 propertyValues$createAnimationSpec$1 = new PropertyValues$createAnimationSpec$1(this, i);
        int i3 = (i2 & 14) | ((i2 << 3) & 896);
        boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) transition.targetState$delegate).getValue()).booleanValue();
        composerImpl.startReplaceGroup(1880460593);
        long j = booleanValue ? ((Color) ((Keyframe) CollectionsKt.last(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.last(this.timestamps)).holder).animatorKeyframes)).value).value : ((Color) ((Keyframe) CollectionsKt.first(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.first(this.timestamps)).holder).animatorKeyframes)).value).value;
        composerImpl.end(false);
        ColorSpace m366getColorSpaceimpl = Color.m366getColorSpaceimpl(j);
        boolean changed = composerImpl.changed(m366getColorSpaceimpl);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = (TwoWayConverter) ColorVectorConverterKt.ColorToVector.invoke(m366getColorSpaceimpl);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        TwoWayConverter twoWayConverter = (TwoWayConverter) rememberedValue;
        int i4 = ((i3 << 3) & 7168) | (i3 & 14);
        boolean booleanValue2 = ((Boolean) transition.getCurrentState()).booleanValue();
        composerImpl.startReplaceGroup(1880460593);
        long j2 = booleanValue2 ? ((Color) ((Keyframe) CollectionsKt.last(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.last(this.timestamps)).holder).animatorKeyframes)).value).value : ((Color) ((Keyframe) CollectionsKt.first(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.first(this.timestamps)).holder).animatorKeyframes)).value).value;
        composerImpl.end(false);
        Color color = new Color(j2);
        boolean booleanValue3 = ((Boolean) ((SnapshotMutableStateImpl) transition.targetState$delegate).getValue()).booleanValue();
        composerImpl.startReplaceGroup(1880460593);
        long j3 = booleanValue3 ? ((Color) ((Keyframe) CollectionsKt.last(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.last(this.timestamps)).holder).animatorKeyframes)).value).value : ((Color) ((Keyframe) CollectionsKt.first(((PropertyValuesHolderColor) ((Timestamp) CollectionsKt.first(this.timestamps)).holder).animatorKeyframes)).value).value;
        composerImpl.end(false);
        Transition.TransitionAnimationState createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, color, new Color(j3), (FiniteAnimationSpec) propertyValues$createAnimationSpec$1.invoke(transition.getSegment(), composerImpl, 0), twoWayConverter, composerImpl, (i4 & 14) | ((i4 << 6) & 458752));
        composerImpl.end(false);
        return createTransitionAnimation;
    }
}
