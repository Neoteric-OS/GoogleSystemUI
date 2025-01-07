package com.android.systemui.scene;

import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.shared.model.SceneContainerConfig;
import com.android.systemui.scene.shared.model.Scenes;
import dagger.internal.Provider;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SceneContainerFrameworkModule_Companion_ContainerConfigFactory implements Provider {
    public static SceneContainerConfig containerConfig() {
        SceneKey sceneKey = Scenes.Gone;
        SceneKey sceneKey2 = Scenes.Communal;
        SceneKey sceneKey3 = Scenes.Lockscreen;
        SceneKey sceneKey4 = Scenes.Bouncer;
        SceneKey sceneKey5 = Scenes.QuickSettings;
        SceneKey sceneKey6 = Scenes.Shade;
        List filterNotNull = ArraysKt.filterNotNull(new SceneKey[]{sceneKey, sceneKey2, sceneKey3, sceneKey4, sceneKey5, sceneKey6});
        OverlayKey overlayKey = Overlays.NotificationsShade;
        List filterNotNull2 = ArraysKt.filterNotNull(new OverlayKey[]{null, null});
        Map mapOf = MapsKt.mapOf(new Pair(sceneKey, 0), new Pair(sceneKey3, 0), new Pair(sceneKey2, 1), new Pair(sceneKey6, 2), new Pair(sceneKey5, 3), new Pair(sceneKey4, 4));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : mapOf.entrySet()) {
            if (((Integer) entry.getValue()) != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            Object key = entry2.getKey();
            Object value = entry2.getValue();
            if (value == null) {
                throw new IllegalStateException("Required value was null.");
            }
            linkedHashMap2.put(key, Integer.valueOf(((Number) value).intValue()));
        }
        return new SceneContainerConfig(filterNotNull, sceneKey3, filterNotNull2, linkedHashMap2);
    }
}
