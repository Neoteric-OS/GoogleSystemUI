package com.google.android.systemui.columbus.legacy.sensors;

import android.util.SparseLongArray;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.google.android.systemui.columbus.legacy.ColumbusService$gestureListener$1;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GestureController implements Dumpable {
    public ColumbusService$gestureListener$1 gestureListener;
    public final GestureSensor gestureSensor;
    public final GestureController$gestureSensorListener$1 gestureSensorListener;
    public final SparseLongArray lastTimestampMap = new SparseLongArray();
    public long softGateBlockCount;
    public final GestureController$softGateListener$1 softGateListener;
    public final Set softGates;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ColumbusCommand implements Command {
        public ColumbusCommand() {
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            if (list.isEmpty()) {
                printWriter.println("usage: quick-tap <command>");
                printWriter.println("Available commands:");
                printWriter.println("  trigger");
            } else {
                if (Intrinsics.areEqual((String) list.get(0), "trigger")) {
                    GestureController.this.gestureSensorListener.onGestureDetected(1, null);
                    return;
                }
                printWriter.println("usage: quick-tap <command>");
                printWriter.println("Available commands:");
                printWriter.println("  trigger");
            }
        }
    }

    public GestureController(GestureSensor gestureSensor, Set set, CommandRegistry commandRegistry, UiEventLogger uiEventLogger) {
        this.gestureSensor = gestureSensor;
        this.softGates = set;
        this.uiEventLogger = uiEventLogger;
        GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = new GestureController$gestureSensorListener$1(this);
        this.gestureSensorListener = gestureController$gestureSensorListener$1;
        this.softGateListener = new GestureController$softGateListener$1();
        gestureSensor.setGestureListener(gestureController$gestureSensorListener$1);
        commandRegistry.registerCommand("quick-tap", new Function0() { // from class: com.google.android.systemui.columbus.legacy.sensors.GestureController.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GestureController.this.new ColumbusCommand();
            }
        });
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("  Soft Blocks: " + this.softGateBlockCount);
        StringBuilder sb = new StringBuilder("  Gesture Sensor: ");
        Object obj = this.gestureSensor;
        sb.append(obj);
        printWriter.println(sb.toString());
        if (obj instanceof Dumpable) {
            ((Dumpable) obj).dump(printWriter, strArr);
        }
    }
}
