package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetHost;
import android.content.Context;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalAppWidgetHost extends AppWidgetHost {
    public final SharedFlowImpl _appWidgetIdToRemove;
    public final ReadonlySharedFlow appWidgetIdToRemove;
    public final CoroutineScope backgroundScope;
    public final Logger logger;
    public final Set observers;

    public CommunalAppWidgetHost(Context context, CoroutineScope coroutineScope, LogBuffer logBuffer) {
        super(context, 116);
        this.backgroundScope = coroutineScope;
        this.logger = new Logger(logBuffer, "CommunalAppWidgetHost");
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this._appWidgetIdToRemove = MutableSharedFlow$default;
        this.appWidgetIdToRemove = new ReadonlySharedFlow(MutableSharedFlow$default);
        this.observers = new LinkedHashSet();
    }

    @Override // android.appwidget.AppWidgetHost
    public final int allocateAppWidgetId() {
        int allocateAppWidgetId = super.allocateAppWidgetId();
        BuildersKt.launch$default(this.backgroundScope, null, null, new CommunalAppWidgetHost$allocateAppWidgetId$1$1(this, allocateAppWidgetId, null), 3);
        return allocateAppWidgetId;
    }

    @Override // android.appwidget.AppWidgetHost
    public final void deleteAppWidgetId(int i) {
        super.deleteAppWidgetId(i);
        BuildersKt.launch$default(this.backgroundScope, null, null, new CommunalAppWidgetHost$deleteAppWidgetId$1(this, i, null), 3);
    }

    @Override // android.appwidget.AppWidgetHost
    public final void onAppWidgetRemoved(int i) {
        BuildersKt.launch$default(this.backgroundScope, null, null, new CommunalAppWidgetHost$onAppWidgetRemoved$1(this, i, null), 3);
    }

    @Override // android.appwidget.AppWidgetHost
    public final void startListening() {
        super.startListening();
        BuildersKt.launch$default(this.backgroundScope, null, null, new CommunalAppWidgetHost$startListening$1(this, null), 3);
    }

    @Override // android.appwidget.AppWidgetHost
    public final void stopListening() {
        super.stopListening();
        BuildersKt.launch$default(this.backgroundScope, null, null, new CommunalAppWidgetHost$stopListening$1(this, null), 3);
    }
}
