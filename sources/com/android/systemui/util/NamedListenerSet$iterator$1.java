package com.android.systemui.util;

import com.android.systemui.util.NamedListenerSet;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NamedListenerSet$iterator$1 extends RestrictedSuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ NamedListenerSet this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NamedListenerSet$iterator$1(NamedListenerSet namedListenerSet, Continuation continuation) {
        super(continuation);
        this.this$0 = namedListenerSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NamedListenerSet$iterator$1 namedListenerSet$iterator$1 = new NamedListenerSet$iterator$1(this.this$0, continuation);
        namedListenerSet$iterator$1.L$0 = obj;
        return namedListenerSet$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NamedListenerSet$iterator$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SequenceBuilderIterator sequenceBuilderIterator;
        Iterator it;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            it = this.this$0.listeners.iterator();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$1;
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (!it.hasNext()) {
            return Unit.INSTANCE;
        }
        Object obj2 = ((NamedListenerSet.NamedListener) it.next()).listener;
        this.L$0 = sequenceBuilderIterator;
        this.L$1 = it;
        this.label = 1;
        sequenceBuilderIterator.yield(obj2, this);
        return coroutineSingletons;
    }
}
