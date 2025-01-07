package com.android.systemui.statusbar.notification.row;

import android.os.Message;
import android.util.ArrayMap;
import android.util.ArraySet;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.row.NotificationEntryProcessorFactoryLooperImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifBindPipeline {
    public final AnonymousClass1 mCollectionListener;
    public final NotifBindPipelineLogger mLogger;
    public RowContentBindStage mStage;
    public final NotificationEntryProcessorFactoryLooperImpl.HandlerProcessor mStartProcessor;
    public final Map mBindEntries = new ArrayMap();
    public final List mScratchCallbacksList = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface BindCallback {
        void onBindFinished(NotificationEntry notificationEntry);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BindEntry {
        public final Set callbacks = new ArraySet();
        public boolean invalidated;
        public ExpandableNotificationRow row;
    }

    public NotifBindPipeline(CommonNotifCollection commonNotifCollection, NotifBindPipelineLogger notifBindPipelineLogger, NotificationEntryProcessorFactoryLooperImpl notificationEntryProcessorFactoryLooperImpl) {
        ((NotifPipeline) commonNotifCollection).addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipeline.1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryCleanUp(NotificationEntry notificationEntry) {
                NotifBindPipeline notifBindPipeline = NotifBindPipeline.this;
                ExpandableNotificationRow expandableNotificationRow = ((BindEntry) ((ArrayMap) notifBindPipeline.mBindEntries).remove(notificationEntry)).row;
                if (expandableNotificationRow != null) {
                    notifBindPipeline.mStage.abortStage(notificationEntry, expandableNotificationRow);
                }
                ((ArrayMap) notifBindPipeline.mStage.mContentParams).remove(notificationEntry);
                NotificationEntryProcessorFactoryLooperImpl.HandlerProcessor handlerProcessor = notifBindPipeline.mStartProcessor;
                handlerProcessor.getClass();
                handlerProcessor.removeMessages(1, notificationEntry);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryInit(NotificationEntry notificationEntry) {
                NotifBindPipeline notifBindPipeline = NotifBindPipeline.this;
                ((ArrayMap) notifBindPipeline.mBindEntries).put(notificationEntry, new BindEntry());
                Map map = notifBindPipeline.mStage.mContentParams;
                RowContentBindParams rowContentBindParams = new RowContentBindParams();
                rowContentBindParams.mContentViews = 3;
                rowContentBindParams.mDirtyContentViews = 3;
                ((ArrayMap) map).put(notificationEntry, rowContentBindParams);
            }
        });
        this.mLogger = notifBindPipelineLogger;
        this.mStartProcessor = new NotificationEntryProcessorFactoryLooperImpl.HandlerProcessor(notificationEntryProcessorFactoryLooperImpl.mMainLooper, new NotifBindPipeline$$ExternalSyntheticLambda0(this));
    }

    public final void requestPipelineRun(NotificationEntry notificationEntry) {
        NotifBindPipelineLogger notifBindPipelineLogger = this.mLogger;
        notifBindPipelineLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        NotifBindPipelineLogger$logRequestPipelineRun$2 notifBindPipelineLogger$logRequestPipelineRun$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger$logRequestPipelineRun$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Request pipeline run for notif: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = notifBindPipelineLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifBindPipeline", logLevel, notifBindPipelineLogger$logRequestPipelineRun$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        ExpandableNotificationRow expandableNotificationRow = ((BindEntry) ((ArrayMap) this.mBindEntries).get(notificationEntry)).row;
        if (expandableNotificationRow == null) {
            LogMessage obtain2 = logBuffer.obtain("NotifBindPipeline", logLevel, new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger$logRequestPipelineRowNotSet$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Row is not set so pipeline will not run. notif = ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain2);
        } else {
            this.mStage.abortStage(notificationEntry, expandableNotificationRow);
            NotificationEntryProcessorFactoryLooperImpl.HandlerProcessor handlerProcessor = this.mStartProcessor;
            handlerProcessor.getClass();
            if (handlerProcessor.hasMessages(1, notificationEntry)) {
                return;
            }
            handlerProcessor.sendMessage(Message.obtain(handlerProcessor, 1, notificationEntry));
        }
    }
}
