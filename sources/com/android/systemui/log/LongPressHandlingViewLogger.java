package com.android.systemui.log;

import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LongPressHandlingViewLogger {
    public final LogBuffer logBuffer;
    public final String tag;

    public LongPressHandlingViewLogger(LogBuffer logBuffer, String str) {
        this.logBuffer = logBuffer;
        this.tag = str;
    }

    public final void cancelingLongPressDueToTouchSlop(int i, float f) {
        LogLevel logLevel = LogLevel.DEBUG;
        LongPressHandlingViewLogger$cancelingLongPressDueToTouchSlop$2 longPressHandlingViewLogger$cancelingLongPressDueToTouchSlop$2 = new Function1() { // from class: com.android.systemui.log.LongPressHandlingViewLogger$cancelingLongPressDueToTouchSlop$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "on MotionEvent.Motion: May cancel long press due to movement: distanceMoved: " + logMessage.getDouble1() + ", allowedTouchSlop: " + logMessage.getInt1() + " ";
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain(this.tag, logLevel, longPressHandlingViewLogger$cancelingLongPressDueToTouchSlop$2, null);
        ((LogMessageImpl) obtain).double1 = f;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongPressHandlingViewLogger)) {
            return false;
        }
        LongPressHandlingViewLogger longPressHandlingViewLogger = (LongPressHandlingViewLogger) obj;
        return Intrinsics.areEqual(this.logBuffer, longPressHandlingViewLogger.logBuffer) && Intrinsics.areEqual(this.tag, longPressHandlingViewLogger.tag);
    }

    public final int hashCode() {
        return this.tag.hashCode() + (this.logBuffer.hashCode() * 31);
    }

    public final void onUpEvent(float f, int i, long j) {
        LogLevel logLevel = LogLevel.DEBUG;
        LongPressHandlingViewLogger$onUpEvent$2 longPressHandlingViewLogger$onUpEvent$2 = new Function1() { // from class: com.android.systemui.log.LongPressHandlingViewLogger$onUpEvent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "on MotionEvent.Up: distanceMoved: " + logMessage.getDouble1() + ", allowedTouchSlop: " + logMessage.getInt1() + ", eventDuration: " + logMessage.getLong1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain(this.tag, logLevel, longPressHandlingViewLogger$onUpEvent$2, null);
        ((LogMessageImpl) obtain).double1 = f;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.long1 = j;
        logBuffer.commit(obtain);
    }

    public final void schedulingLongPress(long j) {
        LogLevel logLevel = LogLevel.DEBUG;
        LongPressHandlingViewLogger$schedulingLongPress$2 longPressHandlingViewLogger$schedulingLongPress$2 = new Function1() { // from class: com.android.systemui.log.LongPressHandlingViewLogger$schedulingLongPress$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return "on MotionEvent.Down: scheduling long press activation after " + ((LogMessage) obj).getLong1() + " ms";
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain(this.tag, logLevel, longPressHandlingViewLogger$schedulingLongPress$2, null);
        ((LogMessageImpl) obtain).long1 = j;
        logBuffer.commit(obtain);
    }

    public final String toString() {
        return "LongPressHandlingViewLogger(logBuffer=" + this.logBuffer + ", tag=" + this.tag + ")";
    }
}
