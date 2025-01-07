package com.android.systemui.power.shared.model;

import com.android.app.viewcapture.data.ViewNode;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WakeSleepReason {
    public static final /* synthetic */ WakeSleepReason[] $VALUES;
    public static final WakeSleepReason BIOMETRIC;
    public static final Companion Companion;
    public static final WakeSleepReason FOLD;
    public static final WakeSleepReason GESTURE;
    public static final WakeSleepReason KEY;
    public static final WakeSleepReason LID;
    public static final WakeSleepReason LIFT;
    public static final WakeSleepReason MOTION;
    public static final WakeSleepReason OTHER;
    public static final WakeSleepReason POWER_BUTTON;
    public static final WakeSleepReason TAP;
    public static final WakeSleepReason TIMEOUT;
    public static final WakeSleepReason UNFOLD;
    private final boolean isTouch;
    private final int powerManagerWakeReason;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static WakeSleepReason fromPowerManagerWakeReason(int i) {
            if (i == 1) {
                return WakeSleepReason.POWER_BUTTON;
            }
            if (i == 4) {
                return WakeSleepReason.GESTURE;
            }
            if (i == 9) {
                return WakeSleepReason.LID;
            }
            if (i == 12) {
                return WakeSleepReason.UNFOLD;
            }
            if (i == 6) {
                return WakeSleepReason.KEY;
            }
            if (i == 7) {
                return WakeSleepReason.MOTION;
            }
            switch (i) {
                case 15:
                    return WakeSleepReason.TAP;
                case 16:
                    return WakeSleepReason.LIFT;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    return WakeSleepReason.BIOMETRIC;
                default:
                    return WakeSleepReason.OTHER;
            }
        }
    }

    static {
        WakeSleepReason wakeSleepReason = new WakeSleepReason(0, 1, "POWER_BUTTON", false);
        POWER_BUTTON = wakeSleepReason;
        WakeSleepReason wakeSleepReason2 = new WakeSleepReason(1, 15, "TAP", true);
        TAP = wakeSleepReason2;
        WakeSleepReason wakeSleepReason3 = new WakeSleepReason(2, 4, "GESTURE", true);
        GESTURE = wakeSleepReason3;
        WakeSleepReason wakeSleepReason4 = new WakeSleepReason(3, 6, "KEY", false);
        KEY = wakeSleepReason4;
        WakeSleepReason wakeSleepReason5 = new WakeSleepReason(4, 7, "MOTION", false);
        MOTION = wakeSleepReason5;
        WakeSleepReason wakeSleepReason6 = new WakeSleepReason(5, 9, "LID", false);
        LID = wakeSleepReason6;
        WakeSleepReason wakeSleepReason7 = new WakeSleepReason(6, 12, "UNFOLD", false);
        UNFOLD = wakeSleepReason7;
        WakeSleepReason wakeSleepReason8 = new WakeSleepReason(7, 16, "LIFT", false);
        LIFT = wakeSleepReason8;
        WakeSleepReason wakeSleepReason9 = new WakeSleepReason(8, 17, "BIOMETRIC", false);
        BIOMETRIC = wakeSleepReason9;
        WakeSleepReason wakeSleepReason10 = new WakeSleepReason(9, 0, "OTHER", false);
        OTHER = wakeSleepReason10;
        WakeSleepReason wakeSleepReason11 = new WakeSleepReason(10, 13, "FOLD", false);
        FOLD = wakeSleepReason11;
        WakeSleepReason wakeSleepReason12 = new WakeSleepReason(11, 2, "TIMEOUT", false);
        TIMEOUT = wakeSleepReason12;
        WakeSleepReason[] wakeSleepReasonArr = {wakeSleepReason, wakeSleepReason2, wakeSleepReason3, wakeSleepReason4, wakeSleepReason5, wakeSleepReason6, wakeSleepReason7, wakeSleepReason8, wakeSleepReason9, wakeSleepReason10, wakeSleepReason11, wakeSleepReason12};
        $VALUES = wakeSleepReasonArr;
        EnumEntriesKt.enumEntries(wakeSleepReasonArr);
        Companion = new Companion();
    }

    public WakeSleepReason(int i, int i2, String str, boolean z) {
        this.isTouch = z;
        this.powerManagerWakeReason = i2;
    }

    public static WakeSleepReason valueOf(String str) {
        return (WakeSleepReason) Enum.valueOf(WakeSleepReason.class, str);
    }

    public static WakeSleepReason[] values() {
        return (WakeSleepReason[]) $VALUES.clone();
    }

    public final int getPowerManagerWakeReason() {
        return this.powerManagerWakeReason;
    }
}
