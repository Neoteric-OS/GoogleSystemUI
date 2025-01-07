package com.google.android.systemui.columbus.legacy.gates;

import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TransientGate extends Gate {
    public StandaloneCoroutine currentJob;

    public final void blockForMillis(long j) {
        StandaloneCoroutine standaloneCoroutine = this.currentJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.currentJob = BuildersKt.launch$default(this.coroutineScope, null, null, new TransientGate$blockForMillis$1(this, j, null), 3);
    }
}
