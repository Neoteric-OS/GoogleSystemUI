package com.android.keyguard.logging;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrimLogger {
    public static final String TAG;
    public final LogBuffer buffer;

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(ScrimLogger.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    public ScrimLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void d(String str, String str2, Object obj) {
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "::");
        m.append(TAG);
        String sb = m.toString();
        LogLevel logLevel = LogLevel.DEBUG;
        ScrimLogger$log$2 scrimLogger$log$2 = new Function1() { // from class: com.android.keyguard.logging.ScrimLogger$log$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain(sb, logLevel, scrimLogger$log$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str2;
        logMessageImpl.str2 = obj.toString();
        logBuffer.commit(obtain);
    }
}
