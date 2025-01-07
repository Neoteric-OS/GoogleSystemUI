package com.android.systemui.shade.domain.interactor;

import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.shade.data.repository.ShadeAnimationRepository;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeAnimationInteractorSceneContainerImpl extends ShadeAnimationInteractor {
    public final ReadonlyStateFlow isAnyCloseAnimationRunning;

    public ShadeAnimationInteractorSceneContainerImpl(CoroutineScope coroutineScope, ShadeAnimationRepository shadeAnimationRepository, SceneInteractor sceneInteractor) {
        super(shadeAnimationRepository);
        ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$1 shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$1 = new ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$1(3, null);
        ReadonlyStateFlow readonlyStateFlow = sceneInteractor.transitionState;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.transformLatest(readonlyStateFlow, shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$1));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        Boolean bool = Boolean.FALSE;
        this.isAnyCloseAnimationRunning = FlowKt.stateIn(distinctUntilChanged, coroutineScope, startedEagerly, bool);
        FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(readonlyStateFlow, new ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2(3, null))), coroutineScope, startedEagerly, bool);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor
    public final StateFlow isAnyCloseAnimationRunning() {
        return this.isAnyCloseAnimationRunning;
    }
}
