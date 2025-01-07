package com.android.systemui.communal.data.repository;

import com.android.systemui.communal.data.model.CommunalMediaModel;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalMediaRepositoryImpl implements MediaDataManager.Listener {
    public final StateFlowImpl _mediaModel;
    public final MediaDataManager mediaDataManager;
    public final SafeFlow mediaModel;

    public CommunalMediaRepositoryImpl(MediaDataManager mediaDataManager, TableLogBuffer tableLogBuffer) {
        this.mediaDataManager = mediaDataManager;
        CommunalMediaModel communalMediaModel = CommunalMediaModel.INACTIVE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(communalMediaModel);
        this._mediaModel = MutableStateFlow;
        this.mediaModel = DiffableKt.logDiffsForTable(MutableStateFlow, tableLogBuffer, "", communalMediaModel);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
        updateMediaModel(mediaData);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        updateMediaModel(null);
    }

    public final void updateMediaModel(MediaData mediaData) {
        boolean hasAnyMediaOrRecommendation = this.mediaDataManager.hasAnyMediaOrRecommendation();
        StateFlowImpl stateFlowImpl = this._mediaModel;
        if (hasAnyMediaOrRecommendation) {
            CommunalMediaModel communalMediaModel = new CommunalMediaModel(mediaData != null ? mediaData.createdTimestampMillis : 0L, true);
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, communalMediaModel);
        } else {
            CommunalMediaModel communalMediaModel2 = CommunalMediaModel.INACTIVE;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, communalMediaModel2);
        }
    }
}
