package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetManager;
import com.android.systemui.communal.widgets.CommunalWidgetHost;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalWidgetHost$refreshProviders$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CommunalWidgetHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalWidgetHost$refreshProviders$1(CommunalWidgetHost communalWidgetHost, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalWidgetHost;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalWidgetHost$refreshProviders$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalWidgetHost$refreshProviders$1 communalWidgetHost$refreshProviders$1 = (CommunalWidgetHost$refreshProviders$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalWidgetHost$refreshProviders$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int[] appWidgetIds = this.this$0.appWidgetHost.getAppWidgetIds();
        CommunalWidgetHost communalWidgetHost = this.this$0;
        for (int i : appWidgetIds) {
            communalWidgetHost.getClass();
            communalWidgetHost.appWidgetHost.setListener(i, new CommunalWidgetHost.CommunalAppWidgetHostListener(i, new CommunalWidgetHost$addListener$1(2, communalWidgetHost, CommunalWidgetHost.class, "onProviderInfoUpdated", "onProviderInfoUpdated(ILandroid/appwidget/AppWidgetProviderInfo;)V", 0)));
            Integer num = new Integer(i);
            AppWidgetManager appWidgetManager = (AppWidgetManager) communalWidgetHost.appWidgetManager.orElse(null);
            linkedHashMap.put(num, appWidgetManager != null ? appWidgetManager.getAppWidgetInfo(i) : null);
        }
        StateFlowImpl stateFlowImpl = this.this$0._appWidgetProviders;
        Map map = MapsKt.toMap(linkedHashMap);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, map);
        return Unit.INSTANCE;
    }
}
