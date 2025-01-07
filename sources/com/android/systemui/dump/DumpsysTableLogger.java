package com.android.systemui.dump;

import android.os.Trace;
import java.io.PrintWriter;
import java.util.List;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DumpsysTableLogger {
    public final List columns;
    public final List rows;
    public final String sectionName;

    public DumpsysTableLogger(String str, List list, List list2) {
        this.sectionName = str;
        this.columns = list;
        this.rows = list2;
    }

    public final void printTableData(PrintWriter printWriter) {
        Trace.beginSection("DumpsysTableLogger#printTableData");
        PrintWriter append = printWriter.append("SystemUI TableSection START: ");
        String str = this.sectionName;
        append.println(str);
        printWriter.append("version ").println("1");
        CollectionsKt.joinTo$default(this.columns, printWriter, "|", null, 124);
        printWriter.println();
        int size = this.columns.size();
        for (List list : this.rows) {
            if (list.size() == size) {
                CollectionsKt.joinTo$default(list, printWriter, "|", null, 124);
                printWriter.println();
            }
        }
        printWriter.append("SystemUI TableSection END: ").println(str);
        Trace.endSection();
    }
}
