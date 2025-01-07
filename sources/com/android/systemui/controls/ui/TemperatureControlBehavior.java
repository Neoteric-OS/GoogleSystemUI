package com.android.systemui.controls.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.view.View;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TemperatureControlBehavior implements Behavior {
    public Drawable clipLayer;
    public Control control;
    public ControlViewHolder cvh;
    public Behavior subBehavior;

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, int i) {
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
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        this.clipLayer = ((LayerDrawable) controlViewHolder2.layout.getBackground()).findDrawableByLayerId(R.id.clip_layer);
        Control control2 = this.control;
        if (control2 == null) {
            control2 = null;
        }
        final TemperatureControlTemplate temperatureControlTemplate = (TemperatureControlTemplate) control2.getControlTemplate();
        int currentActiveMode = temperatureControlTemplate.getCurrentActiveMode();
        ControlTemplate template = temperatureControlTemplate.getTemplate();
        if (template.equals(ControlTemplate.getNoTemplateObject()) || template.equals(ControlTemplate.getErrorTemplate())) {
            boolean z = (currentActiveMode == 0 || currentActiveMode == 1) ? false : true;
            Drawable drawable = this.clipLayer;
            if (drawable == null) {
                drawable = null;
            }
            drawable.setLevel(z ? 10000 : 0);
            ControlViewHolder controlViewHolder3 = this.cvh;
            if (controlViewHolder3 == null) {
                controlViewHolder3 = null;
            }
            controlViewHolder3.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(currentActiveMode, z, true);
            ControlViewHolder controlViewHolder4 = this.cvh;
            (controlViewHolder4 != null ? controlViewHolder4 : null).layout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.TemperatureControlBehavior$bind$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ControlViewHolder controlViewHolder5 = TemperatureControlBehavior.this.cvh;
                    ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder5 != null ? controlViewHolder5 : null).controlActionCoordinator;
                    if (controlViewHolder5 == null) {
                        controlViewHolder5 = null;
                    }
                    String templateId = temperatureControlTemplate.getTemplateId();
                    Control control3 = TemperatureControlBehavior.this.control;
                    controlActionCoordinatorImpl.touch(controlViewHolder5, templateId, control3 != null ? control3 : null);
                }
            });
            return;
        }
        ControlViewHolder controlViewHolder5 = this.cvh;
        ControlViewHolder controlViewHolder6 = controlViewHolder5 != null ? controlViewHolder5 : null;
        Behavior behavior = this.subBehavior;
        if (controlViewHolder5 == null) {
            controlViewHolder5 = null;
        }
        Control control3 = this.control;
        if (control3 == null) {
            control3 = null;
        }
        int status = control3.getStatus();
        Control control4 = this.control;
        this.subBehavior = controlViewHolder6.bindBehavior(behavior, controlViewHolder5.findBehaviorClass(status, template, (control4 != null ? control4 : null).getDeviceType()), currentActiveMode);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
    }
}
