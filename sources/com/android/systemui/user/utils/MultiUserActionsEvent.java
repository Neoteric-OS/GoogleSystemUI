package com.android.systemui.user.utils;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultiUserActionsEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ MultiUserActionsEvent[] $VALUES;
    public static final MultiUserActionsEvent CREATE_GUEST_FROM_USER_SWITCHER;
    public static final MultiUserActionsEvent CREATE_RESTRICTED_USER_FROM_USER_SWITCHER;
    public static final MultiUserActionsEvent CREATE_USER_FROM_USER_SWITCHER;
    public static final MultiUserActionsEvent SWITCH_TO_GUEST_FROM_USER_SWITCHER;
    public static final MultiUserActionsEvent SWITCH_TO_RESTRICTED_USER_FROM_USER_SWITCHER;
    public static final MultiUserActionsEvent SWITCH_TO_USER_FROM_USER_SWITCHER;
    private final int value;

    static {
        MultiUserActionsEvent multiUserActionsEvent = new MultiUserActionsEvent("CREATE_USER_FROM_USER_SWITCHER", 0, 1257);
        CREATE_USER_FROM_USER_SWITCHER = multiUserActionsEvent;
        MultiUserActionsEvent multiUserActionsEvent2 = new MultiUserActionsEvent("CREATE_GUEST_FROM_USER_SWITCHER", 1, 1258);
        CREATE_GUEST_FROM_USER_SWITCHER = multiUserActionsEvent2;
        MultiUserActionsEvent multiUserActionsEvent3 = new MultiUserActionsEvent("CREATE_RESTRICTED_USER_FROM_USER_SWITCHER", 2, 1259);
        CREATE_RESTRICTED_USER_FROM_USER_SWITCHER = multiUserActionsEvent3;
        MultiUserActionsEvent multiUserActionsEvent4 = new MultiUserActionsEvent("SWITCH_TO_USER_FROM_USER_SWITCHER", 3, 1266);
        SWITCH_TO_USER_FROM_USER_SWITCHER = multiUserActionsEvent4;
        MultiUserActionsEvent multiUserActionsEvent5 = new MultiUserActionsEvent("SWITCH_TO_GUEST_FROM_USER_SWITCHER", 4, 1267);
        SWITCH_TO_GUEST_FROM_USER_SWITCHER = multiUserActionsEvent5;
        MultiUserActionsEvent multiUserActionsEvent6 = new MultiUserActionsEvent("SWITCH_TO_RESTRICTED_USER_FROM_USER_SWITCHER", 5, 1268);
        SWITCH_TO_RESTRICTED_USER_FROM_USER_SWITCHER = multiUserActionsEvent6;
        MultiUserActionsEvent[] multiUserActionsEventArr = {multiUserActionsEvent, multiUserActionsEvent2, multiUserActionsEvent3, multiUserActionsEvent4, multiUserActionsEvent5, multiUserActionsEvent6, new MultiUserActionsEvent("GRANT_ADMIN_FROM_USER_SWITCHER_CREATION_DIALOG", 6, 1278), new MultiUserActionsEvent("NOT_GRANT_ADMIN_FROM_USER_SWITCHER_CREATION_DIALOG", 7, 1279)};
        $VALUES = multiUserActionsEventArr;
        EnumEntriesKt.enumEntries(multiUserActionsEventArr);
    }

    public MultiUserActionsEvent(String str, int i, int i2) {
        this.value = i2;
    }

    public static MultiUserActionsEvent valueOf(String str) {
        return (MultiUserActionsEvent) Enum.valueOf(MultiUserActionsEvent.class, str);
    }

    public static MultiUserActionsEvent[] values() {
        return (MultiUserActionsEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.value;
    }
}
