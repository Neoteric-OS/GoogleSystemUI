package com.android.systemui.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.systemui.volume.domain.interactor.VolumePanelNavigationInteractor;
import com.android.systemui.volume.domain.model.VolumePanelRoute;
import com.android.systemui.volume.ui.navigation.VolumeNavigator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelDialogReceiver extends BroadcastReceiver {
    public final VolumeNavigator volumeNavigator;
    public final VolumePanelNavigationInteractor volumePanelNavigationInteractor;

    public VolumePanelDialogReceiver(VolumeNavigator volumeNavigator, VolumePanelNavigationInteractor volumePanelNavigationInteractor) {
        this.volumeNavigator = volumeNavigator;
        this.volumePanelNavigationInteractor = volumePanelNavigationInteractor;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (TextUtils.equals("com.android.systemui.action.LAUNCH_VOLUME_PANEL_DIALOG", intent.getAction()) || TextUtils.equals("android.settings.panel.action.VOLUME", intent.getAction())) {
            VolumeNavigator volumeNavigator = this.volumeNavigator;
            this.volumePanelNavigationInteractor.getClass();
            VolumePanelRoute volumePanelRoute = VolumePanelRoute.COMPOSE_VOLUME_PANEL;
            volumeNavigator.openVolumePanel();
        }
    }
}
