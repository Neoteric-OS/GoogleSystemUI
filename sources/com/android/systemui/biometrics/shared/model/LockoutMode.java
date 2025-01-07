package com.android.systemui.biometrics.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockoutMode {
    public static final /* synthetic */ LockoutMode[] $VALUES;
    public static final LockoutMode NONE;
    public static final LockoutMode PERMANENT;
    public static final LockoutMode TIMED;

    static {
        LockoutMode lockoutMode = new LockoutMode("NONE", 0);
        NONE = lockoutMode;
        LockoutMode lockoutMode2 = new LockoutMode("TIMED", 1);
        TIMED = lockoutMode2;
        LockoutMode lockoutMode3 = new LockoutMode("PERMANENT", 2);
        PERMANENT = lockoutMode3;
        LockoutMode[] lockoutModeArr = {lockoutMode, lockoutMode2, lockoutMode3};
        $VALUES = lockoutModeArr;
        EnumEntriesKt.enumEntries(lockoutModeArr);
    }

    public static LockoutMode valueOf(String str) {
        return (LockoutMode) Enum.valueOf(LockoutMode.class, str);
    }

    public static LockoutMode[] values() {
        return (LockoutMode[]) $VALUES.clone();
    }
}
