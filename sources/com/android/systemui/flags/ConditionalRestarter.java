package com.android.systemui.flags;

import java.util.Set;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConditionalRestarter {
    public final Set conditions;
    public final String pendingReason;
    public final SystemExitRestarter systemExitRestarter;

    public ConditionalRestarter(SystemExitRestarter systemExitRestarter, Set set, CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
    }
}
