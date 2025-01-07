package com.android.systemui.media.controls.domain.pipeline;

import android.content.Context;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.util.time.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LegacyMediaDataFilterImpl implements MediaDataManager.Listener {
    public final BroadcastSender broadcastSender;
    public final Context context;
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final MediaUiEventLogger logger;
    public LegacyMediaDataManagerImpl mediaDataManager;
    public final MediaFlags mediaFlags;
    public String reactivatedKey;
    public final SystemClock systemClock;
    public final LegacyMediaDataFilterImpl$userTrackerCallback$1 userTrackerCallback;
    public final Set _listeners = new LinkedHashSet();
    public final LinkedHashMap allEntries = new LinkedHashMap();
    public final LinkedHashMap userEntries = new LinkedHashMap();
    public SmartspaceMediaData smartspaceMediaData = LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataFilterImpl$userTrackerCallback$1, com.android.systemui.settings.UserTracker$Callback] */
    public LegacyMediaDataFilterImpl(Context context, UserTracker userTracker, BroadcastSender broadcastSender, NotificationLockscreenUserManager notificationLockscreenUserManager, Executor executor, SystemClock systemClock, MediaUiEventLogger mediaUiEventLogger, MediaFlags mediaFlags) {
        this.context = context;
        this.broadcastSender = broadcastSender;
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.systemClock = systemClock;
        this.logger = mediaUiEventLogger;
        this.mediaFlags = mediaFlags;
        ?? r1 = new UserTracker.Callback() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataFilterImpl$userTrackerCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onProfilesChanged(List list) {
                LegacyMediaDataFilterImpl.this.handleProfileChanged$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }

            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                LegacyMediaDataFilterImpl.this.handleUserSwitched$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }
        };
        this.userTrackerCallback = r1;
        ((UserTrackerImpl) userTracker).addCallback(r1, executor);
    }

    public final void handleProfileChanged$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        for (Map.Entry entry : this.allEntries.entrySet()) {
            String str = (String) entry.getKey();
            MediaData mediaData = (MediaData) entry.getValue();
            if (!((NotificationLockscreenUserManagerImpl) this.lockscreenUserManager).isProfileAvailable(mediaData.userId)) {
                Log.d("MediaDataFilter", "Removing " + str + " after profile change");
                this.userEntries.remove(str, mediaData);
                Iterator it = CollectionsKt.toSet(this._listeners).iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, false);
                }
            }
        }
    }

    public final void handleUserSwitched$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        Set<MediaDataManager.Listener> set = CollectionsKt.toSet(this._listeners);
        ArrayList<String> arrayList = new ArrayList(this.userEntries.keySet());
        this.userEntries.clear();
        for (String str : arrayList) {
            Log.d("MediaDataFilter", "Removing " + str + " after user change");
            for (MediaDataManager.Listener listener : set) {
                Intrinsics.checkNotNull(str);
                listener.onMediaDataRemoved(str, false);
            }
        }
        for (Map.Entry entry : this.allEntries.entrySet()) {
            String str2 = (String) entry.getKey();
            MediaData mediaData = (MediaData) entry.getValue();
            if (((NotificationLockscreenUserManagerImpl) this.lockscreenUserManager).isCurrentProfile(mediaData.userId)) {
                Log.d("MediaDataFilter", "Re-adding " + str2 + " after user change");
                this.userEntries.put(str2, mediaData);
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str2, null, mediaData, false, 0, false, 56);
                }
            }
        }
    }

    public final boolean hasActiveMedia() {
        LinkedHashMap linkedHashMap = this.userEntries;
        if (linkedHashMap.isEmpty()) {
            return false;
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            if (((MediaData) ((Map.Entry) it.next()).getValue()).active) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
        if (str2 != null && !str2.equals(str)) {
            this.allEntries.remove(str2);
        }
        this.allEntries.put(str, mediaData);
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) this.lockscreenUserManager;
        int i2 = mediaData.userId;
        if (notificationLockscreenUserManagerImpl.isCurrentProfile(i2) && notificationLockscreenUserManagerImpl.isProfileAvailable(i2)) {
            if (str2 != null && !str2.equals(str)) {
                this.userEntries.remove(str2);
            }
            this.userEntries.put(str, mediaData);
            Iterator it = CollectionsKt.toSet(this._listeners).iterator();
            while (it.hasNext()) {
                MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str, str2, mediaData, false, 0, false, 56);
            }
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        this.allEntries.remove(str);
        if (((MediaData) this.userEntries.remove(str)) != null) {
            Iterator it = CollectionsKt.toSet(this._listeners).iterator();
            while (it.hasNext()) {
                ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x012f, code lost:
    
        if (r3 != false) goto L44;
     */
    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSmartspaceMediaDataLoaded(java.lang.String r32, com.android.systemui.media.controls.shared.model.SmartspaceMediaData r33, boolean r34) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataFilterImpl.onSmartspaceMediaDataLoaded(java.lang.String, com.android.systemui.media.controls.shared.model.SmartspaceMediaData, boolean):void");
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
        String str2 = this.reactivatedKey;
        if (str2 != null) {
            this.reactivatedKey = null;
            Log.d("MediaDataFilter", "expiring reactivated key ".concat(str2));
            MediaData mediaData = (MediaData) this.userEntries.get(str2);
            if (mediaData != null) {
                Iterator it = CollectionsKt.toSet(this._listeners).iterator();
                while (it.hasNext()) {
                    MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str2, str2, mediaData, z, 0, false, 48);
                }
            }
        }
        SmartspaceMediaData smartspaceMediaData = this.smartspaceMediaData;
        if (smartspaceMediaData.isActive) {
            this.smartspaceMediaData = SmartspaceMediaData.copy$default(LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA, smartspaceMediaData.targetId, null, 0L, smartspaceMediaData.instanceId, 0L, 894);
        }
        Iterator it2 = CollectionsKt.toSet(this._listeners).iterator();
        while (it2.hasNext()) {
            ((MediaDataManager.Listener) it2.next()).onSmartspaceMediaDataRemoved(str, z);
        }
    }
}
