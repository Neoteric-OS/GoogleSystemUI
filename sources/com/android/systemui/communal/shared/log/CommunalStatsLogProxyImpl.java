package com.android.systemui.communal.shared.log;

import android.util.StatsEvent;
import android.util.StatsLog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalStatsLogProxyImpl {
    public final void writeCommunalHubWidgetEventReported(String str, int i, int i2) {
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(908);
        newBuilder.writeInt(i);
        newBuilder.writeString(str);
        newBuilder.writeInt(i2);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }
}
