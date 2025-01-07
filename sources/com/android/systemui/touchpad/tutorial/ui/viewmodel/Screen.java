package com.android.systemui.touchpad.tutorial.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Screen {
    public static final /* synthetic */ Screen[] $VALUES;
    public static final Screen BACK_GESTURE;
    public static final Screen HOME_GESTURE;
    public static final Screen RECENT_APPS_GESTURE;
    public static final Screen TUTORIAL_SELECTION;

    static {
        Screen screen = new Screen("TUTORIAL_SELECTION", 0);
        TUTORIAL_SELECTION = screen;
        Screen screen2 = new Screen("BACK_GESTURE", 1);
        BACK_GESTURE = screen2;
        Screen screen3 = new Screen("HOME_GESTURE", 2);
        HOME_GESTURE = screen3;
        Screen screen4 = new Screen("RECENT_APPS_GESTURE", 3);
        RECENT_APPS_GESTURE = screen4;
        Screen[] screenArr = {screen, screen2, screen3, screen4};
        $VALUES = screenArr;
        EnumEntriesKt.enumEntries(screenArr);
    }

    public static Screen valueOf(String str) {
        return (Screen) Enum.valueOf(Screen.class, str);
    }

    public static Screen[] values() {
        return (Screen[]) $VALUES.clone();
    }
}
