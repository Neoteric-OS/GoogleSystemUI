package com.android.systemui.util.kotlin;

import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SimpleFlowDumper implements Dumpable {
    public final ConcurrentHashMap stateFlowMap = new ConcurrentHashMap();
    public final ConcurrentHashMap sharedFlowMap = new ConcurrentHashMap();
    public final ConcurrentHashMap flowCollectionMap = new ConcurrentHashMap();

    @Override // com.android.systemui.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        Set<Map.Entry> entrySet = new TreeMap(this.stateFlowMap).entrySet();
        asIndenting.append("StateFlow (value)").append((CharSequence) ": ").println(entrySet.size());
        asIndenting.increaseIndent();
        try {
            for (Map.Entry entry : entrySet) {
                asIndenting.append((String) entry.getKey()).append('=').println(((StateFlow) entry.getValue()).getValue());
            }
            asIndenting.decreaseIndent();
            Set<Map.Entry> entrySet2 = new TreeMap(this.sharedFlowMap).entrySet();
            asIndenting.append("SharedFlow (replayCache)").append((CharSequence) ": ").println(entrySet2.size());
            asIndenting.increaseIndent();
            try {
                for (Map.Entry entry2 : entrySet2) {
                    asIndenting.append((String) entry2.getKey()).append('=').println(((SharedFlow) entry2.getValue()).getReplayCache());
                }
                asIndenting.decreaseIndent();
                final SimpleFlowDumper$dumpFlows$$inlined$compareBy$1 simpleFlowDumper$dumpFlows$$inlined$compareBy$1 = new SimpleFlowDumper$dumpFlows$$inlined$compareBy$1();
                Comparator comparator = new Comparator() { // from class: com.android.systemui.util.kotlin.SimpleFlowDumper$dumpFlows$$inlined$thenBy$1
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare = SimpleFlowDumper$dumpFlows$$inlined$compareBy$1.this.compare(obj, obj2);
                        return compare != 0 ? compare : ComparisonsKt___ComparisonsJvmKt.compareValues((Comparable) ((Pair) obj).getSecond(), (Comparable) ((Pair) obj2).getSecond());
                    }
                };
                ConcurrentHashMap concurrentHashMap = this.flowCollectionMap;
                TreeMap treeMap = new TreeMap(comparator);
                treeMap.putAll(concurrentHashMap);
                Set<Map.Entry> entrySet3 = treeMap.entrySet();
                asIndenting.append("Flow (latest)").append((CharSequence) ": ").println(entrySet3.size());
                asIndenting.increaseIndent();
                try {
                    for (Map.Entry entry3 : entrySet3) {
                        Pair pair = (Pair) entry3.getKey();
                        asIndenting.append((CharSequence) pair.getFirst()).append('=').println(entry3.getValue());
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    public final StateFlow dumpValue(StateFlow stateFlow, String str) {
        this.stateFlowMap.put(str, stateFlow);
        onMapKeysChanged(true);
        return stateFlow;
    }

    public final Flow dumpWhileCollecting(Flow flow, String str) {
        return new SafeFlow(new SimpleFlowDumper$dumpWhileCollecting$1(str, this, flow, null));
    }

    public abstract void onMapKeysChanged(boolean z);
}
