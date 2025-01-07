package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.panels.domain.interactor.QuickQuickSettingsRowInteractor;
import com.android.systemui.qs.panels.shared.model.SizedTileImpl;
import com.android.systemui.qs.panels.shared.model.TileRowKt;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickQuickSettingsViewModel {
    public final StateFlow columns;
    public final IconTilesViewModel iconTilesViewModel;
    public final ReadonlyStateFlow rows;
    public final ReadonlyStateFlow tileViewModels;

    public QuickQuickSettingsViewModel(CurrentTilesInteractor currentTilesInteractor, FixedColumnsSizeViewModel fixedColumnsSizeViewModel, QuickQuickSettingsRowInteractor quickQuickSettingsRowInteractor, IconTilesViewModel iconTilesViewModel, CoroutineScope coroutineScope) {
        this.iconTilesViewModel = iconTilesViewModel;
        StateFlow columns = fixedColumnsSizeViewModel.getColumns();
        this.columns = columns;
        this.rows = FlowKt.stateIn(quickQuickSettingsRowInteractor.rows, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 2);
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(columns, new QuickQuickSettingsViewModel$special$$inlined$flatMapLatest$1(null, currentTilesInteractor, this));
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Iterable<TileModel> iterable = (Iterable) ((StateFlowImpl) ((CurrentTilesInteractorImpl) currentTilesInteractor).currentTiles.$$delegate_0).getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (TileModel tileModel : iterable) {
            QSTile qSTile = tileModel.tile;
            TileSpec tileSpec = tileModel.spec;
            arrayList.add(new SizedTileImpl(this.iconTilesViewModel.isIconTile(tileSpec) ? 1 : 2, new TileViewModel(qSTile, tileSpec)));
        }
        this.tileViewModels = FlowKt.stateIn(transformLatest, coroutineScope, WhileSubscribed$default, CollectionsKt__IterablesKt.flatten(SequencesKt.toList(SequencesKt.take(TileRowKt.splitInRowsSequence(((Number) this.columns.getValue()).intValue(), arrayList), ((Number) this.rows.getValue()).intValue()))));
    }
}
