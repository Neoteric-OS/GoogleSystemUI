package com.android.systemui.statusbar.phone;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HoverTheme {
    public static final /* synthetic */ HoverTheme[] $VALUES;
    public static final HoverTheme DARK;
    public static final HoverTheme LIGHT;

    static {
        HoverTheme hoverTheme = new HoverTheme("LIGHT", 0);
        LIGHT = hoverTheme;
        HoverTheme hoverTheme2 = new HoverTheme("DARK", 1);
        DARK = hoverTheme2;
        HoverTheme[] hoverThemeArr = {hoverTheme, hoverTheme2};
        $VALUES = hoverThemeArr;
        EnumEntriesKt.enumEntries(hoverThemeArr);
    }

    public static HoverTheme valueOf(String str) {
        return (HoverTheme) Enum.valueOf(HoverTheme.class, str);
    }

    public static HoverTheme[] values() {
        return (HoverTheme[]) $VALUES.clone();
    }
}
