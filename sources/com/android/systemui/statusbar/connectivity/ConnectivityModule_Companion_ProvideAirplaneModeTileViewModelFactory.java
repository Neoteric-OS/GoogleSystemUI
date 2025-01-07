package com.android.systemui.statusbar.connectivity;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.airplane.domain.AirplaneModeMapper;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConnectivityModule_Companion_ProvideAirplaneModeTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideAirplaneModeTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, AirplaneModeMapper airplaneModeMapper, AirplaneModeTileDataInteractor airplaneModeTileDataInteractor, AirplaneModeTileUserActionInteractor airplaneModeTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("airplane"), airplaneModeTileUserActionInteractor, airplaneModeTileDataInteractor, airplaneModeMapper);
    }
}
