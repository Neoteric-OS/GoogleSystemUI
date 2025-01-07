package com.android.systemui.navigationbar.gestural.domain;

import com.android.systemui.navigationbar.gestural.data.respository.GestureRepositoryImpl;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor;
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
final class GestureInteractor$addGestureBlockedMatcher$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ GestureInteractor.Scope $gestureScope;
    final /* synthetic */ TaskMatcher $matcher;
    int label;
    final /* synthetic */ GestureInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GestureInteractor$addGestureBlockedMatcher$1(GestureInteractor.Scope scope, GestureInteractor gestureInteractor, TaskMatcher taskMatcher, Continuation continuation) {
        super(2, continuation);
        this.$gestureScope = scope;
        this.this$0 = gestureInteractor;
        this.$matcher = taskMatcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GestureInteractor$addGestureBlockedMatcher$1(this.$gestureScope, this.this$0, this.$matcher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GestureInteractor$addGestureBlockedMatcher$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int ordinal = this.$gestureScope.ordinal();
            if (ordinal == 0) {
                StateFlowImpl stateFlowImpl = this.this$0._localGestureBlockedMatchers;
                Set mutableSet = CollectionsKt.toMutableSet((Iterable) stateFlowImpl.getValue());
                mutableSet.add(this.$matcher);
                this.label = 1;
                stateFlowImpl.emit(mutableSet, this);
                if (unit == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (ordinal == 1) {
                GestureRepositoryImpl gestureRepositoryImpl = this.this$0.gestureRepository;
                TaskMatcher taskMatcher = this.$matcher;
                this.label = 2;
                if (gestureRepositoryImpl.addGestureBlockedMatcher(taskMatcher, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
