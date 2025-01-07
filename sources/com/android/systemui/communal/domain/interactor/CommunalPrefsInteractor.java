package com.android.systemui.communal.domain.interactor;

import com.android.systemui.communal.data.repository.CommunalPrefsRepositoryImpl;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalPrefsInteractor {
    public final ReadonlyStateFlow isCtaDismissed;
    public final CommunalPrefsRepositoryImpl repository;
    public final UserTracker userTracker;

    public CommunalPrefsInteractor(CoroutineScope coroutineScope, CommunalPrefsRepositoryImpl communalPrefsRepositoryImpl, SelectedUserInteractor selectedUserInteractor, UserTracker userTracker, TableLogBuffer tableLogBuffer) {
        this.repository = communalPrefsRepositoryImpl;
        this.userTracker = userTracker;
        this.isCtaDismissed = FlowKt.stateIn(DiffableKt.logDiffsForTable((Flow) FlowKt.transformLatest(selectedUserInteractor.selectedUserInfo, new CommunalPrefsInteractor$special$$inlined$flatMapLatest$1(null, this)), tableLogBuffer, "", "isCtaDismissed", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }
}
