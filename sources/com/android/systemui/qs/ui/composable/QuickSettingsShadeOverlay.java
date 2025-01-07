package com.android.systemui.qs.ui.composable;

import com.android.compose.animation.scene.OverlayKey;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsContainerViewModel;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsShadeOverlayActionsViewModel;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.ui.composable.Overlay;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$44;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$55;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickSettingsShadeOverlay implements Overlay {
    public final Lazy actionsViewModel$delegate;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$44 actionsViewModelFactory;
    public final BatteryMeterViewController.Factory batteryMeterViewControllerFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$55 contentViewModelFactory;
    public final OverlayKey key;
    public final StatusBarIconController statusBarIconController;
    public final TintedIconManager.Factory tintedIconManagerFactory;
    public final StateFlow userActions;

    public QuickSettingsShadeOverlay(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$44 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$44, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$55 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$55, TintedIconManager.Factory factory, BatteryMeterViewController.Factory factory2, StatusBarIconController statusBarIconController) {
        this.actionsViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$44;
        OverlayKey overlayKey = Overlays.QuickSettingsShade;
        ReadonlyStateFlow readonlyStateFlow = ((QuickSettingsShadeOverlayActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsShadeOverlay$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new QuickSettingsShadeOverlayActionsViewModel((QuickSettingsContainerViewModel) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) QuickSettingsShadeOverlay.this.actionsViewModelFactory.this$0.wMComponentImpl).quickSettingsContainerViewModelProvider.get());
            }
        }).getValue()).actions;
    }
}
