package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetProviderInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalWidgetHost$onProviderInfoUpdated$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $appWidgetId;
    final /* synthetic */ AppWidgetProviderInfo $providerInfo;
    int label;
    final /* synthetic */ CommunalWidgetHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalWidgetHost$onProviderInfoUpdated$1(CommunalWidgetHost communalWidgetHost, int i, AppWidgetProviderInfo appWidgetProviderInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalWidgetHost;
        this.$appWidgetId = i;
        this.$providerInfo = appWidgetProviderInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalWidgetHost$onProviderInfoUpdated$1(this.this$0, this.$appWidgetId, this.$providerInfo, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalWidgetHost$onProviderInfoUpdated$1 communalWidgetHost$onProviderInfoUpdated$1 = (CommunalWidgetHost$onProviderInfoUpdated$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalWidgetHost$onProviderInfoUpdated$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        StateFlowImpl stateFlowImpl = this.this$0._appWidgetProviders;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        int i = this.$appWidgetId;
        linkedHashMap.put(new Integer(i), this.$providerInfo);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        return Unit.INSTANCE;
    }
}
