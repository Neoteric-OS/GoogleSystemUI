package com.android.systemui.contextualeducation;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GestureType {
    public static final /* synthetic */ GestureType[] $VALUES;
    public static final GestureType ALL_APPS;
    public static final GestureType BACK;
    public static final GestureType HOME;
    public static final GestureType OVERVIEW;

    static {
        GestureType gestureType = new GestureType("BACK", 0);
        BACK = gestureType;
        GestureType gestureType2 = new GestureType("HOME", 1);
        HOME = gestureType2;
        GestureType gestureType3 = new GestureType("OVERVIEW", 2);
        OVERVIEW = gestureType3;
        GestureType gestureType4 = new GestureType("ALL_APPS", 3);
        ALL_APPS = gestureType4;
        GestureType[] gestureTypeArr = {gestureType, gestureType2, gestureType3, gestureType4};
        $VALUES = gestureTypeArr;
        EnumEntriesKt.enumEntries(gestureTypeArr);
    }

    public static GestureType valueOf(String str) {
        return (GestureType) Enum.valueOf(GestureType.class, str);
    }

    public static GestureType[] values() {
        return (GestureType[]) $VALUES.clone();
    }
}
