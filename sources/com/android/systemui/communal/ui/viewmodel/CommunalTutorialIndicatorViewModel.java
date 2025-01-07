package com.android.systemui.communal.ui.viewmodel;

import com.android.systemui.communal.domain.interactor.CommunalTutorialInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTutorialIndicatorViewModel {
    public final Flow alpha;
    public final CommunalTutorialInteractor communalTutorialInteractor;

    public CommunalTutorialIndicatorViewModel(CommunalTutorialInteractor communalTutorialInteractor, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor) {
        this.communalTutorialInteractor = communalTutorialInteractor;
        this.alpha = FlowKt.distinctUntilChanged(keyguardBottomAreaInteractor.alpha);
    }
}
