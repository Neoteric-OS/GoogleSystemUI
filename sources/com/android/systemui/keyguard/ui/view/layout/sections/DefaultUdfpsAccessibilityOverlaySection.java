package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.deviceentry.ui.binder.UdfpsAccessibilityOverlayBinder;
import com.android.systemui.deviceentry.ui.view.UdfpsAccessibilityOverlay;
import com.android.systemui.deviceentry.ui.viewmodel.DeviceEntryUdfpsAccessibilityOverlayViewModel;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultUdfpsAccessibilityOverlaySection extends KeyguardSection {
    public final Context context;
    public final DeviceEntryUdfpsAccessibilityOverlayViewModel viewModel;

    public DefaultUdfpsAccessibilityOverlaySection(Context context, DeviceEntryUdfpsAccessibilityOverlayViewModel deviceEntryUdfpsAccessibilityOverlayViewModel) {
        this.context = context;
        this.viewModel = deviceEntryUdfpsAccessibilityOverlayViewModel;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        UdfpsAccessibilityOverlay udfpsAccessibilityOverlay = new UdfpsAccessibilityOverlay(this.context);
        udfpsAccessibilityOverlay.setId(R.id.udfps_accessibility_overlay);
        constraintLayout.addView(udfpsAccessibilityOverlay);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.connect(R.id.udfps_accessibility_overlay, 6, 0, 6);
        constraintSet.connect(R.id.udfps_accessibility_overlay, 7, 0, 7);
        ConstraintSet.Layout layout = constraintSet.get(R.id.udfps_accessibility_overlay_top_guideline).layout;
        layout.mIsGuideline = true;
        layout.orientation = 0;
        constraintSet.setGuidelinePercent(R.id.udfps_accessibility_overlay_top_guideline, 0.5f);
        constraintSet.connect(R.id.udfps_accessibility_overlay, 3, R.id.udfps_accessibility_overlay_top_guideline, 4);
        constraintSet.connect(R.id.udfps_accessibility_overlay, 4, R.id.keyguard_indication_area, 3);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        View findViewById = constraintLayout.findViewById(R.id.udfps_accessibility_overlay);
        Intrinsics.checkNotNull(findViewById);
        UdfpsAccessibilityOverlayBinder.bind((UdfpsAccessibilityOverlay) findViewById, this.viewModel);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.udfps_accessibility_overlay);
    }
}
