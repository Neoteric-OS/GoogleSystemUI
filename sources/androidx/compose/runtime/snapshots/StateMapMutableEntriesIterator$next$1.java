package androidx.compose.runtime.snapshots;

import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StateMapMutableEntriesIterator$next$1 implements Map.Entry, KMutableMap.Entry {
    public final Object key;
    public final /* synthetic */ StateMapMutableEntriesIterator this$0;
    public Object value;

    public StateMapMutableEntriesIterator$next$1(StateMapMutableEntriesIterator stateMapMutableEntriesIterator) {
        this.this$0 = stateMapMutableEntriesIterator;
        Map.Entry entry = stateMapMutableEntriesIterator.current;
        Intrinsics.checkNotNull(entry);
        this.key = entry.getKey();
        Map.Entry entry2 = stateMapMutableEntriesIterator.current;
        Intrinsics.checkNotNull(entry2);
        this.value = entry2.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        StateMapMutableEntriesIterator stateMapMutableEntriesIterator = this.this$0;
        if (stateMapMutableEntriesIterator.map.getReadable$runtime_release().modification != stateMapMutableEntriesIterator.modification) {
            throw new ConcurrentModificationException();
        }
        Object obj2 = this.value;
        stateMapMutableEntriesIterator.map.put(this.key, obj);
        this.value = obj;
        return obj2;
    }
}
