package com.android.systemui.statusbar.notification.collection.coordinator;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManager$Listener;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PreparationCoordinator$$ExternalSyntheticLambda2 {
    public final /* synthetic */ PreparationCoordinator f$0;

    public final void onInflationFinished(NotificationEntry notificationEntry, NotifViewController notifViewController) {
        PreparationCoordinator preparationCoordinator = this.f$0;
        PreparationCoordinatorLogger preparationCoordinatorLogger = preparationCoordinator.mLogger;
        preparationCoordinatorLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        PreparationCoordinatorLogger$logNotifInflated$2 preparationCoordinatorLogger$logNotifInflated$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger$logNotifInflated$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Inflation completed for notif ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = preparationCoordinatorLogger.buffer;
        LogMessage obtain = logBuffer.obtain("PreparationCoordinator", logLevel, preparationCoordinatorLogger$logNotifInflated$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        preparationCoordinator.mInflatingNotifs.remove(notificationEntry);
        preparationCoordinator.mViewBarn.rowMap.put(notificationEntry.mKey, notifViewController);
        preparationCoordinator.mInflationStates.put(notificationEntry, 1);
        Iterator it = preparationCoordinator.mBindEventManager.listeners.listeners.iterator();
        while (it.hasNext()) {
            ((BindEventManager$Listener) it.next()).onViewBound(notificationEntry);
        }
        preparationCoordinator.mNotifInflatingFilter.invalidateList("onInflationFinished for " + NotificationUtils.logKey(notificationEntry));
    }
}
