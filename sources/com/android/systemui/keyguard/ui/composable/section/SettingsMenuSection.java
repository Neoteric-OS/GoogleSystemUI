package com.android.systemui.keyguard.ui.composable.section;

import com.android.systemui.keyguard.ui.viewmodel.KeyguardSettingsMenuViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardTouchHandlingViewModel;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.VibratorHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SettingsMenuSection {
    public final ActivityStarter activityStarter;
    public final KeyguardTouchHandlingViewModel touchHandlingViewModel;
    public final VibratorHelper vibratorHelper;
    public final KeyguardSettingsMenuViewModel viewModel;

    public SettingsMenuSection(KeyguardSettingsMenuViewModel keyguardSettingsMenuViewModel, KeyguardTouchHandlingViewModel keyguardTouchHandlingViewModel, VibratorHelper vibratorHelper, ActivityStarter activityStarter) {
        this.activityStarter = activityStarter;
    }
}
