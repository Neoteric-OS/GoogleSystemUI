package com.android.systemui.animation.back;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScalePivotPosition {
    public static final /* synthetic */ ScalePivotPosition[] $VALUES;
    public static final ScalePivotPosition BOTTOM_CENTER;

    static {
        ScalePivotPosition scalePivotPosition = new ScalePivotPosition("CENTER", 0);
        ScalePivotPosition scalePivotPosition2 = new ScalePivotPosition("BOTTOM_CENTER", 1);
        BOTTOM_CENTER = scalePivotPosition2;
        ScalePivotPosition[] scalePivotPositionArr = {scalePivotPosition, scalePivotPosition2};
        $VALUES = scalePivotPositionArr;
        EnumEntriesKt.enumEntries(scalePivotPositionArr);
    }

    public static ScalePivotPosition valueOf(String str) {
        return (ScalePivotPosition) Enum.valueOf(ScalePivotPosition.class, str);
    }

    public static ScalePivotPosition[] values() {
        return (ScalePivotPosition[]) $VALUES.clone();
    }
}
