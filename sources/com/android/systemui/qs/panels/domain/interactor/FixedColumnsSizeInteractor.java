package com.android.systemui.qs.panels.domain.interactor;

import com.android.systemui.qs.panels.data.repository.FixedColumnsRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FixedColumnsSizeInteractor {
    public final ReadonlyStateFlow columns;

    public FixedColumnsSizeInteractor(FixedColumnsRepository fixedColumnsRepository) {
        this.columns = fixedColumnsRepository.columns;
    }
}
