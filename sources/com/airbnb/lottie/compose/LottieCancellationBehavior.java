package com.airbnb.lottie.compose;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieCancellationBehavior {
    public static final /* synthetic */ LottieCancellationBehavior[] $VALUES;
    public static final LottieCancellationBehavior Immediately;

    static {
        LottieCancellationBehavior lottieCancellationBehavior = new LottieCancellationBehavior("Immediately", 0);
        Immediately = lottieCancellationBehavior;
        LottieCancellationBehavior[] lottieCancellationBehaviorArr = {lottieCancellationBehavior, new LottieCancellationBehavior("OnIterationFinish", 1)};
        $VALUES = lottieCancellationBehaviorArr;
        EnumEntriesKt.enumEntries(lottieCancellationBehaviorArr);
    }

    public static LottieCancellationBehavior valueOf(String str) {
        return (LottieCancellationBehavior) Enum.valueOf(LottieCancellationBehavior.class, str);
    }

    public static LottieCancellationBehavior[] values() {
        return (LottieCancellationBehavior[]) $VALUES.clone();
    }
}
