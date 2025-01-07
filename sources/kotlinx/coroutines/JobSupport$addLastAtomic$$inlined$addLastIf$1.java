package kotlinx.coroutines;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class JobSupport$addLastAtomic$$inlined$addLastIf$1 {
    public final /* synthetic */ Incomplete $expect$inlined;
    public final AtomicRef _consensus = AtomicFU.atomic(AtomicKt.NO_DECISION);
    public final JobNode newNode;
    public NodeList oldNext;
    public final /* synthetic */ JobSupport this$0;

    public JobSupport$addLastAtomic$$inlined$addLastIf$1(JobNode jobNode, JobSupport jobSupport, Incomplete incomplete) {
        this.this$0 = jobSupport;
        this.$expect$inlined = incomplete;
        this.newNode = jobNode;
    }

    public final Object perform(Object obj) {
        Object obj2 = this._consensus.value;
        Symbol symbol = AtomicKt.NO_DECISION;
        if (obj2 == symbol) {
            obj2 = this.this$0.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == this.$expect$inlined ? null : LockFreeLinkedListKt.CONDITION_FALSE;
            Object obj3 = this._consensus.value;
            if (obj3 != symbol) {
                obj2 = obj3;
            } else {
                AtomicRef atomicRef = this._consensus;
                atomicRef.getClass();
                if (!AtomicRef.FU.compareAndSet(atomicRef, symbol, obj2)) {
                    obj2 = this._consensus.value;
                }
            }
        }
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
        boolean z = obj2 == null;
        JobNode jobNode = this.newNode;
        Incomplete incomplete = z ? jobNode : this.oldNext;
        if (incomplete != null) {
            AtomicRef atomicRef2 = lockFreeLinkedListNode._next;
            atomicRef2.getClass();
            if (AtomicRef.FU.compareAndSet(atomicRef2, this, incomplete) && z) {
                NodeList nodeList = this.oldNext;
                Intrinsics.checkNotNull(nodeList);
                jobNode.finishAdd(nodeList);
            }
        }
        return obj2;
    }

    public final String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(DebugStringsKt.getClassSimpleName(this), "@", DebugStringsKt.getHexAddress(this));
    }
}
