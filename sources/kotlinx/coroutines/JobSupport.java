package kotlinx.coroutines;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class JobSupport implements Job {
    public final AtomicRef _parentHandle;
    public final AtomicRef _state;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AwaitContinuation extends CancellableContinuationImpl {
        public final JobSupport job;

        public AwaitContinuation(Continuation continuation, JobSupport jobSupport) {
            super(1, continuation);
            this.job = jobSupport;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public final Throwable getContinuationCancellationCause(JobSupport jobSupport) {
            Throwable rootCause;
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = this.job.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            return (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) || (rootCause = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause()) == null) ? state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally ? ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause : jobSupport.getCancellationException() : rootCause;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public final String nameString() {
            return "AwaitContinuation";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Finishing implements Incomplete {
        public final AtomicRef _rootCause;
        public final NodeList list;
        public final AtomicBoolean _isCompleting = AtomicFU.atomic(false);
        public final AtomicRef _exceptionsHolder = AtomicFU.atomic((Object) null);

        public Finishing(NodeList nodeList, Throwable th) {
            this.list = nodeList;
            this._rootCause = AtomicFU.atomic(th);
        }

        public final void addExceptionLocked(Throwable th) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                this._rootCause.value = th;
                return;
            }
            if (th == rootCause) {
                return;
            }
            Object obj = this._exceptionsHolder.value;
            if (obj == null) {
                this._exceptionsHolder.value = th;
                return;
            }
            if (!(obj instanceof Throwable)) {
                if (obj instanceof ArrayList) {
                    ((ArrayList) obj).add(th);
                    return;
                } else {
                    throw new IllegalStateException(("State is " + obj).toString());
                }
            }
            if (th == obj) {
                return;
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(th);
            this._exceptionsHolder.value = arrayList;
        }

        @Override // kotlinx.coroutines.Incomplete
        public final NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) this._rootCause.value;
        }

        @Override // kotlinx.coroutines.Incomplete
        public final boolean isActive() {
            return getRootCause() == null;
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        public final List sealLocked(Throwable th) {
            ArrayList arrayList;
            Object obj = this._exceptionsHolder.value;
            if (obj == null) {
                arrayList = new ArrayList(4);
            } else if (obj instanceof Throwable) {
                ArrayList arrayList2 = new ArrayList(4);
                arrayList2.add(obj);
                arrayList = arrayList2;
            } else {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + obj).toString());
                }
                arrayList = (ArrayList) obj;
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (th != null && !th.equals(rootCause)) {
                arrayList.add(th);
            }
            this._exceptionsHolder.value = JobSupportKt.SEALED;
            return arrayList;
        }

        public final String toString() {
            boolean isCancelling = isCancelling();
            boolean value = this._isCompleting.getValue();
            Throwable rootCause = getRootCause();
            Object obj = this._exceptionsHolder.value;
            NodeList nodeList = this.list;
            StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("Finishing[cancelling=", ", completing=", ", rootCause=", isCancelling, value);
            m.append(rootCause);
            m.append(", exceptions=");
            m.append(obj);
            m.append(", list=");
            m.append(nodeList);
            m.append("]");
            return m.toString();
        }
    }

    public JobSupport(boolean z) {
        this._state = AtomicFU.atomic(z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW);
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    public static ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            LockFreeLinkedListNode correctPrev = lockFreeLinkedListNode.correctPrev();
            if (correctPrev == null) {
                Object obj = lockFreeLinkedListNode._prev.value;
                while (true) {
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                    if (!lockFreeLinkedListNode.isRemoved()) {
                        break;
                    }
                    obj = lockFreeLinkedListNode._prev.value;
                }
            } else {
                lockFreeLinkedListNode = correctPrev;
            }
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    public static String stateString(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() ? "Active" : "New" : obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing finishing = (Finishing) obj;
        return finishing.isCancelling() ? "Cancelling" : finishing._isCompleting.getValue() ? "Completing" : "Active";
    }

    public final boolean addLastAtomic(Incomplete incomplete, NodeList nodeList, JobNode jobNode) {
        char c;
        JobSupport$addLastAtomic$$inlined$addLastIf$1 jobSupport$addLastAtomic$$inlined$addLastIf$1 = new JobSupport$addLastAtomic$$inlined$addLastIf$1(jobNode, this, incomplete);
        do {
            LockFreeLinkedListNode correctPrev = nodeList.correctPrev();
            if (correctPrev == null) {
                Object obj = nodeList._prev.value;
                while (true) {
                    correctPrev = (LockFreeLinkedListNode) obj;
                    if (!correctPrev.isRemoved()) {
                        break;
                    }
                    obj = correctPrev._prev.value;
                }
            }
            jobNode._prev.lazySet(correctPrev);
            jobNode._next.lazySet(nodeList);
            jobSupport$addLastAtomic$$inlined$addLastIf$1.oldNext = nodeList;
            AtomicRef atomicRef = correctPrev._next;
            atomicRef.getClass();
            c = !AtomicRef.FU.compareAndSet(atomicRef, nodeList, jobSupport$addLastAtomic$$inlined$addLastIf$1) ? (char) 0 : jobSupport$addLastAtomic$$inlined$addLastIf$1.perform(correctPrev) == null ? (char) 1 : (char) 2;
            if (c == 1) {
                return true;
            }
        } while (c != 2);
        return false;
    }

    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(JobSupport jobSupport) {
        DisposableHandle invokeOnCompletion;
        invokeOnCompletion = invokeOnCompletion((r5 & 1) == 0, (r5 & 2) != 0, new ChildHandleNode(jobSupport));
        return (ChildHandle) invokeOnCompletion;
    }

    public final Object awaitInternal(Continuation continuation) {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        do {
            state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete)) {
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
                    throw ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
                }
                return JobSupportKt.unboxState(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
            }
        } while (startInternal(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) < 0);
        AwaitContinuation awaitContinuation = new AwaitContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), this);
        awaitContinuation.initCancellability();
        awaitContinuation.invokeOnCancellation(new DisposeOnCancel(invokeOnCompletion(false, true, new ResumeAwaitOnCompletion(awaitContinuation))));
        Object result = awaitContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    public final boolean cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Symbol symbol;
        Object obj2 = JobSupportKt.COMPLETING_ALREADY;
        if (getOnCancelComplete$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
            do {
                Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) || ((state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) && ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host)._isCompleting.getValue())) {
                    obj2 = JobSupportKt.COMPLETING_ALREADY;
                    break;
                }
                obj2 = tryMakeCompleting(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, new CompletedExceptionally(createCauseException(obj), false));
            } while (obj2 == JobSupportKt.COMPLETING_RETRY);
            if (obj2 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        }
        if (obj2 == JobSupportKt.COMPLETING_ALREADY) {
            Throwable th = null;
            while (true) {
                Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 instanceof Finishing)) {
                    if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2 instanceof Incomplete)) {
                        symbol = JobSupportKt.TOO_LATE_TO_CANCEL;
                        break;
                    }
                    if (th == null) {
                        th = createCauseException(obj);
                    }
                    Incomplete incomplete = (Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2;
                    if (incomplete.isActive()) {
                        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
                        if (orPromoteCancellingList != null) {
                            Finishing finishing = new Finishing(orPromoteCancellingList, th);
                            AtomicRef atomicRef = this._state;
                            atomicRef.getClass();
                            if (AtomicRef.FU.compareAndSet(atomicRef, incomplete, finishing)) {
                                notifyCancelling(orPromoteCancellingList, th);
                                symbol = JobSupportKt.COMPLETING_ALREADY;
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Object tryMakeCompleting = tryMakeCompleting(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2, new CompletedExceptionally(th, false));
                        if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                            throw new IllegalStateException(("Cannot happen in " + state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).toString());
                        }
                        if (tryMakeCompleting != JobSupportKt.COMPLETING_RETRY) {
                            obj2 = tryMakeCompleting;
                            break;
                        }
                    }
                } else {
                    synchronized (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2) {
                        try {
                            if (((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2)._exceptionsHolder.value == JobSupportKt.SEALED) {
                                symbol = JobSupportKt.TOO_LATE_TO_CANCEL;
                            } else {
                                boolean isCancelling = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).isCancelling();
                                if (obj != null || !isCancelling) {
                                    if (th == null) {
                                        th = createCauseException(obj);
                                    }
                                    ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).addExceptionLocked(th);
                                }
                                Throwable rootCause = isCancelling ? null : ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).getRootCause();
                                if (rootCause != null) {
                                    notifyCancelling(((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host2).list, rootCause);
                                }
                                symbol = JobSupportKt.COMPLETING_ALREADY;
                            }
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
            }
            obj2 = symbol;
        }
        if (obj2 != JobSupportKt.COMPLETING_ALREADY && obj2 != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            if (obj2 == JobSupportKt.TOO_LATE_TO_CANCEL) {
                return false;
            }
            afterCompletion(obj2);
        }
        return true;
    }

    public void cancelInternal(Throwable th) {
        cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(th);
    }

    public final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle childHandle = (ChildHandle) this._parentHandle.value;
        return (childHandle == null || childHandle == NonDisposableHandle.INSTANCE) ? z : childHandle.childCancelled(th) || z;
    }

    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(th) && getHandlesException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
    }

    public final void completeStateFinalization(Incomplete incomplete, Object obj) {
        ChildHandle childHandle = (ChildHandle) this._parentHandle.value;
        if (childHandle != null) {
            childHandle.dispose();
            this._parentHandle.value = NonDisposableHandle.INSTANCE;
        }
        CompletionHandlerException completionHandlerException = null;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
                return;
            }
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) list.getNext(); !lockFreeLinkedListNode.equals(list); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            kotlin.ExceptionsKt.addSuppressed(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(completionHandlerException);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    public final Throwable createCauseException(Object obj) {
        CancellationException cancellationException;
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new JobCancellationException(cancellationExceptionMessage(), null, this) : th;
        }
        JobSupport jobSupport = (JobSupport) obj;
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = jobSupport.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) {
            cancellationException = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
            cancellationException = ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
        } else {
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).toString());
            }
            cancellationException = null;
        }
        CancellationException cancellationException2 = cancellationException instanceof CancellationException ? cancellationException : null;
        if (cancellationException2 == null) {
            cancellationException2 = new JobCancellationException("Parent job is ".concat(stateString(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host)), cancellationException, jobSupport);
        }
        return cancellationException2;
    }

    public final Object finalizeFinishingState(Finishing finishing, Object obj) {
        Throwable finalRootCause;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        synchronized (finishing) {
            finishing.isCancelling();
            List<Throwable> sealLocked = finishing.sealLocked(th);
            finalRootCause = getFinalRootCause(finishing, sealLocked);
            if (finalRootCause != null && sealLocked.size() > 1) {
                Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(sealLocked.size()));
                for (Throwable th2 : sealLocked) {
                    if (th2 != finalRootCause && th2 != finalRootCause && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                        kotlin.ExceptionsKt.addSuppressed(finalRootCause, th2);
                    }
                }
            }
        }
        if (finalRootCause != null && finalRootCause != th) {
            obj = new CompletedExceptionally(finalRootCause, false);
        }
        if (finalRootCause != null && (cancelParent(finalRootCause) || handleJobException(finalRootCause))) {
            AtomicBoolean atomicBoolean = ((CompletedExceptionally) obj)._handled;
            atomicBoolean.getClass();
            AtomicBoolean.FU.compareAndSet(atomicBoolean, 0, 1);
        }
        onCompletionInternal(obj);
        this._state.compareAndSet(finishing, obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj);
        completeStateFinalization(finishing, obj);
        return obj;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        return CoroutineContext.DefaultImpls.get(this, key);
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        CancellationException cancellationException;
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing)) {
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally)) {
                return new JobCancellationException(DebugStringsKt.getClassSimpleName(this).concat(" has completed normally"), null, this);
            }
            Throwable th = ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new JobCancellationException(cancellationExceptionMessage(), th, this) : cancellationException;
        }
        Throwable rootCause = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
        if (rootCause == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String concat = DebugStringsKt.getClassSimpleName(this).concat(" is cancelling");
        cancellationException = rootCause instanceof CancellationException ? (CancellationException) rootCause : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (concat == null) {
            concat = cancellationExceptionMessage();
        }
        return new JobCancellationException(concat, rootCause, this);
    }

    public final Throwable getFinalRootCause(Finishing finishing, List list) {
        Object obj;
        Object obj2 = null;
        if (list.isEmpty()) {
            if (finishing.isCancelling()) {
                return new JobCancellationException(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!(((Throwable) obj) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) list.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    public boolean getHandlesException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Job.Key.$$INSTANCE;
    }

    public boolean getOnCancelComplete$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this instanceof CompletableDeferredImpl;
    }

    public final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + incomplete).toString());
    }

    @Override // kotlinx.coroutines.Job
    public final Job getParent() {
        ChildHandle childHandle = (ChildHandle) this._parentHandle.value;
        if (childHandle != null) {
            return childHandle.getParent();
        }
        return null;
    }

    public final Object getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        AtomicRef atomicRef = this._state;
        while (true) {
            Object obj = atomicRef.value;
            if (!(obj instanceof JobSupport$addLastAtomic$$inlined$addLastIf$1)) {
                return obj;
            }
            ((JobSupport$addLastAtomic$$inlined$addLastIf$1) obj).perform(this);
        }
    }

    public boolean handleJobException(Throwable th) {
        return false;
    }

    public final void initParentJob(Job job) {
        NonDisposableHandle nonDisposableHandle = NonDisposableHandle.INSTANCE;
        if (job == null) {
            this._parentHandle.value = nonDisposableHandle;
            return;
        }
        job.start();
        ChildHandle attachChild = job.attachChild(this);
        this._parentHandle.value = attachChild;
        if (isCompleted()) {
            attachChild.dispose();
            this._parentHandle.value = nonDisposableHandle;
        }
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1 function1) {
        return invokeOnCompletion(false, true, function1);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) && ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled$1() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) || ((state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) && ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isCancelling());
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() instanceof Incomplete);
    }

    public boolean isScopedCoroutine() {
        return this instanceof BlockingCoroutine;
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation continuation) {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        Unit unit;
        do {
            state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            boolean z = state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete;
            unit = Unit.INSTANCE;
            if (!z) {
                JobKt.ensureActive(continuation.getContext());
                return unit;
            }
        } while (startInternal(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) < 0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        cancellableContinuationImpl.invokeOnCancellation(new DisposeOnCancel(invokeOnCompletion(false, true, new ResumeOnCompletion(cancellableContinuationImpl))));
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = unit;
        }
        return result == coroutineSingletons ? result : unit;
    }

    public final boolean makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                return false;
            }
            if (tryMakeCompleting == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        afterCompletion(tryMakeCompleting);
        return true;
    }

    public final Object makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
                throw new IllegalStateException(str, completedExceptionally != null ? completedExceptionally.cause : null);
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return CoroutineContext.DefaultImpls.minusKey(this, key);
    }

    public String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    public final void notifyCancelling(NodeList nodeList, Throwable th) {
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) nodeList.getNext(); !lockFreeLinkedListNode.equals(nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        kotlin.ExceptionsKt.addSuppressed(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(completionHandlerException);
        }
        cancelParent(th);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    public final void promoteSingleToNodeList(JobNode jobNode) {
        NodeList nodeList = new NodeList();
        jobNode.getClass();
        nodeList._prev.lazySet(jobNode);
        nodeList._next.lazySet(jobNode);
        while (true) {
            if (jobNode.getNext() != jobNode) {
                break;
            }
            AtomicRef atomicRef = jobNode._next;
            atomicRef.getClass();
            if (AtomicRef.FU.compareAndSet(atomicRef, jobNode, nodeList)) {
                nodeList.finishAdd(jobNode);
                break;
            }
        }
        this._state.compareAndSet(jobNode, jobNode.getNextNode());
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    public final int startInternal(Object obj) {
        boolean z = obj instanceof Empty;
        AtomicRef atomicRef = this._state;
        if (z) {
            if (((Empty) obj).isActive) {
                return 0;
            }
            Empty empty = JobSupportKt.EMPTY_ACTIVE;
            atomicRef.getClass();
            if (!AtomicRef.FU.compareAndSet(atomicRef, obj, empty)) {
                return -1;
            }
            onStart();
            return 1;
        }
        if (!(obj instanceof InactiveNodeList)) {
            return 0;
        }
        NodeList nodeList = ((InactiveNodeList) obj).list;
        atomicRef.getClass();
        if (!AtomicRef.FU.compareAndSet(atomicRef, obj, nodeList)) {
            return -1;
        }
        onStart();
        return 1;
    }

    public final String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() + "{" + stateString(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) + "}", "@", DebugStringsKt.getHexAddress(this));
    }

    public final Object tryMakeCompleting(Object obj, Object obj2) {
        DisposableHandle invokeOnCompletion;
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.COMPLETING_ALREADY;
        }
        if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            Incomplete incomplete = (Incomplete) obj;
            AtomicRef atomicRef = this._state;
            Object incompleteStateBox = obj2 instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj2) : obj2;
            atomicRef.getClass();
            if (!AtomicRef.FU.compareAndSet(atomicRef, incomplete, incompleteStateBox)) {
                return JobSupportKt.COMPLETING_RETRY;
            }
            onCompletionInternal(obj2);
            completeStateFinalization(incomplete, obj2);
            return obj2;
        }
        Incomplete incomplete2 = (Incomplete) obj;
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete2);
        if (orPromoteCancellingList == null) {
            return JobSupportKt.COMPLETING_RETRY;
        }
        ChildHandleNode childHandleNode = null;
        Finishing finishing = incomplete2 instanceof Finishing ? (Finishing) incomplete2 : null;
        if (finishing == null) {
            finishing = new Finishing(orPromoteCancellingList, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (finishing) {
            if (finishing._isCompleting.getValue()) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            finishing._isCompleting._value = 1;
            if (finishing != incomplete2) {
                AtomicRef atomicRef2 = this._state;
                atomicRef2.getClass();
                if (!AtomicRef.FU.compareAndSet(atomicRef2, incomplete2, finishing)) {
                    return JobSupportKt.COMPLETING_RETRY;
                }
            }
            boolean isCancelling = finishing.isCancelling();
            CompletedExceptionally completedExceptionally = obj2 instanceof CompletedExceptionally ? (CompletedExceptionally) obj2 : null;
            if (completedExceptionally != null) {
                finishing.addExceptionLocked(completedExceptionally.cause);
            }
            Throwable rootCause = finishing.getRootCause();
            if (isCancelling) {
                rootCause = null;
            }
            ref$ObjectRef.element = rootCause;
            if (rootCause != null) {
                notifyCancelling(orPromoteCancellingList, rootCause);
            }
            ChildHandleNode childHandleNode2 = incomplete2 instanceof ChildHandleNode ? (ChildHandleNode) incomplete2 : null;
            if (childHandleNode2 == null) {
                NodeList list = incomplete2.getList();
                if (list != null) {
                    childHandleNode = nextChild(list);
                }
            } else {
                childHandleNode = childHandleNode2;
            }
            if (childHandleNode != null) {
                do {
                    invokeOnCompletion = childHandleNode.childJob.invokeOnCompletion((r5 & 1) == 0, (r5 & 2) != 0, new ChildCompletion(this, finishing, childHandleNode, obj2));
                    if (invokeOnCompletion != NonDisposableHandle.INSTANCE) {
                        return JobSupportKt.COMPLETING_WAITING_CHILDREN;
                    }
                    childHandleNode = nextChild(childHandleNode);
                } while (childHandleNode != null);
            }
            return finalizeFinishingState(finishing, obj2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [kotlinx.coroutines.InactiveNodeList] */
    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1) {
        JobNode jobNode;
        Throwable th;
        if (z) {
            jobNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            jobNode = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.job = this;
        while (true) {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Empty) {
                Empty empty = (Empty) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
                if (empty.isActive) {
                    AtomicRef atomicRef = this._state;
                    atomicRef.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, jobNode)) {
                        return jobNode;
                    }
                } else {
                    NodeList nodeList = new NodeList();
                    if (!empty.isActive) {
                        nodeList = new InactiveNodeList(nodeList);
                    }
                    this._state.compareAndSet(empty, nodeList);
                }
            } else {
                if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete)) {
                    if (z2) {
                        CompletedExceptionally completedExceptionally = state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally ? (CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host : null;
                        function1.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                    }
                    return NonDisposableHandle.INSTANCE;
                }
                NodeList list = ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getList();
                if (list == null) {
                    promoteSingleToNodeList((JobNode) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
                } else {
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    if (z && (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing)) {
                        synchronized (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) {
                            try {
                                th = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
                                if (th != null) {
                                    if ((function1 instanceof ChildHandleNode) && !((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host)._isCompleting.getValue()) {
                                    }
                                }
                                if (addLastAtomic((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, list, jobNode)) {
                                    if (th == null) {
                                        return jobNode;
                                    }
                                    disposableHandle = jobNode;
                                }
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } else {
                        th = null;
                    }
                    if (th != null) {
                        if (z2) {
                            function1.invoke(th);
                        }
                        return disposableHandle;
                    }
                    if (addLastAtomic((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, list, jobNode)) {
                        return jobNode;
                    }
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChildCompletion extends JobNode {
        public final ChildHandleNode child;
        public final JobSupport parent;
        public final Object proposedUpdate;
        public final Finishing state;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        @Override // kotlinx.coroutines.JobNode
        public final void invoke(Throwable th) {
            DisposableHandle invokeOnCompletion;
            JobSupport jobSupport = this.parent;
            jobSupport.getClass();
            ChildHandleNode nextChild = JobSupport.nextChild(this.child);
            Finishing finishing = this.state;
            Object obj = this.proposedUpdate;
            if (nextChild != null) {
                do {
                    invokeOnCompletion = nextChild.childJob.invokeOnCompletion((r5 & 1) == 0, (r5 & 2) != 0, new ChildCompletion(jobSupport, finishing, nextChild, obj));
                    if (invokeOnCompletion != NonDisposableHandle.INSTANCE) {
                        return;
                    } else {
                        nextChild = JobSupport.nextChild(nextChild);
                    }
                } while (nextChild != null);
            }
            jobSupport.afterCompletion(jobSupport.finalizeFinishingState(finishing, obj));
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }
    }

    public void afterCompletion(Object obj) {
    }

    public void handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    public void onCompletionInternal(Object obj) {
    }

    public void onStart() {
    }
}
