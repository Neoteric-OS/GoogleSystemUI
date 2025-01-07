package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Build;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationStatsLoggerCoordinator implements Coordinator {
    public final Optional loggerOptional;

    public NotificationStatsLoggerCoordinator(Optional optional) {
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notifications_live_data_store_refactor to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notifications_live_data_store_refactor to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects com.android.systemui.notifications_live_data_store_refactor to be enabled.");
        }
    }
}
