package com.android.systemui.privacy;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyChipEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ PrivacyChipEvent[] $VALUES;
    public static final PrivacyChipEvent ONGOING_INDICATORS_CHIP_CLICK;
    public static final PrivacyChipEvent ONGOING_INDICATORS_CHIP_VIEW;
    private final int _id;

    static {
        PrivacyChipEvent privacyChipEvent = new PrivacyChipEvent("ONGOING_INDICATORS_CHIP_VIEW", 0, 601);
        ONGOING_INDICATORS_CHIP_VIEW = privacyChipEvent;
        PrivacyChipEvent privacyChipEvent2 = new PrivacyChipEvent("ONGOING_INDICATORS_CHIP_CLICK", 1, 602);
        ONGOING_INDICATORS_CHIP_CLICK = privacyChipEvent2;
        PrivacyChipEvent[] privacyChipEventArr = {privacyChipEvent, privacyChipEvent2};
        $VALUES = privacyChipEventArr;
        EnumEntriesKt.enumEntries(privacyChipEventArr);
    }

    public PrivacyChipEvent(String str, int i, int i2) {
        this._id = i2;
    }

    public static PrivacyChipEvent valueOf(String str) {
        return (PrivacyChipEvent) Enum.valueOf(PrivacyChipEvent.class, str);
    }

    public static PrivacyChipEvent[] values() {
        return (PrivacyChipEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this._id;
    }
}
