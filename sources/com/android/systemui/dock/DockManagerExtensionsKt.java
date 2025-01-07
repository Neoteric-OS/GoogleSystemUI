package com.android.systemui.dock;

import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DockManagerExtensionsKt {
    public static final Flow retrieveIsDocked(DockManager dockManager) {
        return FlowConflatedKt.conflatedCallbackFlow(new DockManagerExtensionsKt$retrieveIsDocked$1(dockManager, null));
    }
}
