package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectClause1Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProducerCoroutine extends AbstractCoroutine implements ProducerScope, Channel {
    public final BufferedChannel _channel;

    public ProducerCoroutine(CoroutineContext coroutineContext, BufferedChannel bufferedChannel) {
        super(coroutineContext, true);
        this._channel = bufferedChannel;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void cancel(CancellationException cancellationException) {
        if (isCancelled$1()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void cancelInternal(Throwable th) {
        CancellationException cancellationException = (CancellationException) th;
        this._channel.closeOrCancelImpl(cancellationException, true);
        cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        return this._channel.closeOrCancelImpl(th, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1Impl getOnReceiveCatching() {
        return this._channel.getOnReceiveCatching();
    }

    public final void invokeOnClose(Function1 function1) {
        Symbol symbol;
        AtomicRef atomicRef;
        Symbol symbol2;
        BufferedChannel bufferedChannel = this._channel;
        AtomicRef atomicRef2 = bufferedChannel.closeHandler;
        atomicRef2.getClass();
        if (AtomicRef.FU.compareAndSet(atomicRef2, null, function1)) {
            return;
        }
        AtomicRef atomicRef3 = bufferedChannel.closeHandler;
        do {
            Object obj = atomicRef3.value;
            symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            if (obj != symbol) {
                if (obj == BufferedChannelKt.CLOSE_HANDLER_INVOKED) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked");
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
            atomicRef = bufferedChannel.closeHandler;
            symbol2 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
            atomicRef.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef, symbol, symbol2));
        ((ProduceKt$awaitClose$4$1) function1).invoke(bufferedChannel.getCloseCause());
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final BufferedChannel.BufferedChannelIterator iterator() {
        BufferedChannel bufferedChannel = this._channel;
        bufferedChannel.getClass();
        return bufferedChannel.new BufferedChannelIterator();
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public final void onCancelled(Throwable th, boolean z) {
        if (this._channel.closeOrCancelImpl(th, false) || z) {
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(th, this.context);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public final void onCompleted(Object obj) {
        this._channel.close(null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(SuspendLambda suspendLambda) {
        return this._channel.receive(suspendLambda);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU */
    public final Object mo1787receiveCatchingJP2dKIU(Continuation continuation) {
        BufferedChannel bufferedChannel = this._channel;
        bufferedChannel.getClass();
        Object m1786receiveCatchingJP2dKIU$suspendImpl = BufferedChannel.m1786receiveCatchingJP2dKIU$suspendImpl(bufferedChannel, (ContinuationImpl) continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return m1786receiveCatchingJP2dKIU$suspendImpl;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        return this._channel.send(obj, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk */
    public final Object mo1789tryReceivePtdJZtk() {
        return this._channel.mo1789tryReceivePtdJZtk();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public final Object mo1790trySendJP2dKIU(Object obj) {
        return this._channel.mo1790trySendJP2dKIU(obj);
    }
}
