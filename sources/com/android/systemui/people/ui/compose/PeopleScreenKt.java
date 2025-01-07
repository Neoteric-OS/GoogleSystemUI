package com.android.systemui.people.ui.compose;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Top$1;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.ContentScale$Companion$Fit$1;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextAlign;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.people.ui.viewmodel.PeopleTileViewModel;
import com.android.systemui.people.ui.viewmodel.PeopleViewModel;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PeopleScreenKt {
    public static final float PeopleSpacePadding = 24;

    public static final void ConversationList(final int i, final List list, final Function1 function1, Composer composer, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1586868510);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        String stringResource = StringResources_androidKt.stringResource(i, composerImpl);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        TextKt.m241Text4IGK_g(stringResource, PaddingKt.m102paddingqDBjuR0$default(companion, 16, 0.0f, 0.0f, 0.0f, 14), ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).deprecated.colorAccentPrimaryVariant, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl).labelLarge, composerImpl, 48, 0, 65528);
        SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, 10));
        int i3 = 0;
        for (Object obj : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            PeopleTileViewModel peopleTileViewModel = (PeopleTileViewModel) obj;
            composerImpl.startReplaceGroup(-588347591);
            if (i3 > 0) {
                DividerKt.m207Divider9IZ8Weo(null, 2, ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).deprecated.colorBackground, composerImpl, 48, 1);
            }
            composerImpl.end(false);
            composerImpl.startMovableGroup(-749583209, peopleTileViewModel.key.toString());
            Tile(peopleTileViewModel, function1, i3 == 0, i3 == CollectionsKt__CollectionsKt.getLastIndex(list), composerImpl, ((i2 >> 3) & 112) | 8);
            composerImpl.end(false);
            i3 = i4;
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$ConversationList$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    PeopleScreenKt.ConversationList(i, list, function1, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r8v0, types: [com.android.systemui.people.ui.compose.PeopleScreenKt$PeopleScreen$2, kotlin.jvm.internal.Lambda] */
    public static final void PeopleScreen(final PeopleViewModel peopleViewModel, final Function1 function1, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(825885692);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(peopleViewModel.priorityTiles, composerImpl);
        final MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(peopleViewModel.recentTiles, composerImpl);
        EffectsKt.LaunchedEffect(composerImpl, peopleViewModel.result, new PeopleScreenKt$PeopleScreen$1(peopleViewModel, function1, null));
        AndroidColorScheme.DeprecatedValues deprecatedValues = ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).deprecated;
        long j = deprecatedValues.colorBackground;
        SurfaceKt.m232SurfaceT9BRK9s(SizeKt.FillWholeMaxSize, null, j, deprecatedValues.textColorPrimary, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(899692247, new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$PeopleScreen$2
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
                if (((List) collectAsStateWithLifecycle.getValue()).isEmpty() && ((List) collectAsStateWithLifecycle2.getValue()).isEmpty()) {
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    composerImpl3.startReplaceGroup(-541283938);
                    PeopleScreenEmptyKt.PeopleScreenEmpty(PeopleViewModel.this.onUserJourneyCancelled, composerImpl3, 0);
                    composerImpl3.end(false);
                } else {
                    ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                    composerImpl4.startReplaceGroup(-541284050);
                    PeopleScreenKt.access$PeopleScreenWithConversations((List) collectAsStateWithLifecycle.getValue(), (List) collectAsStateWithLifecycle2.getValue(), PeopleViewModel.this.onTileClicked, composerImpl4, 72);
                    composerImpl4.end(false);
                }
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 12582918, 114);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$PeopleScreen$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PeopleScreenKt.PeopleScreen(PeopleViewModel.this, function1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.people.ui.compose.PeopleScreenKt$Tile$1, kotlin.jvm.internal.Lambda] */
    public static final void Tile(final PeopleTileViewModel peopleTileViewModel, final Function1 function1, final boolean z, final boolean z2, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1505732001);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        AndroidColorScheme.DeprecatedValues deprecatedValues = ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).deprecated;
        float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.people_space_widget_radius, composerImpl);
        float f = z ? dimensionResource : 0;
        if (!z2) {
            dimensionResource = 0;
        }
        SurfaceKt.m232SurfaceT9BRK9s(null, RoundedCornerShapeKt.m149RoundedCornerShapea9UjIt4(f, f, dimensionResource, dimensionResource), deprecatedValues.colorSurface, deprecatedValues.textColorPrimary, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-486400838, new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$Tile$1
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
                Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
                final Function1 function12 = Function1.this;
                final PeopleTileViewModel peopleTileViewModel2 = peopleTileViewModel;
                Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(ClickableKt.m32clickableXHw0xAI$default(fillMaxWidth, false, null, new Function0() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$Tile$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Function1.this.invoke(peopleTileViewModel2);
                        return Unit.INSTANCE;
                    }
                }, 7), 12);
                BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                PeopleTileViewModel peopleTileViewModel3 = peopleTileViewModel;
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, vertical, composer2, 48);
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
                Updater.m259setimpl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function2);
                }
                Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                AndroidImageBitmap androidImageBitmap = new AndroidImageBitmap(peopleTileViewModel3.icon);
                Modifier m113size3ABfNKs = SizeKt.m113size3ABfNKs(companion, PrimitiveResources_androidKt.dimensionResource(R.dimen.avatar_size_for_medium, composer2));
                BiasAlignment biasAlignment = Alignment.Companion.Center;
                ContentScale$Companion$Fit$1 contentScale$Companion$Fit$1 = ContentScale.Companion.Fit;
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                boolean changed = composerImpl4.changed(androidImageBitmap);
                Object rememberedValue = composerImpl4.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.Empty) {
                    BitmapPainter bitmapPainter = new BitmapPainter(androidImageBitmap, (androidImageBitmap.bitmap.getHeight() & 4294967295L) | (androidImageBitmap.bitmap.getWidth() << 32));
                    bitmapPainter.filterQuality = 1;
                    composerImpl4.updateRememberedValue(bitmapPainter);
                    rememberedValue = bitmapPainter;
                }
                ImageKt.Image((BitmapPainter) rememberedValue, null, m113size3ABfNKs, biasAlignment, contentScale$Companion$Fit$1, 1.0f, null, composerImpl4, 48, 0);
                String str = peopleTileViewModel3.username;
                if (str == null) {
                    str = "";
                }
                TextKt.m241Text4IGK_g(str, PaddingKt.m100paddingVpY3zN4$default(companion, 16, 0.0f, 2), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer2).titleLarge, composer2, 48, 0, 65532);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 12582912, 113);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$Tile$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PeopleScreenKt.Tile(PeopleTileViewModel.this, function1, z, z2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$PeopleScreenWithConversations(final List list, final List list2, final Function1 function1, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1382696723);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier sysuiResTag = SysuiTestTagKt.sysuiResTag(companion, "top_level_with_conversations");
        Arrangement$Top$1 arrangement$Top$1 = Arrangement.Top;
        BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement$Top$1, horizontal, composerImpl, 0);
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
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
        float f = PeopleSpacePadding;
        Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(fillMaxWidth, f);
        ColumnMeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement$Top$1, Alignment.Companion.CenterHorizontally, composerImpl, 48);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, m98padding3ABfNKs);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy2, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.select_conversation_title, composerImpl), null, 0L, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl).headlineSmall, composerImpl, 0, 0, 65022);
        float f2 = 24;
        SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, f2));
        TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.select_conversation_text, composerImpl), PaddingKt.m100paddingVpY3zN4$default(companion, f2, 0.0f, 2), 0L, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl).bodyLarge, composerImpl, 48, 0, 65020);
        composerImpl.end(true);
        float f3 = 8;
        Modifier m101paddingqDBjuR0 = PaddingKt.m101paddingqDBjuR0(ScrollKt.verticalScroll$default(SysuiTestTagKt.sysuiResTag(SizeKt.fillMaxWidth(companion, 1.0f), "scroll_view"), ScrollKt.rememberScrollState(composerImpl), false, 14), f3, 16, f3, f);
        ColumnMeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(arrangement$Top$1, horizontal, composerImpl, 0);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, m101paddingqDBjuR0);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy3, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier3, function24);
        boolean isEmpty = list.isEmpty();
        composerImpl.startReplaceGroup(834741047);
        if (!isEmpty) {
            ConversationList(R.string.priority_conversations, list, function1, composerImpl, (i & 896) | 64);
        }
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-497898063);
        if (!list2.isEmpty()) {
            composerImpl.startReplaceGroup(834741250);
            if (!isEmpty) {
                SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, 35));
            }
            composerImpl.end(false);
            ConversationList(R.string.recent_conversations, list2, function1, composerImpl, (i & 896) | 64);
        }
        composerImpl.end(false);
        composerImpl.end(true);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$PeopleScreenWithConversations$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PeopleScreenKt.access$PeopleScreenWithConversations(list, list2, function1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
