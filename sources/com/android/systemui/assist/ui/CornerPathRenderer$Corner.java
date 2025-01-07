package com.android.systemui.assist.ui;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CornerPathRenderer$Corner {
    public static final /* synthetic */ CornerPathRenderer$Corner[] $VALUES;
    public static final CornerPathRenderer$Corner BOTTOM_LEFT;
    public static final CornerPathRenderer$Corner BOTTOM_RIGHT;
    public static final CornerPathRenderer$Corner TOP_LEFT;
    public static final CornerPathRenderer$Corner TOP_RIGHT;

    static {
        CornerPathRenderer$Corner cornerPathRenderer$Corner = new CornerPathRenderer$Corner("BOTTOM_LEFT", 0);
        BOTTOM_LEFT = cornerPathRenderer$Corner;
        CornerPathRenderer$Corner cornerPathRenderer$Corner2 = new CornerPathRenderer$Corner("BOTTOM_RIGHT", 1);
        BOTTOM_RIGHT = cornerPathRenderer$Corner2;
        CornerPathRenderer$Corner cornerPathRenderer$Corner3 = new CornerPathRenderer$Corner("TOP_RIGHT", 2);
        TOP_RIGHT = cornerPathRenderer$Corner3;
        CornerPathRenderer$Corner cornerPathRenderer$Corner4 = new CornerPathRenderer$Corner("TOP_LEFT", 3);
        TOP_LEFT = cornerPathRenderer$Corner4;
        $VALUES = new CornerPathRenderer$Corner[]{cornerPathRenderer$Corner, cornerPathRenderer$Corner2, cornerPathRenderer$Corner3, cornerPathRenderer$Corner4};
    }

    public static CornerPathRenderer$Corner valueOf(String str) {
        return (CornerPathRenderer$Corner) Enum.valueOf(CornerPathRenderer$Corner.class, str);
    }

    public static CornerPathRenderer$Corner[] values() {
        return (CornerPathRenderer$Corner[]) $VALUES.clone();
    }
}
