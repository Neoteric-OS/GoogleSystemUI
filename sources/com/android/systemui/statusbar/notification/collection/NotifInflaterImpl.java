package com.android.systemui.statusbar.notification.collection;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$$ExternalSyntheticLambda2;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderImpl;
import com.android.systemui.statusbar.notification.row.NotifInflationErrorManager;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinder;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifInflaterImpl implements NotifInflater {
    public final NotifInflaterLogger mLogger;
    public final NotifInflationErrorManager mNotifErrorManager;
    public NotificationRowBinderImpl mNotificationRowBinder;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.NotifInflaterImpl$1, reason: invalid class name */
    public final class AnonymousClass1 implements NotificationRowContentBinder.InflationCallback {
        public final /* synthetic */ PreparationCoordinator$$ExternalSyntheticLambda2 val$callback;

        public AnonymousClass1(PreparationCoordinator$$ExternalSyntheticLambda2 preparationCoordinator$$ExternalSyntheticLambda2) {
            this.val$callback = preparationCoordinator$$ExternalSyntheticLambda2;
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void handleInflationException(NotificationEntry notificationEntry, Exception exc) {
            NotifInflaterImpl.this.mNotifErrorManager.setInflationError(notificationEntry, exc);
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void onAsyncInflationFinished(NotificationEntry notificationEntry) {
            NotifInflaterImpl.this.mNotifErrorManager.clearInflationError(notificationEntry);
            this.val$callback.onInflationFinished(notificationEntry, notificationEntry.mRowController);
        }
    }

    public NotifInflaterImpl(NotifInflationErrorManager notifInflationErrorManager, NotifInflaterLogger notifInflaterLogger) {
        this.mNotifErrorManager = notifInflationErrorManager;
        this.mLogger = notifInflaterLogger;
    }

    public final boolean abortInflation(NotificationEntry notificationEntry) {
        boolean abortTask = notificationEntry.abortTask();
        if (abortTask) {
            NotifInflaterLogger notifInflaterLogger = this.mLogger;
            notifInflaterLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            NotifInflaterLogger$logAbortInflationAbortedTask$2 notifInflaterLogger$logAbortInflationAbortedTask$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logAbortInflationAbortedTask$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("aborted task to abort inflation for ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = notifInflaterLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotifInflater", logLevel, notifInflaterLogger$logAbortInflationAbortedTask$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
        return abortTask;
    }

    public final void inflateViews(NotificationEntry notificationEntry, NotifInflater.Params params, PreparationCoordinator$$ExternalSyntheticLambda2 preparationCoordinator$$ExternalSyntheticLambda2) {
        NotifInflaterLogger notifInflaterLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        NotifInflaterLogger$logInflatingViews$2 notifInflaterLogger$logInflatingViews$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logInflatingViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("inflating views for ", logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = notifInflaterLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifInflater", logLevel, notifInflaterLogger$logInflatingViews$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = params.reason;
        logBuffer.commit(obtain);
        inflateViewsImpl(notificationEntry, params, preparationCoordinator$$ExternalSyntheticLambda2);
        LogMessage obtain2 = logBuffer.obtain("NotifInflater", logLevel, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logInflatedViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("inflated views for ", ((LogMessage) obj).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain2);
    }

    public final void inflateViewsImpl(NotificationEntry notificationEntry, NotifInflater.Params params, PreparationCoordinator$$ExternalSyntheticLambda2 preparationCoordinator$$ExternalSyntheticLambda2) {
        try {
            NotificationRowBinderImpl notificationRowBinderImpl = this.mNotificationRowBinder;
            if (notificationRowBinderImpl == null) {
                throw new RuntimeException("NotificationRowBinder must be attached before using NotifInflaterImpl.");
            }
            notificationRowBinderImpl.inflateViews(notificationEntry, params, new AnonymousClass1(preparationCoordinator$$ExternalSyntheticLambda2));
        } catch (InflationException e) {
            NotifInflaterLogger notifInflaterLogger = this.mLogger;
            LogLevel logLevel = LogLevel.WARNING;
            NotifInflaterLogger$logInflationException$2 notifInflaterLogger$logInflationException$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logInflationException$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("exception inflating views for ", logMessage.getStr1(), ": ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = notifInflaterLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotifInflater", logLevel, notifInflaterLogger$logInflationException$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            printWriter.flush();
            logMessageImpl.str2 = stringWriter.toString();
            logBuffer.commit(obtain);
            this.mNotifErrorManager.setInflationError(notificationEntry, e);
        }
    }

    public final void rebindViews(NotificationEntry notificationEntry, NotifInflater.Params params, PreparationCoordinator$$ExternalSyntheticLambda2 preparationCoordinator$$ExternalSyntheticLambda2) {
        NotifInflaterLogger notifInflaterLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        NotifInflaterLogger$logRebindingViews$2 notifInflaterLogger$logRebindingViews$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logRebindingViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("rebinding views for ", logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = notifInflaterLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifInflater", logLevel, notifInflaterLogger$logRebindingViews$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = params.reason;
        logBuffer.commit(obtain);
        inflateViewsImpl(notificationEntry, params, preparationCoordinator$$ExternalSyntheticLambda2);
        LogMessage obtain2 = logBuffer.obtain("NotifInflater", logLevel, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logReboundViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("rebound views for ", ((LogMessage) obj).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain2);
    }

    public final void releaseViews(NotificationEntry notificationEntry) {
        NotifInflaterLogger notifInflaterLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        NotifInflaterLogger$logReleasingViews$2 notifInflaterLogger$logReleasingViews$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.NotifInflaterLogger$logReleasingViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("aborting inflation for ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = notifInflaterLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifInflater", logLevel, notifInflaterLogger$logReleasingViews$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        NotificationRowBinderImpl notificationRowBinderImpl = this.mNotificationRowBinder;
        if (notificationRowBinderImpl == null) {
            throw new RuntimeException("NotificationRowBinder must be attached before using NotifInflaterImpl.");
        }
        notificationRowBinderImpl.releaseViews(notificationEntry);
    }
}
