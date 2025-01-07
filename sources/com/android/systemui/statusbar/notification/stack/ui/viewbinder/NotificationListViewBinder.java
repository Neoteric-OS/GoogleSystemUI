package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerShelfViewBinder;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.DisplaySwitchNotificationsHiderTracker;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.statusbar.notification.ui.viewbinder.HeadsUpNotificationViewBinder;
import com.android.wm.shell.R;
import java.util.Optional;
import javax.inject.Provider;
import kotlin.ResultKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationListViewBinder {
    public final CoroutineDispatcher backgroundDispatcher;
    public final ConfigurationState configuration;
    public final FalsingManager falsingManager;
    public final DisplaySwitchNotificationsHiderTracker hiderTracker;
    public final HeadsUpNotificationViewBinder hunBinder;
    public final MetricsLogger metricsLogger;
    public final NotificationIconContainerShelfViewBinder nicBinder;
    public final Provider notificationActivityStarter;
    public final SectionHeaderNodeControllerImpl silentHeaderController;
    public final NotificationListViewModel viewModel;

    public NotificationListViewBinder(CoroutineDispatcher coroutineDispatcher, DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker, ConfigurationState configurationState, FalsingManager falsingManager, HeadsUpNotificationViewBinder headsUpNotificationViewBinder, Optional optional, MetricsLogger metricsLogger, NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder, Provider provider, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl, NotificationListViewModel notificationListViewModel) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.hiderTracker = displaySwitchNotificationsHiderTracker;
        this.configuration = configurationState;
        this.falsingManager = falsingManager;
        this.metricsLogger = metricsLogger;
        this.nicBinder = notificationIconContainerShelfViewBinder;
        this.notificationActivityStarter = provider;
        this.silentHeaderController = sectionHeaderNodeControllerImpl;
        this.viewModel = notificationListViewModel;
    }

    public static final void access$bindLogger(NotificationListViewBinder notificationListViewBinder, ContinuationImpl continuationImpl) {
        NotificationListViewBinder$bindLogger$1 notificationListViewBinder$bindLogger$1;
        int i;
        notificationListViewBinder.getClass();
        if (continuationImpl instanceof NotificationListViewBinder$bindLogger$1) {
            notificationListViewBinder$bindLogger$1 = (NotificationListViewBinder$bindLogger$1) continuationImpl;
            int i2 = notificationListViewBinder$bindLogger$1.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                notificationListViewBinder$bindLogger$1.label = i2 - Integer.MIN_VALUE;
                Object obj = notificationListViewBinder$bindLogger$1.result;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                i = notificationListViewBinder$bindLogger$1.label;
                if (i == 0 && i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        }
        notificationListViewBinder$bindLogger$1 = new NotificationListViewBinder$bindLogger$1(notificationListViewBinder, continuationImpl);
        Object obj2 = notificationListViewBinder$bindLogger$1.result;
        CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        i = notificationListViewBinder$bindLogger$1.label;
        if (i == 0) {
        }
        ResultKt.throwOnFailure(obj2);
    }

    public final void bindWhileAttached(NotificationStackScrollLayout notificationStackScrollLayout, NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        int i;
        NotificationShelf notificationShelf = (NotificationShelf) LayoutInflater.from(notificationStackScrollLayout.getContext()).inflate(R.layout.status_bar_notification_shelf, (ViewGroup) notificationStackScrollLayout, false);
        View view = notificationStackScrollLayout.mShelf;
        if (view != null) {
            i = notificationStackScrollLayout.indexOfChild(view);
            notificationStackScrollLayout.removeView(notificationStackScrollLayout.mShelf);
        } else {
            i = -1;
        }
        notificationStackScrollLayout.mShelf = notificationShelf;
        notificationStackScrollLayout.addView(notificationShelf, i);
        AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
        NotificationShelf notificationShelf2 = notificationStackScrollLayout.mShelf;
        ambientState.mShelf = notificationShelf2;
        notificationStackScrollLayout.mStateAnimator.mShelf = notificationShelf2;
        NotificationRoundnessManager notificationRoundnessManager = notificationStackScrollLayout.mController.mNotificationRoundnessManager;
        notificationShelf.mAmbientState = ambientState;
        notificationShelf.mHostLayout = notificationStackScrollLayout;
        notificationShelf.mRoundnessManager = notificationRoundnessManager;
        RepeatWhenAttachedKt.repeatWhenAttached(notificationStackScrollLayout, EmptyCoroutineContext.INSTANCE, new NotificationListViewBinder$bindWhileAttached$1(notificationStackScrollLayoutController, this, notificationStackScrollLayout, notificationShelf, null));
    }
}
