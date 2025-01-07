package com.android.systemui.communal.ui.compose;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
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
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntRect;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.communal.smartspace.SmartspaceInteractionHandler;
import com.android.systemui.communal.ui.compose.section.AmbientStatusBarSection;
import com.android.systemui.communal.ui.compose.section.CommunalPopupSection;
import com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon;
import com.android.systemui.keyguard.ui.composable.section.BottomAreaSection;
import com.android.systemui.keyguard.ui.composable.section.LockSection;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalContent {
    public final AmbientStatusBarSection ambientStatusBarSection;
    public final BottomAreaSection bottomAreaSection;
    public final CommunalPopupSection communalPopupSection;
    public final SystemUIDialogFactory dialogFactory;
    public final SmartspaceInteractionHandler interactionHandler;
    public final LockSection lockSection;
    public final CommunalViewModel viewModel;
    public final CommunalAppWidgetSection widgetSection;

    public CommunalContent(CommunalViewModel communalViewModel, SmartspaceInteractionHandler smartspaceInteractionHandler, SystemUIDialogFactory systemUIDialogFactory, LockSection lockSection, BottomAreaSection bottomAreaSection, AmbientStatusBarSection ambientStatusBarSection, CommunalPopupSection communalPopupSection, CommunalAppWidgetSection communalAppWidgetSection) {
        this.viewModel = communalViewModel;
        this.interactionHandler = smartspaceInteractionHandler;
        this.dialogFactory = systemUIDialogFactory;
        this.lockSection = lockSection;
        this.bottomAreaSection = bottomAreaSection;
        this.ambientStatusBarSection = ambientStatusBarSection;
        this.communalPopupSection = communalPopupSection;
        this.widgetSection = communalAppWidgetSection;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.communal.ui.compose.CommunalContent$Content$1, kotlin.jvm.internal.Lambda] */
    public final void Content(final ContentScope contentScope, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1994133520);
        if ((i2 & 1) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        CommunalTouchableSurfaceKt.CommunalTouchableSurface(this.viewModel, modifier, ComposableLambdaKt.rememberComposableLambda(781207450, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalContent$Content$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.ui.compose.CommunalContent$Content$1$1, reason: invalid class name */
            public final class AnonymousClass1 implements MeasurePolicy {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, final long j) {
                    MeasureResult layout$1;
                    Measurable measurable = (Measurable) list.get(0);
                    Measurable measurable2 = (Measurable) list.get(1);
                    Measurable measurable3 = (Measurable) list.get(2);
                    long m648copyZbe2FdA$default = Constraints.m648copyZbe2FdA$default(j, 0, 0, 0, 0, 10);
                    final Placeable mo479measureBRTryo0 = measurable2.mo479measureBRTryo0(m648copyZbe2FdA$default);
                    int i = mo479measureBRTryo0.get(BlueprintAlignmentLines$LockIcon.Left);
                    int i2 = mo479measureBRTryo0.get(BlueprintAlignmentLines$LockIcon.Top);
                    final IntRect intRect = new IntRect(i, i2, mo479measureBRTryo0.get(BlueprintAlignmentLines$LockIcon.Right), mo479measureBRTryo0.get(BlueprintAlignmentLines$LockIcon.Bottom));
                    final Placeable mo479measureBRTryo02 = measurable3.mo479measureBRTryo0(m648copyZbe2FdA$default);
                    final Placeable mo479measureBRTryo03 = measurable.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(m648copyZbe2FdA$default, 0, 0, 0, i2, 7));
                    layout$1 = measureScope.layout$1(Constraints.m655getMaxWidthimpl(j), Constraints.m654getMaxHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalContent.Content.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                            placementScope.place(Placeable.this, 0, 0, 0.0f);
                            Placeable placeable = mo479measureBRTryo0;
                            IntRect intRect2 = intRect;
                            placementScope.place(placeable, intRect2.left, intRect2.top, 0.0f);
                            placementScope.place(mo479measureBRTryo02, 0, Constraints.m654getMaxHeightimpl(j) - mo479measureBRTryo02.height, 0.0f);
                            return Unit.INSTANCE;
                        }
                    });
                    return layout$1;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 81) == 16) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                FillElement fillElement = SizeKt.FillWholeMaxSize;
                AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
                CommunalContent communalContent = CommunalContent.this;
                ContentScope contentScope2 = contentScope;
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, fillElement);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, anonymousClass1, function2);
                Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Function2 function24 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier, function24);
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, fillElement);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy, function2);
                Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier2, function24);
                composerImpl3.startReplaceGroup(-1690890740);
                communalContent.communalPopupSection.Popup(8, composer2);
                composerImpl3.end(false);
                composerImpl3.startReplaceGroup(-1690890677);
                communalContent.ambientStatusBarSection.AmbientStatusBar(contentScope2, SizeKt.fillMaxWidth(companion, 1.0f), composer2, 48, 0);
                composerImpl3.end(false);
                CommunalHubKt.CommunalHub(contentScope2.element(companion, Communal$Elements.Grid), communalContent.viewModel, communalContent.widgetSection, communalContent.interactionHandler, communalContent.dialogFactory, null, null, null, composer2, 37440, 224);
                composerImpl3.end(true);
                composerImpl3.startReplaceGroup(-632078884);
                communalContent.lockSection.m829LockIconBAq54LU(contentScope2, new Color(((AndroidColorScheme) composerImpl3.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).onPrimaryContainer), contentScope2.element(companion, Communal$Elements.LockIcon), composer2, 4096, 0);
                composerImpl3.end(false);
                composerImpl3.startReplaceGroup(-180796695);
                communalContent.bottomAreaSection.IndicationArea(contentScope2, SizeKt.fillMaxWidth(contentScope2.element(companion, Communal$Elements.IndicationArea), 1.0f), composer2, 512, 0);
                composerImpl3.end(false);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, (i & 112) | 392, 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalContent$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalContent.this.Content(contentScope, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
