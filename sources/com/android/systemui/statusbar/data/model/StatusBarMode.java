package com.android.systemui.statusbar.data.model;

import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarMode {
    public static final /* synthetic */ StatusBarMode[] $VALUES;
    public static final StatusBarMode LIGHTS_OUT;
    public static final StatusBarMode LIGHTS_OUT_TRANSPARENT;
    public static final StatusBarMode OPAQUE;
    public static final StatusBarMode SEMI_TRANSPARENT;
    public static final StatusBarMode TRANSPARENT;

    static {
        StatusBarMode statusBarMode = new StatusBarMode("SEMI_TRANSPARENT", 0);
        SEMI_TRANSPARENT = statusBarMode;
        StatusBarMode statusBarMode2 = new StatusBarMode("LIGHTS_OUT", 1);
        LIGHTS_OUT = statusBarMode2;
        StatusBarMode statusBarMode3 = new StatusBarMode("LIGHTS_OUT_TRANSPARENT", 2);
        LIGHTS_OUT_TRANSPARENT = statusBarMode3;
        StatusBarMode statusBarMode4 = new StatusBarMode("OPAQUE", 3);
        OPAQUE = statusBarMode4;
        StatusBarMode statusBarMode5 = new StatusBarMode("TRANSPARENT", 4);
        TRANSPARENT = statusBarMode5;
        StatusBarMode[] statusBarModeArr = {statusBarMode, statusBarMode2, statusBarMode3, statusBarMode4, statusBarMode5};
        $VALUES = statusBarModeArr;
        EnumEntriesKt.enumEntries(statusBarModeArr);
    }

    public static StatusBarMode valueOf(String str) {
        return (StatusBarMode) Enum.valueOf(StatusBarMode.class, str);
    }

    public static StatusBarMode[] values() {
        return (StatusBarMode[]) $VALUES.clone();
    }

    public final int toTransitionModeInt() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return 1;
        }
        if (ordinal == 1) {
            return 3;
        }
        if (ordinal == 2) {
            return 6;
        }
        if (ordinal == 3) {
            return 4;
        }
        if (ordinal == 4) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }
}
