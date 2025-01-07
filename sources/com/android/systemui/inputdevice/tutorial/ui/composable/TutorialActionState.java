package com.android.systemui.inputdevice.tutorial.ui.composable;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TutorialActionState {
    public static final /* synthetic */ TutorialActionState[] $VALUES;
    public static final TutorialActionState FINISHED;
    public static final TutorialActionState IN_PROGRESS;
    public static final TutorialActionState NOT_STARTED;

    static {
        TutorialActionState tutorialActionState = new TutorialActionState("NOT_STARTED", 0);
        NOT_STARTED = tutorialActionState;
        TutorialActionState tutorialActionState2 = new TutorialActionState("IN_PROGRESS", 1);
        IN_PROGRESS = tutorialActionState2;
        TutorialActionState tutorialActionState3 = new TutorialActionState("FINISHED", 2);
        FINISHED = tutorialActionState3;
        TutorialActionState[] tutorialActionStateArr = {tutorialActionState, tutorialActionState2, tutorialActionState3};
        $VALUES = tutorialActionStateArr;
        EnumEntriesKt.enumEntries(tutorialActionStateArr);
    }

    public static TutorialActionState valueOf(String str) {
        return (TutorialActionState) Enum.valueOf(TutorialActionState.class, str);
    }

    public static TutorialActionState[] values() {
        return (TutorialActionState[]) $VALUES.clone();
    }
}
