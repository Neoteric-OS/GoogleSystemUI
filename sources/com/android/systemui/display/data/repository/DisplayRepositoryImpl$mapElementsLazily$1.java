package com.android.systemui.display.data.repository;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DisplayRepositoryImpl$mapElementsLazily$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Function1 $createValue;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplayRepositoryImpl$mapElementsLazily$1(Continuation continuation, Function1 function1) {
        super(3, continuation);
        this.$createValue = function1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DisplayRepositoryImpl$mapElementsLazily$1 displayRepositoryImpl$mapElementsLazily$1 = new DisplayRepositoryImpl$mapElementsLazily$1((Continuation) obj3, this.$createValue);
        displayRepositoryImpl$mapElementsLazily$1.L$0 = (DisplayRepositoryImpl$mapElementsLazily$State) obj;
        displayRepositoryImpl$mapElementsLazily$1.L$1 = (Set) obj2;
        return displayRepositoryImpl$mapElementsLazily$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DisplayRepositoryImpl$mapElementsLazily$State displayRepositoryImpl$mapElementsLazily$State = (DisplayRepositoryImpl$mapElementsLazily$State) this.L$0;
        Set set = (Set) this.L$1;
        if (Intrinsics.areEqual(set, displayRepositoryImpl$mapElementsLazily$State.previousSet)) {
            return displayRepositoryImpl$mapElementsLazily$State;
        }
        Set minus = SetsKt.minus(displayRepositoryImpl$mapElementsLazily$State.previousSet, (Iterable) set);
        Set minus2 = SetsKt.minus(set, (Iterable) displayRepositoryImpl$mapElementsLazily$State.previousSet);
        LinkedHashMap linkedHashMap = new LinkedHashMap(displayRepositoryImpl$mapElementsLazily$State.valueMap);
        Function1 function1 = this.$createValue;
        for (Object obj2 : minus2) {
            Object invoke = function1.invoke(obj2);
            if (invoke != null) {
                linkedHashMap.put(obj2, invoke);
            }
        }
        Iterator it = minus.iterator();
        while (it.hasNext()) {
            linkedHashMap.remove(it.next());
        }
        return new DisplayRepositoryImpl$mapElementsLazily$State(set, linkedHashMap, CollectionsKt.toSet(linkedHashMap.values()));
    }
}
