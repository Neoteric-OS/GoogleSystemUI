package com.android.systemui.volume.panel.component.mediaoutput.ui.composable;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedContentTransitionScopeImpl;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.foundation.BasicMarqueeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.ExpandableKt;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.ui.compose.ColorKt;
import com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.ConnectedDeviceViewModel;
import com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel;
import com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.composable.VolumePanelComposeScope;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputComponent implements ComposeVolumePanelUiComponent {
    public final MediaOutputViewModel viewModel;

    public MediaOutputComponent(MediaOutputViewModel mediaOutputViewModel) {
        this.viewModel = mediaOutputViewModel;
    }

    public static final void access$ConnectedDeviceIcon(final MediaOutputComponent mediaOutputComponent, final DeviceIconViewModel deviceIconViewModel, Composer composer, final int i) {
        int i2;
        mediaOutputComponent.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(575301017);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(deviceIconViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Transition updateTransition = TransitionKt.updateTransition(deviceIconViewModel, "MediaOutputIconTransition", composerImpl, (i2 & 14) | 48);
            Modifier aspectRatio$default = AspectRatioKt.aspectRatio$default(PaddingKt.m98padding3ABfNKs(Modifier.Companion.$$INSTANCE, 16).then(SizeKt.FillWholeMaxHeight), 1.0f);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.Center, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, aspectRatio$default);
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
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            AnimatedContentKt.AnimatedContent(updateTransition, null, new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((AnimatedContentTransitionScopeImpl) ((AnimatedContentTransitionScope) obj)).getTargetState() instanceof DeviceIconViewModel.IsPlaying ? new ContentTransform(EnterExitTransitionKt.m4scaleInL8ZKhE$default(AnimationSpecKt.tween$default(400, 700, null, 4), 0.9f).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(400, 700, null, 4), 2)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.snap$default(), 2)) : new ContentTransform(EnterExitTransitionKt.fadeIn$default(new SnapSpec(900), 2), EnterExitTransitionKt.m5scaleOutL8ZKhE$default(AnimationSpecKt.tween$default(400, 500, null, 4), 0.9f).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(400, 500, null, 4), 2)));
                }
            }, null, new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((DeviceIconViewModel) obj).getBackgroundColor();
                }
            }, ComposableSingletons$MediaOutputComponentKt.f53lambda1, composerImpl, 221568, 5);
            AnimatedContentKt.AnimatedContent(updateTransition, null, new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((AnimatedContentTransitionScopeImpl) ((AnimatedContentTransitionScope) obj)).getTargetState() instanceof DeviceIconViewModel.IsPlaying ? new ContentTransform(EnterExitTransitionKt.fadeIn$default(new SnapSpec(700), 2), EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(400, 300, null, 4), new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$3.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Integer.valueOf(((Number) obj2).intValue());
                        }
                    }).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(400, 300, null, 4), 2))) : new ContentTransform(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween$default(400, 900, null, 4), new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$3.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Integer.valueOf(((Number) obj2).intValue());
                        }
                    }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(400, 900, null, 4), 2)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(400, 500, null, 4), 2));
                }
            }, null, new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$1$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((DeviceIconViewModel) obj).getIcon();
                }
            }, ComposableSingletons$MediaOutputComponentKt.f54lambda2, composerImpl, 221568, 5);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceIcon$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    MediaOutputComponent.access$ConnectedDeviceIcon(MediaOutputComponent.this, deviceIconViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$ConnectedDeviceText(final MediaOutputComponent mediaOutputComponent, final RowScope rowScope, final ConnectedDeviceViewModel connectedDeviceViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl;
        mediaOutputComponent.getClass();
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-1947319223);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(rowScope.weight(companion, 1.0f, true), 24, 0.0f, 0.0f, 0.0f, 14);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.m78spacedBy0680j_4(4), Alignment.Companion.Start, composerImpl2, 6);
        int i2 = composerImpl2.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl2, m102paddingqDBjuR0$default);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl2.startReusableNode();
        if (composerImpl2.inserting) {
            composerImpl2.createNode(function0);
        } else {
            composerImpl2.useNode();
        }
        Updater.m259setimpl(composerImpl2, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl2, i2, function2);
        }
        Updater.m259setimpl(composerImpl2, materializeModifier, ComposeUiNode.Companion.SetModifier);
        TextKt.m241Text4IGK_g(connectedDeviceViewModel.label.toString(), BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(companion, 63), ColorKt.toColor(connectedDeviceViewModel.labelColor, composerImpl2), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, MaterialTheme.getTypography(composerImpl2).labelMedium, composerImpl2, 48, 3072, 57336);
        CharSequence charSequence = connectedDeviceViewModel.deviceName;
        composerImpl2.startReplaceGroup(1043252345);
        if (charSequence == null) {
            composerImpl = composerImpl2;
        } else {
            composerImpl = composerImpl2;
            TextKt.m241Text4IGK_g(charSequence.toString(), BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(companion, 63), ColorKt.toColor(connectedDeviceViewModel.deviceNameColor, composerImpl2), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, MaterialTheme.getTypography(composerImpl2).titleMedium, composerImpl, 48, 3072, 57336);
        }
        ComposerImpl composerImpl3 = composerImpl;
        composerImpl3.end(false);
        composerImpl3.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$ConnectedDeviceText$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    MediaOutputComponent.access$ConnectedDeviceText(MediaOutputComponent.this, rowScope, connectedDeviceViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$3, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        long j;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1150893050);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MediaOutputViewModel mediaOutputViewModel = this.viewModel;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mediaOutputViewModel.connectedDeviceViewModel, composerImpl);
        final MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(mediaOutputViewModel.deviceIconViewModel, composerImpl);
        final String stringResource = StringResources_androidKt.stringResource(R.string.volume_panel_enter_media_output_settings, composerImpl);
        MutableState collectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(mediaOutputViewModel.enabled, composerImpl);
        Modifier semantics = SemanticsModifierKt.semantics(SizeKt.m108height3ABfNKs(SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f), 80), false, new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.LiveRegion;
                KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[3];
                semanticsPropertyKey.setValue(semanticsPropertyReceiver, new LiveRegionMode());
                String str = stringResource;
                final MediaOutputComponent mediaOutputComponent = this;
                SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, str, new Function0() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MediaOutputComponent.this.viewModel.onBarClick(null);
                        return Boolean.TRUE;
                    }
                });
                return Unit.INSTANCE;
            }
        });
        if (((Boolean) collectAsStateWithLifecycle3.getValue()).booleanValue()) {
            composerImpl.startReplaceGroup(2027712071);
            j = MaterialTheme.getColorScheme(composerImpl).surface;
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(2027712150);
            j = MaterialTheme.getColorScheme(composerImpl).surfaceContainerHighest;
            composerImpl.end(false);
        }
        ExpandableKt.m724ExpandableQIcBpto(j, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(28), semantics, 0L, null, ((Boolean) collectAsStateWithLifecycle3.getValue()).booleanValue() ? new Function1() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                MediaOutputComponent.this.viewModel.onBarClick((Expandable) obj);
                return Unit.INSTANCE;
            }
        } : null, null, ComposableLambdaKt.rememberComposableLambda(1146721710, new Function3() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer composer2 = (Composer) obj2;
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                State state = collectAsStateWithLifecycle;
                MediaOutputComponent mediaOutputComponent = this;
                State state2 = collectAsStateWithLifecycle2;
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, vertical, composer2, 48);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, companion);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl2.startReusableNode();
                if (composerImpl2.inserting) {
                    composerImpl2.createNode(function0);
                } else {
                    composerImpl2.useNode();
                }
                Updater.m259setimpl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl2, currentCompositeKeyHash, function2);
                }
                Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ConnectedDeviceViewModel connectedDeviceViewModel = (ConnectedDeviceViewModel) state.getValue();
                composerImpl2.startReplaceGroup(547578863);
                if (connectedDeviceViewModel != null) {
                    MediaOutputComponent.access$ConnectedDeviceText(mediaOutputComponent, rowScopeInstance, connectedDeviceViewModel, composer2, 582);
                }
                composerImpl2.end(false);
                DeviceIconViewModel deviceIconViewModel = (DeviceIconViewModel) state2.getValue();
                composerImpl2.startReplaceGroup(-1784226028);
                if (deviceIconViewModel != null) {
                    MediaOutputComponent.access$ConnectedDeviceIcon(mediaOutputComponent, deviceIconViewModel, composer2, 64);
                }
                composerImpl2.end(false);
                composerImpl2.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 12582912, 88);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent$Content$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    MediaOutputComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
