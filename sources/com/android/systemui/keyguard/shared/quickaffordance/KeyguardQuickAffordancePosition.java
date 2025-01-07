package com.android.systemui.keyguard.shared.quickaffordance;

import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordancePosition {
    public static final /* synthetic */ KeyguardQuickAffordancePosition[] $VALUES;
    public static final KeyguardQuickAffordancePosition BOTTOM_END;
    public static final KeyguardQuickAffordancePosition BOTTOM_START;
    public static final Companion Companion;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        KeyguardQuickAffordancePosition keyguardQuickAffordancePosition = new KeyguardQuickAffordancePosition("BOTTOM_START", 0);
        BOTTOM_START = keyguardQuickAffordancePosition;
        KeyguardQuickAffordancePosition keyguardQuickAffordancePosition2 = new KeyguardQuickAffordancePosition("BOTTOM_END", 1);
        BOTTOM_END = keyguardQuickAffordancePosition2;
        KeyguardQuickAffordancePosition[] keyguardQuickAffordancePositionArr = {keyguardQuickAffordancePosition, keyguardQuickAffordancePosition2};
        $VALUES = keyguardQuickAffordancePositionArr;
        EnumEntriesKt.enumEntries(keyguardQuickAffordancePositionArr);
        Companion = new Companion();
    }

    public static KeyguardQuickAffordancePosition valueOf(String str) {
        return (KeyguardQuickAffordancePosition) Enum.valueOf(KeyguardQuickAffordancePosition.class, str);
    }

    public static KeyguardQuickAffordancePosition[] values() {
        return (KeyguardQuickAffordancePosition[]) $VALUES.clone();
    }

    public final String toSlotId() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return "bottom_start";
        }
        if (ordinal == 1) {
            return "bottom_end";
        }
        throw new NoWhenBranchMatchedException();
    }
}
