package com.google.android.systemui.dagger;

import android.app.AlarmManager;
import android.content.res.Resources;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarView;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;
    public final Object view;

    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, Object obj) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.view = obj;
    }

    public AmbientStatusBarViewController getController() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        Resources m977$$Nest$mmainResources = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl);
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
        AlarmManager alarmManager = (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        return new AmbientStatusBarViewController((AmbientStatusBarView) this.view, m977$$Nest$mmainResources, executor, alarmManager, (NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil(), (IndividualSensorPrivacyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideIndividualSensorPrivacyControllerProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesDreamOverlayNotificationCountProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get(), (StatusBarWindowStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowStateControllerProvider.get(), (DreamOverlayStatusBarItemsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStatusBarItemsProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (WifiInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wifiInteractorImplProvider.get(), (PrivacyItemController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.privacyItemControllerProvider.get(), (CommunalSceneInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalSceneInteractorProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDreamLogBufferProvider.get());
    }
}
