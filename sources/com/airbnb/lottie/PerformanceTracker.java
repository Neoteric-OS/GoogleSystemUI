package com.airbnb.lottie;

import androidx.collection.ArraySet;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PerformanceTracker {
    public boolean enabled = false;
    public final ArraySet frameListeners = new ArraySet(0);
    public final Map layerRenderTimes = new HashMap();
}
