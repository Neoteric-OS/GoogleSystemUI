package com.android.systemui.volume.panel.component.bottombar.ui;

import android.content.Intent;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel;
import com.android.systemui.volume.panel.ui.VolumePanelUiEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class BottomBarComponent$Content$1$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        final BottomBarViewModel bottomBarViewModel = (BottomBarViewModel) this.receiver;
        bottomBarViewModel.uiEventLogger.log(VolumePanelUiEvent.VOLUME_PANEL_SOUND_SETTINGS_CLICKED);
        bottomBarViewModel.activityStarter.startActivityDismissingKeyguard(new Intent("android.settings.SOUND_SETTINGS"), false, true, false, new ActivityStarter.Callback() { // from class: com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel$onSettingsClicked$1
            @Override // com.android.systemui.plugins.ActivityStarter.Callback
            public final void onActivityStarted(int i) {
                BottomBarViewModel.this.volumePanelViewModel.volumePanelGlobalStateInteractor.setVisible(false);
            }
        }, 131072, null, null);
        return Unit.INSTANCE;
    }
}
