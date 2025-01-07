package androidx.compose.foundation.lazy.layout;

import android.view.Choreographer;
import android.view.View;
import androidx.compose.foundation.lazy.layout.PrefetchHandleProvider;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.collection.MutableVector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidPrefetchScheduler implements PrefetchScheduler, RememberObserver, Runnable, Choreographer.FrameCallback {
    public static long frameIntervalNs;
    public long frameStartTimeNanos;
    public boolean isActive;
    public boolean prefetchScheduled;
    public final View view;
    public final MutableVector prefetchRequests = new MutableVector(new PrefetchRequest[16]);
    public final Choreographer choreographer = Choreographer.getInstance();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PrefetchRequestScopeImpl {
        public final long nextFrameTimeNs;

        public PrefetchRequestScopeImpl(long j) {
            this.nextFrameTimeNs = j;
        }

        public final long availableTimeNanos() {
            return Math.max(0L, this.nextFrameTimeNs - System.nanoTime());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0032, code lost:
    
        if (r4 >= 30.0f) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AndroidPrefetchScheduler(android.view.View r5) {
        /*
            r4 = this;
            r4.<init>()
            r4.view = r5
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r1 = 16
            androidx.compose.foundation.lazy.layout.PrefetchRequest[] r1 = new androidx.compose.foundation.lazy.layout.PrefetchRequest[r1]
            r0.<init>(r1)
            r4.prefetchRequests = r0
            android.view.Choreographer r0 = android.view.Choreographer.getInstance()
            r4.choreographer = r0
            long r0 = androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.frameIntervalNs
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L3f
            android.view.Display r4 = r5.getDisplay()
            boolean r5 = r5.isInEditMode()
            if (r5 != 0) goto L35
            if (r4 == 0) goto L35
            float r4 = r4.getRefreshRate()
            r5 = 1106247680(0x41f00000, float:30.0)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 < 0) goto L35
            goto L37
        L35:
            r4 = 1114636288(0x42700000, float:60.0)
        L37:
            r5 = 1000000000(0x3b9aca00, float:0.0047237873)
            float r5 = (float) r5
            float r5 = r5 / r4
            long r4 = (long) r5
            androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.frameIntervalNs = r4
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.<init>(android.view.View):void");
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        if (this.isActive) {
            this.frameStartTimeNanos = j;
            this.view.post(this);
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        this.isActive = false;
        this.view.removeCallbacks(this);
        this.choreographer.removeFrameCallback(this);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
        this.isActive = true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.prefetchRequests.size == 0 || !this.prefetchScheduled || !this.isActive || this.view.getWindowVisibility() != 0) {
            this.prefetchScheduled = false;
            return;
        }
        PrefetchRequestScopeImpl prefetchRequestScopeImpl = new PrefetchRequestScopeImpl(this.frameStartTimeNanos + frameIntervalNs);
        boolean z = false;
        while (this.prefetchRequests.size != 0 && !z) {
            if (prefetchRequestScopeImpl.availableTimeNanos() <= 0 || ((PrefetchHandleProvider.HandleAndRequestImpl) ((PrefetchRequest) this.prefetchRequests.content[0])).execute(prefetchRequestScopeImpl)) {
                z = true;
            } else {
                this.prefetchRequests.removeAt(0);
            }
        }
        if (z) {
            this.choreographer.postFrameCallback(this);
        } else {
            this.prefetchScheduled = false;
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.PrefetchScheduler
    public final void schedulePrefetch(PrefetchRequest prefetchRequest) {
        this.prefetchRequests.add(prefetchRequest);
        if (this.prefetchScheduled) {
            return;
        }
        this.prefetchScheduled = true;
        this.view.post(this);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
    }
}
