package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Map;
import kotlin.collections.AbstractMutableSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AbstractMapBuilderEntries extends AbstractMutableSet {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if ((entry != null ? entry : null) == null) {
            return false;
        }
        PersistentHashMapBuilderEntries persistentHashMapBuilderEntries = (PersistentHashMapBuilderEntries) this;
        Object obj2 = persistentHashMapBuilderEntries.builder.get(entry.getKey());
        return obj2 != null ? obj2.equals(entry.getValue()) : entry.getValue() == null && persistentHashMapBuilderEntries.builder.containsKey(entry.getKey());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if ((entry != null ? entry : null) == null) {
            return false;
        }
        return ((PersistentHashMapBuilderEntries) this).builder.remove(entry.getKey(), entry.getValue());
    }
}
