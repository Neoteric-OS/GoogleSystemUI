package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Removed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class JobNode extends LockFreeLinkedListNode implements DisposableHandle, Incomplete, Function1 {
    public JobSupport job;

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        Object next;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed;
        AtomicRef atomicRef;
        AtomicRef atomicRef2;
        Empty empty;
        JobSupport jobSupport = this.job;
        if (jobSupport == null) {
            jobSupport = null;
        }
        do {
            state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = jobSupport.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof JobNode)) {
                if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) || ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getList() == null) {
                    return;
                }
                do {
                    next = getNext();
                    if (next instanceof Removed) {
                        LockFreeLinkedListNode lockFreeLinkedListNode2 = ((Removed) next).ref;
                        return;
                    }
                    if (next == this) {
                        return;
                    }
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                    removed = (Removed) lockFreeLinkedListNode._removedRef.value;
                    if (removed == null) {
                        removed = new Removed(lockFreeLinkedListNode);
                        lockFreeLinkedListNode._removedRef.lazySet(removed);
                    }
                    atomicRef = this._next;
                    atomicRef.getClass();
                } while (!AtomicRef.FU.compareAndSet(atomicRef, next, removed));
                lockFreeLinkedListNode.correctPrev();
                return;
            }
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != this) {
                return;
            }
            atomicRef2 = jobSupport._state;
            empty = JobSupportKt.EMPTY_ACTIVE;
            atomicRef2.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef2, state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, empty));
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return null;
    }

    public abstract void invoke(Throwable th);

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        JobSupport jobSupport = this.job;
        if (jobSupport == null) {
            jobSupport = null;
        }
        return classSimpleName + "@" + hexAddress + "[job@" + DebugStringsKt.getHexAddress(jobSupport) + "]";
    }
}
