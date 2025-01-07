package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetProviderInfo;
import android.widget.RemoteViews;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppWidgetHostListenerDelegate$updateAppWidget$1 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object $views;
    public final /* synthetic */ AppWidgetHostListenerDelegate this$0;

    public AppWidgetHostListenerDelegate$updateAppWidget$1(AppWidgetHostListenerDelegate appWidgetHostListenerDelegate, AppWidgetProviderInfo appWidgetProviderInfo) {
        this.this$0 = appWidgetHostListenerDelegate;
        this.$views = appWidgetProviderInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.listener.updateAppWidget((RemoteViews) this.$views);
                break;
            default:
                this.this$0.listener.onUpdateProviderInfo((AppWidgetProviderInfo) this.$views);
                break;
        }
    }

    public AppWidgetHostListenerDelegate$updateAppWidget$1(AppWidgetHostListenerDelegate appWidgetHostListenerDelegate, RemoteViews remoteViews) {
        this.this$0 = appWidgetHostListenerDelegate;
        this.$views = remoteViews;
    }
}
