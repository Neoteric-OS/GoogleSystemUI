package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import android.graphics.drawable.Drawable;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.impl.internet.domain.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.model.WifiIcon;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileDataInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ InternetTileDataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileDataInteractor$special$$inlined$flatMapLatest$1(InternetTileDataInteractor internetTileDataInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = internetTileDataInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        InternetTileDataInteractor$special$$inlined$flatMapLatest$1 internetTileDataInteractor$special$$inlined$flatMapLatest$1 = new InternetTileDataInteractor$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        internetTileDataInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        internetTileDataInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return internetTileDataInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) this.L$1;
            WifiIcon fromModel = WifiIcon.Companion.fromModel(wifiNetworkModel, this.this$0.context, true);
            if ((wifiNetworkModel instanceof WifiNetworkModel.Active) && (fromModel instanceof WifiIcon.Visible)) {
                InternetTileModel.Inactive inactive = InternetTileDataInteractor.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
                String str2 = ((WifiNetworkModel.Active) wifiNetworkModel).ssid;
                if (str2 == null) {
                    str = null;
                } else {
                    Character valueOf = str2.length() == 0 ? null : Character.valueOf(str2.charAt(0));
                    if (valueOf != null && valueOf.charValue() == '\"') {
                        Character valueOf2 = str2.length() == 0 ? null : Character.valueOf(str2.charAt(str2.length() - 1));
                        if (valueOf2 != null && valueOf2.charValue() == '\"') {
                            str2 = str2.substring(1, str2.length() - 1);
                        }
                    }
                    str = str2;
                }
                WifiIcon.Visible visible = (WifiIcon.Visible) fromModel;
                int i2 = visible.icon.res;
                Integer num = new Integer(i2);
                Drawable drawable = this.this$0.context.getDrawable(i2);
                Intrinsics.checkNotNull(drawable);
                flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new InternetTileModel.Active(str, null, num, new Icon.Loaded(drawable, null), visible.contentDescription, new ContentDescription.Loaded(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.this$0.internetLabel, ",", str)), 2));
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
