package com.android.systemui.keyguard.dagger;

import android.app.IActivityTaskManager;
import android.app.trust.TrustManager;
import android.content.Context;
import android.os.PowerManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardDisplayManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.WindowManagerOcclusionManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl;
import com.android.wm.shell.keyguard.KeyguardTransitions;
import dagger.Lazy;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardModule_NewKeyguardViewMediatorFactory implements Provider {
    public static KeyguardViewMediator newKeyguardViewMediator(IActivityTaskManager iActivityTaskManager, TrustManager trustManager, Context context, PowerManager powerManager, InteractionJankMonitor interactionJankMonitor, UiEventLogger uiEventLogger, IStatusBarService iStatusBarService, LockPatternUtils lockPatternUtils, KeyguardDisplayManager keyguardDisplayManager, KeyguardUpdateMonitor keyguardUpdateMonitor, BroadcastDispatcher broadcastDispatcher, FalsingCollector falsingCollector, DreamOverlayStateController dreamOverlayStateController, DumpManager dumpManager, SystemPropertiesHelper systemPropertiesHelper, DismissCallbackRegistry dismissCallbackRegistry, WindowManagerOcclusionManager windowManagerOcclusionManager, KeyguardInteractor keyguardInteractor, SessionTracker sessionTracker, NavigationModeController navigationModeController, ProcessWrapper processWrapper, UserTracker userTracker, SysuiStatusBarStateController sysuiStatusBarStateController, DozeParameters dozeParameters, ScreenOffAnimationController screenOffAnimationController, KeyguardStateController keyguardStateController, UserSwitcherController userSwitcherController, SelectedUserInteractor selectedUserInteractor, DeviceConfigProxy deviceConfigProxy, JavaAdapter javaAdapter, SecureSettings secureSettings, SystemSettingsImpl systemSettingsImpl, SystemClock systemClock, WallpaperRepositoryImpl wallpaperRepositoryImpl, KeyguardTransitions keyguardTransitions, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Executor executor) {
        return new KeyguardViewMediator(iActivityTaskManager, trustManager, context, powerManager, interactionJankMonitor, uiEventLogger, iStatusBarService, lockPatternUtils, keyguardDisplayManager, keyguardUpdateMonitor, broadcastDispatcher, falsingCollector, dreamOverlayStateController, dumpManager, systemPropertiesHelper, dismissCallbackRegistry, windowManagerOcclusionManager, keyguardInteractor, sessionTracker, navigationModeController, processWrapper, userTracker, sysuiStatusBarStateController, dozeParameters, screenOffAnimationController, keyguardStateController, userSwitcherController, selectedUserInteractor, deviceConfigProxy, javaAdapter, secureSettings, systemSettingsImpl, systemClock, wallpaperRepositoryImpl, keyguardTransitions, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, executor);
    }
}
