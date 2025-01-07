package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockSize {
    public static final /* synthetic */ ClockSize[] $VALUES;
    public static final Companion Companion;
    public static final ClockSize LARGE;
    public static final ClockSize SMALL;
    public static final String TAG;
    private final int legacyValue;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        ClockSize clockSize = new ClockSize("SMALL", 0, 1);
        SMALL = clockSize;
        ClockSize clockSize2 = new ClockSize("LARGE", 1, 0);
        LARGE = clockSize2;
        ClockSize[] clockSizeArr = {clockSize, clockSize2};
        $VALUES = clockSizeArr;
        EnumEntriesKt.enumEntries(clockSizeArr);
        Companion = new Companion();
        String simpleName = Reflection.getOrCreateKotlinClass(ClockSize.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    public ClockSize(String str, int i, int i2) {
        this.legacyValue = i2;
    }

    public static ClockSize valueOf(String str) {
        return (ClockSize) Enum.valueOf(ClockSize.class, str);
    }

    public static ClockSize[] values() {
        return (ClockSize[]) $VALUES.clone();
    }

    public final int getLegacyValue() {
        return this.legacyValue;
    }
}
