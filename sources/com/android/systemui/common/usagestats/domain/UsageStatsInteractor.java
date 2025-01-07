package com.android.systemui.common.usagestats.domain;

import com.android.systemui.common.usagestats.data.repository.UsageStatsRepositoryImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.time.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UsageStatsInteractor {
    public final UsageStatsRepositoryImpl repository;
    public final SystemClock systemClock;
    public final UserTracker userTracker;

    public UsageStatsInteractor(UserTracker userTracker, UsageStatsRepositoryImpl usageStatsRepositoryImpl, SystemClock systemClock) {
        this.userTracker = userTracker;
        this.repository = usageStatsRepositoryImpl;
        this.systemClock = systemClock;
    }
}
