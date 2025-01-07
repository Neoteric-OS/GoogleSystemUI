package com.android.systemui.battery.unified;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorProfile {
    public static final /* synthetic */ ColorProfile[] $VALUES;
    public static final ColorProfile None;

    static {
        ColorProfile colorProfile = new ColorProfile("None", 0);
        None = colorProfile;
        ColorProfile[] colorProfileArr = {colorProfile, new ColorProfile("Active", 1), new ColorProfile("Warning", 2), new ColorProfile("Error", 3)};
        $VALUES = colorProfileArr;
        EnumEntriesKt.enumEntries(colorProfileArr);
    }

    public static ColorProfile valueOf(String str) {
        return (ColorProfile) Enum.valueOf(ColorProfile.class, str);
    }

    public static ColorProfile[] values() {
        return (ColorProfile[]) $VALUES.clone();
    }
}
