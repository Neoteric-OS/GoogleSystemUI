package com.android.wm.shell.compatui;

import com.android.wm.shell.sysui.ShellCommandHandler;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CompatUIShellCommandHandler implements ShellCommandHandler.ShellCommandActionHandler {
    public final CompatUIConfiguration mCompatUIConfiguration;
    public final ShellCommandHandler mShellCommandHandler;

    public CompatUIShellCommandHandler(ShellCommandHandler shellCommandHandler, CompatUIConfiguration compatUIConfiguration) {
        this.mShellCommandHandler = shellCommandHandler;
        this.mCompatUIConfiguration = compatUIConfiguration;
    }

    public static boolean invokeOrError(String str, PrintWriter printWriter, Consumer consumer) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 48) {
            if (str.equals("0")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 49) {
            if (str.equals("1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 3569038) {
            if (hashCode == 97196323 && str.equals("false")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("true")) {
                c = 1;
            }
            c = 65535;
        }
        Boolean bool = (c == 0 || c == 1) ? Boolean.TRUE : (c == 2 || c == 3) ? Boolean.FALSE : null;
        if (bool == null) {
            printWriter.println("Error: expected true, 1, false, 0.");
            return false;
        }
        consumer.accept(bool);
        return true;
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final boolean onShellCommand(PrintWriter printWriter, String[] strArr) {
        char c;
        if (strArr.length != 2) {
            printWriter.println("Invalid command: " + strArr[0]);
            return false;
        }
        String str = strArr[0];
        int hashCode = str.hashCode();
        if (hashCode != -103552784) {
            if (hashCode == 236996362 && str.equals("restartDialogEnabled")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("reachabilityEducationEnabled")) {
                c = 1;
            }
            c = 65535;
        }
        final CompatUIConfiguration compatUIConfiguration = this.mCompatUIConfiguration;
        if (c == 0) {
            String str2 = strArr[1];
            Objects.requireNonNull(compatUIConfiguration);
            final int i = 0;
            return invokeOrError(str2, printWriter, new Consumer() { // from class: com.android.wm.shell.compatui.CompatUIShellCommandHandler$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    int i2 = i;
                    CompatUIConfiguration compatUIConfiguration2 = compatUIConfiguration;
                    Boolean bool = (Boolean) obj;
                    switch (i2) {
                        case 0:
                            compatUIConfiguration2.mIsRestartDialogOverrideEnabled = bool.booleanValue();
                            break;
                        default:
                            bool.booleanValue();
                            compatUIConfiguration2.getClass();
                            break;
                    }
                }
            });
        }
        if (c != 1) {
            printWriter.println("Invalid command: " + strArr[0]);
            return false;
        }
        String str3 = strArr[1];
        Objects.requireNonNull(compatUIConfiguration);
        final int i2 = 1;
        return invokeOrError(str3, printWriter, new Consumer() { // from class: com.android.wm.shell.compatui.CompatUIShellCommandHandler$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                CompatUIConfiguration compatUIConfiguration2 = compatUIConfiguration;
                Boolean bool = (Boolean) obj;
                switch (i22) {
                    case 0:
                        compatUIConfiguration2.mIsRestartDialogOverrideEnabled = bool.booleanValue();
                        break;
                    default:
                        bool.booleanValue();
                        compatUIConfiguration2.getClass();
                        break;
                }
            }
        });
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println("    restartDialogEnabled [0|false|1|true]");
        printWriter.println("      Enable/Disable the restart education dialog for Size Compat Mode");
        printWriter.println("    reachabilityEducationEnabled [0|false|1|true]");
        printWriter.println("      Enable/Disable the restart education dialog for letterbox reachability");
        printWriter.println("      Disable the restart education dialog for letterbox reachability");
    }
}
