package com.google.android.systemui.elmyra;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ElmyraEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ ElmyraEvent[] $VALUES;
    public static final ElmyraEvent ELMYRA_PRIMED;
    public static final ElmyraEvent ELMYRA_RELEASED;
    public static final ElmyraEvent ELMYRA_TRIGGERED_AP_SUSPENDED;
    public static final ElmyraEvent ELMYRA_TRIGGERED_SCREEN_OFF;
    public static final ElmyraEvent ELMYRA_TRIGGERED_SCREEN_ON;
    private final int id;

    static {
        ElmyraEvent elmyraEvent = new ElmyraEvent("ELMYRA_PRIMED", 0, 559);
        ELMYRA_PRIMED = elmyraEvent;
        ElmyraEvent elmyraEvent2 = new ElmyraEvent("ELMYRA_RELEASED", 1, 560);
        ELMYRA_RELEASED = elmyraEvent2;
        ElmyraEvent elmyraEvent3 = new ElmyraEvent("ELMYRA_TRIGGERED_AP_SUSPENDED", 2, 561);
        ELMYRA_TRIGGERED_AP_SUSPENDED = elmyraEvent3;
        ElmyraEvent elmyraEvent4 = new ElmyraEvent("ELMYRA_TRIGGERED_SCREEN_OFF", 3, 575);
        ELMYRA_TRIGGERED_SCREEN_OFF = elmyraEvent4;
        ElmyraEvent elmyraEvent5 = new ElmyraEvent("ELMYRA_TRIGGERED_SCREEN_ON", 4, 576);
        ELMYRA_TRIGGERED_SCREEN_ON = elmyraEvent5;
        ElmyraEvent[] elmyraEventArr = {elmyraEvent, elmyraEvent2, elmyraEvent3, elmyraEvent4, elmyraEvent5};
        $VALUES = elmyraEventArr;
        EnumEntriesKt.enumEntries(elmyraEventArr);
    }

    public ElmyraEvent(String str, int i, int i2) {
        this.id = i2;
    }

    public static ElmyraEvent valueOf(String str) {
        return (ElmyraEvent) Enum.valueOf(ElmyraEvent.class, str);
    }

    public static ElmyraEvent[] values() {
        return (ElmyraEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.id;
    }
}
