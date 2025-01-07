package com.android.systemui.communal.data.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisabledReason {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ DisabledReason[] $VALUES;
    public static final DisabledReason DISABLED_REASON_DEVICE_POLICY;
    public static final DisabledReason DISABLED_REASON_FLAG;
    public static final DisabledReason DISABLED_REASON_INVALID_USER;
    public static final DisabledReason DISABLED_REASON_USER_SETTING;
    private final String loggingString;

    static {
        DisabledReason disabledReason = new DisabledReason("DISABLED_REASON_INVALID_USER", "invalidUser", 0);
        DISABLED_REASON_INVALID_USER = disabledReason;
        DisabledReason disabledReason2 = new DisabledReason("DISABLED_REASON_FLAG", "flag", 1);
        DISABLED_REASON_FLAG = disabledReason2;
        DisabledReason disabledReason3 = new DisabledReason("DISABLED_REASON_USER_SETTING", "userSetting", 2);
        DISABLED_REASON_USER_SETTING = disabledReason3;
        DisabledReason disabledReason4 = new DisabledReason("DISABLED_REASON_DEVICE_POLICY", "devicePolicy", 3);
        DISABLED_REASON_DEVICE_POLICY = disabledReason4;
        DisabledReason[] disabledReasonArr = {disabledReason, disabledReason2, disabledReason3, disabledReason4};
        $VALUES = disabledReasonArr;
        $ENTRIES = EnumEntriesKt.enumEntries(disabledReasonArr);
    }

    public DisabledReason(String str, String str2, int i) {
        this.loggingString = str2;
    }

    public static DisabledReason valueOf(String str) {
        return (DisabledReason) Enum.valueOf(DisabledReason.class, str);
    }

    public static DisabledReason[] values() {
        return (DisabledReason[]) $VALUES.clone();
    }

    public final String getLoggingString() {
        return this.loggingString;
    }
}
