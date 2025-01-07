package com.android.systemui.notifications.ui.composable;

import com.android.compose.animation.scene.OverlayKey;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.notifications.ui.viewmodel.NotificationsShadeOverlayActionsViewModel;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.ui.composable.Overlay;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$56;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$57;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationsShadeOverlay implements Overlay {
    public final Lazy actionsViewModel$delegate;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$56 actionsViewModelFactory;
    public final BatteryMeterViewController.Factory batteryMeterViewControllerFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$57 contentViewModelFactory;
    public final OverlayKey key;
    public final NotificationsShadeSessionModule$provideShadeSession$1 shadeSession;
    public final dagger.Lazy stackScrollView;
    public final StatusBarIconController statusBarIconController;
    public final TintedIconManager.Factory tintedIconManagerFactory;
    public final StateFlow userActions;

    public NotificationsShadeOverlay(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$56 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$56, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$57 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$57, TintedIconManager.Factory factory, BatteryMeterViewController.Factory factory2, StatusBarIconController statusBarIconController, NotificationsShadeSessionModule$provideShadeSession$1 notificationsShadeSessionModule$provideShadeSession$1, dagger.Lazy lazy) {
        this.actionsViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$56;
        OverlayKey overlayKey = Overlays.NotificationsShade;
        ReadonlyStateFlow readonlyStateFlow = ((NotificationsShadeOverlayActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.notifications.ui.composable.NotificationsShadeOverlay$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                getClass();
                return new NotificationsShadeOverlayActionsViewModel();
            }
        }).getValue()).actions;
    }
}
