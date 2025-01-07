package com.android.systemui.statusbar.notification.collection.inflation;

import android.os.SystemClock;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifCollection.FutureDismissal;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ExecutorImpl.ExecutionToken;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OnUserInteractionCallbackImpl {
    public final HeadsUpManager mHeadsUpManager;
    public final NotifCollection mNotifCollection;
    public final StatusBarStateController mStatusBarStateController;
    public final NotificationVisibilityProvider mVisibilityProvider;
    public final VisualStabilityCoordinator mVisualStabilityCoordinator;

    public OnUserInteractionCallbackImpl(NotificationVisibilityProvider notificationVisibilityProvider, NotifCollection notifCollection, HeadsUpManager headsUpManager, StatusBarStateController statusBarStateController, VisualStabilityCoordinator visualStabilityCoordinator) {
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mNotifCollection = notifCollection;
        this.mHeadsUpManager = headsUpManager;
        this.mStatusBarStateController = statusBarStateController;
        this.mVisualStabilityCoordinator = visualStabilityCoordinator;
    }

    public final void onImportanceChanged(NotificationEntry notificationEntry) {
        long uptimeMillis = SystemClock.uptimeMillis();
        final VisualStabilityCoordinator visualStabilityCoordinator = this.mVisualStabilityCoordinator;
        visualStabilityCoordinator.getClass();
        final String str = notificationEntry.mKey;
        VisualStabilityCoordinator.AnonymousClass1 anonymousClass1 = visualStabilityCoordinator.mNotifStabilityManager;
        boolean isSectionChangeAllowed = anonymousClass1.isSectionChangeAllowed(notificationEntry);
        if (visualStabilityCoordinator.mEntriesThatCanChangeSection.containsKey(str)) {
            ((Runnable) visualStabilityCoordinator.mEntriesThatCanChangeSection.get(str)).run();
        }
        Map map = visualStabilityCoordinator.mEntriesThatCanChangeSection;
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VisualStabilityCoordinator visualStabilityCoordinator2 = VisualStabilityCoordinator.this;
                visualStabilityCoordinator2.mEntriesThatCanChangeSection.remove(str);
            }
        };
        DelayableExecutor delayableExecutor = visualStabilityCoordinator.mDelayableExecutor;
        delayableExecutor.getClass();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        ExecutorImpl executorImpl = (ExecutorImpl) delayableExecutor;
        ExecutorImpl.ExecutionToken executionToken = executorImpl.new ExecutionToken(runnable);
        executorImpl.mHandler.sendMessageAtTime(executorImpl.mHandler.obtainMessage(0, executionToken), timeUnit.toMillis(uptimeMillis + 500));
        map.put(str, executionToken);
        if (isSectionChangeAllowed) {
            return;
        }
        anonymousClass1.invalidateList("temporarilyAllowSectionChanges");
    }

    public final NotifCollection.FutureDismissal registerFutureDismissal(NotificationEntry notificationEntry, int i) {
        OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0 onUserInteractionCallbackImpl$$ExternalSyntheticLambda0 = new OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0(this);
        NotifCollection notifCollection = this.mNotifCollection;
        NotifCollection.FutureDismissal futureDismissal = (NotifCollection.FutureDismissal) notifCollection.mFutureDismissals.get(notificationEntry.mKey);
        NotifCollectionLogger notifCollectionLogger = notifCollection.mLogger;
        if (futureDismissal != null) {
            notifCollectionLogger.logFutureDismissalReused(futureDismissal);
            return futureDismissal;
        }
        NotifCollection.FutureDismissal futureDismissal2 = notifCollection.new FutureDismissal(notificationEntry, i, onUserInteractionCallbackImpl$$ExternalSyntheticLambda0);
        notifCollection.mFutureDismissals.put(notificationEntry.mKey, futureDismissal2);
        notifCollectionLogger.logFutureDismissalRegistered(futureDismissal2);
        return futureDismissal2;
    }
}
