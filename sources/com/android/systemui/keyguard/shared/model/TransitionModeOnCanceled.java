package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionModeOnCanceled {
    public static final /* synthetic */ TransitionModeOnCanceled[] $VALUES;
    public static final TransitionModeOnCanceled LAST_VALUE;
    public static final TransitionModeOnCanceled RESET;
    public static final TransitionModeOnCanceled REVERSE;

    static {
        TransitionModeOnCanceled transitionModeOnCanceled = new TransitionModeOnCanceled("LAST_VALUE", 0);
        LAST_VALUE = transitionModeOnCanceled;
        TransitionModeOnCanceled transitionModeOnCanceled2 = new TransitionModeOnCanceled("RESET", 1);
        RESET = transitionModeOnCanceled2;
        TransitionModeOnCanceled transitionModeOnCanceled3 = new TransitionModeOnCanceled("REVERSE", 2);
        REVERSE = transitionModeOnCanceled3;
        TransitionModeOnCanceled[] transitionModeOnCanceledArr = {transitionModeOnCanceled, transitionModeOnCanceled2, transitionModeOnCanceled3};
        $VALUES = transitionModeOnCanceledArr;
        EnumEntriesKt.enumEntries(transitionModeOnCanceledArr);
    }

    public static TransitionModeOnCanceled valueOf(String str) {
        return (TransitionModeOnCanceled) Enum.valueOf(TransitionModeOnCanceled.class, str);
    }

    public static TransitionModeOnCanceled[] values() {
        return (TransitionModeOnCanceled[]) $VALUES.clone();
    }
}
