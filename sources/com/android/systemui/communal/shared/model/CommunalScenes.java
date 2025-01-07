package com.android.systemui.communal.shared.model;

import com.android.compose.animation.scene.SceneKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalScenes {
    public static final SceneKey Blank;
    public static final SceneKey Communal;
    public static final SceneKey Default;

    static {
        SceneKey sceneKey = new SceneKey("blank");
        Blank = sceneKey;
        Communal = new SceneKey("communal");
        Default = sceneKey;
    }
}
