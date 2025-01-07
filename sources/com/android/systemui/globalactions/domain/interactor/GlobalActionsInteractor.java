package com.android.systemui.globalactions.domain.interactor;

import com.android.systemui.globalactions.data.repository.GlobalActionsRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsInteractor {
    public final ReadonlyStateFlow isVisible;
    public final GlobalActionsRepository repository;

    public GlobalActionsInteractor(GlobalActionsRepository globalActionsRepository) {
        this.repository = globalActionsRepository;
        this.isVisible = globalActionsRepository.isVisible;
    }
}
