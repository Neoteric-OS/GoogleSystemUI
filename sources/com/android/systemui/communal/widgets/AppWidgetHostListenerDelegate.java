package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetProviderInfo;
import android.widget.RemoteViews;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppWidgetHostListenerDelegate implements AppWidgetHost.AppWidgetHostListener {
    public final CommunalAppWidgetHostView listener;
    public final Executor mainExecutor;

    public AppWidgetHostListenerDelegate(Executor executor, CommunalAppWidgetHostView communalAppWidgetHostView) {
        this.mainExecutor = executor;
        this.listener = communalAppWidgetHostView;
    }

    public final void onUpdateProviderInfo(AppWidgetProviderInfo appWidgetProviderInfo) {
        this.mainExecutor.execute(new AppWidgetHostListenerDelegate$updateAppWidget$1(this, appWidgetProviderInfo));
    }

    public final void onViewDataChanged(final int i) {
        this.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.communal.widgets.AppWidgetHostListenerDelegate$onViewDataChanged$1
            @Override // java.lang.Runnable
            public final void run() {
                AppWidgetHostListenerDelegate.this.listener.onViewDataChanged(i);
            }
        });
    }

    public final void updateAppWidget(RemoteViews remoteViews) {
        this.mainExecutor.execute(new AppWidgetHostListenerDelegate$updateAppWidget$1(this, remoteViews));
    }
}
