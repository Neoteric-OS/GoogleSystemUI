package com.android.systemui.statusbar.connectivity;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper;
import com.android.systemui.qs.tiles.impl.internet.domain.interactor.InternetTileDataInteractor;
import com.android.systemui.qs.tiles.impl.internet.domain.interactor.InternetTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConnectivityModule_Companion_ProvideInternetTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideInternetTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, InternetTileMapper internetTileMapper, InternetTileDataInteractor internetTileDataInteractor, InternetTileUserActionInteractor internetTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("internet"), internetTileUserActionInteractor, internetTileDataInteractor, internetTileMapper);
    }
}
