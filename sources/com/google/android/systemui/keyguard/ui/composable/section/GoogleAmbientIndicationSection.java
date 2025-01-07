package com.google.android.systemui.keyguard.ui.composable.section;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GoogleAmbientIndicationSection {
    public final ActivityStarter activityStarter;
    public final AodAlphaViewModel aodAlphaViewModel;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 delayedWakeLockFactory;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final PowerInteractor powerInteractor;
    public final ShadeViewController shadeViewController;
    public final KeyguardAmbientIndicationViewModel viewModel;

    public GoogleAmbientIndicationSection(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AodAlphaViewModel aodAlphaViewModel, ShadeViewController shadeViewController, PowerInteractor powerInteractor, KeyguardUpdateMonitor keyguardUpdateMonitor, ActivityStarter activityStarter, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) {
        this.activityStarter = activityStarter;
    }
}
