package com.android.systemui.accessibility.qs;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.ColorCorrectionTileMapper;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class QSAccessibilityModule_Companion_ProvideColorCorrectionTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideColorCorrectionTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, ColorCorrectionTileMapper colorCorrectionTileMapper, ColorCorrectionTileDataInteractor colorCorrectionTileDataInteractor, ColorCorrectionUserActionInteractor colorCorrectionUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("color_correction"), colorCorrectionUserActionInteractor, colorCorrectionTileDataInteractor, colorCorrectionTileMapper);
    }
}
