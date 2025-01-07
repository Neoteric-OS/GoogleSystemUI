package com.android.systemui.scene.data.repository;

import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.systemui.scene.shared.model.SceneContainerConfig;
import com.android.systemui.scene.shared.model.SceneDataSource;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneContainerRepository {
    public final StateFlowImpl _isVisible;
    public final StateFlowImpl _transitionState;
    public final List allContentKeys;
    public final StateFlow currentOverlays;
    public final StateFlow currentScene;
    public final SceneDataSource dataSource;
    public final ObservableTransitionState.Idle defaultTransitionState;
    public final StateFlowImpl isRemoteUserInputOngoing;
    public final StateFlowImpl isSceneContainerUserInputOngoing;
    public final ReadonlyStateFlow isVisible = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(Boolean.TRUE));
    public final ReadonlyStateFlow transitionState;

    public SceneContainerRepository(CoroutineScope coroutineScope, SceneContainerConfig sceneContainerConfig, SceneDataSource sceneDataSource) {
        this.dataSource = sceneDataSource;
        this.allContentKeys = CollectionsKt.plus((Iterable) sceneContainerConfig.overlayKeys, (Collection) sceneContainerConfig.sceneKeys);
        this.currentScene = sceneDataSource.getCurrentScene();
        this.currentOverlays = sceneDataSource.getCurrentOverlays();
        Boolean bool = Boolean.FALSE;
        this.isRemoteUserInputOngoing = StateFlowKt.MutableStateFlow(bool);
        this.isSceneContainerUserInputOngoing = StateFlowKt.MutableStateFlow(bool);
        ObservableTransitionState.Idle idle = new ObservableTransitionState.Idle(sceneContainerConfig.initialSceneKey);
        this.defaultTransitionState = idle;
        this.transitionState = FlowKt.stateIn(FlowKt.transformLatest(StateFlowKt.MutableStateFlow(null), new SceneContainerRepository$special$$inlined$flatMapLatest$1(null, this)), coroutineScope, SharingStarted.Companion.Eagerly, idle);
    }
}
