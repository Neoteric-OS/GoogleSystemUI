package kotlinx.coroutines;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class YieldKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0086 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object yield(kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            kotlin.coroutines.CoroutineContext r0 = r6.getContext()
            kotlinx.coroutines.JobKt.ensureActive(r0)
            kotlin.coroutines.Continuation r6 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r6)
            boolean r1 = r6 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            r2 = 0
            if (r1 == 0) goto L13
            kotlinx.coroutines.internal.DispatchedContinuation r6 = (kotlinx.coroutines.internal.DispatchedContinuation) r6
            goto L14
        L13:
            r6 = r2
        L14:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            if (r6 != 0) goto L1a
        L18:
            r6 = r1
            goto L82
        L1a:
            kotlinx.coroutines.CoroutineDispatcher r3 = r6.dispatcher
            boolean r3 = r3.isDispatchNeeded()
            r4 = 1
            if (r3 == 0) goto L2d
            r6._state = r1
            r6.resumeMode = r4
            kotlinx.coroutines.CoroutineDispatcher r2 = r6.dispatcher
            r2.dispatchYield(r0, r6)
            goto L80
        L2d:
            kotlinx.coroutines.YieldContext r3 = new kotlinx.coroutines.YieldContext
            kotlinx.coroutines.YieldContext$Key r5 = kotlinx.coroutines.YieldContext.Key
            r3.<init>(r5)
            kotlin.coroutines.CoroutineContext r0 = r0.plus(r3)
            r6._state = r1
            r6.resumeMode = r4
            kotlinx.coroutines.CoroutineDispatcher r5 = r6.dispatcher
            r5.dispatchYield(r0, r6)
            boolean r0 = r3.dispatcherWasUnconfined
            if (r0 == 0) goto L80
            kotlinx.coroutines.EventLoopImplBase r0 = kotlinx.coroutines.ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            kotlin.collections.ArrayDeque r3 = r0.unconfinedQueue
            if (r3 == 0) goto L52
            boolean r3 = r3.isEmpty()
            goto L53
        L52:
            r3 = r4
        L53:
            if (r3 == 0) goto L56
            goto L18
        L56:
            boolean r3 = r0.isUnconfinedLoopActive()
            if (r3 == 0) goto L66
            r6._state = r1
            r6.resumeMode = r4
            r0.dispatchUnconfined(r6)
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            goto L82
        L66:
            r0.incrementUseCount(r4)
            r6.run()     // Catch: java.lang.Throwable -> L76
        L6c:
            boolean r3 = r0.processUnconfinedEvent()     // Catch: java.lang.Throwable -> L76
            if (r3 != 0) goto L6c
        L72:
            r0.decrementUseCount(r4)
            goto L18
        L76:
            r3 = move-exception
            r6.handleFatalException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(r3, r2)     // Catch: java.lang.Throwable -> L7b
            goto L72
        L7b:
            r6 = move-exception
            r0.decrementUseCount(r4)
            throw r6
        L80:
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
        L82:
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r6 != r0) goto L87
            return r6
        L87:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.YieldKt.yield(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
