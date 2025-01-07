package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlanceableHubTransitions {
    public final CommunalInteractor communalInteractor;
    public final KeyguardTransitionInteractor transitionInteractor;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    public GlanceableHubTransitions(KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, CommunalInteractor communalInteractor) {
    }
}
