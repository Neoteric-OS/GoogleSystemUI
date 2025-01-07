package com.android.wm.shell.bubbles;

import android.util.SparseArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BubbleDataRepository$persistToDisk$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SparseArray $entitiesByUser;
    final /* synthetic */ Job $prev;
    int label;
    final /* synthetic */ BubbleDataRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BubbleDataRepository$persistToDisk$1(Job job, BubbleDataRepository bubbleDataRepository, SparseArray sparseArray, Continuation continuation) {
        super(2, continuation);
        this.$prev = job;
        this.this$0 = bubbleDataRepository;
        this.$entitiesByUser = sparseArray;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BubbleDataRepository$persistToDisk$1(this.$prev, this.this$0, this.$entitiesByUser, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BubbleDataRepository$persistToDisk$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r4.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 == r3) goto L18
            if (r1 != r2) goto L10
            kotlin.ResultKt.throwOnFailure(r5)
            goto L35
        L10:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L18:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L2c
        L1c:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.Job r5 = r4.$prev
            if (r5 == 0) goto L2c
            r4.label = r3
            java.lang.Object r5 = kotlinx.coroutines.JobKt.cancelAndJoin(r5, r4)
            if (r5 != r0) goto L2c
            return r0
        L2c:
            r4.label = r2
            java.lang.Object r5 = kotlinx.coroutines.YieldKt.yield(r4)
            if (r5 != r0) goto L35
            return r0
        L35:
            com.android.wm.shell.bubbles.BubbleDataRepository r5 = r4.this$0
            com.android.wm.shell.bubbles.storage.BubblePersistentRepository r5 = r5.persistentRepository
            android.util.SparseArray r4 = r4.$entitiesByUser
            android.util.AtomicFile r0 = r5.bubbleFile
            monitor-enter(r0)
            android.util.AtomicFile r1 = r5.bubbleFile     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L62
            java.io.FileOutputStream r1 = r1.startWrite()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L62
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L62
            com.android.wm.shell.bubbles.storage.BubbleXmlHelperKt.writeXml(r1, r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            android.util.AtomicFile r4 = r5.bubbleFile     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r4.finishWrite(r1)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            monitor-exit(r0)
            goto L6b
        L51:
            r4 = move-exception
            goto L6e
        L53:
            r4 = move-exception
            java.lang.String r2 = "BubblePersistentRepository"
            java.lang.String r3 = "Failed to save bubble file, restoring backup"
            android.util.Log.e(r2, r3, r4)     // Catch: java.lang.Throwable -> L51
            android.util.AtomicFile r4 = r5.bubbleFile     // Catch: java.lang.Throwable -> L51
            r4.failWrite(r1)     // Catch: java.lang.Throwable -> L51
            monitor-exit(r0)
            goto L6b
        L62:
            r4 = move-exception
            java.lang.String r5 = "BubblePersistentRepository"
            java.lang.String r1 = "Failed to save bubble file"
            android.util.Log.e(r5, r1, r4)     // Catch: java.lang.Throwable -> L51
            monitor-exit(r0)
        L6b:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L6e:
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleDataRepository$persistToDisk$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
