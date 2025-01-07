package com.android.systemui.media.muteawait;

import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli.MuteAwaitCommand;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import java.io.PrintWriter;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaMuteAwaitConnectionCli implements CoreStartable {
    public final AudioManager audioManager;
    public final CommandRegistry commandRegistry;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MuteAwaitCommand implements Command {
        public MuteAwaitCommand() {
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            int parseInt = Integer.parseInt((String) list.get(0));
            String str = (String) list.get(1);
            EmptyList emptyList = EmptyList.INSTANCE;
            AudioDeviceAttributes audioDeviceAttributes = new AudioDeviceAttributes(2, parseInt, "address", str, emptyList, emptyList);
            String str2 = (String) list.get(2);
            boolean areEqual = Intrinsics.areEqual(str2, "start");
            MediaMuteAwaitConnectionCli mediaMuteAwaitConnectionCli = MediaMuteAwaitConnectionCli.this;
            if (areEqual) {
                mediaMuteAwaitConnectionCli.audioManager.muteAwaitConnection(new int[]{1}, audioDeviceAttributes, 5L, MediaMuteAwaitConnectionCliKt.TIMEOUT_UNITS);
            } else if (Intrinsics.areEqual(str2, "cancel")) {
                mediaMuteAwaitConnectionCli.audioManager.cancelMuteAwaitConnection(audioDeviceAttributes);
            } else {
                FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "Must specify `start` or `cancel`; was ", str2);
            }
        }
    }

    public MediaMuteAwaitConnectionCli(CommandRegistry commandRegistry, AudioManager audioManager) {
        this.commandRegistry = commandRegistry;
        this.audioManager = audioManager;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.commandRegistry.registerCommand("media-mute-await", new Function0() { // from class: com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli$start$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MediaMuteAwaitConnectionCli.this.new MuteAwaitCommand();
            }
        });
    }
}
