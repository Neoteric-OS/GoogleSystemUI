package com.android.systemui.util;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.util.IndentingPrintWriter;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DumpUtilsKt {
    public static final IndentingPrintWriter asIndenting(PrintWriter printWriter) {
        IndentingPrintWriter indentingPrintWriter = printWriter instanceof IndentingPrintWriter ? (IndentingPrintWriter) printWriter : null;
        return indentingPrintWriter == null ? new IndentingPrintWriter(printWriter) : indentingPrintWriter;
    }

    public static final void println(IndentingPrintWriter indentingPrintWriter, String str, Object obj) {
        indentingPrintWriter.append(str).append('=').println(obj);
    }

    public static final String visibilityString(int i) {
        return i != 0 ? i != 4 ? i != 8 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown:") : "gone" : "invisible" : "visible";
    }

    public static final void withIncreasedIndent(IndentingPrintWriter indentingPrintWriter, Runnable runnable) {
        indentingPrintWriter.increaseIndent();
        try {
            runnable.run();
        } finally {
            indentingPrintWriter.decreaseIndent();
        }
    }
}
