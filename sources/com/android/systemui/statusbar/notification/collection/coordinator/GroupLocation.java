package com.android.systemui.statusbar.notification.collection.coordinator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class GroupLocation {
    public static final /* synthetic */ GroupLocation[] $VALUES;
    public static final GroupLocation Child;
    public static final GroupLocation Detached;
    public static final GroupLocation Isolated;
    public static final GroupLocation Summary;

    static {
        GroupLocation groupLocation = new GroupLocation("Detached", 0);
        Detached = groupLocation;
        GroupLocation groupLocation2 = new GroupLocation("Isolated", 1);
        Isolated = groupLocation2;
        GroupLocation groupLocation3 = new GroupLocation("Summary", 2);
        Summary = groupLocation3;
        GroupLocation groupLocation4 = new GroupLocation("Child", 3);
        Child = groupLocation4;
        GroupLocation[] groupLocationArr = {groupLocation, groupLocation2, groupLocation3, groupLocation4};
        $VALUES = groupLocationArr;
        EnumEntriesKt.enumEntries(groupLocationArr);
    }

    public static GroupLocation valueOf(String str) {
        return (GroupLocation) Enum.valueOf(GroupLocation.class, str);
    }

    public static GroupLocation[] values() {
        return (GroupLocation[]) $VALUES.clone();
    }
}
