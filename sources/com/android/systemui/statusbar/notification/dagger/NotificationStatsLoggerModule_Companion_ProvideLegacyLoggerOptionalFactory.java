package com.android.systemui.statusbar.notification.dagger;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationStatsLoggerModule_Companion_ProvideLegacyLoggerOptionalFactory implements Provider {
    public static Optional provideLegacyLoggerOptional(NotificationListener notificationListener, Executor executor, NotifLiveDataStoreImpl notifLiveDataStoreImpl, NotificationVisibilityProvider notificationVisibilityProvider, NotifPipeline notifPipeline, StatusBarStateController statusBarStateController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, JavaAdapter javaAdapter, NotificationLogger.ExpansionStateLogger expansionStateLogger, NotificationPanelLoggerImpl notificationPanelLoggerImpl) {
        Optional of = Optional.of(new NotificationLogger(notificationListener, executor, notifLiveDataStoreImpl, notificationVisibilityProvider, notifPipeline, statusBarStateController, windowRootViewVisibilityInteractor, javaAdapter, expansionStateLogger, notificationPanelLoggerImpl));
        Intrinsics.checkNotNull(of);
        return of;
    }
}
