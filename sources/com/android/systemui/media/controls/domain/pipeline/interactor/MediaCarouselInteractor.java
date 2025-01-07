package com.android.systemui.media.controls.domain.pipeline.interactor;

import android.service.notification.StatusBarNotification;
import com.android.systemui.CoreStartable;
import com.android.systemui.media.controls.data.repository.MediaFilterRepository;
import com.android.systemui.media.controls.domain.pipeline.MediaDataCombineLatest;
import com.android.systemui.media.controls.domain.pipeline.MediaDataFilterImpl;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor;
import com.android.systemui.media.controls.domain.pipeline.MediaDeviceManager;
import com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.util.MediaFlags;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselInteractor implements MediaDataManager, CoreStartable {
    public final ReadonlyStateFlow currentMedia;
    public final ReadonlyStateFlow hasActiveMediaOrRecommendation;
    public final ReadonlyStateFlow hasAnyMediaOrRecommendation;
    public final MediaDataFilterImpl mediaDataFilter;
    public final MediaDataProcessor mediaDataProcessor;
    public final MediaDeviceManager mediaDeviceManager;
    public final MediaFilterRepository mediaFilterRepository;
    public final MediaFlags mediaFlags;

    public MediaCarouselInteractor(CoroutineScope coroutineScope, MediaDataProcessor mediaDataProcessor, MediaTimeoutListener mediaTimeoutListener, MediaResumeListener mediaResumeListener, MediaSessionBasedFilter mediaSessionBasedFilter, MediaDeviceManager mediaDeviceManager, MediaDataCombineLatest mediaDataCombineLatest, MediaDataFilterImpl mediaDataFilterImpl, MediaFilterRepository mediaFilterRepository, MediaFlags mediaFlags) {
        this.mediaDataProcessor = mediaDataProcessor;
        this.mediaDeviceManager = mediaDeviceManager;
        this.mediaDataFilter = mediaDataFilterImpl;
        this.mediaFilterRepository = mediaFilterRepository;
        this.mediaFlags = mediaFlags;
        ReadonlyStateFlow readonlyStateFlow = mediaFilterRepository.selectedUserEntries;
        MediaCarouselInteractor$hasActiveMediaOrRecommendation$1 mediaCarouselInteractor$hasActiveMediaOrRecommendation$1 = new MediaCarouselInteractor$hasActiveMediaOrRecommendation$1(4, null);
        ReadonlyStateFlow readonlyStateFlow2 = mediaFilterRepository.smartspaceMediaData;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(readonlyStateFlow, readonlyStateFlow2, mediaFilterRepository.reactivatedId, mediaCarouselInteractor$hasActiveMediaOrRecommendation$1);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.hasActiveMediaOrRecommendation = FlowKt.stateIn(combine, coroutineScope, WhileSubscribed$default, bool);
        this.hasAnyMediaOrRecommendation = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, readonlyStateFlow2, new MediaCarouselInteractor$hasAnyMediaOrRecommendation$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.currentMedia = mediaFilterRepository.currentMedia;
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void addListener(MediaDataManager.Listener listener) {
        this.mediaDataFilter._listeners.add(listener);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean dismissMediaData(String str, long j, boolean z) {
        return this.mediaDataProcessor.dismissMediaData(str, j, z);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void dismissSmartspaceRecommendation(long j, String str) {
        this.mediaDataProcessor.dismissSmartspaceRecommendation(j, str);
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.mediaDeviceManager.dump(printWriter);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasActiveMedia() {
        return this.mediaFilterRepository.hasActiveMedia();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasActiveMediaOrRecommendation() {
        MediaFilterRepository mediaFilterRepository = this.mediaFilterRepository;
        Map map = (Map) mediaFilterRepository._selectedUserEntries.getValue();
        if (!map.isEmpty()) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                if (((MediaData) ((Map.Entry) it.next()).getValue()).active) {
                    break;
                }
            }
        }
        return mediaFilterRepository.isRecommendationActive() && (((SmartspaceMediaData) mediaFilterRepository._smartspaceMediaData.getValue()).isValid() || mediaFilterRepository._reactivatedId.getValue() != null);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasAnyMedia() {
        return !((Map) this.mediaFilterRepository._selectedUserEntries.getValue()).entrySet().isEmpty();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasAnyMediaOrRecommendation() {
        return ((Boolean) this.hasAnyMediaOrRecommendation.getValue()).booleanValue();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean isRecommendationActive() {
        return this.mediaFilterRepository.isRecommendationActive();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onNotificationAdded(StatusBarNotification statusBarNotification, String str) {
        this.mediaDataProcessor.onNotificationAdded(statusBarNotification, str);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onNotificationRemoved(String str) {
        this.mediaDataProcessor.onNotificationRemoved(str);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onSwipeToDismiss() {
        throw new IllegalStateException("Code path not supported when SceneContainerFlag is enabled");
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void removeListener(MediaDataManager.Listener listener) {
        this.mediaDataFilter._listeners.remove(listener);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
