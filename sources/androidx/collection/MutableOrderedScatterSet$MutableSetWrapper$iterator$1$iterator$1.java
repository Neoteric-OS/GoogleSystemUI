package androidx.collection;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1 extends RestrictedSuspendLambda implements Function2 {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MutableOrderedScatterSet this$0;
    final /* synthetic */ MutableOrderedScatterSet$MutableSetWrapper$iterator$1 this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1(MutableOrderedScatterSet mutableOrderedScatterSet, MutableOrderedScatterSet$MutableSetWrapper$iterator$1 mutableOrderedScatterSet$MutableSetWrapper$iterator$1, Continuation continuation) {
        super(continuation);
        this.this$0 = mutableOrderedScatterSet;
        this.this$1 = mutableOrderedScatterSet$MutableSetWrapper$iterator$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1 mutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1 = new MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1(this.this$0, this.this$1, continuation);
        mutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$0 = obj;
        return mutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SequenceBuilderIterator sequenceBuilderIterator;
        MutableOrderedScatterSet mutableOrderedScatterSet;
        MutableOrderedScatterSet$MutableSetWrapper$iterator$1 mutableOrderedScatterSet$MutableSetWrapper$iterator$1;
        long[] jArr;
        int i;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            mutableOrderedScatterSet = this.this$0;
            mutableOrderedScatterSet$MutableSetWrapper$iterator$1 = this.this$1;
            jArr = mutableOrderedScatterSet.nodes;
            i = mutableOrderedScatterSet.tail;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            jArr = (long[]) this.L$3;
            mutableOrderedScatterSet = (MutableOrderedScatterSet) this.L$2;
            mutableOrderedScatterSet$MutableSetWrapper$iterator$1 = (MutableOrderedScatterSet$MutableSetWrapper$iterator$1) this.L$1;
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (i == Integer.MAX_VALUE) {
            return Unit.INSTANCE;
        }
        int i3 = (int) ((jArr[i] >> 31) & 2147483647L);
        mutableOrderedScatterSet$MutableSetWrapper$iterator$1.current = i;
        Object obj2 = mutableOrderedScatterSet.elements[i];
        this.L$0 = sequenceBuilderIterator;
        this.L$1 = mutableOrderedScatterSet$MutableSetWrapper$iterator$1;
        this.L$2 = mutableOrderedScatterSet;
        this.L$3 = jArr;
        this.I$0 = i3;
        this.label = 1;
        sequenceBuilderIterator.yield(obj2, this);
        return coroutineSingletons;
    }
}
