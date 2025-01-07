package com.android.systemui.biometrics.domain.interactor;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UserType {
    public static final /* synthetic */ UserType[] $VALUES;
    public static final UserType MANAGED_PROFILE;
    public static final UserType PRIMARY;
    public static final UserType SECONDARY;

    static {
        UserType userType = new UserType("PRIMARY", 0);
        PRIMARY = userType;
        UserType userType2 = new UserType("MANAGED_PROFILE", 1);
        MANAGED_PROFILE = userType2;
        UserType userType3 = new UserType("SECONDARY", 2);
        SECONDARY = userType3;
        UserType[] userTypeArr = {userType, userType2, userType3};
        $VALUES = userTypeArr;
        EnumEntriesKt.enumEntries(userTypeArr);
    }

    public static UserType valueOf(String str) {
        return (UserType) Enum.valueOf(UserType.class, str);
    }

    public static UserType[] values() {
        return (UserType[]) $VALUES.clone();
    }
}
