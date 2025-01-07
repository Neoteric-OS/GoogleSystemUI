package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EnterExitTransitionKt {
    public static final TwoWayConverter TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long j = ((TransformOrigin) obj).packedValue;
            return new AnimationVector2D(TransformOrigin.m399getPivotFractionXimpl(j), TransformOrigin.m400getPivotFractionYimpl(j));
        }
    }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D animationVector2D = (AnimationVector2D) obj;
            return new TransformOrigin(TransformOriginKt.TransformOrigin(animationVector2D.v1, animationVector2D.v2));
        }
    });
    public static final SpringSpec DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5);
    public static final SpringSpec DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntOffset(VisibilityThresholdsKt.getVisibilityThreshold()), 1);
    public static final SpringSpec DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntSize(VisibilityThresholdsKt.getVisibilityThreshold$1()), 1);

    public static final Modifier createModifier(Transition transition, EnterTransition enterTransition, ExitTransition exitTransition, String str, Composer composer, int i) {
        int i2;
        ChangeSize changeSize;
        EnterTransition enterTransition2;
        Transition.DeferredAnimation deferredAnimation;
        Transition.DeferredAnimation deferredAnimation2;
        Transition.DeferredAnimation deferredAnimation3;
        ChangeSize changeSize2;
        boolean z;
        int i3;
        boolean z2;
        Transition.DeferredAnimation deferredAnimation4;
        Transition.DeferredAnimation deferredAnimation5;
        Transition.DeferredAnimation deferredAnimation6;
        EnterTransition enterTransition3;
        final EnterExitTransitionKt$createModifier$1 enterExitTransitionKt$createModifier$1 = new Function0() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.TRUE;
            }
        };
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i4 = i & 14;
        boolean z3 = ((i4 ^ 6) > 4 && ((ComposerImpl) composer).changed(transition)) || (i & 6) == 4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (z3 || rememberedValue == obj) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(enterTransition);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        Object currentState = transition.getCurrentState();
        SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) transition.targetState$delegate;
        Object value = snapshotMutableStateImpl.getValue();
        EnterExitState enterExitState = EnterExitState.Visible;
        if (currentState == value && transition.getCurrentState() == enterExitState) {
            if (transition.isSeeking()) {
                mutableState.setValue(enterTransition);
            } else {
                mutableState.setValue(EnterTransition.None);
            }
        } else if (snapshotMutableStateImpl.getValue() == enterExitState) {
            mutableState.setValue(((EnterTransition) mutableState.getValue()).plus(enterTransition));
        }
        EnterTransition enterTransition4 = (EnterTransition) mutableState.getValue();
        int i5 = i >> 3;
        int i6 = (i5 & 112) | i4;
        boolean z4 = (((i6 & 14) ^ 6) > 4 && composerImpl.changed(transition)) || (i6 & 6) == 4;
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (z4 || rememberedValue2 == obj) {
            rememberedValue2 = SnapshotStateKt.mutableStateOf$default(exitTransition);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        MutableState mutableState2 = (MutableState) rememberedValue2;
        if (transition.getCurrentState() == snapshotMutableStateImpl.getValue() && transition.getCurrentState() == enterExitState) {
            if (transition.isSeeking()) {
                mutableState2.setValue(exitTransition);
            } else {
                mutableState2.setValue(ExitTransition.None);
            }
        } else if (snapshotMutableStateImpl.getValue() != enterExitState) {
            mutableState2.setValue(((ExitTransition) mutableState2.getValue()).plus(exitTransition));
        }
        ExitTransition exitTransition2 = (ExitTransition) mutableState2.getValue();
        TransitionData transitionData = ((EnterTransitionImpl) enterTransition4).data;
        boolean z5 = (transitionData.slide == null && ((ExitTransitionImpl) exitTransition2).data.slide == null) ? false : true;
        ChangeSize changeSize3 = transitionData.changeSize;
        boolean z6 = (changeSize3 == null && ((ExitTransitionImpl) exitTransition2).data.changeSize == null) ? false : true;
        composerImpl.startReplaceGroup(-165037057);
        if (z5) {
            TwoWayConverter twoWayConverter = VectorConvertersKt.IntOffsetToVector;
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (rememberedValue3 == obj) {
                rememberedValue3 = str + " slide";
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            i2 = i5;
            changeSize = changeSize3;
            enterTransition2 = enterTransition4;
            deferredAnimation = TransitionKt.createDeferredAnimation(transition, twoWayConverter, (String) rememberedValue3, composerImpl, i4 | 384, 0);
        } else {
            i2 = i5;
            changeSize = changeSize3;
            enterTransition2 = enterTransition4;
            deferredAnimation = null;
        }
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-165030862);
        if (z6) {
            TwoWayConverter twoWayConverter2 = VectorConvertersKt.IntSizeToVector;
            Object rememberedValue4 = composerImpl.rememberedValue();
            if (rememberedValue4 == obj) {
                rememberedValue4 = str + " shrink/expand";
                composerImpl.updateRememberedValue(rememberedValue4);
            }
            deferredAnimation2 = TransitionKt.createDeferredAnimation(transition, twoWayConverter2, (String) rememberedValue4, composerImpl, i4 | 384, 0);
        } else {
            deferredAnimation2 = null;
        }
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-165024913);
        if (z6) {
            TwoWayConverter twoWayConverter3 = VectorConvertersKt.IntOffsetToVector;
            Object rememberedValue5 = composerImpl.rememberedValue();
            if (rememberedValue5 == obj) {
                rememberedValue5 = str + " InterruptionHandlingOffset";
                composerImpl.updateRememberedValue(rememberedValue5);
            }
            deferredAnimation3 = TransitionKt.createDeferredAnimation(transition, twoWayConverter3, (String) rememberedValue5, composerImpl, i4 | 384, 0);
        } else {
            deferredAnimation3 = null;
        }
        composerImpl.end(false);
        ChangeSize changeSize4 = changeSize;
        if ((changeSize4 == null || changeSize4.clip) && (((changeSize2 = ((ExitTransitionImpl) exitTransition2).data.changeSize) == null || changeSize2.clip) && z6)) {
            z = false;
            i3 = i2;
        } else {
            i3 = i2;
            z = true;
        }
        int i7 = i4 | (i3 & 7168);
        boolean z7 = (transitionData.fade == null && ((ExitTransitionImpl) exitTransition2).data.fade == null) ? false : true;
        boolean z8 = (transitionData.scale == null && ((ExitTransitionImpl) exitTransition2).data.scale == null) ? false : true;
        composerImpl.startReplaceGroup(-1545796423);
        if (z7) {
            TwoWayConverter twoWayConverter4 = VectorConvertersKt.FloatToVector;
            Object rememberedValue6 = composerImpl.rememberedValue();
            if (rememberedValue6 == obj) {
                rememberedValue6 = str + " alpha";
                composerImpl.updateRememberedValue(rememberedValue6);
            }
            z2 = z;
            deferredAnimation4 = TransitionKt.createDeferredAnimation(transition, twoWayConverter4, (String) rememberedValue6, composerImpl, (i7 & 14) | 384, 0);
        } else {
            z2 = z;
            deferredAnimation4 = null;
        }
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-1545788807);
        if (z8) {
            TwoWayConverter twoWayConverter5 = VectorConvertersKt.FloatToVector;
            Object rememberedValue7 = composerImpl.rememberedValue();
            if (rememberedValue7 == obj) {
                rememberedValue7 = str + " scale";
                composerImpl.updateRememberedValue(rememberedValue7);
            }
            deferredAnimation5 = deferredAnimation4;
            deferredAnimation6 = TransitionKt.createDeferredAnimation(transition, twoWayConverter5, (String) rememberedValue7, composerImpl, (i7 & 14) | 384, 0);
        } else {
            deferredAnimation5 = deferredAnimation4;
            deferredAnimation6 = null;
        }
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-1545780868);
        Transition.DeferredAnimation createDeferredAnimation = z8 ? TransitionKt.createDeferredAnimation(transition, TransformOriginVectorConverter, "TransformOriginInterruptionHandling", composerImpl, (i7 & 14) | 384, 0) : null;
        composerImpl.end(false);
        Transition.DeferredAnimation deferredAnimation7 = deferredAnimation5;
        EnterTransition enterTransition5 = enterTransition2;
        boolean changedInstance = composerImpl.changedInstance(deferredAnimation7) | composerImpl.changed(enterTransition5) | composerImpl.changed(exitTransition2) | composerImpl.changedInstance(deferredAnimation6) | ((((i7 & 14) ^ 6) > 4 && composerImpl.changed(transition)) || (i7 & 6) == 4) | composerImpl.changedInstance(createDeferredAnimation);
        Object rememberedValue8 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue8 == obj) {
            enterTransition3 = enterTransition5;
            Object enterExitTransitionKt$$ExternalSyntheticLambda0 = new EnterExitTransitionKt$$ExternalSyntheticLambda0(deferredAnimation7, deferredAnimation6, transition, enterTransition5, exitTransition2, createDeferredAnimation);
            composerImpl.updateRememberedValue(enterExitTransitionKt$$ExternalSyntheticLambda0);
            rememberedValue8 = enterExitTransitionKt$$ExternalSyntheticLambda0;
        } else {
            enterTransition3 = enterTransition5;
        }
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = (GraphicsLayerBlockForEnterExit) rememberedValue8;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        final boolean z9 = z2;
        boolean changed = composerImpl.changed(z9) | ((((i & 7168) ^ 3072) > 2048 && composerImpl.changed(enterExitTransitionKt$createModifier$1)) || (i & 3072) == 2048);
        Object rememberedValue9 = composerImpl.rememberedValue();
        if (changed || rememberedValue9 == obj) {
            rememberedValue9 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    ((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj2)).setClip(!z9 && ((Boolean) enterExitTransitionKt$createModifier$1.invoke()).booleanValue());
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue9);
        }
        return GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) rememberedValue9).then(new EnterExitTransitionElement(transition, deferredAnimation2, deferredAnimation3, deferredAnimation, enterTransition3, exitTransition2, enterExitTransitionKt$createModifier$1, graphicsLayerBlockForEnterExit));
    }

    public static EnterTransition expandVertically$default(TweenSpec tweenSpec, final Function1 function1, int i) {
        BiasAlignment.Vertical vertical = Alignment.Companion.Top;
        FiniteAnimationSpec finiteAnimationSpec = tweenSpec;
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntSize(VisibilityThresholdsKt.getVisibilityThreshold$1()), 1);
        }
        int i2 = i & 2;
        BiasAlignment.Vertical vertical2 = Alignment.Companion.Bottom;
        BiasAlignment.Vertical vertical3 = i2 != 0 ? vertical2 : vertical;
        boolean z = (i & 4) != 0;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    ((Number) obj).intValue();
                    return 0;
                }
            };
        }
        return new EnterTransitionImpl(new TransitionData((Fade) null, (Slide) null, new ChangeSize(Intrinsics.areEqual(vertical3, vertical) ? Alignment.Companion.TopCenter : Intrinsics.areEqual(vertical3, vertical2) ? Alignment.Companion.BottomCenter : Alignment.Companion.Center, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new IntSize((((Number) Function1.this.invoke(Integer.valueOf((int) (r0 & 4294967295L)))).intValue() & 4294967295L) | (((int) (((IntSize) obj).packedValue >> 32)) << 32));
            }
        }, finiteAnimationSpec, z), (Scale) null, (Map) null, 59));
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec finiteAnimationSpec) {
        return new EnterTransitionImpl(new TransitionData(new Fade(finiteAnimationSpec), (Slide) null, (ChangeSize) null, (Scale) null, (Map) null, 62));
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, int i) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5);
        }
        return fadeIn(finiteAnimationSpec);
    }

    public static ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, int i) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5);
        }
        return new ExitTransitionImpl(new TransitionData(new Fade(finiteAnimationSpec), (Slide) null, (ChangeSize) null, (Scale) null, (Map) null, 62));
    }

    /* renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static EnterTransition m4scaleInL8ZKhE$default(TweenSpec tweenSpec, float f) {
        return new EnterTransitionImpl(new TransitionData((Fade) null, (Slide) null, (ChangeSize) null, new Scale(f, TransformOrigin.Center, tweenSpec), (Map) null, 55));
    }

    /* renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static ExitTransition m5scaleOutL8ZKhE$default(TweenSpec tweenSpec, float f) {
        return new ExitTransitionImpl(new TransitionData((Fade) null, (Slide) null, (ChangeSize) null, new Scale(f, TransformOrigin.Center, tweenSpec), (Map) null, 55));
    }

    public static ExitTransition shrinkOut$default() {
        return new ExitTransitionImpl(new TransitionData((Fade) null, (Slide) null, new ChangeSize(Alignment.Companion.BottomEnd, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkOut$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((IntSize) obj).packedValue;
                long j2 = 0;
                return new IntSize((j2 & 4294967295L) | (j2 << 32));
            }
        }, AnimationSpecKt.spring$default(0.0f, 400.0f, new IntSize(VisibilityThresholdsKt.getVisibilityThreshold$1()), 1), true), (Scale) null, (Map) null, 59));
    }

    public static ExitTransition shrinkVertically$default(TweenSpec tweenSpec, final Function1 function1, int i) {
        BiasAlignment.Vertical vertical = Alignment.Companion.Top;
        FiniteAnimationSpec finiteAnimationSpec = tweenSpec;
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntSize(VisibilityThresholdsKt.getVisibilityThreshold$1()), 1);
        }
        int i2 = i & 2;
        BiasAlignment.Vertical vertical2 = Alignment.Companion.Bottom;
        BiasAlignment.Vertical vertical3 = i2 != 0 ? vertical2 : vertical;
        boolean z = (i & 4) != 0;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    ((Number) obj).intValue();
                    return 0;
                }
            };
        }
        return new ExitTransitionImpl(new TransitionData((Fade) null, (Slide) null, new ChangeSize(Intrinsics.areEqual(vertical3, vertical) ? Alignment.Companion.TopCenter : Intrinsics.areEqual(vertical3, vertical2) ? Alignment.Companion.BottomCenter : Alignment.Companion.Center, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new IntSize((((Number) Function1.this.invoke(Integer.valueOf((int) (r0 & 4294967295L)))).intValue() & 4294967295L) | (((int) (((IntSize) obj).packedValue >> 32)) << 32));
            }
        }, finiteAnimationSpec, z), (Scale) null, (Map) null, 59));
    }

    public static final EnterTransition slideInVertically(TweenSpec tweenSpec, final Function1 function1) {
        return new EnterTransitionImpl(new TransitionData((Fade) null, new Slide(tweenSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new IntOffset((((Number) Function1.this.invoke(Integer.valueOf((int) (((IntSize) obj).packedValue & 4294967295L)))).intValue() & 4294967295L) | (0 << 32));
            }
        }), (ChangeSize) null, (Scale) null, (Map) null, 61));
    }

    public static final ExitTransition slideOutVertically(TweenSpec tweenSpec, final Function1 function1) {
        return new ExitTransitionImpl(new TransitionData((Fade) null, new Slide(tweenSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new IntOffset((((Number) Function1.this.invoke(Integer.valueOf((int) (((IntSize) obj).packedValue & 4294967295L)))).intValue() & 4294967295L) | (0 << 32));
            }
        }), (ChangeSize) null, (Scale) null, (Map) null, 61));
    }
}
