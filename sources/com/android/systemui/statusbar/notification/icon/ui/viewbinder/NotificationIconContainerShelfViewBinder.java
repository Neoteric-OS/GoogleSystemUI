package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerShelfViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import java.util.Collection;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerShelfViewBinder {
    public final ConfigurationState configuration;
    public final StatusBarIconViewBindingFailureTracker failureTracker;
    public final SystemBarUtilsState systemBarUtilsState;
    public final NotificationIconContainerShelfViewModel viewModel;
    public final ShelfNotificationIconViewStore viewStore;

    public NotificationIconContainerShelfViewBinder(NotificationIconContainerShelfViewModel notificationIconContainerShelfViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, ShelfNotificationIconViewStore shelfNotificationIconViewStore) {
        this.viewModel = notificationIconContainerShelfViewModel;
        this.configuration = configurationState;
        this.systemBarUtilsState = systemBarUtilsState;
        this.failureTracker = statusBarIconViewBindingFailureTracker;
        this.viewStore = shelfNotificationIconViewStore;
    }

    public final Object bind(NotificationIconContainer notificationIconContainer, Continuation continuation) {
        NotificationIconContainerShelfViewModel notificationIconContainerShelfViewModel = this.viewModel;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new NotificationIconContainerViewBinder$bindIcons$3(this.configuration, this.systemBarUtilsState, notificationIconContainerShelfViewModel.icons, "shelf", notificationIconContainer, new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerShelfViewBinder$bind$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                NotificationIconContainerShelfViewBinder.this.failureTracker.shelfFailures = (Collection) obj;
                return Unit.INSTANCE;
            }
        }, this.viewStore, new NotificationIconContainerViewBinder$bindIcons$2(3, null), null));
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (coroutineScope != coroutineSingletons) {
            coroutineScope = unit;
        }
        return coroutineScope == coroutineSingletons ? coroutineScope : unit;
    }
}
