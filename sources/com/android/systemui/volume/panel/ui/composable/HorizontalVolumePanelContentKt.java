package com.android.systemui.volume.panel.ui.composable;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.RowScopeInstance;
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
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import com.android.systemui.volume.panel.ui.layout.ComponentsLayout;
import com.android.systemui.volume.panel.ui.viewmodel.ComponentState;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class HorizontalVolumePanelContentKt {
    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$2$2, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r5v11, types: [com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$1$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r5v7, types: [com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$2$1, kotlin.jvm.internal.Lambda] */
    public static final void HorizontalVolumePanelContent(final VolumePanelComposeScope volumePanelComposeScope, final ComponentsLayout componentsLayout, Modifier modifier, Composer composer, final int i, final int i2) {
        final VolumePanelComposeScope volumePanelComposeScope2 = volumePanelComposeScope;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1334321093);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final float f = 20;
        Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(f);
        BiasAlignment.Vertical vertical = Alignment.Companion.Top;
        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m78spacedBy0680j_4, vertical, composerImpl, 6);
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
        Updater.m259setimpl(composerImpl, rowMeasurePolicy, function2);
        Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
        Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        final Modifier modifier3 = modifier2;
        Modifier verticalScroll$default = ScrollKt.verticalScroll$default(rowScopeInstance.weight(companion, 1.0f, true), ScrollKt.rememberScrollState(composerImpl), false, 14);
        Arrangement.SpacedAligned m78spacedBy0680j_42 = Arrangement.m78spacedBy0680j_4(f);
        BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_42, horizontal, composerImpl, 6);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, verticalScroll$default);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        composerImpl.startReplaceGroup(625650753);
        for (final ComponentState componentState : componentsLayout.contentComponents) {
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, componentState.isVisible, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-872588377, new Function3() { // from class: com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    ComponentState.this.component.Content(volumePanelComposeScope2, Modifier.Companion.$$INSTANCE, (Composer) obj2, 48);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 1572870, 30);
            volumePanelComposeScope2 = volumePanelComposeScope;
        }
        composerImpl.end(false);
        composerImpl.end(true);
        Modifier verticalScroll$default2 = ScrollKt.verticalScroll$default(rowScopeInstance.weight(companion, 1.0f, true), ScrollKt.rememberScrollState(composerImpl), false, 14);
        Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
        ColumnMeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.m80spacedByD5KLDUw(f, vertical), horizontal, composerImpl, 6);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        int i6 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, verticalScroll$default2);
        ComposeUiNode.Companion.getClass();
        Function0 function02 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy2, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function25 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function25);
        }
        Updater.m259setimpl(composerImpl, materializeModifier3, ComposeUiNode.Companion.SetModifier);
        composerImpl.startReplaceGroup(-1750281161);
        for (final ComponentState componentState2 : componentsLayout.headerComponents) {
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, componentState2.isVisible, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1685962416, new Function3() { // from class: com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                    ComponentState.this.component.Content(volumePanelComposeScope, Modifier.Companion.$$INSTANCE, (Composer) obj2, 48);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 1572870, 30);
        }
        composerImpl.end(false);
        AnimatedContentKt.AnimatedContent(componentsLayout.footerComponents, null, null, null, "FooterComponentAnimation", null, ComposableLambdaKt.rememberComposableLambda(-788015821, new Function4() { // from class: com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$1$2$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                List<ComponentState> list = (List) obj2;
                Composer composer2 = (Composer) obj3;
                ((Number) obj4).intValue();
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion2, 1.0f);
                Arrangement$Start$1 arrangement$Start$12 = Arrangement.Start;
                Arrangement.SpacedAligned m78spacedBy0680j_43 = Arrangement.m78spacedBy0680j_4(f);
                VolumePanelComposeScope volumePanelComposeScope3 = volumePanelComposeScope;
                RowMeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(m78spacedBy0680j_43, Alignment.Companion.Top, composer2, 6);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl2.currentCompositionLocalScope();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer2, fillMaxWidth);
                ComposeUiNode.Companion.getClass();
                Function0 function03 = ComposeUiNode.Companion.Constructor;
                composerImpl2.startReusableNode();
                if (composerImpl2.inserting) {
                    composerImpl2.createNode(function03);
                } else {
                    composerImpl2.useNode();
                }
                Updater.m259setimpl(composer2, rowMeasurePolicy2, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composer2, currentCompositionLocalScope4, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function26 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl2, currentCompositeKeyHash, function26);
                }
                Updater.m259setimpl(composer2, materializeModifier4, ComposeUiNode.Companion.SetModifier);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                composerImpl2.startReplaceGroup(-1192635590);
                for (ComponentState componentState3 : list) {
                    composerImpl2.startReplaceGroup(-621549927);
                    if (componentState3.isVisible) {
                        componentState3.component.Content(volumePanelComposeScope3, rowScopeInstance2.weight(companion2, 1.0f, true), composer2, 0);
                    }
                    composerImpl2.end(false);
                }
                composerImpl2.end(false);
                composerImpl2.end(true);
                OpaqueKey opaqueKey4 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 1597448, 46);
        composerImpl.end(true);
        composerImpl.end(true);
        OpaqueKey opaqueKey3 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.ui.composable.HorizontalVolumePanelContentKt$HorizontalVolumePanelContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    HorizontalVolumePanelContentKt.HorizontalVolumePanelContent(VolumePanelComposeScope.this, componentsLayout, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
