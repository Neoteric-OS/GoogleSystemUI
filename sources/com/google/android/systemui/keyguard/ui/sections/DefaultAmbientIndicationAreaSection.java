package com.google.android.systemui.keyguard.ui.sections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder;
import com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$1;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultAmbientIndicationAreaSection extends KeyguardSection {
    public final ActivityStarter activityStarter;
    public KeyguardAmbientIndicationAreaViewBinder$bind$1 ambientIndicationAreaHandle;
    public final AodAlphaViewModel aodAlphaViewModel;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 delayedWakeLockFactory;
    public final KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final PowerInteractor powerInteractor;
    public final Lazy shadeViewController;

    public DefaultAmbientIndicationAreaSection(KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AodAlphaViewModel aodAlphaViewModel, Lazy lazy, PowerInteractor powerInteractor, ActivityStarter activityStarter, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardAmbientIndicationViewModel = keyguardAmbientIndicationViewModel;
        this.aodAlphaViewModel = aodAlphaViewModel;
        this.shadeViewController = lazy;
        this.powerInteractor = powerInteractor;
        this.activityStarter = activityStarter;
        this.delayedWakeLockFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        constraintLayout.addView(LayoutInflater.from(constraintLayout.getContext()).inflate(R.layout.ambient_indication, (ViewGroup) constraintLayout, false));
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainWidth(R.id.ambient_indication_container, -1);
        if (!this.keyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            constraintSet.constrainHeight(R.id.ambient_indication_container, -2);
            constraintSet.connect(R.id.ambient_indication_container, 4, R.id.device_entry_icon_view, 3);
            constraintSet.connect(R.id.ambient_indication_container, 6, 0, 6);
            constraintSet.connect(R.id.ambient_indication_container, 7, 0, 7);
            return;
        }
        constraintSet.constrainHeight(R.id.ambient_indication_container, 0);
        constraintSet.connect(R.id.ambient_indication_container, 3, R.id.device_entry_icon_view, 4);
        constraintSet.connect(R.id.ambient_indication_container, 4, R.id.keyguard_indication_area, 3);
        constraintSet.connect(R.id.ambient_indication_container, 6, 0, 6);
        constraintSet.connect(R.id.ambient_indication_container, 7, 0, 7);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        ShadeViewController shadeViewController = (ShadeViewController) this.shadeViewController.get();
        this.ambientIndicationAreaHandle = KeyguardAmbientIndicationAreaViewBinder.bind(constraintLayout, this.keyguardAmbientIndicationViewModel, this.aodAlphaViewModel, shadeViewController, this.powerInteractor, this.keyguardUpdateMonitor, this.activityStarter, this.delayedWakeLockFactory);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1;
        KeyguardAmbientIndicationAreaViewBinder$bind$1 keyguardAmbientIndicationAreaViewBinder$bind$1 = this.ambientIndicationAreaHandle;
        if (keyguardAmbientIndicationAreaViewBinder$bind$1 != null && (repeatWhenAttachedKt$repeatWhenAttached$1 = keyguardAmbientIndicationAreaViewBinder$bind$1.$disposableHandle) != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
        }
        View findViewById = constraintLayout.findViewById(R.id.ambient_indication_container);
        if (findViewById != null) {
            constraintLayout.removeView(findViewById);
        }
    }
}
