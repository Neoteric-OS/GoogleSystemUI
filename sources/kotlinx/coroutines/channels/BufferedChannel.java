package kotlinx.coroutines.channels;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BufferedChannel implements Channel {
    public final AtomicRef _closeCause;
    public final AtomicLong bufferEnd;
    public final AtomicRef bufferEndSegment;
    public final int capacity;
    public final AtomicRef closeHandler;
    public final AtomicLong completedExpandBuffersAndPauseFlag;
    public final Function1 onUndeliveredElement;
    public final Function3 onUndeliveredElementReceiveCancellationConstructor;
    public final AtomicRef receiveSegment;
    public final AtomicLong receivers;
    public final AtomicRef sendSegment;
    public final AtomicLong sendersAndCloseStatus;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BufferedChannelIterator implements Waiter {
        public CancellableContinuationImpl continuation;
        public Object receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;

        public BufferedChannelIterator() {
        }

        public final Object hasNext(ContinuationImpl continuationImpl) {
            ChannelSegment channelSegment;
            Boolean bool;
            ChannelSegment channelSegment2;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            ChannelSegment channelSegment3 = (ChannelSegment) bufferedChannel.receiveSegment.value;
            while (!bufferedChannel.isClosedForReceive()) {
                AtomicLong atomicLong = bufferedChannel.receivers;
                atomicLong.getClass();
                long andIncrement = AtomicLong.FU.getAndIncrement(atomicLong);
                long j = BufferedChannelKt.SEGMENT_SIZE;
                long j2 = andIncrement / j;
                int i = (int) (andIncrement % j);
                if (channelSegment3.id != j2) {
                    ChannelSegment findSegmentReceive = bufferedChannel.findSegmentReceive(j2, channelSegment3);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                } else {
                    channelSegment = channelSegment3;
                }
                Object updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i, andIncrement, null);
                Symbol symbol = BufferedChannelKt.SUSPEND;
                if (updateCellReceive == symbol) {
                    throw new IllegalStateException("unreachable");
                }
                Symbol symbol2 = BufferedChannelKt.FAILED;
                if (updateCellReceive != symbol2) {
                    if (updateCellReceive != BufferedChannelKt.SUSPEND_NO_WAITER) {
                        channelSegment.cleanPrev();
                        this.receiveResult = updateCellReceive;
                        return Boolean.TRUE;
                    }
                    BufferedChannel bufferedChannel2 = BufferedChannel.this;
                    CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuationImpl));
                    try {
                        this.continuation = orCreateCancellableContinuation;
                        Object updateCellReceive2 = bufferedChannel2.updateCellReceive(channelSegment, i, andIncrement, this);
                        if (updateCellReceive2 == symbol) {
                            invokeOnCancellation(channelSegment, i);
                        } else {
                            Function1 function1 = null;
                            if (updateCellReceive2 == symbol2) {
                                if (andIncrement < bufferedChannel2.getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                    channelSegment.cleanPrev();
                                }
                                ChannelSegment channelSegment4 = (ChannelSegment) bufferedChannel2.receiveSegment.value;
                                while (true) {
                                    if (bufferedChannel2.isClosedForReceive()) {
                                        CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
                                        Intrinsics.checkNotNull(cancellableContinuationImpl);
                                        this.continuation = null;
                                        this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
                                        Throwable closeCause = BufferedChannel.this.getCloseCause();
                                        if (closeCause == null) {
                                            cancellableContinuationImpl.resumeWith(Boolean.FALSE);
                                        } else {
                                            cancellableContinuationImpl.resumeWith(new Result.Failure(closeCause));
                                        }
                                    } else {
                                        AtomicLong atomicLong2 = bufferedChannel2.receivers;
                                        atomicLong2.getClass();
                                        long andIncrement2 = AtomicLong.FU.getAndIncrement(atomicLong2);
                                        long j3 = BufferedChannelKt.SEGMENT_SIZE;
                                        long j4 = andIncrement2 / j3;
                                        int i2 = (int) (andIncrement2 % j3);
                                        if (channelSegment4.id != j4) {
                                            ChannelSegment findSegmentReceive2 = bufferedChannel2.findSegmentReceive(j4, channelSegment4);
                                            if (findSegmentReceive2 != null) {
                                                channelSegment2 = findSegmentReceive2;
                                            }
                                        } else {
                                            channelSegment2 = channelSegment4;
                                        }
                                        Object updateCellReceive3 = bufferedChannel2.updateCellReceive(channelSegment2, i2, andIncrement2, this);
                                        if (updateCellReceive3 == BufferedChannelKt.SUSPEND) {
                                            invokeOnCancellation(channelSegment2, i2);
                                            break;
                                        }
                                        if (updateCellReceive3 == BufferedChannelKt.FAILED) {
                                            if (andIncrement2 < bufferedChannel2.getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                                channelSegment2.cleanPrev();
                                            }
                                            channelSegment4 = channelSegment2;
                                        } else {
                                            if (updateCellReceive3 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                throw new IllegalStateException("unexpected");
                                            }
                                            channelSegment2.cleanPrev();
                                            this.receiveResult = updateCellReceive3;
                                            this.continuation = null;
                                            bool = Boolean.TRUE;
                                            Function1 function12 = bufferedChannel2.onUndeliveredElement;
                                            if (function12 != null) {
                                                function1 = OnUndeliveredElementKt.bindCancellationFun(function12, updateCellReceive3, orCreateCancellableContinuation.context);
                                            }
                                        }
                                    }
                                }
                            } else {
                                channelSegment.cleanPrev();
                                this.receiveResult = updateCellReceive2;
                                this.continuation = null;
                                bool = Boolean.TRUE;
                                Function1 function13 = bufferedChannel2.onUndeliveredElement;
                                if (function13 != null) {
                                    function1 = OnUndeliveredElementKt.bindCancellationFun(function13, updateCellReceive2, orCreateCancellableContinuation.context);
                                }
                            }
                            orCreateCancellableContinuation.resume(bool, function1);
                        }
                        Object result = orCreateCancellableContinuation.getResult();
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        return result;
                    } catch (Throwable th) {
                        orCreateCancellableContinuation.releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                        throw th;
                    }
                }
                if (andIncrement < bufferedChannel.getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                    channelSegment.cleanPrev();
                }
                channelSegment3 = channelSegment;
            }
            this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
            Throwable closeCause2 = BufferedChannel.this.getCloseCause();
            if (closeCause2 == null) {
                return Boolean.FALSE;
            }
            int i3 = StackTraceRecoveryKt.$r8$clinit;
            throw closeCause2;
        }

        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation(Segment segment, int i) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, i);
            }
        }

        public final Object next() {
            Object obj = this.receiveResult;
            Symbol symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (obj == symbol) {
                throw new IllegalStateException("`hasNext()` has not been invoked");
            }
            this.receiveResult = symbol;
            if (obj != BufferedChannelKt.CHANNEL_CLOSED) {
                return obj;
            }
            Throwable receiveException = BufferedChannel.this.getReceiveException();
            int i = StackTraceRecoveryKt.$r8$clinit;
            throw receiveException;
        }
    }

    public BufferedChannel(int i, Function1 function1) {
        this.capacity = i;
        this.onUndeliveredElement = function1;
        if (i < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Invalid channel capacity: ", ", should be >=0", i).toString());
        }
        this.sendersAndCloseStatus = AtomicFU.atomic(0L);
        this.receivers = AtomicFU.atomic(0L);
        ChannelSegment channelSegment = BufferedChannelKt.NULL_SEGMENT;
        AtomicLong atomic = AtomicFU.atomic(i != 0 ? i != Integer.MAX_VALUE ? i : Long.MAX_VALUE : 0L);
        this.bufferEnd = atomic;
        this.completedExpandBuffersAndPauseFlag = AtomicFU.atomic(atomic.value);
        ChannelSegment channelSegment2 = new ChannelSegment(0L, null, this, 3);
        this.sendSegment = AtomicFU.atomic(channelSegment2);
        this.receiveSegment = AtomicFU.atomic(channelSegment2);
        this.bufferEndSegment = AtomicFU.atomic(isRendezvousOrUnlimited() ? BufferedChannelKt.NULL_SEGMENT : channelSegment2);
        this.onUndeliveredElementReceiveCancellationConstructor = function1 != null ? new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, final Object obj3) {
                final SelectInstance selectInstance = (SelectInstance) obj;
                final BufferedChannel bufferedChannel = BufferedChannel.this;
                return new Function1() { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        Object obj5 = obj3;
                        if (obj5 != BufferedChannelKt.CHANNEL_CLOSED) {
                            Function1 function12 = bufferedChannel.onUndeliveredElement;
                            CoroutineContext coroutineContext = ((SelectImplementation) selectInstance).context;
                            UndeliveredElementException callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function12, obj5, null);
                            if (callUndeliveredElementCatchingException != null) {
                                CoroutineExceptionHandlerKt.handleCoroutineException(callUndeliveredElementCatchingException, coroutineContext);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
            }
        } : null;
        this._closeCause = AtomicFU.atomic(BufferedChannelKt.NO_CLOSE_CAUSE);
        this.closeHandler = AtomicFU.atomic((Object) null);
    }

    public static final ChannelSegment access$findSegmentSend(BufferedChannel bufferedChannel, long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        long j2;
        long j3;
        AtomicLong atomicLong;
        AtomicRef atomicRef = bufferedChannel.sendSegment;
        ChannelSegment channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, bufferedChannelKt$createSegmentFunction$1);
            if (!SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
                Segment m1794getSegmentimpl = SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
                while (true) {
                    Segment segment = (Segment) atomicRef.value;
                    if (segment.id >= m1794getSegmentimpl.id) {
                        break loop0;
                    }
                    if (!m1794getSegmentimpl.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                        break;
                    }
                    if (AtomicRef.FU.compareAndSet(atomicRef, segment, m1794getSegmentimpl)) {
                        if (segment.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                            segment.remove();
                        }
                    } else if (m1794getSegmentimpl.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                        m1794getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        }
        if (SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
            bufferedChannel.isClosedForSend();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= bufferedChannel.receivers.value) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
        long j4 = channelSegment3.id;
        if (j4 <= j) {
            return channelSegment3;
        }
        long j5 = j4 * BufferedChannelKt.SEGMENT_SIZE;
        AtomicLong atomicLong2 = bufferedChannel.sendersAndCloseStatus;
        do {
            j2 = atomicLong2.value;
            long j6 = 1152921504606846975L & j2;
            if (j6 >= j5) {
                break;
            }
            j3 = (((int) (j2 >> 60)) << 60) + j6;
            atomicLong = bufferedChannel.sendersAndCloseStatus;
            atomicLong.getClass();
        } while (!AtomicLong.FU.compareAndSet(atomicLong, j2, j3));
        if (channelSegment3.id * BufferedChannelKt.SEGMENT_SIZE >= bufferedChannel.receivers.value) {
            return null;
        }
        channelSegment3.cleanPrev();
        return null;
    }

    public static final void access$onClosedSendOnNoWaiterSuspend(BufferedChannel bufferedChannel, Object obj, CancellableContinuationImpl cancellableContinuationImpl) {
        Function1 function1 = bufferedChannel.onUndeliveredElement;
        if (function1 != null) {
            CoroutineContext coroutineContext = cancellableContinuationImpl.context;
            UndeliveredElementException callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, null);
            if (callUndeliveredElementCatchingException != null) {
                CoroutineExceptionHandlerKt.handleCoroutineException(callUndeliveredElementCatchingException, coroutineContext);
            }
        }
        cancellableContinuationImpl.resumeWith(new Result.Failure(bufferedChannel.getSendException()));
    }

    public static final int access$updateCellSend(BufferedChannel bufferedChannel, ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        bufferedChannel.getClass();
        channelSegment.setElementLazy(i, obj);
        if (z) {
            return bufferedChannel.updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
        }
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = channelSegment.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null) {
            if (bufferedChannel.bufferOrRendezvousSend(j)) {
                if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, null, obj2)) {
                    return 2;
                }
            }
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Waiter) {
            channelSegment.setElementLazy(i, null);
            if (bufferedChannel.tryResumeReceiver(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, obj)) {
                channelSegment.setState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, BufferedChannelKt.DONE_RCV);
                return 0;
            }
            Symbol symbol = BufferedChannelKt.INTERRUPTED_RCV;
            AtomicRef atomicRef = channelSegment.data.array[(i * 2) + 1];
            atomicRef.getClass();
            if (AtomicRef.FU.getAndSet(atomicRef, symbol) != symbol) {
                channelSegment.onCancelledRequest(i, true);
            }
            return 5;
        }
        return bufferedChannel.updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
    }

    public static void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel) {
        AtomicLong atomicLong = bufferedChannel.completedExpandBuffersAndPauseFlag;
        atomicLong.getClass();
        if ((AtomicLong.FU.addAndGet(atomicLong, 1L) & 4611686018427387904L) != 0) {
            while ((bufferedChannel.completedExpandBuffersAndPauseFlag.value & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object m1786receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            if (r0 == 0) goto L14
            r0 = r14
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            r0.<init>(r13, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.channels.ChannelResult r14 = (kotlinx.coroutines.channels.ChannelResult) r14
            java.lang.Object r13 = r14.holder
            goto L9c
        L2e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.atomicfu.AtomicRef r14 = r13.receiveSegment
            java.lang.Object r14 = r14.value
            kotlinx.coroutines.channels.ChannelSegment r14 = (kotlinx.coroutines.channels.ChannelSegment) r14
        L3f:
            boolean r1 = r13.isClosedForReceive()
            if (r1 == 0) goto L4f
            java.lang.Throwable r13 = r13.getCloseCause()
            kotlinx.coroutines.channels.ChannelResult$Closed r14 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r14.<init>(r13)
            goto La2
        L4f:
            kotlinx.atomicfu.AtomicLong r1 = r13.receivers
            r1.getClass()
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = kotlinx.atomicfu.AtomicLong.FU
            long r4 = r3.getAndIncrement(r1)
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r7 = (long) r1
            long r9 = r4 / r7
            long r7 = r4 % r7
            int r3 = (int) r7
            long r7 = r14.id
            int r1 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r1 == 0) goto L70
            kotlinx.coroutines.channels.ChannelSegment r1 = r13.findSegmentReceive(r9, r14)
            if (r1 != 0) goto L6f
            goto L3f
        L6f:
            r14 = r1
        L70:
            r12 = 0
            r7 = r13
            r8 = r14
            r9 = r3
            r10 = r4
            java.lang.Object r1 = r7.updateCellReceive(r8, r9, r10, r12)
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND
            if (r1 == r7) goto La3
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            if (r1 != r7) goto L8d
            long r7 = r13.getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L3f
            r14.cleanPrev()
            goto L3f
        L8d:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER
            if (r1 != r7) goto L9e
            r6.label = r2
            r1 = r13
            r2 = r14
            java.lang.Object r13 = r1.m1788receiveCatchingOnNoWaiterSuspendGKJJFZk(r2, r3, r4, r6)
            if (r13 != r0) goto L9c
            return r0
        L9c:
            r14 = r13
            goto La2
        L9e:
            r14.cleanPrev()
            r14 = r1
        La2:
            return r14
        La3:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m1786receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final boolean bufferOrRendezvousSend(long j) {
        return j < this.bufferEnd.value || j < this.receivers.value + ((long) this.capacity);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        closeOrCancelImpl(cancellationException, true);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        return closeOrCancelImpl(th, false);
    }

    public final boolean closeOrCancelImpl(Throwable th, boolean z) {
        long j;
        long j2;
        long j3;
        Object obj;
        long j4;
        long j5;
        if (z) {
            AtomicLong atomicLong = this.sendersAndCloseStatus;
            do {
                j5 = atomicLong.value;
                if (((int) (j5 >> 60)) != 0) {
                    break;
                }
                ChannelSegment channelSegment = BufferedChannelKt.NULL_SEGMENT;
            } while (!AtomicLong.FU.compareAndSet(atomicLong, j5, (1 << 60) + (j5 & 1152921504606846975L)));
        }
        AtomicRef atomicRef = this._closeCause;
        Symbol symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
        atomicRef.getClass();
        boolean compareAndSet = AtomicRef.FU.compareAndSet(atomicRef, symbol, th);
        if (z) {
            AtomicLong atomicLong2 = this.sendersAndCloseStatus;
            do {
                j4 = atomicLong2.value;
            } while (!AtomicLong.FU.compareAndSet(atomicLong2, j4, (3 << 60) + (j4 & 1152921504606846975L)));
        } else {
            AtomicLong atomicLong3 = this.sendersAndCloseStatus;
            do {
                j = atomicLong3.value;
                int i = (int) (j >> 60);
                if (i == 0) {
                    j2 = j & 1152921504606846975L;
                    j3 = 2;
                } else {
                    if (i != 1) {
                        break;
                    }
                    j2 = j & 1152921504606846975L;
                    j3 = 3;
                }
            } while (!AtomicLong.FU.compareAndSet(atomicLong3, j, (j3 << 60) + j2));
        }
        isClosedForSend();
        if (compareAndSet) {
            AtomicRef atomicRef2 = this.closeHandler;
            do {
                obj = atomicRef2.value;
            } while (!AtomicRef.FU.compareAndSet(atomicRef2, obj, obj == null ? BufferedChannelKt.CLOSE_HANDLER_CLOSED : BufferedChannelKt.CLOSE_HANDLER_INVOKED));
            if (obj != null) {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(1, obj);
                ((Function1) obj).invoke(getCloseCause());
            }
        }
        return compareAndSet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0087, code lost:
    
        r1 = (kotlinx.coroutines.channels.ChannelSegment) ((kotlinx.coroutines.internal.Segment) r1._prev.value);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.channels.ChannelSegment completeClose(long r13) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.completeClose(long):kotlinx.coroutines.channels.ChannelSegment");
    }

    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long j) {
        UndeliveredElementException callUndeliveredElementCatchingException;
        ChannelSegment channelSegment = (ChannelSegment) this.receiveSegment.value;
        while (true) {
            long j2 = this.receivers.value;
            if (j < Math.max(this.capacity + j2, this.bufferEnd.value)) {
                return;
            }
            AtomicLong atomicLong = this.receivers;
            atomicLong.getClass();
            if (AtomicLong.FU.compareAndSet(atomicLong, j2, j2 + 1)) {
                long j3 = BufferedChannelKt.SEGMENT_SIZE;
                long j4 = j2 / j3;
                int i = (int) (j2 % j3);
                if (channelSegment.id != j4) {
                    ChannelSegment findSegmentReceive = findSegmentReceive(j4, channelSegment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                }
                Object updateCellReceive = updateCellReceive(channelSegment, i, j2, null);
                if (updateCellReceive != BufferedChannelKt.FAILED) {
                    channelSegment.cleanPrev();
                    Function1 function1 = this.onUndeliveredElement;
                    if (function1 != null && (callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, updateCellReceive, null)) != null) {
                        throw callUndeliveredElementCatchingException;
                    }
                } else if (j2 < getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                    channelSegment.cleanPrev();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x019a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void expandBuffer() {
        /*
            Method dump skipped, instructions count: 411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.expandBuffer():void");
    }

    public final ChannelSegment findSegmentReceive(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        long j2;
        AtomicLong atomicLong;
        AtomicRef atomicRef = this.receiveSegment;
        ChannelSegment channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, bufferedChannelKt$createSegmentFunction$1);
            if (!SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
                Segment m1794getSegmentimpl = SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
                while (true) {
                    Segment segment = (Segment) atomicRef.value;
                    if (segment.id >= m1794getSegmentimpl.id) {
                        break loop0;
                    }
                    if (!m1794getSegmentimpl.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                        break;
                    }
                    if (AtomicRef.FU.compareAndSet(atomicRef, segment, m1794getSegmentimpl)) {
                        if (segment.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                            segment.remove();
                        }
                    } else if (m1794getSegmentimpl.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                        m1794getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        }
        if (SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
            isClosedForSend();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
        if (!isRendezvousOrUnlimited() && j <= this.bufferEnd.value / BufferedChannelKt.SEGMENT_SIZE) {
            AtomicRef atomicRef2 = this.bufferEndSegment;
            while (true) {
                Segment segment2 = (Segment) atomicRef2.value;
                if (segment2.id >= channelSegment3.id || !channelSegment3.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                    break;
                }
                if (AtomicRef.FU.compareAndSet(atomicRef2, segment2, channelSegment3)) {
                    if (segment2.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                        segment2.remove();
                    }
                } else if (channelSegment3.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                    channelSegment3.remove();
                }
            }
        }
        long j3 = channelSegment3.id;
        if (j3 <= j) {
            return channelSegment3;
        }
        long j4 = j3 * BufferedChannelKt.SEGMENT_SIZE;
        AtomicLong atomicLong2 = this.receivers;
        do {
            j2 = atomicLong2.value;
            if (j2 >= j4) {
                break;
            }
            atomicLong = this.receivers;
            atomicLong.getClass();
        } while (!AtomicLong.FU.compareAndSet(atomicLong, j2, j4));
        if (channelSegment3.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
            return null;
        }
        channelSegment3.cleanPrev();
        return null;
    }

    public final Throwable getCloseCause() {
        return (Throwable) this._closeCause.value;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1Impl getOnReceiveCatching() {
        BufferedChannel$onReceiveCatching$1 bufferedChannel$onReceiveCatching$1 = BufferedChannel$onReceiveCatching$1.INSTANCE;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, bufferedChannel$onReceiveCatching$1);
        BufferedChannel$onReceiveCatching$2 bufferedChannel$onReceiveCatching$2 = BufferedChannel$onReceiveCatching$2.INSTANCE;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, bufferedChannel$onReceiveCatching$2);
        return new SelectClause1Impl(this, bufferedChannel$onReceiveCatching$1, bufferedChannel$onReceiveCatching$2, this.onUndeliveredElementReceiveCancellationConstructor);
    }

    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException("Channel was closed") : closeCause;
    }

    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException("Channel was closed") : closeCause;
    }

    public final long getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this.sendersAndCloseStatus.value & 1152921504606846975L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:91:0x00b8, code lost:
    
        r12 = (kotlinx.coroutines.channels.ChannelSegment) ((kotlinx.coroutines.internal.Segment) r12._prev.value);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isClosed(long r12, boolean r14) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.isClosed(long, boolean):boolean");
    }

    public final boolean isClosedForReceive() {
        return isClosed(this.sendersAndCloseStatus.value, true);
    }

    public final boolean isClosedForSend() {
        return isClosed(this.sendersAndCloseStatus.value, false);
    }

    public boolean isConflatedDropOldest() {
        return false;
    }

    public final boolean isRendezvousOrUnlimited() {
        long j = this.bufferEnd.value;
        return j == 0 || j == Long.MAX_VALUE;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final BufferedChannelIterator iterator() {
        return new BufferedChannelIterator();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void moveSegmentBufferEndToSpecifiedOrLast(long r5, kotlinx.coroutines.channels.ChannelSegment r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.id
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            kotlinx.coroutines.internal.Segment r0 = r7.getNext()
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L22
            kotlinx.coroutines.internal.Segment r5 = r7.getNext()
            kotlinx.coroutines.channels.ChannelSegment r5 = (kotlinx.coroutines.channels.ChannelSegment) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            kotlinx.atomicfu.AtomicRef r5 = r4.bufferEndSegment
        L24:
            java.lang.Object r6 = r5.value
            kotlinx.coroutines.internal.Segment r6 = (kotlinx.coroutines.internal.Segment) r6
            long r0 = r6.id
            long r2 = r7.id
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L31
            goto L49
        L31:
            boolean r0 = r7.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            if (r0 != 0) goto L38
            goto L11
        L38:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.atomicfu.AtomicRef.FU
            boolean r0 = r0.compareAndSet(r5, r6, r7)
            if (r0 == 0) goto L4a
            boolean r4 = r6.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            if (r4 == 0) goto L49
            r6.remove()
        L49:
            return
        L4a:
            boolean r6 = r7.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            if (r6 == 0) goto L24
            r7.remove()
            goto L24
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.moveSegmentBufferEndToSpecifiedOrLast(long, kotlinx.coroutines.channels.ChannelSegment):void");
    }

    public final Object onClosedSend(Object obj, Continuation continuation) {
        UndeliveredElementException callUndeliveredElementCatchingException;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
            cancellableContinuationImpl.resumeWith(new Result.Failure(getSendException()));
        } else {
            ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException, getSendException());
            cancellableContinuationImpl.resumeWith(new Result.Failure(callUndeliveredElementCatchingException));
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(SuspendLambda suspendLambda) {
        ChannelSegment channelSegment = (ChannelSegment) this.receiveSegment.value;
        while (!isClosedForReceive()) {
            AtomicLong atomicLong = this.receivers;
            atomicLong.getClass();
            long andIncrement = AtomicLong.FU.getAndIncrement(atomicLong);
            long j = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = andIncrement / j;
            int i = (int) (andIncrement % j);
            if (channelSegment.id != j2) {
                ChannelSegment findSegmentReceive = findSegmentReceive(j2, channelSegment);
                if (findSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = findSegmentReceive;
                }
            }
            Object updateCellReceive = updateCellReceive(channelSegment, i, andIncrement, null);
            Object obj = BufferedChannelKt.SUSPEND;
            if (updateCellReceive == obj) {
                throw new IllegalStateException("unexpected");
            }
            Object obj2 = BufferedChannelKt.FAILED;
            if (updateCellReceive != obj2) {
                if (updateCellReceive != BufferedChannelKt.SUSPEND_NO_WAITER) {
                    channelSegment.cleanPrev();
                    return updateCellReceive;
                }
                CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(suspendLambda));
                try {
                    Object updateCellReceive2 = updateCellReceive(channelSegment, i, andIncrement, orCreateCancellableContinuation);
                    if (updateCellReceive2 == obj) {
                        orCreateCancellableContinuation.invokeOnCancellation(channelSegment, i);
                    } else {
                        Function1 function1 = null;
                        if (updateCellReceive2 == obj2) {
                            if (andIncrement < getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                channelSegment.cleanPrev();
                            }
                            ChannelSegment channelSegment2 = (ChannelSegment) this.receiveSegment.value;
                            while (true) {
                                if (isClosedForReceive()) {
                                    orCreateCancellableContinuation.resumeWith(new Result.Failure(getReceiveException()));
                                    break;
                                }
                                AtomicLong atomicLong2 = this.receivers;
                                atomicLong2.getClass();
                                long andIncrement2 = AtomicLong.FU.getAndIncrement(atomicLong2);
                                long j3 = BufferedChannelKt.SEGMENT_SIZE;
                                long j4 = andIncrement2 / j3;
                                int i2 = (int) (andIncrement2 % j3);
                                if (channelSegment2.id != j4) {
                                    ChannelSegment findSegmentReceive2 = findSegmentReceive(j4, channelSegment2);
                                    if (findSegmentReceive2 != null) {
                                        channelSegment2 = findSegmentReceive2;
                                    }
                                }
                                updateCellReceive2 = updateCellReceive(channelSegment2, i2, andIncrement2, orCreateCancellableContinuation);
                                if (updateCellReceive2 == BufferedChannelKt.SUSPEND) {
                                    orCreateCancellableContinuation.invokeOnCancellation(channelSegment2, i2);
                                    break;
                                }
                                if (updateCellReceive2 == BufferedChannelKt.FAILED) {
                                    if (andIncrement2 < getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                        channelSegment2.cleanPrev();
                                    }
                                } else {
                                    if (updateCellReceive2 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                        throw new IllegalStateException("unexpected");
                                    }
                                    channelSegment2.cleanPrev();
                                    Function1 function12 = this.onUndeliveredElement;
                                    if (function12 != null) {
                                        function1 = OnUndeliveredElementKt.bindCancellationFun(function12, updateCellReceive2, orCreateCancellableContinuation.context);
                                    }
                                }
                            }
                        } else {
                            channelSegment.cleanPrev();
                            Function1 function13 = this.onUndeliveredElement;
                            if (function13 != null) {
                                function1 = OnUndeliveredElementKt.bindCancellationFun(function13, updateCellReceive2, orCreateCancellableContinuation.context);
                            }
                        }
                        orCreateCancellableContinuation.resume(updateCellReceive2, function1);
                    }
                    Object result = orCreateCancellableContinuation.getResult();
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    return result;
                } catch (Throwable th) {
                    orCreateCancellableContinuation.releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                    throw th;
                }
            }
            if (andIncrement < getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                channelSegment.cleanPrev();
            }
        }
        Throwable receiveException = getReceiveException();
        int i3 = StackTraceRecoveryKt.$r8$clinit;
        throw receiveException;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public final Object mo1787receiveCatchingJP2dKIU(Continuation continuation) {
        return m1786receiveCatchingJP2dKIU$suspendImpl(this, (ContinuationImpl) continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1788receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment r10, int r11, long r12, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m1788receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment, int, long, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void resumeWaiterOnClosedChannel(Waiter waiter, boolean z) {
        if (waiter instanceof CancellableContinuation) {
            ((Continuation) waiter).resumeWith(new Result.Failure(z ? getReceiveException() : getSendException()));
            return;
        }
        if (waiter instanceof ReceiveCatching) {
            ((ReceiveCatching) waiter).cont.resumeWith(new ChannelResult(new ChannelResult.Closed(getCloseCause())));
            return;
        }
        if (!(waiter instanceof BufferedChannelIterator)) {
            if (waiter instanceof SelectInstance) {
                ((SelectImplementation) ((SelectInstance) waiter)).trySelectInternal(this, BufferedChannelKt.CHANNEL_CLOSED);
                return;
            } else {
                throw new IllegalStateException(("Unexpected waiter: " + waiter).toString());
            }
        }
        BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) waiter;
        CancellableContinuationImpl cancellableContinuationImpl = bufferedChannelIterator.continuation;
        Intrinsics.checkNotNull(cancellableContinuationImpl);
        bufferedChannelIterator.continuation = null;
        bufferedChannelIterator.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
        Throwable closeCause = BufferedChannel.this.getCloseCause();
        if (closeCause == null) {
            cancellableContinuationImpl.resumeWith(Boolean.FALSE);
        } else {
            cancellableContinuationImpl.resumeWith(new Result.Failure(closeCause));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x008f, code lost:
    
        r3 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x01dc, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f5, code lost:
    
        r5 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f7, code lost:
    
        access$onClosedSendOnNoWaiterSuspend(r25, r26, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00fa, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ff, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0100, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01be, code lost:
    
        r1.releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01c1, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0145, code lost:
    
        if (r22 >= r25.receivers.value) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0147, code lost:
    
        r20.cleanPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x014a, code lost:
    
        r1 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x014c, code lost:
    
        access$onClosedSendOnNoWaiterSuspend(r25, r26, r1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @Override // kotlinx.coroutines.channels.SendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object send(java.lang.Object r26, kotlin.coroutines.Continuation r27) {
        /*
            Method dump skipped, instructions count: 477
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x01ae, code lost:
    
        r4 = (kotlinx.coroutines.channels.ChannelSegment) r4.getNext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01b5, code lost:
    
        if (r4 != null) goto L99;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public final Object mo1789tryReceivePtdJZtk() {
        ChannelSegment channelSegment;
        long j = this.receivers.value;
        long j2 = this.sendersAndCloseStatus.value;
        if (isClosed(j2, true)) {
            return new ChannelResult.Closed(getCloseCause());
        }
        long j3 = j2 & 1152921504606846975L;
        Object obj = ChannelResult.failed;
        if (j >= j3) {
            return obj;
        }
        Object obj2 = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment channelSegment2 = (ChannelSegment) this.receiveSegment.value;
        while (!isClosedForReceive()) {
            AtomicLong atomicLong = this.receivers;
            atomicLong.getClass();
            long andIncrement = AtomicLong.FU.getAndIncrement(atomicLong);
            long j4 = BufferedChannelKt.SEGMENT_SIZE;
            long j5 = andIncrement / j4;
            int i = (int) (andIncrement % j4);
            if (channelSegment2.id != j5) {
                ChannelSegment findSegmentReceive = findSegmentReceive(j5, channelSegment2);
                if (findSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = findSegmentReceive;
                }
            } else {
                channelSegment = channelSegment2;
            }
            Object updateCellReceive = updateCellReceive(channelSegment, i, andIncrement, obj2);
            if (updateCellReceive == BufferedChannelKt.SUSPEND) {
                Waiter waiter = obj2 instanceof Waiter ? (Waiter) obj2 : null;
                if (waiter != null) {
                    waiter.invokeOnCancellation(channelSegment, i);
                }
                waitExpandBufferCompletion$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(andIncrement);
                channelSegment.onSlotCleaned();
            } else if (updateCellReceive == BufferedChannelKt.FAILED) {
                if (andIncrement < getSendersCounter$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                    channelSegment.cleanPrev();
                }
                channelSegment2 = channelSegment;
            } else {
                if (updateCellReceive == BufferedChannelKt.SUSPEND_NO_WAITER) {
                    throw new IllegalStateException("unexpected");
                }
                channelSegment.cleanPrev();
                obj = updateCellReceive;
            }
            return obj;
        }
        return new ChannelResult.Closed(getCloseCause());
    }

    public final boolean tryResumeReceiver(Object obj, Object obj2) {
        if (obj instanceof SelectInstance) {
            return ((SelectImplementation) ((SelectInstance) obj)).trySelectInternal(this, obj2) == 0;
        }
        boolean z = obj instanceof ReceiveCatching;
        Function1 function1 = this.onUndeliveredElement;
        if (z) {
            CancellableContinuationImpl cancellableContinuationImpl = ((ReceiveCatching) obj).cont;
            return BufferedChannelKt.tryResume0(cancellableContinuationImpl, new ChannelResult(obj2), function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, obj2, cancellableContinuationImpl.context) : null);
        }
        if (!(obj instanceof BufferedChannelIterator)) {
            if (obj instanceof CancellableContinuation) {
                CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
                return BufferedChannelKt.tryResume0(cancellableContinuation, obj2, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, obj2, cancellableContinuation.getContext()) : null);
            }
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) obj;
        CancellableContinuationImpl cancellableContinuationImpl2 = bufferedChannelIterator.continuation;
        Intrinsics.checkNotNull(cancellableContinuationImpl2);
        bufferedChannelIterator.continuation = null;
        bufferedChannelIterator.receiveResult = obj2;
        Boolean bool = Boolean.TRUE;
        Function1 function12 = BufferedChannel.this.onUndeliveredElement;
        return BufferedChannelKt.tryResume0(cancellableContinuationImpl2, bool, function12 != null ? OnUndeliveredElementKt.bindCancellationFun(function12, obj2, cancellableContinuationImpl2.context) : null);
    }

    public final boolean tryResumeSender(Object obj, ChannelSegment channelSegment, int i) {
        TrySelectDetailedResult trySelectDetailedResult;
        boolean z = obj instanceof CancellableContinuation;
        Unit unit = Unit.INSTANCE;
        if (z) {
            return BufferedChannelKt.tryResume0((CancellableContinuation) obj, unit, null);
        }
        if (!(obj instanceof SelectInstance)) {
            throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
        }
        int trySelectInternal = ((SelectImplementation) obj).trySelectInternal(this, unit);
        Function3 function3 = SelectKt.DUMMY_PROCESS_RESULT_FUNCTION;
        if (trySelectInternal == 0) {
            trySelectDetailedResult = TrySelectDetailedResult.SUCCESSFUL;
        } else if (trySelectInternal == 1) {
            trySelectDetailedResult = TrySelectDetailedResult.REREGISTER;
        } else if (trySelectInternal == 2) {
            trySelectDetailedResult = TrySelectDetailedResult.CANCELLED;
        } else {
            if (trySelectInternal != 3) {
                throw new IllegalStateException(("Unexpected internal result: " + trySelectInternal).toString());
            }
            trySelectDetailedResult = TrySelectDetailedResult.ALREADY_SELECTED;
        }
        if (trySelectDetailedResult == TrySelectDetailedResult.REREGISTER) {
            channelSegment.setElementLazy(i, null);
        }
        return trySelectDetailedResult == TrySelectDetailedResult.SUCCESSFUL;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
    
        return r1;
     */
    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo1790trySendJP2dKIU(java.lang.Object r22) {
        /*
            Method dump skipped, instructions count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.mo1790trySendJP2dKIU(java.lang.Object):java.lang.Object");
    }

    public final Object updateCellReceive(ChannelSegment channelSegment, int i, long j, Object obj) {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = channelSegment.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null) {
            if (j >= (this.sendersAndCloseStatus.value & 1152921504606846975L)) {
                if (obj == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                }
                if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, obj)) {
                    expandBuffer();
                    return BufferedChannelKt.SUSPEND;
                }
            }
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == BufferedChannelKt.BUFFERED && channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, BufferedChannelKt.DONE_RCV)) {
            expandBuffer();
            Object element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = channelSegment.getElement$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
            channelSegment.setElementLazy(i, null);
            return element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        }
        while (true) {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 = channelSegment.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 == null || state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 == BufferedChannelKt.IN_BUFFER) {
                if (j < (this.sendersAndCloseStatus.value & 1152921504606846975L)) {
                    if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, BufferedChannelKt.POISONED)) {
                        expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                } else {
                    if (obj == null) {
                        return BufferedChannelKt.SUSPEND_NO_WAITER;
                    }
                    if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, obj)) {
                        expandBuffer();
                        return BufferedChannelKt.SUSPEND;
                    }
                }
            } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 != BufferedChannelKt.BUFFERED) {
                Symbol symbol = BufferedChannelKt.INTERRUPTED_SEND;
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 == symbol) {
                    return BufferedChannelKt.FAILED;
                }
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 == BufferedChannelKt.POISONED) {
                    return BufferedChannelKt.FAILED;
                }
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 == BufferedChannelKt.CHANNEL_CLOSED) {
                    expandBuffer();
                    return BufferedChannelKt.FAILED;
                }
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 != BufferedChannelKt.RESUMING_BY_EB && channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, BufferedChannelKt.RESUMING_BY_RCV)) {
                    boolean z = state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 instanceof WaiterEB;
                    if (z) {
                        state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 = ((WaiterEB) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).waiter;
                    }
                    if (tryResumeSender(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, channelSegment, i)) {
                        channelSegment.setState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, BufferedChannelKt.DONE_RCV);
                        expandBuffer();
                        Object element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 = channelSegment.getElement$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
                        channelSegment.setElementLazy(i, null);
                        return element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2;
                    }
                    channelSegment.setState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, symbol);
                    channelSegment.onSlotCleaned();
                    if (z) {
                        expandBuffer();
                    }
                    return BufferedChannelKt.FAILED;
                }
            } else if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, BufferedChannelKt.DONE_RCV)) {
                expandBuffer();
                Object element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host3 = channelSegment.getElement$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
                channelSegment.setElementLazy(i, null);
                return element$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host3;
            }
        }
    }

    public final int updateCellSendSlow(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        while (true) {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = channelSegment.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i);
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null) {
                if (!bufferOrRendezvousSend(j) || z) {
                    if (z) {
                        if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, null, BufferedChannelKt.INTERRUPTED_SEND)) {
                            channelSegment.onSlotCleaned();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, null, obj2)) {
                            return 2;
                        }
                    }
                } else if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != BufferedChannelKt.IN_BUFFER) {
                    Symbol symbol = BufferedChannelKt.INTERRUPTED_RCV;
                    if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == symbol) {
                        channelSegment.setElementLazy(i, null);
                        return 5;
                    }
                    if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == BufferedChannelKt.POISONED) {
                        channelSegment.setElementLazy(i, null);
                        return 5;
                    }
                    if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == BufferedChannelKt.CHANNEL_CLOSED) {
                        channelSegment.setElementLazy(i, null);
                        isClosedForSend();
                        return 4;
                    }
                    channelSegment.setElementLazy(i, null);
                    if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof WaiterEB) {
                        state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ((WaiterEB) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).waiter;
                    }
                    if (tryResumeReceiver(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, obj)) {
                        channelSegment.setState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, BufferedChannelKt.DONE_RCV);
                        return 0;
                    }
                    AtomicRef atomicRef = channelSegment.data.array[(i * 2) + 1];
                    atomicRef.getClass();
                    if (AtomicRef.FU.getAndSet(atomicRef, symbol) == symbol) {
                        return 5;
                    }
                    channelSegment.onCancelledRequest(i, true);
                    return 5;
                }
                if (channelSegment.casState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(i, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            }
        }
    }

    public final void waitExpandBufferCompletion$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(long j) {
        long j2;
        long j3;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        while (this.bufferEnd.value <= j) {
        }
        int i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        for (int i2 = 0; i2 < i; i2++) {
            long j4 = this.bufferEnd.value;
            if (j4 == (4611686018427387903L & this.completedExpandBuffersAndPauseFlag.value) && j4 == this.bufferEnd.value) {
                return;
            }
        }
        AtomicLong atomicLong = this.completedExpandBuffersAndPauseFlag;
        do {
            j2 = atomicLong.value;
        } while (!AtomicLong.FU.compareAndSet(atomicLong, j2, 4611686018427387904L + (j2 & 4611686018427387903L)));
        while (true) {
            long j5 = this.bufferEnd.value;
            long j6 = this.completedExpandBuffersAndPauseFlag.value;
            long j7 = j6 & 4611686018427387903L;
            boolean z = (j6 & 4611686018427387904L) != 0;
            if (j5 == j7 && j5 == this.bufferEnd.value) {
                break;
            } else if (!z) {
                this.completedExpandBuffersAndPauseFlag.compareAndSet(j6, j7 + 4611686018427387904L);
            }
        }
        AtomicLong atomicLong2 = this.completedExpandBuffersAndPauseFlag;
        do {
            j3 = atomicLong2.value;
        } while (!AtomicLong.FU.compareAndSet(atomicLong2, j3, j3 & 4611686018427387903L));
    }
}
