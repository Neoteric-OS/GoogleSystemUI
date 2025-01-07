package com.android.systemui.volume.panel.component.bottombar.ui.viewmodel;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BottomBarViewModel {
    public final ActivityStarter activityStarter;
    public final UiEventLogger uiEventLogger;
    public final VolumePanelViewModel volumePanelViewModel;

    public BottomBarViewModel(ActivityStarter activityStarter, VolumePanelViewModel volumePanelViewModel, UiEventLogger uiEventLogger) {
        this.activityStarter = activityStarter;
        this.volumePanelViewModel = volumePanelViewModel;
        this.uiEventLogger = uiEventLogger;
    }
}
