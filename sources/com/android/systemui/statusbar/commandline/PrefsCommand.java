package com.android.systemui.statusbar.commandline;

import com.android.systemui.Prefs;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrefsCommand implements Command {
    @Override // com.android.systemui.statusbar.commandline.Command
    public final void execute(PrintWriter printWriter, List list) {
        if (list.isEmpty()) {
            printWriter.println("usage: prefs <command> [args]");
            printWriter.println("Available commands:");
            printWriter.println("  list-prefs");
            printWriter.println("  set-pref <pref name> <value>");
            return;
        }
        if (!Intrinsics.areEqual((String) list.get(0), "list-prefs")) {
            printWriter.println("usage: prefs <command> [args]");
            printWriter.println("Available commands:");
            printWriter.println("  list-prefs");
            printWriter.println("  set-pref <pref name> <value>");
            return;
        }
        printWriter.println("Available keys:");
        for (Field field : Prefs.Key.class.getDeclaredFields()) {
            printWriter.print("  ");
            printWriter.println(field.get(Prefs.Key.class));
        }
    }
}
