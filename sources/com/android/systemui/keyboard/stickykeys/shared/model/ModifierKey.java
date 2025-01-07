package com.android.systemui.keyboard.stickykeys.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ModifierKey {
    public static final /* synthetic */ ModifierKey[] $VALUES;
    public static final ModifierKey ALT;
    public static final ModifierKey ALT_GR;
    public static final ModifierKey CTRL;
    public static final ModifierKey META;
    public static final ModifierKey SHIFT;
    private final String displayedText;

    static {
        ModifierKey modifierKey = new ModifierKey("ALT", "ALT", 0);
        ALT = modifierKey;
        ModifierKey modifierKey2 = new ModifierKey("ALT_GR", "ALT", 1);
        ALT_GR = modifierKey2;
        ModifierKey modifierKey3 = new ModifierKey("CTRL", "CTRL", 2);
        CTRL = modifierKey3;
        ModifierKey modifierKey4 = new ModifierKey("META", "ACTION", 3);
        META = modifierKey4;
        ModifierKey modifierKey5 = new ModifierKey("SHIFT", "SHIFT", 4);
        SHIFT = modifierKey5;
        ModifierKey[] modifierKeyArr = {modifierKey, modifierKey2, modifierKey3, modifierKey4, modifierKey5};
        $VALUES = modifierKeyArr;
        EnumEntriesKt.enumEntries(modifierKeyArr);
    }

    public ModifierKey(String str, String str2, int i) {
        this.displayedText = str2;
    }

    public static ModifierKey valueOf(String str) {
        return (ModifierKey) Enum.valueOf(ModifierKey.class, str);
    }

    public static ModifierKey[] values() {
        return (ModifierKey[]) $VALUES.clone();
    }

    public final String getDisplayedText() {
        return this.displayedText;
    }
}
