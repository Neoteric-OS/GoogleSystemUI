package com.android.systemui.keyboard.shortcut.ui.composable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FlowLayoutKt;
import androidx.compose.foundation.layout.FlowRowScope;
import androidx.compose.foundation.layout.FlowRowScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.VerticalAlignElement;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListInterval;
import androidx.compose.foundation.lazy.LazyListIntervalContent;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt$$ExternalSyntheticOutline0;
import androidx.compose.material.icons.filled.AppsKt;
import androidx.compose.material.icons.filled.ExpandMoreKt;
import androidx.compose.material.icons.filled.KeyboardKt;
import androidx.compose.material.icons.filled.TvKt;
import androidx.compose.material.icons.filled.VerticalSplitKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MinimumInteractiveModifier;
import androidx.compose.material3.NavigationDrawerItemColors;
import androidx.compose.material3.NavigationDrawerItemDefaults;
import androidx.compose.material3.SearchBarColors;
import androidx.compose.material3.SearchBarDefaults;
import androidx.compose.material3.SearchBar_androidKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarColors;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.NestedScrollInteropConnection;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.android.compose.ui.graphics.painter.DrawablePainterKt;
import com.android.systemui.keyboard.shortcut.shared.model.Shortcut;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategory;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCommand;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutIcon;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutKey;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutSubCategory;
import com.android.systemui.keyboard.shortcut.ui.model.IconSource;
import com.android.systemui.keyboard.shortcut.ui.model.ShortcutsUiState;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.wm.shell.R;
import java.util.List;
import java.util.Locale;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShortcutHelperKt {
    public static final void ActiveShortcutHelper(final ShortcutsUiState.Active active, final Function2 function2, final Function1 function1, final Modifier modifier, final Function0 function0, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(525891623);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object obj = active.defaultSelectedCategory;
        composerImpl.startReplaceGroup(-1920948257);
        boolean changed = composerImpl.changed(obj);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj2 = Composer.Companion.Empty;
        if (changed || rememberedValue == obj2) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(active.defaultSelectedCategory);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        int i2 = i >> 3;
        if (((Boolean) function2.invoke(composerImpl, Integer.valueOf(i2 & 14))).booleanValue()) {
            composerImpl.startReplaceGroup(-1920948090);
            List list = active.shortcutCategories;
            ShortcutCategoryType shortcutCategoryType = (ShortcutCategoryType) mutableState.getValue();
            composerImpl.startReplaceGroup(-1920947872);
            boolean changed2 = composerImpl.changed(mutableState);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue2 == obj2) {
                rememberedValue2 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ActiveShortcutHelper$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        MutableState.this.setValue((ShortcutCategoryType) obj3);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            ShortcutHelperSinglePane((i2 & 112) | 512 | (458752 & (i << 3)) | ((i << 9) & 3670016), 0, composerImpl, modifier, shortcutCategoryType, active.searchQuery, list, function0, function1, (Function1) rememberedValue2);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(-1920947749);
            List list2 = active.shortcutCategories;
            ShortcutCategoryType shortcutCategoryType2 = (ShortcutCategoryType) mutableState.getValue();
            composerImpl.startReplaceGroup(-1920947512);
            boolean changed3 = composerImpl.changed(mutableState);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed3 || rememberedValue3 == obj2) {
                rememberedValue3 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ActiveShortcutHelper$2$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        MutableState.this.setValue((ShortcutCategoryType) obj3);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            ShortcutHelperTwoPane((i2 & 896) | (i2 & 112) | 4096 | ((i << 6) & 3670016), 0, composerImpl, modifier, shortcutCategoryType2, active.searchQuery, list2, function0, function1, (Function1) rememberedValue3);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ActiveShortcutHelper$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    ShortcutHelperKt.ActiveShortcutHelper(ShortcutsUiState.Active.this, function2, function1, modifier, function0, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void CategoriesPanelSinglePane(final String str, final List list, final ShortcutCategoryType shortcutCategoryType, final Function1 function1, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-721585285);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(2);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_4, Alignment.Companion.Start, composerImpl, 6);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, companion);
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
        composerImpl.startReplaceGroup(1462750473);
        int size = list.size();
        int i3 = 0;
        while (i3 < size) {
            final ShortcutCategory shortcutCategory = (ShortcutCategory) list.get(i3);
            final boolean areEqual = Intrinsics.areEqual(shortcutCategoryType, shortcutCategory.type);
            CategoryItemSinglePane(str, shortcutCategory, areEqual, new Function0() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoriesPanelSinglePane$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function1.this.invoke(areEqual ? null : shortcutCategory.type);
                    return Unit.INSTANCE;
                }
            }, list.size() == 1 ? ShortcutHelper$Shapes.singlePaneSingleCategory : i3 == 0 ? ShortcutHelper$Shapes.singlePaneFirstCategory : i3 == CollectionsKt__CollectionsKt.getLastIndex(list) ? ShortcutHelper$Shapes.singlePaneLastCategory : ShortcutHelper$Shapes.singlePaneCategory, composerImpl, (i & 14) | 64);
            i3++;
        }
        composerImpl.end(false);
        composerImpl.end(true);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoriesPanelSinglePane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.CategoriesPanelSinglePane(str, list, shortcutCategoryType, function1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void CategoriesPanelTwoPane(final List list, final ShortcutCategoryType shortcutCategoryType, final Function1 function1, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(390659852);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, companion);
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
        composerImpl.startReplaceGroup(-120454532);
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            final ShortcutCategory shortcutCategory = (ShortcutCategory) list.get(i3);
            CategoryItemTwoPane(label(shortcutCategory, (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)), getIcon(shortcutCategory, composerImpl), Intrinsics.areEqual(shortcutCategoryType, shortcutCategory.type), new Function0() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoriesPanelTwoPane$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function1.this.invoke(shortcutCategory);
                    return Unit.INSTANCE;
                }
            }, null, composerImpl, 64, 16);
        }
        composerImpl.end(false);
        composerImpl.end(true);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoriesPanelTwoPane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.CategoriesPanelTwoPane(list, shortcutCategoryType, function1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemSinglePane$1, kotlin.jvm.internal.Lambda] */
    public static final void CategoryItemSinglePane(final String str, final ShortcutCategory shortcutCategory, final boolean z, final Function0 function0, final Shape shape, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1974665172);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        SurfaceKt.m233Surfaceo_FOJdg(function0, null, false, shape, MaterialTheme.getColorScheme(composerImpl).surfaceBright, 0L, 0.0f, null, null, ComposableLambdaKt.rememberComposableLambda(-1729351073, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemSinglePane$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r0v16, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemSinglePane$1$1$2, kotlin.jvm.internal.Lambda] */
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
                boolean z2 = z;
                final ShortcutCategory shortcutCategory2 = shortcutCategory;
                final String str2 = str;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composer2, 0);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, companion);
                ComposeUiNode.Companion.getClass();
                Function0 function02 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, columnMeasurePolicy, function2);
                Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Function2 function24 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier, function24);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                float f = 16;
                Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(SizeKt.m110heightInVpY3zN4$default(SizeKt.fillMaxWidth(companion, 1.0f), 88, 0.0f, 2), f, 0.0f, 2);
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, vertical, composer2, 48);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, m100paddingVpY3zN4$default);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, rowMeasurePolicy, function2);
                Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier2, function24);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ShortcutHelperKt.m812ShortcutCategoryIconww6aTOc(ShortcutHelperKt.getIcon(shortcutCategory2, composer2), SizeKt.m113size3ABfNKs(companion, 24), null, 0L, composer2, 56, 12);
                SpacerKt.Spacer(composer2, SizeKt.m117width3ABfNKs(companion, f));
                TextKt.m241Text4IGK_g(ShortcutHelperKt.label(shortcutCategory2, (Context) composerImpl3.consume(AndroidCompositionLocals_androidKt.LocalContext)), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 131070);
                SpacerKt.Spacer(composer2, rowScopeInstance.weight(companion, 1.0f, true));
                ShortcutHelperKt.access$RotatingExpandCollapseIcon(z2, composer2, 0);
                composerImpl3.end(true);
                AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, z2, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-198846291, new Function3() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemSinglePane$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        ((Number) obj5).intValue();
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        ShortcutHelperKt.access$ShortcutCategoryDetailsSinglePane(str2, shortcutCategory2, (Composer) obj4, 64);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, 1572870, 30);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, ((i >> 9) & 14) | ((i >> 3) & 7168), 998);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemSinglePane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.CategoryItemSinglePane(str, shortcutCategory, z, function0, shape, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemTwoPane$2, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.keyboard.shortcut.ui.composable.SurfacesKt$SelectableShortcutSurface$1, kotlin.jvm.internal.Lambda] */
    public static final void CategoryItemTwoPane(final String str, final IconSource iconSource, final boolean z, final Function0 function0, NavigationDrawerItemColors navigationDrawerItemColors, Composer composer, final int i, final int i2) {
        final NavigationDrawerItemColors navigationDrawerItemColors2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(322846695);
        if ((i2 & 16) != 0) {
            int i3 = NavigationDrawerItemDefaults.$r8$clinit;
            navigationDrawerItemColors2 = NavigationDrawerItemDefaults.m218colorsoq7We08(Color.Transparent, composerImpl);
        } else {
            navigationDrawerItemColors2 = navigationDrawerItemColors;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Modifier fillMaxWidth = SizeKt.fillMaxWidth(SizeKt.m110heightInVpY3zN4$default(SemanticsModifierKt.semantics(Modifier.Companion.$$INSTANCE, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemTwoPane$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsPropertiesKt.m577setRolekuIjeqM((SemanticsPropertyReceiver) obj, 4);
                return Unit.INSTANCE;
            }
        }), 64, 0.0f, 2), 1.0f);
        float f = 28;
        final RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(f);
        final long j = ((Color) navigationDrawerItemColors2.containerColor(z, composerImpl).getValue()).value;
        final InteractionsConfig interactionsConfig = new InteractionsConfig(MaterialTheme.getColorScheme(composerImpl).onSurface, MaterialTheme.getColorScheme(composerImpl).onSurface, MaterialTheme.getColorScheme(composerImpl).secondary, 3, 2, f, 33, 0.0f, 1536);
        final ComposableLambdaImpl rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2084289772, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemTwoPane$2
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
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                float f2 = 24;
                Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(companion, f2, 0.0f, 2);
                BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                NavigationDrawerItemColors navigationDrawerItemColors3 = NavigationDrawerItemColors.this;
                boolean z2 = z;
                IconSource iconSource2 = iconSource;
                String str2 = str;
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, vertical, composer2, 48);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m100paddingVpY3zN4$default);
                ComposeUiNode.Companion.getClass();
                Function0 function02 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, rowMeasurePolicy, function2);
                Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Function2 function24 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier, function24);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ShortcutHelperKt.m812ShortcutCategoryIconww6aTOc(iconSource2, SizeKt.m113size3ABfNKs(companion, f2), null, ((Color) navigationDrawerItemColors3.iconColor(z2, composer2).getValue()).value, composer2, 440, 0);
                SpacerKt.Spacer(composer2, SizeKt.m117width3ABfNKs(companion, 12));
                Modifier weight = rowScopeInstance.weight(companion, 1.0f, true);
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, weight);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy, function2);
                Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier2, function24);
                TextKt.m241Text4IGK_g(str2, null, ((Color) navigationDrawerItemColors3.textColor(z2, composer2).getValue()).value, TextUnitKt.getSp(18), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer2).headlineSmall, composer2, 3072, 0, 65522);
                composerImpl3.end(true);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl);
        composerImpl.startReplaceGroup(-166635968);
        long m203contentColorForek8zF_U = ColorSchemeKt.m203contentColorForek8zF_U(j, composerImpl);
        final float f2 = 0;
        composerImpl.startReplaceGroup(649000420);
        composerImpl.startReplaceGroup(649000441);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = InteractionSourceKt.MutableInteractionSource();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
        composerImpl.end(false);
        composerImpl.end(false);
        DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = SurfaceKt.LocalAbsoluteTonalElevation;
        final float f3 = ((Dp) composerImpl.consume(dynamicProvidableCompositionLocal)).value + f2;
        CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{AppBarKt$$ExternalSyntheticOutline0.m(m203contentColorForek8zF_U, ContentColorKt.LocalContentColor), dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(new Dp(f3))}, ComposableLambdaKt.rememberComposableLambda(498694528, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.SurfacesKt$SelectableShortcutSurface$1
            final /* synthetic */ BorderStroke $border = null;
            final /* synthetic */ boolean $enabled = true;

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
                MutableState collectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(MutableInteractionSource.this, composer2, 0);
                Modifier modifier = fillMaxWidth;
                StaticProvidableCompositionLocal staticProvidableCompositionLocal = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                Modifier m814access$surfaceXOJAsU = SurfacesKt.m814access$surfaceXOJAsU(modifier.then(MinimumInteractiveModifier.INSTANCE), m148RoundedCornerShape0680j_4, SurfacesKt.m815access$surfaceColorAtElevationCLU3JFs(j, f3, composer2), this.$border, ((Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(f2));
                boolean z2 = z;
                MutableInteractionSource mutableInteractionSource2 = MutableInteractionSource.this;
                Modifier m145selectableO2vRcR0$default = SelectableKt.m145selectableO2vRcR0$default(m814access$surfaceXOJAsU, z2, mutableInteractionSource2, new ShortcutHelperIndication(mutableInteractionSource2, interactionsConfig), this.$enabled, function0, 16);
                if (((Boolean) collectIsFocusedAsState.getValue()).booleanValue()) {
                    m145selectableO2vRcR0$default = m145selectableO2vRcR0$default.then(ZIndexModifierKt.zIndex(Modifier.Companion.$$INSTANCE, 1.0f));
                }
                Function2 function2 = rememberComposableLambda;
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, true);
                int i4 = composerImpl3.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, m145selectableO2vRcR0$default);
                ComposeUiNode.Companion.getClass();
                Function0 function02 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i4))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl3, i4, function22);
                }
                Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m(0, function2, composerImpl3, true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
        composerImpl.end(false);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final NavigationDrawerItemColors navigationDrawerItemColors3 = navigationDrawerItemColors2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$CategoryItemTwoPane$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.CategoryItemTwoPane(str, iconSource, z, function0, navigationDrawerItemColors3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void EndSidePanel(final String str, final Modifier modifier, final ShortcutCategory shortcutCategory, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1059171825);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(1196701697);
        if (shortcutCategory == null) {
            m811NoSearchResultsTextkHDZbjc(24, false, composerImpl, 54);
            composerImpl.end(false);
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        ShortcutHelperKt.EndSidePanel(str, modifier, shortcutCategory, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        composerImpl.end(false);
        View view = (View) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalView);
        boolean changed = composerImpl.changed(view);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new NestedScrollInteropConnection(view);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        LazyDslKt.LazyColumn(NestedScrollModifierKt.nestedScroll(modifier, (NestedScrollInteropConnection) rememberedValue, null), null, null, false, null, null, null, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3] */
            /* JADX WARN: Type inference failed for: r4v2, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyListScope lazyListScope = (LazyListScope) obj;
                final List list = ShortcutCategory.this.subCategories;
                final AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ((ShortcutSubCategory) obj2).label;
                    }
                };
                final String str2 = str;
                final ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$1 shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$1 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$1
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return null;
                    }
                };
                ((LazyListIntervalContent) lazyListScope).intervals.addInterval(list.size(), new LazyListInterval(anonymousClass1 != null ? new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return anonymousClass1.invoke(list.get(((Number) obj2).intValue()));
                    }
                } : null, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$1.invoke(list.get(((Number) obj2).intValue()));
                    }
                }, new ComposableLambdaImpl(-632812321, true, new Function4() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        int i2;
                        LazyItemScope lazyItemScope = (LazyItemScope) obj2;
                        int intValue = ((Number) obj3).intValue();
                        Composer composer2 = (Composer) obj4;
                        int intValue2 = ((Number) obj5).intValue();
                        if ((intValue2 & 6) == 0) {
                            i2 = (((ComposerImpl) composer2).changed(lazyItemScope) ? 4 : 2) | intValue2;
                        } else {
                            i2 = intValue2;
                        }
                        if ((intValue2 & 48) == 0) {
                            i2 |= ((ComposerImpl) composer2).changed(intValue) ? 32 : 16;
                        }
                        if ((i2 & 147) == 146) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        ShortcutSubCategory shortcutSubCategory = (ShortcutSubCategory) list.get(intValue);
                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                        composerImpl3.startReplaceGroup(1229106918);
                        ShortcutHelperKt.access$SubCategoryContainerDualPane(str2, shortcutSubCategory, composerImpl3, 64);
                        SpacerKt.Spacer(composerImpl3, SizeKt.m108height3ABfNKs(Modifier.Companion.$$INSTANCE, 8));
                        composerImpl3.end(false);
                        return Unit.INSTANCE;
                    }
                })));
                return Unit.INSTANCE;
            }
        }, composerImpl, 0, 254);
        RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.EndSidePanel(str, modifier, shortcutCategory, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [com.android.systemui.keyboard.shortcut.ui.composable.SurfacesKt$ClickableShortcutSurface$1, kotlin.jvm.internal.Lambda] */
    /* renamed from: KeyboardSettings-ixp7dh8, reason: not valid java name */
    public static final void m810KeyboardSettingsixp7dh8(final float f, final float f2, final Function0 function0, Composer composer, final int i) {
        int i2;
        boolean z;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-258302129);
        if ((i & 896) == 0) {
            i2 = (composerImpl.changedInstance(function0) ? 256 : 128) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 641) == 128 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl.startReplaceGroup(-298307952);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerImpl.updateRememberedValue(rememberedValue);
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
            composerImpl.end(false);
            float f3 = 24;
            final RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(f3);
            final long j = Color.Transparent;
            final Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(SizeKt.fillMaxWidth(SemanticsModifierKt.semantics(Modifier.Companion.$$INSTANCE, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$KeyboardSettings$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertiesKt.m577setRolekuIjeqM((SemanticsPropertyReceiver) obj, 0);
                    return Unit.INSTANCE;
                }
            }), 1.0f), 12, 0.0f, 2);
            float f4 = 8;
            final InteractionsConfig interactionsConfig = new InteractionsConfig(MaterialTheme.getColorScheme(composerImpl).onSurface, MaterialTheme.getColorScheme(composerImpl).onSurface, MaterialTheme.getColorScheme(composerImpl).secondary, 3, f4, f3, 28, f4, 1024);
            ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$ShortcutHelperKt.f30lambda1;
            composerImpl.startReplaceGroup(1057327756);
            long m203contentColorForek8zF_U = ColorSchemeKt.m203contentColorForek8zF_U(j, composerImpl);
            final float f5 = 0;
            composerImpl.startReplaceGroup(1210357307);
            if (mutableInteractionSource == null) {
                composerImpl.startReplaceGroup(1210357328);
                Object rememberedValue2 = composerImpl.rememberedValue();
                if (rememberedValue2 == composer$Companion$Empty$1) {
                    rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                    composerImpl.updateRememberedValue(rememberedValue2);
                }
                mutableInteractionSource = (MutableInteractionSource) rememberedValue2;
                z = false;
                composerImpl.end(false);
            } else {
                z = false;
            }
            final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
            composerImpl.end(z);
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = SurfaceKt.LocalAbsoluteTonalElevation;
            final float f6 = ((Dp) composerImpl.consume(dynamicProvidableCompositionLocal)).value + f5;
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{AppBarKt$$ExternalSyntheticOutline0.m(m203contentColorForek8zF_U, ContentColorKt.LocalContentColor), dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(new Dp(f6))}, ComposableLambdaKt.rememberComposableLambda(-649281716, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.SurfacesKt$ClickableShortcutSurface$1
                final /* synthetic */ BorderStroke $border;
                final /* synthetic */ Function2 $content;
                final /* synthetic */ boolean $enabled;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                    ComposableLambdaImpl composableLambdaImpl2 = ComposableSingletons$ShortcutHelperKt.f34lambda5;
                    this.$border = null;
                    this.$enabled = true;
                    this.$content = composableLambdaImpl2;
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
                    Modifier modifier = m100paddingVpY3zN4$default;
                    StaticProvidableCompositionLocal staticProvidableCompositionLocal = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    Modifier m814access$surfaceXOJAsU = SurfacesKt.m814access$surfaceXOJAsU(modifier.then(MinimumInteractiveModifier.INSTANCE), m148RoundedCornerShape0680j_4, SurfacesKt.m815access$surfaceColorAtElevationCLU3JFs(j, f6, composer2), this.$border, ((Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(f5));
                    MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                    Modifier m31clickableO2vRcR0$default = ClickableKt.m31clickableO2vRcR0$default(m814access$surfaceXOJAsU, mutableInteractionSource3, new ShortcutHelperIndication(mutableInteractionSource3, interactionsConfig), this.$enabled, null, function0, 24);
                    Function2 function2 = this.$content;
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, true);
                    int i3 = composerImpl3.compoundKeyHash;
                    PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, m31clickableO2vRcR0$default);
                    ComposeUiNode.Companion.getClass();
                    Function0 function02 = ComposeUiNode.Companion.Constructor;
                    composerImpl3.startReusableNode();
                    if (composerImpl3.inserting) {
                        composerImpl3.createNode(function02);
                    } else {
                        composerImpl3.useNode();
                    }
                    Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                    Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                    Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i3))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl3, i3, function22);
                    }
                    Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                    SimpleLayoutKt$$ExternalSyntheticOutline0.m(0, function2, composerImpl3, true);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$KeyboardSettings$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.m810KeyboardSettingsixp7dh8(f, f2, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: NoSearchResultsText-kHDZbjc, reason: not valid java name */
    public static final void m811NoSearchResultsTextkHDZbjc(final float f, final boolean z, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-1804910765);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changed(f) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl2.changed(z) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier fillMaxWidth = SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f);
            if (z) {
                fillMaxWidth = fillMaxWidth.then(SizeKt.FillWholeMaxHeight);
            }
            composerImpl = composerImpl2;
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.shortcut_helper_no_search_results, composerImpl2), PaddingKt.m99paddingVpY3zN4(BackgroundKt.m25backgroundbw27NRU(PaddingKt.m100paddingVpY3zN4$default(fillMaxWidth, 0.0f, 8, 1), MaterialTheme.getColorScheme(composerImpl2).surfaceBright, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(28)), f, 24), MaterialTheme.getColorScheme(composerImpl2).onSurface, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).bodyMedium, composerImpl, 0, 0, 65528);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$NoSearchResultsText$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.m811NoSearchResultsTextkHDZbjc(f, z, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: ShortcutCategoryIcon-ww6aTOc, reason: not valid java name */
    public static final void m812ShortcutCategoryIconww6aTOc(final IconSource iconSource, Modifier modifier, String str, long j, Composer composer, final int i, final int i2) {
        long j2;
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1719048341);
        Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        String str2 = (i2 & 4) != 0 ? null : str;
        if ((i2 & 8) != 0) {
            i3 = i & (-7169);
            j2 = ((Color) composerImpl.consume(ContentColorKt.LocalContentColor)).value;
        } else {
            j2 = j;
            i3 = i;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (iconSource.imageVector != null) {
            composerImpl.startReplaceGroup(240899252);
            IconKt.m214Iconww6aTOc(iconSource.imageVector, str2, modifier2, j2, composerImpl, ((i3 >> 3) & 112) | ((i3 << 3) & 896) | (i3 & 7168), 0);
            composerImpl.end(false);
        } else if (iconSource.painter != null) {
            composerImpl.startReplaceGroup(240899362);
            ImageKt.Image(iconSource.painter, str2, modifier2, null, null, 0.0f, null, composerImpl, ((i3 >> 3) & 112) | 8 | ((i3 << 3) & 896), 120);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(240899419);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final String str3 = str2;
            final long j3 = j2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutCategoryIcon$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.m812ShortcutCategoryIconww6aTOc(IconSource.this, modifier3, str3, j3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ShortcutDescriptionText(final String str, final Shortcut shortcut, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1915721858);
        Modifier modifier2 = (i2 & 4) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        String str2 = shortcut.label;
        composerImpl.startReplaceGroup(1744991708);
        AnnotatedString.Builder builder = new AnnotatedString.Builder();
        Locale locale = Locale.ROOT;
        int indexOf$default = StringsKt.indexOf$default(str2.toLowerCase(locale), StringsKt.trim(str).toString().toLowerCase(locale), 0, 6);
        int length = StringsKt.trim(str).toString().length() + indexOf$default;
        if (indexOf$default > 0) {
            builder.text.append(str2.substring(0, indexOf$default));
        }
        composerImpl.startReplaceGroup(30370546);
        if (indexOf$default >= 0) {
            String substring = str2.substring(indexOf$default, length);
            int pushStyle = builder.pushStyle(new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, MaterialTheme.getColorScheme(composerImpl).primaryContainer, (TextDecoration) null, (Shadow) null, 63487));
            try {
                builder.text.append(substring);
                builder.pop(pushStyle);
                if (length < str2.length()) {
                    builder.text.append(str2.substring(length));
                }
            } catch (Throwable th) {
                builder.pop(pushStyle);
                throw th;
            }
        } else {
            builder.text.append(str2);
        }
        composerImpl.end(false);
        AnnotatedString annotatedString = builder.toAnnotatedString();
        composerImpl.end(false);
        TextKt.m242TextIbK3jfQ(annotatedString, modifier2, MaterialTheme.getColorScheme(composerImpl).onSurface, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, MaterialTheme.getTypography(composerImpl).bodyMedium, composerImpl, (i >> 3) & 112, 0, 131064);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutDescriptionText$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutDescriptionText(str, shortcut, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ShortcutHelper(final kotlin.jvm.functions.Function1 r15, final kotlin.jvm.functions.Function0 r16, androidx.compose.ui.Modifier r17, final com.android.systemui.keyboard.shortcut.ui.model.ShortcutsUiState r18, kotlin.jvm.functions.Function2 r19, androidx.compose.runtime.Composer r20, final int r21, final int r22) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt.ShortcutHelper(kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, com.android.systemui.keyboard.shortcut.ui.model.ShortcutsUiState, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void ShortcutHelperSinglePane(final int i, final int i2, Composer composer, Modifier modifier, final ShortcutCategoryType shortcutCategoryType, final String str, final List list, final Function0 function0, final Function1 function1, final Function1 function12) {
        float f;
        Modifier weight;
        Modifier weight2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(157824801);
        int i3 = i2 & 64;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        float f2 = 16;
        Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(ScrollKt.verticalScroll$default(modifier2.then(SizeKt.FillWholeMaxSize), ScrollKt.rememberScrollState(composerImpl), false, 14), f2, 26, f2, 0.0f, 8);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m102paddingqDBjuR0$default);
        ComposeUiNode.Companion.getClass();
        Function0 function02 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, function2);
        Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
        Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        TitleBar(0, composerImpl);
        SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, 6));
        int i5 = i >> 3;
        ShortcutsSearchBar(function1, composerImpl, i5 & 14);
        SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, f2));
        if (list.isEmpty()) {
            composerImpl.startReplaceGroup(-13849722);
            weight2 = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i6 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, weight2);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function02);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function23);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, function24);
            m811NoSearchResultsTextkHDZbjc(f2, true, composerImpl, 54);
            composerImpl.end(true);
            composerImpl.end(false);
            f = f2;
        } else {
            composerImpl.startReplaceGroup(-13849559);
            f = f2;
            CategoriesPanelSinglePane(str, list, shortcutCategoryType, function12, composerImpl, (i & 14) | 64 | (i5 & 896) | (i5 & 7168));
            weight = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
            SpacerKt.Spacer(composerImpl, weight);
            composerImpl.end(false);
        }
        m810KeyboardSettingsixp7dh8(f, 32, function0, composerImpl, 54 | ((i >> 9) & 896));
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutHelperSinglePane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    String str2 = str;
                    Function1 function13 = function1;
                    List list2 = list;
                    ShortcutCategoryType shortcutCategoryType2 = shortcutCategoryType;
                    Function1 function14 = function12;
                    Function0 function03 = function0;
                    ShortcutHelperKt.ShortcutHelperSinglePane(RecomposeScopeImplKt.updateChangedFlags(i | 1), i2, (Composer) obj, modifier3, shortcutCategoryType2, str2, list2, function03, function13, function14);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0095, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r10.rememberedValue(), java.lang.Integer.valueOf(r4)) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ShortcutHelperTwoPane(final int r17, final int r18, androidx.compose.runtime.Composer r19, androidx.compose.ui.Modifier r20, final com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType r21, final java.lang.String r22, final java.util.List r23, final kotlin.jvm.functions.Function0 r24, final kotlin.jvm.functions.Function1 r25, final kotlin.jvm.functions.Function1 r26) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt.ShortcutHelperTwoPane(int, int, androidx.compose.runtime.Composer, androidx.compose.ui.Modifier, com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType, java.lang.String, java.util.List, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ShortcutIcon(final com.android.systemui.keyboard.shortcut.shared.model.ShortcutIcon r17, androidx.compose.ui.Modifier r18, java.lang.String r19, androidx.compose.runtime.Composer r20, final int r21, final int r22) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt.ShortcutIcon(com.android.systemui.keyboard.shortcut.shared.model.ShortcutIcon, androidx.compose.ui.Modifier, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutKeyCombinations$1, kotlin.jvm.internal.Lambda] */
    public static final void ShortcutKeyCombinations(final Modifier modifier, final Shortcut shortcut, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1686592245);
        if ((i2 & 1) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        FlowLayoutKt.FlowRow(modifier, Arrangement.End, Arrangement.m78spacedBy0680j_4(8), null, 0, 0, null, ComposableLambdaKt.rememberComposableLambda(-1516335951, new Function3() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutKeyCombinations$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                FlowRowScope flowRowScope = (FlowRowScope) obj;
                Composer composer2 = (Composer) obj2;
                int intValue = ((Number) obj3).intValue();
                if ((intValue & 14) == 0) {
                    intValue |= ((ComposerImpl) composer2).changed(flowRowScope) ? 4 : 2;
                }
                if ((intValue & 91) == 18) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                int i3 = 0;
                for (Object obj4 : Shortcut.this.commands) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    ShortcutCommand shortcutCommand = (ShortcutCommand) obj4;
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    composerImpl3.startReplaceGroup(-101558991);
                    if (i3 > 0) {
                        ShortcutHelperKt.m813access$ShortcutOrSeparatorziNgDLE(flowRowScope, 16, composerImpl3, (intValue & 14) | 48);
                    }
                    composerImpl3.end(false);
                    ShortcutHelperKt.access$ShortcutCommand(shortcutCommand, composerImpl3, 8);
                    i3 = i4;
                }
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, (i & 14) | 12583344, 120);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutKeyCombinations$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutKeyCombinations(Modifier.this, shortcut, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ShortcutKeyContainer(final Function3 function3, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1258689008);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(SizeKt.m108height3ABfNKs(Modifier.Companion.$$INSTANCE, 36), MaterialTheme.getColorScheme(composerImpl).surfaceContainer, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(12));
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m25backgroundbw27NRU);
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
            function3.invoke(BoxScopeInstance.INSTANCE, composerImpl, Integer.valueOf(((i2 << 3) & 112) | 6));
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutKeyContainer$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutKeyContainer(Function3.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ShortcutSubCategorySinglePane(final String str, final ShortcutSubCategory shortcutSubCategory, Composer composer, final int i) {
        Shortcut shortcut;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1515485004);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        SubCategoryTitle(shortcutSubCategory.label, composerImpl, 0);
        List list = shortcutSubCategory.shortcuts;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Shortcut shortcut2 = (Shortcut) list.get(i2);
            composerImpl.startReplaceGroup(1841894966);
            if (i2 > 0) {
                shortcut = shortcut2;
                DividerKt.m208HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composerImpl, 0, 7);
            } else {
                shortcut = shortcut2;
            }
            composerImpl.end(false);
            ShortcutView(PaddingKt.m100paddingVpY3zN4$default(Modifier.Companion.$$INSTANCE, 0.0f, 24, 1), str, shortcut, composerImpl, ((i << 3) & 112) | 518);
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutSubCategorySinglePane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutSubCategorySinglePane(str, shortcutSubCategory, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ShortcutView(final Modifier modifier, final String str, final Shortcut shortcut, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(27237308);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-1588488314);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = InteractionSourceKt.MutableInteractionSource();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
        composerImpl.end(false);
        MutableState collectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource, composerImpl, 6);
        Modifier focusable$default = FocusableKt.focusable$default(modifier, false, mutableInteractionSource, 1);
        boolean booleanValue = ((Boolean) collectIsFocusedAsState.getValue()).booleanValue();
        final long j = MaterialTheme.getColorScheme(composerImpl).secondary;
        final float f = 8;
        final float f2 = 16;
        if (booleanValue) {
            focusable$default = ZIndexModifierKt.zIndex(DrawModifierKt.drawWithContent(focusable$default, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$outlineFocusModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
                    LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
                    Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(0L, layoutNodeDrawScope.canvasDrawScope.mo432getSizeNHjbRc());
                    float f3 = f;
                    Rect inflate = Float.compare(f3, (float) 0) > 0 ? m324Recttz77jQw.inflate(layoutNodeDrawScope.mo51toPx0680j_4(f3)) : m324Recttz77jQw.inflate(-layoutNodeDrawScope.mo51toPx0680j_4(-f3));
                    layoutNodeDrawScope.drawContent();
                    Stroke stroke = new Stroke(layoutNodeDrawScope.mo51toPx0680j_4(3), 0.0f, 0, 0, 30);
                    long m322getTopLeftF1C5BW0 = inflate.m322getTopLeftF1C5BW0();
                    long m321getSizeNHjbRc = inflate.m321getSizeNHjbRc();
                    float mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(f2);
                    DrawScope.m429drawRoundRectuAw5IA$default(contentDrawScope, j, m322getTopLeftF1C5BW0, m321getSizeNHjbRc, (Float.floatToRawIntBits(mo51toPx0680j_4) & 4294967295L) | (Float.floatToRawIntBits(mo51toPx0680j_4) << 32), stroke, 0.0f, 224);
                    return Unit.INSTANCE;
                }
            }), 1.0f);
        }
        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, Alignment.Companion.Top, composerImpl, 0);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, focusable$default);
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
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier m117width3ABfNKs = SizeKt.m117width3ABfNKs(companion, 128);
        BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
        rowScopeInstance.getClass();
        Modifier then = m117width3ABfNKs.then(new VerticalAlignElement());
        RowMeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.m78spacedBy0680j_4(f2), vertical, composerImpl, 54);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, then);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, rowMeasurePolicy2, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        composerImpl.startReplaceGroup(1440352996);
        ShortcutIcon shortcutIcon = shortcut.icon;
        if (shortcutIcon != null) {
            ShortcutIcon(shortcutIcon, SizeKt.m113size3ABfNKs(companion, 24), null, composerImpl, 48, 4);
        }
        composerImpl.end(false);
        ShortcutDescriptionText(str, shortcut, null, composerImpl, ((i >> 3) & 14) | 64, 4);
        composerImpl.end(true);
        SpacerKt.Spacer(composerImpl, SizeKt.m117width3ABfNKs(companion, f2));
        ShortcutKeyCombinations(rowScopeInstance.weight(companion, 1.0f, true), shortcut, composerImpl, 64, 0);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutView$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutView(Modifier.this, str, shortcut, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ShortcutsSearchBar(final Function1 function1, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-2050278086);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changedInstance(function1) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl2.startReplaceGroup(-1958006790);
            Object rememberedValue = composerImpl2.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default("");
                composerImpl2.updateRememberedValue(rememberedValue);
            }
            final MutableState mutableState = (MutableState) rememberedValue;
            Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl2, false, -1958006733);
            if (m == composer$Companion$Empty$1) {
                m = new FocusRequester();
                composerImpl2.updateRememberedValue(m);
            }
            FocusRequester focusRequester = (FocusRequester) m;
            composerImpl2.end(false);
            final FocusManager focusManager = (FocusManager) composerImpl2.consume(CompositionLocalsKt.LocalFocusManager);
            Unit unit = Unit.INSTANCE;
            composerImpl2.startReplaceGroup(-1958006629);
            Object rememberedValue2 = composerImpl2.rememberedValue();
            if (rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new ShortcutHelperKt$ShortcutsSearchBar$1$1(focusRequester, null);
                composerImpl2.updateRememberedValue(rememberedValue2);
            }
            composerImpl2.end(false);
            EffectsKt.LaunchedEffect(composerImpl2, unit, (Function2) rememberedValue2);
            Modifier onKeyEvent = KeyInputModifierKt.onKeyEvent(FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f), focusRequester), new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutsSearchBar$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    if (!Key.m446equalsimpl0(Key_androidKt.Key(((KeyEvent) obj).nativeKeyEvent.getKeyCode()), Key.DirectionDown)) {
                        return Boolean.FALSE;
                    }
                    ((FocusOwnerImpl) FocusManager.this).m291moveFocus3ESFkO8(6);
                    return Boolean.TRUE;
                }
            });
            SearchBarDefaults searchBarDefaults = SearchBarDefaults.INSTANCE;
            SearchBarColors m222colorsKlgxPg = SearchBarDefaults.m222colorsKlgxPg(MaterialTheme.getColorScheme(composerImpl2).surfaceBright, composerImpl2, 0, 6);
            String str = (String) mutableState.getValue();
            float f = 0;
            WindowInsets m120WindowInsetsa9UjIt4 = WindowInsetsKt.m120WindowInsetsa9UjIt4(f, f, f, f);
            composerImpl2.startReplaceGroup(-1958006012);
            boolean z = (i2 & 14) == 4;
            Object rememberedValue3 = composerImpl2.rememberedValue();
            if (z || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutsSearchBar$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        String str2 = (String) obj;
                        mutableState.setValue(str2);
                        Function1.this.invoke(str2);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl2.updateRememberedValue(rememberedValue3);
            }
            composerImpl2.end(false);
            composerImpl = composerImpl2;
            SearchBar_androidKt.m224SearchBarWuY5d9Q(str, (Function1) rememberedValue3, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutsSearchBar$4
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            }, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutsSearchBar$5
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    ((Boolean) obj).booleanValue();
                    return Unit.INSTANCE;
                }
            }, onKeyEvent, false, ComposableSingletons$ShortcutHelperKt.f31lambda2, ComposableSingletons$ShortcutHelperKt.f32lambda3, null, null, m222colorsKlgxPg, 0.0f, 0.0f, m120WindowInsetsa9UjIt4, null, ComposableSingletons$ShortcutHelperKt.f33lambda4, composerImpl, 113274240, 1572864, 46656);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutsSearchBar$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.ShortcutsSearchBar(function1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void StartSidePanel(final Function1 function1, final Modifier modifier, final List list, final Function0 function0, final ShortcutCategoryType shortcutCategoryType, final Function1 function12, Composer composer, final int i) {
        Modifier weight;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-43673571);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i2 = i >> 3;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier);
        ComposeUiNode.Companion.getClass();
        Function0 function02 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        ShortcutsSearchBar(function1, composerImpl, i & 14);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        SpacerKt.Spacer(composerImpl, SizeKt.m110heightInVpY3zN4$default(companion, 8, 0.0f, 2));
        int i4 = i >> 9;
        CategoriesPanelTwoPane(list, shortcutCategoryType, function12, composerImpl, 8 | (i4 & 112) | (i4 & 896));
        weight = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
        SpacerKt.Spacer(composerImpl, weight);
        float f = 24;
        m810KeyboardSettingsixp7dh8(f, f, function0, composerImpl, (i2 & 896) | 54);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$StartSidePanel$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.StartSidePanel(Function1.this, modifier, list, function0, shortcutCategoryType, function12, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void SubCategoryTitle(final String str, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(2013630406);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl = composerImpl2;
            TextKt.m241Text4IGK_g(str, null, MaterialTheme.getColorScheme(composerImpl2).primary, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).titleSmall, composerImpl, i2 & 14, 0, 65530);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$SubCategoryTitle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.SubCategoryTitle(str, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void TitleBar(final int i, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-2138769512);
        if (i == 0 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            float f = TopAppBarDefaults.TopAppBarExpandedHeight;
            long j = Color.Transparent;
            long j2 = Color.Unspecified;
            TopAppBarColors defaultCenterAlignedTopAppBarColors$material3_release = TopAppBarDefaults.getDefaultCenterAlignedTopAppBarColors$material3_release(MaterialTheme.getColorScheme(composerImpl));
            if (j == 16) {
                j = defaultCenterAlignedTopAppBarColors$material3_release.containerColor;
            }
            long j3 = j;
            long j4 = j2 != 16 ? j2 : defaultCenterAlignedTopAppBarColors$material3_release.scrolledContainerColor;
            long j5 = j2 != 16 ? j2 : defaultCenterAlignedTopAppBarColors$material3_release.navigationIconContentColor;
            long j6 = j2 != 16 ? j2 : defaultCenterAlignedTopAppBarColors$material3_release.titleContentColor;
            if (j2 == 16) {
                j2 = defaultCenterAlignedTopAppBarColors$material3_release.actionIconContentColor;
            }
            float f2 = 0;
            AppBarKt.m193CenterAlignedTopAppBarGHTll3U(ComposableSingletons$ShortcutHelperKt.f30lambda1, null, null, null, 64, WindowInsetsKt.m120WindowInsetsa9UjIt4(f2, f2, f2, f2), new TopAppBarColors(j3, j4, j5, j6, j2), composerImpl, 24582, 142);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$TitleBar$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.TitleBar(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$RotatingExpandCollapseIcon(final boolean z, Composer composer, final int i) {
        int i2;
        String stringResource;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1532057329);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final State animateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 180.0f : 0.0f, null, "Expand icon rotation animation", null, composerImpl, 3072, 22);
            Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(Modifier.Companion.$$INSTANCE, MaterialTheme.getColorScheme(composerImpl).surfaceContainerHigh, RoundedCornerShapeKt.CircleShape);
            composerImpl.startReplaceGroup(-1399511870);
            boolean changed = composerImpl.changed(animateFloatAsState);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$RotatingExpandCollapseIcon$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj)).setRotationZ(((Number) State.this.getValue()).floatValue());
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(m25backgroundbw27NRU, (Function1) rememberedValue);
            ImageVector imageVector = ExpandMoreKt._expandMore;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.ExpandMore", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(16.59f, 8.59f);
                pathBuilder.lineTo(12.0f, 13.17f);
                pathBuilder.lineTo(7.41f, 8.59f);
                pathBuilder.lineTo(6.0f, 10.0f);
                pathBuilder.lineToRelative(6.0f, 6.0f);
                pathBuilder.lineToRelative(6.0f, -6.0f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                ExpandMoreKt._expandMore = imageVector;
            }
            if (z) {
                composerImpl.startReplaceGroup(-1399511704);
                stringResource = StringResources_androidKt.stringResource(R.string.shortcut_helper_content_description_collapse_icon, composerImpl);
                composerImpl.end(false);
            } else {
                composerImpl.startReplaceGroup(-1399511592);
                stringResource = StringResources_androidKt.stringResource(R.string.shortcut_helper_content_description_expand_icon, composerImpl);
                composerImpl.end(false);
            }
            IconKt.m214Iconww6aTOc(imageVector, stringResource, graphicsLayer, MaterialTheme.getColorScheme(composerImpl).onSurface, composerImpl, 0, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$RotatingExpandCollapseIcon$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.access$RotatingExpandCollapseIcon(z, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$ShortcutCategoryDetailsSinglePane(final String str, final ShortcutCategory shortcutCategory, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1792444000);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(Modifier.Companion.$$INSTANCE, 16, 0.0f, 2);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m100paddingVpY3zN4$default);
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
        composerImpl.startReplaceGroup(-56114588);
        List list = shortcutCategory.subCategories;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            ShortcutSubCategorySinglePane(str, (ShortcutSubCategory) list.get(i3), composerImpl, (i & 14) | 64);
        }
        composerImpl.end(false);
        composerImpl.end(true);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutCategoryDetailsSinglePane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.access$ShortcutCategoryDetailsSinglePane(str, shortcutCategory, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutCommand$1$1$1, kotlin.jvm.internal.Lambda] */
    public static final void access$ShortcutCommand(final ShortcutCommand shortcutCommand, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(833482787);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, Alignment.Companion.Top, composerImpl, 0);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, companion);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        composerImpl.startReplaceGroup(1209378103);
        int i3 = 0;
        for (Object obj : shortcutCommand.keys) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            final ShortcutKey shortcutKey = (ShortcutKey) obj;
            composerImpl.startReplaceGroup(-111389562);
            if (i3 > 0) {
                SpacerKt.Spacer(composerImpl, SizeKt.m117width3ABfNKs(companion, 4));
            }
            composerImpl.end(false);
            ShortcutKeyContainer(ComposableLambdaKt.rememberComposableLambda(-867364547, new Function3() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutCommand$1$1$1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    BoxScope boxScope = (BoxScope) obj2;
                    Composer composer2 = (Composer) obj3;
                    int intValue = ((Number) obj4).intValue();
                    if ((intValue & 14) == 0) {
                        intValue |= ((ComposerImpl) composer2).changed(boxScope) ? 4 : 2;
                    }
                    if ((intValue & 91) == 18) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    ShortcutKey shortcutKey2 = ShortcutKey.this;
                    if (shortcutKey2 instanceof ShortcutKey.Text) {
                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                        composerImpl3.startReplaceGroup(270268157);
                        ShortcutHelperKt.access$ShortcutTextKey(boxScope, (ShortcutKey.Text) ShortcutKey.this, composerImpl3, intValue & 14);
                        composerImpl3.end(false);
                    } else if (shortcutKey2 instanceof ShortcutKey.Icon) {
                        ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                        composerImpl4.startReplaceGroup(270268252);
                        ShortcutHelperKt.access$ShortcutIconKey(boxScope, (ShortcutKey.Icon) ShortcutKey.this, composerImpl4, intValue & 14);
                        composerImpl4.end(false);
                    } else {
                        ComposerImpl composerImpl5 = (ComposerImpl) composer2;
                        composerImpl5.startReplaceGroup(270268290);
                        composerImpl5.end(false);
                    }
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 6);
            i3 = i4;
        }
        composerImpl.end(false);
        composerImpl.end(true);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutCommand$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    ShortcutHelperKt.access$ShortcutCommand(ShortcutCommand.this, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$ShortcutIconKey(final BoxScope boxScope, final ShortcutKey.Icon icon, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(450478271);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(icon) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            IconKt.m213Iconww6aTOc(PainterResources_androidKt.painterResource(icon.drawableResId, 0, composerImpl), (String) null, PaddingKt.m98padding3ABfNKs(boxScope.align(Modifier.Companion.$$INSTANCE, Alignment.Companion.Center), 6), 0L, composerImpl, 56, 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutIconKey$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.access$ShortcutIconKey(BoxScope.this, icon, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: access$ShortcutOrSeparator-ziNgDLE, reason: not valid java name */
    public static final void m813access$ShortcutOrSeparatorziNgDLE(final FlowRowScope flowRowScope, final float f, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(562023815);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(flowRowScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(f) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            SpacerKt.Spacer(composerImpl, SizeKt.m117width3ABfNKs(companion, f));
            String stringResource = StringResources_androidKt.stringResource(R.string.shortcut_helper_key_combinations_or_separator, composerImpl);
            ((FlowRowScopeInstance) flowRowScope).getClass();
            TextKt.m241Text4IGK_g(stringResource, new VerticalAlignElement(), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl).titleSmall, composerImpl, 0, 0, 65532);
            composerImpl = composerImpl;
            SpacerKt.Spacer(composerImpl, SizeKt.m117width3ABfNKs(companion, f));
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutOrSeparator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.m813access$ShortcutOrSeparatorziNgDLE(FlowRowScope.this, f, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$ShortcutTextKey(final BoxScope boxScope, final ShortcutKey.Text text, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(2118702167);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl2.changed(text) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl = composerImpl2;
            TextKt.m241Text4IGK_g(text.value, PaddingKt.m100paddingVpY3zN4$default(boxScope.align(Modifier.Companion.$$INSTANCE, Alignment.Companion.Center), 12, 0.0f, 2), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).titleSmall, composerImpl, 0, 0, 65532);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$ShortcutTextKey$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.access$ShortcutTextKey(BoxScope.this, text, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$SubCategoryContainerDualPane$1, kotlin.jvm.internal.Lambda] */
    public static final void access$SubCategoryContainerDualPane(final String str, final ShortcutSubCategory shortcutSubCategory, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2047957927);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        SurfaceKt.m232SurfaceT9BRK9s(SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f), RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(28), MaterialTheme.getColorScheme(composerImpl).surfaceBright, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1729744852, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$SubCategoryContainerDualPane$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Shortcut shortcut;
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(companion, 24);
                ShortcutSubCategory shortcutSubCategory2 = ShortcutSubCategory.this;
                String str2 = str;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composer2, 0);
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
                ShortcutHelperKt.SubCategoryTitle(shortcutSubCategory2.label, composer2, 0);
                SpacerKt.Spacer(composer2, SizeKt.m108height3ABfNKs(companion, 8));
                composerImpl3.startReplaceGroup(-2116578293);
                List list = shortcutSubCategory2.shortcuts;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Shortcut shortcut2 = (Shortcut) list.get(i2);
                    composerImpl3.startReplaceGroup(1388749284);
                    if (i2 > 0) {
                        shortcut = shortcut2;
                        DividerKt.m208HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                    } else {
                        shortcut = shortcut2;
                    }
                    composerImpl3.end(false);
                    ShortcutHelperKt.ShortcutView(PaddingKt.m100paddingVpY3zN4$default(companion, 0.0f, 16, 1), str2, shortcut, composer2, 518);
                }
                composerImpl3.end(false);
                composerImpl3.end(true);
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 12582918, 120);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$SubCategoryContainerDualPane$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperKt.access$SubCategoryContainerDualPane(str, shortcutSubCategory, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final IconSource getIcon(ShortcutCategory shortcutCategory, Composer composer) {
        IconSource iconSource;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1371352918);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ShortcutCategoryType shortcutCategoryType = shortcutCategory.type;
        if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.System.INSTANCE)) {
            ImageVector imageVector = TvKt._tv;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.Tv", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(21.0f, 3.0f);
                pathBuilder.lineTo(3.0f, 3.0f);
                pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
                pathBuilder.verticalLineToRelative(12.0f);
                pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
                pathBuilder.horizontalLineToRelative(5.0f);
                pathBuilder.verticalLineToRelative(2.0f);
                pathBuilder.horizontalLineToRelative(8.0f);
                pathBuilder.verticalLineToRelative(-2.0f);
                pathBuilder.horizontalLineToRelative(5.0f);
                pathBuilder.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
                pathBuilder.lineTo(23.0f, 5.0f);
                pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
                pathBuilder.close();
                pathBuilder.moveTo(21.0f, 17.0f);
                pathBuilder.lineTo(3.0f, 17.0f);
                pathBuilder.lineTo(3.0f, 5.0f);
                pathBuilder.horizontalLineToRelative(18.0f);
                pathBuilder.verticalLineToRelative(12.0f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                TvKt._tv = imageVector;
            }
            iconSource = new IconSource(imageVector, null, 2);
        } else if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.MultiTasking.INSTANCE)) {
            ImageVector imageVector2 = VerticalSplitKt._verticalSplit;
            if (imageVector2 == null) {
                ImageVector.Builder builder2 = new ImageVector.Builder("Filled.VerticalSplit", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList2 = VectorKt.EmptyPath;
                SolidColor solidColor2 = new SolidColor(Color.Black);
                PathBuilder pathBuilder2 = new PathBuilder();
                pathBuilder2.moveTo(3.0f, 15.0f);
                pathBuilder2.horizontalLineToRelative(8.0f);
                pathBuilder2.verticalLineToRelative(-2.0f);
                pathBuilder2.lineTo(3.0f, 13.0f);
                pathBuilder2.verticalLineToRelative(2.0f);
                pathBuilder2.close();
                pathBuilder2.moveTo(3.0f, 19.0f);
                pathBuilder2.horizontalLineToRelative(8.0f);
                pathBuilder2.verticalLineToRelative(-2.0f);
                pathBuilder2.lineTo(3.0f, 17.0f);
                pathBuilder2.verticalLineToRelative(2.0f);
                pathBuilder2.close();
                pathBuilder2.moveTo(3.0f, 11.0f);
                pathBuilder2.horizontalLineToRelative(8.0f);
                pathBuilder2.lineTo(11.0f, 9.0f);
                pathBuilder2.lineTo(3.0f, 9.0f);
                pathBuilder2.verticalLineToRelative(2.0f);
                pathBuilder2.close();
                pathBuilder2.moveTo(3.0f, 5.0f);
                pathBuilder2.verticalLineToRelative(2.0f);
                pathBuilder2.horizontalLineToRelative(8.0f);
                pathBuilder2.lineTo(11.0f, 5.0f);
                pathBuilder2.lineTo(3.0f, 5.0f);
                pathBuilder2.close();
                pathBuilder2.moveTo(13.0f, 5.0f);
                pathBuilder2.horizontalLineToRelative(8.0f);
                pathBuilder2.verticalLineToRelative(14.0f);
                pathBuilder2.horizontalLineToRelative(-8.0f);
                pathBuilder2.lineTo(13.0f, 5.0f);
                pathBuilder2.close();
                builder2.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor2, null, "", pathBuilder2._nodes);
                imageVector2 = builder2.build();
                VerticalSplitKt._verticalSplit = imageVector2;
            }
            iconSource = new IconSource(imageVector2, null, 2);
        } else if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.InputMethodEditor.INSTANCE)) {
            ImageVector imageVector3 = KeyboardKt._keyboard;
            if (imageVector3 == null) {
                ImageVector.Builder builder3 = new ImageVector.Builder("Filled.Keyboard", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList3 = VectorKt.EmptyPath;
                SolidColor solidColor3 = new SolidColor(Color.Black);
                PathBuilder pathBuilder3 = new PathBuilder();
                pathBuilder3.moveTo(20.0f, 5.0f);
                pathBuilder3.lineTo(4.0f, 5.0f);
                pathBuilder3.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
                pathBuilder3.lineTo(2.0f, 17.0f);
                pathBuilder3.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
                pathBuilder3.horizontalLineToRelative(16.0f);
                pathBuilder3.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
                pathBuilder3.lineTo(22.0f, 7.0f);
                pathBuilder3.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(11.0f, 8.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.lineTo(11.0f, 8.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(11.0f, 11.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(8.0f, 8.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.lineTo(8.0f, 10.0f);
                pathBuilder3.lineTo(8.0f, 8.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(8.0f, 11.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.lineTo(8.0f, 13.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(7.0f, 13.0f);
                pathBuilder3.lineTo(5.0f, 13.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(7.0f, 10.0f);
                pathBuilder3.lineTo(5.0f, 10.0f);
                pathBuilder3.lineTo(5.0f, 8.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(16.0f, 17.0f);
                pathBuilder3.lineTo(8.0f, 17.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.horizontalLineToRelative(8.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(16.0f, 13.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(16.0f, 10.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.lineTo(14.0f, 8.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(19.0f, 13.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.verticalLineToRelative(-2.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                pathBuilder3.moveTo(19.0f, 10.0f);
                pathBuilder3.horizontalLineToRelative(-2.0f);
                pathBuilder3.lineTo(17.0f, 8.0f);
                pathBuilder3.horizontalLineToRelative(2.0f);
                pathBuilder3.verticalLineToRelative(2.0f);
                pathBuilder3.close();
                builder3.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor3, null, "", pathBuilder3._nodes);
                imageVector3 = builder3.build();
                KeyboardKt._keyboard = imageVector3;
            }
            iconSource = new IconSource(imageVector3, null, 2);
        } else if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.AppCategories.INSTANCE)) {
            ImageVector imageVector4 = AppsKt._apps;
            if (imageVector4 == null) {
                ImageVector.Builder builder4 = new ImageVector.Builder("Filled.Apps", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList4 = VectorKt.EmptyPath;
                SolidColor solidColor4 = new SolidColor(Color.Black);
                PathBuilder pathBuilder4 = new PathBuilder();
                pathBuilder4.moveTo(4.0f, 8.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.lineTo(8.0f, 4.0f);
                pathBuilder4.lineTo(4.0f, 4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(10.0f, 20.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(4.0f, 20.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.lineTo(4.0f, 16.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(4.0f, 14.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.lineTo(4.0f, 10.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(10.0f, 14.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(16.0f, 4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.lineTo(20.0f, 4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(10.0f, 8.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.lineTo(14.0f, 4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(16.0f, 14.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                pathBuilder4.moveTo(16.0f, 20.0f);
                pathBuilder4.horizontalLineToRelative(4.0f);
                pathBuilder4.verticalLineToRelative(-4.0f);
                pathBuilder4.horizontalLineToRelative(-4.0f);
                pathBuilder4.verticalLineToRelative(4.0f);
                pathBuilder4.close();
                builder4.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor4, null, "", pathBuilder4._nodes);
                imageVector4 = builder4.build();
                AppsKt._apps = imageVector4;
            }
            iconSource = new IconSource(imageVector4, null, 2);
        } else {
            if (!(shortcutCategoryType instanceof ShortcutCategoryType.CurrentApp)) {
                throw new NoWhenBranchMatchedException();
            }
            iconSource = new IconSource(null, DrawablePainterKt.rememberDrawablePainter(((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)).getPackageManager().getApplicationIcon(((ShortcutCategoryType.CurrentApp) shortcutCategory.type).packageName), composerImpl), 1);
        }
        composerImpl.end(false);
        return iconSource;
    }

    public static final String label(ShortcutCategory shortcutCategory, Context context) {
        ShortcutCategoryType shortcutCategoryType = shortcutCategory.type;
        if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.System.INSTANCE)) {
            return context.getString(R.string.shortcut_helper_category_system);
        }
        if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.MultiTasking.INSTANCE)) {
            return context.getString(R.string.shortcut_helper_category_multitasking);
        }
        if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.InputMethodEditor.INSTANCE)) {
            return context.getString(R.string.shortcut_helper_category_input);
        }
        if (Intrinsics.areEqual(shortcutCategoryType, ShortcutCategoryType.AppCategories.INSTANCE)) {
            return context.getString(R.string.shortcut_helper_category_app_shortcuts);
        }
        if (!(shortcutCategoryType instanceof ShortcutCategoryType.CurrentApp)) {
            throw new NoWhenBranchMatchedException();
        }
        ShortcutCategoryType.CurrentApp currentApp = (ShortcutCategoryType.CurrentApp) shortcutCategory.type;
        PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(context.getUserId(), context);
        try {
            return packageManagerForUser.getApplicationLabel(packageManagerForUser.getApplicationInfoAsUser(currentApp.packageName, 0, context.getUserId())).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            Log.wtf("ShortcutHelperUI", "Couldn't find app info by package name " + currentApp.packageName);
            String string = context.getString(R.string.shortcut_helper_category_current_app_shortcuts);
            Intrinsics.checkNotNull(string);
            return string;
        }
    }
}
