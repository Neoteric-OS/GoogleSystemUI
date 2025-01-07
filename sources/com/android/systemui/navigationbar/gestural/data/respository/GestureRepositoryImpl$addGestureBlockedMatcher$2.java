package com.android.systemui.navigationbar.gestural.data.respository;

import com.android.systemui.navigationbar.gestural.domain.TaskMatcher;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class GestureRepositoryImpl$addGestureBlockedMatcher$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ TaskMatcher $matcher;
    int label;
    final /* synthetic */ GestureRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GestureRepositoryImpl$addGestureBlockedMatcher$2(GestureRepositoryImpl gestureRepositoryImpl, TaskMatcher taskMatcher, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gestureRepositoryImpl;
        this.$matcher = taskMatcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GestureRepositoryImpl$addGestureBlockedMatcher$2(this.this$0, this.$matcher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        GestureRepositoryImpl$addGestureBlockedMatcher$2 gestureRepositoryImpl$addGestureBlockedMatcher$2 = (GestureRepositoryImpl$addGestureBlockedMatcher$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        gestureRepositoryImpl$addGestureBlockedMatcher$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.this$0._gestureBlockedMatchers.getValue();
        boolean contains = set.contains(this.$matcher);
        Unit unit = Unit.INSTANCE;
        if (contains) {
            return unit;
        }
        StateFlowImpl stateFlowImpl = this.this$0._gestureBlockedMatchers;
        Set mutableSet = CollectionsKt.toMutableSet(set);
        mutableSet.add(this.$matcher);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, mutableSet);
        return unit;
    }
}
