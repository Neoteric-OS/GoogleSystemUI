package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDone {
    public static final /* synthetic */ KeyguardDone[] $VALUES;
    public static final KeyguardDone IMMEDIATE;

    static {
        KeyguardDone keyguardDone = new KeyguardDone("IMMEDIATE", 0);
        IMMEDIATE = keyguardDone;
        KeyguardDone[] keyguardDoneArr = {keyguardDone, new KeyguardDone("LATER", 1)};
        $VALUES = keyguardDoneArr;
        EnumEntriesKt.enumEntries(keyguardDoneArr);
    }

    public static KeyguardDone valueOf(String str) {
        return (KeyguardDone) Enum.valueOf(KeyguardDone.class, str);
    }

    public static KeyguardDone[] values() {
        return (KeyguardDone[]) $VALUES.clone();
    }
}
