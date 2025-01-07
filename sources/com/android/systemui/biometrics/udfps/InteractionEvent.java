package com.android.systemui.biometrics.udfps;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InteractionEvent {
    public static final /* synthetic */ InteractionEvent[] $VALUES;
    public static final InteractionEvent CANCEL;
    public static final InteractionEvent DOWN;
    public static final InteractionEvent UNCHANGED;
    public static final InteractionEvent UP;

    static {
        InteractionEvent interactionEvent = new InteractionEvent("DOWN", 0);
        DOWN = interactionEvent;
        InteractionEvent interactionEvent2 = new InteractionEvent("UP", 1);
        UP = interactionEvent2;
        InteractionEvent interactionEvent3 = new InteractionEvent("CANCEL", 2);
        CANCEL = interactionEvent3;
        InteractionEvent interactionEvent4 = new InteractionEvent("UNCHANGED", 3);
        UNCHANGED = interactionEvent4;
        InteractionEvent[] interactionEventArr = {interactionEvent, interactionEvent2, interactionEvent3, interactionEvent4};
        $VALUES = interactionEventArr;
        EnumEntriesKt.enumEntries(interactionEventArr);
    }

    public static InteractionEvent valueOf(String str) {
        return (InteractionEvent) Enum.valueOf(InteractionEvent.class, str);
    }

    public static InteractionEvent[] values() {
        return (InteractionEvent[]) $VALUES.clone();
    }
}
