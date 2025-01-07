package com.android.systemui.communal.shared.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalBackgroundType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ CommunalBackgroundType[] $VALUES;
    public static final CommunalBackgroundType ANIMATED;
    private final int value;

    static {
        CommunalBackgroundType communalBackgroundType = new CommunalBackgroundType("STATIC", 0, 0);
        CommunalBackgroundType communalBackgroundType2 = new CommunalBackgroundType("STATIC_GRADIENT", 1, 1);
        CommunalBackgroundType communalBackgroundType3 = new CommunalBackgroundType("ANIMATED", 2, 2);
        ANIMATED = communalBackgroundType3;
        CommunalBackgroundType[] communalBackgroundTypeArr = {communalBackgroundType, communalBackgroundType2, communalBackgroundType3, new CommunalBackgroundType("NONE", 3, 3)};
        $VALUES = communalBackgroundTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(communalBackgroundTypeArr);
    }

    public CommunalBackgroundType(String str, int i, int i2) {
        this.value = i2;
    }

    public static CommunalBackgroundType valueOf(String str) {
        return (CommunalBackgroundType) Enum.valueOf(CommunalBackgroundType.class, str);
    }

    public static CommunalBackgroundType[] values() {
        return (CommunalBackgroundType[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }
}
