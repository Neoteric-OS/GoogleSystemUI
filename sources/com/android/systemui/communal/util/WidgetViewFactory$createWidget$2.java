package com.android.systemui.communal.util;

import android.content.Context;
import android.os.Bundle;
import android.util.SizeF;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.widgets.AppWidgetHostListenerDelegate;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import com.android.systemui.communal.widgets.CommunalAppWidgetHostView;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WidgetViewFactory$createWidget$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ CommunalContentModel.WidgetContent.Widget $model;
    final /* synthetic */ SizeF $size;
    int label;
    final /* synthetic */ WidgetViewFactory this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WidgetViewFactory$createWidget$2(Context context, WidgetViewFactory widgetViewFactory, CommunalContentModel.WidgetContent.Widget widget, SizeF sizeF, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
        this.this$0 = widgetViewFactory;
        this.$model = widget;
        this.$size = sizeF;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new WidgetViewFactory$createWidget$2(this.$context, this.this$0, this.$model, this.$size, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WidgetViewFactory$createWidget$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommunalAppWidgetHostView communalAppWidgetHostView = new CommunalAppWidgetHostView(this.$context, this.this$0.interactionHandler);
        WidgetViewFactory widgetViewFactory = this.this$0;
        CommunalContentModel.WidgetContent.Widget widget = this.$model;
        communalAppWidgetHostView.setExecutor(widgetViewFactory.uiBgExecutor);
        communalAppWidgetHostView.setAppWidget(widget.appWidgetId, widget.providerInfo);
        WidgetViewFactory widgetViewFactory2 = this.this$0;
        CommunalAppWidgetHost communalAppWidgetHost = widgetViewFactory2.appWidgetHost;
        int i = this.$model.appWidgetId;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35 = widgetViewFactory2.listenerFactory;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35.getClass();
        communalAppWidgetHost.setListener(i, new AppWidgetHostListenerDelegate((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35.this$0.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), communalAppWidgetHostView));
        communalAppWidgetHostView.updateAppWidgetSize(new Bundle(), (int) this.$size.getWidth(), (int) this.$size.getHeight(), (int) this.$size.getWidth(), (int) this.$size.getHeight(), true);
        return communalAppWidgetHostView;
    }
}
