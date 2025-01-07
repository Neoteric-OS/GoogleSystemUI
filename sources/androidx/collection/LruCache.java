package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import androidx.collection.internal.RuntimeHelpersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LruCache {
    public int hitCount;
    public final Lock lock;
    public final LruHashMap map;
    public final int maxSize;
    public int missCount;
    public int size;

    public LruCache(int i) {
        this.maxSize = i;
        if (i <= 0) {
            RuntimeHelpersKt.throwIllegalArgumentException("maxSize <= 0");
            throw null;
        }
        this.map = new LruHashMap();
        this.lock = new Lock();
    }

    public final Object get(Object obj) {
        synchronized (this.lock) {
            Object obj2 = this.map.map.get(obj);
            if (obj2 != null) {
                this.hitCount++;
                return obj2;
            }
            this.missCount++;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0079, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object put(java.lang.Object r4, java.lang.Object r5) {
        /*
            r3 = this;
            androidx.collection.internal.Lock r0 = r3.lock
            monitor-enter(r0)
            int r1 = r3.size     // Catch: java.lang.Throwable -> L1a
            int r1 = r1 + 1
            r3.size = r1     // Catch: java.lang.Throwable -> L1a
            androidx.collection.internal.LruHashMap r1 = r3.map     // Catch: java.lang.Throwable -> L1a
            java.util.LinkedHashMap r1 = r1.map     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r4 = r1.put(r4, r5)     // Catch: java.lang.Throwable -> L1a
            if (r4 == 0) goto L1c
            int r5 = r3.size     // Catch: java.lang.Throwable -> L1a
            int r5 = r5 + (-1)
            r3.size = r5     // Catch: java.lang.Throwable -> L1a
            goto L1c
        L1a:
            r3 = move-exception
            goto L7c
        L1c:
            monitor-exit(r0)
            int r5 = r3.maxSize
        L1f:
            androidx.collection.internal.Lock r0 = r3.lock
            monitor-enter(r0)
            int r1 = r3.size     // Catch: java.lang.Throwable -> L35
            if (r1 < 0) goto L72
            androidx.collection.internal.LruHashMap r1 = r3.map     // Catch: java.lang.Throwable -> L35
            java.util.LinkedHashMap r1 = r1.map     // Catch: java.lang.Throwable -> L35
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L37
            int r1 = r3.size     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto L72
            goto L37
        L35:
            r3 = move-exception
            goto L7a
        L37:
            int r1 = r3.size     // Catch: java.lang.Throwable -> L35
            if (r1 <= r5) goto L70
            androidx.collection.internal.LruHashMap r1 = r3.map     // Catch: java.lang.Throwable -> L35
            java.util.LinkedHashMap r1 = r1.map     // Catch: java.lang.Throwable -> L35
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L46
            goto L70
        L46:
            androidx.collection.internal.LruHashMap r1 = r3.map     // Catch: java.lang.Throwable -> L35
            java.util.LinkedHashMap r1 = r1.map     // Catch: java.lang.Throwable -> L35
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L35
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L35
            java.lang.Object r1 = kotlin.collections.CollectionsKt.firstOrNull(r1)     // Catch: java.lang.Throwable -> L35
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto L5a
            monitor-exit(r0)
            goto L71
        L5a:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L35
            r1.getValue()     // Catch: java.lang.Throwable -> L35
            androidx.collection.internal.LruHashMap r1 = r3.map     // Catch: java.lang.Throwable -> L35
            java.util.LinkedHashMap r1 = r1.map     // Catch: java.lang.Throwable -> L35
            r1.remove(r2)     // Catch: java.lang.Throwable -> L35
            int r1 = r3.size     // Catch: java.lang.Throwable -> L35
            int r1 = r1 + (-1)
            r3.size = r1     // Catch: java.lang.Throwable -> L35
            monitor-exit(r0)
            goto L1f
        L70:
            monitor-exit(r0)
        L71:
            return r4
        L72:
            java.lang.String r3 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L35
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L35
            throw r4     // Catch: java.lang.Throwable -> L35
        L7a:
            monitor-exit(r0)
            throw r3
        L7c:
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final String toString() {
        String str;
        synchronized (this.lock) {
            try {
                int i = this.hitCount;
                int i2 = this.missCount + i;
                str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + (i2 != 0 ? (i * 100) / i2 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
