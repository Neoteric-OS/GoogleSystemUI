package com.android.systemui.common.usagestats.data.repository;

import android.app.usage.UsageStatsManager;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.common.usagestats.data.model.UsageStatsQuery;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UsageStatsRepositoryImpl {
    public final CoroutineContext bgContext;
    public final UsageStatsManager usageStatsManager;

    public UsageStatsRepositoryImpl(CoroutineContext coroutineContext, UsageStatsManager usageStatsManager) {
        this.bgContext = coroutineContext;
        this.usageStatsManager = usageStatsManager;
    }

    public final Object queryActivityEvents(UsageStatsQuery usageStatsQuery, Continuation continuation) {
        return CoroutineTracingKt.withContext(this.bgContext, new UsageStatsRepositoryImpl$queryActivityEvents$2(usageStatsQuery, this, null), (ContinuationImpl) continuation);
    }
}
