package com.android.systemui.statusbar.notification.row;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRowContentBinderLogger {
    public final LogBuffer buffer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static String flagToString(int i) {
            if (i == 0) {
                return "NONE";
            }
            if (i == 255) {
                return "ALL";
            }
            ArrayList arrayList = new ArrayList();
            if ((i & 1) != 0) {
                arrayList.add("CONTRACTED");
            }
            if ((i & 2) != 0) {
                arrayList.add("EXPANDED");
            }
            if ((i & 4) != 0) {
                arrayList.add("HEADS_UP");
            }
            if ((i & 8) != 0) {
                arrayList.add("PUBLIC");
            }
            if ((i & 16) != 0) {
                arrayList.add("SINGLE_LINE");
            }
            if ((i & 32) != 0) {
                arrayList.add("GROUP_SUMMARY_HEADER");
            }
            if ((i & 64) != 0) {
                arrayList.add("LOW_PRIORITY_GROUP_SUMMARY_HEADER");
            }
            return CollectionsKt.joinToString$default(arrayList, "|", null, null, null, 62);
        }
    }

    public NotificationRowContentBinderLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logAsyncTaskException(NotificationEntry notificationEntry, String str, Throwable th) {
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowContentBinderLogger$logAsyncTaskException$2 notificationRowContentBinderLogger$logAsyncTaskException$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger$logAsyncTaskException$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("async task for ", str1, " got exception ", str2, ": ");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logAsyncTaskException$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = str;
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        logMessageImpl.str3 = stringWriter.toString();
        logBuffer.commit(obtain);
    }

    public final void logAsyncTaskProgress(NotificationEntry notificationEntry, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowContentBinderLogger$logAsyncTaskProgress$2 notificationRowContentBinderLogger$logAsyncTaskProgress$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger$logAsyncTaskProgress$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("async task for ", logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logAsyncTaskProgress$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }
}
