package androidx.core.view;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VelocityTrackerCompat {
    public static final Map sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());
}
