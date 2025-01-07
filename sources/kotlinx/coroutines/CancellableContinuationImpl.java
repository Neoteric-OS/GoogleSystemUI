package kotlinx.coroutines;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CancellableContinuationImpl extends DispatchedTask implements CancellableContinuation, CoroutineStackFrame, Waiter {
    public final AtomicInt _decisionAndIndex;
    public final AtomicRef _parentHandle;
    public final AtomicRef _state;
    public final CoroutineContext context;
    public final Continuation delegate;

    public CancellableContinuationImpl(int i, Continuation continuation) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
        this._decisionAndIndex = AtomicFU.atomic(536870911);
        this._state = AtomicFU.atomic(Active.INSTANCE);
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    public static void multipleHandlersError(NotCompleted notCompleted, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + notCompleted + ", already has " + obj).toString());
    }

    public static Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1 function1) {
        if ((obj instanceof CompletedExceptionally) || !DispatchedTaskKt.isCancellableMode(i)) {
            return obj;
        }
        if (function1 != null || (notCompleted instanceof CancelHandler)) {
            return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, (Throwable) null, 16);
        }
        return obj;
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2), this.context);
        }
    }

    public final void callOnCancellation(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2), this.context);
        }
    }

    public final void callSegmentOnCancellation(Segment segment, Throwable th) {
        int i = this._decisionAndIndex.value & 536870911;
        if (i == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            segment.onCancellation(i, this.context);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2), this.context);
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final boolean cancel(Throwable th) {
        Object obj;
        CancelledContinuation cancelledContinuation;
        AtomicRef atomicRef;
        AtomicRef atomicRef2 = this._state;
        do {
            obj = atomicRef2.value;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            cancelledContinuation = new CancelledContinuation(this, th, (obj instanceof CancelHandler) || (obj instanceof Segment));
            atomicRef = this._state;
            atomicRef.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef, obj, cancelledContinuation));
        NotCompleted notCompleted = (NotCompleted) obj;
        if (notCompleted instanceof CancelHandler) {
            callCancelHandler((CancelHandler) obj, th);
        } else if (notCompleted instanceof Segment) {
            callSegmentOnCancellation((Segment) obj, th);
        }
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
        dispatchResume(this.resumeMode);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj, Throwable th) {
        AtomicRef atomicRef = this._state;
        while (true) {
            Object obj2 = atomicRef.value;
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof CompletedExceptionally) {
                return;
            }
            if (obj2 instanceof CompletedContinuation) {
                CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                if (completedContinuation.cancelCause != null) {
                    throw new IllegalStateException("Must be called at most once");
                }
                CompletedContinuation copy$default = CompletedContinuation.copy$default(completedContinuation, null, th, 15);
                AtomicRef atomicRef2 = this._state;
                atomicRef2.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef2, obj2, copy$default)) {
                    CancelHandler cancelHandler = completedContinuation.cancelHandler;
                    if (cancelHandler != null) {
                        callCancelHandler(cancelHandler, th);
                    }
                    Function1 function1 = completedContinuation.onCancellation;
                    if (function1 != null) {
                        callOnCancellation(function1, th);
                        return;
                    }
                    return;
                }
            } else {
                AtomicRef atomicRef3 = this._state;
                CompletedContinuation completedContinuation2 = new CompletedContinuation(obj2, (CancelHandler) null, (Function1) null, th, 14);
                atomicRef3.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef3, obj2, completedContinuation2)) {
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final void completeResume(Object obj) {
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        DisposableHandle disposableHandle = (DisposableHandle) this._parentHandle.value;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this._parentHandle.value = NonDisposableHandle.INSTANCE;
    }

    public final void dispatchResume(int i) {
        int i2;
        AtomicInt atomicInt;
        int i3;
        AtomicInt atomicInt2 = this._decisionAndIndex;
        do {
            i2 = atomicInt2.value;
            int i4 = i2 >> 29;
            if (i4 != 0) {
                if (i4 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                Continuation continuation = this.delegate;
                boolean z = i == 4;
                if (z || !(continuation instanceof DispatchedContinuation) || DispatchedTaskKt.isCancellableMode(i) != DispatchedTaskKt.isCancellableMode(this.resumeMode)) {
                    DispatchedTaskKt.resume(this, continuation, z);
                    return;
                }
                CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) continuation).dispatcher;
                CoroutineContext context = ((DispatchedContinuation) continuation).continuation.getContext();
                if (coroutineDispatcher.isDispatchNeeded()) {
                    coroutineDispatcher.dispatch(context, this);
                    return;
                }
                EventLoopImplBase eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                if (eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.isUnconfinedLoopActive()) {
                    eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.dispatchUnconfined(this);
                    return;
                }
                eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.incrementUseCount(true);
                try {
                    DispatchedTaskKt.resume(this, this.delegate, true);
                    do {
                    } while (eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.processUnconfinedEvent());
                } finally {
                    try {
                        return;
                    } finally {
                    }
                }
                return;
            }
            atomicInt = this._decisionAndIndex;
            i3 = 1073741824 + (536870911 & i2);
            atomicInt.getClass();
        } while (!AtomicInt.FU.compareAndSet(atomicInt, i2, i3));
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    public Throwable getContinuationCancellationCause(JobSupport jobSupport) {
        return jobSupport.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Throwable getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = super.getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj);
        if (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
            return exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        }
        return null;
    }

    public final Object getResult() {
        int i;
        Job job;
        AtomicInt atomicInt;
        int i2;
        boolean isReusable = isReusable();
        AtomicInt atomicInt2 = this._decisionAndIndex;
        do {
            i = atomicInt2.value;
            int i3 = i >> 29;
            if (i3 != 0) {
                if (i3 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (isReusable) {
                    releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                }
                Object obj = this._state.value;
                if (obj instanceof CompletedExceptionally) {
                    throw ((CompletedExceptionally) obj).cause;
                }
                if (!DispatchedTaskKt.isCancellableMode(this.resumeMode) || (job = (Job) this.context.get(Job.Key.$$INSTANCE)) == null || job.isActive()) {
                    return getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj);
                }
                CancellationException cancellationException = job.getCancellationException();
                cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj, cancellationException);
                throw cancellationException;
            }
            atomicInt = this._decisionAndIndex;
            i2 = 536870912 + (536870911 & i);
            atomicInt.getClass();
        } while (!AtomicInt.FU.compareAndSet(atomicInt, i, i2));
        if (((DisposableHandle) this._parentHandle.value) == null) {
            installParentHandle();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        return obj instanceof CompletedContinuation ? ((CompletedContinuation) obj).result : obj;
    }

    public final void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && isCompleted()) {
            installParentHandle.dispose();
            this._parentHandle.value = NonDisposableHandle.INSTANCE;
        }
    }

    public final DisposableHandle installParentHandle() {
        DisposableHandle invokeOnCompletion;
        Job job = (Job) this.context.get(Job.Key.$$INSTANCE);
        if (job == null) {
            return null;
        }
        invokeOnCompletion = job.invokeOnCompletion((r5 & 1) == 0, (r5 & 2) != 0, new ChildContinuation(this));
        this._parentHandle.compareAndSet(null, invokeOnCompletion);
        return invokeOnCompletion;
    }

    @Override // kotlinx.coroutines.Waiter
    public final void invokeOnCancellation(Segment segment, int i) {
        int i2;
        AtomicInt atomicInt = this._decisionAndIndex;
        do {
            i2 = atomicInt.value;
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!AtomicInt.FU.compareAndSet(atomicInt, i2, ((i2 >> 29) << 29) + i));
        invokeOnCancellationImpl(segment);
    }

    public final void invokeOnCancellationImpl(NotCompleted notCompleted) {
        AtomicRef atomicRef = this._state;
        while (true) {
            Object obj = atomicRef.value;
            if (obj instanceof Active) {
                AtomicRef atomicRef2 = this._state;
                atomicRef2.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef2, obj, notCompleted)) {
                    return;
                }
            } else {
                if (obj instanceof CancelHandler ? true : obj instanceof Segment) {
                    multipleHandlersError(notCompleted, obj);
                    throw null;
                }
                if (obj instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                    AtomicBoolean atomicBoolean = completedExceptionally._handled;
                    atomicBoolean.getClass();
                    if (!AtomicBoolean.FU.compareAndSet(atomicBoolean, 0, 1)) {
                        multipleHandlersError(notCompleted, obj);
                        throw null;
                    }
                    if (obj instanceof CancelledContinuation) {
                        if (!(obj instanceof CompletedExceptionally)) {
                            completedExceptionally = null;
                        }
                        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
                        if (notCompleted instanceof CancelHandler) {
                            callCancelHandler((CancelHandler) notCompleted, th);
                            return;
                        } else {
                            callSegmentOnCancellation((Segment) notCompleted, th);
                            return;
                        }
                    }
                    return;
                }
                if (obj instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj;
                    if (completedContinuation.cancelHandler != null) {
                        multipleHandlersError(notCompleted, obj);
                        throw null;
                    }
                    if (notCompleted instanceof Segment) {
                        return;
                    }
                    CancelHandler cancelHandler = (CancelHandler) notCompleted;
                    Throwable th2 = completedContinuation.cancelCause;
                    if (th2 != null) {
                        callCancelHandler(cancelHandler, th2);
                        return;
                    }
                    CompletedContinuation copy$default = CompletedContinuation.copy$default(completedContinuation, cancelHandler, null, 29);
                    AtomicRef atomicRef3 = this._state;
                    atomicRef3.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef3, obj, copy$default)) {
                        return;
                    }
                } else {
                    if (notCompleted instanceof Segment) {
                        return;
                    }
                    CompletedContinuation completedContinuation2 = new CompletedContinuation(obj, (CancelHandler) notCompleted, (Function1) null, (Throwable) null, 28);
                    AtomicRef atomicRef4 = this._state;
                    atomicRef4.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef4, obj, completedContinuation2)) {
                        return;
                    }
                }
            }
        }
    }

    public final boolean isCompleted() {
        return !(this._state.value instanceof NotCompleted);
    }

    public final boolean isReusable() {
        return this.resumeMode == 2 && ((DispatchedContinuation) this.delegate)._reusableCancellableContinuation.value != null;
    }

    public String nameString() {
        return "CancellableContinuation";
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0037, code lost:
    
        if (r2 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
    
        detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        cancel(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0040, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        /*
            r6 = this;
            kotlin.coroutines.Continuation r0 = r6.delegate
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            r2 = 0
            if (r1 == 0) goto La
            kotlinx.coroutines.internal.DispatchedContinuation r0 = (kotlinx.coroutines.internal.DispatchedContinuation) r0
            goto Lb
        La:
            r0 = r2
        Lb:
            if (r0 == 0) goto L61
            kotlinx.atomicfu.AtomicRef r1 = r0._reusableCancellableContinuation
        Lf:
            java.lang.Object r3 = r1.value
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.internal.DispatchedContinuationKt.REUSABLE_CLAIMED
            if (r3 != r4) goto L23
            kotlinx.atomicfu.AtomicRef r3 = r0._reusableCancellableContinuation
            r3.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.atomicfu.AtomicRef.FU
            boolean r3 = r5.compareAndSet(r3, r4, r6)
            if (r3 == 0) goto Lf
            goto L37
        L23:
            boolean r1 = r3 instanceof java.lang.Throwable
            if (r1 == 0) goto L49
            kotlinx.atomicfu.AtomicRef r0 = r0._reusableCancellableContinuation
            r0.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.atomicfu.AtomicRef.FU
            boolean r0 = r1.compareAndSet(r0, r3, r2)
            if (r0 == 0) goto L41
            r2 = r3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
        L37:
            if (r2 != 0) goto L3a
            goto L61
        L3a:
            r6.detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            r6.cancel(r2)
            return
        L41:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Failed requirement."
            r6.<init>(r0)
            throw r6
        L49:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Inconsistent state "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L61:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CancellableContinuationImpl.releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host():void");
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final void resume(Object obj, Function1 function1) {
        resumeImpl(obj, this.resumeMode, function1);
    }

    public final void resumeImpl(Object obj, int i, Function1 function1) {
        Object obj2;
        Object resumedState;
        AtomicRef atomicRef;
        AtomicRef atomicRef2 = this._state;
        do {
            obj2 = atomicRef2.value;
            if (!(obj2 instanceof NotCompleted)) {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    AtomicBoolean atomicBoolean = cancelledContinuation._resumed;
                    atomicBoolean.getClass();
                    if (AtomicBoolean.FU.compareAndSet(atomicBoolean, 0, 1)) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            resumedState = resumedState((NotCompleted) obj2, obj, i, function1);
            atomicRef = this._state;
            atomicRef.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef, obj2, resumedState));
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
        dispatchResume(i);
    }

    public final void resumeUndispatched(CoroutineDispatcher coroutineDispatcher) {
        Unit unit = Unit.INSTANCE;
        Continuation continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl(unit, (dispatchedContinuation != null ? dispatchedContinuation.dispatcher : null) == coroutineDispatcher ? 4 : this.resumeMode, null);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m1771exceptionOrNullimpl = Result.m1771exceptionOrNullimpl(obj);
        if (m1771exceptionOrNullimpl != null) {
            obj = new CompletedExceptionally(m1771exceptionOrNullimpl, false);
        }
        resumeImpl(obj, this.resumeMode, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this._state.value;
    }

    public final String toString() {
        String nameString = nameString();
        String debugString = DebugStringsKt.toDebugString(this.delegate);
        Object obj = this._state.value;
        String str = obj instanceof NotCompleted ? "Active" : obj instanceof CancelledContinuation ? "Cancelled" : "Completed";
        String hexAddress = DebugStringsKt.getHexAddress(this);
        StringBuilder sb = new StringBuilder();
        sb.append(nameString);
        sb.append("(");
        sb.append(debugString);
        sb.append("){");
        sb.append(str);
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, "}@", hexAddress);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final Symbol tryResume(Object obj, Function1 function1) {
        return tryResumeImpl(obj, function1);
    }

    public final Symbol tryResumeImpl(Object obj, Function1 function1) {
        Object obj2;
        Symbol symbol;
        Object resumedState;
        AtomicRef atomicRef;
        AtomicRef atomicRef2 = this._state;
        do {
            obj2 = atomicRef2.value;
            boolean z = obj2 instanceof NotCompleted;
            symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (!z) {
                boolean z2 = obj2 instanceof CompletedContinuation;
                return null;
            }
            resumedState = resumedState((NotCompleted) obj2, obj, this.resumeMode, function1);
            atomicRef = this._state;
            atomicRef.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef, obj2, resumedState));
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
        return symbol;
    }

    public final void invokeOnCancellation(Function1 function1) {
        invokeOnCancellationImpl(function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1));
    }
}
