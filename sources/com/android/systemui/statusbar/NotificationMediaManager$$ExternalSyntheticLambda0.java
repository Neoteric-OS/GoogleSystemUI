package com.android.systemui.statusbar;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.NotificationMediaManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationMediaManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ NotificationMediaManager$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PlaybackState playbackState;
        PlaybackState playbackState2;
        boolean z;
        StatusBarNotification statusBarNotification;
        MediaController mediaController;
        MediaSession.Token token;
        switch (this.$r8$classId) {
            case 0:
                final NotificationMediaManager notificationMediaManager = (NotificationMediaManager) this.f$0;
                final NotificationMediaManager.MediaListener mediaListener = (NotificationMediaManager.MediaListener) this.f$1;
                MediaController mediaController2 = notificationMediaManager.mMediaController;
                final int state = (mediaController2 == null || (playbackState = mediaController2.getPlaybackState()) == null) ? 0 : playbackState.getState();
                final int i = 0;
                notificationMediaManager.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.NotificationMediaManager$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i) {
                            case 0:
                                NotificationMediaManager notificationMediaManager2 = notificationMediaManager;
                                ((NotificationMediaManager.MediaListener) mediaListener).onPrimaryMetadataOrStateChanged(notificationMediaManager2.mMediaMetadata, state);
                                break;
                            default:
                                NotificationMediaManager notificationMediaManager3 = notificationMediaManager;
                                List list = (List) mediaListener;
                                int i2 = state;
                                notificationMediaManager3.getClass();
                                for (int i3 = 0; i3 < list.size(); i3++) {
                                    ((NotificationMediaManager.MediaListener) list.get(i3)).onPrimaryMetadataOrStateChanged(notificationMediaManager3.mMediaMetadata, i2);
                                }
                                break;
                        }
                    }
                });
                break;
            case 1:
                final NotificationMediaManager notificationMediaManager2 = (NotificationMediaManager) this.f$0;
                final ArrayList arrayList = (ArrayList) this.f$1;
                MediaController mediaController3 = notificationMediaManager2.mMediaController;
                final int state2 = (mediaController3 == null || (playbackState2 = mediaController3.getPlaybackState()) == null) ? 0 : playbackState2.getState();
                final int i2 = 1;
                notificationMediaManager2.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.NotificationMediaManager$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i2) {
                            case 0:
                                NotificationMediaManager notificationMediaManager22 = notificationMediaManager2;
                                ((NotificationMediaManager.MediaListener) arrayList).onPrimaryMetadataOrStateChanged(notificationMediaManager22.mMediaMetadata, state2);
                                break;
                            default:
                                NotificationMediaManager notificationMediaManager3 = notificationMediaManager2;
                                List list = (List) arrayList;
                                int i22 = state2;
                                notificationMediaManager3.getClass();
                                for (int i3 = 0; i3 < list.size(); i3++) {
                                    ((NotificationMediaManager.MediaListener) list.get(i3)).onPrimaryMetadataOrStateChanged(notificationMediaManager3.mMediaMetadata, i22);
                                }
                                break;
                        }
                    }
                });
                break;
            case 2:
                NotificationMediaManager notificationMediaManager3 = (NotificationMediaManager) this.f$0;
                List list = (List) this.f$1;
                notificationMediaManager3.getClass();
                Iterator it = list.iterator();
                while (true) {
                    z = false;
                    if (it.hasNext()) {
                        statusBarNotification = (StatusBarNotification) it.next();
                        if (statusBarNotification.getNotification().isMediaNotification() && (token = (MediaSession.Token) statusBarNotification.getNotification().extras.getParcelable("android.mediaSession", MediaSession.Token.class)) != null) {
                            mediaController = new MediaController(notificationMediaManager3.mContext, token);
                            PlaybackState playbackState3 = mediaController.getPlaybackState();
                            if (3 == (playbackState3 != null ? playbackState3.getState() : 0)) {
                            }
                        }
                    } else {
                        statusBarNotification = null;
                        mediaController = null;
                    }
                }
                if (mediaController != null) {
                    MediaController mediaController4 = notificationMediaManager3.mMediaController;
                    if (mediaController4 == mediaController) {
                        z = true;
                    } else if (mediaController4 != null) {
                        z = mediaController4.controlsSameSession(mediaController);
                    }
                    if (!z) {
                        notificationMediaManager3.mMediaMetadata = null;
                        MediaController mediaController5 = notificationMediaManager3.mMediaController;
                        if (mediaController5 != null) {
                            mediaController5.unregisterCallback(notificationMediaManager3.mMediaListener);
                        }
                        notificationMediaManager3.mMediaController = mediaController;
                        mediaController.registerCallback(notificationMediaManager3.mMediaListener, notificationMediaManager3.mHandler);
                        notificationMediaManager3.mMediaMetadata = notificationMediaManager3.mMediaController.getMetadata();
                    }
                }
                if (statusBarNotification != null && !statusBarNotification.getKey().equals(notificationMediaManager3.mMediaNotificationKey)) {
                    notificationMediaManager3.mMediaNotificationKey = statusBarNotification.getKey();
                    break;
                }
                break;
            default:
                ((NotificationMediaManager.AnonymousClass1) this.f$0).this$0.mMediaMetadata = (MediaMetadata) this.f$1;
                break;
        }
    }
}
