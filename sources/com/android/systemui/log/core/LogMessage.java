package com.android.systemui.log.core;

import android.icu.text.SimpleDateFormat;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LogMessage {
    default void dump(PrintWriter printWriter) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = LogMessageKt.DATE_FORMAT;
        String format = simpleDateFormat.format(Long.valueOf(getTimestamp()));
        String shortString = getLevel().getShortString();
        String str = (String) getMessagePrinter().invoke(this);
        Intrinsics.checkNotNull(format);
        LogMessageKt.printLikeLogcat(printWriter, format, shortString, getTag(), str);
        Throwable exception = getException();
        if (exception != null) {
            exception.printStackTrace(printWriter);
        }
    }

    boolean getBool1();

    boolean getBool2();

    boolean getBool3();

    boolean getBool4();

    double getDouble1();

    Throwable getException();

    int getInt1();

    int getInt2();

    LogLevel getLevel();

    long getLong1();

    long getLong2();

    Function1 getMessagePrinter();

    String getStr1();

    String getStr2();

    String getStr3();

    String getTag();

    long getTimestamp();

    void setBool1(boolean z);

    void setBool2(boolean z);

    void setBool3(boolean z);

    void setBool4(boolean z);

    void setDouble1(double d);

    void setInt1(int i);

    void setInt2(int i);

    void setLong1(long j);

    void setLong2(long j);

    void setStr1(String str);

    void setStr2(String str);

    void setStr3(String str);
}
