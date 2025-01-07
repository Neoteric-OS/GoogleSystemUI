package com.android.systemui.screenrecord;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.interactor.ScreenRecordTileDataInteractor;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.interactor.ScreenRecordTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ScreenRecordModule_Companion_ProvideScreenRecordTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideScreenRecordTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, ScreenRecordTileMapper screenRecordTileMapper, ScreenRecordTileDataInteractor screenRecordTileDataInteractor, ScreenRecordTileUserActionInteractor screenRecordTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("screenrecord"), screenRecordTileUserActionInteractor, screenRecordTileDataInteractor, screenRecordTileMapper);
    }
}
