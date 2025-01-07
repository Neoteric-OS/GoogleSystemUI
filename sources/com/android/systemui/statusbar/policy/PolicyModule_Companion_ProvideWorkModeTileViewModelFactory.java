package com.android.systemui.statusbar.policy;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.work.domain.interactor.WorkModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.work.domain.interactor.WorkModeTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PolicyModule_Companion_ProvideWorkModeTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideWorkModeTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, WorkModeTileMapper workModeTileMapper, WorkModeTileDataInteractor workModeTileDataInteractor, WorkModeTileUserActionInteractor workModeTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("work"), workModeTileUserActionInteractor, workModeTileDataInteractor, workModeTileMapper);
    }
}
