package com.android.systemui.shade.domain.interactor;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.data.repository.ShadeHeaderClockRepository;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderClockInteractor {
    public final ActivityStarter activityStarter;
    public final ShadeHeaderClockRepository repository;

    public ShadeHeaderClockInteractor(ShadeHeaderClockRepository shadeHeaderClockRepository, ActivityStarter activityStarter) {
        this.activityStarter = activityStarter;
    }
}
