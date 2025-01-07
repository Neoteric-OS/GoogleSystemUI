package com.android.systemui.inputdevice.tutorial.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RequiredHardware {
    public static final /* synthetic */ RequiredHardware[] $VALUES;
    public static final RequiredHardware KEYBOARD;
    public static final RequiredHardware TOUCHPAD;

    static {
        RequiredHardware requiredHardware = new RequiredHardware("TOUCHPAD", 0);
        TOUCHPAD = requiredHardware;
        RequiredHardware requiredHardware2 = new RequiredHardware("KEYBOARD", 1);
        KEYBOARD = requiredHardware2;
        RequiredHardware[] requiredHardwareArr = {requiredHardware, requiredHardware2};
        $VALUES = requiredHardwareArr;
        EnumEntriesKt.enumEntries(requiredHardwareArr);
    }

    public static RequiredHardware valueOf(String str) {
        return (RequiredHardware) Enum.valueOf(RequiredHardware.class, str);
    }

    public static RequiredHardware[] values() {
        return (RequiredHardware[]) $VALUES.clone();
    }
}
