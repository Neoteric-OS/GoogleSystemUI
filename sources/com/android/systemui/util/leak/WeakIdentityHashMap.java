package com.android.systemui.util.leak;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WeakIdentityHashMap {
    public final HashMap mMap = new HashMap();
    public final ReferenceQueue mRefQueue = new ReferenceQueue();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CmpWeakReference extends WeakReference {
        public final int mHashCode;

        public CmpWeakReference(Object obj) {
            super(obj);
            this.mHashCode = System.identityHashCode(obj);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            T t = get();
            return t != 0 && (obj instanceof CmpWeakReference) && ((CmpWeakReference) obj).get() == t;
        }

        public final int hashCode() {
            return this.mHashCode;
        }

        public CmpWeakReference(Object obj, ReferenceQueue referenceQueue) {
            super(obj, referenceQueue);
            this.mHashCode = System.identityHashCode(obj);
        }
    }
}
