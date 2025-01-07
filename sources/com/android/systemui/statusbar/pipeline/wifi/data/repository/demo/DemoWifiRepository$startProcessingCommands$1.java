package com.android.systemui.statusbar.pipeline.wifi.data.repository.demo;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModelKt;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model.FakeWifiEventModel;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DemoWifiRepository$startProcessingCommands$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DemoWifiRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DemoWifiRepository$startProcessingCommands$1(DemoWifiRepository demoWifiRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = demoWifiRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DemoWifiRepository$startProcessingCommands$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DemoWifiRepository$startProcessingCommands$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final DemoWifiRepository demoWifiRepository = this.this$0;
            ReadonlySharedFlow readonlySharedFlow = demoWifiRepository.dataSource.wifiEvents;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoWifiRepository$startProcessingCommands$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    FakeWifiEventModel fakeWifiEventModel = (FakeWifiEventModel) obj2;
                    DemoWifiRepository demoWifiRepository2 = DemoWifiRepository.this;
                    demoWifiRepository2.getClass();
                    boolean z = fakeWifiEventModel instanceof FakeWifiEventModel.Wifi;
                    StateFlowImpl stateFlowImpl = demoWifiRepository2._wifiNetwork;
                    StateFlowImpl stateFlowImpl2 = demoWifiRepository2._wifiActivity;
                    StateFlowImpl stateFlowImpl3 = demoWifiRepository2._isWifiDefault;
                    StateFlowImpl stateFlowImpl4 = demoWifiRepository2._isWifiEnabled;
                    if (z) {
                        FakeWifiEventModel.Wifi wifi = (FakeWifiEventModel.Wifi) fakeWifiEventModel;
                        Boolean bool = Boolean.TRUE;
                        stateFlowImpl4.getClass();
                        stateFlowImpl4.updateState(null, bool);
                        stateFlowImpl3.getClass();
                        stateFlowImpl3.updateState(null, bool);
                        DataActivityModel wifiDataActivityModel = DataActivityModelKt.toWifiDataActivityModel(wifi.activity);
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, wifiDataActivityModel);
                        boolean booleanValue = wifi.validated.booleanValue();
                        Integer num = wifi.level;
                        int intValue = num != null ? num.intValue() : 0;
                        String str = wifi.ssid;
                        if (str == null) {
                            str = "Demo SSID";
                        }
                        Object inactive = (intValue == -1 || intValue < 0 || intValue >= 5) ? new WifiNetworkModel.Inactive(AnnotationValue$1$$ExternalSyntheticOutline0.m(intValue, "Wifi network was active but had invalid level. 0 <= wifi level <= 4 required; level was ")) : new WifiNetworkModel.Active(booleanValue, intValue, str, wifi.hotspotDeviceType);
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, inactive);
                    } else if (fakeWifiEventModel instanceof FakeWifiEventModel.CarrierMerged) {
                        FakeWifiEventModel.CarrierMerged carrierMerged = (FakeWifiEventModel.CarrierMerged) fakeWifiEventModel;
                        Boolean bool2 = Boolean.TRUE;
                        stateFlowImpl4.getClass();
                        stateFlowImpl4.updateState(null, bool2);
                        stateFlowImpl3.getClass();
                        stateFlowImpl3.updateState(null, bool2);
                        DataActivityModel wifiDataActivityModel2 = DataActivityModelKt.toWifiDataActivityModel(carrierMerged.activity);
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, wifiDataActivityModel2);
                        WifiNetworkModel of = WifiNetworkModel.CarrierMerged.Companion.of(carrierMerged.subscriptionId, carrierMerged.level, carrierMerged.numberOfLevels);
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, of);
                    } else {
                        if (!(fakeWifiEventModel instanceof FakeWifiEventModel.WifiDisabled)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        Boolean bool3 = Boolean.FALSE;
                        stateFlowImpl4.getClass();
                        stateFlowImpl4.updateState(null, bool3);
                        stateFlowImpl3.getClass();
                        stateFlowImpl3.updateState(null, bool3);
                        DataActivityModel dataActivityModel = new DataActivityModel(false, false);
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, dataActivityModel);
                        WifiNetworkModel.Inactive inactive2 = new WifiNetworkModel.Inactive(null);
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, inactive2);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            Object collect = readonlySharedFlow.$$delegate_0.collect(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.AnonymousClass2(flowCollector), this);
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
