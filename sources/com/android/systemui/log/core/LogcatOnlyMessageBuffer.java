package com.android.systemui.log.core;

import android.util.Log;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.LogMessageImplKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogcatOnlyMessageBuffer implements MessageBuffer {
    private boolean isObtained;
    private final LogMessageImpl singleMessage;
    private final LogLevel targetLogLevel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogLevel.values().length];
            try {
                iArr[LogLevel.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LogLevel.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LogLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[LogLevel.WTF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LogcatOnlyMessageBuffer(LogLevel logLevel) {
        this.targetLogLevel = logLevel;
        LogLevel logLevel2 = LogLevel.DEBUG;
        Function1 function1 = LogMessageImplKt.DEFAULT_PRINTER;
        LogMessageImpl logMessageImpl = new LogMessageImpl();
        logMessageImpl.level = logLevel2;
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
        this.singleMessage = logMessageImpl;
    }

    @Override // com.android.systemui.log.core.MessageBuffer
    public synchronized void commit(LogMessage logMessage) {
        try {
            if (!Intrinsics.areEqual(this.singleMessage, logMessage)) {
                throw new IllegalArgumentException("Message argument is not the expected message.");
            }
            if (!this.isObtained) {
                throw new UnsupportedOperationException("Message has not been obtained. Call order is incorrect.");
            }
            if (logMessage.getLevel().compareTo(this.targetLogLevel) >= 0) {
                String str = (String) logMessage.getMessagePrinter().invoke(logMessage);
                switch (WhenMappings.$EnumSwitchMapping$0[logMessage.getLevel().ordinal()]) {
                    case 1:
                        logMessage.getTag();
                        logMessage.getException();
                        break;
                    case 2:
                        Log.d(logMessage.getTag(), str, logMessage.getException());
                        break;
                    case 3:
                        Log.i(logMessage.getTag(), str, logMessage.getException());
                        break;
                    case 4:
                        Log.w(logMessage.getTag(), str, logMessage.getException());
                        break;
                    case 5:
                        Log.e(logMessage.getTag(), str, logMessage.getException());
                        break;
                    case 6:
                        Log.wtf(logMessage.getTag(), str, logMessage.getException());
                        break;
                }
            }
            this.isObtained = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final LogLevel getTargetLogLevel() {
        return this.targetLogLevel;
    }

    @Override // com.android.systemui.log.core.MessageBuffer
    public synchronized LogMessage obtain(String str, LogLevel logLevel, Function1 function1, Throwable th) {
        if (this.isObtained) {
            throw new UnsupportedOperationException("Message has already been obtained. Call order is incorrect.");
        }
        this.singleMessage.reset(str, logLevel, System.currentTimeMillis(), function1, th);
        this.isObtained = true;
        return this.singleMessage;
    }
}
