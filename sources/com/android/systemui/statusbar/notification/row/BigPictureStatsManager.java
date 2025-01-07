package com.android.systemui.statusbar.notification.row;

import android.util.IndentingPrintWriter;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BigPictureStatsManager implements Dumpable {
    public final List durations;
    public final LatencyTracker latencyTracker;
    public final Object lock;
    public final CoroutineDispatcher mainDispatcher;
    public final ConcurrentHashMap startTimes;

    public BigPictureStatsManager(LatencyTracker latencyTracker, CoroutineDispatcher coroutineDispatcher, DumpManager dumpManager) {
        dumpManager.registerNormalDumpable("BigPictureStatsManager", this);
        new ConcurrentHashMap();
        this.durations = new ArrayList();
        this.lock = new Object();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        synchronized (this.lock) {
            if (this.durations.isEmpty()) {
                printWriter.println("No entries");
                return;
            }
            Iterator it = this.durations.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            Comparable comparable = (Comparable) it.next();
            while (it.hasNext()) {
                Comparable comparable2 = (Comparable) it.next();
                if (comparable.compareTo(comparable2) < 0) {
                    comparable = comparable2;
                }
            }
            int intValue = ((Number) comparable).intValue();
            Iterator it2 = this.durations.iterator();
            double d = 0.0d;
            int i = 0;
            while (it2.hasNext()) {
                d += ((Number) it2.next()).intValue();
                i++;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
            }
            int roundToInt = MathKt.roundToInt(i == 0 ? Double.NaN : d / i);
            int intValue2 = ((Number) CollectionsKt.sorted((ArrayList) this.durations).get(MathKt.roundToInt(0.9d * r2.size()) - 1)).intValue();
            int intValue3 = ((Number) CollectionsKt.sorted((ArrayList) this.durations).get(MathKt.roundToInt(0.99d * r3.size()) - 1)).intValue();
            IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
            indentingPrintWriter.println("Lazy-loaded " + ((ArrayList) this.durations).size() + " images:");
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println("Avg: " + roundToInt + " ms");
            indentingPrintWriter.println("Max: " + intValue + " ms");
            indentingPrintWriter.println("P90: " + intValue2 + " ms");
            indentingPrintWriter.println("P99: " + intValue3 + " ms");
        }
    }
}
