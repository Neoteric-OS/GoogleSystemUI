package com.android.systemui.keyguard.ui.view.layout;

import android.text.TextUtils;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.data.repository.KeyguardBlueprintRepository;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBlueprintCommandListener implements CoreStartable {
    public final CommandRegistry commandRegistry;
    public final KeyguardBlueprintInteractor keyguardBlueprintInteractor;
    public final KeyguardBlueprintRepository keyguardBlueprintRepository;
    public final KeyguardLayoutManagerCommand layoutCommand = new KeyguardLayoutManagerCommand();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyguardLayoutManagerCommand implements Command {
        public KeyguardLayoutManagerCommand() {
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            boolean applyBlueprint;
            int i = 0;
            String str = (String) CollectionsKt.getOrNull(0, list);
            KeyguardBlueprintCommandListener keyguardBlueprintCommandListener = KeyguardBlueprintCommandListener.this;
            if (str != null && !str.toLowerCase(Locale.ROOT).equals("help")) {
                if (TextUtils.isDigitsOnly(str)) {
                    printWriter.println("Invalid argument! Use string ids.");
                    return;
                }
                KeyguardBlueprintInteractor keyguardBlueprintInteractor = keyguardBlueprintCommandListener.keyguardBlueprintInteractor;
                if (str.equals(((KeyguardBlueprint) keyguardBlueprintInteractor.blueprint.getValue()).getId())) {
                    keyguardBlueprintInteractor.refreshBlueprint(IntraBlueprintTransition.Type.NoTransition);
                    applyBlueprint = true;
                } else {
                    applyBlueprint = keyguardBlueprintInteractor.keyguardBlueprintRepository.applyBlueprint(str);
                }
                if (applyBlueprint) {
                    printWriter.println("Transition succeeded!");
                    return;
                } else {
                    printWriter.println("Invalid argument! To see available blueprint ids, run:");
                    printWriter.println("$ adb shell cmd statusbar blueprint help");
                    return;
                }
            }
            printWriter.println("Usage: $ adb shell cmd statusbar blueprint <blueprintId>");
            printWriter.println("Existing Blueprint Ids: ");
            for (Object obj : keyguardBlueprintCommandListener.keyguardBlueprintRepository.blueprintIdMap.entrySet()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                printWriter.println(i + ": " + ((Map.Entry) obj).getKey());
                i = i2;
            }
        }
    }

    public KeyguardBlueprintCommandListener(CommandRegistry commandRegistry, KeyguardBlueprintRepository keyguardBlueprintRepository, KeyguardBlueprintInteractor keyguardBlueprintInteractor) {
        this.commandRegistry = commandRegistry;
        this.keyguardBlueprintRepository = keyguardBlueprintRepository;
        this.keyguardBlueprintInteractor = keyguardBlueprintInteractor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.commandRegistry.registerCommand("blueprint", new Function0() { // from class: com.android.systemui.keyguard.ui.view.layout.KeyguardBlueprintCommandListener$start$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return KeyguardBlueprintCommandListener.this.layoutCommand;
            }
        });
    }
}
