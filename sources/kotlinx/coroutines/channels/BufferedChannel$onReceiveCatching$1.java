package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class BufferedChannel$onReceiveCatching$1 extends FunctionReferenceImpl implements Function3 {
    public static final BufferedChannel$onReceiveCatching$1 INSTANCE = new BufferedChannel$onReceiveCatching$1();

    public BufferedChannel$onReceiveCatching$1() {
        super(3, BufferedChannel.class, "registerSelectForReceive", "registerSelectForReceive(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    @Override // kotlin.jvm.functions.Function3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r9, java.lang.Object r10, java.lang.Object r11) {
        /*
            r8 = this;
            kotlinx.coroutines.channels.BufferedChannel r9 = (kotlinx.coroutines.channels.BufferedChannel) r9
            kotlinx.coroutines.selects.SelectInstance r10 = (kotlinx.coroutines.selects.SelectInstance) r10
            kotlinx.atomicfu.AtomicRef r8 = r9.receiveSegment
            java.lang.Object r8 = r8.value
            kotlinx.coroutines.channels.ChannelSegment r8 = (kotlinx.coroutines.channels.ChannelSegment) r8
        La:
            boolean r11 = r9.isClosedForReceive()
            if (r11 == 0) goto L17
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            kotlinx.coroutines.selects.SelectImplementation r10 = (kotlinx.coroutines.selects.SelectImplementation) r10
            r10.internalResult = r8
            goto L6e
        L17:
            kotlinx.atomicfu.AtomicLong r11 = r9.receivers
            r11.getClass()
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.atomicfu.AtomicLong.FU
            long r6 = r0.getAndIncrement(r11)
            int r11 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r11
            long r2 = r6 / r0
            long r0 = r6 % r0
            int r11 = (int) r0
            long r0 = r8.id
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L38
            kotlinx.coroutines.channels.ChannelSegment r0 = r9.findSegmentReceive(r2, r8)
            if (r0 != 0) goto L37
            goto La
        L37:
            r8 = r0
        L38:
            r0 = r9
            r1 = r8
            r2 = r11
            r3 = r6
            r5 = r10
            java.lang.Object r0 = r0.updateCellReceive(r1, r2, r3, r5)
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND
            if (r0 != r1) goto L53
            boolean r9 = r10 instanceof kotlinx.coroutines.Waiter
            if (r9 == 0) goto L4c
            kotlinx.coroutines.Waiter r10 = (kotlinx.coroutines.Waiter) r10
            goto L4d
        L4c:
            r10 = 0
        L4d:
            if (r10 == 0) goto L6e
            r10.invokeOnCancellation(r8, r11)
            goto L6e
        L53:
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            if (r0 != r11) goto L63
            long r0 = r9.getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            int r11 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r11 >= 0) goto La
            r8.cleanPrev()
            goto La
        L63:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER
            if (r0 == r9) goto L71
            r8.cleanPrev()
            kotlinx.coroutines.selects.SelectImplementation r10 = (kotlinx.coroutines.selects.SelectImplementation) r10
            r10.internalResult = r0
        L6e:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L71:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "unexpected"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel$onReceiveCatching$1.invoke(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
