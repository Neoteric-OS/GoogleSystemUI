package com.google.android.systemui.dagger;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CoroutineScopeCoreStartable$start$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CoroutineScopeCoreStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineScopeCoreStartable$start$1(CoroutineScopeCoreStartable coroutineScopeCoreStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = coroutineScopeCoreStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CoroutineScopeCoreStartable$start$1 coroutineScopeCoreStartable$start$1 = new CoroutineScopeCoreStartable$start$1(this.this$0, continuation);
        coroutineScopeCoreStartable$start$1.L$0 = obj;
        return coroutineScopeCoreStartable$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CoroutineScopeCoreStartable$start$1 coroutineScopeCoreStartable$start$1 = (CoroutineScopeCoreStartable$start$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        coroutineScopeCoreStartable$start$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Iterator it = this.this$0.coroutineInitializers.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            BuildersKt.launch$default(coroutineScope, null, null, new CoroutineScopeCoreStartable$start$1$1$1(2, null), 3);
        }
        return Unit.INSTANCE;
    }
}
