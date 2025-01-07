package com.android.systemui.log;

import com.android.systemui.log.core.LogLevel;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LogBufferKt {
    public static final LogMessageImpl FROZEN_MESSAGE;

    static {
        LogLevel logLevel = LogLevel.DEBUG;
        Function1 function1 = LogMessageImplKt.DEFAULT_PRINTER;
        LogMessageImpl logMessageImpl = new LogMessageImpl();
        logMessageImpl.level = logLevel;
        logMessageImpl.tag = "UnknownTag";
        logMessageImpl.timestamp = 0L;
        logMessageImpl.messagePrinter = function1;
        logMessageImpl.exception = null;
        logMessageImpl.str1 = null;
        logMessageImpl.str2 = null;
        logMessageImpl.str3 = null;
        logMessageImpl.int1 = 0;
        logMessageImpl.int2 = 0;
        logMessageImpl.long1 = 0L;
        logMessageImpl.long2 = 0L;
        logMessageImpl.double1 = 0.0d;
        logMessageImpl.bool1 = false;
        logMessageImpl.bool2 = false;
        logMessageImpl.bool3 = false;
        logMessageImpl.bool4 = false;
        FROZEN_MESSAGE = logMessageImpl;
    }
}
