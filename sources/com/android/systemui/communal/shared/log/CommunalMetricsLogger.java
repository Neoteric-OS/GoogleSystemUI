package com.android.systemui.communal.shared.log;

import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalMetricsLogger {
    public final List loggablePrefixes;
    public final CommunalStatsLogProxyImpl statsLogProxy;

    public CommunalMetricsLogger(List list, CommunalStatsLogProxyImpl communalStatsLogProxyImpl) {
        this.loggablePrefixes = list;
        this.statsLogProxy = communalStatsLogProxyImpl;
    }

    public final boolean isLoggable(String str) {
        List list = this.loggablePrefixes;
        if (list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (str.startsWith((String) it.next())) {
                return true;
            }
        }
        return false;
    }
}
