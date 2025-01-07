package com.android.systemui.unfold.domain.interactor;

import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.unfold.data.repository.UnfoldTransitionRepositoryImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionInteractor {
    public final ConfigurationInteractor configurationInteractor;
    public final UnfoldTransitionRepositoryImpl repository;
    public final Flow unfoldProgress;

    public UnfoldTransitionInteractor(UnfoldTransitionRepositoryImpl unfoldTransitionRepositoryImpl, ConfigurationInteractor configurationInteractor) {
        this.repository = unfoldTransitionRepositoryImpl;
        FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new UnfoldTransitionInteractor$unfoldProgress$2(2, null), new UnfoldTransitionInteractor$special$$inlined$map$1(unfoldTransitionRepositoryImpl.getTransitionStatus(), 0)));
    }
}
