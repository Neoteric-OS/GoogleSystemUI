package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.UserHandle;
import android.widget.RemoteViews;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalWidgetHost {
    public final StateFlowImpl _appWidgetProviders;
    public final CommunalAppWidgetHost appWidgetHost;
    public final Optional appWidgetManager;
    public final ReadonlyStateFlow appWidgetProviders;
    public final CoroutineScope bgScope;
    public final Logger logger;
    public final SelectedUserInteractor selectedUserInteractor;

    public CommunalWidgetHost(CoroutineScope coroutineScope, Optional optional, CommunalAppWidgetHost communalAppWidgetHost, SelectedUserInteractor selectedUserInteractor, LogBuffer logBuffer) {
        this.bgScope = coroutineScope;
        this.appWidgetManager = optional;
        this.appWidgetHost = communalAppWidgetHost;
        this.selectedUserInteractor = selectedUserInteractor;
        this.logger = new Logger(logBuffer, "CommunalWidgetHost");
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this._appWidgetProviders = MutableStateFlow;
        this.appWidgetProviders = new ReadonlyStateFlow(MutableStateFlow);
    }

    public final Integer allocateIdAndBindWidget(ComponentName componentName, UserHandle userHandle) {
        boolean z;
        CommunalAppWidgetHost communalAppWidgetHost = this.appWidgetHost;
        int allocateAppWidgetId = communalAppWidgetHost.allocateAppWidgetId();
        if (userHandle == null) {
            userHandle = new UserHandle(this.selectedUserInteractor.getSelectedUserId());
        }
        if (this.appWidgetManager.isPresent()) {
            Bundle bundle = new Bundle();
            bundle.putInt("appWidgetCategory", 2);
            z = ((AppWidgetManager) this.appWidgetManager.get()).bindAppWidgetIdIfAllowed(allocateAppWidgetId, userHandle, componentName, bundle);
        } else {
            z = false;
        }
        Logger logger = this.logger;
        if (!z) {
            communalAppWidgetHost.deleteAppWidgetId(allocateAppWidgetId);
            Logger.d$default(logger, "Failed to bind the widget " + componentName, null, 2, null);
            return null;
        }
        Logger.d$default(logger, "Successfully bound the widget " + componentName, null, 2, null);
        AppWidgetManager appWidgetManager = (AppWidgetManager) this.appWidgetManager.orElse(null);
        BuildersKt.launch$default(this.bgScope, null, null, new CommunalWidgetHost$onProviderInfoUpdated$1(this, allocateAppWidgetId, appWidgetManager != null ? appWidgetManager.getAppWidgetInfo(allocateAppWidgetId) : null, null), 3);
        return Integer.valueOf(allocateAppWidgetId);
    }

    public final void onHostStopListening() {
        StateFlowImpl stateFlowImpl = this._appWidgetProviders;
        Iterator it = ((Map) stateFlowImpl.getValue()).keySet().iterator();
        while (it.hasNext()) {
            this.appWidgetHost.removeListener(((Number) it.next()).intValue());
        }
        Map emptyMap = MapsKt.emptyMap();
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, emptyMap);
    }

    public final void refreshProviders() {
        BuildersKt.launch$default(this.bgScope, null, null, new CommunalWidgetHost$refreshProviders$1(this, null), 3);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CommunalAppWidgetHostListener implements AppWidgetHost.AppWidgetHostListener {
        public final int appWidgetId;
        public final Function2 onUpdateProviderInfo;

        public CommunalAppWidgetHostListener(int i, Function2 function2) {
            this.appWidgetId = i;
            this.onUpdateProviderInfo = function2;
        }

        public final void onUpdateProviderInfo(AppWidgetProviderInfo appWidgetProviderInfo) {
            this.onUpdateProviderInfo.invoke(Integer.valueOf(this.appWidgetId), appWidgetProviderInfo);
        }

        public final void onViewDataChanged(int i) {
        }

        public final void updateAppWidget(RemoteViews remoteViews) {
        }
    }
}
