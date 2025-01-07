package com.android.systemui.scene.domain.resolver;

import dagger.internal.Provider;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SceneResolverModule_Companion_ProvideResolverMapFactory implements Provider {
    public static Map provideResolverMap(Set set) {
        Set set2 = set;
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Object obj : set2) {
            linkedHashMap.put(((HomeSceneFamilyResolver) ((SceneResolver) obj)).targetFamily, obj);
        }
        return linkedHashMap;
    }
}
