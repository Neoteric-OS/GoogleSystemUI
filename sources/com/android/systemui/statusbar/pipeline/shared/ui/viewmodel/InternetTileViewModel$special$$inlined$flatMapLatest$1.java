package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.model.WifiIcon;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ InternetTileViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileViewModel$special$$inlined$flatMapLatest$1(InternetTileViewModel internetTileViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = internetTileViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        InternetTileViewModel$special$$inlined$flatMapLatest$1 internetTileViewModel$special$$inlined$flatMapLatest$1 = new InternetTileViewModel$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        internetTileViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        internetTileViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return internetTileViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) this.L$1;
            WifiIcon fromModel = WifiIcon.Companion.fromModel(wifiNetworkModel, this.this$0.context, true);
            if ((wifiNetworkModel instanceof WifiNetworkModel.Active) && (fromModel instanceof WifiIcon.Visible)) {
                InternetTileModel.Inactive inactive = InternetTileViewModel.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
                String str = ((WifiNetworkModel.Active) wifiNetworkModel).ssid;
                if (str == null) {
                    str = null;
                } else {
                    int length = str.length();
                    if (length > 1 && str.charAt(0) == '\"') {
                        int i2 = length - 1;
                        if (str.charAt(i2) == '\"') {
                            str = str.substring(1, i2);
                        }
                    }
                }
                String str2 = str;
                WifiIcon.Visible visible = (WifiIcon.Visible) fromModel;
                flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new InternetTileModel.Active(str2, null, null, QSTileImpl.ResourceIcon.get(visible.icon.res), visible.contentDescription, new ContentDescription.Loaded(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.this$0.internetLabel, ",", str2)), 6));
            } else {
                flow = this.this$0.notConnectedFlow;
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flow, this) == coroutineSingletons) {
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
