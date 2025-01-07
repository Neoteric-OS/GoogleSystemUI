package com.google.android.systemui.columbus.fetchers;

import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import com.google.android.systemui.columbus.fetchers.GateFetcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GateFetcher$getBlockingGateFlow$1 implements Function {
    public final /* synthetic */ GateFetcher this$0;

    public GateFetcher$getBlockingGateFlow$1(GateFetcher gateFetcher) {
        this.this$0 = gateFetcher;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Set set = ((GateFetcher.GateCollectionKey) obj).gateSet;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
        Iterator it = set.iterator();
        if (it.hasNext()) {
            throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
        }
        final Flow[] flowArr = (Flow[]) CollectionsKt.toList(arrayList).toArray(new Flow[0]);
        return FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1$3, reason: invalid class name */
            public final class AnonymousClass3 extends SuspendLambda implements Function3 {
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(3, (Continuation) obj3);
                    anonymousClass3.L$0 = (FlowCollector) obj;
                    anonymousClass3.L$1 = (Object[]) obj2;
                    return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Pair pair;
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Pair[] pairArr = (Pair[]) ((Object[]) this.L$1);
                        int length = pairArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                pair = null;
                                break;
                            }
                            pair = pairArr[i2];
                            if (((Boolean) pair.getSecond()).booleanValue()) {
                                break;
                            }
                            i2++;
                        }
                        if (pair != null && pair.getFirst() != null) {
                            throw new ClassCastException();
                        }
                        this.label = 1;
                        if (flowCollector.emit(null, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                final Flow[] flowArr2 = flowArr;
                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new Pair[flowArr2.length];
                    }
                }, new AnonymousClass3(3, null), flowCollector, flowArr2);
                return combineInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? combineInternal : Unit.INSTANCE;
            }
        }, this.this$0.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
    }
}
