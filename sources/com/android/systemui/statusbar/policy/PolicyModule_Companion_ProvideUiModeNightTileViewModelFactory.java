package com.android.systemui.statusbar.policy;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PolicyModule_Companion_ProvideUiModeNightTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideUiModeNightTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, UiModeNightTileMapper uiModeNightTileMapper, UiModeNightTileDataInteractor uiModeNightTileDataInteractor, UiModeNightTileUserActionInteractor uiModeNightTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("dark"), uiModeNightTileUserActionInteractor, uiModeNightTileDataInteractor, uiModeNightTileMapper);
    }
}
