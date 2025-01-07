package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.media.controls.util.MediaFeatureFlag;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.util.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaCoordinator implements Coordinator {
    public final IconManager mIconManager;
    public final Boolean mIsMediaFeatureEnabled;
    public final IStatusBarService mStatusBarService;
    public final ArrayMap mIconsState = new ArrayMap();
    public final AnonymousClass1 mMediaFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator.1
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            MediaCoordinator mediaCoordinator = MediaCoordinator.this;
            if (!mediaCoordinator.mIsMediaFeatureEnabled.booleanValue() || !notificationEntry.mSbn.getNotification().isMediaNotification()) {
                return false;
            }
            int intValue = ((Integer) mediaCoordinator.mIconsState.getOrDefault(notificationEntry, 0)).intValue();
            IconManager iconManager = mediaCoordinator.mIconManager;
            if (intValue == 0) {
                try {
                    iconManager.createIcons(notificationEntry);
                    mediaCoordinator.mIconsState.put(notificationEntry, 1);
                } catch (InflationException e) {
                    mediaCoordinator.reportInflationError(notificationEntry, e);
                    mediaCoordinator.mIconsState.put(notificationEntry, 2);
                }
            } else if (intValue == 1) {
                try {
                    iconManager.updateIcons(notificationEntry, false);
                } catch (InflationException e2) {
                    mediaCoordinator.reportInflationError(notificationEntry, e2);
                    mediaCoordinator.mIconsState.put(notificationEntry, 2);
                }
            }
            return true;
        }
    };
    public final AnonymousClass2 mCollectionListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator.2
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryCleanUp(NotificationEntry notificationEntry) {
            MediaCoordinator.this.mIconsState.remove(notificationEntry);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryInit(NotificationEntry notificationEntry) {
            MediaCoordinator.this.mIconsState.put(notificationEntry, 0);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryUpdated(NotificationEntry notificationEntry) {
            MediaCoordinator mediaCoordinator = MediaCoordinator.this;
            if (((Integer) mediaCoordinator.mIconsState.getOrDefault(notificationEntry, 0)).intValue() == 2) {
                mediaCoordinator.mIconsState.put(notificationEntry, 0);
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryAdded(NotificationEntry notificationEntry) {
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator$2] */
    public MediaCoordinator(MediaFeatureFlag mediaFeatureFlag, IStatusBarService iStatusBarService, IconManager iconManager) {
        this.mIsMediaFeatureEnabled = Boolean.valueOf(Utils.useQsMediaPlayer(mediaFeatureFlag.context));
        this.mStatusBarService = iStatusBarService;
        this.mIconManager = iconManager;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addPreGroupFilter(this.mMediaFilter);
        notifPipeline.addCollectionListener(this.mCollectionListener);
    }

    public final void reportInflationError(NotificationEntry notificationEntry, InflationException inflationException) {
        try {
            StatusBarNotification statusBarNotification = notificationEntry.mSbn;
            this.mStatusBarService.onNotificationError(statusBarNotification.getPackageName(), statusBarNotification.getTag(), statusBarNotification.getId(), statusBarNotification.getUid(), statusBarNotification.getInitialPid(), inflationException.getMessage(), statusBarNotification.getUser().getIdentifier());
        } catch (RemoteException unused) {
        }
    }
}
