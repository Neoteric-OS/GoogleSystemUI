package com.android.systemui.qs.ui.viewmodel;

import com.android.systemui.brightness.ui.viewmodel.BrightnessSliderViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.QuickQuickSettingsViewModel;
import com.android.systemui.qs.panels.ui.viewmodel.TileGridViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickSettingsContainerViewModel {
    public final BrightnessSliderViewModel brightnessSliderViewModel;
    public final EditModeViewModel editModeViewModel;
    public final QuickQuickSettingsViewModel quickQuickSettingsViewModel;
    public final TileGridViewModel tileGridViewModel;

    public QuickSettingsContainerViewModel(BrightnessSliderViewModel brightnessSliderViewModel, TileGridViewModel tileGridViewModel, EditModeViewModel editModeViewModel, QuickQuickSettingsViewModel quickQuickSettingsViewModel) {
        this.brightnessSliderViewModel = brightnessSliderViewModel;
        this.tileGridViewModel = tileGridViewModel;
        this.editModeViewModel = editModeViewModel;
        this.quickQuickSettingsViewModel = quickQuickSettingsViewModel;
    }
}
