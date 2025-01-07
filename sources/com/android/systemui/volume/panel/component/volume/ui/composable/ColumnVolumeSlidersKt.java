package com.android.systemui.volume.panel.component.volume.ui.composable;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.PlatformSliderColors;
import com.android.compose.modifiers.PaddingKt;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ColumnVolumeSlidersKt {
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$2, kotlin.jvm.internal.Lambda] */
    public static final void ColumnVolumeSliders(final List list, final boolean z, final Function1 function1, final PlatformSliderColors platformSliderColors, final boolean z2, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(338998927);
        int i3 = i2 & 32;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier2);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, function2);
        Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
        Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, fillMaxWidth);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        final SliderViewModel sliderViewModel = (SliderViewModel) CollectionsKt.first(list);
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(((SliderViewModel) CollectionsKt.first(list)).getSlider(), composerImpl);
        composerImpl.startReplaceGroup(-734902023);
        final State m8animateDpAsStateAjpBEmI = AnimateAsStateKt.m8animateDpAsStateAjpBEmI(z2 ? 72 : 0, z2 ? AnimationSpecKt.tween$default(400, 300, null, 4) : AnimationSpecKt.tween$default(400, 500, null, 4), "TopVolumeSliderPadding", composerImpl, 448, 8);
        composerImpl.end(false);
        composerImpl.startReplaceGroup(1760186531);
        boolean changed = composerImpl.changed(m8animateDpAsStateAjpBEmI);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$1$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(((Density) obj).mo45roundToPx0680j_4(((Dp) State.this.getValue()).value));
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        final Modifier modifier3 = modifier2;
        VolumeSliderKt.VolumeSlider((SliderState) collectAsStateWithLifecycle.getValue(), new Function1() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SliderViewModel.this.onValueChanged((SliderState) collectAsStateWithLifecycle.getValue(), ((Number) obj).floatValue());
                return Unit.INSTANCE;
            }
        }, new Function0() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$1$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SliderViewModel.this.onValueChangeFinished();
                return Unit.INSTANCE;
            }
        }, new Function0() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$1$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SliderViewModel.this.toggleMuted((SliderState) collectAsStateWithLifecycle.getValue());
                return Unit.INSTANCE;
            }
        }, SizeKt.fillMaxWidth(PaddingKt.padding$default(companion, null, (Function1) rememberedValue, null, 11), 1.0f), platformSliderColors, composerImpl, (i << 6) & 458752, 0);
        ExpandButton(z, z2, function1, platformSliderColors, boxScopeInstance.align(companion, Alignment.Companion.CenterEnd), composerImpl, ((i >> 3) & 14) | ((i >> 9) & 112) | (i & 896) | (i & 7168), 0);
        composerImpl.end(true);
        AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, z || !z2, null, EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.tween$default(500, 0, null, 6), null, 14), EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(300, 0, null, 6), null, 14), "CollapsableSliders", ComposableLambdaKt.rememberComposableLambda(536411649, new Function3() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ComposerImpl composerImpl2;
                boolean z3;
                AnimatedVisibilityScope animatedVisibilityScope = (AnimatedVisibilityScope) obj;
                Composer composer2 = (Composer) obj2;
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                Modifier fillMaxWidth2 = SizeKt.fillMaxWidth(companion2, 1.0f);
                BiasAlignment biasAlignment = Alignment.Companion.BottomCenter;
                List list2 = list;
                PlatformSliderColors platformSliderColors2 = platformSliderColors;
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer2, fillMaxWidth2);
                ComposeUiNode.Companion.getClass();
                Function0 function02 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function25 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy2, function25);
                Function2 function26 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope3, function26);
                Function2 function27 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function27);
                }
                Function2 function28 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier3, function28);
                ColumnMeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composer2, 0);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer2, companion2);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, columnMeasurePolicy2, function25);
                Updater.m259setimpl(composer2, currentCompositionLocalScope4, function26);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function27);
                }
                Updater.m259setimpl(composer2, materializeModifier4, function28);
                composerImpl3.startReplaceGroup(439328723);
                int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list2);
                boolean z4 = true;
                if (1 <= lastIndex) {
                    int i6 = 1;
                    while (true) {
                        final SliderViewModel sliderViewModel2 = (SliderViewModel) list2.get(i6);
                        final MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(sliderViewModel2.getSlider(), composer2);
                        Modifier fillMaxWidth3 = SizeKt.fillMaxWidth(androidx.compose.foundation.layout.PaddingKt.m102paddingqDBjuR0$default(companion2, 0.0f, 16, 0.0f, 0.0f, 13), 1.0f);
                        int size = ((list2.size() - i6) + 1) * 10;
                        if (size < 0) {
                            size = 0;
                        }
                        int i7 = 500 - size;
                        if (i7 < 100) {
                            i7 = 100;
                        }
                        ComposerImpl composerImpl4 = composerImpl3;
                        EnterTransition plus = EnterExitTransitionKt.m4scaleInL8ZKhE$default(AnimationSpecKt.tween$default(i7, size, null, 4), 0.9f).plus(EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.tween$default(i7, size, null, 4), new Function1() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$enterTransition$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return Integer.valueOf((int) (((Number) obj4).intValue() * 0.55f));
                            }
                        }, 2)).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(i7, size, null, 4), 2));
                        int size2 = 300 - (((list2.size() - i6) + 1) * 10);
                        int i8 = size2 >= 100 ? size2 : 100;
                        Modifier.Companion companion3 = companion2;
                        Modifier animateEnterExit = animatedVisibilityScope.animateEnterExit(fillMaxWidth3, plus, EnterExitTransitionKt.m5scaleOutL8ZKhE$default(AnimationSpecKt.tween$default(i8, 0, null, 6), 0.9f).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(i8, 0, null, 6), new Function1() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$exitTransition$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return Integer.valueOf((int) (((Number) obj4).intValue() * 0.55f));
                            }
                        }, 2)).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(i8, 0, null, 6), 2)));
                        PlatformSliderColors platformSliderColors3 = platformSliderColors2;
                        PlatformSliderColors platformSliderColors4 = platformSliderColors2;
                        composerImpl2 = composerImpl4;
                        AnimatedVisibilityScope animatedVisibilityScope2 = animatedVisibilityScope;
                        z3 = false;
                        VolumeSliderKt.VolumeSlider((SliderState) collectAsStateWithLifecycle2.getValue(), new Function1() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$2$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                SliderViewModel.this.onValueChanged((SliderState) collectAsStateWithLifecycle2.getValue(), ((Number) obj4).floatValue());
                                return Unit.INSTANCE;
                            }
                        }, new Function0() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$2$1$1$2
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                SliderViewModel.this.onValueChangeFinished();
                                return Unit.INSTANCE;
                            }
                        }, new Function0() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$1$2$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                SliderViewModel.this.toggleMuted((SliderState) collectAsStateWithLifecycle2.getValue());
                                return Unit.INSTANCE;
                            }
                        }, animateEnterExit, platformSliderColors3, composer2, 0, 0);
                        z4 = true;
                        if (i6 == lastIndex) {
                            break;
                        }
                        i6++;
                        animatedVisibilityScope = animatedVisibilityScope2;
                        companion2 = companion3;
                        composerImpl3 = composerImpl2;
                        platformSliderColors2 = platformSliderColors4;
                    }
                } else {
                    composerImpl2 = composerImpl3;
                    z3 = false;
                }
                composerImpl2.end(z3);
                composerImpl2.end(z4);
                composerImpl2.end(z4);
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 1797126, 2);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ColumnVolumeSliders$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ColumnVolumeSlidersKt.ColumnVolumeSliders(list, z, function1, platformSliderColors, z2, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt$ExpandButton$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ExpandButton(final boolean r17, final boolean r18, final kotlin.jvm.functions.Function1 r19, final com.android.compose.PlatformSliderColors r20, androidx.compose.ui.Modifier r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.volume.ui.composable.ColumnVolumeSlidersKt.ExpandButton(boolean, boolean, kotlin.jvm.functions.Function1, com.android.compose.PlatformSliderColors, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }
}
