package com.android.wm.shell.desktopmode;

import com.android.wm.shell.sysui.ShellCommandHandler;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeShellCommandHandler implements ShellCommandHandler.ShellCommandActionHandler {
    public final DesktopTasksController controller;

    public DesktopModeShellCommandHandler(DesktopTasksController desktopTasksController) {
        this.controller = desktopTasksController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onShellCommand(java.io.PrintWriter r12, java.lang.String[] r13) {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.desktopmode.DesktopModeShellCommandHandler.onShellCommand(java.io.PrintWriter, java.lang.String[]):boolean");
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println("    ".concat(" moveToDesktop <taskId> "));
        printWriter.println("    ".concat("  Move a task with given id to desktop mode."));
        printWriter.println("    ".concat(" moveToNextDisplay <taskId> "));
        printWriter.println("    ".concat("  Move a task with given id to next display."));
    }
}
