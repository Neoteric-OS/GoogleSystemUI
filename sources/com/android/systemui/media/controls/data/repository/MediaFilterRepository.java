package com.android.systemui.media.controls.data.repository;

import android.content.Context;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.data.model.MediaSortKeyModel;
import com.android.systemui.media.controls.shared.model.MediaCommonModel;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDataLoadingModel;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaLoadingModel;
import com.android.systemui.media.controls.util.MediaSmartspaceLogger;
import com.android.systemui.media.controls.util.SmallHash;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaFilterRepository {
    public final StateFlowImpl _allUserEntries;
    public final StateFlowImpl _currentMedia;
    public final StateFlowImpl _reactivatedId;
    public final StateFlowImpl _selectedUserEntries;
    public final StateFlowImpl _smartspaceMediaData;
    public final ReadonlyStateFlow allUserEntries;
    public final Context applicationContext;
    public final MediaFilterRepository$special$$inlined$thenByDescending$1 comparator;
    public final ConfigurationController configurationController;
    public final ReadonlyStateFlow currentMedia;
    public Locale locale;
    public String mediaFromRecPackageName;
    public final Flow onAnyMediaConfigurationChange = FlowConflatedKt.conflatedCallbackFlow(new MediaFilterRepository$onAnyMediaConfigurationChange$1(this, null));
    public final ReadonlyStateFlow reactivatedId;
    public final ReadonlyStateFlow selectedUserEntries;
    public final MediaSmartspaceLogger smartspaceLogger;
    public final ReadonlyStateFlow smartspaceMediaData;
    public TreeMap sortedMedia;
    public final SystemClock systemClock;

    public MediaFilterRepository(Context context, SystemClock systemClock, ConfigurationController configurationController, MediaSmartspaceLogger mediaSmartspaceLogger) {
        this.applicationContext = context;
        this.systemClock = systemClock;
        this.configurationController = configurationController;
        this.smartspaceLogger = mediaSmartspaceLogger;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._reactivatedId = MutableStateFlow;
        this.reactivatedId = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(new SmartspaceMediaData(null, false, null, null, null, null, 0L, null, 0L, 1023));
        this._smartspaceMediaData = MutableStateFlow2;
        this.smartspaceMediaData = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(new LinkedHashMap());
        this._selectedUserEntries = MutableStateFlow3;
        this.selectedUserEntries = new ReadonlyStateFlow(MutableStateFlow3);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(new LinkedHashMap());
        this._allUserEntries = MutableStateFlow4;
        this.allUserEntries = new ReadonlyStateFlow(MutableStateFlow4);
        MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1 = new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$thenByDescending$1(new MediaFilterRepository$special$$inlined$compareByDescending$1())), (byte) 0), (char) 0), 0), (short) 0), (byte) 0, false), (byte) 0, (byte) 0);
        this.comparator = mediaFilterRepository$special$$inlined$thenByDescending$1;
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(new ArrayList());
        this._currentMedia = MutableStateFlow5;
        this.currentMedia = new ReadonlyStateFlow(MutableStateFlow5);
        this.sortedMedia = new TreeMap(mediaFilterRepository$special$$inlined$thenByDescending$1);
        this.locale = context.getResources().getConfiguration().getLocales().get(0);
    }

    public static void logSmartspaceCardUserEvent$default(MediaFilterRepository mediaFilterRepository, int i, int i2, int i3, InstanceId instanceId, int i4) {
        int i5 = 0;
        int i6 = (i4 & 4) != 0 ? 0 : i3;
        int i7 = (i4 & 8) != 0 ? 0 : 3;
        InstanceId instanceId2 = (i4 & 16) != 0 ? null : instanceId;
        boolean z = (i4 & 32) == 0;
        for (Object obj : (Iterable) mediaFilterRepository._currentMedia.getValue()) {
            int i8 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaCommonModel mediaCommonModel = (MediaCommonModel) obj;
            if (mediaCommonModel instanceof MediaCommonModel.MediaControl) {
                MediaCommonModel.MediaControl mediaControl = (MediaCommonModel.MediaControl) mediaCommonModel;
                if (Intrinsics.areEqual(mediaControl.mediaLoadedModel.instanceId, instanceId2)) {
                    if (mediaFilterRepository.isSmartspaceLoggingEnabled(mediaCommonModel, i5)) {
                        boolean z2 = mediaControl.mediaLoadedModel.isSsReactivated;
                        int i9 = (128 & 32) != 0 ? 0 : i6;
                        int i10 = (128 & 64) != 0 ? 0 : i7;
                        boolean z3 = (128 & 128) == 0;
                        MediaData mediaData = (MediaData) ((Map) mediaFilterRepository._selectedUserEntries.getValue()).get(instanceId2);
                        if (mediaData != null) {
                            MediaSmartspaceLogger.logSmartspaceCardUIEvent$default(mediaFilterRepository.smartspaceLogger, i, mediaData.smartspaceId, mediaData.appUid, i2, ((List) mediaFilterRepository._currentMedia.getValue()).size(), z2, i9, i10, i5, z3, 32);
                            return;
                        }
                        return;
                    }
                    return;
                }
            } else if ((mediaCommonModel instanceof MediaCommonModel.MediaRecommendations) && z) {
                if (mediaFilterRepository.isSmartspaceLoggingEnabled(mediaCommonModel, i5)) {
                    int i11 = (32 & 8) != 0 ? 0 : i6;
                    int i12 = (32 & 16) != 0 ? 0 : i7;
                    boolean z4 = (32 & 32) == 0;
                    StateFlowImpl stateFlowImpl = mediaFilterRepository._smartspaceMediaData;
                    MediaSmartspaceLogger.logSmartspaceCardUIEvent$default(mediaFilterRepository.smartspaceLogger, i, SmallHash.hash(Objects.hashCode(((SmartspaceMediaData) stateFlowImpl.getValue()).targetId)), ((SmartspaceMediaData) stateFlowImpl.getValue()).getUid(mediaFilterRepository.applicationContext), i2, ((List) mediaFilterRepository._currentMedia.getValue()).size(), false, i11, i12, i5, z4, 64);
                    return;
                }
                return;
            }
            i5 = i8;
        }
    }

    public final void addMediaDataLoadingState(MediaDataLoadingModel mediaDataLoadingModel, boolean z) {
        long j;
        TreeMap treeMap = new TreeMap(this.comparator);
        TreeMap treeMap2 = this.sortedMedia;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : treeMap2.entrySet()) {
            MediaCommonModel mediaCommonModel = (MediaCommonModel) entry.getValue();
            if (!(mediaCommonModel instanceof MediaCommonModel.MediaControl) || !Intrinsics.areEqual(((MediaCommonModel.MediaControl) mediaCommonModel).mediaLoadedModel.instanceId, mediaDataLoadingModel.getInstanceId())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        treeMap.putAll(linkedHashMap);
        MediaData mediaData = (MediaData) ((Map) this._selectedUserEntries.getValue()).get(mediaDataLoadingModel.getInstanceId());
        StateFlowImpl stateFlowImpl = this._currentMedia;
        if (mediaData != null) {
            boolean z2 = mediaData.active;
            long j2 = mediaData.lastActive;
            SystemClockImpl systemClockImpl = (SystemClockImpl) this.systemClock;
            systemClockImpl.getClass();
            long currentTimeMillis = System.currentTimeMillis();
            InstanceId instanceId = mediaData.instanceId;
            int i = mediaData.playbackLocation;
            boolean z3 = mediaData.resumption;
            String str = mediaData.notificationKey;
            Boolean bool = mediaData.isPlaying;
            MediaSortKeyModel mediaSortKeyModel = new MediaSortKeyModel(false, bool, i, z2, z3, j2, str, currentTimeMillis, instanceId);
            if (mediaDataLoadingModel instanceof MediaDataLoadingModel.Loaded) {
                MediaDataLoadingModel.Loaded loaded = (MediaDataLoadingModel.Loaded) mediaDataLoadingModel;
                boolean z4 = (bool != null ? bool.booleanValue() ^ true : mediaData.isClearable) && !mediaData.active;
                Boolean bool2 = Boolean.TRUE;
                boolean areEqual = Intrinsics.areEqual(bool, bool2);
                String str2 = mediaData.packageName;
                boolean z5 = areEqual && Intrinsics.areEqual(this.mediaFromRecPackageName, str2);
                if (z) {
                    systemClockImpl.getClass();
                    j = System.currentTimeMillis();
                } else {
                    j = 0;
                }
                MediaCommonModel.MediaControl mediaControl = new MediaCommonModel.MediaControl(loaded, z4, z5, j);
                treeMap.put(mediaSortKeyModel, mediaControl);
                if (!Intrinsics.areEqual(this.mediaFromRecPackageName, str2)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll((Collection) stateFlowImpl.getValue());
                    boolean z6 = true;
                    int i2 = 0;
                    for (Object obj : arrayList) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                            throw null;
                        }
                        MediaCommonModel mediaCommonModel2 = (MediaCommonModel) obj;
                        if ((mediaCommonModel2 instanceof MediaCommonModel.MediaControl) && Intrinsics.areEqual(((MediaCommonModel.MediaControl) mediaCommonModel2).mediaLoadedModel.instanceId, ((MediaDataLoadingModel.Loaded) mediaDataLoadingModel).instanceId)) {
                            if (!Intrinsics.areEqual(mediaCommonModel2, mediaControl)) {
                                arrayList.set(i2, mediaControl);
                            }
                            z6 = false;
                        }
                        i2 = i3;
                    }
                    if (z6 && mediaData.active) {
                        stateFlowImpl.setValue(CollectionsKt.toList(treeMap.values()));
                    } else {
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, arrayList);
                    }
                } else if (Intrinsics.areEqual(bool, bool2)) {
                    this.mediaFromRecPackageName = null;
                    stateFlowImpl.setValue(CollectionsKt.toList(treeMap.values()));
                }
                this.sortedMedia = treeMap;
                if (z) {
                    int i4 = loaded.receivedSmartspaceCardLatency;
                    if (i4 != 0) {
                        logSmartspaceAllMediaCards(i4);
                    }
                } else {
                    int indexOf = CollectionsKt.indexOf(treeMap.values(), mediaControl);
                    if (isSmartspaceLoggingEnabled(mediaControl, indexOf)) {
                        MediaSmartspaceLogger.logSmartspaceCardReceived$default(this.smartspaceLogger, mediaData.smartspaceId, mediaData.appUid, ((List) stateFlowImpl.getValue()).size(), loaded.isSsReactivated, indexOf, 0, 72);
                    }
                }
            }
        }
        if (mediaDataLoadingModel instanceof MediaDataLoadingModel.Removed) {
            Iterable iterable = (Iterable) stateFlowImpl.getValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : iterable) {
                MediaCommonModel mediaCommonModel3 = (MediaCommonModel) obj2;
                if (((mediaCommonModel3 instanceof MediaCommonModel.MediaControl) && Intrinsics.areEqual(((MediaDataLoadingModel.Removed) mediaDataLoadingModel).instanceId, ((MediaCommonModel.MediaControl) mediaCommonModel3).mediaLoadedModel.instanceId)) ? false : true) {
                    arrayList2.add(obj2);
                }
            }
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, arrayList2);
            this.sortedMedia = treeMap;
        }
    }

    public final boolean addSelectedUserMediaEntry(MediaData mediaData) {
        StateFlowImpl stateFlowImpl = this._selectedUserEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        boolean containsKey = ((Map) stateFlowImpl.getValue()).containsKey(mediaData.instanceId);
        linkedHashMap.put(mediaData.instanceId, mediaData);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        return containsKey;
    }

    public final boolean hasActiveMedia() {
        Map map = (Map) this._selectedUserEntries.getValue();
        if (map.isEmpty()) {
            return false;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (((MediaData) ((Map.Entry) it.next()).getValue()).active) {
                return true;
            }
        }
        return false;
    }

    public final boolean isRecommendationActive() {
        return ((SmartspaceMediaData) this._smartspaceMediaData.getValue()).isActive;
    }

    public final boolean isSmartspaceLoggingEnabled(MediaCommonModel mediaCommonModel, int i) {
        return this.sortedMedia.size() > i && (((SmartspaceMediaData) this._smartspaceMediaData.getValue()).expiryTimeMs != 0 || isRecommendationActive() || (mediaCommonModel instanceof MediaCommonModel.MediaRecommendations));
    }

    public final void logSmartspaceAllMediaCards(int i) {
        int i2 = 0;
        for (Object obj : this.sortedMedia.values()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaCommonModel mediaCommonModel = (MediaCommonModel) obj;
            if (mediaCommonModel instanceof MediaCommonModel.MediaControl) {
                MediaCommonModel.MediaControl mediaControl = (MediaCommonModel.MediaControl) mediaCommonModel;
                MediaData mediaData = (MediaData) ((Map) this._selectedUserEntries.getValue()).get(mediaControl.mediaLoadedModel.instanceId);
                if (mediaData != null) {
                    ((SystemClockImpl) this.systemClock).getClass();
                    mediaData.smartspaceId = SmallHash.hash(mediaData.appUid + ((int) System.currentTimeMillis()));
                    if (isSmartspaceLoggingEnabled(mediaCommonModel, i2)) {
                        MediaSmartspaceLogger.logSmartspaceCardReceived$default(this.smartspaceLogger, mediaData.smartspaceId, mediaData.appUid, ((List) this._currentMedia.getValue()).size(), mediaControl.mediaLoadedModel.isSsReactivated, i2, i, 8);
                    }
                }
            }
            i2 = i3;
        }
    }

    public final MediaData removeMediaEntry(String str) {
        StateFlowImpl stateFlowImpl = this._allUserEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        MediaData mediaData = (MediaData) linkedHashMap.remove(str);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        return mediaData;
    }

    public final void setRecommendationsLoadingState(SmartspaceMediaLoadingModel smartspaceMediaLoadingModel) {
        boolean z = smartspaceMediaLoadingModel instanceof SmartspaceMediaLoadingModel.Loaded;
        boolean z2 = z ? ((SmartspaceMediaLoadingModel.Loaded) smartspaceMediaLoadingModel).isPrioritized : false;
        TreeMap treeMap = new TreeMap(this.comparator);
        TreeMap treeMap2 = this.sortedMedia;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : treeMap2.entrySet()) {
            if (!(((MediaCommonModel) entry.getValue()) instanceof MediaCommonModel.MediaRecommendations)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        treeMap.putAll(linkedHashMap);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = this._smartspaceMediaData;
        MediaSortKeyModel mediaSortKeyModel = new MediaSortKeyModel(z2, bool, 0, ((SmartspaceMediaData) stateFlowImpl.getValue()).isActive, false, 0L, null, 0L, null);
        MediaCommonModel.MediaRecommendations mediaRecommendations = new MediaCommonModel.MediaRecommendations(smartspaceMediaLoadingModel);
        StateFlowImpl stateFlowImpl2 = this._currentMedia;
        if (z) {
            treeMap.put(mediaSortKeyModel, mediaRecommendations);
            stateFlowImpl2.setValue(CollectionsKt.toList(treeMap.values()));
            this.sortedMedia = treeMap;
            if (isRecommendationActive()) {
                if (!hasActiveMedia() && !((Map) this._selectedUserEntries.getValue()).entrySet().isEmpty() && ((SmartspaceMediaLoadingModel.Loaded) smartspaceMediaLoadingModel).isPrioritized) {
                    ((SystemClockImpl) this.systemClock).getClass();
                    logSmartspaceAllMediaCards((int) (System.currentTimeMillis() - ((SmartspaceMediaData) stateFlowImpl.getValue()).headphoneConnectionTimeMillis));
                }
                MediaSmartspaceLogger.logSmartspaceCardReceived$default(this.smartspaceLogger, SmallHash.hash(Objects.hashCode(((SmartspaceMediaData) stateFlowImpl.getValue()).targetId)), ((SmartspaceMediaData) stateFlowImpl.getValue()).getUid(this.applicationContext), ((List) stateFlowImpl2.getValue()).size(), false, ((List) stateFlowImpl2.getValue()).indexOf(mediaRecommendations), 0, 80);
                return;
            }
            return;
        }
        if (smartspaceMediaLoadingModel instanceof SmartspaceMediaLoadingModel.Removed) {
            Iterable iterable = (Iterable) stateFlowImpl2.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (!(((MediaCommonModel) obj) instanceof MediaCommonModel.MediaRecommendations)) {
                    arrayList.add(obj);
                }
            }
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, arrayList);
            this.sortedMedia = treeMap;
        }
    }
}
