package com.android.systemui.notetask;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskEntryPoint {
    public static final /* synthetic */ NoteTaskEntryPoint[] $VALUES;
    public static final NoteTaskEntryPoint APP_CLIPS;
    public static final NoteTaskEntryPoint KEYBOARD_SHORTCUT;
    public static final NoteTaskEntryPoint QUICK_AFFORDANCE;
    public static final NoteTaskEntryPoint TAIL_BUTTON;
    public static final NoteTaskEntryPoint WIDGET_PICKER_SHORTCUT;
    public static final NoteTaskEntryPoint WIDGET_PICKER_SHORTCUT_IN_MULTI_WINDOW_MODE;

    static {
        NoteTaskEntryPoint noteTaskEntryPoint = new NoteTaskEntryPoint("WIDGET_PICKER_SHORTCUT", 0);
        WIDGET_PICKER_SHORTCUT = noteTaskEntryPoint;
        NoteTaskEntryPoint noteTaskEntryPoint2 = new NoteTaskEntryPoint("WIDGET_PICKER_SHORTCUT_IN_MULTI_WINDOW_MODE", 1);
        WIDGET_PICKER_SHORTCUT_IN_MULTI_WINDOW_MODE = noteTaskEntryPoint2;
        NoteTaskEntryPoint noteTaskEntryPoint3 = new NoteTaskEntryPoint("QUICK_AFFORDANCE", 2);
        QUICK_AFFORDANCE = noteTaskEntryPoint3;
        NoteTaskEntryPoint noteTaskEntryPoint4 = new NoteTaskEntryPoint("TAIL_BUTTON", 3);
        TAIL_BUTTON = noteTaskEntryPoint4;
        NoteTaskEntryPoint noteTaskEntryPoint5 = new NoteTaskEntryPoint("APP_CLIPS", 4);
        APP_CLIPS = noteTaskEntryPoint5;
        NoteTaskEntryPoint noteTaskEntryPoint6 = new NoteTaskEntryPoint("KEYBOARD_SHORTCUT", 5);
        KEYBOARD_SHORTCUT = noteTaskEntryPoint6;
        NoteTaskEntryPoint[] noteTaskEntryPointArr = {noteTaskEntryPoint, noteTaskEntryPoint2, noteTaskEntryPoint3, noteTaskEntryPoint4, noteTaskEntryPoint5, noteTaskEntryPoint6};
        $VALUES = noteTaskEntryPointArr;
        EnumEntriesKt.enumEntries(noteTaskEntryPointArr);
    }

    public static NoteTaskEntryPoint valueOf(String str) {
        return (NoteTaskEntryPoint) Enum.valueOf(NoteTaskEntryPoint.class, str);
    }

    public static NoteTaskEntryPoint[] values() {
        return (NoteTaskEntryPoint[]) $VALUES.clone();
    }
}
