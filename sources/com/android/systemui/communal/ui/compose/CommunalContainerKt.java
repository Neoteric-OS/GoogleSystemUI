package com.android.systemui.communal.ui.compose;

import androidx.activity.compose.PredictiveBackHandlerKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.LinearGradient;
import androidx.compose.ui.graphics.RadialGradient;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.lifecycle.compose.FlowExtKt;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.Edge;
import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutState;
import com.android.compose.animation.scene.ObservableTransitionStateKt;
import com.android.compose.animation.scene.OverscrollSpecImpl;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl$updateContents$1;
import com.android.compose.animation.scene.SceneTransitionLayoutKt;
import com.android.compose.animation.scene.SceneTransitionLayoutStateKt;
import com.android.compose.animation.scene.SceneTransitions;
import com.android.compose.animation.scene.SceneTransitionsBuilderImpl;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.SwipeDirection;
import com.android.compose.animation.scene.TransformationSpecImpl;
import com.android.compose.animation.scene.TransitionBuilderImpl;
import com.android.compose.animation.scene.TransitionDslKt;
import com.android.compose.animation.scene.TransitionKey;
import com.android.compose.animation.scene.transformation.Translate;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.communal.shared.model.CommunalBackgroundType;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.communal.shared.model.CommunalTransitionKeys;
import com.android.systemui.communal.ui.compose.extensions.ModifierExtKt$allowGestures$1;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.util.CommunalColors;
import com.android.systemui.communal.util.CommunalColorsImpl;
import com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor;
import com.android.systemui.scene.shared.model.SceneDataSourceDelegator;
import com.android.systemui.scene.ui.composable.SceneTransitionLayoutDataSource;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalContainerKt {
    public static final SceneTransitions sceneTransitions = TransitionDslKt.transitions(new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            SceneTransitionsBuilderImpl sceneTransitionsBuilderImpl = (SceneTransitionsBuilderImpl) obj;
            SceneKey sceneKey = CommunalScenes.Communal;
            TransitionKey transitionKey = CommunalTransitionKeys.SimpleFade;
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey, transitionKey, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default(250, 0, null, 6);
                    transitionBuilderImpl.fade(AllElements.INSTANCE);
                    return Unit.INSTANCE;
                }
            }, 12);
            SceneKey sceneKey2 = CommunalScenes.Blank;
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey2, transitionKey, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default((int) RangesKt.coerceIn(Duration.m1782toLongimpl(FromPrimaryBouncerTransitionInteractor.TO_GONE_DURATION, DurationUnit.MILLISECONDS), -2147483648L, 2147483647L), 0, null, 6);
                    transitionBuilderImpl.fade(AllElements.INSTANCE);
                    return Unit.INSTANCE;
                }
            }, 12);
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey, null, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default(1000, 0, null, 6);
                    TransitionBuilderImpl.translate$default(transitionBuilderImpl, Communal$Elements.Grid, Edge.End);
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, 167, 334, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.3.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            ((TransitionBuilderImpl) obj3).fade(AllElements.INSTANCE);
                            return Unit.INSTANCE;
                        }
                    }, 4);
                    return Unit.INSTANCE;
                }
            }, 14);
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey2, null, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default(1000, 0, null, 6);
                    TransitionBuilderImpl.translate$default(transitionBuilderImpl, Communal$Elements.Grid, Edge.End);
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, null, 167, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.4.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            TransitionBuilderImpl transitionBuilderImpl2 = (TransitionBuilderImpl) obj3;
                            transitionBuilderImpl2.fade(Communal$Elements.Grid);
                            transitionBuilderImpl2.fade(Communal$Elements.IndicationArea);
                            transitionBuilderImpl2.fade(Communal$Elements.LockIcon);
                            transitionBuilderImpl2.fade(Communal$Elements.StatusBar);
                            return Unit.INSTANCE;
                        }
                    }, 5);
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, 167, 334, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.4.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            ((TransitionBuilderImpl) obj3).fade(Communal$Elements.Scrim);
                            return Unit.INSTANCE;
                        }
                    }, 4);
                    return Unit.INSTANCE;
                }
            }, 14);
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey2, CommunalTransitionKeys.ToEditMode, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default(1000, 0, null, 6);
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, null, 250, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.5.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            TransitionBuilderImpl transitionBuilderImpl2 = (TransitionBuilderImpl) obj3;
                            transitionBuilderImpl2.fade(Communal$Elements.Grid);
                            transitionBuilderImpl2.fade(Communal$Elements.IndicationArea);
                            transitionBuilderImpl2.fade(Communal$Elements.LockIcon);
                            return Unit.INSTANCE;
                        }
                    }, 5);
                    transitionBuilderImpl.fade(Communal$Elements.Scrim);
                    return Unit.INSTANCE;
                }
            }, 12);
            SceneTransitionsBuilderImpl.to$default(sceneTransitionsBuilderImpl, sceneKey, CommunalTransitionKeys.FromEditMode, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$sceneTransitions$1.6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    TransitionBuilderImpl transitionBuilderImpl = (TransitionBuilderImpl) obj2;
                    transitionBuilderImpl.spec = AnimationSpecKt.tween$default(1000, 0, null, 6);
                    ElementKey elementKey = Communal$Elements.Grid;
                    float f = Dimensions.SlideOffsetY;
                    transitionBuilderImpl.getClass();
                    transitionBuilderImpl.transformation(new Translate(elementKey, 0, f));
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, null, 167, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.6.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            TransitionBuilderImpl transitionBuilderImpl2 = (TransitionBuilderImpl) obj3;
                            transitionBuilderImpl2.fade(Communal$Elements.IndicationArea);
                            transitionBuilderImpl2.fade(Communal$Elements.LockIcon);
                            transitionBuilderImpl2.fade(Communal$Elements.Scrim);
                            return Unit.INSTANCE;
                        }
                    }, 5);
                    TransitionBuilderImpl.timestampRange$default(transitionBuilderImpl, 167, 334, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt.sceneTransitions.1.6.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            ((TransitionBuilderImpl) obj3).fade(Communal$Elements.Grid);
                            return Unit.INSTANCE;
                        }
                    }, 4);
                    return Unit.INSTANCE;
                }
            }, 12);
            sceneTransitionsBuilderImpl.transitionOverscrollSpecs.add(new OverscrollSpecImpl(sceneKey, Orientation.Horizontal, new TransformationSpecImpl(AnimationSpecKt.snap$default(), null, null, new ArrayList())));
            return Unit.INSTANCE;
        }
    });
    public static final float ANIMATION_OFFSCREEN_OFFSET = 128;

    public static final void AnimatedLinearGradient(final BoxScope boxScope, Composer composer, final int i) {
        int i2;
        final long Color;
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1850432005);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(boxScope.matchParentSize(Modifier.Companion.$$INSTANCE), androidColorScheme.primary, RectangleShapeKt.RectangleShape);
            Color = ColorKt.Color(Color.m368getRedimpl(r7), Color.m367getGreenimpl(r7), Color.m365getBlueimpl(r7), 0.6f, Color.m366getColorSpaceimpl(androidColorScheme.primaryContainer));
            composerImpl.startReplaceGroup(-780450518);
            Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
            Object rememberedValue = composerImpl.rememberedValue();
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new InfiniteTransition();
                composerImpl.updateRememberedValue(rememberedValue);
            }
            InfiniteTransition infiniteTransition = (InfiniteTransition) rememberedValue;
            infiniteTransition.run$animation_core_release(0, composerImpl);
            final InfiniteTransition.TransitionAnimationState animateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 1.0f, AnimationSpecKt.m9infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(10000, 0, new CubicBezierEasing(0.33f, 0.0f, 0.67f, 1.0f), 2), RepeatMode.Reverse, 0L, 4), "radial gradient center fraction", composerImpl, 29112);
            composerImpl.startReplaceGroup(-497804107);
            boolean changed = composerImpl.changed(density);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changed || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = Float.valueOf(density.mo51toPx0680j_4(ANIMATION_OFFSCREEN_OFFSET));
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            final float floatValue = ((Number) rememberedValue2).floatValue();
            composerImpl.end(false);
            composerImpl.startReplaceGroup(-497804010);
            boolean changed2 = composerImpl.changed(floatValue) | composerImpl.changed(animateFloat) | composerImpl.changed(Color);
            final long j = androidColorScheme.primary;
            boolean changed3 = changed2 | composerImpl.changed(j);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed3 || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$animatedRadialGradientBackground$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        DrawScope drawScope = (DrawScope) obj;
                        float f = 2;
                        float intBitsToFloat = (Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() >> 32)) / f) + floatValue;
                        float intBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() & 4294967295L));
                        float f2 = floatValue;
                        float f3 = (f * f2) + intBitsToFloat2;
                        float f4 = -f2;
                        float floatValue2 = (((Number) animateFloat.getValue()).floatValue() * f3) - floatValue;
                        long floatToRawIntBits = (Float.floatToRawIntBits(floatValue2) & 4294967295L) | (Float.floatToRawIntBits(f4) << 32);
                        float intBitsToFloat3 = Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() >> 32)) + floatValue;
                        float floatValue3 = ((1.0f - ((Number) animateFloat.getValue()).floatValue()) * f3) - floatValue;
                        long floatToRawIntBits2 = (Float.floatToRawIntBits(intBitsToFloat3) << 32) | (4294967295L & Float.floatToRawIntBits(floatValue3));
                        drawScope.mo408drawCircleV9BoPsw(new RadialGradient(CollectionsKt__CollectionsKt.listOf(new Color(Color), new Color(j)), floatToRawIntBits2, intBitsToFloat), intBitsToFloat, floatToRawIntBits2);
                        drawScope.mo408drawCircleV9BoPsw(new RadialGradient(CollectionsKt__CollectionsKt.listOf(new Color(Color), new Color(j)), floatToRawIntBits, intBitsToFloat), intBitsToFloat, floatToRawIntBits);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            Modifier drawBehind = DrawModifierKt.drawBehind(m25backgroundbw27NRU, (Function1) rememberedValue3);
            composerImpl.end(false);
            BoxKt.Box(drawBehind, composerImpl, 0);
            BackgroundTopScrim(boxScope, composerImpl, i2 & 14);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$AnimatedLinearGradient$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContainerKt.AnimatedLinearGradient(BoxScope.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void BackgroundTopScrim(final BoxScope boxScope, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1955833836);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            BoxKt.Box(BackgroundKt.m25backgroundbw27NRU(AlphaKt.alpha(boxScope.matchParentSize(Modifier.Companion.$$INSTANCE), 0.34f), DarkThemeKt.isSystemInDarkTheme(composerImpl) ? Color.Black : Color.White, RectangleShapeKt.RectangleShape), composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$BackgroundTopScrim$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContainerKt.BackgroundTopScrim(BoxScope.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void CommunalContainer(Modifier modifier, final CommunalViewModel communalViewModel, final SceneDataSourceDelegator sceneDataSourceDelegator, final CommunalColors communalColors, final CommunalContent communalContent, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(561132849);
        final Modifier modifier2 = (i2 & 1) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (rememberedValue == obj) {
            rememberedValue = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
        }
        final ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).coroutineScope;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.currentScene, CommunalScenes.Blank, composerImpl, 56);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.touchesAllowed, composerImpl);
        final MutableState collectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.communalBackground, CommunalBackgroundType.ANIMATED, composerImpl, 56);
        composerImpl.startReplaceGroup(-2018775999);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == obj) {
            rememberedValue2 = SceneTransitionLayoutStateKt.MutableSceneTransitionLayoutState$default((SceneKey) collectAsStateWithLifecycle.getValue(), sceneTransitions, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$state$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(!((Boolean) ((StateFlowImpl) ((ShadeInteractorImpl) CommunalViewModel.this.shadeInteractor).isAnyFullyExpanded.$$delegate_0).getValue()).booleanValue());
                }
            }, 244);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        final MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState = (MutableSceneTransitionLayoutState) rememberedValue2;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, -2018775714);
        Object obj2 = m;
        if (m == obj) {
            CommunalSwipeDetector communalSwipeDetector = new CommunalSwipeDetector();
            communalSwipeDetector.lastDirection = null;
            composerImpl.updateRememberedValue(communalSwipeDetector);
            obj2 = communalSwipeDetector;
        }
        CommunalSwipeDetector communalSwipeDetector2 = (CommunalSwipeDetector) obj2;
        composerImpl.end(false);
        EffectsKt.DisposableEffect(mutableSceneTransitionLayoutState, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                sceneDataSourceDelegator.setDelegate(new SceneTransitionLayoutDataSource(MutableSceneTransitionLayoutState.this, contextScope));
                return new CommunalContainerKt$CommunalContainer$1$invoke$$inlined$onDispose$1(0, sceneDataSourceDelegator);
            }
        }, composerImpl);
        EffectsKt.DisposableEffect(communalViewModel, mutableSceneTransitionLayoutState, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                CommunalViewModel communalViewModel2 = CommunalViewModel.this;
                communalViewModel2.communalSceneInteractor.repository._transitionState.setValue(ObservableTransitionStateKt.observableTransitionState(mutableSceneTransitionLayoutState));
                return new CommunalContainerKt$CommunalContainer$1$invoke$$inlined$onDispose$1(1, CommunalViewModel.this);
            }
        }, composerImpl);
        Modifier modifier3 = SizeKt.FillWholeMaxSize;
        SceneTransitionLayoutKt.SceneTransitionLayout(mutableSceneTransitionLayoutState, modifier2.then(modifier3), communalSwipeDetector2, communalSwipeDetector2, 0.0f, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                SceneTransitionLayoutImpl$updateContents$1 sceneTransitionLayoutImpl$updateContents$1 = (SceneTransitionLayoutImpl$updateContents$1) obj3;
                SceneKey sceneKey = CommunalScenes.Blank;
                Swipe swipe = new Swipe(SwipeDirection.Start, Edge.End, 2);
                SceneKey sceneKey2 = CommunalScenes.Communal;
                sceneTransitionLayoutImpl$updateContents$1.scene(sceneKey, MapsKt__MapsJVMKt.mapOf(swipe.to(sceneKey2)), ComposableSingletons$CommunalContainerKt.f15lambda1);
                Map mapOf = MapsKt__MapsJVMKt.mapOf(new Swipe(SwipeDirection.End, null, 6).to(sceneKey));
                final CommunalColors communalColors2 = CommunalColors.this;
                final CommunalContent communalContent2 = communalContent;
                final CommunalViewModel communalViewModel2 = communalViewModel;
                final State state = collectAsStateWithLifecycle3;
                sceneTransitionLayoutImpl$updateContents$1.scene(sceneKey2, mapOf, new ComposableLambdaImpl(-61858272, true, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        ContentScope contentScope = (ContentScope) obj4;
                        Composer composer2 = (Composer) obj5;
                        int intValue = ((Number) obj6).intValue();
                        if ((intValue & 14) == 0) {
                            intValue |= ((ComposerImpl) composer2).changed(contentScope) ? 4 : 2;
                        }
                        if ((intValue & 91) == 18) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        State state2 = state;
                        SceneTransitions sceneTransitions2 = CommunalContainerKt.sceneTransitions;
                        CommunalContainerKt.CommunalScene(contentScope, (CommunalBackgroundType) state2.getValue(), CommunalColors.this, communalContent2, communalViewModel2, ContentScope.horizontalNestedScrollToScene$default(contentScope, Modifier.Companion.$$INSTANCE), composer2, (intValue & 14) | 36864, 0);
                        return Unit.INSTANCE;
                    }
                }));
                return Unit.INSTANCE;
            }
        }, composerImpl, 3462, 16);
        if (!((Boolean) collectAsStateWithLifecycle2.getValue()).booleanValue()) {
            modifier3 = modifier3.then(SuspendingPointerInputFilterKt.pointerInput(modifier3, Unit.INSTANCE, ModifierExtKt$allowGestures$1.INSTANCE));
        }
        BoxKt.Box(modifier3, composerImpl, 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalContainer$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    CommunalContainerKt.CommunalContainer(Modifier.this, communalViewModel, sceneDataSourceDelegator, communalColors, communalContent, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void CommunalScene(final ContentScope contentScope, final CommunalBackgroundType communalBackgroundType, final CommunalColors communalColors, final CommunalContent communalContent, final CommunalViewModel communalViewModel, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(989683389);
        int i3 = i2 & 16;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.isFocusable, Boolean.FALSE, composerImpl, 56);
        Modifier then = contentScope.element(companion, Communal$Elements.Scrim).then(SizeKt.FillWholeMaxSize).then(((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue() ? FocusableKt.focusable$default(companion, false, null, 3) : SemanticsModifierKt.clearAndSetSemantics(SemanticsModifierKt.semantics(companion, false, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalScene$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsPropertiesKt.disabled((SemanticsPropertyReceiver) obj);
                return Unit.INSTANCE;
            }
        }), new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalScene$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }));
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, then);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        int ordinal = communalBackgroundType.ordinal();
        if (ordinal == 0) {
            composerImpl.startReplaceGroup(620767801);
            DefaultBackground(boxScopeInstance, communalColors, composerImpl, ((i >> 3) & 112) | 6);
            composerImpl.end(false);
        } else if (ordinal == 1) {
            composerImpl.startReplaceGroup(620767890);
            StaticLinearGradient(boxScopeInstance, composerImpl, 6);
            composerImpl.end(false);
        } else if (ordinal == 2) {
            composerImpl.startReplaceGroup(620767960);
            AnimatedLinearGradient(boxScopeInstance, composerImpl, 6);
            composerImpl.end(false);
        } else if (ordinal != 3) {
            composerImpl.startReplaceGroup(620768058);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(620768028);
            BackgroundTopScrim(boxScopeInstance, composerImpl, 6);
            composerImpl.end(false);
        }
        composerImpl.startReplaceGroup(-1583961693);
        Modifier focusable$default = FocusableKt.focusable$default(modifier2, ((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue(), null, 2);
        composerImpl.startReplaceGroup(-644398109);
        boolean changed = composerImpl.changed(collectAsStateWithLifecycle);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalScene$3$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                    if (!((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue()) {
                        SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                    }
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        communalContent.Content(contentScope, SemanticsModifierKt.semantics(focusable$default, false, (Function1) rememberedValue), composerImpl, (i & 14) | 512, 0);
        composerImpl.end(false);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$CommunalScene$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContainerKt.CommunalScene(ContentScope.this, communalBackgroundType, communalColors, communalContent, communalViewModel, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void DefaultBackground(final BoxScope boxScope, final CommunalColors communalColors, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2110767570);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(communalColors) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            BoxKt.Box(BackgroundKt.m25backgroundbw27NRU(boxScope.matchParentSize(Modifier.Companion.$$INSTANCE), ColorKt.Color(((android.graphics.Color) FlowExtKt.collectAsStateWithLifecycle(((CommunalColorsImpl) communalColors).backgroundColor, composerImpl).getValue()).toArgb()), RectangleShapeKt.RectangleShape), composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$DefaultBackground$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContainerKt.DefaultBackground(BoxScope.this, communalColors, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void StaticLinearGradient(final BoxScope boxScope, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1743878288);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            BoxKt.Box(BackgroundKt.background(boxScope.matchParentSize(Modifier.Companion.$$INSTANCE), new LinearGradient(CollectionsKt__CollectionsKt.listOf(new Color(androidColorScheme.primary), new Color(androidColorScheme.primaryContainer)), 0L, 9187343241974906880L), RectangleShapeKt.RectangleShape, 1.0f), composerImpl, 0);
            BackgroundTopScrim(boxScope, composerImpl, i2 & 14);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContainerKt$StaticLinearGradient$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContainerKt.StaticLinearGradient(BoxScope.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
