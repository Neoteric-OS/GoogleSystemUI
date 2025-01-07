package com.android.systemui.qs.composefragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Top$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsHolder;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnPlacedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutState;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl$updateContents$1;
import com.android.compose.animation.scene.SceneTransitionLayoutKt;
import com.android.compose.animation.scene.SceneTransitionLayoutStateKt;
import com.android.compose.animation.scene.SceneTransitionsBuilderImpl;
import com.android.compose.animation.scene.TransitionBuilderImpl;
import com.android.compose.animation.scene.TransitionDslKt;
import com.android.compose.modifiers.PaddingKt;
import com.android.compose.modifiers.PaddingModifier;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.Dumpable;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.qs.FooterActionsController;
import com.android.systemui.qs.composefragment.ui.FromQuickQuickSettingsToQuickSettingsKt;
import com.android.systemui.qs.composefragment.ui.NotificationScrimClipKt;
import com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel;
import com.android.systemui.qs.footer.ui.compose.FooterActionsKt;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel;
import com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt;
import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.QuickQuickSettingsViewModel;
import com.android.systemui.qs.shared.ui.ElementKeys;
import com.android.systemui.qs.ui.composable.QuickSettingsShade$Dimensions;
import com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlayKt;
import com.android.systemui.qs.ui.composable.QuickSettingsThemeKt;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsContainerViewModel;
import com.android.systemui.shade.LargeScreenHeaderHelper;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.disableflags.data.repository.DisableFlagsRepositoryImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.LifecycleFragment;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import java.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFragmentCompose extends LifecycleFragment implements QS, Dumpable {
    public final Rect composeViewPositionOnScreen;
    public final DumpManager dumpManager;
    public final QSFragmentCompose$notificationScrimClippingParams$1 notificationScrimClippingParams;
    public final MediaHost qqsMediaHost;
    public final Rect qqsPositionOnRoot;
    public final StateFlowImpl qqsVisible;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74 qsFragmentComposeViewModelFactory;
    public final MediaHost qsMediaHost;
    public QSFragmentComposeViewModel viewModel;
    public final StateFlowImpl scrollListener = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl heightListener = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl qsContainerController = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl qqsHeight = StateFlowKt.MutableStateFlow(1);

    public QSFragmentCompose(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74, DumpManager dumpManager, MediaHost mediaHost, MediaHost mediaHost2) {
        this.qsFragmentComposeViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74;
        this.dumpManager = dumpManager;
        this.qqsMediaHost = mediaHost;
        this.qsMediaHost = mediaHost2;
        StateFlowKt.MutableStateFlow(0);
        this.qqsVisible = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.qqsPositionOnRoot = new Rect();
        this.composeViewPositionOnScreen = new Rect();
        this.notificationScrimClippingParams = new QSFragmentCompose$notificationScrimClippingParams$1();
    }

    public static final void access$QuickQuickSettingsElement(final QSFragmentCompose qSFragmentCompose, final ContentScope contentScope, Composer composer, final int i) {
        Modifier weight;
        Modifier modifier;
        qSFragmentCompose.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(261132883);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        QSFragmentComposeViewModel qSFragmentComposeViewModel = qSFragmentCompose.viewModel;
        if (qSFragmentComposeViewModel == null) {
            qSFragmentComposeViewModel = null;
        }
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(qSFragmentComposeViewModel.qqsHeaderHeight, composerImpl);
        final float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.qqs_layout_padding_bottom, composerImpl);
        EffectsKt.DisposableEffect(Unit.INSTANCE, new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                StateFlowImpl stateFlowImpl = QSFragmentCompose.this.qqsVisible;
                Boolean bool = Boolean.TRUE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                final QSFragmentCompose qSFragmentCompose2 = QSFragmentCompose.this;
                return new DisposableEffectResult() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        StateFlowImpl stateFlowImpl2 = QSFragmentCompose.this.qqsVisible;
                        Boolean bool2 = Boolean.FALSE;
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, bool2);
                    }
                };
            }
        }, composerImpl);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier sysuiResTag = SysuiTestTagKt.sysuiResTag(companion, "quick_qs_panel");
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, sysuiResTag);
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
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(OnPlacedModifierKt.onPlaced(SizeKt.fillMaxWidth(companion, 1.0f), new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) obj;
                long m679roundk4lQ0M = IntOffsetKt.m679roundk4lQ0M(layoutCoordinates.mo484localToRootMKHz9U(0L));
                int i3 = (int) (m679roundk4lQ0M >> 32);
                int i4 = (int) (m679roundk4lQ0M & 4294967295L);
                QSFragmentCompose.this.qqsPositionOnRoot.set(i3, i4, ((int) (layoutCoordinates.mo481getSizeYbymL2g() >> 32)) + i3, ((int) (4294967295L & layoutCoordinates.mo481getSizeYbymL2g())) + i4);
                return Unit.INSTANCE;
            }
        }), new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$2$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((IntSize) obj).packedValue;
                StateFlowImpl stateFlowImpl = QSFragmentCompose.this.qqsHeight;
                Integer valueOf = Integer.valueOf((int) (j & 4294967295L));
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
                return Unit.INSTANCE;
            }
        });
        composerImpl.startReplaceGroup(2116651283);
        boolean changed = composerImpl.changed(collectAsStateWithLifecycle);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$2$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(((Number) collectAsStateWithLifecycle.getValue()).intValue());
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        Function1 function1 = (Function1) rememberedValue;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(2116651308);
        boolean changed2 = composerImpl.changed(dimensionResource);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$2$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(((Density) obj).mo45roundToPx0680j_4(dimensionResource));
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        Modifier padding$default = PaddingKt.padding$default(onSizeChanged, function1, null, (Function1) rememberedValue2, 5);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, padding$default);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        QSFragmentComposeViewModel qSFragmentComposeViewModel2 = qSFragmentCompose.viewModel;
        if (qSFragmentComposeViewModel2 == null) {
            qSFragmentComposeViewModel2 = null;
        }
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(qSFragmentComposeViewModel2.qsEnabled, composerImpl);
        composerImpl.startReplaceGroup(2116651454);
        if (((Boolean) collectAsStateWithLifecycle2.getValue()).booleanValue()) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel3 = qSFragmentCompose.viewModel;
            if (qSFragmentComposeViewModel3 == null) {
                qSFragmentComposeViewModel3 = null;
            }
            QuickQuickSettingsViewModel quickQuickSettingsViewModel = qSFragmentComposeViewModel3.containerViewModel.quickQuickSettingsViewModel;
            String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_quick_settings_expand, composerImpl);
            QSFragmentComposeViewModel qSFragmentComposeViewModel4 = qSFragmentCompose.viewModel;
            Runnable runnable = (qSFragmentComposeViewModel4 != null ? qSFragmentComposeViewModel4 : null).collapseExpandAccessibilityAction;
            if (runnable == null || (modifier = SemanticsModifierKt.semantics(companion, false, new QSFragmentCompose$collapseExpandSemanticAction$1$1(stringResource, runnable))) == null) {
                modifier = companion;
            }
            QSFragmentCompose$QuickQuickSettingsElement$2$5$1 qSFragmentCompose$QuickQuickSettingsElement$2$5$1 = new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$2$5$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(((Density) obj).mo45roundToPx0680j_4(QuickSettingsShade$Dimensions.Padding));
                }
            };
            Function1 function12 = PaddingKt.PaddingUnspecified;
            Function1 function13 = InspectableValueKt.NoInspectorInfo;
            QuickQuickSettingsKt.QuickQuickSettings(contentScope, quickQuickSettingsViewModel, modifier.then(new PaddingModifier(qSFragmentCompose$QuickQuickSettingsElement$2$5$1, function12, qSFragmentCompose$QuickQuickSettingsElement$2$5$1, function12)), composerImpl, (i & 14) | 64, 0);
        }
        composerImpl.end(false);
        composerImpl.end(true);
        weight = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
        SpacerKt.Spacer(composerImpl, weight);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickQuickSettingsElement$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QSFragmentCompose.access$QuickQuickSettingsElement(QSFragmentCompose.this, contentScope, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v15, types: [com.android.systemui.qs.composefragment.QSFragmentCompose$QuickSettingsElement$1$2, kotlin.jvm.internal.Lambda] */
    public static final void access$QuickSettingsElement(QSFragmentCompose qSFragmentCompose, ContentScope contentScope, Composer composer, int i) {
        Modifier modifier;
        final int i2;
        final ContentScope contentScope2;
        Modifier weight;
        final QSFragmentCompose qSFragmentCompose2 = qSFragmentCompose;
        qSFragmentCompose.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1768195344);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        QSFragmentComposeViewModel qSFragmentComposeViewModel = qSFragmentCompose2.viewModel;
        if (qSFragmentComposeViewModel == null) {
            qSFragmentComposeViewModel = null;
        }
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(qSFragmentComposeViewModel.qqsHeaderHeight, composerImpl);
        final float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_panel_padding_top, composerImpl);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_quick_settings_collapse, composerImpl);
        QSFragmentComposeViewModel qSFragmentComposeViewModel2 = qSFragmentCompose2.viewModel;
        if (qSFragmentComposeViewModel2 == null) {
            qSFragmentComposeViewModel2 = null;
        }
        Runnable runnable = qSFragmentComposeViewModel2.collapseExpandAccessibilityAction;
        if (runnable == null || (modifier = SemanticsModifierKt.semantics(companion, false, new QSFragmentCompose$collapseExpandSemanticAction$1$1(stringResource, runnable))) == null) {
            modifier = companion;
        }
        Arrangement$Top$1 arrangement$Top$1 = Arrangement.Top;
        BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement$Top$1, horizontal, composerImpl, 0);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier);
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
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        QSFragmentComposeViewModel qSFragmentComposeViewModel3 = qSFragmentCompose2.viewModel;
        if (qSFragmentComposeViewModel3 == null) {
            qSFragmentComposeViewModel3 = null;
        }
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(qSFragmentComposeViewModel3.qsEnabled, composerImpl);
        composerImpl.startReplaceGroup(-522465669);
        if (((Boolean) collectAsStateWithLifecycle2.getValue()).booleanValue()) {
            weight = ColumnScopeInstance.INSTANCE.weight(contentScope.element(companion, ElementKeys.QuickSettingsContent).then(SizeKt.FillWholeMaxSize), 1.0f, true);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i4 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, weight);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, function24);
            ColumnMeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement$Top$1, horizontal, composerImpl, 0);
            int i5 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, companion);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, columnMeasurePolicy2, function2);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function22);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
            }
            Updater.m259setimpl(composerImpl, materializeModifier3, function24);
            composerImpl.startReplaceGroup(785442575);
            boolean changed = composerImpl.changed(collectAsStateWithLifecycle) | composerImpl.changed(dimensionResource);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickSettingsElement$1$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(((Density) obj).mo45roundToPx0680j_4(dimensionResource) + ((Number) collectAsStateWithLifecycle.getValue()).intValue());
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            SpacerKt.Spacer(composerImpl, com.android.compose.modifiers.SizeKt.height(companion, (Function1) rememberedValue));
            qSFragmentCompose2 = qSFragmentCompose;
            QSFragmentComposeViewModel qSFragmentComposeViewModel4 = qSFragmentCompose2.viewModel;
            i2 = i;
            contentScope2 = contentScope;
            QuickSettingsShadeOverlayKt.ShadeBody(contentScope2, (qSFragmentComposeViewModel4 == null ? null : qSFragmentComposeViewModel4).containerViewModel, composerImpl, (i2 & 14) | 64);
            composerImpl.end(true);
            composerImpl.end(true);
            QuickSettingsThemeKt.QuickSettingsTheme(ComposableLambdaKt.rememberComposableLambda(-665244985, new Function2() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickSettingsElement$1$2
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
                    QSFragmentCompose qSFragmentCompose3 = QSFragmentCompose.this;
                    QSFragmentComposeViewModel qSFragmentComposeViewModel5 = qSFragmentCompose3.viewModel;
                    if (qSFragmentComposeViewModel5 == null) {
                        qSFragmentComposeViewModel5 = null;
                    }
                    FooterActionsKt.FooterActions(qSFragmentComposeViewModel5.footerActionsViewModel, qSFragmentCompose3, contentScope2.element(SysuiTestTagKt.sysuiResTag(Modifier.Companion.$$INSTANCE, "qs_footer_actions"), ElementKeys.FooterActions), composer2, 72, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 6);
        } else {
            i2 = i;
            contentScope2 = contentScope;
        }
        composerImpl.end(false);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$QuickSettingsElement$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QSFragmentCompose.access$QuickSettingsElement(QSFragmentCompose.this, contentScope2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void closeCustomizer() {
        QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
        if (qSFragmentComposeViewModel == null) {
            qSFragmentComposeViewModel = null;
        }
        EditModeViewModel editModeViewModel = qSFragmentComposeViewModel.containerViewModel.editModeViewModel;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = editModeViewModel._isEditing;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void closeDetail() {
        closeCustomizer();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        QSFragmentCompose$notificationScrimClippingParams$1 qSFragmentCompose$notificationScrimClippingParams$1 = this.notificationScrimClippingParams;
        qSFragmentCompose$notificationScrimClippingParams$1.getClass();
        asIndenting.append("NotificationScrimClippingParams").println(":");
        asIndenting.increaseIndent();
        try {
            Boolean bool = (Boolean) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.isEnabled$delegate).getValue();
            bool.booleanValue();
            DumpUtilsKt.println(asIndenting, "isEnabled", bool);
            DumpUtilsKt.println(asIndenting, "leftInset", ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.leftInset$delegate).getValue()).intValue() + "px");
            DumpUtilsKt.println(asIndenting, "rightInset", ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.rightInset$delegate).getValue()).intValue() + "px");
            DumpUtilsKt.println(asIndenting, "top", ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.top$delegate).getValue()).intValue() + "px");
            DumpUtilsKt.println(asIndenting, "bottom", ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.bottom$delegate).getValue()).intValue() + "px");
            DumpUtilsKt.println(asIndenting, "radius", ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.radius$delegate).getValue()).intValue() + "px");
            asIndenting.decreaseIndent();
            asIndenting.append("QQS positioning").println(":");
            asIndenting.increaseIndent();
            try {
                DumpUtilsKt.println(asIndenting, "qqsHeight", getHeaderHeight() + "px");
                DumpUtilsKt.println(asIndenting, "qqsTop", this.qqsPositionOnRoot.top + "px");
                DumpUtilsKt.println(asIndenting, "qqsBottom", this.qqsPositionOnRoot.bottom + "px");
                DumpUtilsKt.println(asIndenting, "qqsLeft", this.qqsPositionOnRoot.left + "px");
                DumpUtilsKt.println(asIndenting, "qqsPositionOnRoot", this.qqsPositionOnRoot);
                Rect rect = new Rect();
                getHeaderBoundsOnScreen(rect);
                DumpUtilsKt.println(asIndenting, "qqsPositionOnScreen", rect);
                asIndenting.decreaseIndent();
                DumpUtilsKt.println(asIndenting, "QQS visible", this.qqsVisible.getValue());
                if (this.viewModel != null) {
                    asIndenting.append("View Model").println(":");
                    asIndenting.increaseIndent();
                    try {
                        QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
                        if (qSFragmentComposeViewModel == null) {
                            qSFragmentComposeViewModel = null;
                        }
                        qSFragmentComposeViewModel.dump(asIndenting, strArr);
                    } finally {
                    }
                }
            } finally {
            }
        } finally {
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getDesiredHeight() {
        View view = getView();
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final View getHeader() {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.");
        }
        return null;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderBottom() {
        return this.qqsPositionOnRoot.bottom;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void getHeaderBoundsOnScreen(Rect rect) {
        Unit unit;
        rect.set(this.qqsPositionOnRoot);
        View view = getView();
        if (view != null) {
            view.getBoundsOnScreen(this.composeViewPositionOnScreen);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            this.composeViewPositionOnScreen.setEmpty();
        }
        Rect rect2 = this.composeViewPositionOnScreen;
        rect.offset(rect2.left, rect2.top);
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderLeft() {
        return this.qqsPositionOnRoot.left;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderTop() {
        return this.qqsPositionOnRoot.top;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeightDiff() {
        return 0;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getQsMinExpansionHeight() {
        return ((Number) this.qqsHeight.getValue()).intValue();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isCustomizing() {
        QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
        if (qSFragmentComposeViewModel == null) {
            qSFragmentComposeViewModel = null;
        }
        return ((Boolean) ((StateFlowImpl) qSFragmentComposeViewModel.containerViewModel.editModeViewModel.isEditing.$$delegate_0).getValue()).booleanValue();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isFullyCollapsed() {
        QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
        if (qSFragmentComposeViewModel == null) {
            qSFragmentComposeViewModel = null;
        }
        return ((Number) qSFragmentComposeViewModel._qsExpansion.getValue()).floatValue() <= 0.0f;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isHeaderShown() {
        return ((Boolean) this.qqsVisible.getValue()).booleanValue();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isShowingDetail() {
        return isCustomizing();
    }

    @Override // com.android.systemui.util.LifecycleFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects com.android.systemui.qs_ui_refactor_compose_fragment to be enabled.");
        }
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74 = this.qsFragmentComposeViewModelFactory;
        LifecycleCoroutineScopeImpl coroutineScope = LifecycleKt.getCoroutineScope(getLifecycle());
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$74.this$0;
        QuickSettingsContainerViewModel quickSettingsContainerViewModel = (QuickSettingsContainerViewModel) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).quickSettingsContainerViewModelProvider.get();
        Resources m977$$Nest$mmainResources = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(switchingProvider.sysUIGoogleGlobalRootComponentImpl);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        FooterActionsViewModel.Factory factory = (FooterActionsViewModel.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider25.get();
        FooterActionsController footerActionsController = (FooterActionsController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.footerActionsControllerProvider.get();
        SysuiStatusBarStateController sysuiStatusBarStateController = (SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
        DisableFlagsRepositoryImpl disableFlagsRepositoryImpl = (DisableFlagsRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disableFlagsRepositoryImplProvider.get();
        this.viewModel = new QSFragmentComposeViewModel(quickSettingsContainerViewModel, m977$$Nest$mmainResources, factory, footerActionsController, sysuiStatusBarStateController, disableFlagsRepositoryImpl, (ConfigurationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationInteractorProvider.get(), (LargeScreenHeaderHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.largeScreenHeaderHelperProvider.get(), coroutineScope);
        this.qqsMediaHost.init(1);
        this.qsMediaHost.init(0);
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new QSFragmentCompose$setListenerCollections$1(this, null), 3);
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = layoutInflater.getContext();
        Intrinsics.checkNotNull(context);
        final ComposeView composeView = new ComposeView(context, null, 0, 6, null);
        RepeatWhenAttachedKt.repeatWhenAttached(composeView, EmptyCoroutineContext.INSTANCE, new QSFragmentComposeKt$setBackPressedDispatcher$1(composeView, null));
        composeView.setContent$1(new ComposableLambdaImpl(1007501389, true, new Function2() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                final QSFragmentCompose qSFragmentCompose = QSFragmentCompose.this;
                final ComposeView composeView2 = composeView;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-125646141, new Function2() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX WARN: Type inference failed for: r10v14, types: [com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3, kotlin.jvm.internal.Lambda] */
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer2 = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 11) == 2) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        QSFragmentComposeViewModel qSFragmentComposeViewModel = QSFragmentCompose.this.viewModel;
                        if (qSFragmentComposeViewModel == null) {
                            qSFragmentComposeViewModel = null;
                        }
                        boolean booleanValue = ((Boolean) FlowExtKt.collectAsStateWithLifecycle(qSFragmentComposeViewModel.qsVisible, composer2).getValue()).booleanValue();
                        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                        WindowInsetsHolder.Companion companion2 = WindowInsetsHolder.Companion;
                        Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(companion, WindowInsetsHolder.Companion.current(composer2).navigationBars);
                        boolean booleanValue2 = ((Boolean) ((SnapshotMutableStateImpl) QSFragmentCompose.this.notificationScrimClippingParams.isEnabled$delegate).getValue()).booleanValue();
                        QSFragmentCompose qSFragmentCompose2 = QSFragmentCompose.this;
                        if (booleanValue2) {
                            windowInsetsPadding = windowInsetsPadding.then(NotificationScrimClipKt.notificationScrimClip(((Number) ((SnapshotMutableStateImpl) qSFragmentCompose2.notificationScrimClippingParams.leftInset$delegate).getValue()).intValue(), ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose2.notificationScrimClippingParams.top$delegate).getValue()).intValue(), ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose2.notificationScrimClippingParams.rightInset$delegate).getValue()).intValue(), ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose2.notificationScrimClippingParams.bottom$delegate).getValue()).intValue(), ((Number) ((SnapshotMutableStateImpl) qSFragmentCompose2.notificationScrimClippingParams.radius$delegate).getValue()).intValue()));
                        }
                        final ComposeView composeView3 = composeView2;
                        Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj5) {
                                ComposeView.this.setElevation(((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj5)).getDensity() * 4);
                                return Unit.INSTANCE;
                            }
                        });
                        final QSFragmentCompose qSFragmentCompose3 = QSFragmentCompose.this;
                        AnimatedVisibilityKt.AnimatedVisibility(booleanValue, graphicsLayer, null, null, null, ComposableLambdaKt.rememberComposableLambda(1045499803, new Function3() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.3

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$1, reason: invalid class name and collision with other inner class name */
                            final class C01501 extends SuspendLambda implements Function2 {
                                final /* synthetic */ MutableSceneTransitionLayoutState $sceneState;
                                int label;
                                final /* synthetic */ QSFragmentCompose this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public C01501(MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState, QSFragmentCompose qSFragmentCompose, Continuation continuation) {
                                    super(2, continuation);
                                    this.$sceneState = mutableSceneTransitionLayoutState;
                                    this.this$0 = qSFragmentCompose;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object obj, Continuation continuation) {
                                    return new C01501(this.$sceneState, this.this$0, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    return ((C01501) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                    int i = this.label;
                                    Unit unit = Unit.INSTANCE;
                                    if (i == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState = this.$sceneState;
                                        QSFragmentComposeViewModel qSFragmentComposeViewModel = this.this$0.viewModel;
                                        if (qSFragmentComposeViewModel == null) {
                                            qSFragmentComposeViewModel = null;
                                        }
                                        final ReadonlyStateFlow readonlyStateFlow = qSFragmentComposeViewModel.expansionState;
                                        Flow flow = 
                                        /*  JADX ERROR: Method code generation error
                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0028: CONSTRUCTOR (r5v0 'flow' kotlinx.coroutines.flow.Flow) = (r1v4 'readonlyStateFlow' kotlinx.coroutines.flow.ReadonlyStateFlow A[DONT_INLINE]) A[DECLARE_VAR, MD:(kotlinx.coroutines.flow.StateFlow):void (m)] (LINE:41) call: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$1$invokeSuspend$$inlined$map$1.<init>(kotlinx.coroutines.flow.StateFlow):void type: CONSTRUCTOR in method: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.3.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes2.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
                                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
                                            	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:310)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:782)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
                                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
                                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                                            	at jadx.core.ProcessClass.process(ProcessClass.java:79)
                                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
                                            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
                                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
                                            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:340)
                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$1$invokeSuspend$$inlined$map$1, state: NOT_LOADED
                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	... 178 more
                                            */
                                        /*
                                            this = this;
                                            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                            int r1 = r6.label
                                            kotlin.Unit r2 = kotlin.Unit.INSTANCE
                                            r3 = 1
                                            if (r1 == 0) goto L17
                                            if (r1 != r3) goto Lf
                                            kotlin.ResultKt.throwOnFailure(r7)
                                            goto L3d
                                        Lf:
                                            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                                            r6.<init>(r7)
                                            throw r6
                                        L17:
                                            kotlin.ResultKt.throwOnFailure(r7)
                                            com.android.compose.animation.scene.MutableSceneTransitionLayoutState r7 = r6.$sceneState
                                            com.android.systemui.qs.composefragment.QSFragmentCompose r1 = r6.this$0
                                            com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel r1 = r1.viewModel
                                            r4 = 0
                                            if (r1 != 0) goto L24
                                            r1 = r4
                                        L24:
                                            kotlinx.coroutines.flow.ReadonlyStateFlow r1 = r1.expansionState
                                            com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$1$invokeSuspend$$inlined$map$1 r5 = new com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$1$invokeSuspend$$inlined$map$1
                                            r5.<init>(r1)
                                            r6.label = r3
                                            com.android.systemui.qs.composefragment.QSFragmentComposeKt$synchronizeQsState$2 r1 = new com.android.systemui.qs.composefragment.QSFragmentComposeKt$synchronizeQsState$2
                                            r1.<init>(r5, r7, r4)
                                            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r1)
                                            if (r6 != r0) goto L39
                                            goto L3a
                                        L39:
                                            r6 = r2
                                        L3a:
                                            if (r6 != r0) goto L3d
                                            return r0
                                        L3d:
                                            return r2
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1.AnonymousClass1.AnonymousClass3.C01501.invokeSuspend(java.lang.Object):java.lang.Object");
                                    }
                                }

                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj5, Object obj6, Object obj7) {
                                    ((Number) obj7).intValue();
                                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                                    ComposerImpl composerImpl3 = (ComposerImpl) ((Composer) obj6);
                                    composerImpl3.startReplaceGroup(-523221049);
                                    QSFragmentCompose qSFragmentCompose4 = QSFragmentCompose.this;
                                    Object rememberedValue = composerImpl3.rememberedValue();
                                    if (rememberedValue == Composer.Companion.Empty) {
                                        SceneKey sceneKey = SceneKeys.QuickQuickSettings;
                                        QSFragmentComposeViewModel qSFragmentComposeViewModel2 = qSFragmentCompose4.viewModel;
                                        if (qSFragmentComposeViewModel2 == null) {
                                            qSFragmentComposeViewModel2 = null;
                                        }
                                        rememberedValue = SceneTransitionLayoutStateKt.MutableSceneTransitionLayoutState$default(((QSFragmentComposeViewModel.QSExpansionState) ((StateFlowImpl) qSFragmentComposeViewModel2.expansionState.$$delegate_0).getValue()).progress < 0.5f ? SceneKeys.QuickQuickSettings : SceneKeys.QuickSettings, TransitionDslKt.transitions(new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$sceneState$1$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj8) {
                                                ((SceneTransitionsBuilderImpl) obj8).transition(SceneKeys.QuickQuickSettings, SceneKeys.QuickSettings, null, null, new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$onCreateView$1$1$1$3$sceneState$1$1.1
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj9) {
                                                        FromQuickQuickSettingsToQuickSettingsKt.quickQuickSettingsToQuickSettings((TransitionBuilderImpl) obj9);
                                                        return Unit.INSTANCE;
                                                    }
                                                });
                                                return Unit.INSTANCE;
                                            }
                                        }), null, 508);
                                        composerImpl3.updateRememberedValue(rememberedValue);
                                    }
                                    MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState = (MutableSceneTransitionLayoutState) rememberedValue;
                                    composerImpl3.end(false);
                                    Unit unit = Unit.INSTANCE;
                                    EffectsKt.LaunchedEffect(composerImpl3, unit, new C01501(mutableSceneTransitionLayoutState, QSFragmentCompose.this, null));
                                    FillElement fillElement = SizeKt.FillWholeMaxSize;
                                    final QSFragmentCompose qSFragmentCompose5 = QSFragmentCompose.this;
                                    SceneTransitionLayoutKt.SceneTransitionLayout(mutableSceneTransitionLayoutState, fillElement, null, null, 0.0f, new Function1() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.3.2
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj8) {
                                            SceneTransitionLayoutImpl$updateContents$1 sceneTransitionLayoutImpl$updateContents$1 = (SceneTransitionLayoutImpl$updateContents$1) obj8;
                                            SceneKey sceneKey2 = SceneKeys.QuickSettings;
                                            final QSFragmentCompose qSFragmentCompose6 = QSFragmentCompose.this;
                                            sceneTransitionLayoutImpl$updateContents$1.scene(sceneKey2, MapsKt.emptyMap(), new ComposableLambdaImpl(-1761514603, true, new Function3() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.3.2.1
                                                {
                                                    super(3);
                                                }

                                                @Override // kotlin.jvm.functions.Function3
                                                public final Object invoke(Object obj9, Object obj10, Object obj11) {
                                                    ContentScope contentScope = (ContentScope) obj9;
                                                    Composer composer3 = (Composer) obj10;
                                                    int intValue = ((Number) obj11).intValue();
                                                    if ((intValue & 14) == 0) {
                                                        intValue |= ((ComposerImpl) composer3).changed(contentScope) ? 4 : 2;
                                                    }
                                                    if ((intValue & 91) == 18) {
                                                        ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                                                        if (composerImpl4.getSkipping()) {
                                                            composerImpl4.skipToGroupEnd();
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                    OpaqueKey opaqueKey4 = ComposerKt.invocation;
                                                    QSFragmentCompose.access$QuickSettingsElement(QSFragmentCompose.this, contentScope, composer3, (intValue & 14) | 64);
                                                    return Unit.INSTANCE;
                                                }
                                            }));
                                            SceneKey sceneKey3 = SceneKeys.QuickQuickSettings;
                                            final QSFragmentCompose qSFragmentCompose7 = QSFragmentCompose.this;
                                            sceneTransitionLayoutImpl$updateContents$1.scene(sceneKey3, MapsKt.emptyMap(), new ComposableLambdaImpl(-2024755828, true, new Function3() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose.onCreateView.1.1.1.3.2.2
                                                {
                                                    super(3);
                                                }

                                                @Override // kotlin.jvm.functions.Function3
                                                public final Object invoke(Object obj9, Object obj10, Object obj11) {
                                                    ContentScope contentScope = (ContentScope) obj9;
                                                    Composer composer3 = (Composer) obj10;
                                                    int intValue = ((Number) obj11).intValue();
                                                    if ((intValue & 14) == 0) {
                                                        intValue |= ((ComposerImpl) composer3).changed(contentScope) ? 4 : 2;
                                                    }
                                                    if ((intValue & 91) == 18) {
                                                        ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                                                        if (composerImpl4.getSkipping()) {
                                                            composerImpl4.skipToGroupEnd();
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                    OpaqueKey opaqueKey4 = ComposerKt.invocation;
                                                    QSFragmentCompose.access$QuickQuickSettingsElement(QSFragmentCompose.this, contentScope, composer3, (intValue & 14) | 64);
                                                    return Unit.INSTANCE;
                                                }
                                            }));
                                            return Unit.INSTANCE;
                                        }
                                    }, composerImpl3, 54, 28);
                                    return unit;
                                }
                            }, composer2), composer2, 196608, 28);
                            return Unit.INSTANCE;
                        }
                    }, composer), composer, 48, 1);
                    return Unit.INSTANCE;
                }
            }));
            return composeView;
        }

        @Override // com.android.systemui.util.LifecycleFragment, android.app.Fragment
        public final void onStart() {
            super.onStart();
            QSFragmentComposeKt$instanceProvider$1 qSFragmentComposeKt$instanceProvider$1 = QSFragmentComposeKt.instanceProvider;
            int i = qSFragmentComposeKt$instanceProvider$1.currentId;
            qSFragmentComposeKt$instanceProvider$1.currentId = i + 1;
            BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new QSFragmentCompose$registerDumpable$1(this, i + "-QSFragmentCompose", null), 3);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setCollapseExpandAction(Runnable runnable) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            qSFragmentComposeViewModel.collapseExpandAccessibilityAction = runnable;
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setContainerController(QSContainerController qSContainerController) {
            this.qsContainerController.setValue(qSContainerController);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setExpanded(boolean z) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            Boolean valueOf = Boolean.valueOf(z);
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._qsExpanded;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setFancyClipping(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            ((SnapshotMutableStateImpl) this.notificationScrimClippingParams.isEnabled$delegate).setValue(Boolean.valueOf(z));
            ((SnapshotMutableStateImpl) this.notificationScrimClippingParams.top$delegate).setValue(Integer.valueOf(i2));
            ((SnapshotMutableStateImpl) this.notificationScrimClippingParams.bottom$delegate).setValue(Integer.valueOf(i4));
            QSFragmentCompose$notificationScrimClippingParams$1 qSFragmentCompose$notificationScrimClippingParams$1 = this.notificationScrimClippingParams;
            if (z2) {
                i = 0;
            }
            ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$1.leftInset$delegate).setValue(Integer.valueOf(i));
            QSFragmentCompose$notificationScrimClippingParams$1 qSFragmentCompose$notificationScrimClippingParams$12 = this.notificationScrimClippingParams;
            if (z2) {
                i3 = 0;
            }
            ((SnapshotMutableStateImpl) qSFragmentCompose$notificationScrimClippingParams$12.rightInset$delegate).setValue(Integer.valueOf(i3));
            ((SnapshotMutableStateImpl) this.notificationScrimClippingParams.radius$delegate).setValue(Integer.valueOf(i5));
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setHeightOverride(int i) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._heightOverride;
            Integer valueOf = Integer.valueOf(i);
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setIsNotificationPanelFullWidth(boolean z) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            Boolean valueOf = Boolean.valueOf(z);
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._isSmallScreen;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setOverscrolling(boolean z) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            Boolean valueOf = Boolean.valueOf(z);
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._stackScrollerOverscrolling;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setPanelView(QS.HeightListener heightListener) {
            this.heightListener.setValue(heightListener);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setQsExpansion(float f, float f2, float f3, float f4) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._qsExpansion;
            if (f < 0.0f) {
                Float valueOf = Float.valueOf(-1.0f);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
            }
            Float valueOf2 = Float.valueOf(RangesKt.coerceIn(f, 0.0f, 1.0f));
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf2);
            QSFragmentComposeViewModel qSFragmentComposeViewModel2 = this.viewModel;
            if (qSFragmentComposeViewModel2 == null) {
                qSFragmentComposeViewModel2 = null;
            }
            Float valueOf3 = Float.valueOf(f2);
            StateFlowImpl stateFlowImpl2 = qSFragmentComposeViewModel2._panelFraction;
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, valueOf3);
            QSFragmentComposeViewModel qSFragmentComposeViewModel3 = this.viewModel;
            if (qSFragmentComposeViewModel3 == null) {
                qSFragmentComposeViewModel3 = null;
            }
            Float valueOf4 = Float.valueOf(f4);
            StateFlowImpl stateFlowImpl3 = qSFragmentComposeViewModel3._squishinessFraction;
            stateFlowImpl3.getClass();
            stateFlowImpl3.updateState(null, valueOf4);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setQsVisible(boolean z) {
            QSFragmentComposeViewModel qSFragmentComposeViewModel = this.viewModel;
            if (qSFragmentComposeViewModel == null) {
                qSFragmentComposeViewModel = null;
            }
            Boolean valueOf = Boolean.valueOf(z);
            StateFlowImpl stateFlowImpl = qSFragmentComposeViewModel._qsVisible;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setScrollListener(QS.ScrollListener scrollListener) {
            this.scrollListener.setValue(scrollListener);
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void animateHeaderSlidingOut() {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void hideImmediately() {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void notifyCustomizeChanged() {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setCollapsedMediaVisibilityChangedListener(Consumer consumer) {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setHeaderClickable(boolean z) {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setHeaderListening(boolean z) {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setInSplitShade(boolean z) {
        }

        @Override // com.android.systemui.plugins.qs.QS
        public final void setListening(boolean z) {
        }
    }
