package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerAlwaysOnDisplayViewBinder {
    public final ConfigurationState configuration;
    public final StatusBarIconViewBindingFailureTracker failureTracker;
    public final KeyguardRootViewModel keyguardRootViewModel;
    public final ScreenOffAnimationController screenOffAnimationController;
    public final SystemBarUtilsState systemBarUtilsState;
    public final NotificationIconContainerAlwaysOnDisplayViewModel viewModel;
    public final AlwaysOnDisplayNotificationIconViewStore viewStore;

    public NotificationIconContainerAlwaysOnDisplayViewBinder(NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, KeyguardRootViewModel keyguardRootViewModel, ConfigurationState configurationState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, ScreenOffAnimationController screenOffAnimationController, SystemBarUtilsState systemBarUtilsState, AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore) {
    }
}
