package com.android.wm.shell.desktopmode;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeEventLogger$Companion$ExitReason {
    public static final /* synthetic */ DesktopModeEventLogger$Companion$ExitReason[] $VALUES;
    public static final DesktopModeEventLogger$Companion$ExitReason APP_HANDLE_MENU_BUTTON_EXIT;
    public static final DesktopModeEventLogger$Companion$ExitReason DRAG_TO_EXIT;
    public static final DesktopModeEventLogger$Companion$ExitReason KEYBOARD_SHORTCUT_EXIT;
    public static final DesktopModeEventLogger$Companion$ExitReason RETURN_HOME_OR_OVERVIEW;
    public static final DesktopModeEventLogger$Companion$ExitReason SCREEN_OFF;
    public static final DesktopModeEventLogger$Companion$ExitReason TASK_FINISHED;
    public static final DesktopModeEventLogger$Companion$ExitReason UNKNOWN_EXIT;
    private final int reason;

    static {
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason = new DesktopModeEventLogger$Companion$ExitReason("UNKNOWN_EXIT", 0, 0);
        UNKNOWN_EXIT = desktopModeEventLogger$Companion$ExitReason;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason2 = new DesktopModeEventLogger$Companion$ExitReason("DRAG_TO_EXIT", 1, 1);
        DRAG_TO_EXIT = desktopModeEventLogger$Companion$ExitReason2;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason3 = new DesktopModeEventLogger$Companion$ExitReason("APP_HANDLE_MENU_BUTTON_EXIT", 2, 2);
        APP_HANDLE_MENU_BUTTON_EXIT = desktopModeEventLogger$Companion$ExitReason3;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason4 = new DesktopModeEventLogger$Companion$ExitReason("KEYBOARD_SHORTCUT_EXIT", 3, 3);
        KEYBOARD_SHORTCUT_EXIT = desktopModeEventLogger$Companion$ExitReason4;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason5 = new DesktopModeEventLogger$Companion$ExitReason("RETURN_HOME_OR_OVERVIEW", 4, 4);
        RETURN_HOME_OR_OVERVIEW = desktopModeEventLogger$Companion$ExitReason5;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason6 = new DesktopModeEventLogger$Companion$ExitReason("TASK_FINISHED", 5, 5);
        TASK_FINISHED = desktopModeEventLogger$Companion$ExitReason6;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason7 = new DesktopModeEventLogger$Companion$ExitReason("SCREEN_OFF", 6, 6);
        SCREEN_OFF = desktopModeEventLogger$Companion$ExitReason7;
        DesktopModeEventLogger$Companion$ExitReason[] desktopModeEventLogger$Companion$ExitReasonArr = {desktopModeEventLogger$Companion$ExitReason, desktopModeEventLogger$Companion$ExitReason2, desktopModeEventLogger$Companion$ExitReason3, desktopModeEventLogger$Companion$ExitReason4, desktopModeEventLogger$Companion$ExitReason5, desktopModeEventLogger$Companion$ExitReason6, desktopModeEventLogger$Companion$ExitReason7};
        $VALUES = desktopModeEventLogger$Companion$ExitReasonArr;
        EnumEntriesKt.enumEntries(desktopModeEventLogger$Companion$ExitReasonArr);
    }

    public DesktopModeEventLogger$Companion$ExitReason(String str, int i, int i2) {
        this.reason = i2;
    }

    public static DesktopModeEventLogger$Companion$ExitReason valueOf(String str) {
        return (DesktopModeEventLogger$Companion$ExitReason) Enum.valueOf(DesktopModeEventLogger$Companion$ExitReason.class, str);
    }

    public static DesktopModeEventLogger$Companion$ExitReason[] values() {
        return (DesktopModeEventLogger$Companion$ExitReason[]) $VALUES.clone();
    }

    public final int getReason() {
        return this.reason;
    }
}
