package com.android.systemui.volume.panel.component.anc.ui.composable;

import android.content.res.Configuration;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BasicMarqueeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.slice.Slice;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel;
import com.android.systemui.volume.panel.ui.VolumePanelUiEvent;
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
public final class AncButtonComponent implements ComposeVolumePanelUiComponent {
    public final AncPopup ancPopup;
    public final AncViewModel viewModel;

    public AncButtonComponent(AncViewModel ancViewModel, AncPopup ancPopup) {
        this.viewModel = ancViewModel;
        this.ancPopup = ancPopup;
    }

    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(59322415);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(this.viewModel.buttonSlice, composerImpl);
        final String stringResource = StringResources_androidKt.stringResource(R.string.volume_panel_noise_control_title, composerImpl);
        composerImpl.startReplaceGroup(-924778702);
        final float mo51toPx0680j_4 = ((Density) composerImpl.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(((Configuration) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration)).screenWidthDp);
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-924778597);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (rememberedValue == obj) {
            rememberedValue = SnapshotIntStateKt.mutableIntStateOf(1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableIntState mutableIntState = (MutableIntState) rememberedValue;
        composerImpl.end(false);
        boolean isClickable = AncViewModel.isClickable((Slice) collectAsStateWithLifecycle.getValue());
        composerImpl.startReplaceGroup(-924778399);
        boolean changed = composerImpl.changed(mo51toPx0680j_4);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == obj) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent$Content$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    LayoutCoordinates layoutCoordinates = (LayoutCoordinates) obj2;
                    MutableIntState mutableIntState2 = mutableIntState;
                    float f = mo51toPx0680j_4;
                    int i2 = 1;
                    Rect localBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates).localBoundingBoxOf(layoutCoordinates, true);
                    float f2 = localBoundingBoxOf.right;
                    float f3 = localBoundingBoxOf.left;
                    float intBitsToFloat = Float.intBitsToFloat((int) (((Float.floatToRawIntBits(((f2 - f3) / 2.0f) + f3) << 32) | (Float.floatToRawIntBits(localBoundingBoxOf.bottom) & 4294967295L)) >> 32));
                    float f4 = f / 2;
                    if (intBitsToFloat < f4) {
                        i2 = 3;
                    } else if (intBitsToFloat > f4) {
                        i2 = 5;
                    }
                    ((SnapshotMutableIntStateImpl) mutableIntState2).setIntValue(i2);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        Modifier onGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier, (Function1) rememberedValue2);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.m78spacedBy0680j_4(12), Alignment.Companion.CenterHorizontally, composerImpl, 54);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, onGloballyPositioned);
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
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier m108height3ABfNKs = SizeKt.m108height3ABfNKs(companion, 64);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, m108height3ABfNKs);
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
        FillElement fillElement = SizeKt.FillWholeMaxSize;
        SliceAndroidViewKt.SliceAndroidView((Slice) collectAsStateWithLifecycle.getValue(), modifier.then(fillElement), new AncButtonComponent$Content$2$1$1(1, this.viewModel, AncViewModel.class, "onButtonSliceWidthChanged", "onButtonSliceWidthChanged(I)V", 0), false, composerImpl, 3080, 0);
        Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(modifier.then(fillElement), 8);
        composerImpl.startReplaceGroup(1571977968);
        boolean changed2 = composerImpl.changed(stringResource);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue3 == obj) {
            rememberedValue3 = new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent$Content$2$1$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj2;
                    KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                    SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.LiveRegion;
                    KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[3];
                    semanticsPropertyKey.setValue(semanticsPropertyReceiver, new LiveRegionMode());
                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, stringResource);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        composerImpl.end(false);
        Modifier semantics = SemanticsModifierKt.semantics(m98padding3ABfNKs, false, (Function1) rememberedValue3);
        long j = Color.Transparent;
        ButtonKt.Button(new Function0() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent$Content$2$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                final AncPopup ancPopup = AncButtonComponent.this.ancPopup;
                int intValue = ((SnapshotMutableIntStateImpl) mutableIntState).getIntValue();
                ancPopup.uiEventLogger.log(VolumePanelUiEvent.VOLUME_PANEL_ANC_POPUP_SHOWN);
                ComposableLambdaImpl composableLambdaImpl = new ComposableLambdaImpl(-1384461170, true, new Function3() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncPopup$show$1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        ((Number) obj4).intValue();
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        AncPopup.access$Title(AncPopup.this, (Composer) obj3, 8);
                        return Unit.INSTANCE;
                    }
                });
                ComposableLambdaImpl composableLambdaImpl2 = new ComposableLambdaImpl(-304919123, true, new Function3() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncPopup$show$2
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        ((Number) obj4).intValue();
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        AncPopup.access$Content(AncPopup.this, (SystemUIDialog) obj2, (Composer) obj3, 72);
                        return Unit.INSTANCE;
                    }
                });
                ancPopup.volumePanelPopup.show(null, intValue | 80, composableLambdaImpl, composableLambdaImpl2);
                return Unit.INSTANCE;
            }
        }, semantics, isClickable, null, new ButtonColors(j, j, j, j), null, null, null, null, ComposableSingletons$AncButtonComponentKt.f50lambda1, composerImpl, 805306368, 488);
        composerImpl.end(true);
        TextKt.m241Text4IGK_g(stringResource, BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(SemanticsModifierKt.clearAndSetSemantics(companion, new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent$Content$2$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                return Unit.INSTANCE;
            }
        }), 63), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 2, 0, null, MaterialTheme.getTypography(composerImpl).labelMedium, composerImpl, 0, 3072, 57340);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent$Content$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    AncButtonComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
