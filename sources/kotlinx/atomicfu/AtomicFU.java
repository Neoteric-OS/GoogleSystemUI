package kotlinx.atomicfu;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AtomicFU {
    public static final AtomicRef atomic(Object obj) {
        AtomicRef atomicRef = new AtomicRef();
        atomicRef.value = obj;
        return atomicRef;
    }

    public static final AtomicInt atomic(int i) {
        AtomicInt atomicInt = new AtomicInt();
        atomicInt.value = i;
        return atomicInt;
    }

    public static final AtomicLong atomic(long j) {
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.value = j;
        return atomicLong;
    }

    public static final AtomicBoolean atomic(boolean z) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean._value = z ? 1 : 0;
        return atomicBoolean;
    }
}
