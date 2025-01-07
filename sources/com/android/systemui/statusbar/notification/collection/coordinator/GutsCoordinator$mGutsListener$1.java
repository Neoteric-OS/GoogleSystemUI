package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.ArraySet;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GutsCoordinator$mGutsListener$1 {
    public final /* synthetic */ GutsCoordinator this$0;

    public GutsCoordinator$mGutsListener$1(GutsCoordinator gutsCoordinator) {
        this.this$0 = gutsCoordinator;
    }

    public final void onGutsClose(NotificationEntry notificationEntry) {
        NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12;
        GutsCoordinator gutsCoordinator = this.this$0;
        GutsCoordinatorLogger gutsCoordinatorLogger = gutsCoordinator.logger;
        String str = notificationEntry.mKey;
        LogLevel logLevel = LogLevel.DEBUG;
        GutsCoordinatorLogger$logGutsClosed$2 gutsCoordinatorLogger$logGutsClosed$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger$logGutsClosed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Guts closed for class ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = gutsCoordinatorLogger.buffer;
        LogMessage obtain = logBuffer.obtain("GutsCoordinator", logLevel, gutsCoordinatorLogger$logGutsClosed$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
        ArraySet arraySet = gutsCoordinator.notifsWithOpenGuts;
        String str2 = notificationEntry.mKey;
        arraySet.remove(str2);
        if (!gutsCoordinator.notifsExtendingLifetime.remove(str2) || (notifCollection$$ExternalSyntheticLambda12 = gutsCoordinator.onEndLifetimeExtensionCallback) == null) {
            return;
        }
        notifCollection$$ExternalSyntheticLambda12.onEndLifetimeExtension(notificationEntry, gutsCoordinator.mLifetimeExtender);
    }

    public final void onGutsOpen(NotificationEntry notificationEntry, NotificationGuts notificationGuts) {
        NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12;
        GutsCoordinator gutsCoordinator = this.this$0;
        GutsCoordinatorLogger gutsCoordinatorLogger = gutsCoordinator.logger;
        String str = notificationEntry.mKey;
        LogLevel logLevel = LogLevel.DEBUG;
        GutsCoordinatorLogger$logGutsOpened$2 gutsCoordinatorLogger$logGutsOpened$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger$logGutsOpened$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str2 = logMessage.getStr2();
                boolean bool1 = logMessage.getBool1();
                String str1 = logMessage.getStr1();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Guts of type ", str2, " (leave behind: ", bool1, ") opened for class ");
                m.append(str1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = gutsCoordinatorLogger.buffer;
        LogMessage obtain = logBuffer.obtain("GutsCoordinator", logLevel, gutsCoordinatorLogger$logGutsOpened$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = Reflection.getOrCreateKotlinClass(notificationGuts.mGutsContent.getClass()).getSimpleName();
        NotificationGuts.GutsContent gutsContent = notificationGuts.mGutsContent;
        logMessageImpl.bool1 = gutsContent != null && gutsContent.isLeavebehind();
        logBuffer.commit(obtain);
        NotificationGuts.GutsContent gutsContent2 = notificationGuts.mGutsContent;
        if (gutsContent2 == null || !gutsContent2.isLeavebehind()) {
            gutsCoordinator.notifsWithOpenGuts.add(str);
            return;
        }
        ArraySet arraySet = gutsCoordinator.notifsWithOpenGuts;
        String str2 = notificationEntry.mKey;
        arraySet.remove(str2);
        if (!gutsCoordinator.notifsExtendingLifetime.remove(str2) || (notifCollection$$ExternalSyntheticLambda12 = gutsCoordinator.onEndLifetimeExtensionCallback) == null) {
            return;
        }
        notifCollection$$ExternalSyntheticLambda12.onEndLifetimeExtension(notificationEntry, gutsCoordinator.mLifetimeExtender);
    }
}
