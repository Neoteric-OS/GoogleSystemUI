package com.android.systemui.keyguard.ui.view.layout.sections;

import androidx.constraintlayout.widget.ConstraintSet;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitShadeNotificationStackScrollLayoutSection extends NotificationStackScrollLayoutSection {
    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.connect(R.id.nssl_placeholder, 3, 0, 3, this.context.getResources().getDimensionPixelSize(R.dimen.keyguard_split_shade_top_margin));
        constraintSet.connect(R.id.nssl_placeholder, 6, 0, 6);
        constraintSet.connect(R.id.nssl_placeholder, 7, 0, 7);
        constraintSet.createBarrier(R.id.nssl_placeholder_barrier_bottom, 2, 0, R.id.device_entry_icon_view, R.id.lock_icon_view, R.id.ambient_indication_container);
        constraintSet.connect(R.id.nssl_placeholder, 4, R.id.nssl_placeholder_barrier_bottom, 3);
    }
}
