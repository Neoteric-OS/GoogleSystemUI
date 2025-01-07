package com.android.systemui.biometrics.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptPosition {
    public static final /* synthetic */ PromptPosition[] $VALUES;
    public static final PromptPosition Bottom;
    public static final PromptPosition Left;
    public static final PromptPosition Right;
    public static final PromptPosition Top;

    static {
        PromptPosition promptPosition = new PromptPosition("Top", 0);
        Top = promptPosition;
        PromptPosition promptPosition2 = new PromptPosition("Bottom", 1);
        Bottom = promptPosition2;
        PromptPosition promptPosition3 = new PromptPosition("Left", 2);
        Left = promptPosition3;
        PromptPosition promptPosition4 = new PromptPosition("Right", 3);
        Right = promptPosition4;
        PromptPosition[] promptPositionArr = {promptPosition, promptPosition2, promptPosition3, promptPosition4};
        $VALUES = promptPositionArr;
        EnumEntriesKt.enumEntries(promptPositionArr);
    }

    public static PromptPosition valueOf(String str) {
        return (PromptPosition) Enum.valueOf(PromptPosition.class, str);
    }

    public static PromptPosition[] values() {
        return (PromptPosition[]) $VALUES.clone();
    }
}
