package com.android.systemui.qs.ui.composable;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.systemui.brightness.ui.compose.BrightnessSliderKt;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.qs.composefragment.ui.GridAnchorKt;
import com.android.systemui.qs.panels.ui.compose.EditModeKt;
import com.android.systemui.qs.panels.ui.compose.TileGridKt;
import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsContainerViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QuickSettingsShadeOverlayKt {
    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt$ShadeBody$2, kotlin.jvm.internal.Lambda] */
    public static final void ShadeBody(final ContentScope contentScope, final QuickSettingsContainerViewModel quickSettingsContainerViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1454130984);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Boolean bool = (Boolean) FlowExtKt.collectAsStateWithLifecycle(quickSettingsContainerViewModel.editModeViewModel.isEditing, composerImpl).getValue();
        bool.getClass();
        AnimatedContentKt.AnimatedContent(bool, null, new Function1() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt$ShadeBody$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new ContentTransform(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(500, 0, null, 6), 2), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(500, 0, null, 6), 2));
            }
        }, null, null, null, ComposableLambdaKt.rememberComposableLambda(-37228117, new Function4() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt$ShadeBody$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                boolean booleanValue = ((Boolean) obj2).booleanValue();
                Composer composer2 = (Composer) obj3;
                ((Number) obj4).intValue();
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                if (booleanValue) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    composerImpl2.startReplaceGroup(1834001850);
                    EditModeKt.EditMode(QuickSettingsContainerViewModel.this.editModeViewModel, PaddingKt.m98padding3ABfNKs(SizeKt.fillMaxWidth(companion, 1.0f), QuickSettingsShade$Dimensions.Padding), composerImpl2, 56, 0);
                    composerImpl2.end(false);
                } else {
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    composerImpl3.startReplaceGroup(1834002059);
                    QuickSettingsShadeOverlayKt.access$QuickSettingsLayout(contentScope, QuickSettingsContainerViewModel.this, SysuiTestTagKt.sysuiResTag(companion, "quick_settings_panel"), composerImpl3, 64, 0);
                    composerImpl3.end(false);
                }
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 1573248, 58);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt$ShadeBody$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QuickSettingsShadeOverlayKt.ShadeBody(ContentScope.this, quickSettingsContainerViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$QuickSettingsLayout(final ContentScope contentScope, final QuickSettingsContainerViewModel quickSettingsContainerViewModel, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(821918842);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
        float f = QuickSettingsShade$Dimensions.Padding;
        Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(f);
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(SizeKt.fillMaxWidth(modifier2, 1.0f), f);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_4, horizontal, composerImpl, 54);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m98padding3ABfNKs);
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
        BrightnessSliderKt.BrightnessSliderContainer(quickSettingsContainerViewModel.brightnessSliderViewModel, SizeKt.m108height3ABfNKs(SizeKt.fillMaxWidth(companion, 1.0f), QuickSettingsShade$Dimensions.BrightnessSliderHeight), composerImpl, 56, 0);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, companion);
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
        int i6 = i & 14;
        GridAnchorKt.GridAnchor(contentScope, null, composerImpl, i6, 1);
        TileGridKt.TileGrid(contentScope, quickSettingsContainerViewModel.tileGridViewModel, SizeKt.m110heightInVpY3zN4$default(SizeKt.fillMaxWidth(companion, 1.0f), 0.0f, QuickSettingsShade$Dimensions.GridMaxHeight, 1), new QuickSettingsShadeOverlayKt$QuickSettingsLayout$1$1$1(0, quickSettingsContainerViewModel.editModeViewModel, EditModeViewModel.class, "startEditing", "startEditing()V", 0), composerImpl, i6 | 448, 0);
        composerImpl.end(true);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt$QuickSettingsLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QuickSettingsShadeOverlayKt.access$QuickSettingsLayout(ContentScope.this, quickSettingsContainerViewModel, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
