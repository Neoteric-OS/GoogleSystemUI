package com.google.android.systemui.qs.ui.viewmodel;

import com.android.systemui.qs.panels.domain.interactor.GridLayoutTypeInteractor;
import java.util.Set;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GridLayoutSelectorViewModel {
    public final Set gridLayoutTypes;
    public final GridLayoutTypeInteractor interactor;
    public final ReadonlyStateFlow selectedType;

    public GridLayoutSelectorViewModel(Set set, GridLayoutTypeInteractor gridLayoutTypeInteractor) {
        ReadonlyStateFlow readonlyStateFlow = gridLayoutTypeInteractor.layout;
    }
}
