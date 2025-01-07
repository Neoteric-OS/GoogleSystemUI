package com.android.compose.animation.scene.content;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Scene extends Content {
    public final SceneKey key;

    public Scene(SceneKey sceneKey, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ComposableLambdaImpl composableLambdaImpl, Map map, float f) {
        super(sceneTransitionLayoutImpl, composableLambdaImpl, map, f);
        this.key = sceneKey;
    }

    @Override // com.android.compose.animation.scene.content.Content
    public final ContentKey getKey() {
        return this.key;
    }

    public final String toString() {
        return "Scene(key=" + this.key + ")";
    }
}
