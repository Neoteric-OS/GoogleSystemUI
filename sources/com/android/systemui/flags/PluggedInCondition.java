package com.android.systemui.flags;

import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import dagger.Lazy;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PluggedInCondition {
    public final Lazy batteryControllerLazy;
    public final Flow canRestartNow;

    public PluggedInCondition(Lazy lazy) {
        this.batteryControllerLazy = lazy;
        FlowConflatedKt.conflatedCallbackFlow(new PluggedInCondition$canRestartNow$1(this, null));
    }
}
