package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Resources;
import android.os.PowerManager;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.emergency.EmergencyGestureModule$emergencyGestureIntentFactory$1;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.QSHost;
import com.android.systemui.recents.ScreenPinningRequest;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeHeaderController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesCommandQueueCallbacks_Factory implements Provider {
    public static CentralSurfacesCommandQueueCallbacks newInstance(CentralSurfaces centralSurfaces, QuickSettingsController quickSettingsController, Context context, Resources resources, ScreenPinningRequest screenPinningRequest, ShadeController shadeController, CommandQueue commandQueue, PanelExpansionInteractor panelExpansionInteractor, Lazy lazy, ShadeHeaderController shadeHeaderController, RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, MetricsLogger metricsLogger, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, WakefulnessLifecycle wakefulnessLifecycle, DeviceProvisionedController deviceProvisionedController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, AssistManagerGoogle assistManagerGoogle, DozeServiceHost dozeServiceHost, NotificationStackScrollLayoutController notificationStackScrollLayoutController, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, PowerManager powerManager, Optional optional, int i, Lazy lazy2, UserTracker userTracker, QSHost qSHost, ActivityStarter activityStarter, KeyguardInteractor keyguardInteractor, EmergencyGestureModule$emergencyGestureIntentFactory$1 emergencyGestureModule$emergencyGestureIntentFactory$1) {
        return new CentralSurfacesCommandQueueCallbacks(centralSurfaces, quickSettingsController, context, resources, screenPinningRequest, shadeController, commandQueue, panelExpansionInteractor, lazy, shadeHeaderController, remoteInputQuickSettingsDisabler, metricsLogger, keyguardUpdateMonitor, keyguardStateController, headsUpManager, wakefulnessLifecycle, deviceProvisionedController, statusBarKeyguardViewManager, assistManagerGoogle, dozeServiceHost, notificationStackScrollLayoutController, statusBarHideIconsForBouncerManager, powerManager, optional, i, lazy2, userTracker, qSHost, activityStarter, keyguardInteractor, emergencyGestureModule$emergencyGestureIntentFactory$1);
    }
}
