package com.android.wm.shell.desktopmode;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeEventLogger$Companion$EnterReason {
    public static final /* synthetic */ DesktopModeEventLogger$Companion$EnterReason[] $VALUES;
    public static final DesktopModeEventLogger$Companion$EnterReason APP_FREEFORM_INTENT;
    public static final DesktopModeEventLogger$Companion$EnterReason APP_FROM_OVERVIEW;
    public static final DesktopModeEventLogger$Companion$EnterReason APP_HANDLE_DRAG;
    public static final DesktopModeEventLogger$Companion$EnterReason APP_HANDLE_MENU_BUTTON;
    public static final DesktopModeEventLogger$Companion$EnterReason KEYBOARD_SHORTCUT_ENTER;
    public static final DesktopModeEventLogger$Companion$EnterReason OVERVIEW;
    public static final DesktopModeEventLogger$Companion$EnterReason SCREEN_ON;
    public static final DesktopModeEventLogger$Companion$EnterReason UNKNOWN_ENTER;
    private final int reason;

    static {
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason = new DesktopModeEventLogger$Companion$EnterReason("UNKNOWN_ENTER", 0, 0);
        UNKNOWN_ENTER = desktopModeEventLogger$Companion$EnterReason;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason2 = new DesktopModeEventLogger$Companion$EnterReason("OVERVIEW", 1, 1);
        OVERVIEW = desktopModeEventLogger$Companion$EnterReason2;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason3 = new DesktopModeEventLogger$Companion$EnterReason("APP_HANDLE_DRAG", 2, 2);
        APP_HANDLE_DRAG = desktopModeEventLogger$Companion$EnterReason3;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason4 = new DesktopModeEventLogger$Companion$EnterReason("APP_HANDLE_MENU_BUTTON", 3, 3);
        APP_HANDLE_MENU_BUTTON = desktopModeEventLogger$Companion$EnterReason4;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason5 = new DesktopModeEventLogger$Companion$EnterReason("APP_FREEFORM_INTENT", 4, 4);
        APP_FREEFORM_INTENT = desktopModeEventLogger$Companion$EnterReason5;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason6 = new DesktopModeEventLogger$Companion$EnterReason("KEYBOARD_SHORTCUT_ENTER", 5, 5);
        KEYBOARD_SHORTCUT_ENTER = desktopModeEventLogger$Companion$EnterReason6;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason7 = new DesktopModeEventLogger$Companion$EnterReason("SCREEN_ON", 6, 6);
        SCREEN_ON = desktopModeEventLogger$Companion$EnterReason7;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason8 = new DesktopModeEventLogger$Companion$EnterReason("APP_FROM_OVERVIEW", 7, 7);
        APP_FROM_OVERVIEW = desktopModeEventLogger$Companion$EnterReason8;
        DesktopModeEventLogger$Companion$EnterReason[] desktopModeEventLogger$Companion$EnterReasonArr = {desktopModeEventLogger$Companion$EnterReason, desktopModeEventLogger$Companion$EnterReason2, desktopModeEventLogger$Companion$EnterReason3, desktopModeEventLogger$Companion$EnterReason4, desktopModeEventLogger$Companion$EnterReason5, desktopModeEventLogger$Companion$EnterReason6, desktopModeEventLogger$Companion$EnterReason7, desktopModeEventLogger$Companion$EnterReason8};
        $VALUES = desktopModeEventLogger$Companion$EnterReasonArr;
        EnumEntriesKt.enumEntries(desktopModeEventLogger$Companion$EnterReasonArr);
    }

    public DesktopModeEventLogger$Companion$EnterReason(String str, int i, int i2) {
        this.reason = i2;
    }

    public static DesktopModeEventLogger$Companion$EnterReason valueOf(String str) {
        return (DesktopModeEventLogger$Companion$EnterReason) Enum.valueOf(DesktopModeEventLogger$Companion$EnterReason.class, str);
    }

    public static DesktopModeEventLogger$Companion$EnterReason[] values() {
        return (DesktopModeEventLogger$Companion$EnterReason[]) $VALUES.clone();
    }

    public final int getReason() {
        return this.reason;
    }
}
