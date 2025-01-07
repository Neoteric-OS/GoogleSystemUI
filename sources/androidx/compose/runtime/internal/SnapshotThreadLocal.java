package androidx.compose.runtime.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotThreadLocal {
    public Object mainThreadValue;
    public final AtomicReference map = new AtomicReference(SnapshotThreadLocalKt.emptyThreadMap);
    public final Object writeMutex = new Object();

    public final Object get() {
        long currentThreadId = Thread_jvmKt.currentThreadId();
        if (currentThreadId == Thread_androidKt.MainThreadId) {
            return this.mainThreadValue;
        }
        ThreadMap threadMap = (ThreadMap) this.map.get();
        int find = threadMap.find(currentThreadId);
        if (find >= 0) {
            return threadMap.values[find];
        }
        return null;
    }

    public final void set(Object obj) {
        long currentThreadId = Thread_jvmKt.currentThreadId();
        if (currentThreadId == Thread_androidKt.MainThreadId) {
            this.mainThreadValue = obj;
            return;
        }
        synchronized (this.writeMutex) {
            ThreadMap threadMap = (ThreadMap) this.map.get();
            int find = threadMap.find(currentThreadId);
            if (find < 0) {
                this.map.set(threadMap.newWith(currentThreadId, obj));
            } else {
                threadMap.values[find] = obj;
            }
        }
    }
}
