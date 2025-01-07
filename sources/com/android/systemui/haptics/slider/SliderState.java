package com.android.systemui.haptics.slider;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderState {
    public static final /* synthetic */ SliderState[] $VALUES;
    public static final SliderState ARROW_HANDLE_MOVED_ONCE;
    public static final SliderState ARROW_HANDLE_MOVES_CONTINUOUSLY;
    public static final SliderState ARROW_HANDLE_REACHED_BOOKEND;
    public static final SliderState DRAG_HANDLE_ACQUIRED_BY_TOUCH;
    public static final SliderState DRAG_HANDLE_DRAGGING;
    public static final SliderState DRAG_HANDLE_REACHED_BOOKEND;
    public static final SliderState DRAG_HANDLE_RELEASED_FROM_TOUCH;
    public static final SliderState IDLE;
    public static final SliderState JUMP_BOOKEND_SELECTED;
    public static final SliderState JUMP_TRACK_LOCATION_SELECTED;
    public static final SliderState WAIT;

    static {
        SliderState sliderState = new SliderState("IDLE", 0);
        IDLE = sliderState;
        SliderState sliderState2 = new SliderState("WAIT", 1);
        WAIT = sliderState2;
        SliderState sliderState3 = new SliderState("DRAG_HANDLE_ACQUIRED_BY_TOUCH", 2);
        DRAG_HANDLE_ACQUIRED_BY_TOUCH = sliderState3;
        SliderState sliderState4 = new SliderState("DRAG_HANDLE_RELEASED_FROM_TOUCH", 3);
        DRAG_HANDLE_RELEASED_FROM_TOUCH = sliderState4;
        SliderState sliderState5 = new SliderState("DRAG_HANDLE_DRAGGING", 4);
        DRAG_HANDLE_DRAGGING = sliderState5;
        SliderState sliderState6 = new SliderState("DRAG_HANDLE_REACHED_BOOKEND", 5);
        DRAG_HANDLE_REACHED_BOOKEND = sliderState6;
        SliderState sliderState7 = new SliderState("JUMP_TRACK_LOCATION_SELECTED", 6);
        JUMP_TRACK_LOCATION_SELECTED = sliderState7;
        SliderState sliderState8 = new SliderState("JUMP_BOOKEND_SELECTED", 7);
        JUMP_BOOKEND_SELECTED = sliderState8;
        SliderState sliderState9 = new SliderState("ARROW_HANDLE_MOVED_ONCE", 8);
        ARROW_HANDLE_MOVED_ONCE = sliderState9;
        SliderState sliderState10 = new SliderState("ARROW_HANDLE_MOVES_CONTINUOUSLY", 9);
        ARROW_HANDLE_MOVES_CONTINUOUSLY = sliderState10;
        SliderState sliderState11 = new SliderState("ARROW_HANDLE_REACHED_BOOKEND", 10);
        ARROW_HANDLE_REACHED_BOOKEND = sliderState11;
        SliderState[] sliderStateArr = {sliderState, sliderState2, sliderState3, sliderState4, sliderState5, sliderState6, sliderState7, sliderState8, sliderState9, sliderState10, sliderState11};
        $VALUES = sliderStateArr;
        EnumEntriesKt.enumEntries(sliderStateArr);
    }

    public static SliderState valueOf(String str) {
        return (SliderState) Enum.valueOf(SliderState.class, str);
    }

    public static SliderState[] values() {
        return (SliderState[]) $VALUES.clone();
    }
}
