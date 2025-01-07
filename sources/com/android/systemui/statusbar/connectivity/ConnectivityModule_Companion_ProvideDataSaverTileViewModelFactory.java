package com.android.systemui.statusbar.connectivity;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileDataInteractor;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConnectivityModule_Companion_ProvideDataSaverTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideDataSaverTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, DataSaverTileMapper dataSaverTileMapper, DataSaverTileDataInteractor dataSaverTileDataInteractor, DataSaverTileUserActionInteractor dataSaverTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("saver"), dataSaverTileUserActionInteractor, dataSaverTileDataInteractor, dataSaverTileMapper);
    }
}
