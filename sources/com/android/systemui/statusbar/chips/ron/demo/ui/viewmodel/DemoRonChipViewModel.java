package com.android.systemui.statusbar.chips.ron.demo.ui.viewmodel;

import android.content.pm.PackageManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.chips.ron.demo.ui.viewmodel.DemoRonChipViewModel;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.commandline.ParseableCommand;
import com.android.systemui.statusbar.commandline.Type;
import com.android.systemui.statusbar.commandline.ValueParserKt$parseInt$1;
import com.android.systemui.util.time.SystemClock;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoRonChipViewModel implements CoreStartable {
    public final StateFlowImpl _chip;
    public final ReadonlyStateFlow chip = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(new OngoingActivityChipModel.Hidden(true)));
    public final CommandRegistry commandRegistry;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DemoRonCommand extends ParseableCommand {
        static {
            Reflection.factory.getClass();
        }

        @Override // com.android.systemui.statusbar.commandline.ParseableCommand
        public final void execute(PrintWriter printWriter) {
            printWriter.println("Error: com.android.systemui.status_bar_ron_chips must be enabled before using this demo feature");
        }
    }

    public DemoRonChipViewModel(CommandRegistry commandRegistry, PackageManager packageManager, SystemClock systemClock) {
        this.commandRegistry = commandRegistry;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.commandRegistry.registerCommand("demo-ron", new Function0() { // from class: com.android.systemui.statusbar.chips.ron.demo.ui.viewmodel.DemoRonChipViewModel$start$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DemoRonChipViewModel.DemoRonCommand demoRonCommand = new DemoRonChipViewModel.DemoRonCommand("demo-ron");
                ValueParserKt$parseInt$1 valueParserKt$parseInt$1 = Type.String;
                demoRonCommand.param("packageName", "p", "The package name for the demo RON app", valueParserKt$parseInt$1);
                demoRonCommand.param("text", "t", "Text to display in the chip", valueParserKt$parseInt$1);
                demoRonCommand.param("color", "c", "The color to show as the chip background color. You can either just write a basic color like 'red' or 'green', or you can include a #RRGGBB string in this format: \"\\\\#434343\".", Type.Color);
                demoRonCommand.flag("hide", null, "Hides any existing demo RON chip");
                return demoRonCommand;
            }
        });
    }
}
