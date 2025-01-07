package com.android.systemui.media.controls.domain.pipeline;

import android.content.Context;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.media.controls.data.repository.MediaFilterRepository;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.MediaLogger;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDataLoadingModel;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaLoadingModel;
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
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataFilterImpl implements MediaDataManager.Listener {
    public final Set _listeners = new LinkedHashSet();
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final MediaUiEventLogger logger;
    public final MediaFilterRepository mediaFilterRepository;
    public final MediaFlags mediaFlags;
    public final MediaLogger mediaLogger;
    public final SystemClock systemClock;
    public final MediaDataFilterImpl$userTrackerCallback$1 userTrackerCallback;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.media.controls.domain.pipeline.MediaDataFilterImpl$userTrackerCallback$1, com.android.systemui.settings.UserTracker$Callback] */
    public MediaDataFilterImpl(Context context, UserTracker userTracker, BroadcastSender broadcastSender, NotificationLockscreenUserManager notificationLockscreenUserManager, Executor executor, SystemClock systemClock, MediaUiEventLogger mediaUiEventLogger, MediaFlags mediaFlags, MediaFilterRepository mediaFilterRepository, MediaLogger mediaLogger) {
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.systemClock = systemClock;
        this.logger = mediaUiEventLogger;
        this.mediaFlags = mediaFlags;
        this.mediaFilterRepository = mediaFilterRepository;
        this.mediaLogger = mediaLogger;
        ?? r1 = new UserTracker.Callback() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaDataFilterImpl$userTrackerCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onProfilesChanged(List list) {
                MediaDataFilterImpl.this.handleProfileChanged$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }

            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                MediaDataFilterImpl.this.handleUserSwitched$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }
        };
        this.userTrackerCallback = r1;
        ((UserTrackerImpl) userTracker).addCallback(r1, executor);
    }

    public final String getKey(InstanceId instanceId) {
        Map map = (Map) ((StateFlowImpl) this.mediaFilterRepository.allUserEntries.$$delegate_0).getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (Intrinsics.areEqual(((MediaData) entry.getValue()).instanceId, instanceId)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return (String) CollectionsKt.first(linkedHashMap.keySet());
    }

    public final void handleProfileChanged$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        for (Map.Entry entry : ((Map) ((StateFlowImpl) mediaFilterRepository.allUserEntries.$$delegate_0).getValue()).entrySet()) {
            String str = (String) entry.getKey();
            MediaData mediaData = (MediaData) entry.getValue();
            if (!((NotificationLockscreenUserManagerImpl) this.lockscreenUserManager).isProfileAvailable(mediaData.userId)) {
                InstanceId instanceId = mediaData.instanceId;
                StateFlowImpl stateFlowImpl = mediaFilterRepository._selectedUserEntries;
                LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
                if (linkedHashMap.remove(instanceId, mediaData)) {
                    stateFlowImpl.updateState(null, linkedHashMap);
                }
                mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Removed(mediaData.instanceId), true);
                this.mediaLogger.logMediaRemoved(mediaData.instanceId, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Removing ", str, " after profile change"));
                Iterator it = CollectionsKt.toSet(this._listeners).iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, false);
                }
            }
        }
    }

    public final void handleUserSwitched$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        MediaLogger mediaLogger;
        Set set = CollectionsKt.toSet(this._listeners);
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        ArrayList arrayList = new ArrayList(((Map) ((StateFlowImpl) mediaFilterRepository.selectedUserEntries.$$delegate_0).getValue()).keySet());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        StateFlowImpl stateFlowImpl = mediaFilterRepository._selectedUserEntries;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        Iterator it = arrayList.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            mediaLogger = this.mediaLogger;
            if (!hasNext) {
                break;
            }
            InstanceId instanceId = (InstanceId) it.next();
            mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Removed(instanceId), true);
            mediaLogger.logMediaRemoved(instanceId, "Removing media after user change");
            String key = getKey(instanceId);
            if (key != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    ((MediaDataManager.Listener) it2.next()).onMediaDataRemoved(key, false);
                }
            }
        }
        for (Map.Entry entry : ((Map) ((StateFlowImpl) mediaFilterRepository.allUserEntries.$$delegate_0).getValue()).entrySet()) {
            String str = (String) entry.getKey();
            MediaData mediaData = (MediaData) entry.getValue();
            if (((NotificationLockscreenUserManagerImpl) this.lockscreenUserManager).isCurrentProfile(mediaData.userId)) {
                mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Loaded(mediaData.instanceId, false, 0, 14), mediaFilterRepository.addSelectedUserMediaEntry(mediaData));
                mediaLogger.logMediaLoaded(mediaData.instanceId, mediaData.active, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Re-adding ", str, " after user change"));
                Iterator it3 = set.iterator();
                while (it3.hasNext()) {
                    MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it3.next(), str, null, mediaData, false, 0, false, 56);
                }
            }
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        if (str2 != null && !str2.equals(str)) {
            mediaFilterRepository.removeMediaEntry(str2);
        }
        mediaFilterRepository.getClass();
        StateFlowImpl stateFlowImpl = mediaFilterRepository._allUserEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        linkedHashMap.put(str, mediaData);
        stateFlowImpl.updateState(null, linkedHashMap);
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) this.lockscreenUserManager;
        int i2 = mediaData.userId;
        if (notificationLockscreenUserManagerImpl.isCurrentProfile(i2) && notificationLockscreenUserManagerImpl.isProfileAvailable(i2)) {
            boolean addSelectedUserMediaEntry = mediaFilterRepository.addSelectedUserMediaEntry(mediaData);
            this.mediaLogger.logMediaLoaded(mediaData.instanceId, mediaData.active, "loading media");
            mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Loaded(mediaData.instanceId, false, 0, 14), addSelectedUserMediaEntry);
            Iterator it = CollectionsKt.toSet(this._listeners).iterator();
            while (it.hasNext()) {
                MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str, str2, mediaData, false, 0, false, 56);
            }
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        MediaData removeMediaEntry = mediaFilterRepository.removeMediaEntry(str);
        if (removeMediaEntry != null) {
            InstanceId instanceId = removeMediaEntry.instanceId;
            StateFlowImpl stateFlowImpl = mediaFilterRepository._selectedUserEntries;
            LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
            MediaData mediaData = (MediaData) linkedHashMap.remove(instanceId);
            stateFlowImpl.updateState(null, linkedHashMap);
            if (mediaData != null) {
                mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Removed(instanceId), true);
                this.mediaLogger.logMediaRemoved(instanceId, "removing media card");
                Iterator it = CollectionsKt.toSet(this._listeners).iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x018e  */
    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSmartspaceMediaDataLoaded(java.lang.String r43, com.android.systemui.media.controls.shared.model.SmartspaceMediaData r44, boolean r45) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaDataFilterImpl.onSmartspaceMediaDataLoaded(java.lang.String, com.android.systemui.media.controls.shared.model.SmartspaceMediaData, boolean):void");
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        InstanceId instanceId = (InstanceId) ((StateFlowImpl) mediaFilterRepository.reactivatedId.$$delegate_0).getValue();
        MediaLogger mediaLogger = this.mediaLogger;
        if (instanceId != null) {
            mediaFilterRepository._reactivatedId.setValue(null);
            MediaData mediaData = (MediaData) ((Map) ((StateFlowImpl) mediaFilterRepository.selectedUserEntries.$$delegate_0).getValue()).get(instanceId);
            if (mediaData != null) {
                mediaFilterRepository.addMediaDataLoadingState(new MediaDataLoadingModel.Loaded(instanceId, z, 0, 12), true);
                mediaLogger.logMediaLoaded(instanceId, mediaData.active, "expiring reactivated id");
                for (MediaDataManager.Listener listener : CollectionsKt.toSet(this._listeners)) {
                    String key = getKey(instanceId);
                    if (key != null) {
                        MediaDataManager.Listener.onMediaDataLoaded$default(listener, key, key, mediaData, z, 0, false, 48);
                    }
                }
            }
        }
        SmartspaceMediaData smartspaceMediaData = (SmartspaceMediaData) ((StateFlowImpl) mediaFilterRepository.smartspaceMediaData.$$delegate_0).getValue();
        if (smartspaceMediaData.isActive) {
            SmartspaceMediaData copy$default = SmartspaceMediaData.copy$default(LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA, smartspaceMediaData.targetId, null, 0L, smartspaceMediaData.instanceId, 0L, 894);
            StateFlowImpl stateFlowImpl = mediaFilterRepository._smartspaceMediaData;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, copy$default);
        }
        mediaFilterRepository.setRecommendationsLoadingState(new SmartspaceMediaLoadingModel.Removed(str, z));
        mediaLogger.logRecommendationRemoved(str, z);
        Iterator it = CollectionsKt.toSet(this._listeners).iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataRemoved(str, z);
        }
    }
}
