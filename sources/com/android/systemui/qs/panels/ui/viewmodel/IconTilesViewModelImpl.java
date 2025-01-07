package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.qs.panels.domain.interactor.IconTilesInteractor;
import com.android.systemui.qs.panels.domain.interactor.QSPreferencesInteractor;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconTilesViewModelImpl implements IconTilesViewModel {
    public final IconTilesInteractor interactor;
    public final ReadonlyStateFlow largeTiles;

    public IconTilesViewModelImpl(IconTilesInteractor iconTilesInteractor) {
        this.interactor = iconTilesInteractor;
        this.largeTiles = iconTilesInteractor.largeTilesSpecs;
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final StateFlow getLargeTiles() {
        return this.largeTiles;
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final boolean isIconTile(TileSpec tileSpec) {
        return !((Set) this.interactor.largeTilesSpecs.getValue()).contains(tileSpec);
    }

    @Override // com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel
    public final void resize(TileSpec tileSpec, boolean z) {
        IconTilesInteractor iconTilesInteractor = this.interactor;
        if (iconTilesInteractor.currentTilesInteractor.getCurrentTilesSpecs().contains(tileSpec)) {
            ReadonlyStateFlow readonlyStateFlow = iconTilesInteractor.largeTilesSpecs;
            boolean contains = ((Set) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).contains(tileSpec);
            MutableStateFlow mutableStateFlow = readonlyStateFlow.$$delegate_0;
            QSPreferencesInteractor qSPreferencesInteractor = iconTilesInteractor.preferencesInteractor;
            if (z && contains) {
                qSPreferencesInteractor.setLargeTilesSpecs(SetsKt.minus((Set) ((StateFlowImpl) mutableStateFlow).getValue(), tileSpec));
            } else {
                if (z || contains) {
                    return;
                }
                qSPreferencesInteractor.setLargeTilesSpecs(SetsKt.plus((Set) ((StateFlowImpl) mutableStateFlow).getValue(), tileSpec));
            }
        }
    }
}
