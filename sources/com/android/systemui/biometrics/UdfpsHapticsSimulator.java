package com.android.systemui.biometrics;

import android.media.AudioAttributes;
import android.os.VibrationEffect;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import java.io.PrintWriter;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsHapticsSimulator implements Command {
    public final AudioAttributes sonificationEffects = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    public UdfpsController udfpsController;
    public final VibratorHelper vibrator;

    public UdfpsHapticsSimulator(CommandRegistry commandRegistry, VibratorHelper vibratorHelper) {
        this.vibrator = vibratorHelper;
        commandRegistry.registerCommand("udfps-haptic", new Function0() { // from class: com.android.systemui.biometrics.UdfpsHapticsSimulator.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UdfpsHapticsSimulator.this;
            }
        });
    }

    public static void invalidCommand$1(PrintWriter printWriter) {
        printWriter.println("invalid command");
        printWriter.println("Usage: adb shell cmd statusbar udfps-haptic <haptic>");
        printWriter.println("Available commands:");
        printWriter.println("  start");
        printWriter.println("  success, always plays CLICK haptic");
        printWriter.println("  error, always plays DOUBLE_CLICK haptic");
    }

    @Override // com.android.systemui.statusbar.commandline.Command
    public final void execute(PrintWriter printWriter, List list) {
        if (list.isEmpty()) {
            invalidCommand$1(printWriter);
            return;
        }
        String str = (String) list.get(0);
        int hashCode = str.hashCode();
        VibratorHelper vibratorHelper = this.vibrator;
        if (hashCode != -1867169789) {
            if (hashCode != 96784904) {
                if (hashCode == 109757538 && str.equals("start")) {
                    UdfpsController udfpsController = this.udfpsController;
                    if (udfpsController != null) {
                        udfpsController.playStartHaptic();
                        return;
                    }
                    return;
                }
            } else if (str.equals("error")) {
                vibratorHelper.vibrate(VibrationEffect.get(1), this.sonificationEffects);
                return;
            }
        } else if (str.equals("success")) {
            vibratorHelper.vibrate(VibrationEffect.get(0), this.sonificationEffects);
            return;
        }
        invalidCommand$1(printWriter);
    }
}
