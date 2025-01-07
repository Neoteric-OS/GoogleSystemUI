package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.ElementKey;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.grid.ui.compose.SpannedGridsKt;
import com.android.systemui.qs.composefragment.ui.GridAnchorKt;
import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt;
import com.android.systemui.qs.panels.ui.viewmodel.QuickQuickSettingsViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.TileViewModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.ui.ElementKeys;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QuickQuickSettingsKt {
    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt$QuickQuickSettings$2$2, kotlin.jvm.internal.Lambda] */
    public static final void QuickQuickSettings(final ContentScope contentScope, final QuickQuickSettingsViewModel quickQuickSettingsViewModel, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(519072397);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(quickQuickSettingsViewModel.tileViewModels, EmptyList.INSTANCE, composerImpl, 56);
        List list = (List) collectAsStateWithLifecycle.getValue();
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            arrayList.add((TileViewModel) ((SizedTile) list.get(i4)).getTile());
        }
        EffectsKt.DisposableEffect(arrayList, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt$QuickQuickSettings$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final Object obj2 = new Object();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((TileViewModel) it.next()).tile.setListening(obj2, true);
                }
                final List list2 = arrayList;
                return new DisposableEffectResult() { // from class: com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt$QuickQuickSettings$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        for (TileViewModel tileViewModel : list2) {
                            tileViewModel.tile.setListening(obj2, false);
                        }
                    }
                };
            }
        }, composerImpl);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(quickQuickSettingsViewModel.columns, composerImpl);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier2);
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
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        GridAnchorKt.GridAnchor(contentScope, null, composerImpl, i & 14, 1);
        int intValue = ((Number) collectAsStateWithLifecycle2.getValue()).intValue();
        float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_tile_margin_horizontal, composerImpl);
        float dimensionResource2 = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_tile_margin_vertical, composerImpl);
        List list2 = (List) collectAsStateWithLifecycle.getValue();
        ArrayList arrayList2 = new ArrayList(list2.size());
        int size2 = list2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            arrayList2.add(Integer.valueOf(((SizedTile) list2.get(i6)).getWidth()));
        }
        SpannedGridsKt.m807VerticalSpannedGridZUYZQmM(intValue, dimensionResource, dimensionResource2, arrayList2, SysuiTestTagKt.sysuiResTag(companion, "qqs_tile_layout"), ComposableLambdaKt.rememberComposableLambda(1660472596, new Function4() { // from class: com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt$QuickQuickSettings$2$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int intValue2 = ((Number) obj2).intValue();
                Composer composer2 = (Composer) obj3;
                int intValue3 = ((Number) obj4).intValue();
                if ((intValue3 & 112) == 0) {
                    intValue3 |= ((ComposerImpl) composer2).changed(intValue2) ? 32 : 16;
                }
                if ((intValue3 & 721) == 144) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                SizedTile sizedTile = (SizedTile) ((List) collectAsStateWithLifecycle.getValue()).get(intValue2);
                TileViewModel tileViewModel = (TileViewModel) sizedTile.getTile();
                boolean z = sizedTile.getWidth() == 1;
                ContentScope contentScope2 = ContentScope.this;
                Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                ElementKey elementKey = ElementKeys.QuickSettingsContent;
                TileSpec tileSpec = ((TileViewModel) sizedTile.getTile()).spec;
                TileKt.Tile(tileViewModel, z, contentScope2.element(companion2, new ElementKeys.TileElementKey(tileSpec.getSpec(), tileSpec.getSpec(), null, 12)), composer2, 0);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 200704, 0);
        composerImpl.end(true);
        OpaqueKey opaqueKey3 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.QuickQuickSettingsKt$QuickQuickSettings$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QuickQuickSettingsKt.QuickQuickSettings(ContentScope.this, quickQuickSettingsViewModel, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
