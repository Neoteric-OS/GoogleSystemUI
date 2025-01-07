package kotlinx.coroutines.channels;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChannelSegment extends Segment {
    public final BufferedChannel _channel;
    public final AtomicArray data;

    public ChannelSegment(long j, ChannelSegment channelSegment, BufferedChannel bufferedChannel, int i) {
        super(j, channelSegment, i);
        this._channel = bufferedChannel;
        this.data = new AtomicArray(BufferedChannelKt.SEGMENT_SIZE * 2);
    }

    public final boolean casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(int i, Object obj, Object obj2) {
        AtomicRef atomicRef = this.data.array[(i * 2) + 1];
        atomicRef.getClass();
        return AtomicRef.FU.compareAndSet(atomicRef, obj, obj2);
    }

    public final Object getElement$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(int i) {
        return this.data.array[i * 2].value;
    }

    @Override // kotlinx.coroutines.internal.Segment
    public final int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final Object getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(int i) {
        return this.data.array[(i * 2) + 1].value;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0057, code lost:
    
        setElementLazy(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x005a, code lost:
    
        if (r1 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x005c, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNull(r4);
        r6 = r4.onUndeliveredElement;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0061, code lost:
    
        if (r6 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0063, code lost:
    
        r6 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r6, r0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0067, code lost:
    
        if (r6 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0069, code lost:
    
        kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r6, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x006c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
    
        return;
     */
    @Override // kotlinx.coroutines.internal.Segment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCancellation(int r7, kotlin.coroutines.CoroutineContext r8) {
        /*
            r6 = this;
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            if (r7 < r0) goto L6
            r1 = 1
            goto L7
        L6:
            r1 = 0
        L7:
            if (r1 == 0) goto La
            int r7 = r7 - r0
        La:
            java.lang.Object r0 = r6.getElement$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(r7)
        Le:
            java.lang.Object r2 = r6.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(r7)
            boolean r3 = r2 instanceof kotlinx.coroutines.Waiter
            kotlinx.coroutines.channels.BufferedChannel r4 = r6._channel
            r5 = 0
            if (r3 != 0) goto L6d
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r3 == 0) goto L1e
            goto L6d
        L1e:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r2 == r3) goto L57
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            if (r2 != r3) goto L27
            goto L57
        L27:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r2 == r3) goto Le
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r2 != r3) goto L30
            goto Le
        L30:
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r2 == r6) goto L56
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r2 != r6) goto L39
            goto L56
        L39:
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r2 != r6) goto L3e
            return
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "unexpected state: "
            r7.<init>(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L56:
            return
        L57:
            r6.setElementLazy(r7, r5)
            if (r1 == 0) goto L6c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            kotlin.jvm.functions.Function1 r6 = r4.onUndeliveredElement
            if (r6 == 0) goto L6c
            kotlinx.coroutines.internal.UndeliveredElementException r6 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r6, r0, r5)
            if (r6 == 0) goto L6c
            kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r6, r8)
        L6c:
            return
        L6d:
            if (r1 == 0) goto L72
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            goto L74
        L72:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
        L74:
            boolean r2 = r6.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(r7, r2, r3)
            if (r2 == 0) goto Le
            r6.setElementLazy(r7, r5)
            r2 = r1 ^ 1
            r6.onCancelledRequest(r7, r2)
            if (r1 == 0) goto L94
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            kotlin.jvm.functions.Function1 r6 = r4.onUndeliveredElement
            if (r6 == 0) goto L94
            kotlinx.coroutines.internal.UndeliveredElementException r6 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r6, r0, r5)
            if (r6 == 0) goto L94
            kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r6, r8)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelSegment.onCancellation(int, kotlin.coroutines.CoroutineContext):void");
    }

    public final void onCancelledRequest(int i, boolean z) {
        if (z) {
            BufferedChannel bufferedChannel = this._channel;
            Intrinsics.checkNotNull(bufferedChannel);
            bufferedChannel.waitExpandBufferCompletion$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host((this.id * BufferedChannelKt.SEGMENT_SIZE) + i);
        }
        onSlotCleaned();
    }

    public final void setElementLazy(int i, Object obj) {
        this.data.array[i * 2].lazySet(obj);
    }

    public final void setState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(int i, Symbol symbol) {
        this.data.array[(i * 2) + 1].value = symbol;
    }
}
