package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DynamicValueHolder implements ValueHolder {
    public final MutableState state;

    public DynamicValueHolder(MutableState mutableState) {
        this.state = mutableState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DynamicValueHolder) && Intrinsics.areEqual(this.state, ((DynamicValueHolder) obj).state);
    }

    public final int hashCode() {
        return this.state.hashCode();
    }

    @Override // androidx.compose.runtime.ValueHolder
    public final Object readValue(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        return ((SnapshotMutableStateImpl) this.state).getValue();
    }

    public final String toString() {
        return "DynamicValueHolder(state=" + this.state + ')';
    }
}
