package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MutableScatterMultiMap {
    public final MutableScatterMap map;

    public final boolean equals(Object obj) {
        if (obj instanceof MutableScatterMultiMap) {
            return Intrinsics.areEqual(this.map, ((MutableScatterMultiMap) obj).map);
        }
        return false;
    }

    public final int hashCode() {
        return this.map.hashCode();
    }

    public final String toString() {
        return "MutableScatterMultiMap(map=" + this.map + ')';
    }
}
