package androidx.datastore.core;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RunOnce {
    public final MutexImpl runMutex = MutexKt.Mutex$default();
    public final CompletableDeferredImpl didRun = CompletableDeferredKt.CompletableDeferred$default();

    public abstract Object doRun(ContinuationImpl continuationImpl);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[Catch: all -> 0x0090, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0090, blocks: (B:25:0x0068, B:29:0x0074), top: B:24:0x0068 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r8v9, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runIfNeeded(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.datastore.core.RunOnce$runIfNeeded$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.datastore.core.RunOnce$runIfNeeded$1 r0 = (androidx.datastore.core.RunOnce$runIfNeeded$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.datastore.core.RunOnce$runIfNeeded$1 r0 = new androidx.datastore.core.RunOnce$runIfNeeded$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L4d
            if (r2 == r5) goto L3f
            if (r2 != r4) goto L37
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.RunOnce r0 = (androidx.datastore.core.RunOnce) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            goto L83
        L35:
            r9 = move-exception
            goto L92
        L37:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3f:
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.RunOnce r2 = (androidx.datastore.core.RunOnce) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r8
            r8 = r2
            goto L68
        L4d:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.CompletableDeferredImpl r9 = r8.didRun
            boolean r9 = r9.isCompleted()
            if (r9 == 0) goto L59
            return r3
        L59:
            r0.L$0 = r8
            kotlinx.coroutines.sync.MutexImpl r9 = r8.runMutex
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r2 = r9.lock(r0)
            if (r2 != r1) goto L68
            return r1
        L68:
            kotlinx.coroutines.CompletableDeferredImpl r2 = r8.didRun     // Catch: java.lang.Throwable -> L90
            boolean r2 = r2.isCompleted()     // Catch: java.lang.Throwable -> L90
            if (r2 == 0) goto L74
            r9.unlock(r6)
            return r3
        L74:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L90
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L90
            r0.label = r4     // Catch: java.lang.Throwable -> L90
            java.lang.Object r0 = r8.doRun(r0)     // Catch: java.lang.Throwable -> L90
            if (r0 != r1) goto L81
            return r1
        L81:
            r0 = r8
            r8 = r9
        L83:
            kotlinx.coroutines.CompletableDeferredImpl r9 = r0.didRun     // Catch: java.lang.Throwable -> L35
            r9.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(r3)     // Catch: java.lang.Throwable -> L35
            r8.unlock(r6)
            return r3
        L8c:
            r7 = r9
            r9 = r8
            r8 = r7
            goto L92
        L90:
            r8 = move-exception
            goto L8c
        L92:
            r8.unlock(r6)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.RunOnce.runIfNeeded(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
