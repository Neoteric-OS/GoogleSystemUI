package com.android.systemui.statusbar.notification.logging;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewType {
    public static final /* synthetic */ ViewType[] $VALUES;
    public static final ViewType PRIVATE_CONTRACTED_VIEW;
    public static final ViewType PRIVATE_EXPANDED_VIEW;
    public static final ViewType PRIVATE_HEADS_UP_VIEW;
    public static final ViewType PUBLIC_VIEW;
    public static final ViewType TOTAL;

    static {
        ViewType viewType = new ViewType("PUBLIC_VIEW", 0);
        PUBLIC_VIEW = viewType;
        ViewType viewType2 = new ViewType("PRIVATE_CONTRACTED_VIEW", 1);
        PRIVATE_CONTRACTED_VIEW = viewType2;
        ViewType viewType3 = new ViewType("PRIVATE_EXPANDED_VIEW", 2);
        PRIVATE_EXPANDED_VIEW = viewType3;
        ViewType viewType4 = new ViewType("PRIVATE_HEADS_UP_VIEW", 3);
        PRIVATE_HEADS_UP_VIEW = viewType4;
        ViewType viewType5 = new ViewType("TOTAL", 4);
        TOTAL = viewType5;
        ViewType[] viewTypeArr = {viewType, viewType2, viewType3, viewType4, viewType5};
        $VALUES = viewTypeArr;
        EnumEntriesKt.enumEntries(viewTypeArr);
    }

    public static ViewType valueOf(String str) {
        return (ViewType) Enum.valueOf(ViewType.class, str);
    }

    public static ViewType[] values() {
        return (ViewType[]) $VALUES.clone();
    }
}
