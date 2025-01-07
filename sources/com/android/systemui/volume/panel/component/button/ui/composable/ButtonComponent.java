package com.android.systemui.volume.panel.component.button.ui.composable;

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
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.ExpandableKt;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.ui.compose.IconKt;
import com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.composable.VolumePanelComposeScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ButtonComponent implements ComposeVolumePanelUiComponent {
    public final Function2 onClick;
    public final ReadonlyStateFlow viewModelFlow;

    public ButtonComponent(ReadonlyStateFlow readonlyStateFlow, Function2 function2) {
        this.viewModelFlow = readonlyStateFlow;
        this.onClick = function2;
    }

    /* JADX WARN: Type inference failed for: r6v8, types: [com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1397659621);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final ButtonViewModel buttonViewModel = (ButtonViewModel) FlowExtKt.collectAsStateWithLifecycle(this.viewModelFlow, composerImpl).getValue();
        if (buttonViewModel == null) {
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$viewModel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        ButtonComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        final String obj = buttonViewModel.label.toString();
        composerImpl.startReplaceGroup(2141306049);
        final float mo51toPx0680j_4 = ((Density) composerImpl.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(((Configuration) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration)).screenWidthDp);
        composerImpl.end(false);
        composerImpl.startReplaceGroup(2141306154);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = SnapshotIntStateKt.mutableIntStateOf(1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableIntState mutableIntState = (MutableIntState) rememberedValue;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(2141306298);
        boolean changed = composerImpl.changed(mo51toPx0680j_4);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$1$1
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
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        BottomComponentButtonSurfaceKt.BottomComponentButtonSurface(null, ComposableLambdaKt.rememberComposableLambda(-440073138, new Function2() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r14v7, types: [com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1$3, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj2, Object obj3) {
                long j;
                long j2;
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(SizeKt.FillWholeMaxSize, 8);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                composerImpl3.startReplaceGroup(-1552117019);
                boolean changed2 = composerImpl3.changed(obj);
                final String str = obj;
                Object rememberedValue3 = composerImpl3.rememberedValue();
                if (changed2 || rememberedValue3 == Composer.Companion.Empty) {
                    rememberedValue3 = new Function1() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj4;
                            SemanticsPropertiesKt.m577setRolekuIjeqM(semanticsPropertyReceiver, 0);
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
                            return Unit.INSTANCE;
                        }
                    };
                    composerImpl3.updateRememberedValue(rememberedValue3);
                }
                composerImpl3.end(false);
                Modifier semantics = SemanticsModifierKt.semantics(m98padding3ABfNKs, false, (Function1) rememberedValue3);
                if (buttonViewModel.isActive) {
                    composerImpl3.startReplaceGroup(-1552116756);
                    j = MaterialTheme.getColorScheme(composerImpl3).tertiaryContainer;
                    composerImpl3.end(false);
                } else {
                    composerImpl3.startReplaceGroup(-1552116651);
                    j = MaterialTheme.getColorScheme(composerImpl3).surface;
                    composerImpl3.end(false);
                }
                RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(20);
                if (buttonViewModel.isActive) {
                    composerImpl3.startReplaceGroup(-1552116422);
                    j2 = MaterialTheme.getColorScheme(composerImpl3).onTertiaryContainer;
                    composerImpl3.end(false);
                } else {
                    composerImpl3.startReplaceGroup(-1552116315);
                    j2 = MaterialTheme.getColorScheme(composerImpl3).onSurface;
                    composerImpl3.end(false);
                }
                final ButtonComponent buttonComponent = this;
                final MutableIntState mutableIntState2 = mutableIntState;
                Function1 function1 = new Function1() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        ButtonComponent.this.onClick.invoke((Expandable) obj4, Integer.valueOf(((SnapshotMutableIntStateImpl) mutableIntState2).getIntValue()));
                        return Unit.INSTANCE;
                    }
                };
                final ButtonViewModel buttonViewModel2 = buttonViewModel;
                ExpandableKt.m724ExpandableQIcBpto(j, m148RoundedCornerShape0680j_4, semantics, j2, null, function1, null, ComposableLambdaKt.rememberComposableLambda(2145209882, new Function3() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$1.3
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        Composer composer3 = (Composer) obj5;
                        ((Number) obj6).intValue();
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                        FillElement fillElement = SizeKt.FillWholeMaxSize;
                        BiasAlignment biasAlignment = Alignment.Companion.Center;
                        ButtonViewModel buttonViewModel3 = ButtonViewModel.this;
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3);
                        ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl4.currentCompositionLocalScope();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, fillElement);
                        ComposeUiNode.Companion.getClass();
                        Function0 function02 = ComposeUiNode.Companion.Constructor;
                        composerImpl4.startReusableNode();
                        if (composerImpl4.inserting) {
                            composerImpl4.createNode(function02);
                        } else {
                            composerImpl4.useNode();
                        }
                        Updater.m259setimpl(composer3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                        Updater.m259setimpl(composer3, currentCompositionLocalScope2, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                        Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                        if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl4, currentCompositeKeyHash, function22);
                        }
                        Updater.m259setimpl(composer3, materializeModifier2, ComposeUiNode.Companion.SetModifier);
                        IconKt.m793IconFNF3uiM(buttonViewModel3.icon, SizeKt.m113size3ABfNKs(companion, 24), 0L, composer3, 48, 4);
                        composerImpl4.end(true);
                        return Unit.INSTANCE;
                    }
                }, composerImpl3), composerImpl3, 12582912, 80);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 48, 1);
        TextKt.m241Text4IGK_g(obj, BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(SemanticsModifierKt.clearAndSetSemantics(Modifier.Companion.$$INSTANCE, new Function1() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$2$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                return Unit.INSTANCE;
            }
        }), 63), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 2, 0, null, MaterialTheme.getTypography(composerImpl).labelMedium, composerImpl, 0, 3072, 57340);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.block = new Function2() { // from class: com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent$Content$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    ButtonComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
