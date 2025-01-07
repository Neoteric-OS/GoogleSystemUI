package com.android.systemui.media.controls.domain.pipeline;

import android.service.notification.StatusBarNotification;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface MediaDataManager {
    void addListener(Listener listener);

    boolean dismissMediaData(String str, long j, boolean z);

    void dismissSmartspaceRecommendation(long j, String str);

    boolean hasActiveMedia();

    boolean hasActiveMediaOrRecommendation();

    boolean hasAnyMedia();

    boolean hasAnyMediaOrRecommendation();

    boolean isRecommendationActive();

    void onNotificationAdded(StatusBarNotification statusBarNotification, String str);

    void onNotificationRemoved(String str);

    void onSwipeToDismiss();

    void removeListener(Listener listener);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        static /* synthetic */ void onMediaDataLoaded$default(Listener listener, String str, String str2, MediaData mediaData, boolean z, int i, boolean z2, int i2) {
            if ((i2 & 8) != 0) {
                z = true;
            }
            listener.onMediaDataLoaded(str, str2, mediaData, z, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? false : z2);
        }

        void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2);

        default void onMediaDataRemoved(String str, boolean z) {
        }

        default void onSmartspaceMediaDataRemoved(String str, boolean z) {
        }

        default void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
        }
    }
}
