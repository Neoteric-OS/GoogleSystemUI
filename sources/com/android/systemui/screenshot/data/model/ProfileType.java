package com.android.systemui.screenshot.data.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProfileType {
    public static final /* synthetic */ ProfileType[] $VALUES;
    public static final ProfileType CLONE;
    public static final ProfileType COMMUNAL;
    public static final ProfileType NONE;
    public static final ProfileType PRIVATE;
    public static final ProfileType WORK;

    static {
        ProfileType profileType = new ProfileType("NONE", 0);
        NONE = profileType;
        ProfileType profileType2 = new ProfileType("PRIVATE", 1);
        PRIVATE = profileType2;
        ProfileType profileType3 = new ProfileType("WORK", 2);
        WORK = profileType3;
        ProfileType profileType4 = new ProfileType("CLONE", 3);
        CLONE = profileType4;
        ProfileType profileType5 = new ProfileType("COMMUNAL", 4);
        COMMUNAL = profileType5;
        ProfileType[] profileTypeArr = {profileType, profileType2, profileType3, profileType4, profileType5};
        $VALUES = profileTypeArr;
        EnumEntriesKt.enumEntries(profileTypeArr);
    }

    public static ProfileType valueOf(String str) {
        return (ProfileType) Enum.valueOf(ProfileType.class, str);
    }

    public static ProfileType[] values() {
        return (ProfileType[]) $VALUES.clone();
    }
}
