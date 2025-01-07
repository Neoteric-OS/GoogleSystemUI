package com.android.systemui.unfold.data.repository;

import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.Optional;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionRepositoryImpl {
    public final Optional unfoldProgressProvider;

    public UnfoldTransitionRepositoryImpl(Optional optional) {
        this.unfoldProgressProvider = optional;
    }

    public final Flow getTransitionStatus() {
        UnfoldTransitionProgressProvider unfoldTransitionProgressProvider = (UnfoldTransitionProgressProvider) this.unfoldProgressProvider.orElse(null);
        return unfoldTransitionProgressProvider == null ? EmptyFlow.INSTANCE : FlowConflatedKt.conflatedCallbackFlow(new UnfoldTransitionRepositoryImpl$transitionStatus$1(unfoldTransitionProgressProvider, null));
    }
}
