package com.android.systemui.volume.panel.component.volume.ui.composable;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class VolumeSliderContentComponent {
    public static final /* synthetic */ VolumeSliderContentComponent[] $VALUES;
    public static final VolumeSliderContentComponent DisabledMessage;
    public static final VolumeSliderContentComponent Label;

    static {
        VolumeSliderContentComponent volumeSliderContentComponent = new VolumeSliderContentComponent("Label", 0);
        Label = volumeSliderContentComponent;
        VolumeSliderContentComponent volumeSliderContentComponent2 = new VolumeSliderContentComponent("DisabledMessage", 1);
        DisabledMessage = volumeSliderContentComponent2;
        VolumeSliderContentComponent[] volumeSliderContentComponentArr = {volumeSliderContentComponent, volumeSliderContentComponent2};
        $VALUES = volumeSliderContentComponentArr;
        EnumEntriesKt.enumEntries(volumeSliderContentComponentArr);
    }

    public static VolumeSliderContentComponent valueOf(String str) {
        return (VolumeSliderContentComponent) Enum.valueOf(VolumeSliderContentComponent.class, str);
    }

    public static VolumeSliderContentComponent[] values() {
        return (VolumeSliderContentComponent[]) $VALUES.clone();
    }
}
