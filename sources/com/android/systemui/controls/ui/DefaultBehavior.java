package com.android.systemui.controls.ui;

import android.service.controls.Control;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultBehavior implements Behavior {
    public ControlViewHolder cvh;

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, int i) {
        CharSequence charSequence;
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder == null) {
            controlViewHolder = null;
        }
        Control control = controlWithState.control;
        if (control == null || (charSequence = control.getStatusText()) == null) {
            charSequence = "";
        }
        Set set = ControlViewHolder.FORCE_PANEL_DEVICES;
        controlViewHolder.setStatusText(charSequence, false);
        ControlViewHolder controlViewHolder2 = this.cvh;
        (controlViewHolder2 != null ? controlViewHolder2 : null).applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, false, true);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
    }
}
