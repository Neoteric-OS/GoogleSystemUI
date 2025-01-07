package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.TransitionState;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InternalKeyguardTransitionInteractor {
    public final ReadonlyStateFlow currentTransitionInfoInternal;
    public final KeyguardTransitionRepositoryImpl repository;

    public InternalKeyguardTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl) {
        this.repository = keyguardTransitionRepositoryImpl;
        this.currentTransitionInfoInternal = keyguardTransitionRepositoryImpl.currentTransitionInfoInternal;
    }

    public final Object updateTransition(UUID uuid, float f, TransitionState transitionState, Continuation continuation) {
        Object updateTransition = this.repository.updateTransition(uuid, f, transitionState, continuation);
        return updateTransition == CoroutineSingletons.COROUTINE_SUSPENDED ? updateTransition : Unit.INSTANCE;
    }
}
