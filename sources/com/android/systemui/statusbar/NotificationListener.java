package com.android.systemui.statusbar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.NotificationListenerController;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.domain.interactor.SilentNotificationStatusIconsVisibilityInteractor;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.statusbar.phone.NotificationListenerWithPlugins;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationListener extends NotificationListenerWithPlugins implements PipelineDumpable {
    public final Context mContext;
    public final NotificationListener$$ExternalSyntheticLambda0 mDispatchRankingUpdateRunnable;
    public final Executor mMainExecutor;
    public final List mNotificationHandlers;
    public final NotificationManager mNotificationManager;
    public final Deque mRankingMapQueue;
    public long mSkippingRankingUpdatesSince;
    public final SilentNotificationStatusIconsVisibilityInteractor mStatusIconInteractor;
    public final SystemClock mSystemClock;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface NotificationHandler {
        void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i);

        void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap);

        void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap);

        void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, int i);

        void onNotificationsInitialized();
    }

    /* JADX WARN: Type inference failed for: r8v3, types: [com.android.systemui.statusbar.NotificationListener$$ExternalSyntheticLambda0] */
    public NotificationListener(Context context, NotificationManager notificationManager, SilentNotificationStatusIconsVisibilityInteractor silentNotificationStatusIconsVisibilityInteractor, SystemClock systemClock, Executor executor, PluginManager pluginManager) {
        super(pluginManager);
        this.mNotificationHandlers = new ArrayList();
        this.mRankingMapQueue = new ConcurrentLinkedDeque();
        this.mDispatchRankingUpdateRunnable = new Runnable() { // from class: com.android.systemui.statusbar.NotificationListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NotificationListener notificationListener = NotificationListener.this;
                NotificationListenerService.RankingMap rankingMap = (NotificationListenerService.RankingMap) ((ConcurrentLinkedDeque) notificationListener.mRankingMapQueue).pollFirst();
                if (rankingMap == null) {
                    Log.wtf("NotificationListener", "mRankingMapQueue was empty!");
                }
                if (!notificationListener.mRankingMapQueue.isEmpty()) {
                    ((SystemClockImpl) notificationListener.mSystemClock).getClass();
                    long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
                    if (notificationListener.mSkippingRankingUpdatesSince == -1) {
                        notificationListener.mSkippingRankingUpdatesSince = elapsedRealtime;
                    }
                    if (elapsedRealtime - notificationListener.mSkippingRankingUpdatesSince < 500) {
                        return;
                    }
                }
                notificationListener.mSkippingRankingUpdatesSince = -1L;
                Iterator it = notificationListener.mNotificationHandlers.iterator();
                while (it.hasNext()) {
                    ((NotificationListener.NotificationHandler) it.next()).onNotificationRankingUpdate(rankingMap);
                }
            }
        };
        this.mSkippingRankingUpdatesSince = -1L;
        this.mContext = context;
        this.mNotificationManager = notificationManager;
        this.mStatusIconInteractor = silentNotificationStatusIconsVisibilityInteractor;
        this.mSystemClock = systemClock;
        this.mMainExecutor = executor;
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.mNotificationHandlers, "notificationHandlers");
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onListenerConnected() {
        this.mConnected = true;
        this.mPlugins.forEach(new Consumer() { // from class: com.android.systemui.statusbar.phone.NotificationListenerWithPlugins$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((NotificationListenerController) obj).onListenerConnected(new NotificationListenerWithPlugins.AnonymousClass1());
            }
        });
        StatusBarNotification[] activeNotifications = getActiveNotifications();
        if (activeNotifications == null) {
            Log.w("NotificationListener", "onListenerConnected unable to get active notifications.");
            return;
        }
        this.mMainExecutor.execute(new NotificationListener$$ExternalSyntheticLambda1(this, activeNotifications, getCurrentRanking(), 0));
        onSilentStatusBarIconsVisibilityChanged(this.mNotificationManager.shouldHideSilentStatusBarIcons());
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationChannelModified(final String str, final UserHandle userHandle, final NotificationChannel notificationChannel, final int i) {
        Iterator it = this.mPlugins.iterator();
        while (it.hasNext()) {
            if (((NotificationListenerController) it.next()).onNotificationChannelModified(str, userHandle, notificationChannel, i)) {
                return;
            }
        }
        this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.NotificationListener$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                NotificationListener notificationListener = NotificationListener.this;
                String str2 = str;
                UserHandle userHandle2 = userHandle;
                NotificationChannel notificationChannel2 = notificationChannel;
                int i2 = i;
                Iterator it2 = notificationListener.mNotificationHandlers.iterator();
                while (it2.hasNext()) {
                    ((NotificationListener.NotificationHandler) it2.next()).onNotificationChannelModified(str2, userHandle2, notificationChannel2, i2);
                }
            }
        });
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        if (statusBarNotification != null) {
            Iterator it = this.mPlugins.iterator();
            while (it.hasNext()) {
                if (((NotificationListenerController) it.next()).onNotificationPosted(statusBarNotification, rankingMap)) {
                    return;
                }
            }
            this.mMainExecutor.execute(new NotificationListener$$ExternalSyntheticLambda1(this, statusBarNotification, rankingMap, 1));
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
        if (rankingMap != null) {
            Iterator it = this.mPlugins.iterator();
            while (it.hasNext()) {
                rankingMap = ((NotificationListenerController) it.next()).getCurrentRanking(rankingMap);
            }
            ((ConcurrentLinkedDeque) this.mRankingMapQueue).addLast(rankingMap);
            this.mMainExecutor.execute(this.mDispatchRankingUpdateRunnable);
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationRemoved(final StatusBarNotification statusBarNotification, final NotificationListenerService.RankingMap rankingMap, final int i) {
        if (statusBarNotification != null) {
            Iterator it = this.mPlugins.iterator();
            while (it.hasNext()) {
                if (((NotificationListenerController) it.next()).onNotificationRemoved(statusBarNotification, rankingMap)) {
                    return;
                }
            }
            this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.NotificationListener$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationListener notificationListener = NotificationListener.this;
                    StatusBarNotification statusBarNotification2 = statusBarNotification;
                    NotificationListenerService.RankingMap rankingMap2 = rankingMap;
                    int i2 = i;
                    Iterator it2 = notificationListener.mNotificationHandlers.iterator();
                    while (it2.hasNext()) {
                        ((NotificationListener.NotificationHandler) it2.next()).onNotificationRemoved(statusBarNotification2, rankingMap2, i2);
                    }
                }
            });
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onSilentStatusBarIconsVisibilityChanged(boolean z) {
        AuthContainerView$$ExternalSyntheticOutline0.m(!z, this.mStatusIconInteractor.repository.showSilentStatusIcons, null);
    }

    public final void registerAsSystemService() {
        try {
            registerAsSystemService(this.mContext, new ComponentName(this.mContext.getPackageName(), NotificationListener.class.getCanonicalName()), -1);
        } catch (RemoteException e) {
            Log.e("NotificationListener", "Unable to register notification listener", e);
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        onNotificationRemoved(statusBarNotification, rankingMap, 0);
    }
}
