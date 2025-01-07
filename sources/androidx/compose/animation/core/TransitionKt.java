package androidx.compose.animation.core;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.EnterExitState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TransitionKt {
    static {
        LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.animation.core.TransitionKt$SeekableStateObserver$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.compose.animation.core.TransitionKt$SeekableStateObserver$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Function0) obj).invoke();
                        return Unit.INSTANCE;
                    }
                });
                snapshotStateObserver.applyUnsubscribe = Snapshot.Companion.registerApplyObserver(snapshotStateObserver.applyObserver);
                return snapshotStateObserver;
            }
        });
    }

    public static final Transition createChildTransitionInternal(final Transition transition, EnterExitState enterExitState, EnterExitState enterExitState2, Composer composer, int i) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && ((ComposerImpl) composer).changed(transition)) || (i & 6) == 4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z2 || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new Transition(new MutableTransitionState(enterExitState), transition, ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), transition.label, " > EnterExitTransition"));
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final Transition transition2 = (Transition) rememberedValue;
        if ((i2 <= 4 || !composerImpl.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean changed = composerImpl.changed(transition2) | z;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Transition transition3 = Transition.this;
                    transition3._transitions.add(transition2);
                    final Transition transition4 = Transition.this;
                    final Transition transition5 = transition2;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Transition.this._transitions.remove(transition5);
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.DisposableEffect(transition2, (Function1) rememberedValue2, composerImpl);
        if (transition.isSeeking()) {
            transition2.seek(enterExitState, enterExitState2);
        } else {
            transition2.updateTarget$animation_core_release(enterExitState2);
            ((SnapshotMutableStateImpl) transition2.isSeeking$delegate).setValue(Boolean.FALSE);
        }
        return transition2;
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r7v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r9v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public static final Transition.DeferredAnimation createDeferredAnimation(final Transition transition, TwoWayConverter twoWayConverter, String str, Composer composer, int i, int i2) {
        Transition.DeferredAnimation.DeferredAnimationData deferredAnimationData;
        if ((i2 & 2) != 0) {
            str = "DeferredAnimation";
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && ((ComposerImpl) composer).changed(transition)) || (i & 6) == 4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z2 || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = transition.new DeferredAnimation(twoWayConverter, str);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final Transition.DeferredAnimation deferredAnimation = (Transition.DeferredAnimation) rememberedValue;
        if ((i3 <= 4 || !composerImpl.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean changedInstance = composerImpl.changedInstance(deferredAnimation) | z;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    final Transition transition2 = Transition.this;
                    final Transition.DeferredAnimation deferredAnimation2 = deferredAnimation;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Transition transition3 = Transition.this;
                            transition3.getClass();
                            Transition.DeferredAnimation.DeferredAnimationData deferredAnimationData2 = (Transition.DeferredAnimation.DeferredAnimationData) ((SnapshotMutableStateImpl) deferredAnimation2.data$delegate).getValue();
                            if (deferredAnimationData2 != null) {
                                transition3._animations.remove(deferredAnimationData2.animation);
                            }
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.DisposableEffect(deferredAnimation, (Function1) rememberedValue2, composerImpl);
        if (transition.isSeeking() && (deferredAnimationData = (Transition.DeferredAnimation.DeferredAnimationData) ((SnapshotMutableStateImpl) deferredAnimation.data$delegate).getValue()) != null) {
            ?? r7 = deferredAnimationData.targetValueByState;
            Transition transition2 = Transition.this;
            deferredAnimationData.animation.updateInitialAndTargetValue$animation_core_release(r7.invoke(transition2.getSegment().getInitialState()), deferredAnimationData.targetValueByState.invoke(transition2.getSegment().getTargetState()), (FiniteAnimationSpec) deferredAnimationData.transitionSpec.invoke(transition2.getSegment()));
        }
        return deferredAnimation;
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public static final Transition.TransitionAnimationState createTransitionAnimation(final Transition transition, Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec, TwoWayConverter twoWayConverter, Composer composer, int i) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && ((ComposerImpl) composer).changed(transition)) || (i & 6) == 4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z2 || rememberedValue == composer$Companion$Empty$1) {
            AnimationVector animationVector = (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj2);
            animationVector.reset$animation_core_release();
            rememberedValue = transition.new TransitionAnimationState(obj, animationVector, twoWayConverter);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final Transition.TransitionAnimationState transitionAnimationState = (Transition.TransitionAnimationState) rememberedValue;
        if (transition.isSeeking()) {
            transitionAnimationState.updateInitialAndTargetValue$animation_core_release(obj, obj2, finiteAnimationSpec);
        } else {
            transitionAnimationState.updateTargetValue$animation_core_release(obj2, finiteAnimationSpec);
        }
        if ((i2 <= 4 || !composerImpl.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean changed = composerImpl.changed(transitionAnimationState) | z;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    Transition transition2 = Transition.this;
                    transition2._animations.add(transitionAnimationState);
                    final Transition transition3 = Transition.this;
                    final Transition.TransitionAnimationState transitionAnimationState2 = transitionAnimationState;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Transition.this._animations.remove(transitionAnimationState2);
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.DisposableEffect(transitionAnimationState, (Function1) rememberedValue2, composerImpl);
        return transitionAnimationState;
    }

    public static final Transition updateTransition(Object obj, String str, Composer composer, int i) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new Transition(new MutableTransitionState(obj), null, str);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final Transition transition = (Transition) rememberedValue;
        transition.animateTo$animation_core_release(obj, composerImpl, (i & 8) | 48 | (i & 14));
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    final Transition transition2 = Transition.this;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Transition transition3 = Transition.this;
                            transition3.onTransitionEnd$animation_core_release();
                            transition3.transitionState.getClass();
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.DisposableEffect(transition, (Function1) rememberedValue2, composerImpl);
        return transition;
    }
}
