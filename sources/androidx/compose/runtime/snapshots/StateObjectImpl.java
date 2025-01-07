package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.internal.AtomicInt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class StateObjectImpl implements StateObject {
    public final AtomicInt readerKind = new AtomicInt(0);

    /* renamed from: isReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final boolean m272isReadInh_f27i8$runtime_release(int i) {
        return (this.readerKind.get() & i) != 0;
    }

    /* renamed from: recordReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final void m273recordReadInh_f27i8$runtime_release(int i) {
        AtomicInt atomicInt;
        int i2;
        do {
            atomicInt = this.readerKind;
            i2 = atomicInt.get();
            if ((i2 & i) != 0) {
                return;
            }
        } while (!atomicInt.compareAndSet(i2, i2 | i));
    }
}
