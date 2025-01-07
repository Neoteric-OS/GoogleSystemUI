package com.android.wm.shell.sysui;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShellCommandHandler {
    public final TreeMap mDumpables = new TreeMap();
    public final TreeMap mCommands = new TreeMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ShellCommandActionHandler {
        boolean onShellCommand(PrintWriter printWriter, String[] strArr);

        void printShellCommandHelp(PrintWriter printWriter, String str);
    }

    public final void addCommandCallback(String str, ShellCommandActionHandler shellCommandActionHandler, Object obj) {
        this.mCommands.put(str, shellCommandActionHandler);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, -7021501875761171703L, 0, str, obj.getClass().getSimpleName());
        }
    }

    public final void addDumpCallback(BiConsumer biConsumer, Object obj) {
        this.mDumpables.put(obj.getClass().getSimpleName(), biConsumer);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, 5564691995529851186L, 0, obj.getClass().getSimpleName());
        }
    }
}
