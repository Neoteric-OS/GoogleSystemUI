package com.android.systemui.media.taptotransfer.sender;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TimeoutLength {
    public static final /* synthetic */ TimeoutLength[] $VALUES;
    public static final TimeoutLength DEFAULT;
    public static final TimeoutLength LONG;

    static {
        TimeoutLength timeoutLength = new TimeoutLength("DEFAULT", 0);
        DEFAULT = timeoutLength;
        TimeoutLength timeoutLength2 = new TimeoutLength("LONG", 1);
        LONG = timeoutLength2;
        TimeoutLength[] timeoutLengthArr = {timeoutLength, timeoutLength2};
        $VALUES = timeoutLengthArr;
        EnumEntriesKt.enumEntries(timeoutLengthArr);
    }

    public static TimeoutLength valueOf(String str) {
        return (TimeoutLength) Enum.valueOf(TimeoutLength.class, str);
    }

    public static TimeoutLength[] values() {
        return (TimeoutLength[]) $VALUES.clone();
    }
}
