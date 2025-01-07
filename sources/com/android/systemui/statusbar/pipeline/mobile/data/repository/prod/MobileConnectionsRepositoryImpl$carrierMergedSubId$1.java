package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileConnectionsRepositoryImpl$carrierMergedSubId$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        MobileConnectionsRepositoryImpl$carrierMergedSubId$1 mobileConnectionsRepositoryImpl$carrierMergedSubId$1 = new MobileConnectionsRepositoryImpl$carrierMergedSubId$1(4, (Continuation) obj4);
        mobileConnectionsRepositoryImpl$carrierMergedSubId$1.L$0 = (WifiNetworkModel) obj;
        mobileConnectionsRepositoryImpl$carrierMergedSubId$1.L$1 = (DefaultConnectionModel) obj2;
        mobileConnectionsRepositoryImpl$carrierMergedSubId$1.Z$0 = booleanValue;
        return mobileConnectionsRepositoryImpl$carrierMergedSubId$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) this.L$0;
        DefaultConnectionModel defaultConnectionModel = (DefaultConnectionModel) this.L$1;
        boolean z = defaultConnectionModel.carrierMerged.isDefault || defaultConnectionModel.wifi.isDefault || this.Z$0;
        if ((wifiNetworkModel instanceof WifiNetworkModel.CarrierMerged) && z) {
            return new Integer(((WifiNetworkModel.CarrierMerged) wifiNetworkModel).subscriptionId);
        }
        return null;
    }
}
