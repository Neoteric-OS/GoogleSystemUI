package com.android.systemui.controls.ui;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ThumbnailTemplate;
import android.util.TypedValue;
import android.view.View;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ThumbnailBehavior implements Behavior {
    public final CanUseIconPredicate canUseIconPredicate;
    public Control control;
    public ControlViewHolder cvh;
    public int shadowColor;
    public float shadowOffsetX;
    public float shadowOffsetY;
    public float shadowRadius;
    public ThumbnailTemplate template;

    public ThumbnailBehavior(int i) {
        this.canUseIconPredicate = new CanUseIconPredicate(i);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, final int i) {
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
        ThumbnailTemplate thumbnailTemplate = controlTemplate instanceof ThumbnailTemplate ? (ThumbnailTemplate) controlTemplate : null;
        if (thumbnailTemplate == null) {
            Control control3 = this.control;
            if (control3 == null) {
                control3 = null;
            }
            thumbnailTemplate = (ThumbnailTemplate) ((TemperatureControlTemplate) control3.getControlTemplate()).getTemplate();
        }
        this.template = thumbnailTemplate;
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        final ClipDrawable clipDrawable = (ClipDrawable) ((LayerDrawable) controlViewHolder2.layout.getBackground()).findDrawableByLayerId(R.id.clip_layer);
        ThumbnailTemplate thumbnailTemplate2 = this.template;
        if (thumbnailTemplate2 == null) {
            thumbnailTemplate2 = null;
        }
        clipDrawable.setLevel(thumbnailTemplate2.isActive() ? 10000 : 0);
        ThumbnailTemplate thumbnailTemplate3 = this.template;
        if (thumbnailTemplate3 == null) {
            thumbnailTemplate3 = null;
        }
        if (thumbnailTemplate3.isActive()) {
            ControlViewHolder controlViewHolder3 = this.cvh;
            if (controlViewHolder3 == null) {
                controlViewHolder3 = null;
            }
            controlViewHolder3.title.setVisibility(4);
            ControlViewHolder controlViewHolder4 = this.cvh;
            if (controlViewHolder4 == null) {
                controlViewHolder4 = null;
            }
            controlViewHolder4.subtitle.setVisibility(4);
            ControlViewHolder controlViewHolder5 = this.cvh;
            if (controlViewHolder5 == null) {
                controlViewHolder5 = null;
            }
            controlViewHolder5.status.setShadowLayer(this.shadowOffsetX, this.shadowOffsetY, this.shadowRadius, this.shadowColor);
            ControlViewHolder controlViewHolder6 = this.cvh;
            if (controlViewHolder6 == null) {
                controlViewHolder6 = null;
            }
            ((ExecutorImpl) controlViewHolder6.bgExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.ui.ThumbnailBehavior$bind$1
                /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void run() {
                    /*
                        r5 = this;
                        com.android.systemui.controls.ui.ThumbnailBehavior r0 = com.android.systemui.controls.ui.ThumbnailBehavior.this
                        android.service.controls.templates.ThumbnailTemplate r0 = r0.template
                        r1 = 0
                        if (r0 == 0) goto L8
                        goto L9
                    L8:
                        r0 = r1
                    L9:
                        android.graphics.drawable.Icon r0 = r0.getThumbnail()
                        if (r0 == 0) goto L32
                        com.android.systemui.controls.ui.ThumbnailBehavior r2 = com.android.systemui.controls.ui.ThumbnailBehavior.this
                        com.android.systemui.controls.ui.CanUseIconPredicate r2 = r2.canUseIconPredicate
                        java.lang.Object r2 = r2.invoke(r0)
                        java.lang.Boolean r2 = (java.lang.Boolean) r2
                        boolean r2 = r2.booleanValue()
                        if (r2 == 0) goto L20
                        goto L21
                    L20:
                        r0 = r1
                    L21:
                        if (r0 == 0) goto L32
                        com.android.systemui.controls.ui.ThumbnailBehavior r2 = com.android.systemui.controls.ui.ThumbnailBehavior.this
                        com.android.systemui.controls.ui.ControlViewHolder r2 = r2.cvh
                        if (r2 == 0) goto L2a
                        goto L2b
                    L2a:
                        r2 = r1
                    L2b:
                        android.content.Context r2 = r2.context
                        android.graphics.drawable.Drawable r0 = r0.loadDrawable(r2)
                        goto L33
                    L32:
                        r0 = r1
                    L33:
                        com.android.systemui.controls.ui.ThumbnailBehavior r2 = com.android.systemui.controls.ui.ThumbnailBehavior.this
                        com.android.systemui.controls.ui.ControlViewHolder r3 = r2.cvh
                        if (r3 == 0) goto L3a
                        r1 = r3
                    L3a:
                        com.android.systemui.util.concurrency.DelayableExecutor r1 = r1.uiExecutor
                        com.android.systemui.controls.ui.ThumbnailBehavior$bind$1$1 r3 = new com.android.systemui.controls.ui.ThumbnailBehavior$bind$1$1
                        android.graphics.drawable.ClipDrawable r4 = r2
                        int r5 = r3
                        r3.<init>()
                        com.android.systemui.util.concurrency.ExecutorImpl r1 = (com.android.systemui.util.concurrency.ExecutorImpl) r1
                        r1.execute(r3)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.ui.ThumbnailBehavior$bind$1.run():void");
                }
            });
        } else {
            ControlViewHolder controlViewHolder7 = this.cvh;
            if (controlViewHolder7 == null) {
                controlViewHolder7 = null;
            }
            controlViewHolder7.title.setVisibility(0);
            ControlViewHolder controlViewHolder8 = this.cvh;
            if (controlViewHolder8 == null) {
                controlViewHolder8 = null;
            }
            controlViewHolder8.subtitle.setVisibility(0);
            ControlViewHolder controlViewHolder9 = this.cvh;
            if (controlViewHolder9 == null) {
                controlViewHolder9 = null;
            }
            controlViewHolder9.status.setShadowLayer(0.0f, 0.0f, 0.0f, this.shadowColor);
        }
        ControlViewHolder controlViewHolder10 = this.cvh;
        if (controlViewHolder10 == null) {
            controlViewHolder10 = null;
        }
        ThumbnailTemplate thumbnailTemplate4 = this.template;
        controlViewHolder10.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, (thumbnailTemplate4 != null ? thumbnailTemplate4 : null).isActive(), true);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(final ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
        TypedValue typedValue = new TypedValue();
        controlViewHolder.context.getResources().getValue(R.dimen.controls_thumbnail_shadow_x, typedValue, true);
        this.shadowOffsetX = typedValue.getFloat();
        controlViewHolder.context.getResources().getValue(R.dimen.controls_thumbnail_shadow_y, typedValue, true);
        this.shadowOffsetY = typedValue.getFloat();
        controlViewHolder.context.getResources().getValue(R.dimen.controls_thumbnail_shadow_radius, typedValue, true);
        this.shadowRadius = typedValue.getFloat();
        this.shadowColor = controlViewHolder.context.getResources().getColor(R.color.control_thumbnail_shadow_color);
        controlViewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ThumbnailBehavior$initialize$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlViewHolder controlViewHolder2 = ControlViewHolder.this;
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = controlViewHolder2.controlActionCoordinator;
                ThumbnailTemplate thumbnailTemplate = this.template;
                if (thumbnailTemplate == null) {
                    thumbnailTemplate = null;
                }
                String templateId = thumbnailTemplate.getTemplateId();
                Control control = this.control;
                controlActionCoordinatorImpl.touch(controlViewHolder2, templateId, control != null ? control : null);
            }
        });
    }
}
