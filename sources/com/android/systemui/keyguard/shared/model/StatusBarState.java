package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatusBarState {
    public static final /* synthetic */ StatusBarState[] $VALUES;
    public static final StatusBarState KEYGUARD;
    public static final StatusBarState SHADE;
    public static final StatusBarState SHADE_LOCKED;

    static {
        StatusBarState statusBarState = new StatusBarState("SHADE", 0);
        SHADE = statusBarState;
        StatusBarState statusBarState2 = new StatusBarState("KEYGUARD", 1);
        KEYGUARD = statusBarState2;
        StatusBarState statusBarState3 = new StatusBarState("SHADE_LOCKED", 2);
        SHADE_LOCKED = statusBarState3;
        StatusBarState[] statusBarStateArr = {statusBarState, statusBarState2, statusBarState3};
        $VALUES = statusBarStateArr;
        EnumEntriesKt.enumEntries(statusBarStateArr);
    }

    public static StatusBarState valueOf(String str) {
        return (StatusBarState) Enum.valueOf(StatusBarState.class, str);
    }

    public static StatusBarState[] values() {
        return (StatusBarState[]) $VALUES.clone();
    }
}
