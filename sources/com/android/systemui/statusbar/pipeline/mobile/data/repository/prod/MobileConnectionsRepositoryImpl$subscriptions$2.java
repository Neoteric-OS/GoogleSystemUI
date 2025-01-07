package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileConnectionsRepositoryImpl$subscriptions$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MobileConnectionsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileConnectionsRepositoryImpl$subscriptions$2(MobileConnectionsRepositoryImpl mobileConnectionsRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mobileConnectionsRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MobileConnectionsRepositoryImpl$subscriptions$2 mobileConnectionsRepositoryImpl$subscriptions$2 = new MobileConnectionsRepositoryImpl$subscriptions$2(this.this$0, continuation);
        mobileConnectionsRepositoryImpl$subscriptions$2.L$0 = obj;
        return mobileConnectionsRepositoryImpl$subscriptions$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MobileConnectionsRepositoryImpl$subscriptions$2 mobileConnectionsRepositoryImpl$subscriptions$2 = (MobileConnectionsRepositoryImpl$subscriptions$2) create((List) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        mobileConnectionsRepositoryImpl$subscriptions$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MobileConnectionsRepositoryImpl mobileConnectionsRepositoryImpl = this.this$0;
        for (Map.Entry entry : mobileConnectionsRepositoryImpl.subIdRepositoryCache.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            FullMobileConnectionRepository fullMobileConnectionRepository = (FullMobileConnectionRepository) ((WeakReference) entry.getValue()).get();
            if (fullMobileConnectionRepository != null) {
                Integer num = (Integer) ((StateFlowImpl) mobileConnectionsRepositoryImpl.carrierMergedSubId.$$delegate_0).getValue();
                Boolean valueOf = Boolean.valueOf(num != null && intValue == num.intValue());
                StateFlowImpl stateFlowImpl = fullMobileConnectionRepository._isCarrierMerged;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
            }
        }
        return Unit.INSTANCE;
    }
}
