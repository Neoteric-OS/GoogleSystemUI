package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.ElementKey;
import com.android.systemui.grid.ui.compose.SpannedGridsKt;
import com.android.systemui.qs.panels.shared.model.SizedTileImpl;
import com.android.systemui.qs.panels.ui.compose.EditTileListState;
import com.android.systemui.qs.panels.ui.compose.GridLayout;
import com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.FixedColumnsSizeViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.TileViewModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.ui.ElementKeys;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InfiniteGridLayout implements GridLayout {
    public final FixedColumnsSizeViewModel gridSizeViewModel;
    public final IconTilesViewModel iconTilesViewModel;

    public InfiniteGridLayout(IconTilesViewModel iconTilesViewModel, FixedColumnsSizeViewModel fixedColumnsSizeViewModel) {
        this.iconTilesViewModel = iconTilesViewModel;
        this.gridSizeViewModel = fixedColumnsSizeViewModel;
    }

    @Override // com.android.systemui.qs.panels.ui.compose.GridLayout
    public final void EditTileGrid(final List list, final Modifier modifier, final Function2 function2, final Function1 function1, final Function1 function12, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(601306383);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(this.gridSizeViewModel.getColumns(), composerImpl);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(this.iconTilesViewModel.getLargeTiles(), composerImpl);
        Set set = (Set) collectAsStateWithLifecycle2.getValue();
        composerImpl.startReplaceGroup(157947112);
        boolean changed = composerImpl.changed(set) | composerImpl.changed(list);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        Object obj = rememberedValue;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                EditTileViewModel editTileViewModel = (EditTileViewModel) it.next();
                arrayList.add(new SizedTileImpl((editTileViewModel.isCurrent && ((Set) collectAsStateWithLifecycle2.getValue()).contains(editTileViewModel.tileSpec)) ? 2 : 1, editTileViewModel));
            }
            composerImpl.updateRememberedValue(arrayList);
            obj = arrayList;
        }
        composerImpl.end(false);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : (List) obj) {
            if (((EditTileViewModel) ((SizedTileImpl) obj2).tile).isCurrent) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list2 = (List) pair.component1();
        List list3 = (List) pair.component2();
        int intValue = ((Number) collectAsStateWithLifecycle.getValue()).intValue();
        composerImpl.startReplaceGroup(-2145395675);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        composerImpl.startReplaceGroup(1956869121);
        boolean changed2 = composerImpl.changed(list2) | composerImpl.changed(intValue);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new EditTileListState(intValue, list2);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        composerImpl.end(false);
        int i2 = i << 3;
        EditTileKt.DefaultEditTileGrid((EditTileListState) rememberedValue2, list3, ((Number) collectAsStateWithLifecycle.getValue()).intValue(), modifier, function1, function12, new InfiniteGridLayout$EditTileGrid$2(2, this.iconTilesViewModel, IconTilesViewModel.class, "resize", "resize(Lcom/android/systemui/qs/pipeline/shared/TileSpec;Z)V", 0), composerImpl, ((i << 6) & 7168) | 64 | (57344 & i2) | (i2 & 458752));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$EditTileGrid$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    InfiniteGridLayout.this.EditTileGrid(list, modifier, function2, function1, function12, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$TileGrid$3, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.qs.panels.ui.compose.GridLayout
    public final void TileGrid(final ContentScope contentScope, final List list, final Modifier modifier, final Function0 function0, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1101960128);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        EffectsKt.DisposableEffect(list, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$TileGrid$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final Object obj2 = new Object();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((TileViewModel) it.next()).tile.setListening(obj2, true);
                }
                final List list2 = list;
                return new DisposableEffectResult() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$TileGrid$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        for (TileViewModel tileViewModel : list2) {
                            tileViewModel.tile.setListening(obj2, false);
                        }
                    }
                };
            }
        }, composerImpl);
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(this.gridSizeViewModel.getColumns(), composerImpl);
        final ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TileViewModel tileViewModel = (TileViewModel) it.next();
            arrayList.add(new SizedTileImpl(this.iconTilesViewModel.isIconTile(tileViewModel.spec) ? 1 : 2, tileViewModel));
        }
        int intValue = ((Number) collectAsStateWithLifecycle.getValue()).intValue();
        float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_tile_margin_horizontal, composerImpl);
        float dimensionResource2 = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_tile_margin_vertical, composerImpl);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(Integer.valueOf(((SizedTileImpl) arrayList.get(i2)).width));
        }
        SpannedGridsKt.m807VerticalSpannedGridZUYZQmM(intValue, dimensionResource, dimensionResource2, arrayList2, null, ComposableLambdaKt.rememberComposableLambda(-1604339821, new Function4() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$TileGrid$3
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
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                SizedTileImpl sizedTileImpl = (SizedTileImpl) arrayList.get(intValue2);
                TileViewModel tileViewModel2 = (TileViewModel) sizedTileImpl.tile;
                boolean isIconTile = this.iconTilesViewModel.isIconTile(tileViewModel2.spec);
                ContentScope contentScope2 = contentScope;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                ElementKey elementKey = ElementKeys.QuickSettingsContent;
                TileSpec tileSpec = ((TileViewModel) sizedTileImpl.tile).spec;
                TileKt.Tile(tileViewModel2, isIconTile, contentScope2.element(companion, new ElementKeys.TileElementKey(tileSpec.getSpec(), tileSpec.getSpec(), null, 12)), composer2, 0);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 200704, 16);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout$TileGrid$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    InfiniteGridLayout.this.TileGrid(contentScope, list, modifier, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
