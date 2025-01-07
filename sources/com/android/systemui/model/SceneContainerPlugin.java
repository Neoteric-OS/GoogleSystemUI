package com.android.systemui.model;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.model.SceneContainerPlugin;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.shared.model.Scenes;
import dagger.Lazy;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneContainerPlugin {
    public static final Map EvaluatorByFlag = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class SceneContainerPluginState {
        public final Set overlays;
        public final SceneKey scene;
    }

    static {
        MapsKt.mapOf(new Pair(1073741824L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!Intrinsics.areEqual(((SceneContainerPlugin.SceneContainerPluginState) obj).scene, Scenes.Gone));
            }
        }), new Pair(4L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SceneContainerPlugin.SceneContainerPluginState sceneContainerPluginState = (SceneContainerPlugin.SceneContainerPluginState) obj;
                sceneContainerPluginState.getClass();
                SceneKey sceneKey = Scenes.Lockscreen;
                SceneKey sceneKey2 = sceneContainerPluginState.scene;
                return Boolean.valueOf(Intrinsics.areEqual(sceneKey2, sceneKey) || Intrinsics.areEqual(sceneKey2, Scenes.Shade) || sceneContainerPluginState.overlays.contains(Overlays.NotificationsShade));
            }
        }), new Pair(2048L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SceneContainerPlugin.SceneContainerPluginState sceneContainerPluginState = (SceneContainerPlugin.SceneContainerPluginState) obj;
                return Boolean.valueOf(Intrinsics.areEqual(sceneContainerPluginState.scene, Scenes.QuickSettings) || sceneContainerPluginState.overlays.contains(Overlays.QuickSettingsShade));
            }
        }), new Pair(8L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual(((SceneContainerPlugin.SceneContainerPluginState) obj).scene, Scenes.Bouncer));
            }
        }), new Pair(64L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual(((SceneContainerPlugin.SceneContainerPluginState) obj).scene, Scenes.Lockscreen));
            }
        }), new Pair(512L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Intrinsics.areEqual(((SceneContainerPlugin.SceneContainerPluginState) obj).scene, Scenes.Lockscreen);
                return false;
            }
        }), new Pair(34359738368L, new Function1() { // from class: com.android.systemui.model.SceneContainerPlugin$Companion$EvaluatorByFlag$7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual(((SceneContainerPlugin.SceneContainerPluginState) obj).scene, Scenes.Communal));
            }
        }));
    }

    public SceneContainerPlugin(Lazy lazy, Lazy lazy2) {
    }
}
