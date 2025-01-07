package com.android.systemui.touchpad.tutorial.ui.gesture;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GestureState {
    public static final /* synthetic */ GestureState[] $VALUES;
    public static final GestureState FINISHED;
    public static final GestureState IN_PROGRESS;
    public static final GestureState NOT_STARTED;

    static {
        GestureState gestureState = new GestureState("NOT_STARTED", 0);
        NOT_STARTED = gestureState;
        GestureState gestureState2 = new GestureState("IN_PROGRESS", 1);
        IN_PROGRESS = gestureState2;
        GestureState gestureState3 = new GestureState("FINISHED", 2);
        FINISHED = gestureState3;
        GestureState[] gestureStateArr = {gestureState, gestureState2, gestureState3};
        $VALUES = gestureStateArr;
        EnumEntriesKt.enumEntries(gestureStateArr);
    }

    public static GestureState valueOf(String str) {
        return (GestureState) Enum.valueOf(GestureState.class, str);
    }

    public static GestureState[] values() {
        return (GestureState[]) $VALUES.clone();
    }
}
