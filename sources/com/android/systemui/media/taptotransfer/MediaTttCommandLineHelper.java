package com.android.systemui.media.taptotransfer;

import android.app.StatusBarManager;
import android.content.Context;
import android.media.MediaRoute2Info;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper;
import com.android.systemui.media.taptotransfer.receiver.ChipStateReceiver;
import com.android.systemui.media.taptotransfer.sender.ChipStateSender;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTttCommandLineHelper implements CoreStartable {
    public final CommandRegistry commandRegistry;
    public final Context context;
    public final Executor mainExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SenderCommand implements Command {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ MediaTttCommandLineHelper this$0;

        public /* synthetic */ SenderCommand(MediaTttCommandLineHelper mediaTttCommandLineHelper, int i) {
            this.$r8$classId = i;
            this.this$0 = mediaTttCommandLineHelper;
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            boolean z;
            Executor executor;
            Runnable runnable;
            switch (this.$r8$classId) {
                case 0:
                    if (list.size() < 2) {
                        printWriter.println("Usage: adb shell cmd statusbar media-ttt-chip-sender <deviceName> <chipState> useAppIcon=[true|false] id=<id> showUndo=[true|false]");
                        printWriter.println("Note: useAppIcon, id, and showUndo are optional additional commands.");
                        break;
                    } else {
                        String str = (String) list.get(0);
                        boolean z2 = true;
                        String str2 = (String) list.get(1);
                        String str3 = "id";
                        if (list.size() == 2) {
                            z = true;
                        } else {
                            z = true;
                            for (String str4 : list.subList(2, list.size())) {
                                if (Intrinsics.areEqual(str4, "useAppIcon=false")) {
                                    z2 = false;
                                } else if (Intrinsics.areEqual(str4, "showUndo=false")) {
                                    z = false;
                                } else if (str4.substring(0, 3).equals("id=")) {
                                    str3 = str4.substring(3);
                                }
                            }
                        }
                        try {
                            ChipStateSender.Companion.getClass();
                            int stateInt = ChipStateSender.valueOf(str2).getStateInt();
                            final Integer valueOf = Integer.valueOf(stateInt);
                            MediaTttCommandLineHelper mediaTttCommandLineHelper = this.this$0;
                            StatusBarManager statusBarManager = (StatusBarManager) mediaTttCommandLineHelper.context.getSystemService("statusbar");
                            MediaRoute2Info.Builder addFeature = new MediaRoute2Info.Builder(str3, str).addFeature("feature");
                            if (z2) {
                                addFeature.setClientPackageName("com.android.systemui");
                            }
                            if ((stateInt == 4 || stateInt == 5) && z) {
                                executor = mediaTttCommandLineHelper.mainExecutor;
                                runnable = new Runnable() { // from class: com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper$SenderCommand$execute$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        Log.i("MediaTransferCli", "Undo triggered for " + valueOf);
                                    }
                                };
                            } else {
                                executor = null;
                                runnable = null;
                            }
                            statusBarManager.updateMediaTapToTransferSenderDisplay(stateInt, addFeature.build(), executor, runnable);
                            break;
                        } catch (IllegalArgumentException unused) {
                            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "Invalid command name ", str2);
                            return;
                        }
                    }
                    break;
                default:
                    if (list.isEmpty()) {
                        printWriter.println("Usage: adb shell cmd statusbar media-ttt-chip-receiver <chipState> useAppIcon=[true|false] <id>");
                        break;
                    } else {
                        String str5 = (String) list.get(0);
                        try {
                            ChipStateReceiver.Companion.getClass();
                            int stateInt2 = ChipStateReceiver.valueOf(str5).getStateInt();
                            StatusBarManager statusBarManager2 = (StatusBarManager) this.this$0.context.getSystemService("statusbar");
                            MediaRoute2Info.Builder addFeature2 = new MediaRoute2Info.Builder(list.size() >= 3 ? (String) list.get(2) : "id", "Test Name").addFeature("feature");
                            if (list.size() < 2 || !Intrinsics.areEqual(list.get(1), "useAppIcon=false")) {
                                addFeature2.setClientPackageName("com.android.systemui");
                            }
                            statusBarManager2.updateMediaTapToTransferReceiverDisplay(stateInt2, addFeature2.build(), null, null);
                            break;
                        } catch (IllegalArgumentException unused2) {
                            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "Invalid command name ", str5);
                        }
                    }
            }
        }
    }

    public MediaTttCommandLineHelper(CommandRegistry commandRegistry, Context context, Executor executor) {
        this.commandRegistry = commandRegistry;
        this.context = context;
        this.mainExecutor = executor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Function0 function0 = new Function0() { // from class: com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper$start$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new MediaTttCommandLineHelper.SenderCommand(MediaTttCommandLineHelper.this, 0);
            }
        };
        CommandRegistry commandRegistry = this.commandRegistry;
        commandRegistry.registerCommand("media-ttt-chip-sender", function0);
        commandRegistry.registerCommand("media-ttt-chip-receiver", new Function0() { // from class: com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper$start$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new MediaTttCommandLineHelper.SenderCommand(MediaTttCommandLineHelper.this, 1);
            }
        });
    }
}
