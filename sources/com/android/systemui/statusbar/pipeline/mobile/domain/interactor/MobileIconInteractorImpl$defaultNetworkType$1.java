package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconInteractorImpl$defaultNetworkType$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        MobileIconInteractorImpl$defaultNetworkType$1 mobileIconInteractorImpl$defaultNetworkType$1 = new MobileIconInteractorImpl$defaultNetworkType$1(4, (Continuation) obj4);
        mobileIconInteractorImpl$defaultNetworkType$1.L$0 = (ResolvedNetworkType) obj;
        mobileIconInteractorImpl$defaultNetworkType$1.L$1 = (Map) obj2;
        mobileIconInteractorImpl$defaultNetworkType$1.L$2 = (SignalIcon$MobileIconGroup) obj3;
        return mobileIconInteractorImpl$defaultNetworkType$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ResolvedNetworkType resolvedNetworkType = (ResolvedNetworkType) this.L$0;
        Map map = (Map) this.L$1;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = (SignalIcon$MobileIconGroup) this.L$2;
        if (resolvedNetworkType instanceof ResolvedNetworkType.CarrierMergedNetworkType) {
            ((ResolvedNetworkType.CarrierMergedNetworkType) resolvedNetworkType).getClass();
            return ResolvedNetworkType.CarrierMergedNetworkType.iconGroupOverride;
        }
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup2 = (SignalIcon$MobileIconGroup) map.get(resolvedNetworkType.getLookupKey());
        return signalIcon$MobileIconGroup2 == null ? signalIcon$MobileIconGroup : signalIcon$MobileIconGroup2;
    }
}
