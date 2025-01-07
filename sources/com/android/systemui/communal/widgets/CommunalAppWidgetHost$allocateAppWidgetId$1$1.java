package com.android.systemui.communal.widgets;

import com.android.systemui.communal.widgets.CommunalWidgetHost;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalAppWidgetHost$allocateAppWidgetId$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $appWidgetId;
    int label;
    final /* synthetic */ CommunalAppWidgetHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalAppWidgetHost$allocateAppWidgetId$1$1(CommunalAppWidgetHost communalAppWidgetHost, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalAppWidgetHost;
        this.$appWidgetId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalAppWidgetHost$allocateAppWidgetId$1$1(this.this$0, this.$appWidgetId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalAppWidgetHost$allocateAppWidgetId$1$1 communalAppWidgetHost$allocateAppWidgetId$1$1 = (CommunalAppWidgetHost$allocateAppWidgetId$1$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalAppWidgetHost$allocateAppWidgetId$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommunalAppWidgetHost communalAppWidgetHost = this.this$0;
        Set set = communalAppWidgetHost.observers;
        int i = this.$appWidgetId;
        synchronized (set) {
            for (CommunalWidgetHost communalWidgetHost : communalAppWidgetHost.observers) {
                communalWidgetHost.getClass();
                communalWidgetHost.appWidgetHost.setListener(i, new CommunalWidgetHost.CommunalAppWidgetHostListener(i, new CommunalWidgetHost$addListener$1(2, communalWidgetHost, CommunalWidgetHost.class, "onProviderInfoUpdated", "onProviderInfoUpdated(ILandroid/appwidget/AppWidgetProviderInfo;)V", 0)));
            }
        }
        return Unit.INSTANCE;
    }
}
