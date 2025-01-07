package com.android.systemui.statusbar.policy;

import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class HeadsUpManagerExtKt {
    public static final Flow getHeadsUpEvents(HeadsUpManager headsUpManager) {
        return FlowConflatedKt.conflatedCallbackFlow(new HeadsUpManagerExtKt$headsUpEvents$1(headsUpManager, null));
    }
}
