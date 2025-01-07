package androidx.compose.animation.core;

import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InfiniteTransitionKt {
    public static final InfiniteTransition.TransitionAnimationState animateFloat(final InfiniteTransition infiniteTransition, float f, final InfiniteRepeatableSpec infiniteRepeatableSpec, String str, Composer composer, int i) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Float valueOf = Float.valueOf(0.0f);
        final Float valueOf2 = Float.valueOf(f);
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        int i2 = (i & 1022) | 229376;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new InfiniteTransition.TransitionAnimationState(infiniteTransition, valueOf, valueOf2, infiniteRepeatableSpec);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final InfiniteTransition.TransitionAnimationState transitionAnimationState = (InfiniteTransition.TransitionAnimationState) rememberedValue;
        boolean z = true;
        if ((((i2 & 896) ^ 384) <= 256 || !composerImpl.changedInstance(valueOf2)) && (i2 & 384) != 256) {
            z = false;
        }
        boolean changedInstance = composerImpl.changedInstance(infiniteRepeatableSpec) | z;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function0() { // from class: androidx.compose.animation.core.InfiniteTransitionKt$animateValue$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    if (!Intrinsics.areEqual(valueOf, transitionAnimationState.initialValue) || !Intrinsics.areEqual(valueOf2, transitionAnimationState.targetValue)) {
                        InfiniteTransition.TransitionAnimationState transitionAnimationState2 = transitionAnimationState;
                        Object obj = valueOf;
                        Object obj2 = valueOf2;
                        InfiniteRepeatableSpec infiniteRepeatableSpec2 = infiniteRepeatableSpec;
                        transitionAnimationState2.initialValue = obj;
                        transitionAnimationState2.targetValue = obj2;
                        TwoWayConverter twoWayConverter2 = VectorConvertersKt.FloatToVector;
                        transitionAnimationState2.animation = new TargetBasedAnimation(infiniteRepeatableSpec2, VectorConvertersKt.FloatToVector, obj, obj2, null);
                        ((SnapshotMutableStateImpl) transitionAnimationState2.this$0.refreshChildNeeded$delegate).setValue(Boolean.TRUE);
                        transitionAnimationState2.isFinished = false;
                        transitionAnimationState2.startOnTheNextFrame = true;
                    }
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.SideEffect((Function0) rememberedValue2, composerImpl);
        boolean changedInstance2 = composerImpl.changedInstance(infiniteTransition);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (changedInstance2 || rememberedValue3 == composer$Companion$Empty$1) {
            rememberedValue3 = new Function1() { // from class: androidx.compose.animation.core.InfiniteTransitionKt$animateValue$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    InfiniteTransition infiniteTransition2 = InfiniteTransition.this;
                    infiniteTransition2._animations.add(transitionAnimationState);
                    ((SnapshotMutableStateImpl) infiniteTransition2.refreshChildNeeded$delegate).setValue(Boolean.TRUE);
                    final InfiniteTransition infiniteTransition3 = InfiniteTransition.this;
                    final InfiniteTransition.TransitionAnimationState transitionAnimationState2 = transitionAnimationState;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.InfiniteTransitionKt$animateValue$2$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            InfiniteTransition.this._animations.remove(transitionAnimationState2);
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        EffectsKt.DisposableEffect(transitionAnimationState, (Function1) rememberedValue3, composerImpl);
        return transitionAnimationState;
    }
}
