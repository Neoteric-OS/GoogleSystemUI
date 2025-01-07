package com.android.systemui.statusbar.policy;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileDataInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileUserActionInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PolicyModule_Companion_ProvideAlarmTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideAlarmTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, AlarmTileMapper alarmTileMapper, AlarmTileDataInteractor alarmTileDataInteractor, AlarmTileUserActionInteractor alarmTileUserActionInteractor) {
        return qSTileViewModelFactory$Static.create(TileSpec.Companion.create("alarm"), alarmTileUserActionInteractor, alarmTileDataInteractor, alarmTileMapper);
    }
}
