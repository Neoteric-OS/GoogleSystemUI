package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder;
import com.android.systemui.keyguard.ui.view.KeyguardIndicationArea;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultIndicationAreaSection extends KeyguardSection {
    public final Context context;
    public DisposableHandles indicationAreaHandle;
    public final KeyguardIndicationController indicationController;
    public final KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel;

    public DefaultIndicationAreaSection(Context context, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, KeyguardIndicationController keyguardIndicationController) {
        this.context = context;
        this.keyguardIndicationAreaViewModel = keyguardIndicationAreaViewModel;
        this.indicationController = keyguardIndicationController;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        constraintLayout.addView(new KeyguardIndicationArea(this.context));
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainWidth(R.id.keyguard_indication_area, -1);
        constraintSet.constrainHeight(R.id.keyguard_indication_area, -2);
        constraintSet.connect(R.id.keyguard_indication_area, 4, 0, 4, this.context.getResources().getDimensionPixelSize(R.dimen.keyguard_indication_margin_bottom));
        constraintSet.connect(R.id.keyguard_indication_area, 6, 0, 6);
        constraintSet.connect(R.id.keyguard_indication_area, 7, 0, 7);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        this.indicationAreaHandle = KeyguardIndicationAreaBinder.bind((ViewGroup) constraintLayout.requireViewById(R.id.keyguard_indication_area), this.keyguardIndicationAreaViewModel, this.indicationController);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        DisposableHandles disposableHandles = this.indicationAreaHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
        ExtensionsKt.removeView(constraintLayout, R.id.keyguard_indication_area);
    }
}
