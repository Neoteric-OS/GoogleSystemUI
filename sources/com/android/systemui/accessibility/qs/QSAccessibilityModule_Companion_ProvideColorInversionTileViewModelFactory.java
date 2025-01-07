package com.android.systemui.accessibility.qs;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.inversion.domain.ColorInversionTileMapper;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class QSAccessibilityModule_Companion_ProvideColorInversionTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideColorInversionTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, ColorInversionTileMapper colorInversionTileMapper, ColorInversionTileDataInteractor colorInversionTileDataInteractor, ColorInversionUserActionInteractor colorInversionUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("inversion"), colorInversionUserActionInteractor, colorInversionTileDataInteractor, colorInversionTileMapper);
    }
}
