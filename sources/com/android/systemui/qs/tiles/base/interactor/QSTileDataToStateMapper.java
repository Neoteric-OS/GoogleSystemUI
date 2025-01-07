package com.android.systemui.qs.tiles.base.interactor;

import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSTileDataToStateMapper {
    QSTileState map(QSTileConfig qSTileConfig, Object obj);
}
