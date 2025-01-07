package com.android.systemui.dagger;

import android.app.INotificationManager;
import android.content.Context;
import android.service.dreams.IDreamManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.model.SysUiState;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.Bubbles;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SystemUIModule_ProvideBubblesManagerFactory implements Provider {
    public static Optional provideBubblesManager(Context context, Optional optional, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, ShadeController shadeController, IStatusBarService iStatusBarService, INotificationManager iNotificationManager, IDreamManager iDreamManager, NotificationVisibilityProvider notificationVisibilityProvider, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, ZenModeController zenModeController, NotificationLockscreenUserManager notificationLockscreenUserManager, SensitiveNotificationProtectionController sensitiveNotificationProtectionController, CommonNotifCollection commonNotifCollection, NotifPipeline notifPipeline, SysUiState sysUiState, FeatureFlags featureFlags, NotifPipelineFlags notifPipelineFlags, Executor executor, Executor executor2) {
        Optional ofNullable = Optional.ofNullable(optional.isPresent() ? new BubblesManager(context, (Bubbles) optional.get(), notificationShadeWindowController, keyguardStateController, shadeController, iStatusBarService, iNotificationManager, iDreamManager, notificationVisibilityProvider, visualInterruptionDecisionProvider, zenModeController, notificationLockscreenUserManager, sensitiveNotificationProtectionController, commonNotifCollection, notifPipeline, sysUiState, featureFlags, notifPipelineFlags, executor, executor2) : null);
        Preconditions.checkNotNullFromProvides(ofNullable);
        return ofNullable;
    }
}
