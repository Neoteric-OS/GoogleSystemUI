package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.os.ParcelUuid;
import android.telephony.CarrierConfigManager;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.util.CarrierConfigTracker;
import java.util.Collections;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconsInteractorImpl$filteredSubscriptions$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;
    final /* synthetic */ MobileIconsInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileIconsInteractorImpl$filteredSubscriptions$1(MobileIconsInteractorImpl mobileIconsInteractorImpl, Continuation continuation) {
        super(4, continuation);
        this.this$0 = mobileIconsInteractorImpl;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        MobileIconsInteractorImpl$filteredSubscriptions$1 mobileIconsInteractorImpl$filteredSubscriptions$1 = new MobileIconsInteractorImpl$filteredSubscriptions$1(this.this$0, (Continuation) obj4);
        mobileIconsInteractorImpl$filteredSubscriptions$1.L$0 = (List) obj;
        mobileIconsInteractorImpl$filteredSubscriptions$1.L$1 = (Integer) obj2;
        mobileIconsInteractorImpl$filteredSubscriptions$1.L$2 = (Integer) obj3;
        return mobileIconsInteractorImpl$filteredSubscriptions$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List singletonList;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        Integer num = (Integer) this.L$1;
        Integer num2 = (Integer) this.L$2;
        MobileIconsInteractorImpl mobileIconsInteractorImpl = this.this$0;
        mobileIconsInteractorImpl.getClass();
        if (list.size() != 2) {
            return list;
        }
        SubscriptionModel subscriptionModel = (SubscriptionModel) list.get(0);
        SubscriptionModel subscriptionModel2 = (SubscriptionModel) list.get(1);
        ParcelUuid parcelUuid = subscriptionModel.groupUuid;
        if (parcelUuid == null || !parcelUuid.equals(subscriptionModel2.groupUuid)) {
            return list;
        }
        boolean z = subscriptionModel.isOpportunistic;
        if (!z && !subscriptionModel2.isOpportunistic) {
            return list;
        }
        CarrierConfigTracker carrierConfigTracker = mobileIconsInteractorImpl.carrierConfigTracker;
        if (!carrierConfigTracker.mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfigLoaded) {
            carrierConfigTracker.mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfig = CarrierConfigManager.getDefaultConfig().getBoolean("always_show_primary_signal_bar_in_opportunistic_network_boolean");
            carrierConfigTracker.mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfigLoaded = true;
        }
        if (carrierConfigTracker.mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfig) {
            singletonList = z ? Collections.singletonList(subscriptionModel2) : Collections.singletonList(subscriptionModel);
        } else {
            if (num2 != null) {
                num = num2;
            }
            if (num != null) {
                if (subscriptionModel.subscriptionId == num.intValue()) {
                    singletonList = Collections.singletonList(subscriptionModel);
                }
            }
            singletonList = Collections.singletonList(subscriptionModel2);
        }
        return singletonList;
    }
}
