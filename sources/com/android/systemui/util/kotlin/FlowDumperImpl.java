package com.android.systemui.util.kotlin;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FlowDumperImpl extends SimpleFlowDumper {
    public final DumpManager dumpManager;
    public final String dumpManagerName = GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("[", Integer.toHexString(System.identityHashCode(this)), "] ", getClass().getSimpleName());
    public final AtomicBoolean registered = new AtomicBoolean(false);

    public FlowDumperImpl(DumpManager dumpManager) {
        this.dumpManager = dumpManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    @Override // com.android.systemui.util.kotlin.SimpleFlowDumper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMapKeysChanged(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto Lb
            java.util.concurrent.atomic.AtomicBoolean r3 = r2.registered
            boolean r3 = r3.get()
            if (r3 == 0) goto Lb
            goto L46
        Lb:
            java.util.concurrent.atomic.AtomicBoolean r3 = r2.registered
            monitor-enter(r3)
            java.util.concurrent.ConcurrentHashMap r0 = r2.stateFlowMap     // Catch: java.lang.Throwable -> L3c
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L29
            java.util.concurrent.ConcurrentHashMap r0 = r2.sharedFlowMap     // Catch: java.lang.Throwable -> L3c
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L29
            java.util.concurrent.ConcurrentHashMap r0 = r2.flowCollectionMap     // Catch: java.lang.Throwable -> L3c
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L3c
            if (r0 != 0) goto L27
            goto L29
        L27:
            r0 = 0
            goto L2a
        L29:
            r0 = 1
        L2a:
            java.util.concurrent.atomic.AtomicBoolean r1 = r2.registered     // Catch: java.lang.Throwable -> L3c
            boolean r1 = r1.getAndSet(r0)     // Catch: java.lang.Throwable -> L3c
            if (r1 == r0) goto L45
            if (r0 == 0) goto L3e
            com.android.systemui.dump.DumpManager r0 = r2.dumpManager     // Catch: java.lang.Throwable -> L3c
            java.lang.String r1 = r2.dumpManagerName     // Catch: java.lang.Throwable -> L3c
            r0.registerCriticalDumpable(r1, r2)     // Catch: java.lang.Throwable -> L3c
            goto L45
        L3c:
            r2 = move-exception
            goto L47
        L3e:
            com.android.systemui.dump.DumpManager r0 = r2.dumpManager     // Catch: java.lang.Throwable -> L3c
            java.lang.String r2 = r2.dumpManagerName     // Catch: java.lang.Throwable -> L3c
            r0.unregisterDumpable(r2)     // Catch: java.lang.Throwable -> L3c
        L45:
            monitor-exit(r3)
        L46:
            return
        L47:
            monitor-exit(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.kotlin.FlowDumperImpl.onMapKeysChanged(boolean):void");
    }
}
