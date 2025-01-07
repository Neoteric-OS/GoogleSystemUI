package com.android.systemui.scene.ui.composable;

import androidx.compose.runtime.SnapshotStateKt;
import com.android.compose.animation.scene.AnimateToSceneKt;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutState;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import com.android.compose.animation.scene.ObservableTransitionStateKt;
import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.scene.shared.model.SceneDataSource;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneTransitionLayoutDataSource implements SceneDataSource {
    public final CoroutineScope coroutineScope;
    public final ReadonlyStateFlow currentOverlays;
    public final ReadonlyStateFlow currentScene;
    public final MutableSceneTransitionLayoutState state;

    public SceneTransitionLayoutDataSource(MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState, CoroutineScope coroutineScope) {
        this.state = mutableSceneTransitionLayoutState;
        this.coroutineScope = coroutineScope;
        this.currentScene = FlowKt.stateIn(FlowKt.transformLatest(ObservableTransitionStateKt.observableTransitionState(mutableSceneTransitionLayoutState), new SceneTransitionLayoutDataSource$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MutableSceneTransitionLayoutStateImpl) mutableSceneTransitionLayoutState).getTransitionState().getCurrentScene());
        this.currentOverlays = FlowKt.stateIn(SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.systemui.scene.ui.composable.SceneTransitionLayoutDataSource$currentOverlays$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ((MutableSceneTransitionLayoutStateImpl) SceneTransitionLayoutDataSource.this.state).getTransitionState().getCurrentOverlays();
            }
        }), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptySet.INSTANCE);
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final void changeScene(SceneKey sceneKey, TransitionKey transitionKey) {
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = (MutableSceneTransitionLayoutStateImpl) this.state;
        mutableSceneTransitionLayoutStateImpl.checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        AnimateToSceneKt.animateToScene(this.coroutineScope, mutableSceneTransitionLayoutStateImpl, sceneKey, transitionKey);
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
        ((MutableSceneTransitionLayoutStateImpl) this.state).hideOverlay(overlayKey, this.coroutineScope);
    }

    @Override // com.android.systemui.scene.shared.model.SceneDataSource
    public final void snapToScene(SceneKey sceneKey) {
        MutableSceneTransitionLayoutState.snapToScene$default(this.state, sceneKey);
    }
}
