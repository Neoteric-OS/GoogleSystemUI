package com.android.compose.animation.scene;

import androidx.compose.ui.unit.LayoutDirection;
import com.android.compose.animation.scene.UserActionResult;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class UserAction {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Resolved {
    }

    public abstract Resolved resolve$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(LayoutDirection layoutDirection);

    public final Pair to(SceneKey sceneKey) {
        return new Pair(this, new UserActionResult.ChangeScene(sceneKey, null, false));
    }
}
