package com.android.systemui.biometrics.ui.viewmodel;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptSize {
    public static final /* synthetic */ PromptSize[] $VALUES;
    public static final PromptSize LARGE;
    public static final PromptSize MEDIUM;
    public static final PromptSize SMALL;

    static {
        PromptSize promptSize = new PromptSize("SMALL", 0);
        SMALL = promptSize;
        PromptSize promptSize2 = new PromptSize("MEDIUM", 1);
        MEDIUM = promptSize2;
        PromptSize promptSize3 = new PromptSize("LARGE", 2);
        LARGE = promptSize3;
        PromptSize[] promptSizeArr = {promptSize, promptSize2, promptSize3};
        $VALUES = promptSizeArr;
        EnumEntriesKt.enumEntries(promptSizeArr);
    }

    public static PromptSize valueOf(String str) {
        return (PromptSize) Enum.valueOf(PromptSize.class, str);
    }

    public static PromptSize[] values() {
        return (PromptSize[]) $VALUES.clone();
    }
}
