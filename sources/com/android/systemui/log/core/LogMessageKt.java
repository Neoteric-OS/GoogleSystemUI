package com.android.systemui.log.core;

import android.icu.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogMessageKt {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);

    /* JADX INFO: Access modifiers changed from: private */
    public static final void printLikeLogcat(PrintWriter printWriter, String str, String str2, String str3, String str4) {
        printWriter.print(str);
        printWriter.print(" ");
        printWriter.print(str2);
        printWriter.print(" ");
        printWriter.print(str3);
        printWriter.print(": ");
        printWriter.println(str4);
    }
}
