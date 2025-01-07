package com.android.systemui.scene.shared.model;

import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.scene.ui.composable.SceneTransitionLayoutDataSource;
import kotlin.collections.EmptySet;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneDataSourceDelegator implements SceneDataSource {
    public final ReadonlyStateFlow currentOverlays;
    public final ReadonlyStateFlow currentScene;
    public final StateFlowImpl delegateMutable;
    public final NoOpSceneDataSource noOpDelegate;

    public SceneDataSourceDelegator(CoroutineScope coroutineScope, SceneContainerConfig sceneContainerConfig) {
        SceneKey sceneKey = sceneContainerConfig.initialSceneKey;
        NoOpSceneDataSource noOpSceneDataSource = new NoOpSceneDataSource(sceneKey);
        this.noOpDelegate = noOpSceneDataSource;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(noOpSceneDataSource);
        this.delegateMutable = MutableStateFlow;
        this.currentScene = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new SceneDataSourceDelegator$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), sceneKey);
        this.currentOverlays = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new SceneDataSourceDelegator$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptySet.INSTANCE);
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final void changeScene(SceneKey sceneKey, TransitionKey transitionKey) {
        ((SceneDataSource) this.delegateMutable.getValue()).changeScene(sceneKey, transitionKey);
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final StateFlow getCurrentOverlays() {
        return this.currentOverlays;
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final StateFlow getCurrentScene() {
        return this.currentScene;
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final void hideOverlay(OverlayKey overlayKey) {
        ((SceneDataSource) this.delegateMutable.getValue()).hideOverlay(overlayKey);
    }

    public final void setDelegate(SceneTransitionLayoutDataSource sceneTransitionLayoutDataSource) {
        SceneDataSource sceneDataSource = sceneTransitionLayoutDataSource;
        if (sceneTransitionLayoutDataSource == null) {
            sceneDataSource = this.noOpDelegate;
        }
        this.delegateMutable.setValue(sceneDataSource);
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final void snapToScene(SceneKey sceneKey) {
        ((SceneDataSource) this.delegateMutable.getValue()).snapToScene(sceneKey);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoOpSceneDataSource implements SceneDataSource {
        public final ReadonlyStateFlow currentOverlays = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(EmptySet.INSTANCE));
        public final ReadonlyStateFlow currentScene;

        public NoOpSceneDataSource(SceneKey sceneKey) {
            this.currentScene = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(sceneKey));
        }

        @Override // com.android.systemui.scene.shared.model.SceneDataSource
        public final StateFlow getCurrentOverlays() {
            return this.currentOverlays;
        }

        @Override // com.android.systemui.scene.shared.model.SceneDataSource
        public final StateFlow getCurrentScene() {
            return this.currentScene;
        }

        @Override // com.android.systemui.scene.shared.model.SceneDataSource
        public final void hideOverlay(OverlayKey overlayKey) {
        }

        @Override // com.android.systemui.scene.shared.model.SceneDataSource
        public final void snapToScene(SceneKey sceneKey) {
        }

        @Override // com.android.systemui.scene.shared.model.SceneDataSource
        public final void changeScene(SceneKey sceneKey, TransitionKey transitionKey) {
        }
    }
}
