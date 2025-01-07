package androidx.compose.animation;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SingleValueAnimationKt {
    public static final SpringSpec colorDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);

    /* renamed from: animateColorAsState-euL9pac, reason: not valid java name */
    public static final State m7animateColorAsStateeuL9pac(long j, FiniteAnimationSpec finiteAnimationSpec, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            finiteAnimationSpec = colorDefaultSpring;
        }
        FiniteAnimationSpec finiteAnimationSpec2 = finiteAnimationSpec;
        if ((i2 & 4) != 0) {
            str = "ColorAnimation";
        }
        String str2 = str;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        boolean changed = composerImpl.changed(Color.m366getColorSpaceimpl(j));
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = (TwoWayConverter) ((ColorVectorConverterKt$ColorToVector$1) ColorVectorConverterKt.ColorToVector).invoke(Color.m366getColorSpaceimpl(j));
            composerImpl.updateRememberedValue(rememberedValue);
        }
        return AnimateAsStateKt.animateValueAsState(new Color(j), (TwoWayConverter) rememberedValue, finiteAnimationSpec2, null, str2, null, composerImpl, (i << 6) & 57344, 8);
    }
}
