package com.android.systemui.biometrics.ui.viewmodel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PromptSizeKt {
    public static final boolean isMedium(PromptSize promptSize) {
        return promptSize != null && promptSize == PromptSize.MEDIUM;
    }

    public static final boolean isNotSmall(PromptSize promptSize) {
        return (promptSize == null || promptSize == PromptSize.SMALL) ? false : true;
    }

    public static final boolean isSmall(PromptSize promptSize) {
        return promptSize != null && promptSize == PromptSize.SMALL;
    }
}
