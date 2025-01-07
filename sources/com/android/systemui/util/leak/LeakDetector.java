package com.android.systemui.util.leak;

import android.os.Build;
import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LeakDetector implements Dumpable {
    public static final boolean ENABLED = Build.IS_DEBUGGABLE;
    public final TrackedCollections mTrackedCollections;

    public LeakDetector(TrackedCollections trackedCollections, TrackedGarbage trackedGarbage, TrackedObjects trackedObjects, DumpManager dumpManager) {
        this.mTrackedCollections = trackedCollections;
        String simpleName = getClass().getSimpleName();
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, simpleName, this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("SYSUI LEAK DETECTOR");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("disabled");
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println();
    }
}
