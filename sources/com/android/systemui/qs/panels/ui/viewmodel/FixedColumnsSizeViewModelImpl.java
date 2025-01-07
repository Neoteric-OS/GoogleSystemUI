package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.qs.panels.domain.interactor.FixedColumnsSizeInteractor;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FixedColumnsSizeViewModelImpl implements FixedColumnsSizeViewModel {
    public final ReadonlyStateFlow columns;

    public FixedColumnsSizeViewModelImpl(FixedColumnsSizeInteractor fixedColumnsSizeInteractor) {
        this.columns = fixedColumnsSizeInteractor.columns;
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.FixedColumnsSizeViewModel
    public final StateFlow getColumns() {
        return this.columns;
    }
}
