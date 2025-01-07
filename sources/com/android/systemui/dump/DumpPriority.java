package com.android.systemui.dump;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DumpPriority {
    public static final /* synthetic */ DumpPriority[] $VALUES;
    public static final DumpPriority CRITICAL;
    public static final DumpPriority NORMAL;

    static {
        DumpPriority dumpPriority = new DumpPriority("CRITICAL", 0);
        CRITICAL = dumpPriority;
        DumpPriority dumpPriority2 = new DumpPriority("NORMAL", 1);
        NORMAL = dumpPriority2;
        DumpPriority[] dumpPriorityArr = {dumpPriority, dumpPriority2};
        $VALUES = dumpPriorityArr;
        EnumEntriesKt.enumEntries(dumpPriorityArr);
    }

    public static DumpPriority valueOf(String str) {
        return (DumpPriority) Enum.valueOf(DumpPriority.class, str);
    }

    public static DumpPriority[] values() {
        return (DumpPriority[]) $VALUES.clone();
    }
}
