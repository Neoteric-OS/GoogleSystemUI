package kotlinx.coroutines.channels;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConflatedBufferedChannel extends BufferedChannel {
    public final BufferOverflow onBufferOverflow;

    public ConflatedBufferedChannel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        super(i, function1);
        this.onBufferOverflow = bufferOverflow;
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("This implementation does not support suspension for senders, use ", Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName(), " instead").toString());
        }
        if (i < 1) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Buffered channel capacity must be at least 1, but ", " was specified", i).toString());
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public final boolean isConflatedDropOldest() {
        return this.onBufferOverflow == BufferOverflow.DROP_OLDEST;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        UndeliveredElementException callUndeliveredElementCatchingException;
        Object m1792trySendImplMj0NB7M = m1792trySendImplMj0NB7M(obj, true);
        if (!(m1792trySendImplMj0NB7M instanceof ChannelResult.Closed)) {
            return Unit.INSTANCE;
        }
        ChannelResult.m1791exceptionOrNullimpl(m1792trySendImplMj0NB7M);
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
            throw getSendException();
        }
        ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException, getSendException());
        throw callUndeliveredElementCatchingException;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public final Object mo1790trySendJP2dKIU(Object obj) {
        return m1792trySendImplMj0NB7M(obj, false);
    }

    /* renamed from: trySendImpl-Mj0NB7M, reason: not valid java name */
    public final Object m1792trySendImplMj0NB7M(Object obj, boolean z) {
        ChannelSegment channelSegment;
        Function1 function1;
        UndeliveredElementException callUndeliveredElementCatchingException;
        BufferOverflow bufferOverflow = this.onBufferOverflow;
        BufferOverflow bufferOverflow2 = BufferOverflow.DROP_LATEST;
        Unit unit = Unit.INSTANCE;
        if (bufferOverflow == bufferOverflow2) {
            Object mo1790trySendJP2dKIU = super.mo1790trySendJP2dKIU(obj);
            if (!(mo1790trySendJP2dKIU instanceof ChannelResult.Failed) || (mo1790trySendJP2dKIU instanceof ChannelResult.Closed)) {
                return mo1790trySendJP2dKIU;
            }
            if (!z || (function1 = this.onUndeliveredElement) == null || (callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
                return unit;
            }
            throw callUndeliveredElementCatchingException;
        }
        Object obj2 = BufferedChannelKt.BUFFERED;
        ChannelSegment channelSegment2 = (ChannelSegment) this.sendSegment.value;
        while (true) {
            AtomicLong atomicLong = this.sendersAndCloseStatus;
            atomicLong.getClass();
            long andIncrement = AtomicLong.FU.getAndIncrement(atomicLong);
            long j = andIncrement & 1152921504606846975L;
            boolean isClosed = isClosed(andIncrement, false);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = i;
            long j3 = j / j2;
            int i2 = (int) (j % j2);
            if (channelSegment2.id != j3) {
                ChannelSegment access$findSegmentSend = BufferedChannel.access$findSegmentSend(this, j3, channelSegment2);
                if (access$findSegmentSend != null) {
                    channelSegment = access$findSegmentSend;
                } else if (isClosed) {
                    return new ChannelResult.Closed(getSendException());
                }
            } else {
                channelSegment = channelSegment2;
            }
            int access$updateCellSend = BufferedChannel.access$updateCellSend(this, channelSegment, i2, obj, j, obj2, isClosed);
            if (access$updateCellSend == 0) {
                channelSegment.cleanPrev();
                return unit;
            }
            if (access$updateCellSend == 1) {
                return unit;
            }
            if (access$updateCellSend == 2) {
                if (isClosed) {
                    channelSegment.onSlotCleaned();
                    return new ChannelResult.Closed(getSendException());
                }
                Waiter waiter = obj2 instanceof Waiter ? (Waiter) obj2 : null;
                if (waiter != null) {
                    waiter.invokeOnCancellation(channelSegment, i2 + i);
                }
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((channelSegment.id * j2) + i2);
                return unit;
            }
            if (access$updateCellSend == 3) {
                throw new IllegalStateException("unexpected");
            }
            if (access$updateCellSend == 4) {
                if (j < this.receivers.value) {
                    channelSegment.cleanPrev();
                }
                return new ChannelResult.Closed(getSendException());
            }
            if (access$updateCellSend == 5) {
                channelSegment.cleanPrev();
            }
            channelSegment2 = channelSegment;
        }
    }
}
