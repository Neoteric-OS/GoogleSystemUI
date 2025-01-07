package com.android.systemui.statusbar.notification.row;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RichOngoingNotificationViewType {
    public static final /* synthetic */ RichOngoingNotificationViewType[] $VALUES;
    public static final RichOngoingNotificationViewType Contracted = null;
    public static final RichOngoingNotificationViewType Expanded = null;
    public static final RichOngoingNotificationViewType HeadsUp = null;

    static {
        RichOngoingNotificationViewType[] richOngoingNotificationViewTypeArr = {new RichOngoingNotificationViewType("Contracted", 0), new RichOngoingNotificationViewType("Expanded", 1), new RichOngoingNotificationViewType("HeadsUp", 2)};
        $VALUES = richOngoingNotificationViewTypeArr;
        EnumEntriesKt.enumEntries(richOngoingNotificationViewTypeArr);
    }

    public static RichOngoingNotificationViewType valueOf(String str) {
        return (RichOngoingNotificationViewType) Enum.valueOf(RichOngoingNotificationViewType.class, str);
    }

    public static RichOngoingNotificationViewType[] values() {
        return (RichOngoingNotificationViewType[]) $VALUES.clone();
    }
}
