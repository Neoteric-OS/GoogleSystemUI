package com.android.systemui.statusbar.pipeline.mobile.data.repository.demo;

import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.model.FakeNetworkEventModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModelKt;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model.FakeWifiEventModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DemoMobileConnectionsRepository$startProcessingCommands$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DemoMobileConnectionsRepository this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$startProcessingCommands$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ DemoMobileConnectionsRepository this$0;

        public /* synthetic */ AnonymousClass1(DemoMobileConnectionsRepository demoMobileConnectionsRepository, int i) {
            this.$r8$classId = i;
            this.this$0 = demoMobileConnectionsRepository;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            int i;
            switch (this.$r8$classId) {
                case 0:
                    FakeNetworkEventModel fakeNetworkEventModel = (FakeNetworkEventModel) obj;
                    DemoMobileConnectionsRepository demoMobileConnectionsRepository = this.this$0;
                    demoMobileConnectionsRepository.getClass();
                    if (fakeNetworkEventModel instanceof FakeNetworkEventModel.Mobile) {
                        demoMobileConnectionsRepository.processEnabledMobileState((FakeNetworkEventModel.Mobile) fakeNetworkEventModel);
                    } else if (fakeNetworkEventModel instanceof FakeNetworkEventModel.MobileDisabled) {
                        Integer num = ((FakeNetworkEventModel.MobileDisabled) fakeNetworkEventModel).subId;
                        StateFlowImpl stateFlowImpl = demoMobileConnectionsRepository._subscriptions;
                        if (!((List) stateFlowImpl.getValue()).isEmpty()) {
                            if (num != null) {
                                i = num.intValue();
                            } else if (((List) stateFlowImpl.getValue()).size() > 1) {
                                Log.d("DemoMobileConnectionsRepo", "processDisabledMobileState: Unable to infer subscription to disable. Specify subId using '-e slot <subId>'Known subIds: [" + CollectionsKt.joinToString$default((Iterable) stateFlowImpl.getValue(), ",", null, null, new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$subIdsString$1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj2) {
                                        return String.valueOf(((SubscriptionModel) obj2).subscriptionId);
                                    }
                                }, 30) + "]");
                            } else {
                                i = ((SubscriptionModel) ((List) stateFlowImpl.getValue()).get(0)).subscriptionId;
                            }
                            demoMobileConnectionsRepository.removeSubscription(i);
                        }
                    }
                    break;
                default:
                    FakeWifiEventModel fakeWifiEventModel = (FakeWifiEventModel) obj;
                    DemoMobileConnectionsRepository demoMobileConnectionsRepository2 = this.this$0;
                    demoMobileConnectionsRepository2.getClass();
                    if (fakeWifiEventModel instanceof FakeWifiEventModel.WifiDisabled) {
                        demoMobileConnectionsRepository2.disableCarrierMerged();
                    } else if (fakeWifiEventModel instanceof FakeWifiEventModel.Wifi) {
                        demoMobileConnectionsRepository2.disableCarrierMerged();
                    } else if (fakeWifiEventModel instanceof FakeWifiEventModel.CarrierMerged) {
                        FakeWifiEventModel.CarrierMerged carrierMerged = (FakeWifiEventModel.CarrierMerged) fakeWifiEventModel;
                        Integer num2 = demoMobileConnectionsRepository2.carrierMergedSubId;
                        int i2 = carrierMerged.subscriptionId;
                        if (num2 == null || num2.intValue() != i2) {
                            demoMobileConnectionsRepository2.disableCarrierMerged();
                        }
                        int i3 = carrierMerged.subscriptionId;
                        demoMobileConnectionsRepository2.maybeCreateSubscription(i3);
                        demoMobileConnectionsRepository2.carrierMergedSubId = Integer.valueOf(i3);
                        Integer valueOf = Integer.valueOf(i3);
                        StateFlowImpl stateFlowImpl2 = demoMobileConnectionsRepository2.defaultDataSubId;
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, valueOf);
                        DemoMobileConnectionRepository repoForSubId = demoMobileConnectionsRepository2.getRepoForSubId(i3);
                        Boolean bool = Boolean.TRUE;
                        StateFlowImpl stateFlowImpl3 = repoForSubId.dataEnabled;
                        stateFlowImpl3.getClass();
                        stateFlowImpl3.updateState(null, bool);
                        NetworkNameModel.IntentDerived intentDerived = new NetworkNameModel.IntentDerived("Carrier Merged Network");
                        StateFlowImpl stateFlowImpl4 = repoForSubId.networkName;
                        stateFlowImpl4.getClass();
                        stateFlowImpl4.updateState(null, intentDerived);
                        NetworkNameModel.SubscriptionDerived subscriptionDerived = new NetworkNameModel.SubscriptionDerived("Carrier Merged Network");
                        StateFlowImpl stateFlowImpl5 = repoForSubId.carrierName;
                        stateFlowImpl5.getClass();
                        stateFlowImpl5.updateState(null, subscriptionDerived);
                        StateFlowImpl stateFlowImpl6 = repoForSubId._carrierId;
                        stateFlowImpl6.getClass();
                        stateFlowImpl6.updateState(null, -1);
                        Boolean bool2 = Boolean.FALSE;
                        StateFlowImpl stateFlowImpl7 = repoForSubId.cdmaRoaming;
                        stateFlowImpl7.getClass();
                        stateFlowImpl7.updateState(null, bool2);
                        int i4 = carrierMerged.level;
                        Integer valueOf2 = Integer.valueOf(i4);
                        StateFlowImpl stateFlowImpl8 = repoForSubId._primaryLevel;
                        stateFlowImpl8.getClass();
                        stateFlowImpl8.updateState(null, valueOf2);
                        Integer valueOf3 = Integer.valueOf(i4);
                        StateFlowImpl stateFlowImpl9 = repoForSubId._cdmaLevel;
                        stateFlowImpl9.getClass();
                        stateFlowImpl9.updateState(null, valueOf3);
                        DataActivityModel mobileDataActivityModel = DataActivityModelKt.toMobileDataActivityModel(carrierMerged.activity);
                        StateFlowImpl stateFlowImpl10 = repoForSubId._dataActivityDirection;
                        stateFlowImpl10.getClass();
                        stateFlowImpl10.updateState(null, mobileDataActivityModel);
                        ResolvedNetworkType.CarrierMergedNetworkType carrierMergedNetworkType = ResolvedNetworkType.CarrierMergedNetworkType.INSTANCE;
                        StateFlowImpl stateFlowImpl11 = repoForSubId._resolvedNetworkType;
                        stateFlowImpl11.getClass();
                        stateFlowImpl11.updateState(null, carrierMergedNetworkType);
                        DataConnectionState dataConnectionState = DataConnectionState.Connected;
                        StateFlowImpl stateFlowImpl12 = repoForSubId._dataConnectionState;
                        stateFlowImpl12.getClass();
                        stateFlowImpl12.updateState(null, dataConnectionState);
                        StateFlowImpl stateFlowImpl13 = repoForSubId._isRoaming;
                        stateFlowImpl13.getClass();
                        stateFlowImpl13.updateState(null, bool2);
                        StateFlowImpl stateFlowImpl14 = repoForSubId._isEmergencyOnly;
                        stateFlowImpl14.getClass();
                        stateFlowImpl14.updateState(null, bool2);
                        repoForSubId._operatorAlphaShort.setValue(null);
                        StateFlowImpl stateFlowImpl15 = repoForSubId._isInService;
                        stateFlowImpl15.getClass();
                        stateFlowImpl15.updateState(null, bool);
                        StateFlowImpl stateFlowImpl16 = repoForSubId._isGsm;
                        stateFlowImpl16.getClass();
                        stateFlowImpl16.updateState(null, bool2);
                        StateFlowImpl stateFlowImpl17 = repoForSubId._carrierNetworkChangeActive;
                        stateFlowImpl17.getClass();
                        stateFlowImpl17.updateState(null, bool2);
                        StateFlowImpl stateFlowImpl18 = repoForSubId.isAllowedDuringAirplaneMode;
                        stateFlowImpl18.getClass();
                        stateFlowImpl18.updateState(null, bool);
                        StateFlowImpl stateFlowImpl19 = repoForSubId.hasPrioritizedNetworkCapabilities;
                        stateFlowImpl19.getClass();
                        stateFlowImpl19.updateState(null, bool2);
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DemoMobileConnectionsRepository$startProcessingCommands$1(DemoMobileConnectionsRepository demoMobileConnectionsRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = demoMobileConnectionsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DemoMobileConnectionsRepository$startProcessingCommands$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DemoMobileConnectionsRepository$startProcessingCommands$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DemoMobileConnectionsRepository demoMobileConnectionsRepository = this.this$0;
            ReadonlySharedFlow readonlySharedFlow = demoMobileConnectionsRepository.mobileDataSource.mobileEvents;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(demoMobileConnectionsRepository, 0);
            this.label = 1;
            Object collect = readonlySharedFlow.$$delegate_0.collect(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.AnonymousClass2(anonymousClass1), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
