package com.android.systemui.communal.ui.compose.section;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.EasingKt$$ExternalSyntheticLambda0;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.outlined.WidgetsKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.ui.viewmodel.PopupType;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalPopupSection {
    public final CommunalViewModel viewModel;

    public CommunalPopupSection(CommunalViewModel communalViewModel) {
        this.viewModel = communalViewModel;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$2, kotlin.jvm.internal.Lambda] */
    public static final void access$ButtonToEditWidgets(final CommunalPopupSection communalPopupSection, final AnimatedVisibilityScope animatedVisibilityScope, final Function0 function0, final Function0 function02, Composer composer, final int i) {
        communalPopupSection.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(824270661);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(867248708);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = InteractionSourceKt.MutableInteractionSource();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 867248777);
        if (m == composer$Companion$Empty$1) {
            m = new FocusRequester();
            composerImpl.updateRememberedValue(m);
        }
        final FocusRequester focusRequester = (FocusRequester) m;
        composerImpl.end(false);
        Unit unit = Unit.INSTANCE;
        composerImpl.startReplaceGroup(867248837);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new CommunalPopupSection$ButtonToEditWidgets$1$1(focusRequester, null);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(composerImpl, unit, (Function2) rememberedValue2);
        AndroidPopup_androidKt.m703PopupK5zGePQ(Alignment.Companion.TopCenter, (0 << 32) | (40 & 4294967295L), function02, null, ComposableLambdaKt.rememberComposableLambda(-1145349246, new Function2() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r2v8, types: [com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$2$2, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                final AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl3.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
                AnimatedVisibilityScope animatedVisibilityScope2 = AnimatedVisibilityScope.this;
                Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(SizeKt.m108height3ABfNKs(Modifier.Companion.$$INSTANCE, 56), focusRequester), false, mutableInteractionSource, 1), new Function1() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        ((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj3)).m391setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.0f, 0.0f));
                        return Unit.INSTANCE;
                    }
                });
                EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda0 = EasingKt.LinearEasing;
                Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(animatedVisibilityScope2.animateEnterExit(graphicsLayer, EnterExitTransitionKt.fadeIn(AnimationSpecKt.tween$default(83, 0, easingKt$$ExternalSyntheticLambda0, 2)), EnterExitTransitionKt.fadeOut$default(new TweenSpec(83, 167, easingKt$$ExternalSyntheticLambda0), 2)), androidColorScheme.secondary, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(50));
                Function0 function03 = function0;
                final AnimatedVisibilityScope animatedVisibilityScope3 = AnimatedVisibilityScope.this;
                ButtonKt.Button(function03, m25backgroundbw27NRU, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1804176786, new Function3() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        Composer composer3 = (Composer) obj4;
                        if ((((Number) obj5).intValue() & 81) == 16) {
                            ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                            if (composerImpl4.getSkipping()) {
                                composerImpl4.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        AnimatedVisibilityScope animatedVisibilityScope4 = AnimatedVisibilityScope.this;
                        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                        EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda02 = EasingKt.LinearEasing;
                        Modifier animateEnterExit = animatedVisibilityScope4.animateEnterExit(companion, EnterExitTransitionKt.fadeIn$default(new TweenSpec(167, 83, easingKt$$ExternalSyntheticLambda02), 2), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(167, 0, easingKt$$ExternalSyntheticLambda02, 2), 2));
                        AndroidColorScheme androidColorScheme2 = androidColorScheme;
                        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, Alignment.Companion.Top, composer3, 0);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3);
                        ComposerImpl composerImpl5 = (ComposerImpl) composer3;
                        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl5.currentCompositionLocalScope();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, animateEnterExit);
                        ComposeUiNode.Companion.getClass();
                        Function0 function04 = ComposeUiNode.Companion.Constructor;
                        composerImpl5.startReusableNode();
                        if (composerImpl5.inserting) {
                            composerImpl5.createNode(function04);
                        } else {
                            composerImpl5.useNode();
                        }
                        Updater.m259setimpl(composer3, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                        Updater.m259setimpl(composer3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                        if (composerImpl5.inserting || !Intrinsics.areEqual(composerImpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl5, currentCompositeKeyHash, function2);
                        }
                        Updater.m259setimpl(composer3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                        IconKt.m214Iconww6aTOc(WidgetsKt.getWidgets(), (String) null, SizeKt.m113size3ABfNKs(companion, 20), androidColorScheme2.onSecondary, composer3, 432, 0);
                        SpacerKt.Spacer(composer3, SizeKt.m113size3ABfNKs(companion, 8));
                        TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.button_to_configure_widgets_text, composer3), null, androidColorScheme2.onSecondary, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer3).titleSmall, composer3, 0, 0, 65530);
                        composerImpl5.end(true);
                        return Unit.INSTANCE;
                    }
                }, composerImpl3), composerImpl3, 805306368, 508);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, (i & 896) | 24630, 8);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$ButtonToEditWidgets$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalPopupSection.access$ButtonToEditWidgets(CommunalPopupSection.this, animatedVisibilityScope, function0, function02, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.communal.ui.compose.section.CommunalPopupSection$Popup$2, kotlin.jvm.internal.Lambda] */
    public final void Popup(final int i, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2073420952);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(this.viewModel.currentPopup, null, composerImpl, 56);
        composerImpl.startReplaceGroup(1935330153);
        if (Intrinsics.areEqual((PopupType) collectAsStateWithLifecycle.getValue(), PopupType.CtaTile.INSTANCE)) {
            PopupOnDismissCtaTile(new CommunalPopupSection$Popup$1(0, this.viewModel, CommunalViewModel.class, "onHidePopup", "onHidePopup()V", 0), composerImpl, 64);
        }
        composerImpl.end(false);
        AnimatedVisibilityKt.AnimatedVisibility(Intrinsics.areEqual((PopupType) collectAsStateWithLifecycle.getValue(), PopupType.CustomizeWidgetButton.INSTANCE), SizeKt.FillWholeMaxSize, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1233446720, new Function3() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$Popup$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                final CommunalPopupSection communalPopupSection = CommunalPopupSection.this;
                CommunalPopupSection.access$ButtonToEditWidgets(communalPopupSection, (AnimatedVisibilityScope) obj, new Function0() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$Popup$2.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        CommunalPopupSection.this.viewModel.setCurrentPopupType(null);
                        CommunalPopupSection.this.viewModel.onOpenWidgetEditor(false);
                        return Unit.INSTANCE;
                    }
                }, new Function0() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$Popup$2.2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        CommunalPopupSection.this.viewModel.setCurrentPopupType(null);
                        CommunalPopupSection.this.viewModel.setSelectedKey(null);
                        return Unit.INSTANCE;
                    }
                }, (Composer) obj2, 4104);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 196656, 28);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$Popup$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalPopupSection.this.Popup(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final void PopupOnDismissCtaTile(final Function0 function0, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1734221839);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidPopup_androidKt.m703PopupK5zGePQ(Alignment.Companion.TopCenter, (0 << 32) | (40 & 4294967295L), function0, null, ComposableSingletons$CommunalPopupSectionKt.f25lambda1, composerImpl, ((i2 << 6) & 896) | 24630, 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.section.CommunalPopupSection$PopupOnDismissCtaTile$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalPopupSection.this.PopupOnDismissCtaTile(function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
