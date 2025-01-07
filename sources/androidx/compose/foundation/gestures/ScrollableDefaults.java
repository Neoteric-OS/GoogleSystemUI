package androidx.compose.foundation.gestures;

import android.content.Context;
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect;
import androidx.compose.foundation.NoOpOverscrollEffect;
import androidx.compose.foundation.OverscrollConfiguration;
import androidx.compose.foundation.OverscrollConfiguration_androidKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollableDefaults {
    public static DefaultFlingBehavior flingBehavior(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        DecayAnimationSpec rememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer);
        ComposerImpl composerImpl = (ComposerImpl) composer;
        boolean changed = composerImpl.changed(rememberSplineBasedDecay);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new DefaultFlingBehavior(rememberSplineBasedDecay);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        return (DefaultFlingBehavior) rememberedValue;
    }

    public static OverscrollEffect overscrollEffect(Composer composer) {
        OverscrollEffect overscrollEffect;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
        OverscrollConfiguration overscrollConfiguration = (OverscrollConfiguration) composerImpl.consume(OverscrollConfiguration_androidKt.LocalOverscrollConfiguration);
        composerImpl.startReplaceGroup(1852285245);
        if (overscrollConfiguration != null) {
            boolean changed = composerImpl.changed(context) | composerImpl.changed(density) | composerImpl.changed(overscrollConfiguration);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new AndroidEdgeEffectOverscrollEffect(context, density, overscrollConfiguration);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            overscrollEffect = (AndroidEdgeEffectOverscrollEffect) rememberedValue;
        } else {
            overscrollEffect = NoOpOverscrollEffect.INSTANCE;
        }
        composerImpl.end(false);
        return overscrollEffect;
    }

    public static boolean reverseDirection(LayoutDirection layoutDirection, Orientation orientation, boolean z) {
        return (layoutDirection != LayoutDirection.Rtl || orientation == Orientation.Vertical) ? !z : z;
    }
}
