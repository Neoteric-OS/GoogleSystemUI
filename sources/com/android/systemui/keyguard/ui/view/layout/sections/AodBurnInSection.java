package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.wm.shell.R;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodBurnInSection extends KeyguardSection {
    public AodBurnInLayer burnInLayer;
    public final KeyguardClockViewModel clockViewModel;
    public final Context context;
    public final Lazy emptyView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInSection$emptyView$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            View view = new View(AodBurnInSection.this.context, null);
            view.setId(R.id.burn_in_layer_empty_view);
            view.setVisibility(8);
            return view;
        }
    });
    public final KeyguardRootView rootView;

    public AodBurnInSection(Context context, KeyguardRootView keyguardRootView, KeyguardClockViewModel keyguardClockViewModel) {
        this.context = context;
        this.rootView = keyguardRootView;
        this.clockViewModel = keyguardClockViewModel;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        Lazy lazy = this.emptyView$delegate;
        constraintLayout.addView((View) lazy.getValue());
        AodBurnInLayer aodBurnInLayer = new AodBurnInLayer(this.context);
        aodBurnInLayer.setId(R.id.burn_in_layer);
        aodBurnInLayer.getRootView().getViewTreeObserver().addOnPreDrawListener(aodBurnInLayer._predrawListener);
        aodBurnInLayer.addView((View) lazy.getValue());
        this.burnInLayer = aodBurnInLayer;
        constraintLayout.addView(aodBurnInLayer);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainHeight(R.id.burn_in_layer_empty_view, 1);
        constraintSet.constrainWidth(R.id.burn_in_layer_empty_view, 0);
        constraintSet.connect(R.id.burn_in_layer_empty_view, 4, 0, 4);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        AodBurnInLayer aodBurnInLayer = this.burnInLayer;
        if (aodBurnInLayer == null) {
            aodBurnInLayer = null;
        }
        this.clockViewModel.burnInLayer = aodBurnInLayer;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        AodBurnInLayer aodBurnInLayer = this.burnInLayer;
        if (aodBurnInLayer == null) {
            aodBurnInLayer = null;
        }
        aodBurnInLayer.getClass();
        this.rootView.getViewTreeObserver().removeOnPreDrawListener(aodBurnInLayer._predrawListener);
        ExtensionsKt.removeView(constraintLayout, R.id.burn_in_layer);
    }
}
