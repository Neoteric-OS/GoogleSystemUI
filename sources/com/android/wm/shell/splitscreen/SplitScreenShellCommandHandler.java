package com.android.wm.shell.splitscreen;

import android.window.WindowContainerTransaction;
import com.android.wm.shell.sysui.ShellCommandHandler;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitScreenShellCommandHandler implements ShellCommandHandler.ShellCommandActionHandler {
    public final SplitScreenController mController;

    public SplitScreenShellCommandHandler(SplitScreenController splitScreenController) {
        this.mController = splitScreenController;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final boolean onShellCommand(PrintWriter printWriter, String[] strArr) {
        char c;
        String str = strArr[0];
        switch (str.hashCode()) {
            case -1521472625:
                if (str.equals("switchSplitPosition")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -91197669:
                if (str.equals("moveToSideStage")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 295561529:
                if (str.equals("removeFromSideStage")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1522429422:
                if (str.equals("setSideStagePosition")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2112044072:
                if (str.equals("exitSplitScreen")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        SplitScreenController splitScreenController = this.mController;
        if (c == 0) {
            if (strArr.length < 3) {
                printWriter.println("Error: task id should be provided as arguments");
                return false;
            }
            int intValue = new Integer(strArr[1]).intValue();
            int intValue2 = strArr.length > 2 ? new Integer(strArr[2]).intValue() : 1;
            splitScreenController.getClass();
            splitScreenController.moveToStage(intValue, intValue2, new WindowContainerTransaction());
            return true;
        }
        if (c == 1) {
            if (strArr.length < 2) {
                printWriter.println("Error: task id should be provided as arguments");
                return false;
            }
            splitScreenController.removeFromSideStage(new Integer(strArr[1]).intValue());
            return true;
        }
        if (c == 2) {
            if (strArr.length < 2) {
                printWriter.println("Error: side stage position should be provided as arguments");
                return false;
            }
            splitScreenController.mStageCoordinator.setSideStagePosition(new Integer(strArr[1]).intValue(), null);
            return true;
        }
        if (c == 3) {
            if (splitScreenController.mStageCoordinator.isSplitScreenVisible()) {
                splitScreenController.mStageCoordinator.switchSplitPosition("shellCommand");
            }
            return true;
        }
        if (c != 4) {
            printWriter.println("Invalid command: " + strArr[0]);
            return false;
        }
        if (strArr.length < 2) {
            printWriter.println("Error: task id should be provided as arguments");
            return false;
        }
        splitScreenController.exitSplitScreen(Integer.parseInt(strArr[1]), 0);
        return true;
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println("    moveToSideStage <taskId> <SideStagePosition>");
        printWriter.println("      Move a task with given id in split-screen mode.");
        printWriter.println("    removeFromSideStage <taskId>");
        printWriter.println("      Remove a task with given id in split-screen mode.");
        printWriter.println("    setSideStagePosition <SideStagePosition>");
        printWriter.println("      Sets the position of the side-stage.");
        printWriter.println("    switchSplitPosition");
        printWriter.println("      Reverses the split.");
        printWriter.println("    exitSplitScreen <taskId>");
        printWriter.println("      Exits split screen and leaves the provided split task on top.");
    }
}
