package com.android.systemui.statusbar.notification.row;

import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.core.os.CancellationSignal;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinder;
import java.util.ArrayList;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RowContentBindStage {
    public NotifBindPipeline$$ExternalSyntheticLambda1 mBindRequestListener;
    public final NotificationRowContentBinder mBinder;
    public final Map mContentParams = new ArrayMap();
    public final RowContentBindStageLogger mLogger;
    public final NotifInflationErrorManager mNotifInflationErrorManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.row.RowContentBindStage$1, reason: invalid class name */
    public final class AnonymousClass1 implements NotificationRowContentBinder.InflationCallback {
        public final /* synthetic */ NotifBindPipeline$$ExternalSyntheticLambda1 val$callback;

        public AnonymousClass1(NotifBindPipeline$$ExternalSyntheticLambda1 notifBindPipeline$$ExternalSyntheticLambda1) {
            this.val$callback = notifBindPipeline$$ExternalSyntheticLambda1;
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void handleInflationException(NotificationEntry notificationEntry, Exception exc) {
            RowContentBindStage.this.mNotifInflationErrorManager.setInflationError(notificationEntry, exc);
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void onAsyncInflationFinished(NotificationEntry notificationEntry) {
            RowContentBindStage rowContentBindStage = RowContentBindStage.this;
            rowContentBindStage.mNotifInflationErrorManager.clearInflationError(notificationEntry);
            ((RowContentBindParams) rowContentBindStage.getStageParams(notificationEntry)).mDirtyContentViews = 0;
            NotifBindPipeline notifBindPipeline = this.val$callback.f$0;
            NotifBindPipeline.BindEntry bindEntry = (NotifBindPipeline.BindEntry) ((ArrayMap) notifBindPipeline.mBindEntries).get(notificationEntry);
            ArraySet arraySet = (ArraySet) bindEntry.callbacks;
            int size = arraySet.size();
            NotifBindPipelineLogger notifBindPipelineLogger = notifBindPipeline.mLogger;
            notifBindPipelineLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotifBindPipelineLogger$logFinishedPipeline$2 notifBindPipelineLogger$logFinishedPipeline$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger$logFinishedPipeline$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Finished pipeline for notif " + logMessage.getStr1() + " with " + logMessage.getInt1() + " callbacks";
                }
            };
            LogBuffer logBuffer = notifBindPipelineLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotifBindPipeline", logLevel, notifBindPipelineLogger$logFinishedPipeline$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.int1 = size;
            logBuffer.commit(obtain);
            bindEntry.invalidated = false;
            notifBindPipeline.mScratchCallbacksList.addAll(arraySet);
            arraySet.clear();
            for (int i = 0; i < ((ArrayList) notifBindPipeline.mScratchCallbacksList).size(); i++) {
                ((NotifBindPipeline.BindCallback) ((ArrayList) notifBindPipeline.mScratchCallbacksList).get(i)).onBindFinished(notificationEntry);
            }
            notifBindPipeline.mScratchCallbacksList.clear();
        }
    }

    public RowContentBindStage(NotificationRowContentBinder notificationRowContentBinder, NotifInflationErrorManager notifInflationErrorManager, RowContentBindStageLogger rowContentBindStageLogger) {
        this.mBinder = notificationRowContentBinder;
        this.mNotifInflationErrorManager = notifInflationErrorManager;
        this.mLogger = rowContentBindStageLogger;
    }

    public final void abortStage(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow) {
        if (this.mBinder.cancelBind(notificationEntry)) {
            RowContentBindStageLogger rowContentBindStageLogger = this.mLogger;
            rowContentBindStageLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            RowContentBindStageLogger$logAbortStageCancelledBind$2 rowContentBindStageLogger$logAbortStageCancelledBind$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.RowContentBindStageLogger$logAbortStageCancelledBind$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("cancelled bind to abort stage for ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = rowContentBindStageLogger.buffer;
            LogMessage obtain = logBuffer.obtain("RowContentBindStage", logLevel, rowContentBindStageLogger$logAbortStageCancelledBind$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
    }

    public final Object getStageParams(NotificationEntry notificationEntry) {
        Object obj = ((ArrayMap) this.mContentParams).get(notificationEntry);
        if (obj != null) {
            return obj;
        }
        Log.wtf("BindStage", "Entry does not have any stage parameters. key: " + notificationEntry.mKey);
        RowContentBindParams rowContentBindParams = new RowContentBindParams();
        rowContentBindParams.mContentViews = 3;
        rowContentBindParams.mDirtyContentViews = 3;
        return rowContentBindParams;
    }

    public final CancellationSignal requestRebind(NotificationEntry notificationEntry, NotifBindPipeline.BindCallback bindCallback) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        NotifBindPipeline$$ExternalSyntheticLambda1 notifBindPipeline$$ExternalSyntheticLambda1 = this.mBindRequestListener;
        if (notifBindPipeline$$ExternalSyntheticLambda1 != null) {
            NotifBindPipeline notifBindPipeline = notifBindPipeline$$ExternalSyntheticLambda1.f$0;
            NotifBindPipeline.BindEntry bindEntry = (NotifBindPipeline.BindEntry) ((ArrayMap) notifBindPipeline.mBindEntries).get(notificationEntry);
            if (bindEntry != null) {
                bindEntry.invalidated = true;
                if (bindCallback != null) {
                    ArraySet arraySet = (ArraySet) bindEntry.callbacks;
                    arraySet.add(bindCallback);
                    NotifBindPipeline$$ExternalSyntheticLambda3 notifBindPipeline$$ExternalSyntheticLambda3 = new NotifBindPipeline$$ExternalSyntheticLambda3(arraySet, bindCallback);
                    synchronized (cancellationSignal) {
                        while (cancellationSignal.mCancelInProgress) {
                            try {
                                try {
                                    cancellationSignal.wait();
                                } catch (InterruptedException unused) {
                                }
                            } finally {
                            }
                        }
                        if (cancellationSignal.mOnCancelListener != notifBindPipeline$$ExternalSyntheticLambda3) {
                            cancellationSignal.mOnCancelListener = notifBindPipeline$$ExternalSyntheticLambda3;
                            if (cancellationSignal.mIsCanceled) {
                                notifBindPipeline$$ExternalSyntheticLambda3.onCancel();
                            }
                        }
                    }
                }
                notifBindPipeline.requestPipelineRun(notificationEntry);
            }
        }
        return cancellationSignal;
    }
}
