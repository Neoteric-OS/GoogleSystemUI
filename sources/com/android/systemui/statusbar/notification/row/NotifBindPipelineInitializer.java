package com.android.systemui.statusbar.notification.row;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifBindPipelineInitializer {
    public NotifBindPipeline mNotifBindPipeline;
    public RowContentBindStage mRowContentBindStage;

    public final void initialize() {
        NotifBindPipeline notifBindPipeline = this.mNotifBindPipeline;
        notifBindPipeline.getClass();
        RowContentBindStage rowContentBindStage = this.mRowContentBindStage;
        String name = rowContentBindStage.getClass().getName();
        NotifBindPipelineLogger notifBindPipelineLogger = notifBindPipeline.mLogger;
        notifBindPipelineLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        NotifBindPipelineLogger$logStageSet$2 notifBindPipelineLogger$logStageSet$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger$logStageSet$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Stage set: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = notifBindPipelineLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifBindPipeline", logLevel, notifBindPipelineLogger$logStageSet$2, null);
        ((LogMessageImpl) obtain).str1 = name;
        logBuffer.commit(obtain);
        notifBindPipeline.mStage = rowContentBindStage;
        rowContentBindStage.mBindRequestListener = new NotifBindPipeline$$ExternalSyntheticLambda1(notifBindPipeline);
    }
}
