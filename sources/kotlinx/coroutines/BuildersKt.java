package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BuildersKt {
    public static DeferredCoroutine async$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, int i) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineStart coroutineStart = CoroutineStart.DEFAULT;
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        CoroutineStart coroutineStart2 = CoroutineStart.DEFAULT;
        DeferredCoroutine deferredCoroutine = new DeferredCoroutine(newCoroutineContext, true);
        deferredCoroutine.start(coroutineStart, deferredCoroutine, function2);
        return deferredCoroutine;
    }

    public static final StandaloneCoroutine launch(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2) {
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        coroutineStart.getClass();
        StandaloneCoroutine lazyStandaloneCoroutine = coroutineStart == CoroutineStart.LAZY ? new LazyStandaloneCoroutine(newCoroutineContext, function2) : new StandaloneCoroutine(newCoroutineContext, true);
        lazyStandaloneCoroutine.start(coroutineStart, lazyStandaloneCoroutine, function2);
        return lazyStandaloneCoroutine;
    }

    public static /* synthetic */ StandaloneCoroutine launch$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Object runBlocking(CoroutineContext coroutineContext, Function2 function2) {
        EventLoopImplBase eventLoopImplBase;
        CoroutineContext foldCopies;
        Thread currentThread = Thread.currentThread();
        CoroutineContext.Key key = ContinuationInterceptor.Key.$$INSTANCE;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(key);
        if (continuationInterceptor == null) {
            eventLoopImplBase = ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            foldCopies = CoroutineContextKt.foldCopies(EmptyCoroutineContext.INSTANCE, coroutineContext.plus(eventLoopImplBase), true);
            DefaultScheduler defaultScheduler = Dispatchers.Default;
            if (foldCopies != defaultScheduler && foldCopies.get(key) == null) {
                foldCopies = foldCopies.plus(defaultScheduler);
            }
        } else {
            if (continuationInterceptor instanceof EventLoopImplBase) {
            }
            eventLoopImplBase = (EventLoopImplBase) ThreadLocalEventLoop.ref.get();
            foldCopies = CoroutineContextKt.foldCopies(EmptyCoroutineContext.INSTANCE, coroutineContext, true);
            DefaultScheduler defaultScheduler2 = Dispatchers.Default;
            if (foldCopies != defaultScheduler2 && foldCopies.get(key) == null) {
                foldCopies = foldCopies.plus(defaultScheduler2);
            }
        }
        Intrinsics.checkNotNull(currentThread);
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(foldCopies, currentThread, eventLoopImplBase);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        EventLoopImplBase eventLoopImplBase2 = blockingCoroutine.eventLoop;
        if (eventLoopImplBase2 != null) {
            int i = EventLoopImplBase.$r8$clinit;
            eventLoopImplBase2.incrementUseCount(false);
        }
        while (!Thread.interrupted()) {
            try {
                long processNextEvent = eventLoopImplBase2 != null ? eventLoopImplBase2.processNextEvent() : Long.MAX_VALUE;
                if (blockingCoroutine.isCompleted()) {
                    if (eventLoopImplBase2 != null) {
                        int i2 = EventLoopImplBase.$r8$clinit;
                        eventLoopImplBase2.decrementUseCount(false);
                    }
                    Object unboxState = JobSupportKt.unboxState(blockingCoroutine.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
                    CompletedExceptionally completedExceptionally = unboxState instanceof CompletedExceptionally ? (CompletedExceptionally) unboxState : null;
                    if (completedExceptionally == null) {
                        return unboxState;
                    }
                    throw completedExceptionally.cause;
                }
                LockSupport.parkNanos(blockingCoroutine, processNextEvent);
            } catch (Throwable th) {
                if (eventLoopImplBase2 != null) {
                    int i3 = EventLoopImplBase.$r8$clinit;
                    eventLoopImplBase2.decrementUseCount(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        blockingCoroutine.cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(interruptedException);
        throw interruptedException;
    }

    public static final Object withContext(CoroutineContext coroutineContext, Function2 function2, Continuation continuation) {
        Object unboxState;
        CoroutineContext context = continuation.getContext();
        CoroutineContext plus = !((Boolean) coroutineContext.fold(Boolean.FALSE, CoroutineContextKt$hasCopyableElements$1.INSTANCE)).booleanValue() ? context.plus(coroutineContext) : CoroutineContextKt.foldCopies(context, coroutineContext, false);
        JobKt.ensureActive(plus);
        if (plus == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation, plus);
            unboxState = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        } else {
            ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
            if (Intrinsics.areEqual(plus.get(key), context.get(key))) {
                UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(continuation, plus);
                CoroutineContext coroutineContext2 = undispatchedCoroutine.context;
                Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext2, null);
                try {
                    Object startUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
                    ThreadContextKt.restoreThreadContext(coroutineContext2, updateThreadContext);
                    unboxState = startUndispatchedOrReturn;
                } catch (Throwable th) {
                    ThreadContextKt.restoreThreadContext(coroutineContext2, updateThreadContext);
                    throw th;
                }
            } else {
                DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(continuation, plus);
                CancellableKt.startCoroutineCancellable$default(function2, dispatchedCoroutine, dispatchedCoroutine);
                AtomicInt atomicInt = dispatchedCoroutine._decision;
                while (true) {
                    int i = atomicInt.value;
                    if (i == 0) {
                        AtomicInt atomicInt2 = dispatchedCoroutine._decision;
                        atomicInt2.getClass();
                        if (AtomicInt.FU.compareAndSet(atomicInt2, 0, 1)) {
                            unboxState = CoroutineSingletons.COROUTINE_SUSPENDED;
                            break;
                        }
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("Already suspended");
                        }
                        unboxState = JobSupportKt.unboxState(dispatchedCoroutine.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
                        if (unboxState instanceof CompletedExceptionally) {
                            throw ((CompletedExceptionally) unboxState).cause;
                        }
                    }
                }
            }
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return unboxState;
    }
}
