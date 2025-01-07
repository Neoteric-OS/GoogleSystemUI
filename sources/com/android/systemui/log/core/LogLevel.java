package com.android.systemui.log.core;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogLevel {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LogLevel[] $VALUES;
    private final int nativeLevel;
    private final String shortString;
    public static final LogLevel VERBOSE = new LogLevel("VERBOSE", 0, 2, "V");
    public static final LogLevel DEBUG = new LogLevel("DEBUG", 1, 3, "D");
    public static final LogLevel INFO = new LogLevel("INFO", 2, 4, "I");
    public static final LogLevel WARNING = new LogLevel("WARNING", 3, 5, "W");
    public static final LogLevel ERROR = new LogLevel("ERROR", 4, 6, "E");
    public static final LogLevel WTF = new LogLevel("WTF", 5, 7, "WTF");

    private static final /* synthetic */ LogLevel[] $values() {
        return new LogLevel[]{VERBOSE, DEBUG, INFO, WARNING, ERROR, WTF};
    }

    static {
        LogLevel[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private LogLevel(String str, int i, int i2, String str2) {
        this.nativeLevel = i2;
        this.shortString = str2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static LogLevel valueOf(String str) {
        return (LogLevel) Enum.valueOf(LogLevel.class, str);
    }

    public static LogLevel[] values() {
        return (LogLevel[]) $VALUES.clone();
    }

    public final int getNativeLevel() {
        return this.nativeLevel;
    }

    public final String getShortString() {
        return this.shortString;
    }
}
