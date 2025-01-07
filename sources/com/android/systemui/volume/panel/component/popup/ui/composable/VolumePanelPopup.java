package com.android.systemui.volume.panel.component.popup.ui.composable;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.IconButtonDefaults;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelPopup {
    public final SystemUIDialogFactory dialogFactory;
    public final DialogTransitionAnimator dialogTransitionAnimator;

    public VolumePanelPopup(SystemUIDialogFactory systemUIDialogFactory, DialogTransitionAnimator dialogTransitionAnimator) {
        this.dialogFactory = systemUIDialogFactory;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
    }

    public static final void access$PopupComposable(final VolumePanelPopup volumePanelPopup, final SystemUIDialog systemUIDialog, final Function3 function3, final Function3 function32, Composer composer, final int i) {
        long Color;
        volumePanelPopup.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1687433933);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_volume_settings, composerImpl);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
        composerImpl.startReplaceGroup(1841956073);
        boolean changed = composerImpl.changed(stringResource);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup$PopupComposable$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertiesKt.setPaneTitle((SemanticsPropertyReceiver) obj, stringResource);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        Modifier semantics = SemanticsModifierKt.semantics(fillMaxWidth, false, (Function1) rememberedValue);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, semantics);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
        Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
        Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        float f = 20;
        Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(SizeKt.fillMaxWidth(companion, 1.0f), 0.0f, f, 1);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.m78spacedBy0680j_4(f), Alignment.Companion.Start, composerImpl, 6);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, m100paddingVpY3zN4$default);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        Modifier wrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth(PaddingKt.m100paddingVpY3zN4$default(companion, 80, 0.0f, 2), 1.0f), 3);
        BiasAlignment biasAlignment = Alignment.Companion.Center;
        MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, wrapContentHeight$default);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier3, function24);
        function3.invoke(systemUIDialog, composerImpl, Integer.valueOf((i & 112) | 8));
        composerImpl.end(true);
        Modifier wrapContentHeight$default2 = SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth(PaddingKt.m100paddingVpY3zN4$default(companion, 16, 0.0f, 2), 1.0f), 3);
        MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composerImpl, wrapContentHeight$default2);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy3, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope4, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier4, function24);
        function32.invoke(systemUIDialog, composerImpl, Integer.valueOf(((i >> 3) & 112) | 8));
        composerImpl.end(true);
        composerImpl.end(true);
        Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(SizeKt.m113size3ABfNKs(boxScopeInstance.align(companion, Alignment.Companion.TopEnd), 64), f);
        IconButtonDefaults iconButtonDefaults = IconButtonDefaults.INSTANCE;
        long j = MaterialTheme.getColorScheme(composerImpl).outline;
        long j2 = Color.Unspecified;
        Color = ColorKt.Color(Color.m368getRedimpl(j), Color.m367getGreenimpl(j), Color.m365getBlueimpl(j), 0.38f, Color.m366getColorSpaceimpl(j));
        IconButtonKt.IconButton(1572864, 52, null, IconButtonDefaults.m210defaultIconButtonColors0Yiz4hI$material3_release(MaterialTheme.getColorScheme(composerImpl), null).m209copyjRlVdoo(j2, j, j2, Color), composerImpl, m98padding3ABfNKs, null, new Function0() { // from class: com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup$PopupComposable$2$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SystemUIDialog.this.dismiss();
                return Unit.INSTANCE;
            }
        }, ComposableSingletons$VolumePanelPopupKt.f55lambda1, false);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup$PopupComposable$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    VolumePanelPopup.access$PopupComposable(VolumePanelPopup.this, systemUIDialog, function3, function32, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final void show(Expandable expandable, int i, final ComposableLambdaImpl composableLambdaImpl, final ComposableLambdaImpl composableLambdaImpl2) {
        ComponentSystemUIDialog create$default = SystemUIDialogFactoryExtKt.create$default(this.dialogFactory, null, Integer.valueOf(i), new ComposableLambdaImpl(1290029988, true, new Function3() { // from class: com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup$show$dialog$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey = ComposerKt.invocation;
                VolumePanelPopup.access$PopupComposable(VolumePanelPopup.this, (SystemUIDialog) obj, composableLambdaImpl, composableLambdaImpl2, (Composer) obj2, 4104);
                return Unit.INSTANCE;
            }
        }), 5);
        DialogTransitionAnimator.Controller dialogTransitionController = expandable != null ? expandable.dialogTransitionController(null) : null;
        if (dialogTransitionController == null) {
            create$default.show();
        } else {
            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
            this.dialogTransitionAnimator.show(create$default, dialogTransitionController, false);
        }
    }
}
