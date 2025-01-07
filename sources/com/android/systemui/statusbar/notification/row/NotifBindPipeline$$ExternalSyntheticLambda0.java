package com.android.systemui.statusbar.notification.row;

import android.util.ArrayMap;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinder;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.row.RowContentBindStage.AnonymousClass1;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotifBindPipeline$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ NotifBindPipeline f$0;

    public /* synthetic */ NotifBindPipeline$$ExternalSyntheticLambda0(NotifBindPipeline notifBindPipeline) {
        this.f$0 = notifBindPipeline;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        NotifBindPipeline notifBindPipeline = this.f$0;
        NotificationEntry notificationEntry = (NotificationEntry) obj;
        NotifBindPipelineLogger notifBindPipelineLogger = notifBindPipeline.mLogger;
        notifBindPipelineLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        NotifBindPipelineLogger$logStartPipeline$2 notifBindPipelineLogger$logStartPipeline$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger$logStartPipeline$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Start pipeline for notif: ", ((LogMessage) obj2).getStr1());
            }
        };
        LogBuffer logBuffer = notifBindPipelineLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifBindPipeline", logLevel, notifBindPipelineLogger$logStartPipeline$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        if (notifBindPipeline.mStage == null) {
            throw new IllegalStateException("No stage was ever set on the pipeline");
        }
        ExpandableNotificationRow expandableNotificationRow = ((NotifBindPipeline.BindEntry) ((ArrayMap) notifBindPipeline.mBindEntries).get(notificationEntry)).row;
        RowContentBindStage rowContentBindStage = notifBindPipeline.mStage;
        NotifBindPipeline$$ExternalSyntheticLambda1 notifBindPipeline$$ExternalSyntheticLambda1 = new NotifBindPipeline$$ExternalSyntheticLambda1(notifBindPipeline);
        RowContentBindParams rowContentBindParams = (RowContentBindParams) rowContentBindStage.getStageParams(notificationEntry);
        RowContentBindStageLogger rowContentBindStageLogger = rowContentBindStage.mLogger;
        rowContentBindStageLogger.getClass();
        RowContentBindStageLogger$logExecutingStage$2 rowContentBindStageLogger$logExecutingStage$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.RowContentBindStageLogger$logExecutingStage$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("executing bind stage for ", logMessage.getStr1(), " with params ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer2 = rowContentBindStageLogger.buffer;
        LogMessage obtain2 = logBuffer2.obtain("RowContentBindStage", logLevel, rowContentBindStageLogger$logExecutingStage$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = rowContentBindParams.toString();
        logBuffer2.commit(obtain2);
        int i = rowContentBindParams.mContentViews;
        int i2 = rowContentBindParams.mDirtyContentViews & i;
        NotificationRowContentBinder notificationRowContentBinder = rowContentBindStage.mBinder;
        notificationRowContentBinder.unbindContent(notificationEntry, expandableNotificationRow, i ^ 255);
        NotificationRowContentBinder.BindParams bindParams = new NotificationRowContentBinder.BindParams();
        bindParams.isMinimized = rowContentBindParams.mUseMinimized;
        bindParams.usesIncreasedHeight = rowContentBindParams.mUseIncreasedHeight;
        bindParams.usesIncreasedHeadsUpHeight = rowContentBindParams.mUseIncreasedHeadsUpHeight;
        boolean z = rowContentBindParams.mViewsNeedReinflation;
        RowContentBindStage.AnonymousClass1 anonymousClass1 = rowContentBindStage.new AnonymousClass1(notifBindPipeline$$ExternalSyntheticLambda1);
        notificationRowContentBinder.cancelBind(notificationEntry);
        rowContentBindStage.mBinder.bindContent(notificationEntry, expandableNotificationRow, i2, bindParams, z, anonymousClass1);
    }
}
