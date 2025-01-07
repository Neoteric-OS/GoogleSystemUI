package com.android.systemui.controls.ui;

import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.view.View;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TouchBehavior implements Behavior {
    public Control control;
    public ControlViewHolder cvh;
    public int lastColorOffset;
    public boolean statelessTouch;
    public ControlTemplate template;

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, int i) {
        Control control = controlWithState.control;
        Intrinsics.checkNotNull(control);
        this.control = control;
        this.lastColorOffset = i;
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
        this.template = control2.getControlTemplate();
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        ((LayerDrawable) controlViewHolder2.layout.getBackground()).findDrawableByLayerId(R.id.clip_layer).setLevel(getEnabled$1() ? 10000 : 0);
        ControlViewHolder controlViewHolder3 = this.cvh;
        (controlViewHolder3 != null ? controlViewHolder3 : null).applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, getEnabled$1(), true);
    }

    public final boolean getEnabled$1() {
        return this.lastColorOffset > 0 || this.statelessTouch;
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(final ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
        controlViewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.TouchBehavior$initialize$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlViewHolder controlViewHolder2 = ControlViewHolder.this;
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = controlViewHolder2.controlActionCoordinator;
                ControlTemplate controlTemplate = this.template;
                if (controlTemplate == null) {
                    controlTemplate = null;
                }
                String templateId = controlTemplate.getTemplateId();
                Control control = this.control;
                if (control == null) {
                    control = null;
                }
                controlActionCoordinatorImpl.touch(controlViewHolder2, templateId, control);
                TouchBehavior touchBehavior = this;
                ControlTemplate controlTemplate2 = touchBehavior.template;
                if ((controlTemplate2 != null ? controlTemplate2 : null) instanceof StatelessTemplate) {
                    touchBehavior.statelessTouch = true;
                    ControlViewHolder.this.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(this.lastColorOffset, touchBehavior.getEnabled$1(), true);
                    final ControlViewHolder controlViewHolder3 = ControlViewHolder.this;
                    DelayableExecutor delayableExecutor = controlViewHolder3.uiExecutor;
                    final TouchBehavior touchBehavior2 = this;
                    delayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.controls.ui.TouchBehavior$initialize$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TouchBehavior touchBehavior3 = touchBehavior2;
                            touchBehavior3.statelessTouch = false;
                            ControlViewHolder controlViewHolder4 = controlViewHolder3;
                            boolean enabled$1 = touchBehavior3.getEnabled$1();
                            int i = touchBehavior2.lastColorOffset;
                            Set set = ControlViewHolder.FORCE_PANEL_DEVICES;
                            controlViewHolder4.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, enabled$1, true);
                        }
                    }, 3000L);
                }
            }
        });
    }
}
