package com.android.systemui.keyguard.ui.view.layout.sections;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSliceViewSection extends KeyguardSection {
    public final LockscreenSmartspaceController smartspaceController;

    public KeyguardSliceViewSection(LockscreenSmartspaceController lockscreenSmartspaceController) {
        this.smartspaceController = lockscreenSmartspaceController;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        View findViewById;
        if (this.smartspaceController.isEnabled || (findViewById = constraintLayout.findViewById(R.id.keyguard_slice_view)) == null) {
            return;
        }
        ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        constraintLayout.addView(findViewById);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        if (this.smartspaceController.isEnabled) {
            return;
        }
        constraintSet.connect(R.id.keyguard_slice_view, 6, 0, 6);
        constraintSet.connect(R.id.keyguard_slice_view, 7, 0, 7);
        constraintSet.constrainHeight(R.id.keyguard_slice_view, -2);
        constraintSet.connect(R.id.keyguard_slice_view, 3, R.id.lockscreen_clock_view, 4);
        constraintSet.createBarrier(R.id.smart_space_barrier_bottom, 3, 0, R.id.keyguard_slice_view);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        if (this.smartspaceController.isEnabled) {
            return;
        }
        ExtensionsKt.removeView(constraintLayout, R.id.keyguard_slice_view);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
    }
}
