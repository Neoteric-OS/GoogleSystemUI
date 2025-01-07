package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.shade.NotificationPanelView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitShadeMediaSection extends KeyguardSection {
    public final Context context;
    public final KeyguardMediaController keyguardMediaController;
    public final NotificationPanelView notificationPanelView;

    public SplitShadeMediaSection(Context context, NotificationPanelView notificationPanelView, KeyguardMediaController keyguardMediaController) {
        this.context = context;
        this.notificationPanelView = notificationPanelView;
        this.keyguardMediaController = keyguardMediaController;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        View findViewById = notificationPanelView.findViewById(R.id.status_view_media_container);
        if (findViewById != null) {
            notificationPanelView.removeView(findViewById);
        }
        FrameLayout frameLayout = new FrameLayout(this.context, null);
        frameLayout.setId(R.id.status_view_media_container);
        int dimensionPixelSize = frameLayout.getContext().getResources().getDimensionPixelSize(R.dimen.qs_media_padding);
        int dimensionPixelSize2 = frameLayout.getContext().getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + dimensionPixelSize;
        frameLayout.setPaddingRelative(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
        constraintLayout.addView(frameLayout);
        KeyguardMediaController keyguardMediaController = this.keyguardMediaController;
        keyguardMediaController.splitShadeContainer = frameLayout;
        keyguardMediaController.reattachHostView();
        keyguardMediaController.refreshMediaPosition("attachSplitShadeContainer");
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainWidth(R.id.status_view_media_container, 0);
        constraintSet.constrainHeight(R.id.status_view_media_container, -2);
        constraintSet.connect(R.id.status_view_media_container, 3, R.id.smart_space_barrier_bottom, 4);
        constraintSet.connect(R.id.status_view_media_container, 6, 0, 6);
        constraintSet.connect(R.id.status_view_media_container, 7, R.id.split_shade_guideline, 7);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.status_view_media_container);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
    }
}
