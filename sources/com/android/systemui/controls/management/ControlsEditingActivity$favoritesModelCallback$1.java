package com.android.systemui.controls.management;

import android.view.View;
import com.android.systemui.controls.management.ControlsModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsEditingActivity$favoritesModelCallback$1 implements ControlsModel.ControlsModelCallback {
    public final /* synthetic */ ControlsEditingActivity this$0;

    public ControlsEditingActivity$favoritesModelCallback$1(ControlsEditingActivity controlsEditingActivity) {
        this.this$0 = controlsEditingActivity;
    }

    @Override // com.android.systemui.controls.management.ControlsModel.ControlsModelCallback
    public final void onFirstChange() {
        View view = this.this$0.saveButton;
        if (view == null) {
            view = null;
        }
        view.setEnabled(true);
    }

    @Override // com.android.systemui.controls.management.ControlsModel.ControlsModelCallback
    public final void onChange() {
    }
}
