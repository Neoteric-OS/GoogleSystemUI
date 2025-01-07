package com.android.systemui.statusbar.policy.ui.dialog.composable;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.foundation.BasicMarqueeKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.font.FontWeight;
import com.android.systemui.common.ui.compose.IconKt;
import com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModeTileViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ModeTileKt {
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1, kotlin.jvm.internal.Lambda] */
    public static final void ModeTile(final ModeTileViewModel modeTileViewModel, Composer composer, final int i) {
        int i2;
        long j;
        long j2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-406963538);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(modeTileViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean z = modeTileViewModel.enabled;
            if (z) {
                composerImpl.startReplaceGroup(958260777);
                j = MaterialTheme.getColorScheme(composerImpl).primary;
                composerImpl.end(false);
            } else {
                composerImpl.startReplaceGroup(958260828);
                j = MaterialTheme.getColorScheme(composerImpl).surfaceVariant;
                composerImpl.end(false);
            }
            final State m7animateColorAsStateeuL9pac = SingleValueAnimationKt.m7animateColorAsStateeuL9pac(j, null, null, composerImpl, 0, 14);
            if (z) {
                composerImpl.startReplaceGroup(958260974);
                j2 = MaterialTheme.getColorScheme(composerImpl).onPrimary;
                composerImpl.end(false);
            } else {
                composerImpl.startReplaceGroup(958261027);
                j2 = MaterialTheme.getColorScheme(composerImpl).onSurfaceVariant;
                composerImpl.end(false);
            }
            CompositionLocalKt.CompositionLocalProvider(AppBarKt$$ExternalSyntheticOutline0.m(((Color) SingleValueAnimationKt.m7animateColorAsStateeuL9pac(j2, null, null, composerImpl, 0, 14).getValue()).value, ContentColorKt.LocalContentColor), ComposableLambdaKt.rememberComposableLambda(653639534, new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX WARN: Type inference failed for: r14v9, types: [com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1$1, kotlin.jvm.internal.Lambda] */
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
                    long j3 = ((Color) State.this.getValue()).value;
                    RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(16);
                    final ModeTileViewModel modeTileViewModel2 = modeTileViewModel;
                    SurfaceKt.m232SurfaceT9BRK9s(null, m148RoundedCornerShape0680j_4, j3, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1081513225, new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            Composer composer3 = (Composer) obj3;
                            if ((((Number) obj4).intValue() & 11) == 2) {
                                ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                                if (composerImpl3.getSkipping()) {
                                    composerImpl3.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey3 = ComposerKt.invocation;
                            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                            ModeTileViewModel modeTileViewModel3 = ModeTileViewModel.this;
                            Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(ClickableKt.m35combinedClickablef5TDLPQ$default(companion, null, modeTileViewModel3.onLongClickLabel, modeTileViewModel3.onLongClick, modeTileViewModel3.onClick, 103), 16);
                            ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                            composerImpl4.startReplaceGroup(-587880937);
                            boolean changed = composerImpl4.changed(ModeTileViewModel.this);
                            final ModeTileViewModel modeTileViewModel4 = ModeTileViewModel.this;
                            Object rememberedValue = composerImpl4.rememberedValue();
                            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                            if (changed || rememberedValue == composer$Companion$Empty$1) {
                                rememberedValue = new Function1() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1$1$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj5) {
                                        SemanticsPropertiesKt.setStateDescription((SemanticsPropertyReceiver) obj5, ModeTileViewModel.this.stateDescription);
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerImpl4.updateRememberedValue(rememberedValue);
                            }
                            composerImpl4.end(false);
                            Modifier semantics = SemanticsModifierKt.semantics(m98padding3ABfNKs, false, (Function1) rememberedValue);
                            BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                            Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
                            BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
                            Arrangement.SpacedAligned m79spacedByD5KLDUw = Arrangement.m79spacedByD5KLDUw(12, horizontal);
                            final ModeTileViewModel modeTileViewModel5 = ModeTileViewModel.this;
                            RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m79spacedByD5KLDUw, vertical, composerImpl4, 54);
                            int i3 = composerImpl4.compoundKeyHash;
                            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl4.currentCompositionLocalScope();
                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl4, semantics);
                            ComposeUiNode.Companion.getClass();
                            Function0 function0 = ComposeUiNode.Companion.Constructor;
                            composerImpl4.startReusableNode();
                            if (composerImpl4.inserting) {
                                composerImpl4.createNode(function0);
                            } else {
                                composerImpl4.useNode();
                            }
                            Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                            Updater.m259setimpl(composerImpl4, rowMeasurePolicy, function2);
                            Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                            Updater.m259setimpl(composerImpl4, currentCompositionLocalScope, function22);
                            Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                            if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(i3))) {
                                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl4, i3, function23);
                            }
                            Function2 function24 = ComposeUiNode.Companion.SetModifier;
                            Updater.m259setimpl(composerImpl4, materializeModifier, function24);
                            IconKt.m793IconFNF3uiM(modeTileViewModel5.icon, SizeKt.m113size3ABfNKs(companion, 24), 0L, composerImpl4, 48, 4);
                            ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, horizontal, composerImpl4, 0);
                            int i4 = composerImpl4.compoundKeyHash;
                            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl4.currentCompositionLocalScope();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl4, companion);
                            composerImpl4.startReusableNode();
                            if (composerImpl4.inserting) {
                                composerImpl4.createNode(function0);
                            } else {
                                composerImpl4.useNode();
                            }
                            Updater.m259setimpl(composerImpl4, columnMeasurePolicy, function2);
                            Updater.m259setimpl(composerImpl4, currentCompositionLocalScope2, function22);
                            if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(i4))) {
                                AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl4, i4, function23);
                            }
                            Updater.m259setimpl(composerImpl4, materializeModifier2, function24);
                            TextKt.m241Text4IGK_g(modeTileViewModel5.text, TestTagKt.testTag(BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(companion, 62), "name"), 0L, 0L, null, FontWeight.W500, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl4, 196608, 0, 131036);
                            FontWeight fontWeight = FontWeight.W400;
                            Modifier testTag = TestTagKt.testTag(BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(companion, 62), modeTileViewModel5.enabled ? "stateOn" : "stateOff");
                            composerImpl4.startReplaceGroup(-789331845);
                            boolean changed2 = composerImpl4.changed(modeTileViewModel5);
                            Object rememberedValue2 = composerImpl4.rememberedValue();
                            if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
                                rememberedValue2 = new Function1() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$1$1$2$1$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj5) {
                                        SemanticsPropertiesKt.setContentDescription((SemanticsPropertyReceiver) obj5, ModeTileViewModel.this.subtextDescription);
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerImpl4.updateRememberedValue(rememberedValue2);
                            }
                            composerImpl4.end(false);
                            TextKt.m241Text4IGK_g(modeTileViewModel5.subtext, SemanticsModifierKt.clearAndSetSemantics(testTag, (Function1) rememberedValue2), 0L, 0L, null, fontWeight, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl4, 196608, 0, 131036);
                            composerImpl4.end(true);
                            composerImpl4.end(true);
                            return Unit.INSTANCE;
                        }
                    }, composer2), composer2, 12582912, 121);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileKt$ModeTile$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ModeTileKt.ModeTile(ModeTileViewModel.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
