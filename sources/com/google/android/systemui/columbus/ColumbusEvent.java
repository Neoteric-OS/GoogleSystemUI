package com.google.android.systemui.columbus;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ ColumbusEvent[] $VALUES;
    public static final ColumbusEvent COLUMBUS_DOUBLE_TAP_DETECTED;
    public static final ColumbusEvent COLUMBUS_INVOKED_ASSISTANT;
    public static final ColumbusEvent COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE;
    public static final ColumbusEvent COLUMBUS_INVOKED_LAUNCH_APP;
    public static final ColumbusEvent COLUMBUS_INVOKED_LAUNCH_APP_SECURE;
    public static final ColumbusEvent COLUMBUS_INVOKED_LAUNCH_SHORTCUT;
    public static final ColumbusEvent COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE;
    public static final ColumbusEvent COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN;
    public static final ColumbusEvent COLUMBUS_INVOKED_ON_SETTINGS;
    public static final ColumbusEvent COLUMBUS_INVOKED_OVERVIEW;
    public static final ColumbusEvent COLUMBUS_INVOKED_PAUSE_MEDIA;
    public static final ColumbusEvent COLUMBUS_INVOKED_PLAY_MEDIA;
    public static final ColumbusEvent COLUMBUS_INVOKED_SCREENSHOT;
    public static final ColumbusEvent COLUMBUS_MODE_HIGH_POWER_ACTIVE;
    public static final ColumbusEvent COLUMBUS_MODE_INACTIVE;
    public static final ColumbusEvent COLUMBUS_MODE_LOW_POWER_ACTIVE;
    public static final ColumbusEvent COLUMBUS_RETARGET_APPROVED;
    public static final ColumbusEvent COLUMBUS_RETARGET_DIALOG_SHOWN;
    public static final ColumbusEvent COLUMBUS_RETARGET_FOLLOW_ON_APPROVED;
    public static final ColumbusEvent COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED;
    public static final ColumbusEvent COLUMBUS_RETARGET_NOT_APPROVED;
    private final int id;

    static {
        ColumbusEvent columbusEvent = new ColumbusEvent("COLUMBUS_UNKNOWN_EVENT", 0, 0);
        ColumbusEvent columbusEvent2 = new ColumbusEvent("COLUMBUS_DOUBLE_TAP_DETECTED", 1, 628);
        COLUMBUS_DOUBLE_TAP_DETECTED = columbusEvent2;
        ColumbusEvent columbusEvent3 = new ColumbusEvent("COLUMBUS_INVOKED_ASSISTANT", 2, 629);
        COLUMBUS_INVOKED_ASSISTANT = columbusEvent3;
        ColumbusEvent columbusEvent4 = new ColumbusEvent("COLUMBUS_INVOKED_SCREENSHOT", 3, 630);
        COLUMBUS_INVOKED_SCREENSHOT = columbusEvent4;
        ColumbusEvent columbusEvent5 = new ColumbusEvent("COLUMBUS_INVOKED_PLAY_MEDIA", 4, 631);
        COLUMBUS_INVOKED_PLAY_MEDIA = columbusEvent5;
        ColumbusEvent columbusEvent6 = new ColumbusEvent("COLUMBUS_INVOKED_PAUSE_MEDIA", 5, 639);
        COLUMBUS_INVOKED_PAUSE_MEDIA = columbusEvent6;
        ColumbusEvent columbusEvent7 = new ColumbusEvent("COLUMBUS_INVOKED_OVERVIEW", 6, 632);
        COLUMBUS_INVOKED_OVERVIEW = columbusEvent7;
        ColumbusEvent columbusEvent8 = new ColumbusEvent("COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN", 7, 633);
        COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN = columbusEvent8;
        ColumbusEvent columbusEvent9 = new ColumbusEvent("COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE", 8, 634);
        COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE = columbusEvent9;
        ColumbusEvent columbusEvent10 = new ColumbusEvent("COLUMBUS_INVOKED_LAUNCH_APP", 9, 815);
        COLUMBUS_INVOKED_LAUNCH_APP = columbusEvent10;
        ColumbusEvent columbusEvent11 = new ColumbusEvent("COLUMBUS_INVOKED_LAUNCH_SHORTCUT", 10, 816);
        COLUMBUS_INVOKED_LAUNCH_SHORTCUT = columbusEvent11;
        ColumbusEvent columbusEvent12 = new ColumbusEvent("COLUMBUS_INVOKED_LAUNCH_APP_SECURE", 11, 898);
        COLUMBUS_INVOKED_LAUNCH_APP_SECURE = columbusEvent12;
        ColumbusEvent columbusEvent13 = new ColumbusEvent("COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE", 12, 932);
        COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE = columbusEvent13;
        ColumbusEvent columbusEvent14 = new ColumbusEvent("COLUMBUS_INVOKED_ON_SETTINGS", 13, 817);
        COLUMBUS_INVOKED_ON_SETTINGS = columbusEvent14;
        ColumbusEvent columbusEvent15 = new ColumbusEvent("COLUMBUS_MODE_LOW_POWER_ACTIVE", 14, 635);
        COLUMBUS_MODE_LOW_POWER_ACTIVE = columbusEvent15;
        ColumbusEvent columbusEvent16 = new ColumbusEvent("COLUMBUS_MODE_HIGH_POWER_ACTIVE", 15, 636);
        COLUMBUS_MODE_HIGH_POWER_ACTIVE = columbusEvent16;
        ColumbusEvent columbusEvent17 = new ColumbusEvent("COLUMBUS_MODE_INACTIVE", 16, 637);
        COLUMBUS_MODE_INACTIVE = columbusEvent17;
        ColumbusEvent columbusEvent18 = new ColumbusEvent("COLUMBUS_RETARGET_DIALOG_SHOWN", 17, 899);
        COLUMBUS_RETARGET_DIALOG_SHOWN = columbusEvent18;
        ColumbusEvent columbusEvent19 = new ColumbusEvent("COLUMBUS_RETARGET_APPROVED", 18, 900);
        COLUMBUS_RETARGET_APPROVED = columbusEvent19;
        ColumbusEvent columbusEvent20 = new ColumbusEvent("COLUMBUS_RETARGET_NOT_APPROVED", 19, 901);
        COLUMBUS_RETARGET_NOT_APPROVED = columbusEvent20;
        ColumbusEvent columbusEvent21 = new ColumbusEvent("COLUMBUS_RETARGET_FOLLOW_ON_APPROVED", 20, 902);
        COLUMBUS_RETARGET_FOLLOW_ON_APPROVED = columbusEvent21;
        ColumbusEvent columbusEvent22 = new ColumbusEvent("COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED", 21, 903);
        COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED = columbusEvent22;
        ColumbusEvent[] columbusEventArr = {columbusEvent, columbusEvent2, columbusEvent3, columbusEvent4, columbusEvent5, columbusEvent6, columbusEvent7, columbusEvent8, columbusEvent9, columbusEvent10, columbusEvent11, columbusEvent12, columbusEvent13, columbusEvent14, columbusEvent15, columbusEvent16, columbusEvent17, columbusEvent18, columbusEvent19, columbusEvent20, columbusEvent21, columbusEvent22};
        $VALUES = columbusEventArr;
        EnumEntriesKt.enumEntries(columbusEventArr);
    }

    public ColumbusEvent(String str, int i, int i2) {
        this.id = i2;
    }

    public static ColumbusEvent valueOf(String str) {
        return (ColumbusEvent) Enum.valueOf(ColumbusEvent.class, str);
    }

    public static ColumbusEvent[] values() {
        return (ColumbusEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.id;
    }
}
