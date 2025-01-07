package androidx.compose.ui.platform;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class GlobalSnapshotManager$ensureStarted$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Channel $channel;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobalSnapshotManager$ensureStarted$1(Channel channel, Continuation continuation) {
        super(2, continuation);
        this.$channel = channel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GlobalSnapshotManager$ensureStarted$1(this.$channel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GlobalSnapshotManager$ensureStarted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003f A[Catch: all -> 0x0016, TRY_LEAVE, TryCatch #1 {all -> 0x0016, blocks: (B:6:0x0012, B:7:0x0037, B:9:0x003f, B:10:0x002a, B:20:0x0025), top: B:2:0x0006 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0034 -> B:7:0x0037). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 != r3) goto L18
            java.lang.Object r1 = r6.L$1
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
            java.lang.Object r4 = r6.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L16
            goto L37
        L16:
            r6 = move-exception
            goto L55
        L18:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L20:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.Channel r4 = r6.$channel
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r7 = r4.iterator()     // Catch: java.lang.Throwable -> L16
            r1 = r7
        L2a:
            r6.L$0 = r4     // Catch: java.lang.Throwable -> L16
            r6.L$1 = r1     // Catch: java.lang.Throwable -> L16
            r6.label = r3     // Catch: java.lang.Throwable -> L16
            java.lang.Object r7 = r1.hasNext(r6)     // Catch: java.lang.Throwable -> L16
            if (r7 != r0) goto L37
            return r0
        L37:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L16
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L16
            if (r7 == 0) goto L4f
            java.lang.Object r7 = r1.next()     // Catch: java.lang.Throwable -> L16
            kotlin.Unit r7 = (kotlin.Unit) r7     // Catch: java.lang.Throwable -> L16
            java.util.concurrent.atomic.AtomicBoolean r7 = androidx.compose.ui.platform.GlobalSnapshotManager.sent     // Catch: java.lang.Throwable -> L16
            r5 = 0
            r7.set(r5)     // Catch: java.lang.Throwable -> L16
            androidx.compose.runtime.snapshots.Snapshot.Companion.sendApplyNotifications()     // Catch: java.lang.Throwable -> L16
            goto L2a
        L4f:
            r4.cancel(r2)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L55:
            throw r6     // Catch: java.lang.Throwable -> L56
        L56:
            r7 = move-exception
            boolean r0 = r6 instanceof java.util.concurrent.CancellationException
            if (r0 == 0) goto L5e
            r2 = r6
            java.util.concurrent.CancellationException r2 = (java.util.concurrent.CancellationException) r2
        L5e:
            if (r2 != 0) goto L66
            java.lang.String r0 = "Channel was consumed, consumer had failed"
            java.util.concurrent.CancellationException r2 = kotlinx.coroutines.ExceptionsKt.CancellationException(r0, r6)
        L66:
            r4.cancel(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
