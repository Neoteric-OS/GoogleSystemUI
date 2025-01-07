package com.android.wm.shell.bubbles;

import android.view.InputMonitor;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubblesNavBarGestureTracker$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BubblesNavBarGestureTracker f$0;

    @Override // java.lang.Runnable
    public final void run() {
        BubblesNavBarGestureTracker bubblesNavBarGestureTracker = this.f$0;
        bubblesNavBarGestureTracker.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -4230696345096735024L, 0, null);
        }
        InputMonitor inputMonitor = bubblesNavBarGestureTracker.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.pilferPointers();
        }
    }
}
