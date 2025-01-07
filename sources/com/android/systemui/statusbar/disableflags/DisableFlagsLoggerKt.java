package com.android.systemui.statusbar.disableflags;

import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DisableFlagsLoggerKt {
    public static final List defaultDisable1FlagsList = CollectionsKt__CollectionsKt.listOf(new DisableFlagsLogger.DisableFlag(65536, 'E', 'e'), new DisableFlagsLogger.DisableFlag(131072, 'N', 'n'), new DisableFlagsLogger.DisableFlag(262144, 'A', 'a'), new DisableFlagsLogger.DisableFlag(1048576, 'I', 'i'), new DisableFlagsLogger.DisableFlag(2097152, 'H', 'h'), new DisableFlagsLogger.DisableFlag(4194304, 'B', 'b'), new DisableFlagsLogger.DisableFlag(8388608, 'C', 'c'), new DisableFlagsLogger.DisableFlag(16777216, 'R', 'r'), new DisableFlagsLogger.DisableFlag(33554432, 'S', 's'), new DisableFlagsLogger.DisableFlag(67108864, 'O', 'o'));
    public static final List defaultDisable2FlagsList = CollectionsKt__CollectionsKt.listOf(new DisableFlagsLogger.DisableFlag(1, 'Q', 'q'), new DisableFlagsLogger.DisableFlag(2, 'I', 'i'), new DisableFlagsLogger.DisableFlag(4, 'N', 'n'), new DisableFlagsLogger.DisableFlag(8, 'G', 'g'), new DisableFlagsLogger.DisableFlag(16, 'R', 'r'));
}
