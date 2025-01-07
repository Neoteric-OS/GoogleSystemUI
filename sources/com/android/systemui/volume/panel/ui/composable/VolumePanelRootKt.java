package com.android.systemui.volume.panel.ui.composable;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.volume.panel.ui.layout.ComponentsLayout;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelState;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class VolumePanelRootKt {
    public static final float padding = 24;

    public static final void BottomBar(final VolumePanelComposeScope volumePanelComposeScope, final ComponentsLayout componentsLayout, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(54378751);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        final Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (componentsLayout.bottomBarComponent.isVisible) {
            Modifier fillMaxWidth = SizeKt.fillMaxWidth(modifier2, 1.0f);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.Center, false);
            int i4 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, fillMaxWidth);
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
            composerImpl.startReplaceGroup(1466769619);
            componentsLayout.bottomBarComponent.component.Content(volumePanelComposeScope, companion, composerImpl, (i & 14) | 48);
            composerImpl.end(false);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$BottomBar$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    VolumePanelRootKt.BottomBar(VolumePanelComposeScope.this, componentsLayout, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$VolumePanelRoot$1$1$1, kotlin.jvm.internal.Lambda] */
    public static final void VolumePanelRoot(final VolumePanelViewModel volumePanelViewModel, final Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1113483181);
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_volume_settings, composerImpl);
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(volumePanelViewModel.volumePanelState, composerImpl);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(volumePanelViewModel.componentsLayout, composerImpl);
        final VolumePanelComposeScope volumePanelComposeScope = new VolumePanelComposeScope((VolumePanelState) collectAsStateWithLifecycle.getValue());
        final ComponentsLayout componentsLayout = (ComponentsLayout) collectAsStateWithLifecycle2.getValue();
        composerImpl.startReplaceGroup(2084750475);
        if (componentsLayout != null) {
            PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(434304954, new Function2() { // from class: com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$VolumePanelRoot$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

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
                    VolumePanelComposeScope volumePanelComposeScope2 = VolumePanelComposeScope.this;
                    ComponentsLayout componentsLayout2 = componentsLayout;
                    Modifier sysuiResTag = SysuiTestTagKt.sysuiResTag(modifier, "VolumePanel");
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    composerImpl3.startReplaceGroup(-471475619);
                    boolean changed = composerImpl3.changed(stringResource);
                    final String str = stringResource;
                    Object rememberedValue = composerImpl3.rememberedValue();
                    if (changed || rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = new Function1() { // from class: com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$VolumePanelRoot$1$1$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                SemanticsPropertiesKt.setPaneTitle((SemanticsPropertyReceiver) obj3, str);
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl3.updateRememberedValue(rememberedValue);
                    }
                    composerImpl3.end(false);
                    Modifier semantics = SemanticsModifierKt.semantics(sysuiResTag, false, (Function1) rememberedValue);
                    float f = VolumePanelRootKt.padding;
                    VolumePanelRootKt.access$Components(volumePanelComposeScope2, componentsLayout2, PaddingKt.m101paddingqDBjuR0(semantics, f, f, f, 20), composerImpl3, 64, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 48, 1);
        }
        composerImpl.end(false);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$VolumePanelRoot$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    VolumePanelRootKt.VolumePanelRoot(VolumePanelViewModel.this, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$Components(final VolumePanelComposeScope volumePanelComposeScope, final ComponentsLayout componentsLayout, Modifier modifier, Composer composer, final int i, final int i2) {
        float f;
        Arrangement.SpacedAligned m78spacedBy0680j_4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(359173169);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        VolumePanelState volumePanelState = volumePanelComposeScope.state;
        if (volumePanelState.isLargeScreen) {
            m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(20);
        } else {
            if (volumePanelState.orientation == 1) {
                Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
                f = padding;
            } else {
                f = 4;
            }
            m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(f);
        }
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_4, Alignment.Companion.Start, composerImpl, 0);
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
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        VolumePanelState volumePanelState2 = volumePanelComposeScope.state;
        if ((volumePanelState2.orientation == 1) || volumePanelState2.isLargeScreen) {
            composerImpl.startReplaceGroup(-1674715073);
            VerticalVolumePanelContentKt.VerticalVolumePanelContent(volumePanelComposeScope, componentsLayout, columnScopeInstance.weight(companion, 1.0f, false), composerImpl, (i & 14) | 64, 0);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(-1674714899);
            HorizontalVolumePanelContentKt.HorizontalVolumePanelContent(volumePanelComposeScope, componentsLayout, SizeKt.m110heightInVpY3zN4$default(columnScopeInstance.weight(companion, 1.0f, false), 0.0f, 212, 1), composerImpl, (i & 14) | 64, 0);
            composerImpl.end(false);
        }
        BottomBar(volumePanelComposeScope, componentsLayout, companion, composerImpl, (i & 14) | 448, 0);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.ui.composable.VolumePanelRootKt$Components$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    VolumePanelRootKt.access$Components(VolumePanelComposeScope.this, componentsLayout, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
