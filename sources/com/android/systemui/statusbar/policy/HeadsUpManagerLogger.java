package com.android.systemui.statusbar.policy;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpManagerLogger {
    public final LogBuffer buffer;

    public HeadsUpManagerLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logRemoveEntryAfterExpand(NotificationEntry notificationEntry) {
        LogLevel logLevel = LogLevel.VERBOSE;
        HeadsUpManagerLogger$logRemoveEntryAfterExpand$2 headsUpManagerLogger$logRemoveEntryAfterExpand$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logRemoveEntryAfterExpand$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("remove entry after expand: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logRemoveEntryAfterExpand$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
    }
}
