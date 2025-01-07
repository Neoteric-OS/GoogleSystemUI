package com.android.systemui.dump;

import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpsysEntry;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DumpManager {
    public final Map dumpables = new TreeMap();
    public final Map buffers = new TreeMap();
    public final Map tableLogBuffers = new TreeMap();

    public static /* synthetic */ void registerDumpable$default(DumpManager dumpManager, String str, Dumpable dumpable) {
        dumpManager.registerDumpable(dumpable, DumpPriority.CRITICAL, str);
    }

    public final boolean canAssignToNameLocked(Object obj, String str) {
        Object obj2;
        DumpsysEntry.DumpableEntry dumpableEntry = (DumpsysEntry.DumpableEntry) this.dumpables.get(str);
        if (dumpableEntry == null || (obj2 = dumpableEntry.dumpable) == null) {
            DumpsysEntry.LogBufferEntry logBufferEntry = (DumpsysEntry.LogBufferEntry) this.buffers.get(str);
            if (logBufferEntry != null) {
                obj2 = logBufferEntry.buffer;
            } else {
                DumpsysEntry.TableLogBufferEntry tableLogBufferEntry = (DumpsysEntry.TableLogBufferEntry) this.tableLogBuffers.get(str);
                obj2 = tableLogBufferEntry != null ? tableLogBufferEntry.table : null;
            }
        }
        return obj2 == null || Intrinsics.areEqual(obj, obj2);
    }

    public final synchronized Collection getDumpables() {
        return CollectionsKt.toList(this.dumpables.values());
    }

    public final synchronized Collection getLogBuffers() {
        return CollectionsKt.toList(this.buffers.values());
    }

    public final synchronized Collection getTableLogBuffers() {
        return CollectionsKt.toList(this.tableLogBuffers.values());
    }

    public final void registerCriticalDumpable(String str, Dumpable dumpable) {
        registerDumpable(dumpable, DumpPriority.CRITICAL, str);
    }

    public final synchronized void registerDumpable(Dumpable dumpable, DumpPriority dumpPriority, String str) {
        if (!canAssignToNameLocked(dumpable, str)) {
            throw new IllegalArgumentException("'" + str + "' is already registered");
        }
        this.dumpables.put(str, new DumpsysEntry.DumpableEntry(dumpable, dumpPriority, str));
    }

    public final void registerNormalDumpable(Dumpable dumpable) {
        registerNormalDumpable(dumpable.getClass().getCanonicalName(), dumpable);
    }

    public final synchronized void unregisterDumpable(String str) {
        this.dumpables.remove(str);
    }

    public final void registerNormalDumpable(String str, Dumpable dumpable) {
        registerDumpable(dumpable, DumpPriority.NORMAL, str);
    }

    public final synchronized void registerDumpable(Dumpable dumpable) {
        registerDumpable$default(this, dumpable.getClass().getCanonicalName(), dumpable);
    }
}
