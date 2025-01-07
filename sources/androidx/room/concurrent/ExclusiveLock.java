package androidx.room.concurrent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExclusiveLock {
    public static final Companion Companion = new Companion();
    public static final Map threadLocksMap = new LinkedHashMap();
    public final FileLock fileLock;
    public final ReentrantLock threadLock;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    public ExclusiveLock(String str, boolean z) {
        ReentrantLock reentrantLock;
        synchronized (Companion) {
            try {
                Map map = threadLocksMap;
                Object obj = map.get(str);
                if (obj == null) {
                    obj = new ReentrantLock();
                    map.put(str, obj);
                }
                reentrantLock = (ReentrantLock) obj;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.threadLock = reentrantLock;
        this.fileLock = z ? new FileLock(str) : null;
    }
}
