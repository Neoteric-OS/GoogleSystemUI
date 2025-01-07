package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Function;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SelectImplementation extends CancelHandler implements SelectInstance, Waiter {
    public final CoroutineContext context;
    public Object disposableHandleOrSegment;
    public final AtomicRef state = AtomicFU.atomic(SelectKt.STATE_REG);
    public List clauses = new ArrayList(2);
    public int indexInSegment = -1;
    public Object internalResult = SelectKt.NO_RESULT;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClauseData {
        public final Function block;
        public final Object clauseObject;
        public Object disposableHandleOrSegment;
        public int indexInSegment = -1;
        public final Function3 onCancellationConstructor;
        public final Symbol param;
        public final Function3 processResFunc;
        public final FunctionReferenceImpl regFunc;

        /* JADX WARN: Multi-variable type inference failed */
        public ClauseData(Object obj, Function3 function3, Function3 function32, Symbol symbol, Function function, Function3 function33) {
            this.clauseObject = obj;
            this.regFunc = (FunctionReferenceImpl) function3;
            this.processResFunc = function32;
            this.param = symbol;
            this.block = function;
            this.onCancellationConstructor = function33;
        }

        public final void dispose() {
            Object obj = this.disposableHandleOrSegment;
            if (obj instanceof Segment) {
                ((Segment) obj).onCancellation(this.indexInSegment, SelectImplementation.this.context);
                return;
            }
            DisposableHandle disposableHandle = obj instanceof DisposableHandle ? (DisposableHandle) obj : null;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
        }
    }

    public SelectImplementation(CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    public final Object complete(ContinuationImpl continuationImpl) {
        ClauseData clauseData = (ClauseData) this.state.value;
        Object obj = this.internalResult;
        List<ClauseData> list = this.clauses;
        if (list != null) {
            for (ClauseData clauseData2 : list) {
                if (clauseData2 != clauseData) {
                    clauseData2.dispose();
                }
            }
            this.state.value = SelectKt.STATE_COMPLETED;
            this.internalResult = SelectKt.NO_RESULT;
            this.clauses = null;
        }
        Object invoke = clauseData.processResFunc.invoke(clauseData.clauseObject, clauseData.param, obj);
        Symbol symbol = SelectKt.PARAM_CLAUSE_0;
        Function function = clauseData.block;
        return clauseData.param == symbol ? ((Function1) function).invoke(continuationImpl) : ((Function2) function).invoke(invoke, continuationImpl);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00cf A[PHI: r11
      0x00cf: PHI (r11v8 java.lang.Object) = (r11v7 java.lang.Object), (r11v1 java.lang.Object) binds: [B:17:0x00cc, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object doSelectSuspend(kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectImplementation.doSelectSuspend(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final ClauseData findClause(Object obj) {
        List list = this.clauses;
        Object obj2 = null;
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((ClauseData) next).clauseObject == obj) {
                obj2 = next;
                break;
            }
        }
        ClauseData clauseData = (ClauseData) obj2;
        if (clauseData != null) {
            return clauseData;
        }
        throw new IllegalStateException(("Clause with object " + obj + " is not found").toString());
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.Waiter
    public final void invokeOnCancellation(Segment segment, int i) {
        this.disposableHandleOrSegment = segment;
        this.indexInSegment = i;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.FunctionReferenceImpl] */
    public final void register(ClauseData clauseData, boolean z) {
        if (this.state.value instanceof ClauseData) {
            return;
        }
        if (!z) {
            Object obj = clauseData.clauseObject;
            List list = this.clauses;
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((ClauseData) it.next()).clauseObject == obj) {
                        throw new IllegalStateException(("Cannot use select clauses on the same object: " + obj).toString());
                    }
                }
            }
        }
        clauseData.regFunc.invoke(clauseData.clauseObject, this, clauseData.param);
        if (this.internalResult != SelectKt.NO_RESULT) {
            this.state.value = clauseData;
            return;
        }
        if (!z) {
            List list2 = this.clauses;
            Intrinsics.checkNotNull(list2);
            list2.add(clauseData);
        }
        clauseData.disposableHandleOrSegment = this.disposableHandleOrSegment;
        clauseData.indexInSegment = this.indexInSegment;
        this.disposableHandleOrSegment = null;
        this.indexInSegment = -1;
    }

    public final int trySelectInternal(Object obj, Object obj2) {
        while (true) {
            Object obj3 = this.state.value;
            if (obj3 instanceof CancellableContinuation) {
                ClauseData findClause = findClause(obj);
                if (findClause != null) {
                    Function3 function3 = findClause.onCancellationConstructor;
                    Function1 function1 = function3 != null ? (Function1) function3.invoke(this, findClause.param, obj2) : null;
                    AtomicRef atomicRef = this.state;
                    atomicRef.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef, obj3, findClause)) {
                        CancellableContinuation cancellableContinuation = (CancellableContinuation) obj3;
                        this.internalResult = obj2;
                        Function3 function32 = SelectKt.DUMMY_PROCESS_RESULT_FUNCTION;
                        Symbol tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, function1);
                        if (tryResume == null) {
                            this.internalResult = null;
                            return 2;
                        }
                        cancellableContinuation.completeResume(tryResume);
                        return 0;
                    }
                } else {
                    continue;
                }
            } else {
                if (Intrinsics.areEqual(obj3, SelectKt.STATE_COMPLETED) ? true : obj3 instanceof ClauseData) {
                    return 3;
                }
                if (Intrinsics.areEqual(obj3, SelectKt.STATE_CANCELLED)) {
                    return 2;
                }
                if (Intrinsics.areEqual(obj3, SelectKt.STATE_REG)) {
                    AtomicRef atomicRef2 = this.state;
                    List singletonList = Collections.singletonList(obj);
                    atomicRef2.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef2, obj3, singletonList)) {
                        return 1;
                    }
                } else {
                    if (!(obj3 instanceof List)) {
                        throw new IllegalStateException(("Unexpected state: " + obj3).toString());
                    }
                    AtomicRef atomicRef3 = this.state;
                    List plus = CollectionsKt.plus((Collection) obj3, obj);
                    atomicRef3.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef3, obj3, plus)) {
                        return 1;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CancelHandler
    public final void invoke(Throwable th) {
        Object obj;
        AtomicRef atomicRef = this.state;
        do {
            obj = atomicRef.value;
            if (obj == SelectKt.STATE_COMPLETED) {
                return;
            }
        } while (!AtomicRef.FU.compareAndSet(atomicRef, obj, SelectKt.STATE_CANCELLED));
        List list = this.clauses;
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ClauseData) it.next()).dispose();
        }
        this.internalResult = SelectKt.NO_RESULT;
        this.clauses = null;
    }
}
