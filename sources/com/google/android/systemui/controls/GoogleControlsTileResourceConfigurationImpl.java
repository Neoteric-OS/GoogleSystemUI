package com.google.android.systemui.controls;

import android.content.ComponentName;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsTileResourceConfiguration;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GoogleControlsTileResourceConfigurationImpl implements ControlsTileResourceConfiguration {
    public final Lazy controlsComponent;

    public GoogleControlsTileResourceConfigurationImpl(Lazy lazy) {
        this.controlsComponent = lazy;
    }

    public final ComponentName getComponentName() {
        Lazy lazy = this.controlsComponent;
        if (!((ControlsComponent) lazy.get()).featureEnabled) {
            return new ComponentName("", "");
        }
        SelectedItem preferredSelection = ((ControlsControllerImpl) ((ControlsController) ((ControlsComponent) lazy.get()).controlsController.get())).getPreferredSelection();
        if (preferredSelection instanceof SelectedItem.StructureItem) {
            return ((SelectedItem.StructureItem) preferredSelection).componentName;
        }
        if (preferredSelection instanceof SelectedItem.PanelItem) {
            return ((SelectedItem.PanelItem) preferredSelection).componentName;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final String getPackageName() {
        String packageName = getComponentName().getPackageName();
        if (Intrinsics.areEqual(packageName, "com.google.android.apps.chromecast.app")) {
            return packageName;
        }
        return null;
    }

    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final int getTileImageId() {
        return Intrinsics.areEqual(getComponentName().getPackageName(), "com.google.android.apps.chromecast.app") ? R.drawable.home_controls_icon : R.drawable.controls_icon;
    }

    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final int getTileTitleId() {
        return Intrinsics.areEqual(getComponentName().getPackageName(), "com.google.android.apps.chromecast.app") ? R.string.home_controls_tile_title : R.string.quick_controls_title;
    }
}
