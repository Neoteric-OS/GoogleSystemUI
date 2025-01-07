package androidx.compose.runtime;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface MutableIntState extends State, MutableState {
    @Override // androidx.compose.runtime.State
    default Object getValue() {
        return Integer.valueOf(((SnapshotMutableIntStateImpl) this).getIntValue());
    }

    @Override // androidx.compose.runtime.MutableState
    default void setValue(Object obj) {
        ((SnapshotMutableIntStateImpl) this).setIntValue(((Number) obj).intValue());
    }
}
