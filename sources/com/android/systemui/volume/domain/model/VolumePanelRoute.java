package com.android.systemui.volume.domain.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelRoute {
    public static final /* synthetic */ VolumePanelRoute[] $VALUES;
    public static final VolumePanelRoute COMPOSE_VOLUME_PANEL = null;

    static {
        VolumePanelRoute[] volumePanelRouteArr = {new VolumePanelRoute("COMPOSE_VOLUME_PANEL", 0), new VolumePanelRoute("SETTINGS_VOLUME_PANEL", 1), new VolumePanelRoute("SYSTEM_UI_VOLUME_PANEL", 2)};
        $VALUES = volumePanelRouteArr;
        EnumEntriesKt.enumEntries(volumePanelRouteArr);
    }

    public static VolumePanelRoute valueOf(String str) {
        return (VolumePanelRoute) Enum.valueOf(VolumePanelRoute.class, str);
    }

    public static VolumePanelRoute[] values() {
        return (VolumePanelRoute[]) $VALUES.clone();
    }
}
