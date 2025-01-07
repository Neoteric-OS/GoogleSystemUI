package com.android.systemui.qs.ui.composable;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.lifecycle.ExclusiveActivatable;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.notifications.ui.composable.NotificationsShadeSessionModule$provideShadeSession$1;
import com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsUserActionsViewModel;
import com.android.systemui.scene.domain.interactor.SceneBackInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.ui.composable.Scene;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$38;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$39;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickSettingsScene extends ExclusiveActivatable implements Scene {
    public final Lazy actionsViewModel$delegate;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$38 actionsViewModelFactory;
    public final BatteryMeterViewController.Factory batteryMeterViewControllerFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$39 contentViewModelFactory;
    public final SceneKey key;
    public final MediaCarouselController mediaCarouselController;
    public final MediaHost mediaHost;
    public final dagger.Lazy notificationStackScrollView;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 notificationsPlaceholderViewModelFactory;
    public final NotificationsShadeSessionModule$provideShadeSession$1 shadeSession;
    public final StatusBarIconController statusBarIconController;
    public final TintedIconManager.Factory tintedIconManagerFactory;
    public final StateFlow userActions;

    public QuickSettingsScene(NotificationsShadeSessionModule$provideShadeSession$1 notificationsShadeSessionModule$provideShadeSession$1, dagger.Lazy lazy, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$38 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$38, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$39 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$39, TintedIconManager.Factory factory, BatteryMeterViewController.Factory factory2, StatusBarIconController statusBarIconController, MediaCarouselController mediaCarouselController, MediaHost mediaHost) {
        this.actionsViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$38;
        SceneKey sceneKey = Scenes.QuickSettings;
        ReadonlyStateFlow readonlyStateFlow = ((QuickSettingsUserActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsScene$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = QuickSettingsScene.this.actionsViewModelFactory.this$0;
                return new QuickSettingsUserActionsViewModel((QSSceneAdapterImpl) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).qSSceneAdapterImplProvider.get(), (SceneBackInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).sceneBackInteractorProvider.get());
            }
        }).getValue()).actions;
    }
}
