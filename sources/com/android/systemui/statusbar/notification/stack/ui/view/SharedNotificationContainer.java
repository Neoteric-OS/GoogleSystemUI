package com.android.systemui.statusbar.notification.stack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainer extends ConstraintLayout {
    public final ConstraintSet baseConstraintSet;

    public SharedNotificationContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ConstraintSet constraintSet = new ConstraintSet();
        this.baseConstraintSet = constraintSet;
        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
        int i = constraintWidgetContainer.mOptimizationLevel | 64;
        this.mOptimizationLevel = i;
        constraintWidgetContainer.mOptimizationLevel = i;
        LinearSystem.USE_DEPENDENCY_ORDERING = constraintWidgetContainer.optimizeFor(512);
        ConstraintSet.Layout layout = constraintSet.get(R.id.nssl_guideline).layout;
        layout.mIsGuideline = true;
        layout.orientation = 1;
        constraintSet.setGuidelinePercent(R.id.nssl_guideline, 0.5f);
        constraintSet.applyTo(this);
    }
}
