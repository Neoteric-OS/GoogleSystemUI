package com.android.systemui.scene.shared.model;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Scenes {
    public static final SceneKey Bouncer = new SceneKey("bouncer");
    public static final SceneKey Communal = new SceneKey("communal");
    public static final SceneKey Gone = new SceneKey("gone");
    public static final SceneKey Lockscreen = new SceneKey(BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD);
    public static final SceneKey QuickSettings = new SceneKey("quick_settings");
    public static final SceneKey Shade = new SceneKey("shade");
}
