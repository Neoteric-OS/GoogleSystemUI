package com.android.systemui.broadcast;

import android.util.IndentingPrintWriter;
import android.util.SparseSetArray;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.logging.BroadcastDispatcherLogger;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PendingRemovalStore implements Dumpable {
    public final BroadcastDispatcherLogger logger;
    public final SparseSetArray pendingRemoval = new SparseSetArray();

    public PendingRemovalStore(BroadcastDispatcherLogger broadcastDispatcherLogger) {
        this.logger = broadcastDispatcherLogger;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        synchronized (this.pendingRemoval) {
            try {
                if (printWriter instanceof IndentingPrintWriter) {
                    ((IndentingPrintWriter) printWriter).increaseIndent();
                }
                int size = this.pendingRemoval.size();
                for (int i = 0; i < size; i++) {
                    int keyAt = this.pendingRemoval.keyAt(i);
                    printWriter.print(keyAt);
                    printWriter.print("->");
                    printWriter.println(this.pendingRemoval.get(keyAt));
                }
                if (printWriter instanceof IndentingPrintWriter) {
                    ((IndentingPrintWriter) printWriter).decreaseIndent();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
