package com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import com.android.systemui.statusbar.pipeline.wifi.shared.WifiConstants;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.model.WifiIcon;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WifiViewModel$wifiIcon$1 extends SuspendLambda implements Function5 {
    final /* synthetic */ ConnectivityConstants $connectivityConstants;
    final /* synthetic */ WifiConstants $wifiConstants;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ WifiViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiViewModel$wifiIcon$1(WifiViewModel wifiViewModel, WifiConstants wifiConstants, ConnectivityConstants connectivityConstants, Continuation continuation) {
        super(5, continuation);
        this.this$0 = wifiViewModel;
        this.$wifiConstants = wifiConstants;
        this.$connectivityConstants = connectivityConstants;
    }

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        WifiViewModel$wifiIcon$1 wifiViewModel$wifiIcon$1 = new WifiViewModel$wifiIcon$1(this.this$0, this.$wifiConstants, this.$connectivityConstants, (Continuation) obj5);
        wifiViewModel$wifiIcon$1.Z$0 = booleanValue;
        wifiViewModel$wifiIcon$1.Z$1 = booleanValue2;
        wifiViewModel$wifiIcon$1.Z$2 = booleanValue3;
        wifiViewModel$wifiIcon$1.L$0 = (WifiNetworkModel) obj4;
        return wifiViewModel$wifiIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) this.L$0;
        WifiIcon.Hidden hidden = WifiIcon.Hidden.INSTANCE;
        if (!z || z3 || (wifiNetworkModel instanceof WifiNetworkModel.CarrierMerged)) {
            return hidden;
        }
        return (z2 || this.$wifiConstants.alwaysShowIconIfEnabled || !this.$connectivityConstants.hasDataCapabilities) ? WifiIcon.Companion.fromModel(wifiNetworkModel, this.this$0.context, false) : hidden;
    }
}
