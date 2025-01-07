package com.android.systemui.qs.panels.domain.interactor;

import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.LatestConflatedKt;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NewTilesAvailabilityInteractor {
    public final Map availabilityInteractors;

    public NewTilesAvailabilityInteractor(Map map, UserRepositoryImpl userRepositoryImpl) {
        this.availabilityInteractors = map;
        LatestConflatedKt.flatMapLatestConflated(new NewTilesAvailabilityInteractor$newTilesAvailable$2(this, null), new NewTilesAvailabilityInteractor$special$$inlined$map$1(0, userRepositoryImpl.selectedUserInfo));
    }
}
