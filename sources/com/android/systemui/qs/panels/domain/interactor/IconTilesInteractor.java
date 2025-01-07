package com.android.systemui.qs.panels.domain.interactor;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.qs.panels.data.repository.DefaultLargeTilesRepositoryImpl;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconTilesInteractor {
    public final CurrentTilesInteractor currentTilesInteractor;
    public final ReadonlyStateFlow largeTilesSpecs;
    public final LogBuffer logBuffer;
    public final QSPreferencesInteractor preferencesInteractor;

    public IconTilesInteractor(DefaultLargeTilesRepositoryImpl defaultLargeTilesRepositoryImpl, CurrentTilesInteractor currentTilesInteractor, QSPreferencesInteractor qSPreferencesInteractor, LogBuffer logBuffer, CoroutineScope coroutineScope) {
        this.currentTilesInteractor = currentTilesInteractor;
        this.preferencesInteractor = qSPreferencesInteractor;
        this.logBuffer = logBuffer;
        this.largeTilesSpecs = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(qSPreferencesInteractor.largeTilesSpecs, ((CurrentTilesInteractorImpl) currentTilesInteractor).currentTiles, new IconTilesInteractor$largeTilesSpecs$1(this, null)), new IconTilesInteractor$largeTilesSpecs$2(this, null), 0), coroutineScope, SharingStarted.Companion.Eagerly, defaultLargeTilesRepositoryImpl.defaultLargeTiles);
    }
}
