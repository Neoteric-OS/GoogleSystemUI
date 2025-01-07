package com.android.systemui.statusbar.policy;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.location.domain.LocationTileMapper;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileDataInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PolicyModule_Companion_ProvideLocationTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideLocationTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, LocationTileMapper locationTileMapper, LocationTileDataInteractor locationTileDataInteractor, LocationTileUserActionInteractor locationTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("location"), locationTileUserActionInteractor, locationTileDataInteractor, locationTileMapper);
    }
}
