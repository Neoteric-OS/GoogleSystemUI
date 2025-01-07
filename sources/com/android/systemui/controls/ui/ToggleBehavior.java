package com.android.systemui.controls.ui;

import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.util.Log;
import android.view.View;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToggleBehavior implements Behavior {
    public Control control;
    public ControlViewHolder cvh;
    public ToggleTemplate template;

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, int i) {
        ToggleTemplate toggleTemplate;
        Control control = controlWithState.control;
        Intrinsics.checkNotNull(control);
        this.control = control;
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder == null) {
            controlViewHolder = null;
        }
        CharSequence statusText = control.getStatusText();
        Set set = ControlViewHolder.FORCE_PANEL_DEVICES;
        controlViewHolder.setStatusText(statusText, false);
        Control control2 = this.control;
        if (control2 == null) {
            control2 = null;
        }
        ControlTemplate controlTemplate = control2.getControlTemplate();
        if (controlTemplate instanceof ToggleTemplate) {
            toggleTemplate = (ToggleTemplate) controlTemplate;
        } else {
            if (!(controlTemplate instanceof TemperatureControlTemplate)) {
                Log.e("ControlsUiController", "Unsupported template type: " + controlTemplate);
                return;
            }
            toggleTemplate = (ToggleTemplate) ((TemperatureControlTemplate) controlTemplate).getTemplate();
        }
        this.template = toggleTemplate;
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        ((LayerDrawable) controlViewHolder2.layout.getBackground()).findDrawableByLayerId(R.id.clip_layer).setLevel(10000);
        ToggleTemplate toggleTemplate2 = this.template;
        if (toggleTemplate2 == null) {
            toggleTemplate2 = null;
        }
        boolean isChecked = toggleTemplate2.isChecked();
        ControlViewHolder controlViewHolder3 = this.cvh;
        (controlViewHolder3 != null ? controlViewHolder3 : null).applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, isChecked, true);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(final ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
        controlViewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ToggleBehavior$initialize$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlViewHolder controlViewHolder2 = ControlViewHolder.this;
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = controlViewHolder2.controlActionCoordinator;
                ToggleTemplate toggleTemplate = this.template;
                if (toggleTemplate == null) {
                    toggleTemplate = null;
                }
                String templateId = toggleTemplate.getTemplateId();
                ToggleTemplate toggleTemplate2 = this.template;
                controlActionCoordinatorImpl.toggle(controlViewHolder2, templateId, (toggleTemplate2 != null ? toggleTemplate2 : null).isChecked());
            }
        });
    }
}
