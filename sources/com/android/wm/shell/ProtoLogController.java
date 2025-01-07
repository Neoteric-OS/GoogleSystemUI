package com.android.wm.shell;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.protolog.common.ILogger;
import com.android.internal.protolog.common.IProtoLog;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellInit;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ProtoLogController implements ShellCommandHandler.ShellCommandActionHandler {
    public final ShellCommandHandler mShellCommandHandler;
    public final IProtoLog mShellProtoLog;

    public ProtoLogController(ShellInit shellInit, ShellCommandHandler shellCommandHandler) {
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.ProtoLogController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ProtoLogController protoLogController = ProtoLogController.this;
                protoLogController.mShellCommandHandler.addCommandCallback("protolog", protoLogController, protoLogController);
            }
        }, this);
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellProtoLog = ProtoLogImpl_411527699.getSingleInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final boolean onShellCommand(final PrintWriter printWriter, String[] strArr) {
        char c;
        Objects.requireNonNull(printWriter);
        ILogger iLogger = new ILogger() { // from class: com.android.wm.shell.ProtoLogController$$ExternalSyntheticLambda1
            public final void log(String str) {
                printWriter.println(str);
            }
        };
        String str = strArr[0];
        switch (str.hashCode()) {
            case -1475003593:
                if (str.equals("enable-text")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1298848381:
                if (str.equals("enable")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1032071950:
                if (str.equals("disable-text")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -892481550:
                if (str.equals("status")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -390772652:
                if (str.equals("save-for-bugreport")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 3540994:
                if (str.equals("stop")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 109757538:
                if (str.equals("start")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1671308008:
                if (str.equals("disable")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mShellProtoLog.getStatus();
                break;
            case 1:
                this.mShellProtoLog.startProtoLog(printWriter);
                break;
            case 2:
                this.mShellProtoLog.stopProtoLog(printWriter, true);
                break;
            case 3:
                String[] strArr2 = (String[]) Arrays.copyOfRange(strArr, 1, strArr.length);
                if (this.mShellProtoLog.startLoggingToLogcat(strArr2, iLogger) == 0) {
                    printWriter.println("Starting logging on groups: " + Arrays.toString(strArr2));
                    break;
                }
                break;
            case 4:
                String[] strArr3 = (String[]) Arrays.copyOfRange(strArr, 1, strArr.length);
                if (this.mShellProtoLog.stopLoggingToLogcat(strArr3, iLogger) == 0) {
                    printWriter.println("Stopping logging on groups: " + Arrays.toString(strArr3));
                    break;
                }
                break;
            case 5:
                if (this.mShellProtoLog.startLoggingToLogcat((String[]) Arrays.copyOfRange(strArr, 1, strArr.length), iLogger) == 0) {
                    break;
                }
                break;
            case 6:
                if (this.mShellProtoLog.stopLoggingToLogcat((String[]) Arrays.copyOfRange(strArr, 1, strArr.length), iLogger) == 0) {
                    break;
                }
                break;
            case 7:
                if (!this.mShellProtoLog.isProtoEnabled()) {
                    printWriter.println("Logging to proto is not enabled for WMShell.");
                    break;
                } else {
                    this.mShellProtoLog.stopProtoLog(printWriter, true);
                    this.mShellProtoLog.startProtoLog(printWriter);
                    break;
                }
            default:
                printWriter.println("Invalid command: " + strArr[0]);
                printShellCommandHelp(printWriter, "");
                break;
        }
        return false;
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println(str.concat("status"));
        printWriter.println(str.concat("  Get current ProtoLog status."));
        printWriter.println(str.concat("start"));
        printWriter.println(str.concat("  Start proto logging."));
        printWriter.println(str.concat("stop"));
        printWriter.println(str.concat("  Stop proto logging and flush to file."));
        printWriter.println(str.concat("enable [group...]"));
        printWriter.println(str.concat("  Enable proto logging for given groups."));
        printWriter.println(str.concat("disable [group...]"));
        printWriter.println(str.concat("  Disable proto logging for given groups."));
        printWriter.println(str.concat("enable-text [group...]"));
        printWriter.println(str.concat("  Enable logcat logging for given groups."));
        printWriter.println(str.concat("disable-text [group...]"));
        printWriter.println(str.concat("  Disable logcat logging for given groups."));
        printWriter.println(str.concat("save-for-bugreport"));
        printWriter.println(str.concat("  Flush proto logging to file, only if it's enabled."));
    }
}
