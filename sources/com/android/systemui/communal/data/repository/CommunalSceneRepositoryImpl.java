package com.android.systemui.communal.data.repository;

import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.scene.shared.model.SceneDataSource;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSceneRepositoryImpl {
    public final StateFlowImpl _transitionState;
    public final CoroutineScope applicationScope;
    public final StateFlow currentScene;
    public final ObservableTransitionState.Idle defaultTransitionState;
    public final SceneDataSource sceneDataSource;
    public final ReadonlyStateFlow transitionState;

    public CommunalSceneRepositoryImpl(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, SceneDataSource sceneDataSource) {
        this.applicationScope = coroutineScope;
        this.sceneDataSource = sceneDataSource;
        this.currentScene = sceneDataSource.getCurrentScene();
        ObservableTransitionState.Idle idle = new ObservableTransitionState.Idle(CommunalScenes.Default);
        this.defaultTransitionState = idle;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._transitionState = MutableStateFlow;
        this.transitionState = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new CommunalSceneRepositoryImpl$special$$inlined$flatMapLatest$1(null, this)), coroutineScope2, SharingStarted.Companion.Lazily, idle);
    }

    public final void changeScene(SceneKey sceneKey, TransitionKey transitionKey) {
        BuildersKt.launch$default(this.applicationScope, null, null, new CommunalSceneRepositoryImpl$changeScene$1(this, sceneKey, transitionKey, null), 3);
    }

    public final void snapToScene(SceneKey sceneKey) {
        BuildersKt.launch$default(this.applicationScope, null, null, new CommunalSceneRepositoryImpl$snapToScene$1(this, sceneKey, null), 3);
    }
}
