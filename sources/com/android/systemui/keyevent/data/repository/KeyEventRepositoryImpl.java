package com.android.systemui.keyevent.data.repository;

import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyEventRepositoryImpl {
    public final CommandQueue commandQueue;
    public final Flow isPowerButtonDown = FlowConflatedKt.conflatedCallbackFlow(new KeyEventRepositoryImpl$isPowerButtonDown$1(this, null));

    public KeyEventRepositoryImpl(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }
}
