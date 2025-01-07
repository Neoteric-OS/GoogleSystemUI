package com.android.systemui.statusbar;

import android.app.PendingIntent;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.RemoteViews;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinatorKt;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationRemoteInputManager$1$$ExternalSyntheticLambda0 {
    public final /* synthetic */ NotificationRemoteInputManager.AnonymousClass1 f$0;
    public final /* synthetic */ RemoteViews.RemoteResponse f$1;
    public final /* synthetic */ View f$2;
    public final /* synthetic */ NotificationEntry f$3;
    public final /* synthetic */ PendingIntent f$4;
    public final /* synthetic */ Integer f$5;

    public /* synthetic */ NotificationRemoteInputManager$1$$ExternalSyntheticLambda0(NotificationRemoteInputManager.AnonymousClass1 anonymousClass1, RemoteViews.RemoteResponse remoteResponse, View view, NotificationEntry notificationEntry, PendingIntent pendingIntent, Integer num) {
        this.f$0 = anonymousClass1;
        this.f$1 = remoteResponse;
        this.f$2 = view;
        this.f$3 = notificationEntry;
        this.f$4 = pendingIntent;
        this.f$5 = num;
    }

    public final boolean handleClick() {
        boolean booleanValue;
        RemoteViews.RemoteResponse remoteResponse = this.f$1;
        View view = this.f$2;
        PendingIntent pendingIntent = this.f$4;
        NotificationRemoteInputManager.AnonymousClass1 anonymousClass1 = this.f$0;
        Pair launchOptions = remoteResponse.getLaunchOptions(view);
        ActionClickLogger actionClickLogger = NotificationRemoteInputManager.this.mLogger;
        actionClickLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ActionClickLogger$logStartingIntentWithDefaultHandler$2 actionClickLogger$logStartingIntentWithDefaultHandler$2 = new Function1() { // from class: com.android.systemui.statusbar.ActionClickLogger$logStartingIntentWithDefaultHandler$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str2 = logMessage.getStr2();
                String str1 = logMessage.getStr1();
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("  [Action click] Launching intent ", str2, " via default handler (for ", str1, " at index "), logMessage.getInt1(), ")");
            }
        };
        LogBuffer logBuffer = actionClickLogger.buffer;
        LogMessage obtain = logBuffer.obtain("ActionClickLogger", logLevel, actionClickLogger$logStartingIntentWithDefaultHandler$2, null);
        NotificationEntry notificationEntry = this.f$3;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = notificationEntry != null ? notificationEntry.mKey : null;
        logMessageImpl.str2 = pendingIntent.toString();
        Integer num = this.f$5;
        logMessageImpl.int1 = num != null ? num.intValue() : Integer.MIN_VALUE;
        logBuffer.commit(obtain);
        boolean startPendingIntent = RemoteViews.startPendingIntent(view, pendingIntent, launchOptions);
        if (startPendingIntent) {
            NotificationRemoteInputManager notificationRemoteInputManager = NotificationRemoteInputManager.this;
            if (notificationEntry == null) {
                notificationRemoteInputManager.getClass();
            } else {
                RemoteInputCoordinator remoteInputCoordinator = notificationRemoteInputManager.mRemoteInputListener;
                if (remoteInputCoordinator != null) {
                    booleanValue = ((Boolean) RemoteInputCoordinatorKt.DEBUG$delegate.getValue()).booleanValue();
                    String str = notificationEntry.mKey;
                    if (booleanValue) {
                        Log.d("RemoteInputCoordinator", "releaseNotificationIfKeptForRemoteInputHistory(entry=" + str + ")");
                    }
                    remoteInputCoordinator.mRemoteInputHistoryExtender.endLifetimeExtensionAfterDelay(200L, str);
                    remoteInputCoordinator.mSmartReplyHistoryExtender.endLifetimeExtensionAfterDelay(200L, str);
                    remoteInputCoordinator.mRemoteInputActiveExtender.endLifetimeExtensionAfterDelay(200L, str);
                }
                Iterator it = notificationRemoteInputManager.mActionPressListeners.listeners.iterator();
                while (it.hasNext()) {
                    ((Consumer) it.next()).accept(notificationEntry);
                }
            }
        }
        return startPendingIntent;
    }
}
