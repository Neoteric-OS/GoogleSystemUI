package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimateAsStateKt {
    public static final SpringSpec defaultAnimation = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);
    public static final SpringSpec dpDefaultSpring;
    public static final SpringSpec intDefaultSpring;

    static {
        Map map = VisibilityThresholdsKt.VisibilityThresholdMap;
        dpDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, new Dp(0.1f), 3);
        Float.floatToRawIntBits(0.5f);
        Float.floatToRawIntBits(0.5f);
        Float.floatToRawIntBits(0.5f);
        Float.floatToRawIntBits(0.5f);
        intDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3);
    }

    /* renamed from: animateDpAsState-AjpBEmI, reason: not valid java name */
    public static final State m8animateDpAsStateAjpBEmI(float f, FiniteAnimationSpec finiteAnimationSpec, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            finiteAnimationSpec = dpDefaultSpring;
        }
        FiniteAnimationSpec finiteAnimationSpec2 = finiteAnimationSpec;
        if ((i2 & 4) != 0) {
            str = "DpAnimation";
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return animateValueAsState(new Dp(f), VectorConvertersKt.DpToVector, finiteAnimationSpec2, null, str, null, composer, ((i << 3) & 896) | ((i << 6) & 57344), 8);
    }

    public static final State animateFloatAsState(float f, FiniteAnimationSpec finiteAnimationSpec, String str, Function1 function1, Composer composer, int i, int i2) {
        int i3 = i2 & 2;
        SpringSpec springSpec = defaultAnimation;
        FiniteAnimationSpec finiteAnimationSpec2 = i3 != 0 ? springSpec : finiteAnimationSpec;
        String str2 = (i2 & 8) != 0 ? "FloatAnimation" : str;
        Function1 function12 = (i2 & 16) != 0 ? null : function1;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(313401822);
        if (finiteAnimationSpec2 == springSpec) {
            boolean changed = composerImpl.changed(0.01f);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, Float.valueOf(0.01f), 3);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            finiteAnimationSpec2 = (SpringSpec) rememberedValue;
        }
        FiniteAnimationSpec finiteAnimationSpec3 = finiteAnimationSpec2;
        composerImpl.end(false);
        return animateValueAsState(Float.valueOf(f), VectorConvertersKt.FloatToVector, finiteAnimationSpec3, Float.valueOf(0.01f), str2, function12, composerImpl, (i << 3) & 516096, 0);
    }

    public static final State animateValueAsState(final Object obj, TwoWayConverter twoWayConverter, AnimationSpec animationSpec, Object obj2, String str, Function1 function1, Composer composer, int i, int i2) {
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if ((i2 & 8) != 0) {
            obj2 = null;
        }
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Animatable(obj, twoWayConverter, obj2);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        Animatable animatable = (Animatable) rememberedValue2;
        MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composerImpl);
        if (obj2 != null && (animationSpec instanceof SpringSpec)) {
            SpringSpec springSpec = (SpringSpec) animationSpec;
            if (!Intrinsics.areEqual(springSpec.visibilityThreshold, obj2)) {
                animationSpec = new SpringSpec(springSpec.dampingRatio, springSpec.stiffness, obj2);
            }
        }
        MutableState rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(animationSpec, composerImpl);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (rememberedValue3 == composer$Companion$Empty$1) {
            rememberedValue3 = ChannelKt.Channel$default(-1, null, null, 6);
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        final Channel channel = (Channel) rememberedValue3;
        boolean changedInstance = composerImpl.changedInstance(channel) | composerImpl.changedInstance(obj);
        Object rememberedValue4 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue4 == composer$Companion$Empty$1) {
            rememberedValue4 = new Function0() { // from class: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Channel.this.mo1790trySendJP2dKIU(obj);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue4);
        }
        EffectsKt.SideEffect((Function0) rememberedValue4, composerImpl);
        boolean changedInstance2 = composerImpl.changedInstance(channel) | composerImpl.changedInstance(animatable) | composerImpl.changed(rememberUpdatedState2) | composerImpl.changed(rememberUpdatedState);
        Object rememberedValue5 = composerImpl.rememberedValue();
        if (changedInstance2 || rememberedValue5 == composer$Companion$Empty$1) {
            rememberedValue5 = new AnimateAsStateKt$animateValueAsState$3$1(channel, animatable, rememberUpdatedState2, rememberUpdatedState, null);
            composerImpl.updateRememberedValue(rememberedValue5);
        }
        EffectsKt.LaunchedEffect(composerImpl, channel, (Function2) rememberedValue5);
        State state = (State) mutableState.getValue();
        return state == null ? animatable.internalState : state;
    }
}
