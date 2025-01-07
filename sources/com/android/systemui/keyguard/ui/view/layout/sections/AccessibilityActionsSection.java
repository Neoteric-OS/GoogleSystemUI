package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.AccessibilityActionsViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityActionsSection extends KeyguardSection {
    public RepeatWhenAttachedKt$repeatWhenAttached$1 accessibilityActionsViewHandle;
    public final AccessibilityActionsViewModel accessibilityActionsViewModel;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final Context context;

    public AccessibilityActionsSection(Context context, CommunalSettingsInteractor communalSettingsInteractor, AccessibilityActionsViewModel accessibilityActionsViewModel) {
        this.context = context;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.accessibilityActionsViewModel = accessibilityActionsViewModel;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            View view = new View(constraintLayout.getContext());
            view.setId(R.id.accessibility_actions_view);
            constraintLayout.addView(view);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.connect(R.id.accessibility_actions_view, 3, 0, 3, Utils.getStatusBarHeaderHeightKeyguard(this.context));
        constraintSet.connect(R.id.accessibility_actions_view, 4, 0, 4);
        constraintSet.connect(R.id.accessibility_actions_view, 6, 0, 6);
        constraintSet.connect(R.id.accessibility_actions_view, 7, 0, 7);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            this.accessibilityActionsViewHandle = AccessibilityActionsViewBinder.bind(constraintLayout.requireViewById(R.id.accessibility_actions_view), this.accessibilityActionsViewModel);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.accessibilityActionsViewHandle;
        if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
        }
        this.accessibilityActionsViewHandle = null;
        ExtensionsKt.removeView(constraintLayout, R.id.accessibility_actions_view);
    }
}
