package com.android.systemui.keyboard.stickykeys.ui.view;

import android.content.Context;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Center$1;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.keyboard.stickykeys.shared.model.Locked;
import com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey;
import com.android.systemui.keyboard.stickykeys.ui.viewmodel.StickyKeysIndicatorViewModel;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class StickyKeysIndicatorKt {
    public static final void StickyKeysIndicator(final StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-322293386);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        StickyKeysIndicator((Map) FlowExtKt.collectAsStateWithLifecycle(stickyKeysIndicatorViewModel.indicatorContent, MapsKt.emptyMap(), composerImpl, 56).getValue(), null, composerImpl, 8, 2);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$StickyKeysIndicator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    StickyKeysIndicatorKt.StickyKeysIndicator(StickyKeysIndicatorViewModel.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0075  */
    /* renamed from: access$StickyKeyText-GLLOLKI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m816access$StickyKeyTextGLLOLKI(final com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r32, final boolean r33, androidx.compose.ui.Modifier r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt.m816access$StickyKeyTextGLLOLKI(com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final ComposeView createStickyKeyIndicatorView(Context context, final StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel) {
        ComposeView composeView = new ComposeView(context, null, 0, 6, null);
        composeView.setContent$1(new ComposableLambdaImpl(1769291460, true, new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$createStickyKeyIndicatorView$1$1
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$createStickyKeyIndicatorView$1$1$1, kotlin.jvm.internal.Lambda] */
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
                final StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel2 = StickyKeysIndicatorViewModel.this;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(474739514, new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$createStickyKeyIndicatorView$1$1.1
                    {
                        super(2);
                    }

                    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$createStickyKeyIndicatorView$1$1$1$1, kotlin.jvm.internal.Lambda] */
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
                        ProvidedValue m = AppBarKt$$ExternalSyntheticOutline0.m(MaterialTheme.getColorScheme(composer2).onSurfaceVariant, ContentColorKt.LocalContentColor);
                        final StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel3 = StickyKeysIndicatorViewModel.this;
                        CompositionLocalKt.CompositionLocalProvider(m, ComposableLambdaKt.rememberComposableLambda(2070702074, new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt.createStickyKeyIndicatorView.1.1.1.1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj5, Object obj6) {
                                Composer composer3 = (Composer) obj5;
                                if ((((Number) obj6).intValue() & 11) == 2) {
                                    ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                                    if (composerImpl3.getSkipping()) {
                                        composerImpl3.skipToGroupEnd();
                                        return Unit.INSTANCE;
                                    }
                                }
                                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                                StickyKeysIndicatorKt.StickyKeysIndicator(StickyKeysIndicatorViewModel.this, composer3, 8);
                                return Unit.INSTANCE;
                            }
                        }, composer2), composer2, 56);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
        return composeView;
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$StickyKeysIndicator$2, kotlin.jvm.internal.Lambda] */
    public static final void StickyKeysIndicator(final Map map, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(371615851);
        Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Modifier modifier3 = modifier2;
        SurfaceKt.m232SurfaceT9BRK9s(SizeKt.m117width3ABfNKs(SizeKt.m110heightInVpY3zN4$default(modifier2, 84, 0.0f, 2), 96), MaterialTheme.getShapes(composerImpl).medium, MaterialTheme.getColorScheme(composerImpl).inverseSurface, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1119523152, new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$StickyKeysIndicator$2
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
                BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
                Arrangement$Center$1 arrangement$Center$1 = Arrangement.Center;
                Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(Modifier.Companion.$$INSTANCE, 16);
                Map map2 = map;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement$Center$1, horizontal, composer2, 54);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m98padding3ABfNKs);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function2);
                }
                Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                composerImpl3.startReplaceGroup(1398080713);
                for (Map.Entry entry : map2.entrySet()) {
                    ModifierKey modifierKey = (ModifierKey) entry.getKey();
                    boolean z = ((Locked) entry.getValue()).locked;
                    composerImpl3.startMovableGroup(-1613251928, modifierKey);
                    StickyKeysIndicatorKt.m816access$StickyKeyTextGLLOLKI(modifierKey, z, null, composer2, 0, 4);
                    composerImpl3.end(false);
                }
                composerImpl3.end(false);
                composerImpl3.end(true);
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 12582912, 120);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt$StickyKeysIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    StickyKeysIndicatorKt.StickyKeysIndicator(map, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
