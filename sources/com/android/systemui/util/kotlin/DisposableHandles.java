package com.android.systemui.util.kotlin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisposableHandles implements DisposableHandle {
    public final List handles = new ArrayList();

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        Iterator it = this.handles.iterator();
        while (it.hasNext()) {
            ((DisposableHandle) it.next()).dispose();
        }
        this.handles.clear();
    }

    public final void plusAssign(DisposableHandle disposableHandle) {
        this.handles.add(disposableHandle);
    }
}
