package com.android.systemui.scene.shared.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.SceneKey;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneContainerConfig {
    public final SceneKey initialSceneKey;
    public final Map navigationDistances;
    public final List overlayKeys;
    public final List sceneKeys;

    public SceneContainerConfig(List list, SceneKey sceneKey, List list2, Map map) {
        this.sceneKeys = list;
        this.initialSceneKey = sceneKey;
        this.overlayKeys = list2;
        this.navigationDistances = map;
        if (list.isEmpty()) {
            throw new IllegalStateException("A container must have at least one scene key.");
        }
        if (list.contains(sceneKey)) {
            if (!Intrinsics.areEqual(map.keySet(), CollectionsKt.toSet(list))) {
                throw new IllegalStateException("Scene keys and distance map must match.");
            }
        } else {
            throw new IllegalStateException(("The initial key \"" + sceneKey + "\" is not present in this container.").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SceneContainerConfig)) {
            return false;
        }
        SceneContainerConfig sceneContainerConfig = (SceneContainerConfig) obj;
        return Intrinsics.areEqual(this.sceneKeys, sceneContainerConfig.sceneKeys) && Intrinsics.areEqual(this.initialSceneKey, sceneContainerConfig.initialSceneKey) && Intrinsics.areEqual(this.overlayKeys, sceneContainerConfig.overlayKeys) && Intrinsics.areEqual(this.navigationDistances, sceneContainerConfig.navigationDistances);
    }

    public final int hashCode() {
        return this.navigationDistances.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((this.initialSceneKey.identity.hashCode() + (this.sceneKeys.hashCode() * 31)) * 31, 31, this.overlayKeys);
    }

    public final String toString() {
        return "SceneContainerConfig(sceneKeys=" + this.sceneKeys + ", initialSceneKey=" + this.initialSceneKey + ", overlayKeys=" + this.overlayKeys + ", navigationDistances=" + this.navigationDistances + ")";
    }
}
