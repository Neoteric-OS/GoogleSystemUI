package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerViewBinder {
    public static final NotificationIconContainerViewBinder INSTANCE = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface IconViewStore {
        StatusBarIconView iconView(String str);
    }

    public static final RepeatWhenAttachedKt$repeatWhenAttached$1 bindWhileAttached(NotificationIconContainer notificationIconContainer, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, IconViewStore iconViewStore) {
        NotificationIconContainerViewBinder$bindWhileAttached$1 notificationIconContainerViewBinder$bindWhileAttached$1 = new NotificationIconContainerViewBinder$bindWhileAttached$1(configurationState, iconViewStore, statusBarIconViewBindingFailureTracker, notificationIconContainerAlwaysOnDisplayViewModel, notificationIconContainer, systemBarUtilsState, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        return RepeatWhenAttachedKt.repeatWhenAttached(notificationIconContainer, EmptyCoroutineContext.INSTANCE, notificationIconContainerViewBinder$bindWhileAttached$1);
    }
}
