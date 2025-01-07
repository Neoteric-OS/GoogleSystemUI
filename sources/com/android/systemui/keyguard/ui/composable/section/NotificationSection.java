package com.android.systemui.keyguard.ui.composable.section;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationSection {
    public final AodBurnInViewModel aodBurnInViewModel;
    public final KeyguardClockInteractor clockInteractor;
    public final ConfigurationState configurationState;
    public final StatusBarIconViewBindingFailureTracker iconBindingFailureTracker;
    public final KeyguardRootViewModel keyguardRootViewModel;
    public final AlwaysOnDisplayNotificationIconViewStore nicAodIconViewStore;
    public final NotificationIconContainerAlwaysOnDisplayViewModel nicAodViewModel;
    public final Lazy stackScrollView;
    public final SystemBarUtilsState systemBarUtilsState;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 viewModelFactory;

    public NotificationSection(Lazy lazy, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34, AodBurnInViewModel aodBurnInViewModel, SharedNotificationContainer sharedNotificationContainer, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, NotificationStackScrollLayout notificationStackScrollLayout, SharedNotificationContainerBinder sharedNotificationContainerBinder, KeyguardRootViewModel keyguardRootViewModel, ConfigurationState configurationState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore, SystemBarUtilsState systemBarUtilsState, KeyguardClockInteractor keyguardClockInteractor) {
        if (!Intrinsics.areEqual(notificationStackScrollLayout.getParent(), sharedNotificationContainer)) {
            ViewParent parent = notificationStackScrollLayout.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(notificationStackScrollLayout);
            }
            sharedNotificationContainer.addView(notificationStackScrollLayout);
        }
        sharedNotificationContainerBinder.bind(sharedNotificationContainer, sharedNotificationContainerViewModel);
    }
}
