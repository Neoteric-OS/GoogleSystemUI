package com.android.systemui.inputdevice.tutorial.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Screen {
    public static final /* synthetic */ Screen[] $VALUES;
    public static final Screen ACTION_KEY;
    public static final Screen BACK_GESTURE;
    public static final Screen HOME_GESTURE;
    private final RequiredHardware requiredHardware;

    static {
        RequiredHardware requiredHardware = RequiredHardware.TOUCHPAD;
        Screen screen = new Screen("BACK_GESTURE", 0, requiredHardware);
        BACK_GESTURE = screen;
        Screen screen2 = new Screen("HOME_GESTURE", 1, requiredHardware);
        HOME_GESTURE = screen2;
        Screen screen3 = new Screen("ACTION_KEY", 2, RequiredHardware.KEYBOARD);
        ACTION_KEY = screen3;
        Screen[] screenArr = {screen, screen2, screen3};
        $VALUES = screenArr;
        EnumEntriesKt.enumEntries(screenArr);
    }

    public Screen(String str, int i, RequiredHardware requiredHardware) {
        this.requiredHardware = requiredHardware;
    }

    public static Screen valueOf(String str) {
        return (Screen) Enum.valueOf(Screen.class, str);
    }

    public static Screen[] values() {
        return (Screen[]) $VALUES.clone();
    }

    public final RequiredHardware getRequiredHardware() {
        return this.requiredHardware;
    }
}
