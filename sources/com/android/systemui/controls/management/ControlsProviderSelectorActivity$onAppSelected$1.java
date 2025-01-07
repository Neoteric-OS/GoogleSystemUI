package com.android.systemui.controls.management;

import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.controls.ui.SelectedItem;
import java.util.Collections;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderSelectorActivity$onAppSelected$1 implements Consumer {
    public final /* synthetic */ CharSequence $appName;
    public final /* synthetic */ ControlsServiceInfo $serviceInfo;
    public final /* synthetic */ ControlsProviderSelectorActivity this$0;

    public ControlsProviderSelectorActivity$onAppSelected$1(ControlsProviderSelectorActivity controlsProviderSelectorActivity, ControlsServiceInfo controlsServiceInfo, CharSequence charSequence) {
        this.this$0 = controlsProviderSelectorActivity;
        this.$serviceInfo = controlsServiceInfo;
        this.$appName = charSequence;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            ((AuthorizedPanelsRepositoryImpl) this.this$0.authorizedPanelsRepository).addAuthorizedPanels(Collections.singleton(this.$serviceInfo.componentName.getPackageName()));
            ((ControlsControllerImpl) this.this$0.controlsController).setPreferredSelection(new SelectedItem.PanelItem(this.$serviceInfo.componentName, this.$appName));
            this.this$0.animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            ControlsProviderSelectorActivity controlsProviderSelectorActivity = this.this$0;
            controlsProviderSelectorActivity.getClass();
            controlsProviderSelectorActivity.startActivity(new Intent(controlsProviderSelectorActivity.getApplicationContext(), (Class<?>) ControlsActivity.class), ActivityOptions.makeSceneTransitionAnimation(controlsProviderSelectorActivity, new Pair[0]).toBundle());
        }
        this.this$0.dialog = null;
    }
}
