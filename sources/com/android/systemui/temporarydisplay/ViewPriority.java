package com.android.systemui.temporarydisplay;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewPriority {
    public static final /* synthetic */ ViewPriority[] $VALUES;
    public static final ViewPriority CRITICAL;
    public static final ViewPriority NORMAL;

    static {
        ViewPriority viewPriority = new ViewPriority("NORMAL", 0);
        NORMAL = viewPriority;
        ViewPriority viewPriority2 = new ViewPriority("CRITICAL", 1);
        CRITICAL = viewPriority2;
        ViewPriority[] viewPriorityArr = {viewPriority, viewPriority2};
        $VALUES = viewPriorityArr;
        EnumEntriesKt.enumEntries(viewPriorityArr);
    }

    public static ViewPriority valueOf(String str) {
        return (ViewPriority) Enum.valueOf(ViewPriority.class, str);
    }

    public static ViewPriority[] values() {
        return (ViewPriority[]) $VALUES.clone();
    }
}
