package com.android.systemui.statusbar.pipeline.mobile.data.repository.demo;

import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DemoMobileConnectionsRepository$subscriptions$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DemoMobileConnectionsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DemoMobileConnectionsRepository$subscriptions$1(DemoMobileConnectionsRepository demoMobileConnectionsRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = demoMobileConnectionsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DemoMobileConnectionsRepository$subscriptions$1 demoMobileConnectionsRepository$subscriptions$1 = new DemoMobileConnectionsRepository$subscriptions$1(this.this$0, continuation);
        demoMobileConnectionsRepository$subscriptions$1.L$0 = obj;
        return demoMobileConnectionsRepository$subscriptions$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DemoMobileConnectionsRepository$subscriptions$1 demoMobileConnectionsRepository$subscriptions$1 = (DemoMobileConnectionsRepository$subscriptions$1) create((List) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        demoMobileConnectionsRepository$subscriptions$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        DemoMobileConnectionsRepository demoMobileConnectionsRepository = this.this$0;
        demoMobileConnectionsRepository.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((SubscriptionModel) it.next()).subscriptionId));
        }
        Map map = demoMobileConnectionsRepository.connectionRepoCache;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (arrayList.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        demoMobileConnectionsRepository.connectionRepoCache = new LinkedHashMap(linkedHashMap);
        return Unit.INSTANCE;
    }
}
