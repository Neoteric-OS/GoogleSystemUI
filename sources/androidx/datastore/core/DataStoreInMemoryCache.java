package androidx.datastore.core;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DataStoreInMemoryCache {
    public final StateFlowImpl cachedValue = StateFlowKt.MutableStateFlow(UnInitialized.INSTANCE);

    public final State getCurrentState() {
        return (State) this.cachedValue.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        if (r6.version > r2.version) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void tryUpdate(androidx.datastore.core.State r6) {
        /*
            r5 = this;
        L0:
            kotlinx.coroutines.flow.StateFlowImpl r0 = r5.cachedValue
            java.lang.Object r1 = r0.getValue()
            r2 = r1
            androidx.datastore.core.State r2 = (androidx.datastore.core.State) r2
            boolean r3 = r2 instanceof androidx.datastore.core.ReadException
            if (r3 == 0) goto Lf
            r3 = 1
            goto L15
        Lf:
            androidx.datastore.core.UnInitialized r3 = androidx.datastore.core.UnInitialized.INSTANCE
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
        L15:
            if (r3 == 0) goto L18
            goto L22
        L18:
            boolean r3 = r2 instanceof androidx.datastore.core.Data
            if (r3 == 0) goto L24
            int r3 = r2.version
            int r4 = r6.version
            if (r4 <= r3) goto L28
        L22:
            r2 = r6
            goto L28
        L24:
            boolean r3 = r2 instanceof androidx.datastore.core.Final
            if (r3 == 0) goto L2f
        L28:
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L0
            return
        L2f:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreInMemoryCache.tryUpdate(androidx.datastore.core.State):void");
    }
}
