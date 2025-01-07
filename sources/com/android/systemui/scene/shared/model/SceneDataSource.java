package com.android.systemui.scene.shared.model;

import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SceneDataSource {
    void changeScene(SceneKey sceneKey, TransitionKey transitionKey);

    StateFlow getCurrentOverlays();

    StateFlow getCurrentScene();

    void hideOverlay(OverlayKey overlayKey);

    void snapToScene(SceneKey sceneKey);
}
