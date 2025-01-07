package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.qs.panels.domain.interactor.PaginatedGridInteractor;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PaginatedGridViewModel implements IconTilesViewModel, FixedColumnsSizeViewModel, IconLabelVisibilityViewModel {
    public final /* synthetic */ IconTilesViewModel $$delegate_0;
    public final /* synthetic */ FixedColumnsSizeViewModel $$delegate_1;
    public final ReadonlyStateFlow rows;

    public PaginatedGridViewModel(IconTilesViewModel iconTilesViewModel, FixedColumnsSizeViewModel fixedColumnsSizeViewModel, PaginatedGridInteractor paginatedGridInteractor, CoroutineScope coroutineScope) {
        this.$$delegate_0 = iconTilesViewModel;
        this.$$delegate_1 = fixedColumnsSizeViewModel;
        this.rows = FlowKt.stateIn(paginatedGridInteractor.rows, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 4);
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.FixedColumnsSizeViewModel
    public final StateFlow getColumns() {
        return this.$$delegate_1.getColumns();
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final StateFlow getLargeTiles() {
        return this.$$delegate_0.getLargeTiles();
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final boolean isIconTile(TileSpec tileSpec) {
        return this.$$delegate_0.isIconTile(tileSpec);
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final void resize(TileSpec tileSpec, boolean z) {
        this.$$delegate_0.resize(tileSpec, z);
    }
}
