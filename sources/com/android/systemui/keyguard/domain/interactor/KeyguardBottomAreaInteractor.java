package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.common.shared.model.Position;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBottomAreaInteractor {
    public final StateFlowImpl _clockPosition;
    public final StateFlow alpha;
    public final StateFlow animateDozingTransitions;
    public final ReadonlyStateFlow clockPosition = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(new Position()));

    public KeyguardBottomAreaInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl) {
        this.animateDozingTransitions = keyguardRepositoryImpl.animateBottomAreaDozingTransitions;
        this.alpha = keyguardRepositoryImpl.bottomAreaAlpha;
    }
}
