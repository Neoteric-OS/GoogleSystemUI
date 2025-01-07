package com.android.systemui.qs.tiles.impl.alarm.domain.interactor;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlarmTileDataInteractor implements QSTileDataInteractor {
    public final NextAlarmController alarmController;
    public final DateFormatUtil dateFormatUtil;

    public AlarmTileDataInteractor(NextAlarmController nextAlarmController, DateFormatUtil dateFormatUtil) {
        this.alarmController = nextAlarmController;
        this.dateFormatUtil = dateFormatUtil;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowConflatedKt.conflatedCallbackFlow(new AlarmTileDataInteractor$tileData$1(this, null));
    }
}
