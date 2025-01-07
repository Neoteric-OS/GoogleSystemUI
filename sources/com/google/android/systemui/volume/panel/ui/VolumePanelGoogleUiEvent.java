package com.google.android.systemui.volume.panel.ui;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelGoogleUiEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ VolumePanelGoogleUiEvent[] $VALUES;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_CLEAR_CALLING_TOGGLE_CLICKED;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_CLEAR_CALLING_TOGGLE_GONE;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_CLEAR_CALLING_TOGGLE_SHOWN;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_CLICKED;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_GONE;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_SHOWN;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_MEDIA_ROUTED_TO_3P_BLUETOOTH_DEVICE;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_MEDIA_ROUTED_TO_PHONE_SPEAKER;
    public static final VolumePanelGoogleUiEvent VOLUME_PANEL_MEDIA_ROUTED_TO_PIXEL_BUDS;
    private final int metricId;

    static {
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent = new VolumePanelGoogleUiEvent("VOLUME_PANEL_MEDIA_ROUTED_TO_PIXEL_BUDS", 0, 1682);
        VOLUME_PANEL_MEDIA_ROUTED_TO_PIXEL_BUDS = volumePanelGoogleUiEvent;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent2 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_MEDIA_ROUTED_TO_3P_BLUETOOTH_DEVICE", 1, 1683);
        VOLUME_PANEL_MEDIA_ROUTED_TO_3P_BLUETOOTH_DEVICE = volumePanelGoogleUiEvent2;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent3 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_MEDIA_ROUTED_TO_PHONE_SPEAKER", 2, 1684);
        VOLUME_PANEL_MEDIA_ROUTED_TO_PHONE_SPEAKER = volumePanelGoogleUiEvent3;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent4 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_CLEAR_CALLING_TOGGLE_SHOWN", 3, 1655);
        VOLUME_PANEL_CLEAR_CALLING_TOGGLE_SHOWN = volumePanelGoogleUiEvent4;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent5 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_CLEAR_CALLING_TOGGLE_GONE", 4, 1656);
        VOLUME_PANEL_CLEAR_CALLING_TOGGLE_GONE = volumePanelGoogleUiEvent5;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent6 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_CLEAR_CALLING_TOGGLE_CLICKED", 5, 1657);
        VOLUME_PANEL_CLEAR_CALLING_TOGGLE_CLICKED = volumePanelGoogleUiEvent6;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent7 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_SHOWN", 6, 1658);
        VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_SHOWN = volumePanelGoogleUiEvent7;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent8 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_GONE", 7, 1659);
        VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_GONE = volumePanelGoogleUiEvent8;
        VolumePanelGoogleUiEvent volumePanelGoogleUiEvent9 = new VolumePanelGoogleUiEvent("VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_CLICKED", 8, 1660);
        VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_CLICKED = volumePanelGoogleUiEvent9;
        VolumePanelGoogleUiEvent[] volumePanelGoogleUiEventArr = {volumePanelGoogleUiEvent, volumePanelGoogleUiEvent2, volumePanelGoogleUiEvent3, volumePanelGoogleUiEvent4, volumePanelGoogleUiEvent5, volumePanelGoogleUiEvent6, volumePanelGoogleUiEvent7, volumePanelGoogleUiEvent8, volumePanelGoogleUiEvent9};
        $VALUES = volumePanelGoogleUiEventArr;
        EnumEntriesKt.enumEntries(volumePanelGoogleUiEventArr);
    }

    public VolumePanelGoogleUiEvent(String str, int i, int i2) {
        this.metricId = i2;
    }

    public static VolumePanelGoogleUiEvent valueOf(String str) {
        return (VolumePanelGoogleUiEvent) Enum.valueOf(VolumePanelGoogleUiEvent.class, str);
    }

    public static VolumePanelGoogleUiEvent[] values() {
        return (VolumePanelGoogleUiEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.metricId;
    }
}
