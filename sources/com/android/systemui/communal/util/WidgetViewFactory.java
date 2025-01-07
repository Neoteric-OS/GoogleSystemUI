package com.android.systemui.communal.util;

import android.content.Context;
import android.util.SizeF;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import com.android.systemui.communal.widgets.WidgetInteractionHandler;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetViewFactory {
    public final CommunalAppWidgetHost appWidgetHost;
    public final WidgetInteractionHandler interactionHandler;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35 listenerFactory;
    public final CoroutineContext uiBgContext;
    public final Executor uiBgExecutor;

    public WidgetViewFactory(CoroutineContext coroutineContext, Executor executor, CommunalAppWidgetHost communalAppWidgetHost, WidgetInteractionHandler widgetInteractionHandler, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35) {
        this.uiBgContext = coroutineContext;
        this.uiBgExecutor = executor;
        this.appWidgetHost = communalAppWidgetHost;
        this.interactionHandler = widgetInteractionHandler;
        this.listenerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35;
    }

    public final Object createWidget(Context context, CommunalContentModel.WidgetContent.Widget widget, SizeF sizeF, Continuation continuation) {
        return CoroutineTracingKt.withContext(this.uiBgContext, new WidgetViewFactory$createWidget$2(context, this, widget, sizeF, null), (ContinuationImpl) continuation);
    }
}
