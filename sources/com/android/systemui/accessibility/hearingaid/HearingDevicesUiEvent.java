package com.android.systemui.accessibility.hearingaid;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesUiEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ HearingDevicesUiEvent[] $VALUES;
    public static final HearingDevicesUiEvent HEARING_DEVICES_CONNECT;
    public static final HearingDevicesUiEvent HEARING_DEVICES_DIALOG_SHOW;
    public static final HearingDevicesUiEvent HEARING_DEVICES_DISCONNECT;
    public static final HearingDevicesUiEvent HEARING_DEVICES_GEAR_CLICK;
    public static final HearingDevicesUiEvent HEARING_DEVICES_PAIR;
    public static final HearingDevicesUiEvent HEARING_DEVICES_PRESET_SELECT;
    public static final HearingDevicesUiEvent HEARING_DEVICES_RELATED_TOOL_CLICK;
    public static final HearingDevicesUiEvent HEARING_DEVICES_SET_ACTIVE;
    private final int id;

    static {
        HearingDevicesUiEvent hearingDevicesUiEvent = new HearingDevicesUiEvent("HEARING_DEVICES_DIALOG_SHOW", 0, 1848);
        HEARING_DEVICES_DIALOG_SHOW = hearingDevicesUiEvent;
        HearingDevicesUiEvent hearingDevicesUiEvent2 = new HearingDevicesUiEvent("HEARING_DEVICES_PAIR", 1, 1849);
        HEARING_DEVICES_PAIR = hearingDevicesUiEvent2;
        HearingDevicesUiEvent hearingDevicesUiEvent3 = new HearingDevicesUiEvent("HEARING_DEVICES_CONNECT", 2, 1850);
        HEARING_DEVICES_CONNECT = hearingDevicesUiEvent3;
        HearingDevicesUiEvent hearingDevicesUiEvent4 = new HearingDevicesUiEvent("HEARING_DEVICES_DISCONNECT", 3, 1851);
        HEARING_DEVICES_DISCONNECT = hearingDevicesUiEvent4;
        HearingDevicesUiEvent hearingDevicesUiEvent5 = new HearingDevicesUiEvent("HEARING_DEVICES_SET_ACTIVE", 4, 1852);
        HEARING_DEVICES_SET_ACTIVE = hearingDevicesUiEvent5;
        HearingDevicesUiEvent hearingDevicesUiEvent6 = new HearingDevicesUiEvent("HEARING_DEVICES_GEAR_CLICK", 5, 1853);
        HEARING_DEVICES_GEAR_CLICK = hearingDevicesUiEvent6;
        HearingDevicesUiEvent hearingDevicesUiEvent7 = new HearingDevicesUiEvent("HEARING_DEVICES_PRESET_SELECT", 6, 1854);
        HEARING_DEVICES_PRESET_SELECT = hearingDevicesUiEvent7;
        HearingDevicesUiEvent hearingDevicesUiEvent8 = new HearingDevicesUiEvent("HEARING_DEVICES_RELATED_TOOL_CLICK", 7, 1856);
        HEARING_DEVICES_RELATED_TOOL_CLICK = hearingDevicesUiEvent8;
        HearingDevicesUiEvent[] hearingDevicesUiEventArr = {hearingDevicesUiEvent, hearingDevicesUiEvent2, hearingDevicesUiEvent3, hearingDevicesUiEvent4, hearingDevicesUiEvent5, hearingDevicesUiEvent6, hearingDevicesUiEvent7, hearingDevicesUiEvent8};
        $VALUES = hearingDevicesUiEventArr;
        EnumEntriesKt.enumEntries(hearingDevicesUiEventArr);
    }

    public HearingDevicesUiEvent(String str, int i, int i2) {
        this.id = i2;
    }

    public static HearingDevicesUiEvent valueOf(String str) {
        return (HearingDevicesUiEvent) Enum.valueOf(HearingDevicesUiEvent.class, str);
    }

    public static HearingDevicesUiEvent[] values() {
        return (HearingDevicesUiEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.id;
    }
}
