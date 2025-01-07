package com.android.systemui.screenshot.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnimationState {
    public static final /* synthetic */ AnimationState[] $VALUES;
    public static final AnimationState ENTRANCE_COMPLETE;
    public static final AnimationState ENTRANCE_REVEAL;
    public static final AnimationState ENTRANCE_STARTED;
    public static final AnimationState NOT_STARTED;

    static {
        AnimationState animationState = new AnimationState("NOT_STARTED", 0);
        NOT_STARTED = animationState;
        AnimationState animationState2 = new AnimationState("ENTRANCE_STARTED", 1);
        ENTRANCE_STARTED = animationState2;
        AnimationState animationState3 = new AnimationState("ENTRANCE_REVEAL", 2);
        ENTRANCE_REVEAL = animationState3;
        AnimationState animationState4 = new AnimationState("ENTRANCE_COMPLETE", 3);
        ENTRANCE_COMPLETE = animationState4;
        AnimationState[] animationStateArr = {animationState, animationState2, animationState3, animationState4};
        $VALUES = animationStateArr;
        EnumEntriesKt.enumEntries(animationStateArr);
    }

    public static AnimationState valueOf(String str) {
        return (AnimationState) Enum.valueOf(AnimationState.class, str);
    }

    public static AnimationState[] values() {
        return (AnimationState[]) $VALUES.clone();
    }
}
