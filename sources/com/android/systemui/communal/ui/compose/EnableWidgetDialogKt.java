package com.android.systemui.communal.ui.compose;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.style.TextAlign;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EnableWidgetDialogKt {
    public static final void EnableWidgetDialog(final boolean z, final SystemUIDialogFactory systemUIDialogFactory, final String str, final String str2, final Function0 function0, final Function0 function02, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1900524164);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-1549863291);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        final Context context = ((View) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalView)).getContext();
        EffectsKt.DisposableEffect(Boolean.valueOf(z), new Function1() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$EnableWidgetDialog$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                if (z) {
                    MutableState mutableState2 = mutableState;
                    SystemUIDialogFactory systemUIDialogFactory2 = systemUIDialogFactory;
                    Context context2 = context;
                    final String str3 = str;
                    final String str4 = str2;
                    final Function0 function03 = function0;
                    final Function0 function04 = function02;
                    mutableState2.setValue(SystemUIDialogFactoryExtKt.create$default(systemUIDialogFactory2, context2, null, new ComposableLambdaImpl(-1476030410, true, new Function3() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$EnableWidgetDialog$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            ((Number) obj4).intValue();
                            OpaqueKey opaqueKey2 = ComposerKt.invocation;
                            EnableWidgetDialogKt.access$DialogComposable(str3, str4, function03, function04, (Composer) obj3, 0);
                            return Unit.INSTANCE;
                        }
                    }), 14));
                    ComponentSystemUIDialog componentSystemUIDialog = (ComponentSystemUIDialog) mutableState.getValue();
                    if (componentSystemUIDialog != null) {
                        final Function0 function05 = function02;
                        componentSystemUIDialog.setCancelable(true);
                        componentSystemUIDialog.setCanceledOnTouchOutside(true);
                        componentSystemUIDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$EnableWidgetDialog$1$2$1
                            @Override // android.content.DialogInterface.OnCancelListener
                            public final void onCancel(DialogInterface dialogInterface) {
                                Function0.this.invoke();
                            }
                        });
                        componentSystemUIDialog.show();
                    }
                }
                final MutableState mutableState3 = mutableState;
                return new DisposableEffectResult() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$EnableWidgetDialog$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        MutableState mutableState4 = MutableState.this;
                        ComponentSystemUIDialog componentSystemUIDialog2 = (ComponentSystemUIDialog) mutableState4.getValue();
                        if (componentSystemUIDialog2 != null) {
                            componentSystemUIDialog2.dismiss();
                        }
                        mutableState4.setValue(null);
                    }
                };
            }
        }, composerImpl);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$EnableWidgetDialog$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EnableWidgetDialogKt.EnableWidgetDialog(z, systemUIDialogFactory, str, str2, function0, function02, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$DialogComposable$1$1$2$1$1, kotlin.jvm.internal.Lambda] */
    public static final void access$DialogComposable(final String str, final String str2, final Function0 function0, final Function0 function02, Composer composer, final int i) {
        int i2;
        Function2 function2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-906920275);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(str2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerImpl.changedInstance(function02) ? 2048 : 1024;
        }
        if ((i2 & 5851) == 1170 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(SizeKt.fillMaxWidth(companion, 1.0f), 0.0f, 18, 0.0f, 8, 5);
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidColorSchemeKt.LocalAndroidColorScheme;
            Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(m102paddingqDBjuR0$default, ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).surfaceBright, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(28));
            BiasAlignment biasAlignment = Alignment.Companion.TopStart;
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m25backgroundbw27NRU);
            ComposeUiNode.Companion.getClass();
            Function0 function03 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Function2 function22 = ComposeUiNode.Companion.SetMeasurePolicy;
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function22);
            Function2 function23 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function23);
            Function2 function24 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function24);
            }
            Function2 function25 = ComposeUiNode.Companion.SetModifier;
            Updater.m259setimpl(composerImpl, materializeModifier, function25);
            Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
            ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.m78spacedBy0680j_4(20), Alignment.Companion.Start, composerImpl, 6);
            int i4 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, fillMaxWidth);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, columnMeasurePolicy, function22);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function23);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function24);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, function25);
            Modifier wrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth(PaddingKt.m100paddingVpY3zN4$default(companion, 24, 0.0f, 2), 1.0f), 3);
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
            int i5 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, wrapContentHeight$default);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function22);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function23);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function24);
            }
            Updater.m259setimpl(composerImpl, materializeModifier3, function25);
            TextKt.m241Text4IGK_g(str, null, ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onSurface, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 1, 0, null, MaterialTheme.getTypography(composerImpl).titleMedium, composerImpl, i2 & 14, 3072, 56826);
            composerImpl.end(true);
            Modifier wrapContentHeight$default2 = SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth(PaddingKt.m102paddingqDBjuR0$default(companion, 0.0f, 0.0f, 12, 0.0f, 11), 1.0f), 3);
            MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.Center, false);
            int i6 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composerImpl, wrapContentHeight$default2);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy3, function22);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope4, function23);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
                function2 = function24;
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function2);
            } else {
                function2 = function24;
            }
            Updater.m259setimpl(composerImpl, materializeModifier4, function25);
            Modifier fillMaxWidth2 = SizeKt.fillMaxWidth(companion, 1.0f);
            RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.End, Alignment.Companion.Top, composerImpl, 6);
            int i7 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope5 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composerImpl, fillMaxWidth2);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, rowMeasurePolicy, function22);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope5, function23);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i7))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i7, composerImpl, i7, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier5, function25);
            float f = 16;
            ButtonKt.TextButton(function02, null, false, null, null, null, null, new PaddingValuesImpl(f, f, f, f), null, ComposableSingletons$EnableWidgetDialogKt.f24lambda1, composerImpl, ((i2 >> 9) & 14) | 817889280, 382);
            ButtonKt.TextButton(function0, null, false, null, null, null, null, new PaddingValuesImpl(f, f, f, f), null, ComposableLambdaKt.rememberComposableLambda(-1297407490, new Function3() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$DialogComposable$1$1$2$1$1
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
                    TextKt.m241Text4IGK_g(str2, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 131070);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, ((i2 >> 6) & 14) | 817889280, 382);
            composerImpl.end(true);
            composerImpl.end(true);
            composerImpl.end(true);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.EnableWidgetDialogKt$DialogComposable$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EnableWidgetDialogKt.access$DialogComposable(str, str2, function0, function02, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
