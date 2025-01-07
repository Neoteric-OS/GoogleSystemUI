package com.google.android.systemui.biometrics.domain;

import com.android.internal.util.LatencyTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LatencyTrackerWrapper {
    public final int cuj;
    public final LatencyTracker tracker;

    public LatencyTrackerWrapper(LatencyTracker latencyTracker, int i) {
        this.tracker = latencyTracker;
        this.cuj = i;
    }
}
