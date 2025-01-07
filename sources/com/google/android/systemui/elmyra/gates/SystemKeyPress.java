package com.google.android.systemui.elmyra.gates;

import android.content.res.Resources;
import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemKeyPress extends TransientGate {
    public final int[] mBlockingKeys;
    public final CommandQueue mCommandQueue;
    public final AnonymousClass1 mCommandQueueCallbacks;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.elmyra.gates.SystemKeyPress$1] */
    public SystemKeyPress(Resources resources, DelayableExecutor delayableExecutor, int i, CommandQueue commandQueue) {
        super(delayableExecutor, i);
        this.mCommandQueueCallbacks = new CommandQueue.Callbacks() { // from class: com.google.android.systemui.elmyra.gates.SystemKeyPress.1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void handleSystemKey(KeyEvent keyEvent) {
                int i2 = 0;
                while (true) {
                    SystemKeyPress systemKeyPress = SystemKeyPress.this;
                    int[] iArr = systemKeyPress.mBlockingKeys;
                    if (i2 >= iArr.length) {
                        return;
                    }
                    if (iArr[i2] == keyEvent.getKeyCode()) {
                        systemKeyPress.block();
                        return;
                    }
                    i2++;
                }
            }
        };
        this.mBlockingKeys = resources.getIntArray(R.array.elmyra_blocking_system_keys);
        this.mCommandQueue = commandQueue;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this.mCommandQueueCallbacks);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this.mCommandQueueCallbacks);
    }
}
