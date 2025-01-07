package com.android.systemui.shade;

import android.view.MotionEvent;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TouchLogger {
    public static DispatchTouchLogger touchLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static void logDispatchTouch(String str, MotionEvent motionEvent, boolean z) {
            DispatchTouchLogger dispatchTouchLogger = TouchLogger.touchLogger;
            if (dispatchTouchLogger != null) {
                LogLevel logLevel = LogLevel.DEBUG;
                DispatchTouchLogger$logDispatchTouch$2 dispatchTouchLogger$logDispatchTouch$2 = new DispatchTouchLogger$logDispatchTouch$2(dispatchTouchLogger);
                LogBuffer logBuffer = dispatchTouchLogger.buffer;
                LogMessage obtain = logBuffer.obtain("systemui.shade.touch", logLevel, dispatchTouchLogger$logDispatchTouch$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = str;
                logMessageImpl.int1 = motionEvent.getAction();
                logMessageImpl.long1 = motionEvent.getDownTime();
                logMessageImpl.bool1 = z;
                logBuffer.commit(obtain);
            }
        }
    }
}
