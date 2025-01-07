package com.android.systemui.qs.tiles.base.interactor;

import android.os.UserHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSTileDataInteractor {
    Flow availability(UserHandle userHandle);

    Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow);
}
