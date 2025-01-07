package com.android.systemui.communal;

import android.app.StatsManager;
import android.util.StatsEvent;
import com.android.systemui.CoreStartable;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.communal.shared.log.CommunalMetricsLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalMetricsStartable implements CoreStartable, StatsManager.StatsPullAtomCallback {
    public final Executor bgExecutor;
    public final CommunalInteractor communalInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final CommunalMetricsLogger metricsLogger;
    public final StatsManager statsManager;

    public CommunalMetricsStartable(Executor executor, CommunalSettingsInteractor communalSettingsInteractor, CommunalInteractor communalInteractor, StatsManager statsManager, CommunalMetricsLogger communalMetricsLogger) {
        this.bgExecutor = executor;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.communalInteractor = communalInteractor;
        this.statsManager = statsManager;
        this.metricsLogger = communalMetricsLogger;
    }

    public final int onPullAtom(int i, List list) {
        if (i != 10226) {
            return 1;
        }
        CommunalMetricsLogger communalMetricsLogger = this.metricsLogger;
        List list2 = (List) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new CommunalMetricsStartable$onPullAtom$1(this, null));
        communalMetricsLogger.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            if (communalMetricsLogger.isLoggable((String) obj)) {
                arrayList.add(obj);
            }
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        int size = list2.size();
        communalMetricsLogger.statsLogProxy.getClass();
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(10226);
        newBuilder.writeStringArray(strArr);
        newBuilder.writeInt(size);
        list.add(newBuilder.build());
        return 0;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            this.statsManager.setPullAtomCallback(10226, (StatsManager.PullAtomMetadata) null, this.bgExecutor, this);
        }
    }
}
