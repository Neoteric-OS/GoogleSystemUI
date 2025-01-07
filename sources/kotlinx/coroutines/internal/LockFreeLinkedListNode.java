package kotlinx.coroutines.internal;

import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LockFreeLinkedListNode {
    public final AtomicRef _next = AtomicFU.atomic(this);
    public final AtomicRef _prev = AtomicFU.atomic(this);
    public final AtomicRef _removedRef = AtomicFU.atomic((Object) null);

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        r5 = r3._next;
        r4 = ((kotlinx.coroutines.internal.Removed) r4).ref;
        r5.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
    
        if (kotlinx.atomicfu.AtomicRef.FU.compareAndSet(r5, r2, r4) != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.internal.LockFreeLinkedListNode correctPrev() {
        /*
            r8 = this;
        L0:
            kotlinx.atomicfu.AtomicRef r0 = r8._prev
            java.lang.Object r0 = r0.value
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        L8:
            r3 = r1
        L9:
            kotlinx.atomicfu.AtomicRef r4 = r2._next
            java.lang.Object r4 = r4.value
            if (r4 != r8) goto L21
            if (r0 != r2) goto L12
            return r2
        L12:
            kotlinx.atomicfu.AtomicRef r1 = r8._prev
            r1.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.atomicfu.AtomicRef.FU
            boolean r0 = r3.compareAndSet(r1, r0, r2)
            if (r0 != 0) goto L20
            goto L0
        L20:
            return r2
        L21:
            boolean r5 = r8.isRemoved()
            if (r5 == 0) goto L28
            return r1
        L28:
            if (r4 != 0) goto L2b
            return r2
        L2b:
            boolean r5 = r4 instanceof kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            if (r5 == 0) goto L35
            kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1 r4 = (kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1) r4
            r4.perform(r2)
            goto L0
        L35:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.Removed
            if (r5 == 0) goto L56
            if (r3 == 0) goto L4f
            kotlinx.atomicfu.AtomicRef r5 = r3._next
            kotlinx.coroutines.internal.Removed r4 = (kotlinx.coroutines.internal.Removed) r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.ref
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = kotlinx.atomicfu.AtomicRef.FU
            boolean r2 = r6.compareAndSet(r5, r2, r4)
            if (r2 != 0) goto L4d
            goto L0
        L4d:
            r2 = r3
            goto L8
        L4f:
            kotlinx.atomicfu.AtomicRef r2 = r2._prev
            java.lang.Object r2 = r2.value
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto L9
        L56:
            r3 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r7 = r3
            r3 = r2
            r2 = r7
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.correctPrev():kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    public final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        AtomicRef atomicRef;
        AtomicRef atomicRef2 = lockFreeLinkedListNode._prev;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) atomicRef2.value;
            if (getNext() != lockFreeLinkedListNode) {
                return;
            }
            atomicRef = lockFreeLinkedListNode._prev;
            atomicRef.getClass();
        } while (!AtomicRef.FU.compareAndSet(atomicRef, lockFreeLinkedListNode2, this));
        if (isRemoved()) {
            lockFreeLinkedListNode.correctPrev();
        }
    }

    public final Object getNext() {
        AtomicRef atomicRef = this._next;
        while (true) {
            Object obj = atomicRef.value;
            if (!(obj instanceof JobSupport$addLastAtomic$$inlined$addLastIf$1)) {
                return obj;
            }
            ((JobSupport$addLastAtomic$$inlined$addLastIf$1) obj).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Object next = getNext();
        Removed removed = next instanceof Removed ? (Removed) next : null;
        return (removed == null || (lockFreeLinkedListNode = removed.ref) == null) ? (LockFreeLinkedListNode) next : lockFreeLinkedListNode;
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public String toString() {
        return new LockFreeLinkedListNode$toString$1(this, DebugStringsKt.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + "@" + DebugStringsKt.getHexAddress(this);
    }
}
