package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerStatusBarViewBinder {
    public final ConfigurationState configuration;
    public final StatusBarIconViewBindingFailureTracker failureTracker;
    public final SystemBarUtilsState systemBarUtilsState;
    public final NotificationIconContainerStatusBarViewModel viewModel;
    public final StatusBarNotificationIconViewStore viewStore;

    public NotificationIconContainerStatusBarViewBinder(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, StatusBarNotificationIconViewStore statusBarNotificationIconViewStore) {
        this.viewModel = notificationIconContainerStatusBarViewModel;
        this.configuration = configurationState;
        this.systemBarUtilsState = systemBarUtilsState;
        this.failureTracker = statusBarIconViewBindingFailureTracker;
        this.viewStore = statusBarNotificationIconViewStore;
    }

    public final RepeatWhenAttachedKt$repeatWhenAttached$1 bindWhileAttached(NotificationIconContainer notificationIconContainer) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("NICStatusBar#bindWhileAttached");
        }
        try {
            NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1 notificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1 = new NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1(notificationIconContainer, this, null);
            CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
            return RepeatWhenAttachedKt.repeatWhenAttached(notificationIconContainer, EmptyCoroutineContext.INSTANCE, notificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
